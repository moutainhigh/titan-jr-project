
package com.fangcang.titanjr.pay.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.BusinessLog;
import com.fangcang.titanjr.common.enums.CashierItemTypeEnum;
import com.fangcang.titanjr.common.enums.LoanProductEnum;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderKindEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.PayerTypeEnum;
import com.fangcang.titanjr.common.enums.ReqstatusEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
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
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.request.TransferRequest;
import com.fangcang.titanjr.dto.request.UserBindInfoRequest;
import com.fangcang.titanjr.dto.response.AccountCheckResponse;
import com.fangcang.titanjr.dto.response.ApplyLoanResponse;
import com.fangcang.titanjr.dto.response.ConfirmOrdernQueryResponse;
import com.fangcang.titanjr.dto.response.LocalAddTransOrderResponse;
import com.fangcang.titanjr.dto.response.QrCodeResponse;
import com.fangcang.titanjr.dto.response.RechargeResponse;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;
import com.fangcang.titanjr.dto.response.TransferResponse;
import com.fangcang.titanjr.enums.PayTypeEnum;
import com.fangcang.titanjr.pay.constant.TitanConstantDefine;
import com.fangcang.titanjr.pay.req.CreateTitanRateRecordReq;
import com.fangcang.titanjr.pay.req.OperationLoanPayReq;
import com.fangcang.titanjr.pay.req.TitanRateComputeReq;
import com.fangcang.titanjr.pay.services.TitanPaymentService;
import com.fangcang.titanjr.pay.services.TitanRateService;
import com.fangcang.titanjr.pay.services.TitanTradeService;
import com.fangcang.titanjr.service.BusinessLogService;
import com.fangcang.titanjr.service.RedisService;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialLoanService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
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
		if(!StringUtil.isValidString(orderNo)){
			log.error("RS callback is fail");
			return;
		}

		response.getWriter().print("returnCode=000000&returnMsg=成功");
		response.flushBuffer();
		
		log.info("收到融数通知(notify)的支付结果rechargeResultConfirmRequest："+Tools.gsonToString(rechargeResultConfirmRequest));
		
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
        	businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.CallbackNotify, OrderKindEnum.TransOrderId, transOrderDTO.getTransid()+""));
        	PayerTypeEnum payerType = PayerTypeEnum.getPayerTypeEnumByKey(transOrderDTO.getPayerType());
        	
        	//validate transfer order 
        	boolean validateResult = titanPaymentService.validateIsConfirmed(transOrderDTO.getTransid());
			if(!validateResult){
				log.error("该订单已转帐成功,orderNo:"+orderNo);
				unlockOutTradeNoList(orderNo);
				return ;
			}
        	
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
    			
    			titanPaymentService.updateOrderStatus(transOrderDTO.getTransid(),orderStatusEnum);
    			return ;
    		}
        	
        	if(PayerTypeEnum.RECHARGE.key.equals(payerType.getKey())){//如果是充值则置订单为成功
        		orderStatusEnum= OrderStatusEnum.ORDER_SUCCESS;
        	}else{//不是充值操作，就需要转帐
        		TransferRequest transferRequest = titanPaymentService.convertToTransferRequest(transOrderDTO);
        		//校验实际付款金额和订单应付金额（包含手续费）：付款方是中间账户
        		boolean payFlag = NumberUtil.subtract(rechargeResultConfirmRequest.getPayAmount(), transOrderDTO.getAmount()).floatValue()==0.0D;
        		if(!payFlag){
        			log.error("订单支付异常：支付金额和收到的金额不相等，订单号："+transOrderDTO.getOrderid());
        			titanPaymentService.updateOrderStatus(transOrderDTO.getTransid(),OrderStatusEnum.ORDER_FAIL);
        			titanFinancialUtilService.saveOrderException(transOrderDTO.getOrderid(),OrderKindEnum.OrderId, OrderExceptionEnum.Notify_Order_Amount_Execption,null);
        			return ;
        		}
        		
        		log.info("begin to transfer:"+JsonConversionTool.toJson(transferRequest));
	        	TransferResponse transferResponse = titanFinancialTradeService.transferAccounts(transferRequest);
	        	log.info("the result of transfer :"+JsonConversionTool.toJson(transferResponse)+",orderid:"+transferRequest.getOrderid());
	        	
	        	if(!transferResponse.isResult()){//transfer fail
	        		orderStatusEnum = OrderStatusEnum.ORDER_FAIL;
	        	}else{//transfer success
	        		businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.TransferSucc, OrderKindEnum.TransOrderId, transOrderDTO.getTransid()+""));
	        		orderStatusEnum = OrderStatusEnum.TRANSFER_SUCCESS;
	        		financialTradeService.notifyPayResult(transOrderDTO.getUserorderid());
	        		
	        		if(CommonConstant.FREEZE_ORDER.equals(transOrderDTO.getIsEscrowedPayment())){//需要进行冻结操作
	        			log.info("begin to freeze,transferRequest:"+Tools.gsonToString(transferRequest));
	        			boolean freezeSuccess = titanPaymentService.freezeAccountBalance(transferRequest,orderNo);
	        			log.info("the result of freeze,orderNo:"+orderNo+",冻结状态："+freezeSuccess);
    					//update the status of the order
    					if(freezeSuccess){//freeze order is success
    						orderStatusEnum = OrderStatusEnum.FREEZE_SUCCESS;
    						businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.FreezeSucc, OrderKindEnum.TransOrderId, transOrderDTO.getTransid()+""));
    					}else{
    						log.error("update the status of the order was failed,the msg is "+JsonConversionTool.toJson(transferRequest));
    						orderStatusEnum = OrderStatusEnum.FREEZE_FAIL;
    						titanFinancialUtilService.saveOrderException(orderNo,OrderKindEnum.OrderId, OrderExceptionEnum.Notify_Freeze_Insert_Fail, JSONSerializer.toJSON(transferRequest).toString());
    					}
	        		}
	        		
	        		//save the trade account
					if(payerType.isAddAccountHistory()){
						titanPaymentService.addAccountHistory(transOrderDTO);
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

			response = titanFinancialTradeService.ordernQuery(request);

			if (response == null || !response.isResult()
					|| null == response.getTransOrderInfos()
					|| response.getTransOrderInfos().size() != 1) {
				log.error("confirem ordern query is null,param:"+Tools.gsonToString(request)+",response:"+Tools.gsonToString(response));
				try {//线程等待
					if(i<2){
						Thread.sleep(500 * (2<<i));
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
		log.info("账户余额请求参数:"+JsonConversionTool.toJson(titanPaymentRequest));
		if(null == titanPaymentRequest || !StringUtil.isValidString(titanPaymentRequest.getTradeAmount())
				||!StringUtil.isValidString(titanPaymentRequest.getPayOrderNo())){
			log.error("参数不合法");
			return toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}
		
	    Map<String,String> validResult = this.validPaymentData(titanPaymentRequest);
        if(!CommonConstant.OPERATE_SUCCESS.equals(validResult.get(CommonConstant.RESULT))){//合规性验证
        	log.error("支付验证失败"+JSONSerializer.toJSON(validResult));
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("result", validResult.get(CommonConstant.RESULT));
    		map.put("resultMsg", validResult.get(CommonConstant.RETURN_MSG));
        	return JSONSerializer.toJSON(map).toString();
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
        TransferRequest transferRequest = this.convertToTransferRequest(titanPaymentRequest);
       
        //存在安全隐患，如果余额支付两次会不会存在重复支付
        lockOutTradeNoList(titanPaymentRequest.getPayOrderNo());//锁定支付单
        TransferResponse transferResponse = titanFinancialTradeService.transferAccounts(transferRequest);
        unlockOutTradeNoList(titanPaymentRequest.getPayOrderNo());//解锁支付单
		
        TransOrderRequest transOrderRequest = new TransOrderRequest();
		transOrderRequest.setOrderid(localOrderResp.getOrderNo());
		TransOrderDTO transOrder= titanOrderService.queryTransOrderDTO(transOrderRequest);
		OrderStatusEnum orderStatusEnum = OrderStatusEnum.ORDER_IN_PROCESS;
        
		if(!transferResponse.isResult()){
			log.error("transfer is failed");
			orderStatusEnum = OrderStatusEnum.ORDER_FAIL;
			boolean updateStatus = titanPaymentService.updateOrderStatus(transOrder.getTransid(),orderStatusEnum);
			if(!updateStatus){
				log.error("转账后更新订单失败："+transOrder.getTransid());
				titanFinancialUtilService.saveOrderException(titanPaymentRequest.getPayOrderNo(),OrderKindEnum.PayOrderNo, OrderExceptionEnum.Balance_Pay_Update_TransOrder_Fail, JSONSerializer.toJSON(transOrder).toString());
			}
			return toMsgJson(TitanMsgCodeEnum.TRANSFER_FAIL);
		}
		
		financialTradeService.notifyPayResult(localOrderResp.getUserOrderId());
		orderStatusEnum = OrderStatusEnum.ORDER_SUCCESS;
		if(CommonConstant.FREEZE_ORDER.equals(transOrder.getIsEscrowedPayment())){
			boolean freezeSuccess = titanPaymentService.freezeAccountBalance(transferRequest,localOrderResp.getOrderNo());
			//修改订单状态
			if(freezeSuccess){//冻结成功改变订单状态
				orderStatusEnum = OrderStatusEnum.FREEZE_SUCCESS;
			}else{
				log.error("freeze the order was failed");
				orderStatusEnum =OrderStatusEnum.FREEZE_FAIL;
				titanFinancialUtilService.saveOrderException(localOrderResp.getOrderNo(),OrderKindEnum.OrderId, OrderExceptionEnum.Balance_Pay_Freeze_Fail, JSONSerializer.toJSON(transferRequest).toString());
			}
		}
		
		//中间账户不计历史
		if(!transOrder.getUserid().equals(titanFinancialAccountService.getDefaultPayerConfig().getUserId())){
			titanPaymentService.addAccountHistory(transOrder);
		}
		
		boolean updateStatus = titanPaymentService.updateOrderStatus(transOrder.getTransid(),orderStatusEnum);
		if(!updateStatus){
			log.error("冻结余额后更新订单失败");
			titanFinancialUtilService.saveOrderException(transOrder.getPayorderno(),OrderKindEnum.PayOrderNo, OrderExceptionEnum.Balance_Pay_Update_TransOrder_Fail, JSONSerializer.toJSON(transOrder).toString());
		}
		return toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS,transOrder.getOrderid());
	}
	

	private TransferRequest convertToTransferRequest(TitanPaymentRequest titanPaymentRequest){
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setCreator(titanPaymentRequest.getCreator());
    	transferRequest.setUserid(titanPaymentRequest.getUserid());										//转出的用户
    	transferRequest.setRequestno(OrderGenerateService.genResquestNo());									//业务订单号
    	transferRequest.setRequesttime(DateUtil.sdf4.format(new Date()));				//请求时间
    	transferRequest.setAmount(NumberUtil.covertToCents(titanPaymentRequest.getTradeAmount()));										//金额 必须是分
    	transferRequest.setUserfee("0");									
    	transferRequest.setUserrelateid(titanPaymentRequest.getUserrelateid());	                   //接收方用户Id
    	transferRequest.setOrderid(titanPaymentRequest.getOrderid());
		return transferRequest;
	}
	
	@RequestMapping("qrCodePayment")
	public String qrCodePayment(HttpServletRequest request,TitanPaymentRequest titanPaymentRequest,Model model) throws Exception{
		this.packageRechargeData(request, titanPaymentRequest, model);
		return CommonConstant.PAY_WX;
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
		log.info("网银支付请求参数titanPaymentRequest:"+JsonConversionTool.toJson(titanPaymentRequest));
		model.addAttribute(CommonConstant.RESULT, CommonConstant.OPERATE_FAIL);
		businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.BeginPackageRechargeData, OrderKindEnum.PayOrderNo, titanPaymentRequest.getPayOrderNo()));
		//检查必填参数
		if(null == titanPaymentRequest || !StringUtil.isValidString(titanPaymentRequest.getTradeAmount())){
			log.error("订单金额不能为空，参数titanPaymentRequest:"+JsonConversionTool.toJson(titanPaymentRequest));
			model.addAttribute(CommonConstant.RETURN_MSG, "必填参数不能为空");
			return CommonConstant.GATE_WAY_PAYGE;
		}
		//计算支付金额(不包含手续费)，余额，
		String  payAmount = "0";//网银需要支付的金额
		String	transferAmount= "0";//余额要支付的金额
		if(PaySourceEnum.RECHARDE.getDeskCode().equals(titanPaymentRequest.getPaySource())){//充值单
			payAmount = titanPaymentRequest.getTradeAmount();
		}else{//付款
			if("1".equals(titanPaymentRequest.getIsaccount())){//勾选了余额支付
				TransOrderRequest transOrderRequest = new TransOrderRequest();
				transOrderRequest.setPayorderno(titanPaymentRequest.getPayOrderNo());
				TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
				if (titanFinancialAccountService.getDefaultPayerConfig().getUserId().equals(titanPaymentRequest.getUserid())) {//中间账户不支持余额支付
					model.addAttribute(CommonConstant.RETURN_MSG, "该业务暂时不允许用余额支付");
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
		if(!PaySourceEnum.RECHARDE.getDeskCode().equals(titanPaymentRequest.getPaySource())){
			//检查sign
			String paramSing = titanPaymentRequest.getSign();
			String md5Sign = md5Sign(titanPaymentRequest, TitanConstantDefine.PAY_APP_CASHIER_SIGN_MD5_KEY);
			if(!(StringUtil.isValidString(paramSing)&&paramSing.equals(md5Sign))){
				log.error("网银支付请求参数签名错误,，参数titanPaymentRequest:"+JsonConversionTool.toJson(titanPaymentRequest)+",签名sing:"+md5Sign);
				model.addAttribute(CommonConstant.RETURN_MSG, "参数签名错误");
				return CommonConstant.GATE_WAY_PAYGE;
			}
			//如果付款到中间账户的方式，就没有余额支付.后期跟进业务调整
			if(("1".equals(titanPaymentRequest.getIsaccount()))&&titanFinancialAccountService.getDefaultPayerConfig().getUserId().equals(titanPaymentRequest.getUserid())){
				model.addAttribute(CommonConstant.RETURN_MSG, "该业务暂时不允许用余额支付");
				return CommonConstant.GATE_WAY_PAYGE;
			}
		}
		
		
		if(!titanPaymentRequest.getPaySource().equals(PaySourceEnum.RECHARDE.getDeskCode()) )
		{
	        Map<String,String> validResult = this.validPaymentData(titanPaymentRequest);
	        if(!CommonConstant.OPERATE_SUCCESS.equals(validResult.get(CommonConstant.RESULT))){//合规性验证
	        	log.error("网银支付验证参数失败,，参数titanPaymentRequest:"+JsonConversionTool.toJson(titanPaymentRequest));
	        	model.addAttribute(CommonConstant.RETURN_MSG, validResult.get(CommonConstant.RETURN_MSG));
				return CommonConstant.GATE_WAY_PAYGE;
	        }
		}
		// 确认收银台支付类型是否存在
		CashierItemTypeEnum cashierItemTypeEnum = CashierItemTypeEnum
				.getCashierItemTypeEnumByKey(titanPaymentRequest
						.getLinePayType());

		if (cashierItemTypeEnum == null) {
			log.error("支付类型不存在");
			model.addAttribute(CommonConstant.RETURN_MSG,
					TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED.getKey());
			return CommonConstant.GATE_WAY_PAYGE;
		}
		
		// 开始计算并设置费率
		TitanRateComputeReq computeReq = new TitanRateComputeReq();
		computeReq.setAmount(titanPaymentRequest.getPayAmount());
		computeReq.setItemTypeEnum(cashierItemTypeEnum);
		computeReq.setUserId(titanPaymentRequest.getUserid());
		if(TitanConstantDefine.EXTERNAL_PAYMENT_ACCOUNT.equals(titanPaymentRequest.getUserid())){
			computeReq.setUserId(titanPaymentRequest.getUserrelateid());
		}
		
		//设置费率信息
		titanPaymentRequest = titanRateService.setRateAmount(computeReq,
				titanPaymentRequest);
		businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.CreateRsOrder, OrderKindEnum.PayOrderNo, titanPaymentRequest.getPayOrderNo()));
		
        TransOrderCreateResponse transOrderCreateResponse = titanFinancialTradeService.createRsOrder(titanPaymentRequest);
        if(!transOrderCreateResponse.isResult() || !StringUtil.isValidString(transOrderCreateResponse.getOrderNo()) ){
        	log.error("call createTitanTransOrder fail msg["+ JsonConversionTool.toJson(transOrderCreateResponse)+"]");
        	model.addAttribute("msg", transOrderCreateResponse.getReturnMessage());
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
    		return CommonConstant.GATE_WAY_PAYGE;
    	}
		
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
		req.setOrderNo(transOrderCreateResponse.getOrderNo());
		req.setCreator(computeReq.getUserId());
		titanRateService.addRateRecord(req);
		titanPaymentService.saveCommonPayMethod(titanPaymentRequest);
		//如果是扫码支付则调用httpClient接口进行
		if(PayTypeEnum.WECHAT_URL.getLinePayType().equals(titanPaymentRequest.getLinePayType())){
			businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.WechatpayStep, OrderKindEnum.PayOrderNo, titanPaymentRequest.getPayOrderNo()));
			return weChat(rechargeResponse, model);
		}
		businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.CyberBankStep, OrderKindEnum.PayOrderNo, titanPaymentRequest.getPayOrderNo()));
		
		return cyberBank(rechargeResponse,model);
    	
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
		return MD5.MD5Encode(stringBuilder.toString());
	}
	
	private String weChat(RechargeResponse rechargeResponse,Model model) throws Exception{
		RechargeDataDTO rechargeDataDTO = rechargeResponse.getRechargeDataDTO();
		if(PayTypeEnum.ALIPAY_URL.key.equals(rechargeDataDTO.getPayType())){
			rechargeDataDTO.setExpand2(CommonConstant.ALIPAY);
		}
		QrCodeResponse response = titanFinancialTradeService.getQrCodeUrl(rechargeDataDTO);
		if(!response.isResult()){
			log.error("第三方支付获取地址失败");
			titanFinancialUtilService.saveOrderException(rechargeDataDTO.getPayOrderNo(),OrderKindEnum.PayOrderNo, OrderExceptionEnum.Online_Pay_Get_Pay_Url_Fail, JSONSerializer.toJSON(rechargeDataDTO).toString());
			model.addAttribute(CommonConstant.RETURN_MSG, TitanMsgCodeEnum.QR_EXCEPTION.getKey());
			return CommonConstant.PAY_WX;
		}
		model.addAttribute(CommonConstant.RESULT, CommonConstant.RETURN_SUCCESS);
		model.addAttribute(CommonConstant.QRCODE,response.getQrCodeDTO());
		return CommonConstant.PAY_WX;
	}
	
	//网银支付
	private String cyberBank(RechargeResponse rechargeResponse,Model model){
		model.addAttribute(CommonConstant.RESULT, CommonConstant.RETURN_SUCCESS);
    	model.addAttribute("rechargeDataDTO", rechargeResponse.getRechargeDataDTO());
    	log.info("支付请求的参数如下:"+JsonConversionTool.toJson(rechargeResponse.getRechargeDataDTO()));
    	//保存常用的支付方式
		return CommonConstant.GATE_WAY_PAYGE;
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
		
		if("1".equals(titanPaymentRequest.getIsaccount())){//有转账金额,需要输入密码
			boolean isAllowNoPwdPay = titanPaymentService.isAllowNoPwdPay(titanPaymentRequest.getUserid(), titanPaymentRequest.getTradeAmount());
	        if(!isAllowNoPwdPay){//不允许免密支付，需要输入密码
	        	boolean isTrue = titanPaymentService.checkPwd(titanPaymentRequest.getPayPassword(), titanPaymentRequest.getFcUserid());
	            if(!isTrue){
	    			resultMap.put(CommonConstant.RETURN_MSG, "付款密码不正确");
	    			resultMap.put(CommonConstant.RESULT, CommonConstant.OPERATE_FAIL);
	    			return resultMap;
	            }
	        }
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
