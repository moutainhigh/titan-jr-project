/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName JRAccountController.java
 * @author Jerry
 * @date 2017年8月14日 上午11:14:42  
 */
package com.fangcang.titanjr.web.controller;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.fangcang.titanjr.common.enums.*;

import net.sf.json.JSONSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.AddPayLogRequest;
import com.fangcang.titanjr.dto.request.NotifyRefundRequest;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.RefundOrderRequest;
import com.fangcang.titanjr.dto.request.TitanJrRefundRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.request.TransferRequest;
import com.fangcang.titanjr.dto.request.UnFreeBalanceBatchRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.FreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.NotifyRefundResponse;
import com.fangcang.titanjr.dto.response.TitanJrRefundResponse;
import com.fangcang.titanjr.dto.response.TransferResponse;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.response.BaseResponse;
import com.fangcang.titanjr.rest.enums.AccReceOperTypeEnum;
import com.fangcang.titanjr.rest.request.JRAccountReceiveRequest;
import com.fangcang.titanjr.service.BusinessLogService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialRefundService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

/**
 * @author Jerry
 * @date 2017年8月14日 上午11:14:42  
 */
@RestController
@Api(value = "AccountApi")
public class JRAccountController {
	
	private static final Log log = LogFactory.getLog(JRAccountController.class);
	
	@Resource
	private TitanOrderService titanOrderService;
	
	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;
	
	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;
	
	@Resource
	private TitanFinancialOrganService titanFinancialOrganService;
	
	@Resource
	private TitanFinancialRefundService titanFinancialRefundService;
	
	@Resource
    private TitanFinancialUtilService titanFinancialUtilService;
	
	@Resource
	private BusinessLogService businessLogService;
	
	
	/**
	 * 供应商在线确认订单
	 * @author Jerry
	 * @date 2017年8月28日 上午11:33:23
	 */
	@RequestMapping(value = "/account/payeeAccountReceive", method = RequestMethod.POST)
    @ApiOperation(value = "供应商确认订单", produces = "application/json", httpMethod = "POST",
            response = BaseResponse.class, notes = "可以确认订单或者拒绝订单")
    public BaseResponse payeeAccountReceive(@ApiParam(required = true, name = "jrAccountReceiveRequest", 
    	value = "请求参数") @RequestBody JRAccountReceiveRequest jrAccountReceiveRequest, HttpServletRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		log.info("供应商确认订单请求参数：" + jrAccountReceiveRequest.toString());
		
		try {
			
			ValidateResponse res = GenericValidate.validateObj(jrAccountReceiveRequest);
			if(!res.isSuccess()){
				baseResponse.putErrorResult(res.getReturnMessage());
				return baseResponse;
			}
			
			//查询订单
			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setPayorderno(jrAccountReceiveRequest.getPayOrderNo());
			transOrderRequest.setStatusId(OrderStatusEnum.FREEZE_SUCCESS.getStatus());//查询冻结状态的订单，表示不考虑冻结方案为1的场景
			TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
			if(transOrderDTO!=null){
				log.info("供应商确认订单，查询订单信息：transOrderDTO："+Tools.gsonToString(transOrderDTO));
			}
			
			//校验信息
			baseResponse = checkInfo(jrAccountReceiveRequest, transOrderDTO);
			if(!baseResponse.isResult()){
				return baseResponse;
			}
			transOrderDTO.setCreator(jrAccountReceiveRequest.getOperator());
			
			if(FreezeTypeEnum.FREEZE_PAYER.getKey().equals(transOrderDTO.getFreezeType())){
				//如果冻结方案是3，则根据传入参数类型操作
				if(jrAccountReceiveRequest.getOperateType() == AccReceOperTypeEnum.RECEIVE_ONLY.getKey()){//直接收款
					
					baseResponse = unfreezePayer(transOrderDTO, true);
					if(!baseResponse.isResult()) {
						return baseResponse;
					}
					
					baseResponse = transferToPayee(transOrderDTO);
					if(!baseResponse.isResult()) {
						return baseResponse;
					}
					
				}else if (jrAccountReceiveRequest.getOperateType() == AccReceOperTypeEnum.RECEIVE_FREEZE.getKey()){//收款并冻结收款方资金	
					
					baseResponse = unfreezePayer(transOrderDTO, true);
					if(!baseResponse.isResult()) {
						return baseResponse;
					}
					
					baseResponse = transferToPayee(transOrderDTO);
					if(!baseResponse.isResult()) {
						return baseResponse;
					}
					
					baseResponse = freezePayee(transOrderDTO);
					if(!baseResponse.isResult()) {
						return baseResponse;
					}
					
				}else {//不收款，解冻付款方资金
					
					baseResponse = unfreezePayer(transOrderDTO, false);
					if(!baseResponse.isResult()) {
						log.error("拒单时，解冻失败,订单号orderid:"+transOrderDTO.getOrderid());
						return baseResponse;
					}
					//需要原路退回
					if (jrAccountReceiveRequest.getIsBackTrack() != 0) {
						backTrack(transOrderDTO);
					}
					if (baseResponse.isResult()&&jrAccountReceiveRequest.getIsBackTrack() == 0) {//不原路退回则改为交易取消，包括余额支付
						//改订单状态为16,交易取消
						TransOrderDTO updateTransOrderDTO = new TransOrderDTO();
						updateTransOrderDTO.setTransid(transOrderDTO.getTransid());
						updateTransOrderDTO.setStatusid(OrderStatusEnum.ORDER_CANCEL.getStatus());
						titanOrderService.updateTransOrder(updateTransOrderDTO);
					}
				}
				
			}else{
				
				//如果冻结方案不是3（应该只有冻结方案为2的场景），确认订单就直接返回成功，拒单就调退款接口
				if(jrAccountReceiveRequest.getOperateType() != AccReceOperTypeEnum.UNRECEIVE_UNFREEZE.getKey()){
					log.info("冻结方案不是3，确认订单直接返回成功");
					baseResponse.putSuccess();
					return baseResponse;
				}else{
					log.info("冻结方案不是3，拒单直接调退款接口");
					refundOrder(transOrderDTO, jrAccountReceiveRequest.getIsBackTrack());
				}
				
			}
			
		} catch (Exception e) {
			log.error("收款方是否确认收款操作异常，收款参数jrAccountReceiveRequest："+Tools.gsonToString(jrAccountReceiveRequest), e);
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.UNEXPECTED_ERROR.getCode()), 
					TitanMsgCodeEnum.UNEXPECTED_ERROR.getKey());
			return baseResponse;
			
		}
		
		return baseResponse;
		
	}
	
	
	private BaseResponse checkInfo(JRAccountReceiveRequest jrAccountReceiveRequest, 
			TransOrderDTO transOrderDTO){
		
		BaseResponse baseResponse = new BaseResponse();
		if (null == transOrderDTO) {
			log.error("query trans order is failed, payOrderNo:" + jrAccountReceiveRequest.getPayOrderNo());
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.ORDER_NOT_FREEZE.getCode()), 
					TitanMsgCodeEnum.ORDER_NOT_FREEZE.getKey());
			return baseResponse;
		}
		
		//退单的时候，如果没有成功充值单表示是余额支付，需要设置为非原路退回
		if(AccReceOperTypeEnum.UNRECEIVE_UNFREEZE.getKey() == jrAccountReceiveRequest.getOperateType() 
				&& 1 == jrAccountReceiveRequest.getIsBackTrack()){
			TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
			titanOrderPayDTO.setOrderNo(transOrderDTO.getOrderid());
			TitanOrderPayDTO orderPayDTOResult = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
			if (null == orderPayDTOResult){
				jrAccountReceiveRequest.setIsBackTrack(0);
			}
		}
		if (!OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())){
			log.error("trans order is not freeze, payOrderNo:" + jrAccountReceiveRequest.getPayOrderNo());
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.ORDER_NOT_FREEZE.getCode()), 
					TitanMsgCodeEnum.ORDER_NOT_FREEZE.getKey());
			return baseResponse;
		}
		/*if (!FreezeTypeEnum.FREEZE_PAYER.getKey().equals(transOrderDTO.getFreezeType())) {
			log.error("trans order freezetype is not 3");
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.ORDER_FREEZETYPE_NOT3.getCode()), 
					TitanMsgCodeEnum.ORDER_FREEZETYPE_NOT3.getKey());
			return baseResponse;
		}*/
		if(!jrAccountReceiveRequest.getTradeAmount().equals(transOrderDTO.getTradeamount())){
			log.error("tradeAmount is error, payOrderNo:" + jrAccountReceiveRequest.getPayOrderNo());
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getCode()), 
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getKey());
			return baseResponse;
		}
		if(!AccReceOperTypeEnum.isExist(jrAccountReceiveRequest.getOperateType())){
			log.error("operateType is not exist, payOrderNo:" + jrAccountReceiveRequest.getPayOrderNo());
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getCode()), 
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getKey());
			return baseResponse;
		}
		OrgBindInfo orgBindInfo = queryOrgBindInfo(jrAccountReceiveRequest.getPartnerOrgCode());
		if(orgBindInfo == null){
			log.error("query orgBindInfo failed, payOrderNo:" + jrAccountReceiveRequest.getPayOrderNo());
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getCode()), 
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getKey());
			return baseResponse;
		}
		if(!transOrderDTO.getUserrelateid().equals(orgBindInfo.getOrgcode())){
			log.error("partnerOrgCode is error, payOrderNo:" + jrAccountReceiveRequest.getPayOrderNo());
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getCode()), 
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getKey());
			return baseResponse;
		}
		
		baseResponse.putSuccess();
		return baseResponse;
	}
	
	
	private OrgBindInfo queryOrgBindInfo(String partnerOrgCode){
		OrgBindInfo orgBindInfo = new OrgBindInfo();
		orgBindInfo.setMerchantCode(partnerOrgCode);
		return titanFinancialOrganService.queryOrgBindInfo(orgBindInfo);
	}
	
	
	private List<FundFreezeDTO> getFundFreezeList(String orderId) {
		if (!StringUtil.isValidString(orderId)) {
			return null;
		}
		FundFreezeDTO fundFreezeDTO = new FundFreezeDTO();
		fundFreezeDTO.setOrderNo(orderId);
		List<FundFreezeDTO> fundFreezeDTOList = titanOrderService.queryFundFreezeDTO(fundFreezeDTO);
		if (CollectionUtils.isEmpty(fundFreezeDTOList)) {
			return null;
		}else if(fundFreezeDTOList.size() > 0){//只取最新的那条冻结记录
			fundFreezeDTOList = fundFreezeDTOList.subList(0, 1);
		}
		return fundFreezeDTOList;
	}
	
	
	/**
	 * 根据冻结记录解冻付款方资金
	 * @param isReceive：是否收款
	 * @author Jerry
	 * @date 2017年8月14日 下午4:29:32
	 */
	private BaseResponse unfreezePayer(TransOrderDTO transOrderDTO, boolean isReceive) {
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.putSuccess();
		log.info("==========>>begin to unfreezePayer");
		
		//查询冻结单
		List<FundFreezeDTO> fundFreezeList = getFundFreezeList(transOrderDTO.getOrderid());
		if (null == fundFreezeList) {
			log.error("冻结单查询失败，orderid:" + transOrderDTO.getOrderid());
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.QUERY_FREEZEORDER_FAIL.getCode()), 
					TitanMsgCodeEnum.QUERY_FREEZEORDER_FAIL.getKey());
			return baseResponse;
		}
//		if (fundFreezeList.get(0) != null && !isReceive) {
//			OrderStatusEnum orderStatusEnum = OrderStatusEnum.ORDER_FAIL;
//			fundFreezeList.get(0).setOrderStatusEnum(orderStatusEnum);
//		}
		//资金解冻
		UnFreeBalanceBatchRequest unFreeBalanceBatchRequest = new UnFreeBalanceBatchRequest();
		unFreeBalanceBatchRequest.setFundFreezeDTOList(fundFreezeList);
		boolean unfreezeResult = titanFinancialAccountService.unfreezeAccountBalanceOne(unFreeBalanceBatchRequest);
		if (!unfreezeResult) {
			log.error("资金解冻失败，orderid:" + transOrderDTO.getOrderid());
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.REFUND_UNFREEZE_FAIL.getCode()), 
					TitanMsgCodeEnum.REFUND_UNFREEZE_FAIL.getKey());
			return baseResponse;
		}
		log.info("==========>>unfreezePayer success");
		
    	return baseResponse;
	}
	
	
	/**
	 * 转账到收款方（设置手续费）
	 * @author Jerry
	 * @date 2017年8月14日 下午4:53:21
	 */
	private BaseResponse transferToPayee(TransOrderDTO transOrderDTO) throws Exception {
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.putSuccess();
		
		Long receivedFee = 0L;
		if(transOrderDTO.getReceivedfee() != null){
			receivedFee = transOrderDTO.getReceivedfee();
		}
		PayerTypeEnum payerTypeEnum = PayerTypeEnum
				.getPayerTypeEnumByKey(transOrderDTO.getPayerType());
		
		TransOrderDTO transOrder = new TransOrderDTO();
		transOrder.setOrderid(transOrderDTO.getOrderid());
		
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setCreator(transOrderDTO.getCreator());
		transferRequest.setUserid(transOrderDTO.getUserid()); // 转出的用户
		transferRequest.setRequestno(OrderGenerateService.genResquestNo()); // 业务订单号
		transferRequest.setRequesttime(DateUtil.sdf4.format(new Date())); // 请求时间
		if (payerTypeEnum != null && !payerTypeEnum.isNeedPayerInfo()) {//收款方出手续费，减去手续费
			transferRequest.setAmount(String.valueOf(transOrderDTO.getTradeamount()-receivedFee));
		}else{
			transferRequest.setAmount(String.valueOf(transOrderDTO.getTradeamount()));
		}
		transferRequest.setUserfee("0");
		transferRequest.setOrderid(transOrderDTO.getOrderid());
		transferRequest.setUserrelateid(transOrderDTO.getUserrelateid());
		transferRequest.setProductId(transOrderDTO.getProductid());
		
		log.info("begin to transfer:" + JsonConversionTool.toJson(transferRequest));
		TransferResponse transferResponse = titanFinancialTradeService.transferAccounts(transferRequest);
    	log.info("the result of transfer :" + JsonConversionTool.toJson(transferResponse)+", orderid:"+transferRequest.getOrderid());
    	
    	if(!transferResponse.isResult()){
    		log.error("转账失败，orderid:" + transOrderDTO.getOrderid());
    		transOrder.setStatusid(OrderStatusEnum.ORDER_FAIL.getStatus());
    		BaseResponseDTO baseResponseDTO = titanFinancialAccountService.reFreezeOrder(transOrderDTO);//转账失败后重新冻结付款方
			if(baseResponseDTO.isResult()){
				transOrder.setFreezeAt(CommonConstant.FREEZE_PAYER);
				transOrder.setStatusid(OrderStatusEnum.FREEZE_SUCCESS.getStatus());
			}
    		baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.TRANSFER_FAIL.getCode()), 
					TitanMsgCodeEnum.TRANSFER_FAIL.getKey());
    	}else{
    		transOrder.setStatusid(OrderStatusEnum.ORDER_SUCCESS.getStatus());
    		//将手续费转到收益子账户
        	if(transferResponse.isResult()){
    			if(transOrderDTO.getReceivedfee() != null && transOrderDTO.getReceivedfee() > 0){
    				log.info("began transfer to revenueAccount");
    				TransferRequest transferRevenueAccountRequest = this.buildRevenueAccountTransferRequest(transOrderDTO);
    				transferResponse = titanFinancialTradeService.transferAccounts(transferRevenueAccountRequest);
    				if(transferResponse.isResult()){
    					log.info("transfer to revenueAccount success, transOrderId: " + transOrderDTO.getTransid());
    					businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.TransferSucc, OrderKindEnum.TransOrderId, transOrderDTO.getTransid()+""));
    				}else{
    					log.error("transfer to revenueAccount success faild, transOrderId: " + transOrderDTO.getTransid());
    					titanFinancialUtilService.saveOrderException(transOrderDTO.getOrderid(), OrderKindEnum.OrderId, OrderExceptionEnum.Transfer_revenueAccount_Fail, transOrderDTO.getStatusid());
    				}
    			}
        	}
    	}
    	
    	//更新订单状态
		if(!titanOrderService.updateTransOrder(transOrder)){
			log.error("账户收款，转账后修改订单状态失败");
			titanFinancialUtilService.saveOrderException(transOrderDTO.getOrderid(),OrderKindEnum.OrderId, OrderExceptionEnum.AccountReceive_Transfer_UpdateOrder_Fail, JSONSerializer.toJSON(transOrder).toString());
		}
		
		return baseResponse;
	}
	
	
	/**
	 * 冻结收款方资金
	 * @author Jerry
	 * @throws Exception 
	 * @date 2017年8月14日 下午4:30:23
	 */
	private BaseResponse freezePayee(TransOrderDTO transOrderDTO) throws Exception {
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.putSuccess();
		
		TransOrderDTO transOrder = new TransOrderDTO();
		transOrder.setOrderid(transOrderDTO.getOrderid());
		
		Long receivedFee = 0L;
		if(transOrderDTO.getReceivedfee() != null){
			receivedFee = transOrderDTO.getReceivedfee();
		}
		PayerTypeEnum payerTypeEnum = PayerTypeEnum
				.getPayerTypeEnumByKey(transOrderDTO.getPayerType());
		
		RechargeResultConfirmRequest rechargeResultConfirmRequest = new RechargeResultConfirmRequest();
		rechargeResultConfirmRequest.setOrderNo(transOrderDTO.getOrderid());
		if (payerTypeEnum != null && !payerTypeEnum.isNeedPayerInfo()) {//收款方出手续费，减去手续费
			rechargeResultConfirmRequest.setPayAmount(String.valueOf(transOrderDTO.getTradeamount()-receivedFee));
		}else{
			rechargeResultConfirmRequest.setPayAmount(transOrderDTO.getTradeamount().toString());
		}
		rechargeResultConfirmRequest.setUserid(transOrderDTO.getUserrelateid());
		
		log.info("==========>>begin to freezePayee");
		FreezeAccountBalanceResponse freezeAccountBalanceResponse = titanFinancialAccountService
				.freezeAccountBalance(rechargeResultConfirmRequest);
		if (!freezeAccountBalanceResponse.isFreezeSuccess()) {
			
			log.error("冻结收款方资金失败，orderid:" + transOrderDTO.getOrderid());
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.FREEZEORDER_FAIL.getCode()), 
					TitanMsgCodeEnum.FREEZEORDER_FAIL.getKey());
			titanFinancialUtilService.saveOrderException(transOrderDTO.getOrderid(),OrderKindEnum.OrderId, OrderExceptionEnum.AccountReceive_FreezePayee_Fail, JSONSerializer.toJSON(rechargeResultConfirmRequest).toString());
			transOrder.setStatusid(OrderStatusEnum.FREEZE_FAIL.getStatus());
			
		}else{
			
			transOrder.setFreezeAt(CommonConstant.FREEZE_PAYEE);
			transOrder.setStatusid(OrderStatusEnum.FREEZE_SUCCESS.getStatus());
		}
		log.info("==========>>freezePayee success");
		
		//更新订单状态
		log.info("==========>>begin to updateTransOrder");
		if(!titanOrderService.updateTransOrder(transOrder)){
			log.error("账户收款，冻结收款方后修改订单状态失败");
			log.info("==========>>updateTransOrder is fail");
			titanFinancialUtilService.saveOrderException(transOrderDTO.getOrderid(),OrderKindEnum.OrderId, OrderExceptionEnum.AccountReceive_FreezePayee_UpdateOrder_Fail, JSONSerializer.toJSON(transOrder).toString());
		}
		log.info("==========>>updateTransOrder end");
		
		return baseResponse;
	}
	
	
	/**
	 * 查询充值记录，判断充值时间是否超过30天，未超过需要原路退回
	 * @author Jerry
	 * @throws ParseException 
	 * @date 2017年8月16日 下午6:30:06
	 */
	private BaseResponse backTrack(TransOrderDTO transOrderDTO) throws ParseException{
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.putSuccess("退款成功");
		
		log.info("拒单开始原路退款，订单号orderid："+transOrderDTO.getOrderid());
		TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
		titanOrderPayDTO.setOrderNo(transOrderDTO.getOrderid());
		TitanOrderPayDTO payOrder = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
		if (payOrder != null && StringUtil.isValidString(payOrder.getOrderTime())) {
			
			Long orderDate = DateUtil.sdf5.parse(payOrder.getOrderTime()).getTime();
			Long nowDate = new Date().getTime();
			if (nowDate - orderDate <= CommonConstant.MS) {
				
				log.info("执行原路退回，通知融数网关退款，订单号orderid："+transOrderDTO.getOrderid());
				RefundOrderRequest refundOrderRequest = buildRefundOrderRequest(
						transOrderDTO, payOrder.getOrderTime());
				NotifyRefundRequest notifyRefundRequest = buildNotifyRefundRequest(
						transOrderDTO, payOrder);
				NotifyRefundResponse notifyRefundResponse = titanFinancialRefundService
						.notifyRefund(refundOrderRequest, notifyRefundRequest, transOrderDTO);
				if(notifyRefundResponse.isResult()){
					log.info("网关退款成功, 订单orderid: " + transOrderDTO.getOrderid()+", 响应结果:" + 
							Tools.gsonToString(notifyRefundResponse));
				}else{
					log.error("网关退款失败, 订单orderid: " + transOrderDTO.getOrderid()+", 响应结果:" + 
							Tools.gsonToString(notifyRefundResponse));
					baseResponse.putErrorResult(notifyRefundResponse.getErrMsg());
				}
				
			}else{
				log.info("充值超过30天，不执行原路退回");
			}
		}else{
			log.info("退款未找到支付记录TitanOrderPay，无需发起退款，订单号orderid："+transOrderDTO.getOrderid());
		}
		
		return baseResponse;
	}
	
	private RefundOrderRequest buildRefundOrderRequest(TransOrderDTO transOrderDTO, 
			String orderTime){
		RefundOrderRequest refundOrderRequest = new RefundOrderRequest();
		refundOrderRequest.setAmount(String.valueOf(transOrderDTO.getAmount())); //充值的钱（包含手续费）
		refundOrderRequest.setOrderId(transOrderDTO.getOrderid());
		refundOrderRequest.setOrderTime(orderTime);
		refundOrderRequest.setTransferAmount("0");//冻结在付款方还没转账
		refundOrderRequest.setFee("0");//新版收银台充值不先扣手续费
		refundOrderRequest.setPayOrderNo(transOrderDTO.getPayorderno());
		refundOrderRequest.setUserOrderId(transOrderDTO.getUserorderid());
		//refundOrderRequest.setNotifyUrl(refundRequest.getNotifyUrl());
		//refundOrderRequest.setBusinessInfo(refundRequest.getBusinessInfo());
		refundOrderRequest.setCreator(transOrderDTO.getCreator());
		
		return refundOrderRequest;
	}
	
	private NotifyRefundRequest buildNotifyRefundRequest(TransOrderDTO transOrderDTO, 
			TitanOrderPayDTO payOrder){
		NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
		notifyRefundRequest.setBusiCode(BusiCodeEnum.MerchantRefund.getKey());
		notifyRefundRequest.setMerchantNo(transOrderDTO.getConstid());
		notifyRefundRequest.setOrderNo(transOrderDTO.getOrderid());
		notifyRefundRequest.setRefundAmount(String.valueOf(transOrderDTO.getAmount()));
		notifyRefundRequest.setOrderTime(payOrder.getOrderTime());
		notifyRefundRequest.setSignType(String.valueOf(payOrder.getSignType()));
		notifyRefundRequest.setVersion(payOrder.getVersion());//关于版本？
		return notifyRefundRequest;
	}
	
	private TransferRequest buildRevenueAccountTransferRequest(TransOrderDTO transOrderDTO){
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setCreator(transOrderDTO.getCreator());
		transferRequest.setUserid(transOrderDTO.getUserid()); // 转出的用户
		transferRequest.setProductId(transOrderDTO.getProductid());
		transferRequest.setRequestno(OrderGenerateService.genResquestNo()); // 业务订单号
		transferRequest.setRequesttime(DateUtil.sdf4.format(new Date())); // 请求时间
		transferRequest.setAmount(String.valueOf(transOrderDTO.getReceivedfee()));
		transferRequest.setUserfee("0");
		transferRequest.setOrderid(transOrderDTO.getOrderid());
		transferRequest.setUserrelateid(CommonConstant.RS_FANGCANG_USER_ID); // 转入的用户
		transferRequest.setInterproductid(CommonConstant.RS_FANGCANG_PRODUCT_ID_229);
		return transferRequest;
	}
	
	/**
	 * 订单退款
	 * @author Jerry
	 * @date 2017年11月15日 下午8:07:12
	 */
	private BaseResponse refundOrder(TransOrderDTO transOrderDTO, int isBackTrack){
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.putSuccess();
		try {
			if(OrderStatusEnum.isRefund(transOrderDTO.getStatusid())){
				log.error("该订单已退款，请勿重复退款,orderid:"+transOrderDTO.getOrderid());
				baseResponse.putErrorResult(TitanMsgCodeEnum.ORDER_REFUNND_IN_PROCESS.getResMsg());
				return baseResponse;
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
				log.error("退款时，未查询到成功的转账记录,支付单号Payorderno："+transOrderDTO.getPayorderno());
				baseResponse.putErrorResult("退款时，未查询到成功的转账记录");
				return baseResponse;
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
					baseResponse.putErrorResult(TitanMsgCodeEnum.TITAN_ACCOUNT_NOT_EXISTS.getResMsg());
					return baseResponse;
				}

				//账户余额不足不能退款
				BigDecimal balance = new BigDecimal(balanceResponse.getAccountBalance().get(0).getBalanceusable());
				if (balance.subtract(transferAmount).compareTo(BigDecimal.ZERO) == -1) {
					log.error("账户余额余额不足，需先充值,userid:"+transOrderDTO.getUserrelateid());
					baseResponse.putErrorResult(TitanMsgCodeEnum.ACCOUNT_BALANCE_NOT_ENOUGH.getResMsg());
					return baseResponse;
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
			titanJrRefundRequest.setFreeze(false);
			//titanJrRefundRequest.setTfsUerId(refundRequest.getTfsUserid());
			titanJrRefundRequest.setUserOrderId(transOrderDTO.getUserorderid());
			//titanJrRefundRequest.setNotifyUrl("");
			//titanJrRefundRequest.setBusinessInfo(refundRequest.getBusinessInfo());
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
				//有充值支付的退款超过30天不能做原路退回，只能退款到账户余额
				if (StringUtil.isValidString(titanOrderPayDTO.getOrderTime())) {
					Long orderDate = DateUtil.sdf5.parse(titanOrderPayDTO.getOrderTime()).getTime();
					Long nowDate = new Date().getTime();
					if (nowDate - orderDate > CommonConstant.MS) {
						titanJrRefundRequest.setToBankCardOrAccount(RefundTypeEnum.REFUND_ACCOUNT.type);
					}
				}
			} else {//直接进行账户退款,退款到账户余额
				titanJrRefundRequest.setToBankCardOrAccount(RefundTypeEnum.REFUND_ACCOUNT.type);
			}
			//如果请求条件不需要原路退回，设置直接退回卡
			if (isBackTrack != 0) {
				titanJrRefundRequest.setToBankCardOrAccount(RefundTypeEnum.REFUND_ACCOUNT.type);
			}

			//解冻操作
			log.info("5.交易单若冻结则执行解冻");
			if (OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())) {//立即解冻
				List<FundFreezeDTO> fundFreezeList = getFundFreezeList(transOrderDTO.getOrderid());
				if (null == fundFreezeList) {
					log.error("冻结单查询失败，orderid:"+transOrderDTO.getOrderid());
					baseResponse.putErrorResult(TitanMsgCodeEnum.REFUND_FAIL.getResMsg());
					return baseResponse;
				}
				UnFreeBalanceBatchRequest unFreeBalanceBatchRequest = new UnFreeBalanceBatchRequest();
				unFreeBalanceBatchRequest.setFundFreezeDTOList(fundFreezeList);
				boolean flag = titanFinancialAccountService.unfreezeAccountBalanceOne(unFreeBalanceBatchRequest);
				if (!flag) {
					log.error("资金解冻失败，orderid:"+transOrderDTO.getOrderid());
					baseResponse.putErrorResult(TitanMsgCodeEnum.REFUND_UNFREEZE_FAIL.getResMsg());
					return baseResponse;
				}
			}
			log.info("6.调用服务退款");
			TitanJrRefundResponse titanJrRefundResponse = titanFinancialRefundService.refund(titanJrRefundRequest);
			if (!titanJrRefundResponse.isResult()) {
				log.error("退款操作失败:订单orderid:"+transOrderDTO.getOrderid()+",错误信息："+ titanJrRefundResponse.getReturnMessage());
				baseResponse.putErrorResult(TitanMsgCodeEnum.REFUND_FAIL.getResMsg());
				return baseResponse;
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
			boolean flag = titanOrderService.updateTransOrder(transOrder);
			if (!flag) {
				log.error("退款单状态更新失败,orderId:"+transOrderDTO.getOrderid());
				titanFinancialUtilService.saveOrderException(transOrderDTO.getOrderid(),OrderKindEnum.OrderId, OrderExceptionEnum.Refund_Success_Update_Order_Fail, JSONSerializer.toJSON(transOrder).toString());
			}
			log.info("退款操作成功");
			
		} catch (Exception e) {
			log.error("退款出现异常", e);
		} 
		baseResponse.putSysError();
		return baseResponse;
	}

}
