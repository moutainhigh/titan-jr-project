package com.fangcang.titanjr.pay.strategy.pay;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.ui.Model;

import com.fangcang.titanjr.common.enums.BusinessLog;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderKindEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.TitanjrVersionEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dto.bean.RechargeDataDTO;
import com.fangcang.titanjr.dto.request.AddPayLogRequest;
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;
import com.fangcang.titanjr.dto.response.QrCodeResponse;
import com.fangcang.titanjr.enums.PayTypeEnum;
import com.fangcang.titanjr.service.BusinessLogService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.util.JsonUtil;
import com.fangcang.util.SpringContextUtil;

/**
 * 二维码支付
 * @author Jerry
 *
 */
public class QRCodePay implements PayStrategy {
	
	private static final Log log = LogFactory.getLog(QRCodePay.class);
	private BusinessLogService businessLogService;
	private TitanFinancialTradeService titanFinancialTradeService;
	private TitanFinancialUtilService titanFinancialUtilService;

	@Override
	public String pay(RechargeDataDTO rechargeDataDTO, TitanPaymentRequest titanPaymentRequest, Model model) {
		
		businessLogService = (BusinessLogService) SpringContextUtil.getBean("businessLogService");
		titanFinancialTradeService = (TitanFinancialTradeService) SpringContextUtil.getBean("titanFinancialTradeService");
		titanFinancialUtilService = (TitanFinancialUtilService) SpringContextUtil.getBean("titanFinancialUtilService");

		businessLogService.addPayLog(new AddPayLogRequest(BusinessLog.PayStep.WechatpayStep, 
				OrderKindEnum.PayOrderNo, titanPaymentRequest.getPayOrderNo()));
		
		if(PayTypeEnum.ALIPAY_URL.key.equals(rechargeDataDTO.getPayType())){
			rechargeDataDTO.setExpand2(CommonConstant.ALIPAY);
		}
		QrCodeResponse response = titanFinancialTradeService.getQrCodeUrl(rechargeDataDTO);
		if(!response.isResult()){
			log.error("订单号："+rechargeDataDTO.getOrderNo()+",第三方支付获取地址失败,错误信息："+response.getReturnMessage());
			titanFinancialUtilService.saveOrderException(rechargeDataDTO.getPayOrderNo(),OrderKindEnum.PayOrderNo, OrderExceptionEnum.Online_Pay_Get_Pay_Url_Fail, JSONSerializer.toJSON(rechargeDataDTO).toString());
			model.addAttribute(CommonConstant.RETURN_MSG, TitanMsgCodeEnum.QR_EXCEPTION.getKey());
			return CommonConstant.PAY_WX;
		}
		
		model.addAttribute(CommonConstant.RESULT, CommonConstant.RETURN_SUCCESS);
		model.addAttribute(CommonConstant.QRCODE,response.getQrCodeDTO());
		
		if(TitanjrVersionEnum.VERSION_1.getKey().equals(titanPaymentRequest.getJrVersion())){
			return CommonConstant.PAY_WX;
		}else{
			return JsonUtil.objectToJson(response);
		}
		
	}

}
