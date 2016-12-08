package com.fangcang.titanjr.pay.services;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.RefundTypeEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.TransferReqEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.bean.OrgDTO;
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.PermissionRequest;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.RefundRequest;
import com.fangcang.titanjr.dto.request.TitanJrRefundRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.request.UnFreeBalanceBatchRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.CheckPermissionResponse;
import com.fangcang.titanjr.dto.response.RefundResponse;
import com.fangcang.titanjr.dto.response.TitanJrRefundResponse;
import com.fangcang.titanjr.enums.BusiCodeEnum;
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
		//可以验证该身份
		
		//获取该订单
		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setPayorderno(refundRequest.getOrderNo());
		
		//该订单不存在或者该单没有支付成功
		TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
		if(null == transOrderDTO || !OrderStatusEnum.isPaySuccess(transOrderDTO.getStatusid())){
			response.putErrorResult(TitanMsgCodeEnum.ORDER_NOT_REFUND);
			return response;
		}
		
		//如果是冻结的需要立即解冻
		if(OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())){//立即解冻
			List<FundFreezeDTO>  fundFreezeDTOList =  getUnFreezeAccountBalanceRequest(transOrderDTO.getOrderid());
			if(null !=fundFreezeDTOList && fundFreezeDTOList.size()==1){
				UnFreeBalanceBatchRequest unFreeBalanceBatchRequest = new UnFreeBalanceBatchRequest();
				unFreeBalanceBatchRequest.setFundFreezeDTOList(fundFreezeDTOList);
				titanFinancialAccountService.unfreezeAccountBalanceBatch(unFreeBalanceBatchRequest);
				//标识该订单有解冻操作
				refundRequest.setFreeze(true);
			}
		}
		
		//查询余额
		AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
	    accountBalanceRequest.setUserid(transOrderDTO.getUserrelateid());
	    AccountBalanceResponse balanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
	    if(!balanceResponse.isResult() || null == balanceResponse.getAccountBalance() || null == balanceResponse.getAccountBalance().get(0)){
			response.putErrorResult(TitanMsgCodeEnum.TITAN_ACCOUNT_NOT_EXISTS);
			return response;
		}
	    
	    //账户余额不足不能退款
	    AccountBalance accountBalace =  balanceResponse.getAccountBalance().get(0);
		BigDecimal balance = new BigDecimal(accountBalace.getBalanceusable());
		Long fee = transOrderDTO.getReceivedfee();
		if(null == fee){
			fee = (long)0;
		}
		
		//查询账户转账金额
		TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
		titanTransferDTO.setPayOrderNo(transOrderDTO.getPayorderno());
		titanTransferDTO.setUserid(transOrderDTO.getUserid());
		titanTransferDTO.setUserrelateid(transOrderDTO.getUserrelateid());
		titanTransferDTO.setStatus(TransferReqEnum.TRANSFER_SUCCESS.getStatus());
		titanTransferDTO = titanOrderService.getTitanTransferDTO(titanTransferDTO);
		if(null ==titanTransferDTO){
			response.putErrorResult(TitanMsgCodeEnum.UNEXPECTED_ERROR);
			return response;
		}
		
		BigDecimal tradeAmount  = new BigDecimal(titanTransferDTO.getAmount());
		if(balance.subtract(tradeAmount).compareTo(BigDecimal.ZERO)==-1){
			response.putErrorResult(TitanMsgCodeEnum.ACCOUNT_BALANCE_NOT_ENOUGH);
			return response;
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
		//查看是否有充值单
		TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
		titanOrderPayDTO.setTransorderid(transOrderDTO.getTransid());
		titanOrderPayDTO.setOrderNo(transOrderDTO.getOrderid());
		titanOrderPayDTO = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
		
		if(null !=titanOrderPayDTO){//有充值单
			titanJrRefundRequest.setOrderNo(transOrderDTO.getOrderid());
			titanJrRefundRequest.setRefundAmount(transOrderDTO.getAmount().toString());
			titanJrRefundRequest.setBusiCode(BusiCodeEnum.MerchantRefund.getKey());
			titanJrRefundRequest.setOrderTime(titanOrderPayDTO.getOrderTime());
			titanJrRefundRequest.setVersion(titanOrderPayDTO.getVersion());
			titanJrRefundRequest.setSignType(titanOrderPayDTO.getSignType().toString());
			
			BigDecimal orderAmount = new BigDecimal(titanOrderPayDTO.getOrderAmount());
			//只有充值
			if(tradeAmount.subtract(orderAmount).compareTo(BigDecimal.ZERO)!=1 ){//交易金额比充值金额小(有手续费时)，或者相等
				titanJrRefundRequest.setToBankCardOrAccount(RefundTypeEnum.REFUND_BANKCARD.type);
				
			}
			//充值和余额支付并存
	        if(tradeAmount.subtract(orderAmount).compareTo(BigDecimal.ZERO)==1){
	        	titanJrRefundRequest.setToBankCardOrAccount(RefundTypeEnum.REFUND_BANKCARD_ACCOUNT.type);
			}
		}
		
		//直接进行账户退款,退款到账户余额
		if(null == titanOrderPayDTO){
			titanJrRefundRequest.setToBankCardOrAccount(RefundTypeEnum.REFUND_ACCOUNT.type);
		}
		
        TitanJrRefundResponse titanJrRefundResponse = titanFinancialRefundService.refund(titanJrRefundRequest);
	    if(!titanJrRefundResponse.isResult()){
	    	//如果有解冻操作需要重新冻结
	    	 response.putErrorResult(TitanMsgCodeEnum.REFUND_FAIL);
	    	 return response;
	    }
	    
	    //修改transOrderDTO
	    transOrderDTO.setStatusid(OrderStatusEnum.REFUND_IN_PROCESS.getStatus());
	    transOrderDTO.setAmount((long)0);
	    boolean flag = titanOrderService.updateTransOrder(transOrderDTO);
	    if(!flag){
	    	OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(transOrderDTO.getOrderid(), "退款请求更新本地单失败", OrderExceptionEnum.REFUND_UPDATE_TRANSORDER, JSON.toJSONString(transOrderDTO));
    		titanOrderService.saveOrderException(orderExceptionDTO);
	    }
	    
	    response.putSuccess();
	    return response;
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
		
		
		if(!(OrderStatusEnum.ORDER_SUCCESS.getStatus().equals(transOrderDTO.getStatusid()) || OrderStatusEnum.FREEZE_SUCCESS.equals(transOrderDTO.getStatusid()))){
			log.error("该订单为支付成功，不能退款");
			model.addAttribute("msg",
					TitanMsgCodeEnum.ORDER_NOT_REFUND.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
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
		
		
		if(new BigDecimal(accountBalance).compareTo(new BigDecimal(refundAmount))==-1){
			log.error("账户余额不足,请充值后再退款");
			model.addAttribute("msg",
					TitanMsgCodeEnum.ACCOUNT_BALANCE_NOT_ENOUGH.getResMsg());
			return TitanConstantDefine.TRADE_PAY_ERROR_PAGE;
		}
		
		
		model.addAttribute("transOrderDTO", transOrderDTO);
		model.addAttribute("refundRequest", refundRequest);
		
		return TitanConstantDefine.REFUND_MAIN_PAGE;
	}
	
}
