package com.titanjr.checkstand.api;

import com.titanjr.checkstand.constants.OperateTypeEnum;
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
    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public String payment(HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {

        //请求频次校验
        //参数MD5验证

        resetParameter(request,attr);
        
        //根据操作类型选择路由
        //OperateTypeEnum operateTypeEnum = JRBeanUtils.recognizeRequestType(request.getParameterMap().keySet());
        OperateTypeEnum operateTypeEnum = JRBeanUtils.getOperateType(request);
        if(operateTypeEnum == null){
        	logger.error("路由错误");
        	return super.payFailedCallback(model);
        }

        if (operateTypeEnum.equals(OperateTypeEnum.PAY_REQUEST)){
            request.setCharacterEncoding("UTF-8");
            return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/pay/entrance.shtml";
        }
        
        if (operateTypeEnum.equals(OperateTypeEnum.PAY_QUERY)){
            request.setCharacterEncoding("UTF-8");
            return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/query/entrance.shtml";
        }

        if (operateTypeEnum.equals(OperateTypeEnum.REFUND_REQUEST)){
            return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/refund/entrance.shtml";
        }
        
        if (operateTypeEnum.equals(OperateTypeEnum.REFUND_QUERY)){
            return "redirect:" + WebUtils.getRequestBaseUrl(request) + "/rfQuery/entrance.shtml";
        }
        
        logger.error("【{}】{}未匹配对应的入口", OperateTypeEnum.REFUND_QUERY.getKey(), OperateTypeEnum.REFUND_QUERY.getValue());
        return super.payFailedCallback(model);
    }

}
