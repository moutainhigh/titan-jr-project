package com.titanjr.checkstand.api;

import com.titanjr.checkstand.constants.OperateTypeEnum;
import com.titanjr.checkstand.controller.BaseController;
import com.titanjr.checkstand.util.JRBeanUtils;
import com.titanjr.checkstand.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
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

    private final static Logger logger = LoggerFactory.getLogger(CheckStandController.class);


    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public String payment(HttpServletRequest request,  RedirectAttributes attr) throws Exception {

        //请求频次校验
        //参数MD5验证

        resetParameter(request,attr);
        //根据策略跳转到对应的收银台路由代理,有些是返回字串，有些是做网关跳转
        //需要从渠道和交易方式两个维度来处理，可能需要选择配置信息
        //参数解析判断与最初步校验
        OperateTypeEnum operateTypeEnum = JRBeanUtils.recognizeRequestType(request.getParameterMap().keySet());

        if (operateTypeEnum.equals(OperateTypeEnum.PAY_REQUEST)){
            return "redirect:" + WebUtil.getRequestBaseUrl(request) + "/pay/entrance.shtml";
        }

        if (operateTypeEnum.equals(OperateTypeEnum.REFUND_REQUEST)){
            return "redirect:" + WebUtil.getRequestBaseUrl(request) + "/refund/entrance.shtml";
        }

        return null;
    }

}
