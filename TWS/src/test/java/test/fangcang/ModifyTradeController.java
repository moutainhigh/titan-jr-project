package test.fangcang;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.ReqstatusEnum;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.request.TransferRequest;
import com.fangcang.titanjr.dto.response.TransferResponse;
import com.fangcang.titanjr.web.util.WebConstant;
import com.fangcang.util.StringUtil;

public class ModifyTradeController {

	//改进融数回调的方法，待测
//	 public void payResultConfirm(RechargeResultConfirmRequest rechargeResultConfirmRequest,HttpServletResponse response) throws IOException{
//			String orderNo = rechargeResultConfirmRequest.getOrderNo();
//			try{
//				if(!StringUtil.isValidString(orderNo)){
//					return;
//				}
//				
//				response.getWriter().print("returnCode=000000&returnMag=成功");
//				
//				if(!isValidSign(rechargeResultConfirmRequest)){//验证签名
//					return ;
//				}
//				
//				if(!WebConstant.PAY_SUCCESS.equals(rechargeResultConfirmRequest.getPayStatus())){//充值未成功
//					return;
//				}
//				
//				lockOutTradeNoList(orderNo);//锁单
//				
//				TransOrderRequest transOrderRequest = new TransOrderRequest();
//	    		transOrderRequest.setOrderid(orderNo);
//	    		TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
//	    		if(transOrderDTO == null){//判断该回调是否已经执行过
//	    			unlockOutTradeNoList(orderNo);
//	    			return ;
//	    		}
//	    		
//				if(!validateIsConfirmed(transOrderDTO.getTransid())){//确认订单是否已经被成功回调过
//					unlockOutTradeNoList(orderNo);
//					return ;
//				}
//	    		
//				if(!validateOrderIsSuccess(transOrderDTO)){//主动调融数，查询订单是否成功
//					titanOrderService.updateTitanOrderPayreq(orderNo,ReqstatusEnum.Status_3.getStatus()+"");
//		        	this.updateOrderStatus(transOrderDTO.getTransid(),OrderStatusEnum.RECHARGE_FAIL);
//					unlockOutTradeNoList(orderNo);
//					return;
//				}
//				
//				int row = titanOrderService.updateTitanOrderPayreq(orderNo,ReqstatusEnum.Status_2.getStatus()+"");
//	        	if(row<1){
//	        		OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(orderNo, "充值成功 修改充值单失败", OrderExceptionEnum.OrderPay_Update, JSON.toJSONString(orderNo));
//	        		titanOrderService.saveOrderException(orderExceptionDTO);
//	        	}
//				
//	        	OrderStatusEnum orderStatusEnum = OrderStatusEnum.RECHARGE_SUCCESS;
//				
//	        	if(!StringUtil.isValidString(transOrderDTO.getPayermerchant())){//判断是充值操作，订单结束
//	        		orderStatusEnum= OrderStatusEnum.ORDER_SUCCESS;
//	        		this.updateOrder(transOrderDTO, orderStatusEnum);
//					unlockOutTradeNoList(orderNo);
//					return;
//	        	}
//	        	
//	        	TransferRequest transferRequest = convertToTransferRequest(transOrderDTO);
//	        	TransferResponse transferResponse = titanFinancialTradeService.transferAccounts(transferRequest);
//	        	log.info("转账结果:"+toJson(transferResponse));
//	        	if(!transferResponse.isResult()){
//	        		orderStatusEnum= OrderStatusEnum.TRANSFER_FAIL;
//	        		this.updateOrder(transOrderDTO, orderStatusEnum);
//					unlockOutTradeNoList(orderNo);
//					return;
//	        	}
//	        	
//	        	orderStatusEnum = OrderStatusEnum.TRANSFER_SUCCESS;
//	        	log.info("回调:"+toJson(transOrderDTO));
//				titanFinancialTradeService.confirmFinance(transOrderDTO);
//	        	
//				if(WebConstant.FREEZE_ORDER.equals(transOrderDTO.getIsEscrowedPayment())){//需要进行冻结操作
//	    			boolean freezeSuccess = freezeAccountBalance(transferRequest,orderNo);
//	    			log.info("冻结结果:"+freezeSuccess);
//					//修改订单状态
//					if(freezeSuccess){//冻结成功改变订单状态
//						orderStatusEnum = OrderStatusEnum.FREEZE_SUCCESS;
//					}else{
//						//TODO 添加部分成功的操作
//						orderStatusEnum = OrderStatusEnum.FREEZE_FAIL;
//						OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(orderNo, "冻结失败", OrderExceptionEnum.Freeze_Insert, JSON.toJSONString(transferRequest));
//		        		titanOrderService.saveOrderException(orderExceptionDTO);
//					}
//	    		}
//	    		
//				//记录收款账户
//				if(StringUtil.isValidString(transOrderDTO.getMerchantcode())){//GDP的账户历史是不需要记录的
//					if(StringUtil.isValidString(transOrderDTO.getMerchantcode())){
//						titanFinancialAccountService.addAccountHistory(transferRequest);
//					}
//				}
//				
//				if(orderStatusEnum.getStatus().equals(OrderStatusEnum.TRANSFER_SUCCESS.getStatus())){
//					orderStatusEnum = OrderStatusEnum.ORDER_SUCCESS;
//				}
//				this.updateOrder(transOrderDTO, orderStatusEnum);
//				unlockOutTradeNoList(orderNo);
//	    	}catch(Exception e){
//	    		log.error(e.getMessage());
//	    	}finally{
//	    		unlockOutTradeNoList(orderNo);
//	    	}
//	    }
	
//	private void updateOrder(TransOrderDTO transOrderDTO,OrderStatusEnum orderStatusEnum){
//		boolean updateStatus = this.updateOrderStatus(transOrderDTO.getTransid(),orderStatusEnum);
//		//修改订单状态
//		if(!updateStatus){
//			OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO(transOrderDTO.getOrderid(), "冻结成功 修改订单状态失败", OrderExceptionEnum.TransOrder_update, JSON.toJSONString(transOrderDTO.getTransid()));
//    		titanOrderService.saveOrderException(orderExceptionDTO);
//		}
//	}
//
//	private boolean isValidSign(RechargeResultConfirmRequest rechargeResultConfirmRequest ){
//		log.info("融数后台回调成功参数:"+toJson(rechargeResultConfirmRequest));
//		String signMsg = rechargeResultConfirmRequest.getSignMsg();
//   	    String sign = RechargeResultConfirmRequest.getSignStr(rechargeResultConfirmRequest);
//    	if(MD5.MD5Encode(sign, "UTF-8").equals(signMsg)){
//   		   return true;
//    	}
//    	return false;
//	}
	
	
		
}
