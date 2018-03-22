package com.titanjr.checkstand.api;

import com.titanjr.checkstand.constants.BusiCodeEnum;
import com.titanjr.checkstand.controller.BaseController;
import com.titanjr.checkstand.util.JRBeanUtils;
import com.titanjr.checkstand.util.WebUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

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
	 * 根据策略跳转到对应的收银台路由代理,有些是返回字串，有些是做网关跳转
	 */
    @RequestMapping(value = "/payment", method = {RequestMethod.POST, RequestMethod.GET})
    public String payment(HttpServletRequest request, RedirectAttributes attr, Model model) {

        //请求频次校验
        //参数MD5验证
        try {
        	
			resetParameter(request, attr);
			BusiCodeEnum busiCodeEnum = JRBeanUtils.getBusiCode(request);
			
			if(busiCodeEnum == null){
				logger.error("路由错误，busiCodeEnum={}", busiCodeEnum);
				return super.payFailedCallback(model);
			}

			if (busiCodeEnum.equals(BusiCodeEnum.PAY_REQUEST)){
			    request.setCharacterEncoding("UTF-8");
			    //return "forward:/pay/entrance.shtml";
			    return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/pay/entrance.shtml";
			}
			
			if (busiCodeEnum.equals(BusiCodeEnum.PAY_QUERY)){
			    request.setCharacterEncoding("UTF-8");
			    //return "forward:/query/entrance.shtml";
			    return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/query/entrance.shtml";
			}

			if (busiCodeEnum.equals(BusiCodeEnum.REFUND_REQUEST)){
			    request.setCharacterEncoding("UTF-8");
			    //return "forward:/refund/entrance.shtml";
			    return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/refund/entrance.shtml";
			}
			
			if (busiCodeEnum.equals(BusiCodeEnum.REFUND_QUERY)){
			    request.setCharacterEncoding("UTF-8");
			    //return "forward:/rfQuery/entrance.shtml";
			    return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/rfQuery/entrance.shtml";
			}
			
			if (busiCodeEnum.equals(BusiCodeEnum.AGENT_TRADE)){
			    request.setCharacterEncoding("UTF-8");
				return "forward:/agent/entrance.shtml";
			    //return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/agent/entrance.shtml";
			}
			
			if (busiCodeEnum.equals(BusiCodeEnum.CARD_BIN_QUERY) || busiCodeEnum.equals(BusiCodeEnum.QUICK_PAY_CONFIRM)
					|| busiCodeEnum.equals(BusiCodeEnum.RE_SEND_MSG) || busiCodeEnum.equals(BusiCodeEnum.CARD_AUTH)
					|| busiCodeEnum.equals(BusiCodeEnum.CARD_BIND_QUERY) || busiCodeEnum.equals(BusiCodeEnum.CARD_UNBIND)){
			    request.setCharacterEncoding("UTF-8");
				//return "forward:/quick/entrance.shtml";
			    return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/quick/entrance.shtml";
			}
			
			if (busiCodeEnum.equals(BusiCodeEnum.ACCOUNT_DOWNLOAD)){
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

}
