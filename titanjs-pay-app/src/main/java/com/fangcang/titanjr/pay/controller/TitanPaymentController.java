package com.fangcang.titanjr.pay.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.ReqstatusEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.bean.PayTypeEnum;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.request.TransferRequest;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.RechargeResponse;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;
import com.fangcang.titanjr.dto.response.TransferResponse;
import com.fangcang.titanjr.pay.services.TitanPaymentService;
import com.fangcang.titanjr.pay.util.JsonConversionTool;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;
@Controller
@RequestMapping("/payment")
public class TitanPaymentController extends BaseController {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private static final Log log = LogFactory.getLog(TitanPaymentController.class);
	
	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;
	
	@Resource
	private TitanPaymentService titanPaymentService;
	
	@Resource
	private TitanOrderService titanOrderService;
	
	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;
	
	private static Map<String,Object> mapLock = new  ConcurrentHashMap<String, Object>();
	
	/**
	 * 消息回调接口
	 * @param rechargeResultConfirmRequest
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
    @RequestMapping(value = "notify")
    public void payResultConfirm(RechargeResultConfirmRequest rechargeResultConfirmRequest,HttpServletResponse response) throws IOException{
		String orderNo = rechargeResultConfirmRequest.getOrderNo();
		if(!StringUtil.isValidString(orderNo)){
			log.error("RS callback is fail");
			return;
		}

		response.getWriter().print("returnCode=000000&returnMsg=成功");
		
		String sign  =titanFinancialTradeService.getSign(rechargeResultConfirmRequest);
		String signMsg = rechargeResultConfirmRequest.getSignMsg();
    	if(!MD5.MD5Encode(sign, "UTF-8").equals(signMsg)){
    	   log.error("signature verification is fail,The data is:"+JsonConversionTool.toJson(rechargeResultConfirmRequest));
   		   return;
    	}
		
    	if(!CommonConstant.PAY_SUCCESS.equals(rechargeResultConfirmRequest.getPayStatus())){
    		log.error("recharge is fail");
    		return ;
    	}
    	
    	try{
    		lockOutTradeNoList(orderNo);
        	
        	TransOrderRequest transOrderRequest = new TransOrderRequest();
    		transOrderRequest.setOrderid(orderNo);
    		TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
        	if(null == transOrderDTO){
        		log.error("the object is null");
        		unlockOutTradeNoList(orderNo);
        		return ;
        	}
        	
        	PayerTypeEnum payerType = PayerTypeEnum.getPayerTypeEnumByKey(transOrderDTO.getPayerType());
        	
        	//validate transfer order 
        	boolean validateResult = titanPaymentService.validateIsConfirmed(transOrderDTO.getTransid());
			if(!validateResult){
				log.error("transfer were successed");
				unlockOutTradeNoList(orderNo);
				return ;
			}
        	
        	// update recharge order
			int row = titanOrderService.updateTitanOrderPayreq(orderNo,ReqstatusEnum.RECHARFE_SUCCESS.getStatus()+"");
        	if(row<1){
        		OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(orderNo, "充值成功 修改充值单失败", OrderExceptionEnum.OrderPay_Update, JSON.toJSONString(orderNo));
        		titanOrderService.saveOrderException(orderExceptionDTO);
        	}
        	OrderStatusEnum orderStatusEnum = OrderStatusEnum.RECHARGE_SUCCESS;
        	
        	if(!StringUtil.isValidString(transOrderDTO.getPayermerchant())){
        		orderStatusEnum= OrderStatusEnum.ORDER_SUCCESS;
        	}else{
        		TransferRequest transferRequest = titanPaymentService.convertToTransferRequest(transOrderDTO);
	        	log.info("begin to transfer:"+JsonConversionTool.toJson(transferRequest));
	        	TransferResponse transferResponse = titanFinancialTradeService.transferAccounts(transferRequest);
	        	log.info("the result of transfer:"+JsonConversionTool.toJson(transferResponse));
	        	
	        	if(!transferResponse.isResult()){//transfer fail
	        		orderStatusEnum = OrderStatusEnum.ORDER_FAIL;
	        	}else{//transfer success
	        		orderStatusEnum = OrderStatusEnum.TRANSFER_SUCCESS;
	        		titanFinancialTradeService.confirmFinance(transOrderDTO);
	        		
	        		if(CommonConstant.FREEZE_ORDER.equals(transOrderDTO.getIsEscrowedPayment())){//需要进行冻结操作
	        			log.info("begin to freeze:"+transferRequest);
	        			boolean freezeSuccess = titanPaymentService.freezeAccountBalance(transferRequest,orderNo);
	        			log.info("the result of freeze:"+freezeSuccess);
    					//update the status of the order
    					if(freezeSuccess){//freeze order is success
    						orderStatusEnum = OrderStatusEnum.FREEZE_SUCCESS;
    					}else{
    						log.error("update the status of the order was failed,the msg is "+JsonConversionTool.toJson(transferRequest));
    						orderStatusEnum = OrderStatusEnum.FREEZE_FAIL;
    						OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(orderNo, "freeze the order was failed", OrderExceptionEnum.Freeze_Insert, JSON.toJSONString(transferRequest));
        	        		titanOrderService.saveOrderException(orderExceptionDTO);
    					}
	        		}
	        		
	        		//save the trade account
					if(payerType.isMustPayerment()){
    				    titanFinancialAccountService.addAccountHistory(transferRequest);
					}
	        		
	        	}
        	}
        	
        	//udate the status being success if the transfer is success but that is not freeze 
			if(orderStatusEnum.getStatus().equals(OrderStatusEnum.TRANSFER_SUCCESS.getStatus())){
				orderStatusEnum = OrderStatusEnum.ORDER_SUCCESS;
			}
			
			log.info("update the status of the order:"+JsonConversionTool.toJson(orderStatusEnum));
			boolean updateStatus = titanPaymentService.updateOrderStatus(transOrderDTO.getTransid(),orderStatusEnum);
			
			if(!updateStatus){//udate the status was failed 
				OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(orderNo, "freeze was successed but update the status was failed ", OrderExceptionEnum.TransOrder_update, JSON.toJSONString(transOrderDTO.getTransid()));
        		titanOrderService.saveOrderException(orderExceptionDTO);
			}
        	
    	}catch(Exception e){
            log.error(e.getMessage());    		
    	}finally{
    		unlockOutTradeNoList(orderNo);
    	}
    	
	}
	/**
	 * 需要充值的接口
	 * @param request
	 * @param paymentRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("packageRechargeData")
	public String packageRechargeData(HttpServletRequest request,TitanPaymentRequest titanPaymentRequest,Model model) throws Exception{
	
		log.info("网银支付请求参数:"+JsonConversionTool.toJson(titanPaymentRequest));
		model.addAttribute("result", "false");
		if(null == titanPaymentRequest || !StringUtil.isValidString(titanPaymentRequest.getTradeAmount()) 
				|| !StringUtil.isValidString(titanPaymentRequest.getPayAmount())){
			model.addAttribute("msg", "参数错误");
			return "checkstand-pay/genRechargePayment";
		}
		
        Map<String,Object> validResult = this.validPaymentData(titanPaymentRequest);
        if("true".equals(validResult.get("result"))){//合规性验证
        	model.addAttribute("msg", validResult.get("msg"));
			return "checkstand-pay/genRechargePayment";
        }
		
        TransOrderCreateResponse transOrderCreateResponse = titanFinancialTradeService.createRsOrder(titanPaymentRequest);
        if(!transOrderCreateResponse.isResult() || !StringUtil.isValidString(transOrderCreateResponse.getOrderNo()) ){
        	log.error("call createTitanTransOrder fail msg["+ JsonConversionTool.toJson(transOrderCreateResponse)+"]");
        	model.addAttribute("msg", transOrderCreateResponse.getReturnMessage());
        	return "checkstand-pay/genRechargePayment";
        }
        
        //设置支付方式
    	if(StringUtil.isValidString(titanPaymentRequest.getLinePayType())){
    		titanPaymentRequest.setPayType(PayTypeEnum.getPayTypeEnum(titanPaymentRequest.getLinePayType()));
    	}
    	titanPaymentRequest.setOrderid(transOrderCreateResponse.getOrderNo());
    	
    	RechargeResponse rechargeResponse = titanPaymentService.packageRechargeData(titanPaymentRequest);
    	if(!rechargeResponse.isResult()){
    		model.addAttribute("msg", rechargeResponse.getReturnMessage());
    		return "checkstand-pay/genRechargePayment";
    	}
		
    	model.addAttribute("result", "success");
    	model.addAttribute("rechargeDataDTO", rechargeResponse.getRechargeDataDTO());
    	log.info("支付请求的参数如下:"+JsonConversionTool.toJson(rechargeResponse.getRechargeDataDTO()));
    	//保存常用的支付方式
    	titanPaymentService.saveCommonPayMethod(titanPaymentRequest);
		return "checkstand-pay/genRechargePayment";
	}
	
	
    private Map<String,Object> validPaymentData(TitanPaymentRequest titanPaymentRequest){
    	Map<String,Object> resultMap = new HashMap<String, Object>();
    	//判断付款方是否存在
		boolean isExist = titanPaymentService.accountIsExist(titanPaymentRequest.getRecieveOrgName(),
				titanPaymentRequest.getRecieveTitanCode());
		
		if(!isExist){
			resultMap.put("msg", "收款方不存在");
			resultMap.put("result", false);
			return resultMap;
		}
		
		//TODO  支付请求发生时需要确定金额
		
		//交易金额
		BigDecimal tradeAmount = new BigDecimal(titanPaymentRequest.getTradeAmount());
		//在线支付金额
		BigDecimal  payAmount = new BigDecimal(titanPaymentRequest.getPayAmount());
		
		if(tradeAmount.subtract(payAmount).compareTo(BigDecimal.ZERO)==1){//有转账金额,需要输入密码
			boolean isAllowNoPwdPay = titanPaymentService.isAllowNoPwdPay(titanPaymentRequest.getUserid(), titanPaymentRequest.getTradeAmount());
	        if(!isAllowNoPwdPay){//不允许免密支付，需要输入密码
	        	boolean isTrue = titanPaymentService.checkPwd(titanPaymentRequest.getPayPassword(), titanPaymentRequest.getFcUserid());
	            if(!isTrue){
	    			resultMap.put("msg", "付款密码不正确");
	    			resultMap.put("result", false);
	    			return resultMap;
	            }
	        }
		}
		
		if(StringUtil.isValidString(titanPaymentRequest.getUserrelateid())){//收款方不为空，则判断是否自己给自己付款
			if(titanPaymentRequest.getUserrelateid().equals(titanPaymentRequest.getUserid())){
    			resultMap.put("msg", "收款账户不能和付款账户相同");
    			resultMap.put("result", false);
    			return resultMap;
			}
		}
		resultMap.put("result", true);
		return resultMap;
    }	
    

	@RequestMapping("payConfirmPage")
	public String payConfirmPage(RechargeResultConfirmRequest rechargeResultConfirmRequest,Model model){
		return titanPaymentService.payConfirmPage(rechargeResultConfirmRequest,model);
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
