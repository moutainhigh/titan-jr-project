package com.fangcang.titanjr.pay.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderKindEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.RefundTypeEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.TransferReqEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.dto.bean.OrgDTO;
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.PermissionRequest;
import com.fangcang.titanjr.dto.request.RefundRequest;
import com.fangcang.titanjr.dto.request.TitanJrRefundRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.request.UnFreeBalanceBatchRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.CheckPermissionResponse;
import com.fangcang.titanjr.dto.response.RefundResponse;
import com.fangcang.titanjr.dto.response.TitanJrRefundResponse;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.pay.constant.TitanConstantDefine;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialRefundService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;

@Component
public class TitanRefundService {
	
	@Resource
	private TitanOrderService titanOrderService;
	
	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;
	
	@Resource 
	private TitanFinancialOrganService titanFinancialOrganService;
	
	@Resource
	private TitanFinancialRefundService titanFinancialRefundService;
	
	@Resource
	private TitanFinancialUserService titanFinancialUserService;
	
	@Resource
	private TitanFinancialUtilService utilService;

	private static final Log log = LogFactory.getLog(TitanRefundService.class);
	private static Map<String,Object> mapLock = new  ConcurrentHashMap<String, Object>();
	
	public RefundResponse orderRefund(RefundRequest refundRequest){
		RefundResponse response = new RefundResponse();
		try {
			//已验证过过机构，去掉
			log.info("1.校验支付密码，退款请求参数refundRequest："+Tools.gsonToString(refundRequest));
			boolean flag = titanFinancialUserService.checkPayPassword( refundRequest.getPayPassword(),refundRequest.getTfsUserid());
			if (!flag) {
				log.error("付款密码错误,tfsuserid:"+refundRequest.getTfsUserid());
				response.putErrorResult(TitanMsgCodeEnum.PAY_PWD_ERROR);
				return response;
			}
			
			this.lockOutTradeNoList(refundRequest.getOrderNo());
			//获取该订单
			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setUserorderid(refundRequest.getOrderNo());

			//该订单不存在或者该单没有支付成功
			log.info("2.校验支付单状态");
			TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
			if (null == transOrderDTO || !OrderStatusEnum.isPaySuccess(transOrderDTO.getStatusid())) {
				log.error("该订单不存在或者该单没有支付成功,orderid:"+transOrderDTO.getOrderid());
				response.putErrorResult(TitanMsgCodeEnum.ORDER_NOT_REFUND);
				return response;
			}
			
			if(OrderStatusEnum.isRefund(transOrderDTO.getStatusid())){
				log.error("该订单已退款，请勿重复退款,orderid:"+transOrderDTO.getOrderid());
				response.putErrorResult(TitanMsgCodeEnum.ORDER_REFUNND_IN_PROCESS);
				return response;
			}
			//查询账户转账金额
			log.info("3.获取转账金额并校验账户余额是否大于转账金额");
			TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
			titanTransferDTO.setPayOrderNo(transOrderDTO.getPayorderno());
			titanTransferDTO.setUserid(transOrderDTO.getUserid());
			titanTransferDTO.setUserrelateid(transOrderDTO.getUserrelateid());
			titanTransferDTO.setStatus(TransferReqEnum.TRANSFER_SUCCESS.getStatus());
			titanTransferDTO = titanOrderService.getTitanTransferDTO(titanTransferDTO);
			if (null == titanTransferDTO) {
				log.error("查询账户转账金额失败");
				response.putErrorResult(TitanMsgCodeEnum.UNEXPECTED_ERROR);
				return response;
			}
			BigDecimal transferAmount = new BigDecimal(titanTransferDTO.getAmount());
			//如果没有解冻操作则需要查询余额
			if (!OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())) {
				AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
				accountBalanceRequest.setUserid(transOrderDTO.getUserrelateid());
				AccountBalanceResponse balanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
				if (!balanceResponse.isResult() || null == balanceResponse.getAccountBalance() ||
						null == balanceResponse.getAccountBalance().get(0)) {
					log.error("查询账户信息失败或者不存在,userid:"+transOrderDTO.getUserrelateid());
					response.putErrorResult(TitanMsgCodeEnum.TITAN_ACCOUNT_NOT_EXISTS);
					return response;
				}

				//账户余额不足不能退款
				BigDecimal balance = new BigDecimal(balanceResponse.getAccountBalance().get(0).getBalanceusable());
				if (balance.subtract(transferAmount).compareTo(BigDecimal.ZERO) == -1) {
					log.error("账户余额余额不足，需先充值,userid:"+transOrderDTO.getUserrelateid());
					response.putErrorResult(TitanMsgCodeEnum.ACCOUNT_BALANCE_NOT_ENOUGH);
					return response;
				}
			}

			Long fee = transOrderDTO.getReceivedfee();
			if (null == fee) {
				fee = 0l;
			}
			log.info("4.组装退款请求参数");
			TitanJrRefundRequest titanJrRefundRequest = new TitanJrRefundRequest();
			titanJrRefundRequest.setInterMerchantCode(transOrderDTO.getConstid());
			titanJrRefundRequest.setMerchantCode(transOrderDTO.getConstid());
			titanJrRefundRequest.setProductId(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			titanJrRefundRequest.setInterProductId(transOrderDTO.getProductid());
			titanJrRefundRequest.setUserId(transOrderDTO.getUserrelateid());
			titanJrRefundRequest.setUserRelateId(transOrderDTO.getUserid());
			titanJrRefundRequest.setTradeAmount(transferAmount.toString());//转账金额
			titanJrRefundRequest.setPayOrderNo(transOrderDTO.getPayorderno());
			titanJrRefundRequest.setTransorderid(transOrderDTO.getTransid());
			titanJrRefundRequest.setFee(fee.toString());
			titanJrRefundRequest.setFreeze(refundRequest.isFreeze());
			titanJrRefundRequest.setTfsUerId(refundRequest.getTfsUserid());
			titanJrRefundRequest.setUserOrderId(transOrderDTO.getUserorderid());
			titanJrRefundRequest.setNotifyUrl(refundRequest.getNotifyUrl());
			titanJrRefundRequest.setBusinessInfo(refundRequest.getBusinessInfo());
			//查看是否有充值单
			titanJrRefundRequest.setOrderNo(transOrderDTO.getOrderid());
			if (transOrderDTO.getTradeamount() != null) {
				titanJrRefundRequest.setRefundAmount(transOrderDTO.getTradeamount().toString());
			}
			log.info("4.1.设置充值单数据");
			TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
			titanOrderPayDTO.setTransorderid(transOrderDTO.getTransid());
			titanOrderPayDTO.setOrderNo(transOrderDTO.getOrderid());
			titanOrderPayDTO = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
			if (null != titanOrderPayDTO) {//有充值单
				if (transOrderDTO.getAmount() != null) {//充值的钱
					titanJrRefundRequest.setRefundAmount(transOrderDTO.getAmount().toString());
				}
				titanJrRefundRequest.setBusiCode(BusiCodeEnum.MerchantRefund.getKey());
				titanJrRefundRequest.setOrderTime(titanOrderPayDTO.getOrderTime());
				titanJrRefundRequest.setVersion(titanOrderPayDTO.getVersion());
				titanJrRefundRequest.setSignType(titanOrderPayDTO.getSignType().toString());
//				titanJrRefundRequest.setIsRealTime(CommonConstant.NOT_REAL_TIME);
			} else {//直接进行账户退款,退款到账户余额
				titanJrRefundRequest.setToBankCardOrAccount(RefundTypeEnum.REFUND_ACCOUNT.type);
			}

			//解冻操作
			log.info("5.交易单若冻结则执行解冻");
			if (OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())) {//立即解冻
				List<FundFreezeDTO> fundFreezeList = getFundFreezeList(transOrderDTO.getOrderid());
				if (null == fundFreezeList) {
					log.error("冻结单查询失败，orderid:"+transOrderDTO.getOrderid());
					response.putErrorResult(TitanMsgCodeEnum.REFUND_FAIL);
					return response;
				}

				UnFreeBalanceBatchRequest unFreeBalanceBatchRequest = new UnFreeBalanceBatchRequest();
				unFreeBalanceBatchRequest.setFundFreezeDTOList(fundFreezeList);
				flag = titanFinancialAccountService.unfreezeAccountBalanceOne(unFreeBalanceBatchRequest);
				if (!flag) {
					log.error("资金解冻失败，orderid:"+transOrderDTO.getOrderid());
					response.putErrorResult(TitanMsgCodeEnum.REFUND_UNFREEZE_FAIL);
					return response;
				}
				//标识该订单有解冻操作
				refundRequest.setFreeze(true);
			}
			log.info("6.调用服务退款");
			TitanJrRefundResponse titanJrRefundResponse = titanFinancialRefundService.refund(titanJrRefundRequest);
			if (!titanJrRefundResponse.isResult()) {
				log.error("退款操作失败:订单orderid:"+transOrderDTO.getOrderid()+",错误信息："+ titanJrRefundResponse.getReturnMessage());
				response.putErrorResult(TitanMsgCodeEnum.REFUND_FAIL);
				return response;
			}
			log.info("7.成功退款后更新交易单状态,account:" + titanJrRefundRequest.getToBankCardOrAccount()
					+ ",realTime:" + titanJrRefundRequest.getIsRealTime());
			TransOrderDTO transOrder = new TransOrderDTO();
			transOrder.setStatusid(OrderStatusEnum.REFUND_IN_PROCESS.getStatus());
			if (RefundTypeEnum.REFUND_ACCOUNT.type.equals(titanJrRefundRequest.getToBankCardOrAccount())) {//余额支付的退款
				transOrder.setStatusid(OrderStatusEnum.REFUND_SUCCESS.getStatus());
			}
			log.info("退款成功修改订单状态,Transid:" + transOrderDTO.getTransid() + ",orderId:"+transOrderDTO.getOrderid()+",Statusid:" + transOrder.getStatusid());
			transOrder.setTransid(transOrderDTO.getTransid());
			flag = titanOrderService.updateTransOrder(transOrder);
			if (!flag) {
				log.error("退款单状态更新失败,orderId:"+transOrderDTO.getOrderid());
				utilService.saveOrderException(transOrderDTO.getOrderid(),OrderKindEnum.OrderId, OrderExceptionEnum.Refund_Success_Update_Order_Fail, JSONSerializer.toJSON(transOrder).toString());
			}
			log.info("退款操作成功");
			response.putSuccess();
			return response;
		} catch (Exception e) {
			log.error("退款出现异常", e);
		} finally {
			this.unlockOutTradeNoList(refundRequest.getOrderNo());
		}
		response.putSysError();
		return response;
	}

	private List<FundFreezeDTO> getFundFreezeList(String orderId) {
		if (!StringUtil.isValidString(orderId)) {
			return null;
		}
		FundFreezeDTO fundFreezeDTO = new FundFreezeDTO();
		fundFreezeDTO.setOrderNo(orderId);
		List<FundFreezeDTO> fundFreezeDTOList = titanOrderService.queryFundFreezeDTO(fundFreezeDTO);
		if (null == fundFreezeDTOList || fundFreezeDTOList.size() != 1) {
			return null;
		}
		return fundFreezeDTOList;
	}

	private boolean validateIsTitanOrg(String userId) {
		OrgDTO orgDTO = new OrgDTO();
		orgDTO.setOrgcode(userId);
		orgDTO.setStatusId(1);
		orgDTO = titanFinancialOrganService.queryOrg(orgDTO);
		if (null != orgDTO && StringUtil.isValidString(orgDTO.getUserid())) {
			return true;
		}
		return false;
	}
	
	private boolean validatePermission(String tfsUserId){
		 PermissionRequest permissionRequest = new PermissionRequest();
         permissionRequest.setTfsuserid(tfsUserId);
         permissionRequest.setPermission("1");
         CheckPermissionResponse checkResponse = titanFinancialUserService.checkUserPermission(permissionRequest);
		 if(checkResponse.isPermission()){
			 return true;
		 }
         return false;
	}

	public String refundRequest(RefundRequest refundRequest,Model model){
		//将传入的信息转换为金融信息，为saas商家编码，需设置数据
		log.info("1.开始执行交易单校验");
		if("1".equals(refundRequest.getIsMerchCode())){
			if(this.setupTitanOrgInfo(refundRequest)==null){
				log.error("SaaS商家未绑定金融账户或员工未绑定金融用户");
				return setUpErrorResult(model,TitanMsgCodeEnum.REFUND_CONCERT_FAIL);
			}
		} else {
			boolean orgCheck = this.validateIsTitanOrg(refundRequest.getUserId());
			if(!orgCheck){
				log.error("退款对应金融机构不存在：" + refundRequest.getUserId());
				return setUpErrorResult(model,TitanMsgCodeEnum.TITAN_ACCOUNT_NOT_EXISTS);
			}
		}

		//验证支付人付款权限
		boolean permissionCheck  = this.validatePermission(refundRequest.getTfsUserid());
		if(!permissionCheck){
			log.error("验证该员工无付款权限：" + refundRequest.getTfsUserid());
			return setUpErrorResult(model,TitanMsgCodeEnum.PERMISSION_CHECK_FAILED);
		}
		
		//根据payOrderNo查询相关的单
		log.info("2.开始查询校验交易单状态");
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setPayorderno(refundRequest.getOrderNo());
		TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
		String validateResult = validateTransOrderStatus(model,transOrderDTO);
		if (null != validateResult){
			return validateResult;
		}
		//判断订单是否超过退款时间，
		log.info("3.开始进行交易金额验证");
		if(transOrderDTO.getAmount() !=null && transOrderDTO.getAmount()>0 ){
			TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
			titanOrderPayDTO.setOrderNo(transOrderDTO.getOrderid());
			TitanOrderPayDTO payOrder = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
			if (payOrder != null && StringUtil.isValidString(payOrder.getOrderTime())) {
				try {
					Long orderDate = DateUtil.sdf5.parse(payOrder.getOrderTime()).getTime();
					Long nowDate = new Date().getTime();
					if (nowDate - orderDate > CommonConstant.MS) {
						log.error("该订单已超出退款时限");
						return setUpErrorResult(model, TitanMsgCodeEnum.ORDER_OUT_TIME);
					}
				} catch (ParseException e) {
					log.error("时间转换异常", e);
					return setUpErrorResult(model, TitanMsgCodeEnum.ORDER_OUT_TIME);
				}
			}
		}

		//查询商家的账户余额，看是否能满足退款
		log.info("4.开始进行账户余额校验");
		AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
		accountBalanceRequest.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
		accountBalanceRequest.setUserid(refundRequest.getUserId());
		AccountBalanceResponse accountBalanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
		if(!accountBalanceResponse.isResult()){
			log.error("账户余额查询异常");
			return setUpErrorResult(model, TitanMsgCodeEnum.ORDER_NOT_REFUND);
		}
		
		List<AccountBalance> accountBalanceList  = accountBalanceResponse.getAccountBalance();
		String accountBalance = "";
		for(AccountBalance balance :accountBalanceList){
			if(balance.getProductid().equals(CommonConstant.RS_FANGCANG_PRODUCT_ID)){
				accountBalance = balance.getBalanceusable();
			}
		}

		log.info("5.开始设置请求到页面的参数");
		PayerTypeEnum payerType= PayerTypeEnum.getPayerTypeEnumByKey(transOrderDTO.getPayerType());
		if(payerType !=null && payerType.useReceiverCashDesk()){
			model.addAttribute("paySourceMark", CommonConstant.ISRECIEVEDESK);
		}
		
		model.addAttribute("balanceAmount",accountBalance);
		model.addAttribute("transOrderDTO", transOrderDTO);
		model.addAttribute("refundRequest", refundRequest);
		
		return TitanConstantDefine.REFUND_MAIN_PAGE;
	}

	private String setUpErrorResult(Model model, TitanMsgCodeEnum message){
		model.addAttribute("msg",message.getResMsg());
		return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
	}

	private String validateTransOrderStatus(Model model, TransOrderDTO transOrderDTO){
		if(transOrderDTO ==null){
			log.error("交易单订单不存在");
			return setUpErrorResult(model,TitanMsgCodeEnum.QUERY_LOCAL_ORDER);
		}

		if(OrderStatusEnum.REFUND_IN_PROCESS.getStatus().equals(transOrderDTO.getStatusid())){
			log.error("该订单正在退款中，不能重复退款");
			return setUpErrorResult(model,TitanMsgCodeEnum.ORDER_REFUNND_IN_PROCESS);
		}

		if(OrderStatusEnum.REFUND_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())){
			log.error("该订单退款成功，不能重复退款");
			return setUpErrorResult(model,TitanMsgCodeEnum.REFUND_SUCCESSED);
		}

		if(!(OrderStatusEnum.ORDER_SUCCESS.getStatus().equals(transOrderDTO.getStatusid()) || 

OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid()))){
			log.error("该订单未支付成功，不能退款");
			return setUpErrorResult(model,TitanMsgCodeEnum.ORDER_NOT_REFUND);
		}
		return null;
	}

	private RefundRequest setupTitanOrgInfo(RefundRequest refundRequest){
		
		TitanUserBindInfoDTO bindInfo = new TitanUserBindInfoDTO();
		bindInfo.setFcuserid(Long.parseLong(refundRequest.getTfsUserid()));
		bindInfo.setMerchantcode(refundRequest.getUserId());
		bindInfo = titanFinancialUserService.getUserBindInfoByFcuserid(bindInfo);
		if(bindInfo ==null || bindInfo.getTfsuserid()==null){
			log.error("没有绑定的商家");
			return null;
		}
		
		UserInfoQueryRequest request = new UserInfoQueryRequest();
		request.setTfsUserId(bindInfo.getTfsuserid());
		UserInfoPageResponse response = titanFinancialUserService.queryUserInfoPage(request);
		if(response ==null ){
			log.error("金融信息查询失败");
			return null;
		}
		
		List<TitanUser> userList = response.getTitanUserPaginationSupport().getItemList();
		
		if(userList == null || userList.size() !=1 || userList.get(0)==null){
			log.error("金融信息为空");
			return null;
		}
		
		TitanUser user = userList.get(0);
		
		if(user.getTfsuserid() ==null || !StringUtil.isValidString(user.getUserid())){
			log.error("金融用户不存在");
			return null;
		}
		
		refundRequest.setTfsUserid(user.getTfsuserid().toString());
		refundRequest.setUserId(user.getUserid());
		return refundRequest;
	}
	
	private void lockOutTradeNoList(String out_trade_no) throws InterruptedException {
		synchronized (mapLock) {
			if(mapLock.containsKey(out_trade_no)) {
				synchronized (mapLock.get(out_trade_no)) 
				{
					mapLock.get(out_trade_no).wait();
				}
			}
			else{
				mapLock.put(out_trade_no, new Object());
			}
			
		}
	}
	
	private void unlockOutTradeNoList(String out_trade_no) {
		if(mapLock.containsKey(out_trade_no)){
			synchronized (mapLock.get(out_trade_no)) {
				mapLock.remove(out_trade_no).notifyAll();
			}
		}
	}
}
