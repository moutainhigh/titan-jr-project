/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName ChannelCallbackController.java
 * @author Jerry
 * @date 2017年11月27日 下午5:28:18  
 */
package com.titanjr.checkstand.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.fangcang.util.JsonUtil;
import com.titanjr.checkstand.request.RBDataRequest;
import com.titanjr.checkstand.request.RBQuickPayCallbackData;
import com.titanjr.checkstand.request.TLNetBankPayCallbackRequest;
import com.titanjr.checkstand.request.TLQrCodePayCallbackRequest;
import com.titanjr.checkstand.util.rbUtil.Decipher;

/**
 * 支付渠道回调控制器
 * @author Jerry
 * @date 2017年11月27日 下午5:28:18  
 */
@Controller
@RequestMapping(value = "/callback")
public class ChannelCallbackController extends BaseController {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 4077037033223889564L;
	private final static Logger logger = LoggerFactory.getLogger(ChannelCallbackController.class);
	
	
	/**
	 * 通联网银支付前台回调
	 * @author Jerry
	 * @date 2017年11月27日 下午5:33:38
	 */
	@RequestMapping(value = "/tlNetBankPayConfirmPage", method = {RequestMethod.POST})
	public void tlNetBankPayConfirmPage(HttpServletRequest request, TLNetBankPayCallbackRequest 
			callbackRequest){
		
		logger.info("=========================通联网银支付前台回调");
		
	}
	
	/**
	 * 通联网银支付后台通知
	 * @author Jerry
	 * @date 2017年11月27日 下午5:33:38
	 */
	@RequestMapping(value = "/tlNetBankPayNotice", method = {RequestMethod.POST})
	public void tlNetBankPayNotify(HttpServletRequest request, TLNetBankPayCallbackRequest 
			callbackRequest){
		
		logger.info("=========================通联网银支付后台通知");
		
	}
	
	/**
	 * 通联扫码支付后台通知（此接口暂时不接，通联建议主动查询）
	 * @author Jerry
	 * @date 2017年12月21日 上午9:53:49
	 */
	@RequestMapping(value = "/tlQrCodePayNotice", method = {RequestMethod.POST})
	public void tlQrCodePayNotice(HttpServletRequest request, TLQrCodePayCallbackRequest 
			tlQrCodePayCallbackRequest){
		
		logger.info("=========================通联扫码支付结果通知");
		
	}
	
	/**
	 * 融宝快捷支付后台通知
	 * @author Jerry
	 * @throws Exception 
	 * @date 2018年1月5日 下午5:00:50
	 */
	@RequestMapping(value = "/rbQuickPayNotice", method = {RequestMethod.POST})
	public void rbQuickPayNotice(HttpServletRequest request, HttpServletResponse response, 
			RBDataRequest rbDataRequest) throws Exception{
		
		RBQuickPayCallbackData data = new RBQuickPayCallbackData();
		response.getWriter().print("success");
		response.flushBuffer();
		
		String jsonRes = JsonUtil.objectToJson(rbDataRequest);
		jsonRes = Decipher.decryptData(jsonRes);
		data = (RBQuickPayCallbackData)JsonUtil.jsonToBean(jsonRes, RBQuickPayCallbackData.class);
		logger.info("【融宝-快捷支付结果通知】:" + data.toString());
		
	}

}
