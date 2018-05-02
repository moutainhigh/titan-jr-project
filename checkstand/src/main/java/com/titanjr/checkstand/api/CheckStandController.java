package com.titanjr.checkstand.api;

import com.titanjr.checkstand.constants.BusiCodeEnum;
import com.titanjr.checkstand.controller.BaseController;
import com.titanjr.checkstand.util.JRBeanUtils;
import com.titanjr.checkstand.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 网关支付总入口
 * Created by zhaoshan on 2017/11/15.
 */
@Controller
@RequestMapping(value = "/")
public class CheckStandController extends BaseController {

    /** 
	 * 
	 */
	private static final long serialVersionUID = -7886169613793728087L;
	private final static Logger logger = LoggerFactory.getLogger(CheckStandController.class);

	/**
	 * encode参数
	 * @param request
	 * @param attr
	 */
	private void resetParam(HttpServletRequest request, RedirectAttributes attr){
        for (String key : request.getParameterMap().keySet()){
			try {
				attr.addAttribute(key, URLEncoder.encode(request.getParameterMap().get(key)[0],"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.error("当前参数:{}编码失败，请注意", key, e);
			}
		}
    }
	
	/**
	 * 根据策略跳转到对应的收银台路由代理,有些是返回字串，有些是做网关跳转
	 */
    @RequestMapping(value = "/payment", method = {RequestMethod.POST, RequestMethod.GET})
    public String payment(HttpServletRequest request, RedirectAttributes attr, Model model) {
    	
    	logger.info("CHECKSTAND START");
    	
        //请求频次校验
        //参数MD5验证
        try {
        	
        	this.resetParam(request, attr);
			BusiCodeEnum busiCodeEnum = JRBeanUtils.getBusiCode(request);
			
			if(busiCodeEnum == null){
				logger.error("路由错误，busiCodeEnum={}", busiCodeEnum);
				return super.payFailedCallback(model);
			}

			if (busiCodeEnum.equals(BusiCodeEnum.PAY_REQUEST)){
				logger.info("CHECKSTAND：PAY_REQUEST");
			    request.setCharacterEncoding("UTF-8");
			    //return "forward:/pay/entrance.shtml";
			    return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/pay/entrance.shtml";
			}
			
			if (busiCodeEnum.equals(BusiCodeEnum.PAY_QUERY)){
				logger.info("CHECKSTAND：PAY_QUERY");
			    request.setCharacterEncoding("UTF-8");
			    //return "forward:/query/entrance.shtml";
			    return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/query/entrance.shtml";
			}

			if (busiCodeEnum.equals(BusiCodeEnum.REFUND_REQUEST)){
				logger.info("CHECKSTAND：REFUND_REQUEST");
			    request.setCharacterEncoding("UTF-8");
			    //return "forward:/refund/entrance.shtml";
			    return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/refund/entrance.shtml";
			}
			
			if (busiCodeEnum.equals(BusiCodeEnum.REFUND_QUERY)){
				logger.info("CHECKSTAND：REFUND_QUERY");
			    request.setCharacterEncoding("UTF-8");
			    //return "forward:/rfQuery/entrance.shtml";
			    return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/rfQuery/entrance.shtml";
			}
			
			if (busiCodeEnum.equals(BusiCodeEnum.AGENT_TRADE)){
				logger.info("CHECKSTAND：AGENT_TRADE");
			    request.setCharacterEncoding("UTF-8");
				return "forward:/agent/entrance.shtml";
			    //return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/agent/entrance.shtml";
			}
			
			if (busiCodeEnum.equals(BusiCodeEnum.CARD_BIN_QUERY) || busiCodeEnum.equals(BusiCodeEnum.QUICK_PAY_CONFIRM)
					|| busiCodeEnum.equals(BusiCodeEnum.RE_SEND_MSG) || busiCodeEnum.equals(BusiCodeEnum.CARD_AUTH)
					|| busiCodeEnum.equals(BusiCodeEnum.CARD_BIND_QUERY) || busiCodeEnum.equals(BusiCodeEnum.CARD_UNBIND)){
				logger.info("CHECKSTAND：QUICKPAY");
			    request.setCharacterEncoding("UTF-8");
				//return "forward:/quick/entrance.shtml";
			    return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/quick/entrance.shtml";
			}
			
			if (busiCodeEnum.equals(BusiCodeEnum.ACCOUNT_DOWNLOAD)){
				logger.info("CHECKSTAND：ACCOUNT_DOWNLOAD");
			    request.setCharacterEncoding("UTF-8");
				//return "forward:/download/entrance.shtml";
			    return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/download/entrance.shtml";
			}
			
			logger.error("【{}】{}未匹配对应的入口", busiCodeEnum.getKey(), busiCodeEnum.getValue());
			return super.payFailedCallback(model);
			
		} catch (Exception e) {

			logger.error("支付路由异常：", e);
			return super.payFailedCallback(model);
			
		}
    }

	/**
	 * 根据策略跳转到对应的收银台路由代理,有些是返回字串，有些是做网关跳转
	 */
	@RequestMapping(value = "/resultPage", method = {RequestMethod.POST, RequestMethod.GET})
	public String toResultPage(HttpServletRequest request, RedirectAttributes attr, Model model) {

		model.addAttribute("errorMsg", "请求频率过高，请稍后重试");
		return "payment/resultPage";
	}


	@Autowired
	ApplicationContext applicationContext;

	@RequestMapping(value = "/pathTest", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String pathTest(HttpServletRequest request) {
		String result = null;
		try {
			String testPath = applicationContext.getResource("classpath:").getFile().getPath();
			testPath = testPath.replace("classes","certify/tl/20022200000978704.p12");
			File testFile = new File(testPath);
			result = "filePath:" + testPath + ",======result:" + testFile.exists();
			String prePath = System.getProperty("checkstand.root"); 
			System.out.println("----------------web-info:"+prePath);

		} catch (IOException e) {
			e.printStackTrace();
			result="error";
		}
		return result;
	}

}
