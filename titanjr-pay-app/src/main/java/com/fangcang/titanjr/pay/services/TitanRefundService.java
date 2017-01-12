package com.fangcang.titanjr.pay.services;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.fangcang.titanjr.common.enums.FreezeConditionCodeEnum;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.RefundTypeEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.TransferReqEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.bean.OrgDTO;
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.PermissionRequest;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.RefundRequest;
import com.fangcang.titanjr.dto.request.TitanJrRefundRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.request.UnFreeBalanceBatchRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.CheckPermissionResponse;
import com.fangcang.titanjr.dto.response.FreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.OrganBriefResponse;
import com.fangcang.titanjr.dto.response.RefundResponse;
import com.fangcang.titanjr.dto.response.TitanJrRefundResponse;
import com.fangcang.titanjr.dto.response.UserInfoPageResponse;
import com.fangcang.titanjr.entity.TitanUser;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.enums.PayTypeEnum;
import com.fangcang.titanjr.pay.constant.TitanConstantDefine;
import com.fangcang.titanjr.pay.controller.TitanTradeController;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialRefundService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
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
	
	
	private static final Log log = LogFactory
			.getLog(TitanTradeController.class);
	
	public RefundResponse orderRefund(RefundRequest refundRequest){
		RefundResponse response = new RefundResponse();
		try{
			//可以验证该身份
			boolean flag = this.validateUserId(refundRequest.getUserId());
			
			if(!flag){
				log.error("该账户不存在");
				response.putErrorResult(TitanMsgCodeEnum.AUTHENTITCATION_FAILED);
				return response;
			}
			
			flag = this.validatePsd(refundRequest.getTfsUserid(), refundRequest.getPayPassword());
			
			
			if(!flag){
				log.error("付款密码错误");
				response.putErrorResult(TitanMsgCodeEnum.PAY_PWD_ERROR);
				return response;
			}
			
			
			//获取该订单
			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setUserorderid(refundRequest.getOrderNo());
			
			//该订单不存在或者该单没有支付成功
			TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
			if(null == transOrderDTO || !OrderStatusEnum.isPaySuccess(transOrderDTO.getStatusid())){
				log.error("该订单不存在或者该单没有支付成功");
				response.putErrorResult(TitanMsgCodeEnum.ORDER_NOT_REFUND);
				return response;
			}
			
			//查询账户转账金额
			TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
			titanTransferDTO.setPayOrderNo(transOrderDTO.getPayorderno());
			titanTransferDTO.setUserid(transOrderDTO.getUserid());
			titanTransferDTO.setUserrelateid(transOrderDTO.getUserrelateid());
			titanTransferDTO.setStatus(TransferReqEnum.TRANSFER_SUCCESS.getStatus());
			titanTransferDTO = titanOrderService.getTitanTransferDTO(titanTransferDTO);
			if(null ==titanTransferDTO){
				log.error("查询账户转账金额失败");
				response.putErrorResult(TitanMsgCodeEnum.UNEXPECTED_ERROR);
				return response;
			}
			BigDecimal tradeAmount  = new BigDecimal(titanTransferDTO.getAmount());
			
			if(!OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())){//如果没有解冻操作则需要查询余额
				AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
			    accountBalanceRequest.setUserid(transOrderDTO.getUserrelateid());
			    AccountBalanceResponse balanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
			    if(!balanceResponse.isResult() || null == balanceResponse.getAccountBalance() || null == balanceResponse.getAccountBalance().get(0)){
					log.error("查询余额失败");
			    	response.putErrorResult(TitanMsgCodeEnum.TITAN_ACCOUNT_NOT_EXISTS);
					return response;
				}
			    
			    //账户余额不足不能退款
			    AccountBalance accountBalace =  balanceResponse.getAccountBalance().get(0);
				BigDecimal balance = new BigDecimal(accountBalace.getBalanceusable());
				
				
				if(balance.subtract(tradeAmount).compareTo(BigDecimal.ZERO)==-1){
					log.error("查询余额不足");
					response.putErrorResult(TitanMsgCodeEnum.ACCOUNT_BALANCE_NOT_ENOUGH);
					return response;
				}
			}
			
			Long fee = transOrderDTO.getReceivedfee();
			if(null == fee){
				fee = (long)0;
			}
		
			TitanJrRefundRequest titanJrRefundRequest  = new TitanJrRefundRequest();
			titanJrRefundRequest.setInterMerchantCode(transOrderDTO.getConstid());
			titanJrRefundRequest.setMerchantCode(transOrderDTO.getConstid());
			titanJrRefundRequest.setProductId(CommonConstant.RS_FANGCANG_PRODUCT_ID);
			titanJrRefundRequest.setInterProductId(transOrderDTO.getProductid());
			titanJrRefundRequest.setUserId(transOrderDTO.getUserrelateid());
			titanJrRefundRequest.setUserRelateId(transOrderDTO.getUserid());
			titanJrRefundRequest.setTradeAmount(tradeAmount.toString());
			titanJrRefundRequest.setPayOrderNo(transOrderDTO.getPayorderno());
			titanJrRefundRequest.setTransorderid(transOrderDTO.getTransid());
			titanJrRefundRequest.setFee(fee.toString());
			titanJrRefundRequest.setFreeze(refundRequest.isFreeze());
			titanJrRefundRequest.setTfsUerId(refundRequest.getTfsUserid());
			titanJrRefundRequest.setUserOrderId(transOrderDTO.getUserorderid());
			titanJrRefundRequest.setNotifyUrl(refundRequest.getNotifyUrl());
			titanJrRefundRequest.setBusinessInfo(refundRequest.getBusinessInfo());
			//查看是否有充值单
			TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
			titanOrderPayDTO.setTransorderid(transOrderDTO.getTransid());
			titanOrderPayDTO.setOrderNo(transOrderDTO.getOrderid());
			titanOrderPayDTO = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
			
			titanJrRefundRequest.setOrderNo(transOrderDTO.getOrderid());
			if(transOrderDTO.getTradeamount() !=null){
				titanJrRefundRequest.setRefundAmount(transOrderDTO.getTradeamount().toString());
			}
			if(null !=titanOrderPayDTO){//有充值单
				if(transOrderDTO.getAmount()!=null){//充值的钱
					titanJrRefundRequest.setRefundAmount(transOrderDTO.getAmount().toString());
				}
				titanJrRefundRequest.setBusiCode(BusiCodeEnum.MerchantRefund.getKey());
				titanJrRefundRequest.setOrderTime(titanOrderPayDTO.getOrderTime());
				titanJrRefundRequest.setVersion(titanOrderPayDTO.getVersion());
				titanJrRefundRequest.setSignType(titanOrderPayDTO.getSignType().toString());
				titanJrRefundRequest.setIsRealTime(CommonConstant.NOT_REAL_TIME);
				if(PayTypeEnum.isRealTimeToAccount(titanOrderPayDTO.getPayType())){
					titanJrRefundRequest.setIsRealTime(CommonConstant.REAL_TIME);
				}
			}
			
			//直接进行账户退款,退款到账户余额
			if(null == titanOrderPayDTO){
				titanJrRefundRequest.setToBankCardOrAccount(RefundTypeEnum.REFUND_ACCOUNT.type);
			}
			//解冻操作
			if(OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())){//立即解冻
				List<FundFreezeDTO>  fundFreezeDTOList =  getUnFreezeAccountBalanceRequest(transOrderDTO.getOrderid());
				if(null ==fundFreezeDTOList || fundFreezeDTOList.size()!=1){
					log.error("冻结单查询失败");
			    	response.putErrorResult(TitanMsgCodeEnum.REFUND_FAIL);
			    	return response;
				}
				
				UnFreeBalanceBatchRequest unFreeBalanceBatchRequest = new UnFreeBalanceBatchRequest();
				unFreeBalanceBatchRequest.setFundFreezeDTOList(fundFreezeDTOList);
				flag = titanFinancialAccountService.unfreezeAccountBalanceOne(unFreeBalanceBatchRequest);
				if(!flag){
					log.error("资金解冻失败");
			    	response.putErrorResult(TitanMsgCodeEnum.REFUND_UNFREEZE_FAIL);
			    	return response;
				}
				//标识该订单有解冻操作
				refundRequest.setFreeze(true);
			}
			
	        TitanJrRefundResponse titanJrRefundResponse = titanFinancialRefundService.refund(titanJrRefundRequest);
		    if(!titanJrRefundResponse.isResult()){
		    	log.error("退款操作失败:"+titanJrRefundResponse.getReturnMessage());
		    	response.putErrorResult(TitanMsgCodeEnum.REFUND_FAIL);
		    	return response;
		    }
		    TransOrderDTO order = new TransOrderDTO();
		    order.setStatusid(OrderStatusEnum.REFUND_IN_PROCESS.getStatus());
		    if(StringUtil.isValidString(titanJrRefundRequest.getToBankCardOrAccount()) && 
		    		titanJrRefundRequest.getToBankCardOrAccount().equals(RefundTypeEnum.REFUND_ACCOUNT.type)){//余额支付的退款
		    	 order.setStatusid(OrderStatusEnum.REFUND_SUCCESS.getStatus());
		    }else if(titanJrRefundRequest.getIsRealTime()==CommonConstant.REAL_TIME){//第三方支付等实时到账的退款
		    	order.setStatusid(OrderStatusEnum.REFUND_SUCCESS.getStatus());
		    }
		    log.info("退款成功修改订单状态:"+order.getTransid()+":"+order.getStatusid());
		    order.setTransid(transOrderDTO.getTransid());
		    flag = titanOrderService.updateTransOrder(order);
		    if(!flag){
		    	OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(transOrderDTO.getOrderid(), "退款请求更新本地单失败", OrderExceptionEnum.REFUND_UPDATE_TRANSORDER, JSON.toJSONString(transOrderDTO));
	    		titanOrderService.saveOrderException(orderExceptionDTO);
		    }
		    response.putSuccess();
		    return response;			
		}catch(Exception e){
			log.error("退款出现异常:"+e.getMessage());
		}
		response.putSysError();
		return response;		
	}
	
	private boolean validateUserId(String userId){
		OrgDTO orgDTO = new OrgDTO();
		orgDTO.setOrgcode(userId);
		orgDTO.setStatusId(CommonConstant.IS_ACTIVE);
		orgDTO =  titanFinancialOrganService.queryOrg(orgDTO);
        if (orgDTO !=null && userId.equals(orgDTO.getUserid())){
            return true;
        }
		return false;
	}
	
	private boolean validatePsd(String tfsUserId,String psd){
		return titanFinancialUserService.checkPayPassword(psd,tfsUserId);
	}
	
	private List<FundFreezeDTO> getUnFreezeAccountBalanceRequest(String orderId){
		if(!StringUtil.isValidString(orderId)){
			return null;
		}
		FundFreezeDTO fundFreezeDTO = new FundFreezeDTO();
		fundFreezeDTO.setOrderNo(orderId);
		List<FundFreezeDTO>  fundFreezeDTOList =  titanOrderService.queryFundFreezeDTO(fundFreezeDTO);
		if(null == fundFreezeDTOList ||fundFreezeDTOList.size()!=1){
			return null;
		}
		
		return fundFreezeDTOList;
	}
	
	
	
	/**
	 * 验证是否具有退款的权限
	 * @param userid
	 * @param tfsUserid
	 * @return
	 */
	private boolean validateIsPassRefund(String userid,String tfsUserid){
		
		boolean orgCheck = this.validateIsTitanOrg(userid);
		
		if(!orgCheck){
			log.error("验证机构不存在");
			return false;
		}
		
		boolean perssionCheck  = this.validatePerssion(tfsUserid);
		
		if(!perssionCheck){
			log.error("验证该员工无付款权限");
			return false;
		}
		
		return true;
	}
	
	private boolean validateIsTitanOrg(String userid){
		OrgDTO orgDTO = new OrgDTO();
		orgDTO.setOrgcode(userid);
		orgDTO.setStatusId(1);
		orgDTO = titanFinancialOrganService.queryOrg(orgDTO);
		if(StringUtil.isValidString(orgDTO.getUserid())){
			return true;
		}
		return false;
	}
	
	private boolean validatePerssion(String tfsUserId){
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
		//将传入的信息转换为金融信息
		if(CommonConstant.ISMERCHCODE.equals(refundRequest.getIsMerchCode())){
			if(this.saasToJr(refundRequest)==null){
				log.error("商家未绑定金融账户或操作人为绑定金融用户");
				model.addAttribute("msg",
						TitanMsgCodeEnum.REFUND_CONCERT_FAIL.getResMsg());
				return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
			}
		}
		
		
		//验证机构和支付人权限
		boolean isRefund = this.validateIsPassRefund(refundRequest.getUserId(), refundRequest.getTfsUserid());

		if(!isRefund){
			log.error("验证机构或权限失败");
			model.addAttribute("msg",
					TitanMsgCodeEnum.PERMISSION_CHECK_FAILED.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}
		
		//根据payOrderNo查询相关的单
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setPayorderno(refundRequest.getOrderNo());
		TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
		
		if(transOrderDTO ==null){
			log.error("订单不存在");
			model.addAttribute("msg",
					TitanMsgCodeEnum.QUERY_LOCAL_ORDER.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}
		
		
		if(OrderStatusEnum.REFUND_IN_PROCESS.getStatus().equals(transOrderDTO.getStatusid())){
			log.error("该订单正在退款中，不能重复退款");
			model.addAttribute("msg",
					TitanMsgCodeEnum.ORDER_REFUNND_IN_PROCESS.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}
		
		if(OrderStatusEnum.REFUND_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())){
			log.error("该订单退款成功，不能重复退款");
			model.addAttribute("msg",
					TitanMsgCodeEnum.TRANSFER_SUCCESS_UPDATE_LOACL_FAIL.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}
		
		
		if(!(OrderStatusEnum.ORDER_SUCCESS.getStatus().equals(transOrderDTO.getStatusid()) || OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid()))){
			log.error("该订单未支付成功，不能退款");
			model.addAttribute("msg",
					TitanMsgCodeEnum.ORDER_NOT_REFUND.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}
		
		//判断订单是否超过退款时间，
		if(transOrderDTO.getAmount() !=null && transOrderDTO.getAmount()>0 ){
			TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
			titanOrderPayDTO.setOrderNo(transOrderDTO.getOrderid());
			TitanOrderPayDTO payOrder = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
			if(payOrder !=null  && StringUtil.isValidString(payOrder.getOrderTime())){
				try {
					Long orderDate = DateUtil.sdf5.parse(payOrder.getOrderTime()).getTime();
					Long nowDate = new Date().getTime();
					if(nowDate-orderDate>CommonConstant.MS){
						log.error("该订单已超出退款时限");
						model.addAttribute("msg",
								TitanMsgCodeEnum.ORDER_OUT_TIME.getResMsg());
						return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
					}
					
				} catch (ParseException e) {
					log.error("时间转换异常:"+e.getMessage());
				}
			}
		}
		
		
		//查询商家的账户余额，看是否能满足退款
		AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
		accountBalanceRequest.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
		accountBalanceRequest.setUserid(refundRequest.getUserId());
		AccountBalanceResponse accountBalanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
		
		if(!accountBalanceResponse.isResult()){
			log.error("账户异常");
			model.addAttribute("msg",
					TitanMsgCodeEnum.ORDER_NOT_REFUND.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}
		
		List<AccountBalance> accountBalanceList  = accountBalanceResponse.getAccountBalance();
		String accountBalance = "";
		String refundAmount = transOrderDTO.getTradeamount().toString();
		for(AccountBalance balance :accountBalanceList){
			if(balance.getProductid().equals(CommonConstant.RS_FANGCANG_PRODUCT_ID)){
				accountBalance = balance.getBalanceusable();
			}
		}
		
		TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
		titanTransferDTO.setTransorderid(transOrderDTO.getTransid());
		titanTransferDTO = titanOrderService.getTitanTransferDTO(titanTransferDTO);
		if(titanTransferDTO !=null && titanTransferDTO.getAmount()!=null){
			refundAmount = titanTransferDTO.getAmount().toString();
		}
		
//		if(new BigDecimal(accountBalance).compareTo(new BigDecimal(refundAmount))==-1){
//			log.error("账户余额不足,请充值后再退款");
//			model.addAttribute("msg",
//					TitanMsgCodeEnum.ACCOUNT_BALANCE_NOT_ENOUGH.getResMsg());
//			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
//		}
		
		PayerTypeEnum payerType= PayerTypeEnum.getPayerTypeEnumByKey(transOrderDTO.getPayerType());
		if(payerType !=null && payerType.isRecieveCashDesk()){
			model.addAttribute("paySourceMark", CommonConstant.ISRECIEVEDESK);
		}
		
		model.addAttribute("balanceAmount",accountBalance);
		model.addAttribute("transOrderDTO", transOrderDTO);
		model.addAttribute("refundRequest", refundRequest);
		
		return TitanConstantDefine.REFUND_MAIN_PAGE;
	}
	
	private RefundRequest saasToJr(RefundRequest refundRequest){
		
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
	
}
