package com.fangcang.titanjr.pay.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.BusinessLog;
import com.fangcang.titanjr.common.enums.CashierItemTypeEnum;
import com.fangcang.titanjr.common.enums.FreezeTypeEnum;
import com.fangcang.titanjr.common.enums.LoanProductEnum;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderKindEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.ReqstatusEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.TitanjrVersionEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.NumberUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.common.util.Wxutil;
import com.fangcang.titanjr.dto.PaySourceEnum;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.LoanSpecificationBean;
import com.fangcang.titanjr.dto.bean.RechargeDataDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.bean.TransOrderInfo;
import com.fangcang.titanjr.dto.request.AddPayLogRequest;
import com.fangcang.titanjr.dto.request.ApplyLoanRequest;
import com.fangcang.titanjr.dto.request.ConfirmOrdernQueryRequest;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.RecordRequest;
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.request.TransferRequest;
import com.fangcang.titanjr.dto.response.AccountCheckResponse;
import com.fangcang.titanjr.dto.response.ApplyLoanResponse;
import com.fangcang.titanjr.dto.response.ConfirmOrdernQueryResponse;
import com.fangcang.titanjr.dto.response.LocalAddTransOrderResponse;
import com.fangcang.titanjr.dto.response.RechargeResponse;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;
import com.fangcang.titanjr.dto.response.TransferResponse;
import com.fangcang.titanjr.enums.PayTypeEnum;
import com.fangcang.titanjr.enums.RsVersionEnum;
import com.fangcang.titanjr.pay.constant.TitanConstantDefine;
import com.fangcang.titanjr.pay.req.CreateTitanRateRecordReq;
import com.fangcang.titanjr.pay.req.OperationLoanPayReq;
import com.fangcang.titanjr.pay.req.TitanRateComputeReq;
import com.fangcang.titanjr.pay.services.TitanPaymentService;
import com.fangcang.titanjr.pay.services.TitanRateService;
import com.fangcang.titanjr.pay.services.TitanTradeService;
import com.fangcang.titanjr.pay.strategy.pay.ECPay;
import com.fangcang.titanjr.pay.strategy.pay.PayStrategy;
import com.fangcang.titanjr.pay.strategy.pay.Payment;
import com.fangcang.titanjr.pay.strategy.pay.QRCodePay;
import com.fangcang.titanjr.pay.strategy.pay.QuickPay;
import com.fangcang.titanjr.pay.util.IPUtil;
import com.fangcang.titanjr.pay.util.TerminalUtil;
import com.fangcang.titanjr.redis.service.RedisService;
import com.fangcang.titanjr.service.AccountRecordService;
import com.fangcang.titanjr.service.BusinessLogService;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialLoanService;
import com.fangcang.titanjr.service.RSGatewayInterfaceService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.JsonUtil;
import com.fangcang.util.StringUtil;

import net.sf.json.JSONSerializer;
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
	
	@Resource
	private TitanTradeService financialTradeService;
	
	@Resource
	private TitanRateService titanRateService;

	@Resource
	private TitanCashierDeskService titanCashierDeskService;
	
	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;
	
	@Resource
	private TitanFinancialLoanService titanFinancialLoanService;
	
	@Resource
	private TitanFinancialUserService titanFinancialUserService;
	
	@Resource
	private BusinessLogService businessLogService;
	
	@Resource
	private RedisService redisService;
	
	@Resource
	private AccountRecordService accountRecordService;
	
	@Resource
	private RSGatewayInterfaceService rsGatewayInterfaceService;
	
	private static Map<String,Object> mapLock = new  ConcurrentHashMap<String, Object>();
	/**
	 * 消息回调接口	 * @param rechargeResultConfirmRequest
	 * @param response
	 * @throws IOException
	 */
	@ResponseBody
    @RequestMapping(value = "notify")
    public void notify(RechargeResultConfirmRequest rechargeResultConfirmRequest,HttpServletResponse response) throws IOException{
		String orderNo = rechargeResultConfirmRequest.getOrderNo();
		log.info("收到融数通知(notify)的支付结果rechargeResultConfirmRequest："+Tools.gsonToString(rechargeResultConfirmRequest));
		
		if(!StringUtil.isValidString(orderNo)){
			log.error("notify callback is fail,orderNo is null");
			return;
		}

		response.getWriter().print("returnCode=000000&returnMsg=成功");
		response.flushBuffer();
		
		String sign  = titanPaymentService.getSign(rechargeResultConfirmRequest);
		String signMsg = rechargeResultConfirmRequest.getSignMsg();
    	if(!MD5.MD5Encode(sign, "UTF-8").equals(signMsg)){
    	   log.error("signature verification is fail,The data is:"+JsonConversionTool.toJson(rechargeResultConfirmRequest));
   		   return;
    	}
		
    	if(!CommonConstant.PAY_SUCCESS.equals(rechargeResultConfirmRequest.getPayStatus())){
    		log.error("recharge is fail");
    		return ;
    	}
    	boolean isNotify = false;//是否通知第三
    	//查询订单
    	try{
    		lockOutTradeNoList(orderNo);
        	TransOrderRequest transOrderRequest = new TransOrderRequest();
    		transOrderRequest.setOrderid(orderNo);
    		TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
        	if(null == transOrderDTO){
        		log.error("the transOrderDTO is null,orderNo:"+orderNo);
        		unlockOutTradeNoList(orderNo);
        		return ;
        	}
        	log.info("query transOrderDTO success：" + JSONSerializer.toJSON(transOrderDTO).toString());
        	businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.CallbackNotify, OrderKindEnum.TransOrderId, transOrderDTO.getTransid()+""));
        	PayerTypeEnum payerType = PayerTypeEnum.getPayerTypeEnumByKey(transOrderDTO.getPayerType());
        	
        	if(!StringUtil.isValidString(transOrderDTO.getFreezeType())){
    			transOrderDTO.setFreezeType(FreezeTypeEnum.FREEZE_PAYEE.getKey());
    		}
        	
        	
        	
        	//validate transfer order 
        	boolean validateResult = titanPaymentService.validateIsConfirmed(transOrderDTO.getTransid());
			if(!validateResult){
				log.error("该订单已转帐成功,orderNo:"+orderNo);
				unlockOutTradeNoList(orderNo);
				return ;
			}
			if(FreezeTypeEnum.FREEZE_PAYER.getKey().equals(transOrderDTO.getFreezeType()) 
					&& OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())){
				log.error("资金已冻结在付款方，不需转账   orderNo：" + orderNo);
				unlockOutTradeNoList(orderNo);
				return ;
			}
			//充值记账
			RecordRequest recordRechargeRequest  = new RecordRequest();
        	recordRechargeRequest.setAmount(Long.parseLong(rechargeResultConfirmRequest.getPayAmount()));
        	recordRechargeRequest.setTransOrderId(transOrderDTO.getTransid());
        	recordRechargeRequest.setProductId(transOrderDTO.getProductid());
        	recordRechargeRequest.setUserId(transOrderDTO.getUserid());
        	
        	accountRecordService.recharge(recordRechargeRequest);
        	
        	// update recharge order
			int row = titanOrderService.updateTitanOrderPayreq(orderNo,ReqstatusEnum.RECHARFE_SUCCESS.getStatus()+"");
        	if(row<1){
        		log.error("更新充值单失败,orderNo:"+orderNo);
        		titanFinancialUtilService.saveOrderException(orderNo,OrderKindEnum.OrderId, OrderExceptionEnum.Notify_Update_PayOrder_Fail, null);
        	}
        	
        	OrderStatusEnum orderStatusEnum = OrderStatusEnum.RECHARGE_SUCCESS;
        	if(!PayerTypeEnum.RECHARGE.key.equals(payerType.getKey())&&!validateOrderStatus(orderNo)){//非充值的需要发出三次确认订单成功到帐
    			log.error("实在没办法,钱没到账，不能转账，orderNo："+orderNo);
    			//融数可能会回调两次，使用redis控制“充值金额未到账户”的错误邮件发送次数
    			if(redisService.getValue(orderNo+"_"+OrderExceptionEnum.Notify_Money_Not_In_Account_Fail.msg) == null){
    				titanFinancialUtilService.saveOrderException(orderNo,OrderKindEnum.OrderId, OrderExceptionEnum.Notify_Money_Not_In_Account_Fail, null);
    				redisService.setValue(orderNo+"_"+OrderExceptionEnum.Notify_Money_Not_In_Account_Fail.msg, 
    						OrderExceptionEnum.Notify_Money_Not_In_Account_Fail.failState, 120);
    				log.info("充值金额未到账户已发送邮件，set redis log，orderNo：" + orderNo);
    			}
    			
    			orderStatusEnum = OrderStatusEnum.ORDER_FAIL;
    			if(CommonConstant.RS_FANGCANG_USER_ID.equals(transOrderDTO.getPayermerchant())){//中间账户的延时到帐就是失败
    				orderStatusEnum = OrderStatusEnum.ORDER_DELAY;
    			}
    			
    			titanPaymentService.updateOrderStatus(transOrderDTO,orderStatusEnum);
    			return ;
    		}
        	
        	if(PayerTypeEnum.RECHARGE.key.equals(payerType.getKey())){//如果是充值则置订单为成功
        		orderStatusEnum= OrderStatusEnum.ORDER_SUCCESS;
        		
        	}else{//不是充值操作，就需要转帐
        		
        		TransferResponse transferResponse = null;
    			TransferRequest transferRequest = titanPaymentService.convertToTransferRequest(transOrderDTO);
        		//如果冻结方案不是3才需要转账
        		if(!FreezeTypeEnum.FREEZE_PAYER.getKey().equals(transOrderDTO.getFreezeType())){
        			
            		//校验实际付款金额和订单应付金额（包含手续费）：付款方是中间账户
            		boolean payFlag = NumberUtil.subtract(rechargeResultConfirmRequest.getPayAmount(), transOrderDTO.getAmount()).floatValue()==0.0D;
            		if(!payFlag){
            			log.error("订单支付异常：支付金额和收到的金额不相等，订单号："+transOrderDTO.getOrderid());
            			titanPaymentService.updateOrderStatus(transOrderDTO,OrderStatusEnum.ORDER_FAIL);
            			titanFinancialUtilService.saveOrderException(transOrderDTO.getOrderid(),OrderKindEnum.OrderId, OrderExceptionEnum.Notify_Order_Amount_Execption,null);
            			return ;
            		}
            		
            		log.info("begin to transfer:"+JsonConversionTool.toJson(transferRequest));
    	        	transferResponse = titanFinancialTradeService.transferAccounts(transferRequest);
    	        	log.info("the result of transfer :"+JsonConversionTool.toJson(transferResponse)+",orderid:"+transferRequest.getOrderid());
    	        	 
        		}
	        	
	        	if(transferResponse != null && !transferResponse.isResult()){//转账失败
	        		orderStatusEnum = OrderStatusEnum.ORDER_FAIL;
	        		
	        	}else{
	        		//充值或者支付有手续费，需要将手续费转到收益子账户
        			if(transOrderDTO.getReceivedfee() != null && transOrderDTO.getReceivedfee() > 0){
        				TransferRequest transferRevenueAccountRequest = titanPaymentService
        						.getRevenueAccountTransferRequest(transOrderDTO);
        				transferResponse = titanFinancialTradeService.transferAccounts(transferRevenueAccountRequest);
        				if(transferResponse.isResult()){
        					businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.TransferSucc, OrderKindEnum.TransOrderId, transOrderDTO.getTransid()+""));
        				}else{
        					log.error("transfer to revenueAccount success faild, transOrderId: " + transOrderDTO.getTransid());
        					titanFinancialUtilService.saveOrderException(orderNo,OrderKindEnum.OrderId, OrderExceptionEnum.Transfer_revenueAccount_Fail,orderStatusEnum.getStatus());
        				}
        			}
	        		
	        		businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.TransferSucc, OrderKindEnum.TransOrderId, transOrderDTO.getTransid()+""));
	        		orderStatusEnum = OrderStatusEnum.TRANSFER_SUCCESS;
	        		isNotify = true;
	        		
	        		//根据订单冻结方案进行冻结操作
	        		if(CommonConstant.FREEZE_ORDER.equals(transOrderDTO.getIsEscrowedPayment())){
	        			log.info("begin to freeze, transferRequest：" + JSONSerializer.toJSON(transferRequest).toString());
	        			int freezeSuccess = titanPaymentService.freezeAccountBalance(transferRequest, transOrderDTO);
	        			log.info("the result of freeze, orderNo:"+orderNo+", freeze status："+freezeSuccess);
    					
	        			if(freezeSuccess == 0){//不需要冻结，订单状态为成功
	        				orderStatusEnum = OrderStatusEnum.ORDER_SUCCESS;
	        			}else if(freezeSuccess == 1){//冻结付款方
	        				transOrderDTO.setFreezeAt(CommonConstant.FREEZE_PAYER);
    						orderStatusEnum = OrderStatusEnum.FREEZE_SUCCESS;
    						businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.FreezeSucc, OrderKindEnum.TransOrderId, transOrderDTO.getTransid()+""));
    					}else if(freezeSuccess == 2){//冻结收款方
    						transOrderDTO.setFreezeAt(CommonConstant.FREEZE_PAYEE);
    						orderStatusEnum = OrderStatusEnum.FREEZE_SUCCESS;
    						businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.FreezeSucc, OrderKindEnum.TransOrderId, transOrderDTO.getTransid()+""));
    					}else{//冻结失败
    						log.error("订单冻结失败,订单号orderid："+Tools.gsonToString(transOrderDTO.getOrderid())+",冻结返回值："+freezeSuccess);
    						orderStatusEnum = OrderStatusEnum.FREEZE_FAIL;
    						titanFinancialUtilService.saveOrderException(orderNo,OrderKindEnum.OrderId, OrderExceptionEnum.Notify_Freeze_Insert_Fail, JSONSerializer.toJSON(transOrderDTO).toString());
    					}
	        		}
	        		
	        		//财务供应商保存账户历史
					if(payerType.isAddAccountHistory()){
						titanPaymentService.addAccountHistory(transOrderDTO);
					}
	        	}
        	}
        	
        	//udate the status being success if the transfer is success but that is not freeze 
			if(orderStatusEnum.getStatus().equals(OrderStatusEnum.TRANSFER_SUCCESS.getStatus())){
				orderStatusEnum = OrderStatusEnum.ORDER_SUCCESS;
			}
			
			log.info("update the status of the order:"+JsonConversionTool.toJson(orderStatusEnum)+",orderNo:"+orderNo);
			boolean updateStatus = titanPaymentService.updateOrderStatus(transOrderDTO,orderStatusEnum);
			if(isNotify){//该代码一定要在保存完本地订单状态后再执行，通知第三方系统
				financialTradeService.notifyPayResult(transOrderDTO.getUserorderid());
			}
			if(!updateStatus){//udate the status was failed 
				log.error("冻结成功修改订单状态失败：update the status of the order were failed");
				titanFinancialUtilService.saveOrderException(orderNo,OrderKindEnum.OrderId, OrderExceptionEnum.Online_Freeze_Success_Update_Order_Fail,orderStatusEnum.getStatus());
			}
        	
    	}catch(Exception e){
            log.error("支付通知时转账失败，订单号orderid:"+orderNo ,e);    		
    	}finally{
    		unlockOutTradeNoList(orderNo);
    	}
    	
	}
	
	
	private boolean validateOrderStatus(String orderNo){

		ConfirmOrdernQueryRequest request = new ConfirmOrdernQueryRequest();
		request.setOrderNo(orderNo);
		request.setMerchantcode(CommonConstant.RS_FANGCANG_CONST_ID);
		
		ConfirmOrdernQueryResponse response = null;
		
		TransOrderInfo order = null;
		// 重试三次
		for (int i = 0; i < 3; i++) {

			response = titanFinancialTradeService.confirmRechargeStatus(request);

			if (response == null || !response.isResult()
					|| null == response.getTransOrderInfos()
					|| response.getTransOrderInfos().size() != 1) {
				log.error("confirem ordern query is null,param:"+Tools.gsonToString(request)+",response:"+Tools.gsonToString(response));
				try {//线程等待
					if(i<2){
						Thread.sleep(2000 * (2<<i));
					}
				} catch (InterruptedException e) {
					log.error("", e);
				}
				continue;
			}

			order = response.getTransOrderInfos().get(0);

			log.info("查询订单状态,第["+i+"]次，订单号："+orderNo + ",状态:" + order.getOrderstatus());

			if (CommonConstant.RS_ORDER_STATUS.equals(order.getOrderstatus())) {
				return true;
			}
			try {
				if(i<2){
					Thread.sleep(500 * (2<<i));
				}
				
			} catch (InterruptedException e) {
				log.error("", e);
			}
		}
		return false;
	}
	
	@RequestMapping("notifyPayResult")
	public void notifyPayResult(String userOrderId){
		financialTradeService.notifyPayResult(userOrderId);
	}
	
	/**
	 * 全部用账户余额支付订单
	 * @param request
	 * @param titanPaymentRequest
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/showTitanPayPage")
	public String showTitanPayPage(HttpServletRequest request,TitanPaymentRequest titanPaymentRequest) throws Exception{
		log.info("账户余额支付请求参数:"+JsonConversionTool.toJson(titanPaymentRequest));
		if(null == titanPaymentRequest || !StringUtil.isValidString(titanPaymentRequest.getTradeAmount() )
				||!StringUtil.isValidString(titanPaymentRequest.getPayOrderNo())){
			log.error("账户余额支付参数不合法，参数："+JsonConversionTool.toJson(titanPaymentRequest));
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}
		
	    Map<String,String> validResult = this.validPaymentData(titanPaymentRequest);
        if(!CommonConstant.OPERATE_SUCCESS.equals(validResult.get(CommonConstant.RESULT))){//合规性验证
        	log.error("账户余额支付参数验证失败，验证参数:"+JsonConversionTool.toJson(titanPaymentRequest)+"验证结果："+JSONSerializer.toJSON(validResult));
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("result", validResult.get(CommonConstant.RESULT));
    		map.put("resultMsg", validResult.get(CommonConstant.RETURN_MSG));
        	return JSONSerializer.toJSON(map).toString();
        }
        
        //计算并设置费率（财务付款的收银台余额支付不收手续费，不用计算）
        TitanRateComputeReq computeReq = null;
        if(!PaySourceEnum.FINANCE_SUPPLY_PC.getDeskCode().equals(titanPaymentRequest.getPaySource())){
        	titanPaymentRequest.setPayAmount(titanPaymentRequest.getTradeAmount());
            titanPaymentRequest.setTransferAmount(titanPaymentRequest.getTradeAmount());
     		computeReq = new TitanRateComputeReq();
     		rateCompute(computeReq, titanPaymentRequest);
        }
		
		LocalAddTransOrderResponse localOrderResp = titanFinancialTradeService.addLocalTransOrder(titanPaymentRequest);
        log.info("the params of local order:"+JsonConversionTool.toJson(titanPaymentRequest)+"the result of local order:"+JsonConversionTool.toJson(localOrderResp));
		
        if (!localOrderResp.isResult()) {
        	log.error("the result of local order was failed");
        	Map<String, Object> map = new HashMap<String, Object>();
    		map.put("result", localOrderResp.getReturnCode());
    		map.put("resultMsg", localOrderResp.getReturnMessage());
    		return JSONSerializer.toJSON(map).toString();
        }
        titanPaymentRequest.setOrderid(localOrderResp.getOrderNo());
        //添加费率记录
        if(computeReq != null){
        	addRateRecord(computeReq, titanPaymentRequest);
        }
        
        TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setOrderid(localOrderResp.getOrderNo());
		TransOrderDTO transOrder= titanOrderService.queryTransOrderDTO(transOrderRequest);
		OrderStatusEnum orderStatusEnum = OrderStatusEnum.ORDER_IN_PROCESS;
       
		if(!StringUtil.isValidString(transOrder.getFreezeType())){
			transOrder.setFreezeType(FreezeTypeEnum.FREEZE_PAYEE.getKey());
		}
		titanPaymentRequest.setPayerType(transOrder.getPayerType());
		
		TransferRequest transferRequest = this.convertToTransferRequest(titanPaymentRequest);
		TransferResponse transferResponse = null;
		//如果冻结方案不是3才需要转账
		if(!FreezeTypeEnum.FREEZE_PAYER.getKey().equals(transOrder.getFreezeType())){
			log.info("余额支付，开始转账，orderNo：" + transOrder.getOrderid());
			//存在安全隐患，如果余额支付两次会不会存在重复支付
	        lockOutTradeNoList(titanPaymentRequest.getPayOrderNo());//锁定支付单
	        transferResponse = titanFinancialTradeService.transferAccounts(transferRequest);
        	//新版收银台的余额支付如果是非财务付款，需要将手续费转到收益子账户
	        if(transferResponse.isResult() 
	        		&& TitanjrVersionEnum.VERSION_2.getKey().equals(titanPaymentRequest.getJrVersion()) 
	        		&& !PaySourceEnum.FINANCE_SUPPLY_PC.getDeskCode().equals(titanPaymentRequest.getPaySource())){
				if(titanPaymentRequest.getReceivedfee() != null && Integer.parseInt(titanPaymentRequest.getReceivedfee()) > 0){
					log.info("余额支付，手续费转入收益子账户，orderNo：" + transOrder.getOrderid());
					TransferRequest transferRevenueAccountRequest = this.getRevenueAccountTransferRequest(titanPaymentRequest);
					transferResponse = titanFinancialTradeService.transferAccounts(transferRevenueAccountRequest);
					if(transferResponse.isResult()){
						log.info("transfer to revenueAccount success, orderNo: " + transOrder.getOrderid());
						businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.TransferSucc, OrderKindEnum.TransOrderId, transOrder.getTransid()+""));
					}else{
						log.error("transfer to revenueAccount success faild, orderNo: " + transOrder.getOrderid());
						titanFinancialUtilService.saveOrderException(transOrder.getOrderid(),OrderKindEnum.OrderId, OrderExceptionEnum.Transfer_revenueAccount_Fail,orderStatusEnum.getStatus());
					}
				}
	        }
	        unlockOutTradeNoList(titanPaymentRequest.getPayOrderNo());//解锁支付单
		}
        
		if(transferResponse != null && !transferResponse.isResult()){
			log.error("transfer is failed");
			orderStatusEnum = OrderStatusEnum.ORDER_FAIL;
			boolean updateStatus = titanPaymentService.updateOrderStatus(transOrder,orderStatusEnum);
			if(!updateStatus){
				log.error("转账后更新订单失败："+transOrder.getTransid());
				titanFinancialUtilService.saveOrderException(titanPaymentRequest.getPayOrderNo(),OrderKindEnum.PayOrderNo, OrderExceptionEnum.Balance_Pay_Update_TransOrder_Fail, JSONSerializer.toJSON(transOrder).toString());
			}
			return toMsgJson(TitanMsgCodeEnum.TRANSFER_FAIL);
		}
		
		orderStatusEnum = OrderStatusEnum.ORDER_SUCCESS;
		if(CommonConstant.FREEZE_ORDER.equals(transOrder.getIsEscrowedPayment())){
			int freezeSuccess = titanPaymentService.freezeAccountBalance(transferRequest, transOrder);
			//修改订单状态
			if(freezeSuccess == 0){
				log.info("不需要冻结，订单状态为成功");
				orderStatusEnum = OrderStatusEnum.ORDER_SUCCESS;
			}else if(freezeSuccess == 1){
				log.info("冻结付款方");
				transOrder.setFreezeAt(CommonConstant.FREEZE_PAYER);
				orderStatusEnum = OrderStatusEnum.FREEZE_SUCCESS;
				businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.FreezeSucc, OrderKindEnum.TransOrderId, transOrder.getTransid()+""));
			}else if(freezeSuccess == 2){
				log.info("冻结收款方");
				transOrder.setFreezeAt(CommonConstant.FREEZE_PAYEE);
				orderStatusEnum = OrderStatusEnum.FREEZE_SUCCESS;
				businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.FreezeSucc, OrderKindEnum.TransOrderId, transOrder.getTransid()+""));
			}else{//冻结失败
				log.error("冻结失败");
				orderStatusEnum =OrderStatusEnum.FREEZE_FAIL;
				titanFinancialUtilService.saveOrderException(localOrderResp.getOrderNo(),OrderKindEnum.OrderId, OrderExceptionEnum.Balance_Pay_Freeze_Fail, JSONSerializer.toJSON(transferRequest).toString());
			}
		}
		
		//中间账户不计历史
		if(!transOrder.getUserid().equals(titanFinancialAccountService.getDefaultPayerConfig().getUserId())){
			titanPaymentService.addAccountHistory(transOrder);
		}
		
		boolean updateStatus = titanPaymentService.updateOrderStatus(transOrder,orderStatusEnum);
		if(!updateStatus){
			log.error("冻结余额后更新订单失败");
			titanFinancialUtilService.saveOrderException(transOrder.getPayorderno(),OrderKindEnum.PayOrderNo, OrderExceptionEnum.Balance_Pay_Update_TransOrder_Fail, JSONSerializer.toJSON(transOrder).toString());
		}
		financialTradeService.notifyPayResult(localOrderResp.getUserOrderId());
		
		return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS,transOrder.getOrderid());
	}
	

	private TransferRequest convertToTransferRequest(TitanPaymentRequest titanPaymentRequest){
		PayerTypeEnum payerTypeEnum = PayerTypeEnum
				.getPayerTypeEnumByKey(titanPaymentRequest.getPayerType());
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setCreator(titanPaymentRequest.getCreator());
    	transferRequest.setUserid(titanPaymentRequest.getUserid());										//转出的用户
    	transferRequest.setRequestno(OrderGenerateService.genResquestNo());									//业务订单号
    	transferRequest.setRequesttime(DateUtil.sdf4.format(new Date()));				//请求时间
    	//新版收银台转账金额需要考虑手续费
    	if(TitanjrVersionEnum.isVersion1(titanPaymentRequest.getJrVersion())){
    		transferRequest.setAmount(NumberUtil.covertToCents(titanPaymentRequest.getTradeAmount()));	
    	}else{
    		if (titanPaymentRequest.getReceivedfee() != null && payerTypeEnum != null && !payerTypeEnum.isNeedPayerInfo()) {
    			log.info("收款方出手续费的，交易金额减去手续费，手续费将转入收益子账户");
	    		transferRequest.setAmount(String.valueOf(Integer.parseInt(NumberUtil.covertToCents(titanPaymentRequest.getTradeAmount()))-Integer.parseInt(titanPaymentRequest.getReceivedfee())));//金额 必须是分
    		}else{
    			transferRequest.setAmount(NumberUtil.covertToCents(titanPaymentRequest.getTradeAmount()));
    		}
    	}						
    	transferRequest.setUserfee("0");
    	transferRequest.setUserrelateid(titanPaymentRequest.getUserrelateid());	                   //接收方用户Id
    	transferRequest.setOrderid(titanPaymentRequest.getOrderid());
		return transferRequest;
	}
	
	public TransferRequest getRevenueAccountTransferRequest(TitanPaymentRequest titanPaymentRequest){
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setCreator(titanPaymentRequest.getCreator());
		transferRequest.setUserid(titanPaymentRequest.getUserid()); // 转出的用户
		transferRequest.setProductId(CommonConstant.RS_FANGCANG_PRODUCT_ID);
		transferRequest.setRequestno(OrderGenerateService.genResquestNo()); // 业务订单号
		transferRequest.setRequesttime(DateUtil.sdf4.format(new Date())); // 请求时间
		transferRequest.setAmount(titanPaymentRequest.getReceivedfee());
		transferRequest.setUserfee("0");
		transferRequest.setOrderid(titanPaymentRequest.getOrderid());
		transferRequest.setUserrelateid(CommonConstant.RS_FANGCANG_USER_ID); // 转入的用户
		transferRequest.setInterproductid(CommonConstant.RS_FANGCANG_PRODUCT_ID_229);
		return transferRequest;
	}
	
	@RequestMapping("qrCodePayment")
	public String qrCodePayment(HttpServletRequest request,TitanPaymentRequest titanPaymentRequest,Model model) throws Exception{
		this.packageRechargeData(request, titanPaymentRequest, model);
		return CommonConstant.PAY_WX; 
	}
	//新版收银台扫码支付
	@RequestMapping("qrCodePaymentUpgrade")
	@ResponseBody
	public String qrCodePaymentUpgrade(HttpServletRequest request,TitanPaymentRequest titanPaymentRequest,Model model) throws Exception{
		return this.packageRechargeData(request, titanPaymentRequest, model);
	}
	
	@RequestMapping("showQuickPayView")
	public String showQuickPayView(Model model){
		return "checkstand-pay/quickPayView";
	}
	
	/**
	 * 快捷支付--需要返回json数据
	 * @author Jerry
	 */
	@RequestMapping("quickPayRecharge")
	@ResponseBody
	public String quickPayRecharge(HttpServletRequest request,TitanPaymentRequest titanPaymentRequest,Model model) throws Exception{
		String rechargeJson = packageRechargeData(request, titanPaymentRequest, model);
		return rechargeJson;
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
		//设置第三方支付平台的版本
		if(CashierItemTypeEnum.isQuickPay(titanPaymentRequest.getLinePayType())){
			titanPaymentRequest.setRsVersion(RsVersionEnum.Version_2.key);
		}else{
			titanPaymentRequest.setRsVersion(RsVersionEnum.Version_1.key);
		}
		
		log.info("网银支付请求参数titanPaymentRequest:"+JsonConversionTool.toJson(titanPaymentRequest));
		model.addAttribute(CommonConstant.RESULT, CommonConstant.OPERATE_FAIL);
		businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.BeginPackageRechargeData, OrderKindEnum.PayOrderNo, titanPaymentRequest.getPayOrderNo()));
		//检查必填参数
		if(null == titanPaymentRequest || !StringUtil.isValidString(titanPaymentRequest.getTradeAmount())){
			log.error("订单金额不能为空，参数titanPaymentRequest:"+JsonConversionTool.toJson(titanPaymentRequest));
			model.addAttribute(CommonConstant.RETURN_MSG, "必填参数不能为空");
			if(TitanjrVersionEnum.VERSION_2.getKey().equals(titanPaymentRequest.getJrVersion())){
				if(CashierItemTypeEnum.needReturnJson(titanPaymentRequest.getLinePayType())){
					return JsonUtil.objectToJson("必填参数不能为空");
				}
			}
			return CommonConstant.GATE_WAY_PAYGE;
		}
		//计算支付金额(不包含手续费)，余额，
		String  payAmount = "0";//网银需要支付的金额
		String	transferAmount= "0";//余额要支付的金额
		if(PaySourceEnum.RECHARGE.getDeskCode().equals(titanPaymentRequest.getPaySource())){//充值单
			payAmount = titanPaymentRequest.getTradeAmount();
		}else{//付款
			if("1".equals(titanPaymentRequest.getIsaccount())){//勾选了余额支付
				TransOrderRequest transOrderRequest = new TransOrderRequest();
				transOrderRequest.setPayorderno(titanPaymentRequest.getPayOrderNo());
				TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
				if (titanFinancialAccountService.getDefaultPayerConfig().getUserId().equals(titanPaymentRequest.getUserid())) {//中间账户不支持余额支付
					model.addAttribute(CommonConstant.RETURN_MSG, "该业务暂时不允许用余额支付");
					if(TitanjrVersionEnum.VERSION_2.getKey().equals(titanPaymentRequest.getJrVersion())){
						if(CashierItemTypeEnum.needReturnJson(titanPaymentRequest.getLinePayType())){
							return JsonUtil.objectToJson("该业务暂时不允许用余额支付");
						}
					}
					return CommonConstant.GATE_WAY_PAYGE;
				}
				// 付款方不是中间账户就需要查询账户信息	
				AccountBalance accountBalance = financialTradeService.getAccountBalance(transOrderDTO.getUserid());
				String balanceusable = accountBalance.getBalanceusable();//可用余额,元
				if(NumberUtil.subtract(titanPaymentRequest.getTradeAmount(),balanceusable).floatValue()>0){
					//订单金额大于余额，需要网银再支付剩下的款
					payAmount = NumberUtil.subtract(titanPaymentRequest.getTradeAmount(),balanceusable).toString();
					transferAmount = balanceusable;
				}else{
					//余额大于订单金额，可以只用余额支付，不用网银支付
					payAmount = "0";
					transferAmount = titanPaymentRequest.getTradeAmount();
				}
			}else{//没有勾选余额支付
				payAmount = titanPaymentRequest.getTradeAmount();
				transferAmount= "0";
			}
		}
		titanPaymentRequest.setPayAmount(payAmount);
		titanPaymentRequest.setTransferAmount(transferAmount);
		
		//非充值单才校验，通常是支付单
		if(!PaySourceEnum.RECHARGE.getDeskCode().equals(titanPaymentRequest.getPaySource())){
			//检查sign
			String paramSing = titanPaymentRequest.getSign();
			String md5Sign = md5Sign(titanPaymentRequest, TitanConstantDefine.PAY_APP_CASHIER_SIGN_MD5_KEY);
			if(!(StringUtil.isValidString(paramSing)&&paramSing.equals(md5Sign))){
				log.error("网银支付请求参数签名错误,，参数titanPaymentRequest:"+JsonConversionTool.toJson(titanPaymentRequest)+",签名sing:"+md5Sign);
				model.addAttribute(CommonConstant.RETURN_MSG, "参数签名错误");
				if(TitanjrVersionEnum.VERSION_2.getKey().equals(titanPaymentRequest.getJrVersion())){
					if(CashierItemTypeEnum.needReturnJson(titanPaymentRequest.getLinePayType())){
						return JsonUtil.objectToJson("参数签名错误");
					}
				}
				return CommonConstant.GATE_WAY_PAYGE;
			}
			//如果付款到中间账户的方式，就没有余额支付.后期跟进业务调整
			if(("1".equals(titanPaymentRequest.getIsaccount()))&&titanFinancialAccountService.getDefaultPayerConfig().getUserId().equals(titanPaymentRequest.getUserid())){
				model.addAttribute(CommonConstant.RETURN_MSG, "该业务暂时不允许用余额支付");
				if(TitanjrVersionEnum.VERSION_2.getKey().equals(titanPaymentRequest.getJrVersion())){
					if(CashierItemTypeEnum.needReturnJson(titanPaymentRequest.getLinePayType())){
						return JsonUtil.objectToJson("该业务暂时不允许用余额支付");
					}
				}
				return CommonConstant.GATE_WAY_PAYGE;
			}
		}
		
		
		if(!titanPaymentRequest.getPaySource().equals(PaySourceEnum.RECHARGE.getDeskCode()) )
		{
	        Map<String,String> validResult = this.validPaymentData(titanPaymentRequest);
	        if(!CommonConstant.OPERATE_SUCCESS.equals(validResult.get(CommonConstant.RESULT))){//合规性验证
	        	log.error("网银支付验证参数失败,，参数titanPaymentRequest:"+JsonConversionTool.toJson(titanPaymentRequest));
	        	model.addAttribute(CommonConstant.RETURN_MSG, validResult.get(CommonConstant.RETURN_MSG));
	        	if(TitanjrVersionEnum.VERSION_2.getKey().equals(titanPaymentRequest.getJrVersion())){
					if(CashierItemTypeEnum.needReturnJson(titanPaymentRequest.getLinePayType())){
						return JsonUtil.objectToJson(validResult.get(CommonConstant.RETURN_MSG));
					}
				}
				return CommonConstant.GATE_WAY_PAYGE;
	        }
		}
		// 确认收银台支付类型是否存在
		CashierItemTypeEnum cashierItemTypeEnum = CashierItemTypeEnum
				.getCashierItemTypeEnumByKey(titanPaymentRequest
						.getLinePayType());

		if (cashierItemTypeEnum == null) {
			log.error("支付类型不存在，LinePayType 不存在");
			model.addAttribute(CommonConstant.RETURN_MSG,
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getKey());
			if(TitanjrVersionEnum.VERSION_2.getKey().equals(titanPaymentRequest.getJrVersion())){
				if(CashierItemTypeEnum.needReturnJson(titanPaymentRequest.getLinePayType())){
					return JsonUtil.objectToJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getKey());
				}
			}
			return CommonConstant.GATE_WAY_PAYGE;
		}
		
		// 开始计算并设置费率
		TitanRateComputeReq computeReq = new TitanRateComputeReq();
		rateCompute(computeReq, titanPaymentRequest);
		
		businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.CreateRsOrder, OrderKindEnum.PayOrderNo, titanPaymentRequest.getPayOrderNo()));
		
        TransOrderCreateResponse transOrderCreateResponse = titanFinancialTradeService.createRsOrder(titanPaymentRequest);
        if(!transOrderCreateResponse.isResult() || !StringUtil.isValidString(transOrderCreateResponse.getOrderNo()) ){
        	log.error("call createTitanTransOrder fail msg["+ JsonConversionTool.toJson(transOrderCreateResponse)+"]");
        	model.addAttribute("msg", transOrderCreateResponse.getReturnMessage());
        	if(TitanjrVersionEnum.VERSION_2.getKey().equals(titanPaymentRequest.getJrVersion())){
				if(CashierItemTypeEnum.needReturnJson(titanPaymentRequest.getLinePayType())){
					return JsonUtil.objectToJson(transOrderCreateResponse.getReturnMessage());
				}
			}
        	return CommonConstant.GATE_WAY_PAYGE;
        }
        
        //设置支付方式
    	if(StringUtil.isValidString(titanPaymentRequest.getLinePayType())){
    		titanPaymentRequest.setPayType(PayTypeEnum.getPayTypeEnum(titanPaymentRequest.getLinePayType()));
    		if(CommonConstant.ALIPAY.equals(titanPaymentRequest.getBankInfo())){
    			titanPaymentRequest.setPayType(PayTypeEnum.ALIPAY_URL);
    		}
    	}
    	titanPaymentRequest.setOrderid(transOrderCreateResponse.getOrderNo());
    	
    	RechargeResponse rechargeResponse = titanPaymentService.packageRechargeData(titanPaymentRequest);
    	if(!rechargeResponse.isResult()){
    		log.error("封装充值参数失败");
    		model.addAttribute(CommonConstant.RETURN_MSG, rechargeResponse.getReturnMessage());
    		if(TitanjrVersionEnum.VERSION_2.getKey().equals(titanPaymentRequest.getJrVersion())){
				if(CashierItemTypeEnum.needReturnJson(titanPaymentRequest.getLinePayType())){
					return JsonUtil.objectToJson(rechargeResponse.getReturnMessage());
				}
			}
    		return CommonConstant.GATE_WAY_PAYGE;
    	}
		
    	//添加费率记录
    	addRateRecord(computeReq, titanPaymentRequest);
    	//保存常用支付方式
		if(TitanjrVersionEnum.isVersion1(titanPaymentRequest.getJrVersion())){
			titanPaymentService.saveCommonPayMethod(titanPaymentRequest);
		}else{
			if(CashierItemTypeEnum.isNeedSaveCommonpay(titanPaymentRequest.getLinePayType())){
				titanPaymentService.saveCommonPayHistory(titanPaymentRequest, null);
			}
		}
		
		RechargeDataDTO rechargeDataDTO = rechargeResponse.getRechargeDataDTO();
		PayStrategy strategy = null;
		if(PayTypeEnum.between(titanPaymentRequest.getPayType().getKey(), PayTypeEnum.WECHAT_URL, PayTypeEnum.ALIPAY_URL)){
			strategy = new QRCodePay(); //第三方扫码支付
			
		}else if(PayTypeEnum.between(titanPaymentRequest.getPayType().getKey(), PayTypeEnum.QUICK_PAY_NEW)){
			String strUserAgent = request.getHeader("user-agent").toString().toLowerCase();
			String terminalType = null;
			rechargeDataDTO.setTerminalIp(IPUtil.getUserRealIP(request));
			if(TerminalUtil.check(strUserAgent)){
				terminalType = "wap";
			}else{
				terminalType = "web";
			}
			rechargeDataDTO.setTerminalType(terminalType);
			rechargeDataDTO.setTerminalInfo("null_MAC");
			strategy = new QuickPay(); //快捷支付
			
		}else{
			strategy = new ECPay(); //网银支付
			
		}
		Payment payment = new Payment(strategy);
		return payment.doPay(rechargeDataDTO, titanPaymentRequest, model);
	}
	
	/**
	 * 计算并设置费率
	 * @author Jerry
	 * @date 2017年10月18日 下午5:07:58
	 * @param computeReq
	 * @param titanPaymentRequest
	 */
	private void rateCompute(TitanRateComputeReq computeReq, TitanPaymentRequest titanPaymentRequest){
		// 开始计算并设置费率
		computeReq.setAmount(titanPaymentRequest.getPayAmount());
		computeReq.setPayType(titanPaymentRequest.getLinePayType());//第三方支付是9，未细分
		computeReq.setUserId(titanPaymentRequest.getUserid());
		computeReq.setDeskId(titanPaymentRequest.getDeskId());
		//财务端收银台或者充值收付款方的手续费
		if(PaySourceEnum.FINANCE_SUPPLY_PC.getDeskCode().equals(titanPaymentRequest.getPaySource()) 
				|| PaySourceEnum.RECHARGE.getDeskCode().equals(titanPaymentRequest.getPaySource())){
			computeReq.setUserId(titanPaymentRequest.getUserid());
		}else{
			computeReq.setUserId(titanPaymentRequest.getUserrelateid());
		}
		
		//设置费率信息
		titanPaymentRequest = titanRateService.setRateAmount(computeReq,
				titanPaymentRequest);
	}
	
	/**
	 * 添加费率记录
	 * @author Jerry
	 * @date 2017年10月18日 下午5:28:29
	 * @param computeReq
	 * @param titanPaymentRequest
	 */
	private void addRateRecord(TitanRateComputeReq computeReq, TitanPaymentRequest titanPaymentRequest){
		CashierItemTypeEnum cashierItemTypeEnum = CashierItemTypeEnum
				.getCashierItemTypeEnumByKey(titanPaymentRequest
						.getLinePayType());
		CreateTitanRateRecordReq req = new CreateTitanRateRecordReq();
		req.setAmount(Long.parseLong(NumberUtil.covertToCents(computeReq
				.getAmount())));
		req.setReceivablefee(Long.parseLong(titanPaymentRequest
				.getReceivablefee()));
		req.setReceivedfee(Long.parseLong(titanPaymentRequest.getReceivedfee()));
		req.setStanderdfee(Long.parseLong(titanPaymentRequest.getStandfee()));
		req.setPayType(Integer.parseInt(cashierItemTypeEnum.itemCode));
		if (StringUtil.isValidString(titanPaymentRequest.getPaySource())) {
			req.setUsedFor(Integer.parseInt(titanPaymentRequest.getPaySource()));
		}
		req.setUserId(computeReq.getUserId());
		req.setReceivableRate(titanPaymentRequest.getReceivablerate());
		req.setReceivedRate(titanPaymentRequest.getExecutionrate());
		req.setStandardRate(titanPaymentRequest.getStandardrate());
		req.setRateType(titanPaymentRequest.getRateType());
		req.setOrderNo(titanPaymentRequest.getOrderid());
		req.setCreator(computeReq.getUserId());
		titanRateService.addRateRecord(req);
	}
	
	private String md5Sign(TitanPaymentRequest titanPaymentRequest,String md5key){
		StringBuilder stringBuilder = new StringBuilder("1=2");
		if(StringUtil.isValidString(titanPaymentRequest.getUserid())){
			stringBuilder.append("&").append("userId=").append(titanPaymentRequest.getUserid());
		}
		if(StringUtil.isValidString(titanPaymentRequest.getPayOrderNo())){
			stringBuilder.append("&").append("payOrderNo=").append(titanPaymentRequest.getPayOrderNo());
		}
 
		if(StringUtil.isValidString(titanPaymentRequest.getTradeAmount())){
			stringBuilder.append("&").append("amount=").append(titanPaymentRequest.getTradeAmount());
		}
 
		if(StringUtil.isValidString(titanPaymentRequest.getFcUserid())){
			stringBuilder.append("&").append("fcUserid=").append(titanPaymentRequest.getFcUserid());
		}
 
		if(StringUtil.isValidString(titanPaymentRequest.getCreator())){
			stringBuilder.append("&").append("operator=").append(titanPaymentRequest.getCreator());
		}
		if(StringUtil.isValidString(titanPaymentRequest.getPaySource())){
			stringBuilder.append("&").append("paySource=").append(titanPaymentRequest.getPaySource());
		}
 
		if(StringUtil.isValidString(titanPaymentRequest.getDeskId())){
			stringBuilder.append("&").append("deskId=").append(titanPaymentRequest.getDeskId());
		}
		stringBuilder.append("&").append("key=").append(md5key);
		log.info("封装支付参数packageRechargeData,md5原明文"+stringBuilder.toString());
		return MD5.MD5Encode(stringBuilder.toString(), "UTF-8");
	}
	
 	
    private Map<String,String> validPaymentData(TitanPaymentRequest titanPaymentRequest){
    	Map<String,String> resultMap = new HashMap<String, String>();
    	//判断收款方是否存在
    	AccountCheckResponse  accountCheckResponse = titanPaymentService.accountIsExist(titanPaymentRequest.getRecieveOrgName(),
				titanPaymentRequest.getRecieveTitanCode());
		
		if(null ==accountCheckResponse){
			resultMap.put(CommonConstant.RETURN_MSG, "收款方不存在");
			resultMap.put(CommonConstant.RESULT, CommonConstant.OPERATE_FAIL);
			return resultMap;
		}
		
		titanPaymentRequest.setUserrelateid(accountCheckResponse.getUserid());
		//TODO  支付请求发生时需要确定金额
		
		if("1".equals(titanPaymentRequest.getIsaccount())){//有使用余额就需要输入密码（去掉是否免密校验）
//			boolean isAllowNoPwdPay = titanPaymentService.isAllowNoPwdPay(titanPaymentRequest.getUserid(), titanPaymentRequest.getTradeAmount());
//	        if(!isAllowNoPwdPay){//不允许免密支付，需要输入密码
	        	boolean isTrue = titanPaymentService.checkPwd(titanPaymentRequest.getPayPassword(), titanPaymentRequest.getFcUserid(), titanPaymentRequest.getPartnerOrgCode());
	        	if(!isTrue){
	    			resultMap.put(CommonConstant.RETURN_MSG, "付款密码不正确");
	    			resultMap.put(CommonConstant.RESULT, CommonConstant.OPERATE_FAIL);
	    			return resultMap;
	            }
//	        }
		}
		
		if(StringUtil.isValidString(titanPaymentRequest.getUserrelateid())){//收款方不为空，则判断是否自己给自己付款
			if(titanPaymentRequest.getUserrelateid().equals(titanPaymentRequest.getUserid())){
    			resultMap.put(CommonConstant.RETURN_MSG, "收款账户不能和付款账户相同");
    			resultMap.put(CommonConstant.RESULT, CommonConstant.OPERATE_FAIL);
    			return resultMap;
			}
		}
		resultMap.put(CommonConstant.RESULT, CommonConstant.OPERATE_SUCCESS);
		return resultMap;
    }	
    

	@RequestMapping("payConfirmPage")
	public String payConfirmPage(RechargeResultConfirmRequest rechargeResultConfirmRequest,Model model) throws NamingException{
		
		return titanPaymentService.payConfirmPage(rechargeResultConfirmRequest,model);
	}
	
	@RequestMapping("confirmOrder")
	@ResponseBody
    public Map<String,String> confirmOrder(String orderNo){
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put(CommonConstant.RETURN_MSG, titanOrderService.confirmOrderStatus(orderNo));
    	return resultMap;
    }
	
	@RequestMapping("wxPicture")
	public void getWxPicture(String url,HttpServletResponse response){
		try{
			Wxutil.createRqCode(url, CommonConstant.RQ_WIDTH, CommonConstant.RQ_HEIGH, response.getOutputStream());
		}catch(Exception e){
			log.error("微信生成图片错误："+e.getMessage());
		}
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
	

	/**
	 * 申请运营贷
	 * 
	 * @Title: operationLoanPay
	 * @Description: TODO
	 * @param req
	 * @return
	 * @return: String
	 */
	@ResponseBody
	@RequestMapping("operationLoanPay")
	public String operationLoanPay(HttpServletRequest httpRequest,
			OperationLoanPayReq req) {

		// 校验参数是否合法
		if (req == null || !StringUtil.isValidString(req.getAmount())
				|| !StringUtil.isValidString(req.getBankName())
				|| !StringUtil.isValidString(req.getCardNum())
				|| !StringUtil.isValidString(req.getAccountName())) {

			log.error("check loan request param is null!");

			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}

		log.info("operation loan pay req = " + JsonConversionTool.toJson(req));

		TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setPayorderno(req.getPayOrderNo());
		TransOrderDTO transOrderDTO = titanOrderService
				.queryTransOrderDTO(transOrderRequest);

		if (transOrderDTO == null) {
			log.error("trans order is error !");
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}
		Map<String, String> bussinessInfoMap = JsonConversionTool.toObject(
				transOrderDTO.getBusinessinfo(), Map.class);

		String billOrderNo = bussinessInfoMap.get("billCode");

		if (!StringUtil.isValidString(billOrderNo)) {
			log.error("bill order is null !");
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}
		// 申请贷款
		LoanSpecificationBean loanSpecBean = new LoanSpecificationBean();
		loanSpecBean.setAmount(NumberUtil.covertToCents(req.getAmount()));
		loanSpecBean.setOrderNo(OrderGenerateService.genLoanApplyOrderNo());
		loanSpecBean.setAccount(req.getCardNum());
		loanSpecBean.setAccountName(req.getAccountName());
		loanSpecBean.setBank(req.getBankName());
		loanSpecBean.setAccessory("billing_details.xls");

		Map<String, String> contentMap = new HashMap<String, String>();
		contentMap.put("billOrderNo", billOrderNo);
		contentMap.put("transOrderNo", transOrderDTO.getUserorderid());
		loanSpecBean.setContent(JsonConversionTool.toJson(contentMap));

		ApplyLoanRequest request = new ApplyLoanRequest();
		request.setProductType(LoanProductEnum.OPERACTION);
		request.setLcanSpec(loanSpecBean);
		request.setOrgCode(transOrderDTO.getUserid());
		request.setOperator("0");
		
		if(StringUtil.isValidString(req.getFcUserId()))
		{
			TitanUserBindInfoDTO titanUserBindInfoDTO = new TitanUserBindInfoDTO();
			titanUserBindInfoDTO.setFcuserid(Long.parseLong(req.getFcUserId()));
			TitanUserBindInfoDTO dto = titanFinancialUserService
					.getUserBindInfoByFcuserid(titanUserBindInfoDTO);
			if (dto != null) {
				request.setOperator("" + dto.getTfsuserid());
			}
		}

		try {
			log.info("apply loan request = "
					+ JsonConversionTool.toJson(request));

			ApplyLoanResponse response = titanFinancialLoanService
					.applyLoan(request);

			if (response == null || !response.isResult()) {

				log.error("apply loan fail!"
						+ JsonConversionTool.toJson(response));

				return toMsgJson(TitanMsgCodeEnum.LOAN_REQUEST_FAIL);
			}

			return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS);

		} catch (Exception e) {
			log.error("apply loan fail!", e);
		}
		return toMsgJson(TitanMsgCodeEnum.LOAN_REQUEST_FAIL);
	}
	    
}
