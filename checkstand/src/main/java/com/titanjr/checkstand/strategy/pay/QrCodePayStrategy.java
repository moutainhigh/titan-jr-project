package com.titanjr.checkstand.strategy.pay;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.dto.bean.SysConfig;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.dto.TitanPayDTO;
import com.titanjr.checkstand.service.TitanCommonService;
import com.titanjr.checkstand.util.TitanSignValidater;
import com.titanjr.checkstand.util.WebUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2017/11/20.
 */
@Service("qrCodePayStrategy")
public class QrCodePayStrategy implements PayRequestStrategy{

	private static final Logger logger = LoggerFactory.getLogger(NetBankPayStrategy.class);

	@Resource
	private TitanOrderService titanOrderService;

	@Resource
	private TitanFinancialUtilService titanFinancialUtilService;
	
	@Resource
	private TitanCommonService titanCommonService;

    @Override
    public String redirectResult(HttpServletRequest request) {
    	
    	TitanPayDTO payDTO = WebUtils.switch2RequestDTO(TitanPayDTO.class, request);
		ValidateResponse res = GenericValidate.validateNew(payDTO);
		if (!res.isSuccess()){
			logger.error("【扫码/公众号支付】参数错误：{}", res.getReturnMessage());
			return null;
		}
		if(!titanCommonService.isOrderCanPay(payDTO)){
			return null;
		}
		SysConfig config = titanFinancialUtilService.querySysConfig();
		if(!TitanSignValidater.validatePaySign(payDTO, config.getRsCheckKey())){
			return null;
		}
    	
    	PayTypeEnum payTypeEnum = PayTypeEnum.getPayTypeEnum(request.getParameter("payType"));
    	String redirectUrl = "/pay/qrCodePay.shtml";
    	//微信公众号
    	if(PayTypeEnum.WECHAT.equals(payTypeEnum)){
    		redirectUrl = "/pay/wechatPay.shtml";
    	}
    	
        return redirectUrl;
        
    }
}
