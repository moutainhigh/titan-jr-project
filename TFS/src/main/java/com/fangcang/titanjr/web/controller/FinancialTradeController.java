package com.fangcang.titanjr.web.controller;

import com.alibaba.fastjson.JSON;
import com.fangcang.merchant.api.MerchantFacade;
import com.fangcang.merchant.query.dto.MerchantDetailQueryDTO;
import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.titanjr.common.enums.CashierDeskTypeEnum;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.ReqstatusEnum;
import com.fangcang.titanjr.common.enums.TransferReqEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.NumberUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.AccountHistoryDTO;
import com.fangcang.titanjr.dto.bean.CommonPayMethodDTO;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.GDPOrderDTO;
import com.fangcang.titanjr.dto.bean.OperTypeEnum;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.bean.OrderOperateInfoDTO;
import com.fangcang.titanjr.dto.bean.OrderTypeEnum;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.OrgDTO;
import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.bean.PayTypeEnum;
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanUserBindInfoDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.*;
import com.fangcang.titanjr.dto.response.*;
import com.fangcang.titanjr.service.*;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.titanjr.web.pojo.DefaultPayerConfig;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.util.StringUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简化controller命名和前缀
 *
 */
@Controller
@RequestMapping("/trade")
public class FinancialTradeController extends BaseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory.getLog(FinancialTradeController.class);
	
	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;

	@Resource
	private TitanCashierDeskService titanCashierDeskService;

	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;
	
	@Resource
	private TitanOrderService titanOrderService;
	
	@Resource
	private DefaultPayerConfig defaultPayerConfig;

	@Resource
	private TitanFinancialOrganService titanFinancialOrganService;
	
	@Resource
	private TitanFinancialUserService titanFinancialUserService;
	
    private MerchantFacade merchantFacade;
    
	@Resource
	private HessianProxyBeanFactory hessianProxyBeanFactory;
	
//	private static List<String> orderNoList = new ArrayList<String>();
	
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
		try{
    		if(rechargeResultConfirmRequest !=null){
    			response.getWriter().print("returnCode=000000&returnMag=成功");
    			log.info("融数后台回调成功参数:"+toJson(rechargeResultConfirmRequest));
    			String signMsg = rechargeResultConfirmRequest.getSignMsg();
           	    String sign = RechargeResultConfirmRequest.getSignStr(rechargeResultConfirmRequest);
            	if(!MD5.MD5Encode(sign, "UTF-8").equals(signMsg)){
           		   return;
            	}
            	if(WebConstant.PAY_SUCCESS.equals(rechargeResultConfirmRequest.getPayStatus())){//支付成功
            		
            		lockOutTradeNoList(orderNo);
            			 //查询订单
            			TransOrderRequest transOrderRequest = new TransOrderRequest();
                		transOrderRequest.setOrderid(orderNo);
                		TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
                        //判断该回调是否已经执行过
                		if(transOrderDTO !=null){
                			boolean validateResult = validateIsConfirmed(transOrderDTO.getTransid());
                			if(!validateResult){
                				return ;
                			}
                			//查询订单是否成功
                			boolean flag = validateOrderIsSuccess(transOrderDTO);
                			log.info("订单是否被执行:"+flag);
                	        if(flag){    
                	        	int row = titanOrderService.updateTitanOrderPayreq(orderNo,ReqstatusEnum.Status_2.getStatus()+"");
                	        	if(row<1){
                	        		OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(orderNo, "充值成功 修改充值单失败", OrderExceptionEnum.OrderPay_Update, JSON.toJSONString(orderNo));
                	        		titanOrderService.saveOrderException(orderExceptionDTO);
                	        	}
                	        	OrderStatusEnum orderStatusEnum = OrderStatusEnum.RECHARFE_SUCCESS;
                	        	//判断该交易是支付还是充值
                    			if(StringUtil.isValidString(transOrderDTO.getPayermerchant())){
                    				
                    				//回调成功，转账，查询出需要的转账账单
                    	        	TransferRequest transferRequest = convertToTransferRequest(transOrderDTO);
                    	        	log.info("回调开始转账:"+toJson(transferRequest));
                    	        	TransferResponse transferResponse = titanFinancialTradeService.transferAccounts(transferRequest);
                    	        	log.info("回调转账结果:"+toJson(transferResponse));
                    	        	if(transferResponse.isResult()){//转账成功之后需要调用
                    	        		orderStatusEnum = OrderStatusEnum.TRANSFER_SUCCESS;
                    	        		if(StringUtil.isValidString(transOrderDTO.getMerchantcode())){//GDP的回调
                    	        			log.info("回调财务:"+toJson(transOrderDTO));
                    	        			boolean confirmFlag =titanFinancialTradeService.confirmFinance(transOrderDTO);
                    	        			log.info("回调财务结果:"+confirmFlag);
                        	        		if(!confirmFlag){
                        	        			OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(orderNo, "转账成功 回调财务失败", OrderExceptionEnum.Finance_Confirm, JSON.toJSONString(transOrderDTO));
                            	        		titanOrderService.saveOrderException(orderExceptionDTO);
                        	        		}
                    	        		}
                    	        		
                    	        		//冻结操作,如果冻结失败该进行什么操作,
                    	        		if(WebConstant.FREEZE_ORDER.equals(transOrderDTO.getIsEscrowedPayment())){//需要进行冻结操作
                    	        			log.info("开始冻结:"+transferRequest);
                    	        			boolean freezeSuccess = freezeAccountBalance(transferRequest,orderNo);
                    	        			log.info("冻结结果:"+freezeSuccess);
                        					//修改订单状态
                        					if(freezeSuccess){//冻结成功改变订单状态
                        						orderStatusEnum = OrderStatusEnum.FREEZE_SUCCESS;
                        					}else{
                        						//TODO 添加部分成功的操作
                        						orderStatusEnum = OrderStatusEnum.FREEZE_FAIL;
                        						OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(orderNo, "冻结失败", OrderExceptionEnum.Freeze_Insert, JSON.toJSONString(transferRequest));
                            	        		titanOrderService.saveOrderException(orderExceptionDTO);
                        					}
                    	        		}
                    	        		
                    					//记录收款账户
                    					if(StringUtil.isValidString(transOrderDTO.getMerchantcode())){//GDP的账户历史是不需要记录的
                    						if(StringUtil.isValidString(transOrderDTO.getMerchantcode())){
                        						titanFinancialAccountService.addAccountHistory(transferRequest);
                        					}
                    					}
                    					
                    	        	}else{//转账失败记录此次交易失败
                    	        		orderStatusEnum = OrderStatusEnum.TRANSFER_FAIL;
                    	        	}
                    			}else{//
                    				orderStatusEnum= OrderStatusEnum.ORDER_SUCCESS;
                    			}
                    			//修改订单状态,如果转账成功之后未被冻结，则设置该单为成功
                    			if(orderStatusEnum.getStatus().equals(OrderStatusEnum.TRANSFER_SUCCESS.getStatus())){
                    				orderStatusEnum = OrderStatusEnum.ORDER_SUCCESS;
                    			}
                    			
                    			log.info("修改财务单:"+toJson(orderStatusEnum));
                				boolean updateStatus = this.updateOrderStatus(transOrderDTO.getTransid(),orderStatusEnum);
                				//修改订单状态
                				if(!updateStatus){
        							OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(orderNo, "冻结成功 修改订单状态失败", OrderExceptionEnum.TransOrder_update, JSON.toJSONString(transOrderDTO.getTransid()));
                	        		titanOrderService.saveOrderException(orderExceptionDTO);
        						}
                	        }else{//充值失败
                	        	titanOrderService.updateTitanOrderPayreq(orderNo,ReqstatusEnum.Status_3.getStatus()+"");
                	        	this.updateOrderStatus(transOrderDTO.getTransid(),OrderStatusEnum.RECHARFE_FAIL);
                	        }
					}
                	unlockOutTradeNoList(orderNo);
            	}
    		}
    	}catch(Exception e){
    		log.error(e.getMessage());
    	}finally{
    		unlockOutTradeNoList(orderNo);
    	}
    }
	

	@RequestMapping("payConfirmPage")
	public String payConfirmPage(RechargeResultConfirmRequest rechargeResultConfirmRequest,Model model){
		if(StringUtil.isValidString(rechargeResultConfirmRequest.getOrderNo())){
			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setOrderid(rechargeResultConfirmRequest.getOrderNo());
			TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
			if(transOrderDTO !=null){
				model.addAttribute("transOrderDTO", transOrderDTO);
				FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
				organQueryRequest.setOrgCode(transOrderDTO.getPayeemerchant());
				FinancialOrganResponse financialOrganResponse = titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
		        if(financialOrganResponse.isResult()){
		        	model.addAttribute("financialOrganDTO", financialOrganResponse.getFinancialOrganDTO());
		        }
		        
		        model.addAttribute("payType", "网银支付");
		        if(!StringUtil.isValidString(rechargeResultConfirmRequest.getPayStatus())){//判断是本地回调
		        	
		        	boolean paySuccess = OrderStatusEnum.isPaySuccess(transOrderDTO.getStatusid());
		        	if(transOrderDTO.getTradeamount() !=null){
	        			rechargeResultConfirmRequest.setPayAmount(transOrderDTO.getTradeamount().toString());
	        		}
		        	rechargeResultConfirmRequest.setOrderPayTime(DateUtil.sdf5.format(transOrderDTO.getCreatetime()));
		        	rechargeResultConfirmRequest.setPayMsg("付款失败");
		        	if(paySuccess){
		        		rechargeResultConfirmRequest.setPayStatus("3");
		        		rechargeResultConfirmRequest.setPayMsg("付款成功");
		        	}
		        	model.addAttribute("payType", "余额支付");
				}
		        
			}
		}
	    model.addAttribute("rechargeResultConfirmRequest", rechargeResultConfirmRequest);
		return "checkstand-pay/payResult";
	}
	
	/**
	 * 只有转账操作的controller
	 * @param request
	 * @param paymentRequest
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/showTitanPayPage")
	
	public Map<String,String> showTitanPayPage(HttpServletRequest request,PaymentRequest paymentRequest) throws Exception{
		Map<String,String> resultMap = new HashMap<String,String>();
		
		if(paymentRequest !=null && StringUtil.isValidString(paymentRequest.getMerchantcode()) 
                &&StringUtil.isValidString(paymentRequest.getPayOrderNo())){
			
			FinancialOrderResponse financialOrderResponse = getFinancialOrderResponse(paymentRequest);
			//验证
			Map<String,String> result = validatePaymentDate(paymentRequest,financialOrderResponse);
			if(!WebConstant.FAIL.equals(result.get(WebConstant.RESULT))){
				//同一张单是否存在已经充值过
				if (WebConstant.TRANSFER_PAYAMOUNT.equals(paymentRequest.getPayAmount())) {//只有转账的交易，银行卡支付金额为空
					//手动下单，转账
					log.info("本地下单入参:"+toJson(paymentRequest)+"财务入参:"+toJson(financialOrderResponse));
					LocalAddTransOrderResponse localOrderResp = titanFinancialTradeService.addLocalTransOrder(paymentRequest, financialOrderResponse);
					resultMap.put("orderNo", localOrderResp.getOrderNo());
					log.info("本地下单结果:"+toJson(localOrderResp));
					if (localOrderResp.isResult()) {//本地落单成功，转账
						TransferRequest transferRequest = convertToTransferRequest(paymentRequest);
						transferRequest.setOrderid(localOrderResp.getOrderNo());
						TransferResponse transferResponse = titanFinancialTradeService.transferAccounts(transferRequest);
						
						TransOrderRequest transOrderRequest = new TransOrderRequest();
    	        		transOrderRequest.setOrderid(localOrderResp.getOrderNo());
                		TransOrderDTO transOrder= titanOrderService.queryTransOrderDTO(transOrderRequest);
                		OrderStatusEnum orderStatusEnum = OrderStatusEnum.ORDER_IN_PROCESS;
						if (transferResponse.isResult()) {//转账成功，流程结束
							//将转账参数和转账结果
							TransOrderDTO transOrderDTO = new TransOrderDTO();
							transOrderDTO.setMerchantcode(paymentRequest.getMerchantcode());
							transOrderDTO.setPayorderno(paymentRequest.getPayOrderNo());
							boolean confirmFlag =titanFinancialTradeService.confirmFinance(transOrderDTO);
        	        		if(!confirmFlag){
        	        			OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(localOrderResp.getOrderNo(), "余额转账成功 回调财务失败", OrderExceptionEnum.Finance_Confirm, JSON.toJSONString(transOrderDTO));
            	        		titanOrderService.saveOrderException(orderExceptionDTO);
        	        		}
							//冻结操作,如果冻结失败该进行什么操作
        	        		//判断其是否需要冻结
        	        		orderStatusEnum = OrderStatusEnum.ORDER_SUCCESS;
                    		if(WebConstant.FREEZE_ORDER.equals(transOrder.getIsEscrowedPayment())){
                    			boolean freezeSuccess = freezeAccountBalance(transferRequest,localOrderResp.getOrderNo());
    							//修改订单状态
                    			if(freezeSuccess){//冻结成功改变订单状态
            						orderStatusEnum = OrderStatusEnum.FREEZE_SUCCESS;
            					}else{
            						orderStatusEnum =OrderStatusEnum.FREEZE_FAIL;
            						//TODO 添加部分成功的操作
            						OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(localOrderResp.getOrderNo(), "冻结失败", OrderExceptionEnum.Freeze_Insert, JSON.toJSONString(transferRequest));
                	        		titanOrderService.saveOrderException(orderExceptionDTO);
            					}
                    		}
							//记录收款账户
							titanFinancialAccountService.addAccountHistory(transferRequest);
							resultMap.put(WebConstant.RESULT, WebConstant.SUCCESS);
				    		resultMap.put(WebConstant.MSG, "支付成功");
							
						}else{
							orderStatusEnum = OrderStatusEnum.TRANSFER_FAIL;
							resultMap.put(WebConstant.RESULT, WebConstant.FAIL);
				    		resultMap.put(WebConstant.MSG, "支付失败");
						}
						boolean updateStatus = this.updateOrderStatus(transOrder.getTransid(),orderStatusEnum);
						if(!updateStatus){
							OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(transOrder.getOrderid(), "冻结成功 修改订单状态失败", OrderExceptionEnum.TransOrder_update, JSON.toJSONString(transOrder.getTransid()));
							titanOrderService.saveOrderException(orderExceptionDTO);
						}
						return resultMap;
					}else{
						resultMap.put(WebConstant.RESULT, WebConstant.FAIL);
			    		resultMap.put(WebConstant.MSG, localOrderResp.getReturnMessage());
			    		return resultMap;
			    		}
				}
			}else{
				return result;
			}
	    }
		putSysError(resultMap);
		return resultMap;
	}
	
//	//修改订单状态
//	boolean updateStatus = this.updateOrderStatus(transOrderDTO.getTransid(),orderStatusEnum);
//	//修改订单状态
//	if(!updateStatus){
//		OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(orderNo, "冻结成功 修改订单状态失败", OrderExceptionEnum.TransOrder_update, JSON.toJSONString(transOrderDTO.getTransid()));
//		titanOrderService.saveOrderException(orderExceptionDTO);
//	}
	
	/**
	 * 需要充值的接口
	 * @param request
	 * @param paymentRequest
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("genRechargeData")
	public String genRechargeData(HttpServletRequest request,PaymentRequest paymentRequest,Model model) throws Exception{
		if(paymentRequest !=null){
			if(CashierDeskTypeEnum.RECHARGE.deskCode.equals(paymentRequest.getPaySource())){
				paymentRequest.setUserid(this.getUserId());
			}
			model.addAttribute(WebConstant.RESULT, WebConstant.FAIL);
			
			Map<String,String> result =null;
			if(CashierDeskTypeEnum.B2B_DESK.deskCode.equals(paymentRequest.getPaySource())){
				paymentRequest.setProductId(defaultPayerConfig.getProductId());
				paymentRequest.setUserid(defaultPayerConfig.getUserId());
				result = validateB2BData(paymentRequest);
			}else{
				FinancialOrderResponse financialOrderResponse =null;
				if(CashierDeskTypeEnum.SUPPLY_DESK.deskCode.equals(paymentRequest.getPaySource())){
					financialOrderResponse = getFinancialOrderResponse(paymentRequest);
				}
				result = validatePaymentDate(paymentRequest,financialOrderResponse);
			}
			
			if(!WebConstant.FAIL.equals(result.get(WebConstant.RESULT))){
				TransOrderCreateResponse transOrderCreateResponse = titanFinancialTradeService.createTitanTransOrder(paymentRequest);
				if(!transOrderCreateResponse.isResult() || !StringUtil.isValidString(transOrderCreateResponse.getOrderNo()) ){
			    	//下单失败
					model.addAttribute(WebConstant.MSG, transOrderCreateResponse.getReturnMessage());
			    }else{
			    	//设置支付方式
			    	if(StringUtil.isValidString(paymentRequest.getLinePayType())){
			    		paymentRequest.setPayType(PayTypeEnum.getPayTypeEnum(paymentRequest.getLinePayType()));
			    	}
			    	paymentRequest.setOrderid(transOrderCreateResponse.getOrderNo());
				    //获取支付页面
					RechargePageRequest rechargePageRequest = convertToRechargePageRequest(paymentRequest);
			    	RechargeResponse rechargeResponse = titanFinancialTradeService.generateRechargePage(rechargePageRequest);
				    if(rechargeResponse.getRechargeDataDTO()!=null){
				    	model.addAttribute(WebConstant.RESULT, WebConstant.SUCCESS);
				    	model.addAttribute("rechargeDataDTO", rechargeResponse.getRechargeDataDTO());
				    	//保存常用的支付方式
				    	saveCommonPayMethod(paymentRequest);
				    }
			    }
			}else{
				model.addAttribute(WebConstant.RESULT, WebConstant.FAIL);
				model.addAttribute(WebConstant.MSG, result.get(WebConstant.MSG));
			}
		}
		return "checkstand-pay/genRechargePayment";
	}
	
	
	@ResponseBody
	@RequestMapping(value="confirmedTrade")
	public Map<String,String> confirmedTrade(String payOrderNo,String paySource){
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put(WebConstant.RESULT,  WebConstant.SUCCESS);
		if(StringUtil.isValidString(payOrderNo)){//支付单号不为空则查询订单
			TransOrderRequest transOrderRequest = new TransOrderRequest();
			transOrderRequest.setPayorderno(payOrderNo);
			TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
			if(transOrderDTO ==null){
				resultMap.put(WebConstant.MSG, "支付失败");
				return resultMap;
			}
			
			//将冻结失败，冻结成功，和订单成功统一认为支付成功
			if(OrderStatusEnum.ORDER_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())
					|| OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())
					|| OrderStatusEnum.FREEZE_FAIL.getStatus().equals(transOrderDTO.getStatusid())){
				resultMap.put(WebConstant.MSG, "支付成功");
				return resultMap;
			}
			
			//将充值失败，转账失败，和订单失败，统一设置为失败
			if(OrderStatusEnum.ORDER_FAIL.getStatus().equals(transOrderDTO.getStatusid())
					||OrderStatusEnum.RECHARFE_FAIL.getStatus().equals(transOrderDTO.getStatusid())
					||OrderStatusEnum.TRANSFER_FAIL.getStatus().equals(transOrderDTO.getStatusid())){
				resultMap.put(WebConstant.MSG, "支付失败");
				return resultMap;
			}
			
			//处理中是指 充值成功，转账成功视为处理中
			if(OrderStatusEnum.RECHARFE_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())
					||OrderStatusEnum.TRANSFER_SUCCESS.getStatus().equals(transOrderDTO.getStatusid())
					||OrderStatusEnum.ORDER_IN_PROCESS.getStatus().equals(transOrderDTO.getStatusid())
					||OrderStatusEnum.RECHARFE_IN_PROCESS.getStatus().equals(transOrderDTO.getStatusid())){
				resultMap.put(WebConstant.MSG, "支付处理中");
				return resultMap;
			}
			
//            if(OrderStatusEnum..getStatus().equals(transOrderDTO.getStatusid())){//订单状态为处理中，查看充值单
//            	TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
//            	titanOrderPayDTO.setTransorderid(transOrderDTO.getTransid());
//                titanOrderPayDTO = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
//                if(titanOrderPayDTO !=null){//充值单不为空则查看其状态
//            		if(ReqstatusEnum.Status_3.getStatus()==titanOrderPayDTO.getReqstatus().intValue()){
//            			resultMap.put(CommonConstant.MSG, "支付失败");
//        				return resultMap;
//            		}
//                	
//                    if(CashierDeskTypeEnum.RECHARGE.deskCode.equals(paySource)){//充值请求如果充值成功就返回成功
//                    	if(ReqstatusEnum.Status_2.getStatus()==titanOrderPayDTO.getReqstatus().intValue()){
//                			resultMap.put(CommonConstant.MSG, "充值成功");
//            				return resultMap;
//                		}else{
//                			//查询融数账户信息，查询充值信息和转账信息,如果是充值只查询充值信息
//                			boolean isRechargeSuccess = validateOrderIsSuccess(transOrderDTO);
//                			if(isRechargeSuccess){
//                				resultMap.put(CommonConstant.MSG, "充值成功");
//                				return resultMap;
//                			}
//                		}
//                	}
//                	
//                    if(CashierDeskTypeEnum.SUPPLY_DESK.deskCode.equals(paySource)){//支付请求
//		            	TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
//		            	titanTransferDTO.setTransorderid(transOrderDTO.getTransid());
//		            	titanTransferDTO = titanOrderService.getTitanTransferDTO(titanTransferDTO);
//		            	
//		            	if(titanTransferDTO !=null){//转账单不为空则查看其状态
//		            		if(titanTransferDTO.getStatus() !=null){
//		            			if(TransferReqEnum.Status_3.getStatus()==titanTransferDTO.getStatus().intValue()){
//		                			resultMap.put(CommonConstant.MSG, "支付失败");
//		                			return resultMap;
//		                		}
//		            			if(TransferReqEnum.Status_2.getStatus()==titanTransferDTO.getStatus().intValue()){
//		            				resultMap.put(CommonConstant.MSG, "支付成功");
//		            				return resultMap;
//		            			}
//		            			if(TransferReqEnum.Status_1.getStatus()==titanTransferDTO.getStatus().intValue()){//处理中则在融数去处理
//		            				AccountTransferFlowRequest accountTransferFlowRequest = new AccountTransferFlowRequest();
//		            				accountTransferFlowRequest.setProductId(titanTransferDTO.getProductid());
//		            				accountTransferFlowRequest.setUserId(titanTransferDTO.getUserid());
//		            				titanTransferDTO.setRequestno(titanTransferDTO.getRequestno());
//		            				boolean isTransferSuccess = titanFinancialTradeService.confirmTransAccountSuccess(accountTransferFlowRequest);
//		            			    if(isTransferSuccess){
//		            			    	resultMap.put(CommonConstant.MSG, "支付成功");
//			            				return resultMap;
//		            			    }
//		            			}
//		            		}
//		            	}
//                    }
//                }
//				resultMap.put(CommonConstant.MSG, "支付处理中");
//				return resultMap;
//			}
			
			
		}
		resultMap.put(WebConstant.MSG, "系统错误");
		return resultMap;
	}
	
	
	@ResponseBody
	@RequestMapping("genLoacalPayOrderNo")
	public Map<String,String> getRechargePayOrderNo(){
		Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put(WebConstant.RESULT, OrderGenerateService.genLoacalPayOrderNo());
	    return resultMap;
	}
	
	private TransferRequest convertToTransferRequest(TransOrderDTO transOrderDTO){
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setCreator("system");
    	transferRequest.setUserid(transOrderDTO.getUserid());										//转出的用户
    	transferRequest.setRequestno(OrderGenerateService.genResquestNo());									//业务订单号
    	transferRequest.setRequesttime(DateUtil.sdf4.format(new Date()));	//请求时间
    	if(transOrderDTO.getTradeamount()!=null ){
    		transferRequest.setAmount(transOrderDTO.getTradeamount().toString());
    	}
    	transferRequest.setUserfee("0");			
    	transferRequest.setOrderid(transOrderDTO.getOrderid());
    	transferRequest.setUserrelateid(transOrderDTO.getUserrelateid());	
    	transferRequest.setProductId(transOrderDTO.getProductid());
    	return transferRequest;
	}
	
	private Map<String,String> validateB2BData(PaymentRequest paymentRequest){
		Map<String,String>  resultMap = new HashMap<String, String>();
		resultMap.put(WebConstant.RESULT, WebConstant.FAIL);
		boolean isExit = accountIsExist(paymentRequest);
		if(!isExit){
			resultMap.put(WebConstant.MSG, "收款账户不存在");
			return resultMap;
		}
		//验证其金额
		if(!StringUtil.isValidString(paymentRequest.getPayOrderNo())){
			resultMap.put(WebConstant.MSG, "系统错误");
			return resultMap;
		}
		//验证支付金额
		GDPOrderResponse gDPOrderResponse =titanFinancialTradeService.getGDPOrderDTO(paymentRequest.getPayOrderNo());
	    if(gDPOrderResponse.isResult()){
	    	GDPOrderDTO gDPOrderDTO = gDPOrderResponse.getgDPOrderDTO();
	    	if(gDPOrderDTO!=null && gDPOrderDTO.getOrderSum() !=null){
	    		paymentRequest.setPayAmount(gDPOrderDTO.getOrderSum().toString());
		    	paymentRequest.setTradeamount(Long.parseLong(NumberUtil.covertToCents(gDPOrderDTO.getOrderSum().toString())));
	    	}else{
	    		resultMap.put(WebConstant.MSG, "该订单错误");
		    	return resultMap;
	    	}
	    }else{
	    	resultMap.put(WebConstant.MSG, "该订单不存在");
	    	return resultMap;
	    }
	   
		resultMap.put(WebConstant.RESULT, WebConstant.SUCCESS);
		return resultMap;
	}
	
	private boolean validateIsConfirmed(Integer transId){
		TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
		titanTransferDTO.setTransorderid(transId);
		List<TitanTransferDTO> titanTransferDTOList = titanOrderService.getTitanTransferDTOList(titanTransferDTO);
		if(titanTransferDTOList !=null && titanTransferDTOList.size()>0){
			for(TitanTransferDTO transferDTO : titanTransferDTOList){
				if(transferDTO.getStatus() !=null){
					if(TransferReqEnum.Status_2.getStatus()==transferDTO.getStatus().intValue()){
						return false;
					}
				}
				
			}
		}
		return true;
	}
	
	//订单是否回调成功
	private boolean  validateOrderIsSuccess(TransOrderDTO transOrderDTO){
		OrderRequest orderRequest = new OrderRequest();
		orderRequest.setOpertype(OperTypeEnum.Query_Order.getKey());
		orderRequest.setUserid(transOrderDTO.getUserid());
		orderRequest.setOrdertime(transOrderDTO.getOrdertime());
		orderRequest.setOrderdate(transOrderDTO.getOrderdate());
		orderRequest.setOrdertypeid(OrderTypeEnum.OrderType_1.getKey());
		orderRequest.setUserorderid(transOrderDTO.getUserorderid());
		orderRequest.setAmount(transOrderDTO.getAmount().toString());
		orderRequest.setProductId(transOrderDTO.getProductid());
		TransOrderCreateResponse transOrderCreateResponse = titanFinancialTradeService.operateRSTransOrder(orderRequest);
		if(transOrderCreateResponse.getOrderOperateInfoList()!=null){
			for(OrderOperateInfoDTO orderOperateInfoDTO :transOrderCreateResponse.getOrderOperateInfoList()){
				if(orderOperateInfoDTO.getStatusId().equals("4")){
					return true;
				}
			}
		}
		
		return false;
	}
	
	

	/**
	 * 确认是否免密支付
	 * @param userid
	 * @param totalAmount
	 * @return
	 */
	@ResponseBody
	@RequestMapping("allownopwdpay")
	public Map<String,String> validateIsAllowAoAwdpay(String userid,String totalAmount){
		Map<String,String> resultMap = new HashMap<String, String>();
		if(!StringUtil.isValidString(userid)){
			userid = this.getUserId();
		}
		if(StringUtil.isValidString(userid)){
			AllowNoPwdPayResponse allowNoPwdPayResponse = isAllowNoPwdPay(userid,totalAmount);
            if(allowNoPwdPayResponse.isAllowNoPwdPay()){
            	resultMap.put(WebConstant.RESULT, WebConstant.SUCCESS);
            	return resultMap;
            }
		}
		resultMap.put(WebConstant.RESULT, "failure");
		return resultMap;
	}
	
	private AllowNoPwdPayResponse isAllowNoPwdPay(String userid,String totalAmount){
		JudgeAllowNoPwdPayRequest judgeAllowNoPwdPayRequest = new JudgeAllowNoPwdPayRequest();
		judgeAllowNoPwdPayRequest.setMoney(totalAmount);
		judgeAllowNoPwdPayRequest.setUserid(userid);
		AllowNoPwdPayResponse allowNoPwdPayResponse = titanFinancialTradeService.isAllowNoPwdPay(judgeAllowNoPwdPayRequest);
	    return allowNoPwdPayResponse;
	}
	
	private void saveCommonPayMethod(PaymentRequest paymentRequest){
		try{
			CommonPayMethodDTO commonPayMethodDTO = new CommonPayMethodDTO();
			if(StringUtil.isValidString(paymentRequest.getDeskId())){
				commonPayMethodDTO.setDeskid(Integer.parseInt(paymentRequest.getDeskId()));
			}
			commonPayMethodDTO.setBankname(paymentRequest.getBankInfo());
			commonPayMethodDTO.setCreator(paymentRequest.getUserid());
			commonPayMethodDTO.setCreatetime(new Date());
			if(StringUtil.isValidString(paymentRequest.getLinePayType())){
			   commonPayMethodDTO.setPaytype(Integer.parseInt(paymentRequest.getLinePayType()));
			}
			titanCashierDeskService.saveCommonPayMethod(commonPayMethodDTO);
		}catch(Exception e){
			log.error("保存常用的支付方式失败"+e.getMessage(),e);
			e.printStackTrace();
		}
	}
	
	private FinancialOrderResponse getFinancialOrderResponse(PaymentRequest paymentRequest){
		FinancialOrderRequest financialOrderRequest = new FinancialOrderRequest();
		financialOrderRequest.setMerchantcode(paymentRequest.getMerchantcode());
		financialOrderRequest.setOrderNo(paymentRequest.getPayOrderNo());
		return titanFinancialTradeService.queryFinanceOrderDetail(financialOrderRequest);
	    
	}
	
    private Map<String,String> validatePaymentDate(PaymentRequest paymentRequest,FinancialOrderResponse financialOrderResponse) throws Exception{
    	Map<String,String> resultMap = new HashMap<String, String>();
		resultMap.put(WebConstant.RESULT, WebConstant.FAIL); 
		
		//查询付款方信息
		TitanUserBindInfoDTO titanUserBindInfoDTO = getTitanUserBindInfo(paymentRequest.getFcUserid());
		if(titanUserBindInfoDTO !=null ){
			paymentRequest.setCreator(titanUserBindInfoDTO.getUsername());
			paymentRequest.setOperator(titanUserBindInfoDTO.getUsername());
		}
		
		//是否需要免密支付,只有用到余额转账付款的时候才需要验证密码
		BigDecimal totalAmount = null;
		totalAmount = new BigDecimal(paymentRequest.getPayAmount());
		if(StringUtil.isValidString(paymentRequest.getTransferAmount())){
			totalAmount = new BigDecimal(paymentRequest.getPayAmount()).add(new BigDecimal(paymentRequest.getTransferAmount()));
		}
		
		if(totalAmount.subtract(new BigDecimal(paymentRequest.getPayAmount())).compareTo(BigDecimal.ZERO)==1){//有转账交易
			AllowNoPwdPayResponse allowNoPwdPayResponse = isAllowNoPwdPay(paymentRequest.getUserid(),totalAmount.toString());
			if(!allowNoPwdPayResponse.isAllowNoPwdPay()){
				if(titanUserBindInfoDTO !=null && titanUserBindInfoDTO.getTfsuserid()!=null){
//						paymentRequest.setPayPassword(RSADecryptString.decryptString(paymentRequest.getPayPassword(),request));
					paymentRequest.setPayPassword(paymentRequest.getPayPassword());
					//验证支付密码
					boolean flag = titanFinancialUserService.checkPayPassword(paymentRequest.getPayPassword(),titanUserBindInfoDTO.getTfsuserid().toString());
				   if(!flag){
				   	resultMap.put(WebConstant.MSG, "支付密码错误");
				   	return resultMap;
				   }
				}else{
				   	resultMap.put(WebConstant.MSG, "用户不存在");
				   	return resultMap;
				}
			}
		}
		
		if(financialOrderResponse !=null){//查询账户
			OrgDTO orgDTO = new OrgDTO();
			orgDTO.setOrgname(paymentRequest.getRecieveOrgName());
			orgDTO.setTitancode(paymentRequest.getRecieveTitanCode());
			orgDTO= titanFinancialOrganService.queryOrg(orgDTO);
			if(orgDTO ==null){
				resultMap.put(WebConstant.MSG, "账户不存在");
				return resultMap;
			}
			paymentRequest.setUserrelateid(orgDTO.getUserid());
			paymentRequest.setInterProductid(orgDTO.getProductid());
			
			
			if(StringUtil.isValidString(paymentRequest.getUserrelateid())){//收款方不为空，则判断是否自己给自己付款
				if(paymentRequest.getUserrelateid().equals(paymentRequest.getUserid())){
					resultMap.put(WebConstant.MSG, "收款账户不能和付款账户相同");
					return resultMap;
				}
			}
			
			//获取账户余额
			AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
			accountBalanceRequest.setUserid(paymentRequest.getUserid());
			AccountBalanceResponse accountBalanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
			BigDecimal balanceAccount = getBalanceAccount(accountBalanceResponse);
			
			//验证支付金额准确性，balanceAccount默认等于0 不会为空
			if (financialOrderResponse.getPayAmount() != null && balanceAccount != null) { //判断转账金额和支付金额的总和是不是等于
				boolean flag = validatePayAmount(paymentRequest.getTransferAmount(), paymentRequest.getPayAmount(),
						financialOrderResponse.getPayAmount(), balanceAccount);
				if (!flag) {
					resultMap.put(WebConstant.MSG, "系统错误");
					return resultMap;
				}
			}else{
					resultMap.put(WebConstant.MSG, "系统错误");
					return resultMap;
			    }
			 //交易金额
			paymentRequest.setTradeamount(Long.parseLong(NumberUtil.covertToCents(financialOrderResponse.getPayAmount().toString())));
		}else{
			paymentRequest.setTradeamount(Long.parseLong(NumberUtil.covertToCents(paymentRequest.getPayAmount().toString())));
		}
		resultMap.put(WebConstant.RESULT, WebConstant.SUCCESS);
		return resultMap;
	}
    
    private TitanUserBindInfoDTO getTitanUserBindInfo(String fcUserId) throws GlobalServiceException{
    	TitanUserBindInfoDTO  titanUserBindInfoDTO = new TitanUserBindInfoDTO();
		if(StringUtil.isValidString(fcUserId)){
			titanUserBindInfoDTO.setFcuserid(Long.parseLong(fcUserId));
		}else if(null !=this.getTfsUserId()){
			titanUserBindInfoDTO.setTfsuserid(Integer.parseInt(this.getTfsUserId()));
		}else{
			return null;
		}
		return titanFinancialUserService.getUserBindInfoByFcuserid(titanUserBindInfoDTO);
    }
    
    private boolean accountIsExist(PaymentRequest paymentRequest){
    	if(StringUtil.isValidString(paymentRequest.getRecieveOrgName())&&
    			StringUtil.isValidString(paymentRequest.getRecieveTitanCode())){
    		AccountCheckRequest accountCheckRequest = new AccountCheckRequest();
    		accountCheckRequest.setOrgName(paymentRequest.getRecieveOrgName());
    		accountCheckRequest.setTitanCode(paymentRequest.getRecieveTitanCode());
    		AccountCheckResponse accountCheckResponse = titanFinancialAccountService.checkTitanCode(accountCheckRequest);
    		if(accountCheckResponse.isCheckResult()){
    			paymentRequest.setUserrelateid(accountCheckResponse.getUserid());
    			return true;
    		}
    	}
		return false;
    }    
    
	private void putSysError(Map<String,String> resultMap){
		resultMap.put("result", "false");
		resultMap.put("msg", "系统错误");
	}
	//更新订单状态
	private boolean updateOrderStatus(Integer transId,OrderStatusEnum orderStatusEnum){
		try{
			TransOrderDTO transOrderDTO = new TransOrderDTO();
			transOrderDTO.setStatusid(orderStatusEnum.getStatus());
			transOrderDTO.setTransid(transId);
			boolean flag =  titanOrderService.updateTransOrder(transOrderDTO);
			return flag;
		}catch(Exception e){
			log.error("更新订单失败"+e.getMessage(),e);
		}
		return false;
	}
	
	//冻结
	private boolean freezeAccountBalance(TransferRequest transferRequest,String orderNo) {
		try{
			RechargeResultConfirmRequest rechargeResultConfirmRequest = new RechargeResultConfirmRequest();
	    	rechargeResultConfirmRequest.setOrderNo(orderNo);
	    	rechargeResultConfirmRequest.setPayAmount(transferRequest.getAmount());
	    	rechargeResultConfirmRequest.setUserid(transferRequest.getUserrelateid());
	    	rechargeResultConfirmRequest.setOrderAmount(transferRequest.getAmount());
	    	FreezeAccountBalanceResponse freezeAccountBalanceResponse = titanFinancialAccountService.freezeAccountBalance(rechargeResultConfirmRequest);
			if(freezeAccountBalanceResponse.isFreezeSuccess()){
				return true;
			}
		}catch(Exception e){
			log.error("冻结余额失败"+e.getMessage(),e);
		}
    	return false;
	}
	
	//应该要判定是否返回正确的response
	private RechargePageRequest convertToRechargePageRequest(PaymentRequest paymentRequest) {
		RechargePageRequest rechargePageRequest = new RechargePageRequest();
		PayMethodConfigRequest payMethodConfigRequest = new PayMethodConfigRequest();
		payMethodConfigRequest.setUserId(paymentRequest.getUserid());
		PayMethodConfigDTO payMethodConfigDTO = titanFinancialTradeService.getPayMethodConfigDTO(payMethodConfigRequest);
		if(payMethodConfigDTO !=null){
			rechargePageRequest.setPageUrl(payMethodConfigDTO.getPageurl());
			rechargePageRequest.setNotifyUrl(payMethodConfigDTO.getNotifyurl());
		}
		
		rechargePageRequest.setUserid(paymentRequest.getUserid());
		rechargePageRequest.setOrderid(paymentRequest.getOrderid());
		rechargePageRequest.setOrderExpireTime(45);
		rechargePageRequest.setBankInfo(paymentRequest.getBankInfo());
		rechargePageRequest.setUserrelateid(paymentRequest.getUserrelateid());
		return rechargePageRequest;
	}
	
	private TransferRequest convertToTransferRequest(PaymentRequest paymentRequest){
		TransferRequest transferRequest = new TransferRequest();
		transferRequest.setCreator(paymentRequest.getCreator());
    	transferRequest.setUserid(paymentRequest.getUserid());										//转出的用户
    	transferRequest.setRequestno(OrderGenerateService.genResquestNo());									//业务订单号
    	transferRequest.setRequesttime(DateUtil.sdf4.format(new Date()));				//请求时间
    	transferRequest.setAmount(NumberUtil.covertToCents(paymentRequest.getTransferAmount()));										//金额 必须是分
    	transferRequest.setUserfee("0");									
    	transferRequest.setUserrelateid(paymentRequest.getUserrelateid());	                    //接收方用户Id
		return transferRequest;
	}

	//获取可用余额，多账户体系时候需考虑是哪个子账户
	private BigDecimal getBalanceAccount(AccountBalanceResponse accountBalanceResponse){
		if (CollectionUtils.isNotEmpty(accountBalanceResponse.getAccountBalance())) {
			AccountBalance accountBalance = accountBalanceResponse.getAccountBalance().get(0);
			if (accountBalance != null && StringUtil.isValidString(accountBalance.getBalanceusable())) {
				return new BigDecimal(accountBalance.getBalanceusable());
			}
		}
		return null;
	}
	
	//验证金额
	private boolean validatePayAmount(String transferAmount, String payAmount,
									  BigDecimal totalPayAmount, BigDecimal balanceAccount) {
		boolean flag = true;
		if (!StringUtil.isValidString(payAmount)) {
			payAmount = "0";
		}
		if (!StringUtil.isValidString(transferAmount)) {
			transferAmount = "0";
		}
		totalPayAmount = (totalPayAmount.multiply(new BigDecimal(100))).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal payTotal = new BigDecimal(NumberUtil.covertToCents(payAmount)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		BigDecimal transTotal = new BigDecimal(NumberUtil.covertToCents(transferAmount)).setScale(2, BigDecimal.ROUND_HALF_DOWN);
		//比较总金额是否相等
		if (!totalPayAmount.equals(payTotal.add(transTotal))) {
			flag = false;
		}
		//判断转账金额是否小于可用账户余额
		balanceAccount = balanceAccount.setScale(2, BigDecimal.ROUND_HALF_DOWN);
		if (balanceAccount.subtract(transTotal).compareTo(BigDecimal.ZERO) < 0) {
			flag = false;
		}
		return flag;
	}
	
	@RequestMapping(value = "/showCashierDesk", method = RequestMethod.GET)
	public String showCashierDesk(PaymentUrlRequest paymentUrlRequest,HttpServletRequest request, Model model) throws Exception {
		log.info("获取支付地址入参:" + toJson(paymentUrlRequest));
		if (!CashierDeskTypeEnum.RECHARGE.deskCode.equals(paymentUrlRequest.getPaySource())) {
			boolean flag = validateShowDeskSign(paymentUrlRequest);
			if (!flag) {//签名验证失败
				model.addAttribute(WebConstant.MSG, "签名验证失败");
				return "checkstand-pay/cashierDeskError";
			}
		}
		
		if(CashierDeskTypeEnum.B2B_DESK.deskCode.equals(paymentUrlRequest.getPaySource())){//GDP付款
			GDPOrderResponse gDPOrderResponse =titanFinancialTradeService.getGDPOrderDTO(paymentUrlRequest.getPayOrderNo());
		    if(gDPOrderResponse.isResult() && null != gDPOrderResponse.getgDPOrderDTO()){
				if(!"CNY".equals(gDPOrderResponse.getgDPOrderDTO().getCurrency())){
		    		model.addAttribute(WebConstant.MSG,"必须是人民币支付");
		    		return "checkstand-pay/cashierDeskError";
		    	}
		    	model.addAttribute("gDPOrderDTO",gDPOrderResponse.getgDPOrderDTO());
		    	model.addAttribute("payOrderNo", paymentUrlRequest.getPayOrderNo());
		    }else{
	    		model.addAttribute(WebConstant.MSG,"该订单不存在");
    			return "checkstand-pay/cashierDeskError";
	    	}
			paymentUrlRequest.setUserid(defaultPayerConfig.getUserId());
			model.addAttribute("userId", paymentUrlRequest.getUserid());
		}

		//非B2B支付时，将付款方userId查询出来
		if(StringUtil.isValidString(paymentUrlRequest.getMerchantcode())){
			String userId = queryUserIdByMerchantCode(paymentUrlRequest.getMerchantcode());
			if(!StringUtil.isValidString(userId)){
				model.addAttribute(WebConstant.MSG,"该机构未绑定账户");
    			return "checkstand-pay/cashierDeskError";
			}
			paymentUrlRequest.setUserid(userId);
		}

		//用于GDP和商家联盟收款方获取，只允许去付款到对应公司账户
		String orgCode = null;
		if (StringUtil.isValidString(paymentUrlRequest.getRecieveMerchantCode())) {
			//获取绑定的机构信息
			OrgDTO orgDTO = queryFinancialOrganDTO(paymentUrlRequest.getRecieveMerchantCode());
			if (orgDTO == null || !StringUtil.isValidString(orgDTO.getOrgcode())) {
				model.addAttribute(WebConstant.MSG, "收款机构不存在");
				return "checkstand-pay/cashierDeskError";
			}
			model.addAttribute("orgDTO", orgDTO);
			orgCode = orgDTO.getOrgcode();
		}

		//将解冻时间和是否冻结以及其它参数传入组装
		model.addAttribute("isEscrowed", paymentUrlRequest.getIsEscrowed());
		model.addAttribute("escrowedDate", paymentUrlRequest.getEscrowedDate());
		if(CashierDeskTypeEnum.SUPPLY_DESK.deskCode.equals(paymentUrlRequest.getPaySource())){//商家联盟
			model.addAttribute("fcUserid",paymentUrlRequest.getFcUserid());
			model.addAttribute("userId", paymentUrlRequest.getUserid());
			model.addAttribute("merchantcode", paymentUrlRequest.getMerchantcode());
			model.addAttribute("payOrderNo", paymentUrlRequest.getPayOrderNo());
			model.addAttribute("operator",paymentUrlRequest.getOperater());
		}else if(CashierDeskTypeEnum.RECHARGE.deskCode.equals(paymentUrlRequest.getPaySource())){//充值
			paymentUrlRequest.setUserid(this.getUserId());
	    	model.addAttribute("tfsUserId",getTfsUserId());
		}

		//开始获取收银台
		model.addAttribute("paySource",paymentUrlRequest.getPaySource());
		CashierDeskQueryRequest cashierDeskQueryRequest = new CashierDeskQueryRequest();
		cashierDeskQueryRequest.setUserId(paymentUrlRequest.getUserid());
		//GDP支付时用商家的收银台
		if(CashierDeskTypeEnum.B2B_DESK.deskCode.equals(paymentUrlRequest.getPaySource())){
			cashierDeskQueryRequest.setUserId(orgCode);
		}
		cashierDeskQueryRequest.setUsedFor(Integer.valueOf(paymentUrlRequest.getPaySource()));
		CashierDeskResponse response = titanCashierDeskService.queryCashierDesk(cashierDeskQueryRequest);
		if (!(response.isResult() && CollectionUtils.isNotEmpty(response.getCashierDeskDTOList()))){
			model.addAttribute(WebConstant.MSG,"收银台不存在");
			return "checkstand-pay/cashierDeskError";
		}
		model.addAttribute("cashierDesk", response.getCashierDeskDTOList().get(0));

		//常用的支付方式
		if(!CashierDeskTypeEnum.B2B_DESK.deskCode.equals(paymentUrlRequest.getPaySource())){
			List<CommonPayMethodDTO> commonPayMethodDTOList = titanCashierDeskService.queryCommonPayMethod(cashierDeskQueryRequest);
			if(commonPayMethodDTOList!=null && commonPayMethodDTOList.size()>0){
				model.addAttribute("commomPayMethod",commonPayMethodDTOList);
			}
			AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
			accountBalanceRequest.setUserid(paymentUrlRequest.getUserid());
			AccountBalanceResponse balanceResponse = titanFinancialAccountService.queryAccountBalance(accountBalanceRequest);
			if (balanceResponse.isResult() && CollectionUtils.isNotEmpty(balanceResponse.getAccountBalance())) {
				AccountBalance accountBalance = balanceResponse.getAccountBalance().get(0);
				if (accountBalance.getBalanceusable() != null) {
					accountBalance.setBalanceusable(new BigDecimal(accountBalance.getBalanceusable()).divide(new BigDecimal(100)).toString());
					model.addAttribute("accountBalance", accountBalance);
				} else {
					model.addAttribute(WebConstant.MSG, "账户资金异常");
					return "checkstand-pay/cashierDeskError";
				}
			} else {
				model.addAttribute(WebConstant.MSG, "账户查询异常");
				return "checkstand-pay/cashierDeskError";
			}

			if (CashierDeskTypeEnum.SUPPLY_DESK.deskCode.equals(paymentUrlRequest.getPaySource())) {//充值时不需要查余额，和查财务
				FinancialOrderRequest financialOrderRequest = new FinancialOrderRequest();
				financialOrderRequest.setMerchantcode(paymentUrlRequest.getMerchantcode());
				financialOrderRequest.setOrderNo(paymentUrlRequest.getPayOrderNo());
				FinancialOrderResponse financialOrderResponse = titanFinancialTradeService.queryFinanceOrderDetail(financialOrderRequest);
				if (financialOrderResponse.isResult()) {
					model.addAttribute("orderDTO", financialOrderResponse);
					if (!StringUtil.isValidString(paymentUrlRequest.getRecieveMerchantCode())) {//如果收款方机构为空时需查历史，否则付款给指定账户
						AccountHistoryRequest accHistoryRequest = new AccountHistoryRequest();
						accHistoryRequest.setAccountHistoryDTO(new AccountHistoryDTO());
						accHistoryRequest.getAccountHistoryDTO().setInaccountcode(financialOrderResponse.getInAccountCode());
						accHistoryRequest.getAccountHistoryDTO().setOutaccountcode(financialOrderResponse.getOutAccountCode());
						accHistoryRequest.getAccountHistoryDTO().setPayeruserid(paymentUrlRequest.getUserid());
						AccountHistoryResponse accountHistoryResponse = titanFinancialAccountService.queryAccountHistory(accHistoryRequest);
						if (accountHistoryResponse.isResult() &&
								CollectionUtils.isNotEmpty(accountHistoryResponse.getAccountHistoryDTOList())) {
							Map<String, FinancialOrganDTO> userIDOrgMap = this.buildUserIdOrganMap(accountHistoryResponse.getAccountHistoryDTOList());
							model.addAttribute("userIDOrgMap", userIDOrgMap);
							/*model.addAttribute("accountHistoryList", accountHistoryResponse.getAccountHistoryDTOList());*/
							model.addAttribute("accountHistory", accountHistoryResponse.getAccountHistoryDTOList().get(0));
						}
					}
				} else {
					model.addAttribute(WebConstant.MSG, "该订单不存在");
					return "checkstand-pay/cashierDeskError";
				}
			}
		}
		
		MerchantResponseDTO merchantResponseDTO = null;
		if(CashierDeskTypeEnum.B2B_DESK.deskCode.equals(paymentUrlRequest.getPaySource())){
			merchantResponseDTO = getMerchantResponseDTO(paymentUrlRequest.getRecieveMerchantCode());
		}else if(CashierDeskTypeEnum.SUPPLY_DESK.deskCode.equals(paymentUrlRequest.getPaySource())){
			merchantResponseDTO = getMerchantResponseDTO(paymentUrlRequest.getMerchantcode());
		}
        if(merchantResponseDTO !=null){
        	model.addAttribute(WebConstant.SESSION_KEY_CURRENT_THEME, merchantResponseDTO.getTheme());
        }
		
	    if(CashierDeskTypeEnum.RECHARGE.deskCode.equals(paymentUrlRequest.getPaySource())){//充值返回充值收银台
	        //查询账户的泰坦码和账户
	    	FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
	    	organQueryRequest.setUserId(paymentUrlRequest.getUserid());
	    	FinancialOrganResponse financialOrgan =  titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
	    	if(financialOrgan !=null){
	    		model.addAttribute("financialOrgan",financialOrgan.getFinancialOrganDTO());
	    	}
	    	
	    	return "account-overview/account-recharge";
	    }
		return "checkstand-pay/cashierDesk";
	}

	private String queryUserIdByMerchantCode(String merchantCode){
		OrgBindInfo orgBindInfo = new OrgBindInfo();
		orgBindInfo.setMerchantCode(merchantCode);
		orgBindInfo = titanFinancialOrganService.queryOrgBindInfoByUserid(orgBindInfo);
		if(orgBindInfo !=null){
			return orgBindInfo.getOrgcode();
		}
		return null;
	}
	
	private OrgDTO queryFinancialOrganDTO(String recieveMerchantCode){
		OrgBindInfo orgBindInfo  = new OrgBindInfo();
		orgBindInfo.setMerchantCode(recieveMerchantCode);
		orgBindInfo = titanFinancialOrganService.queryOrgBindInfoByUserid(orgBindInfo);
		if(orgBindInfo !=null){
			OrgDTO orgDTO = new OrgDTO();
			orgDTO.setOrgcode(orgBindInfo.getOrgcode());
			return titanFinancialOrganService.queryOrg(orgDTO);
		}
		return null;
	}
	
	private MerchantResponseDTO getMerchantResponseDTO(String merchantCode){
		MerchantDetailQueryDTO queryDTO = new MerchantDetailQueryDTO();
        queryDTO.setMerchantCode(merchantCode);
        return getMerchantFacade().queryMerchantDetail(queryDTO);
	}

	private MerchantFacade getMerchantFacade(){
		if(merchantFacade ==null){
			merchantFacade = hessianProxyBeanFactory.getHessianProxyBean(MerchantFacade.class,
		             ProxyFactoryConstants.merchantServerUrl + "merchantFacade");
		}
		 return merchantFacade;
	}
	
	private Map<String,FinancialOrganDTO> buildUserIdOrganMap(List<AccountHistoryDTO> historyDTOList){
		Map<String,FinancialOrganDTO> result = new HashMap<String, FinancialOrganDTO>();
		for (AccountHistoryDTO accountHistoryDTO : historyDTOList) {
			FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
			organQueryRequest.setUserId(accountHistoryDTO.getPayeeuserid());
			FinancialOrganResponse financialOrganResponse = titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
			if (financialOrganResponse.isResult() && financialOrganResponse.getFinancialOrganDTO() != null) {
				result.put(accountHistoryDTO.getPayeeuserid(), financialOrganResponse.getFinancialOrganDTO());
			}
		}
		return result;
	}
	
	//验证签名
	private boolean validateShowDeskSign(PaymentUrlRequest paymentUrlRequest)
			throws Exception {
		if (paymentUrlRequest != null) {
			// 获取key
			String key = titanOrderService.getKeyByPayOrderNo(paymentUrlRequest
					.getPayOrderNo());
			if (StringUtil.isValidString(key)) {
				StringBuffer sign = new StringBuffer("?merchantcode="+paymentUrlRequest.getMerchantcode());
				sign.append("&fcUserid="+paymentUrlRequest.getFcUserid());
				sign.append("&payOrderNo="+paymentUrlRequest.getPayOrderNo());
				sign.append("&paySource="+paymentUrlRequest.getPaySource());
				sign.append("&operater="+paymentUrlRequest.getOperater());
				sign.append("&recieveMerchantCode="+paymentUrlRequest.getRecieveMerchantCode());
				sign.append("&isEscrowed="+paymentUrlRequest.getIsEscrowed());
				sign.append("&escrowedDate="+paymentUrlRequest.getEscrowedDate());
				sign.append("&key="+key);
				if (MD5.MD5Encode(sign.toString(), "UTF-8").equals(
						paymentUrlRequest.getSign())) {// 验证签名,调用融数下单
                   return true;
				}
			}
		}
		return false;
	}
	
//	private void lockOutTradeNoList(String out_trade_no) throws InterruptedException {
//		synchronized (orderNoList) {
//			while(orderNoList.contains(out_trade_no)) {
//				orderNoList.wait();
//			}
//			orderNoList.add(out_trade_no);
//		} 
//	}
//	
//	private void unlockOutTradeNoList(String out_trade_no) {
//		synchronized (orderNoList) {
//			orderNoList.remove(out_trade_no);
//			orderNoList.notifyAll();
//		}
//	}

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
