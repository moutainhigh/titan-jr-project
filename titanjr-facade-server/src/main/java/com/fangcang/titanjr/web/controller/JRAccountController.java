/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName JRAccountController.java
 * @author Jerry
 * @date 2017年8月14日 上午11:14:42  
 */
package com.fangcang.titanjr.web.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.enums.FreezeTypeEnum;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderKindEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
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
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.NotifyRefundRequest;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.RefundOrderRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.request.TransferRequest;
import com.fangcang.titanjr.dto.request.UnFreeBalanceBatchRequest;
import com.fangcang.titanjr.dto.response.FreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.NotifyRefundResponse;
import com.fangcang.titanjr.dto.response.TransferResponse;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.response.BaseResponse;
import com.fangcang.titanjr.rest.enums.AccReceOperTypeEnum;
import com.fangcang.titanjr.rest.request.JRAccountReceiveRequest;
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
	
	
	/**
	 * 资金冻结在付款方的时候，供应商在线收款操作
	 * @author Jerry
	 * @date 2017年8月28日 上午11:33:23
	 */
	@RequestMapping(value = "/account/payeeAccountReceive", method = RequestMethod.POST)
    @ApiOperation(value = "供应商在线收款", produces = "application/json", httpMethod = "POST",
            response = BaseResponse.class, notes = "收款方收款操作")
    public BaseResponse payeeAccountReceive(@ApiParam(required = true, name = "jrAccountReceiveRequest", 
    	value = "收款操作请求") @RequestBody JRAccountReceiveRequest jrAccountReceiveRequest, HttpServletRequest request) {
		
		BaseResponse baseResponse = new BaseResponse();
		log.info("收款方收款操作请求参数：" + jrAccountReceiveRequest.toString());
		
		try {
			
			ValidateResponse res = GenericValidate.validateObj(jrAccountReceiveRequest);
			if(!res.isSuccess()){
				baseResponse.putErrorResult(res.getReturnMessage());
				return baseResponse;
			}
			
			//查询订单
			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setPayorderno(jrAccountReceiveRequest.getPayOrderNo());
			TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
			
			//校验信息
			baseResponse = checkInfo(jrAccountReceiveRequest, transOrderDTO);
			if(!baseResponse.isResult()){
				return baseResponse;
			}
			transOrderDTO.setCreator(jrAccountReceiveRequest.getOperator());
			
			//直接收款
			if(jrAccountReceiveRequest.getOperateType() == AccReceOperTypeEnum.RECEIVE_ONLY.getKey()){
				
				baseResponse = unfreezePayer(transOrderDTO, true);
				if(!baseResponse.isResult()) {
					return baseResponse;
				}
				
				baseResponse = transferToPayee(transOrderDTO);
				if(!baseResponse.isResult()) {
					return baseResponse;
				}
				
			//收款并冻结收款方资金	
			}else if (jrAccountReceiveRequest.getOperateType() == AccReceOperTypeEnum.RECEIVE_FREEZE.getKey()){
				
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
				
			//不收款，解冻付款方资金	
			}else {
				
				baseResponse = unfreezePayer(transOrderDTO, false);
				if(!baseResponse.isResult()) {
					return baseResponse;
				}
				//需要原路退回才执行
				if (jrAccountReceiveRequest.getIsBackTrack() != 0) {
					backTrack(transOrderDTO);
				}
				
			}
			
		} catch (Exception e) {
			
			log.error("收款方是否确认收款操作异常：", e);
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
			log.error("query trans order is failed");
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getCode()), 
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getKey());
			return baseResponse;
		}
		if (!OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())){
			log.error("trans order is not freeze");
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.ORDER_NOT_FREEZE.getCode()), 
					TitanMsgCodeEnum.ORDER_NOT_FREEZE.getKey());
			return baseResponse;
		}
		if (!FreezeTypeEnum.FREEZE_PAYER.getKey().equals(transOrderDTO.getFreezeType())) {
			log.error("trans order freezetype is not 3");
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.ORDER_FREEZETYPE_NOT3.getCode()), 
					TitanMsgCodeEnum.ORDER_FREEZETYPE_NOT3.getKey());
			return baseResponse;
		}
		if(jrAccountReceiveRequest.getTradeAmount() != transOrderDTO.getTradeamount()){
			log.error("tradeAmount is error");
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getCode()), 
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getKey());
			return baseResponse;
		}
		if(!AccReceOperTypeEnum.isExist(jrAccountReceiveRequest.getOperateType())){
			log.error("operateType is not exist");
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getCode()), 
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getKey());
			return baseResponse;
		}
		OrgBindInfo orgBindInfo = queryOrgBindInfo(jrAccountReceiveRequest.getPartnerOrgCode());
		if(orgBindInfo == null){
			log.error("query orgBindInfo failed");
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getCode()), 
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getKey());
			return baseResponse;
		}
		if(!transOrderDTO.getUserrelateid().equals(orgBindInfo.getOrgcode())){
			log.error("partnerOrgCode is error");
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
		if (null == fundFreezeDTOList || fundFreezeDTOList.size() != 1) {
			return null;
		}
		return fundFreezeDTOList;
	}
	
	
	/**
	 * 解冻付款方资金
	 * @param isReceive：是否收款
	 * @author Jerry
	 * @date 2017年8月14日 下午4:29:32
	 */
	private BaseResponse unfreezePayer(TransOrderDTO transOrderDTO, boolean isReceive) {
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.putSuccess();
		
		//查询冻结单
		List<FundFreezeDTO> fundFreezeList = getFundFreezeList(transOrderDTO.getOrderid());
		if (null == fundFreezeList) {
			log.error("冻结单查询失败，orderid:" + transOrderDTO.getOrderid());
			baseResponse.putErrorResult(String.valueOf(TitanMsgCodeEnum.QUERY_FREEZEORDER_FAIL.getCode()), 
					TitanMsgCodeEnum.QUERY_FREEZEORDER_FAIL.getKey());
			return baseResponse;
		}
		if (fundFreezeList.get(0) != null && !isReceive) {
			OrderStatusEnum orderStatusEnum = OrderStatusEnum.ORDER_FAIL;
			fundFreezeList.get(0).setOrderStatusEnum(orderStatusEnum);
		}
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
		
    	return baseResponse;
	}
	
	
	/**
	 * 转账到收款方
	 * @author Jerry
	 * @date 2017年8月14日 下午4:53:21
	 */
	private BaseResponse transferToPayee(TransOrderDTO transOrderDTO) throws Exception {
		
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.putSuccess();
		
		TransOrderDTO transOrder = new TransOrderDTO();
		transOrder.setOrderid(transOrderDTO.getOrderid());
		
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setCreator(transOrderDTO.getCreator());
		transferRequest.setUserid(transOrderDTO.getUserid()); // 转出的用户
		transferRequest.setRequestno(OrderGenerateService.genResquestNo()); // 业务订单号
		transferRequest.setRequesttime(DateUtil.sdf4.format(new Date())); // 请求时间
		transferRequest.setAmount(transOrderDTO.getTradeamount().toString());
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
		
		RechargeResultConfirmRequest rechargeResultConfirmRequest = new RechargeResultConfirmRequest();
		rechargeResultConfirmRequest.setOrderNo(transOrderDTO.getOrderid());
		rechargeResultConfirmRequest.setPayAmount(transOrderDTO.getTradeamount().toString());
		rechargeResultConfirmRequest.setUserid(transOrderDTO.getUserrelateid());
		
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
		
		//更新订单状态
		if(!titanOrderService.updateTransOrder(transOrder)){
			log.error("账户收款，冻结收款方后修改订单状态失败");
			titanFinancialUtilService.saveOrderException(transOrderDTO.getOrderid(),OrderKindEnum.OrderId, OrderExceptionEnum.AccountReceive_FreezePayee_UpdateOrder_Fail, JSONSerializer.toJSON(transOrder).toString());
		}
		
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
		baseResponse.putSuccess();
		
		log.info("查询充值记录，判断充值时间是否超过30天");
		TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
		titanOrderPayDTO.setOrderNo(transOrderDTO.getOrderid());
		TitanOrderPayDTO payOrder = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
		if (payOrder != null && StringUtil.isValidString(payOrder.getOrderTime())) {
			
			Long orderDate = DateUtil.sdf5.parse(payOrder.getOrderTime()).getTime();
			Long nowDate = new Date().getTime();
			if (nowDate - orderDate <= CommonConstant.MS) {
				
				log.info("充值未超过30天，执行原路退回，通知融数网关退款");
				RefundOrderRequest refundOrderRequest = buildRefundOrderRequest(
						transOrderDTO, payOrder.getOrderTime());
				NotifyRefundRequest notifyRefundRequest = buildNotifyRefundRequest(
						transOrderDTO, payOrder);
				NotifyRefundResponse notifyRefundResponse = titanFinancialRefundService
						.notifyRefund(refundOrderRequest, notifyRefundRequest, transOrderDTO);
				log.info("网关退款, 订单orderid: " + transOrderDTO.getOrderid()+", 响应结果:" + 
						Tools.gsonToString(notifyRefundResponse));
			}
		}
		
		return baseResponse;
	}
	
	private RefundOrderRequest buildRefundOrderRequest(TransOrderDTO transOrderDTO, 
			String orderTime){
		
		RefundOrderRequest refundOrderRequest = new RefundOrderRequest();
		refundOrderRequest.setAmount(String.valueOf(transOrderDTO.getAmount())); //充值的钱
		refundOrderRequest.setOrderId(transOrderDTO.getOrderid());
		refundOrderRequest.setOrderTime(orderTime);
		refundOrderRequest.setTransferAmount(String.valueOf(transOrderDTO.getTradeamount()));
		refundOrderRequest.setFee(String.valueOf(transOrderDTO.getReceivedfee()));
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

}