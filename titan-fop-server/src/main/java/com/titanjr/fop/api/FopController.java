package com.titanjr.fop.api;

import com.fangcang.util.StringUtil;
import com.titanjr.fop.constants.CommonConstants;
import com.titanjr.fop.constants.InterfaceConfigEnum;
import com.titanjr.fop.constants.InterfaceURlConfig;
import com.titanjr.fop.controller.BaseController;
import com.titanjr.fop.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 开放平台请求入口
 * 考虑修改成微服务架构
 * Created by zhaoshan on 2017/11/15.
 */
@Controller
@RequestMapping(value = "/")
public class FopController extends BaseController {


    private final static Logger logger = LoggerFactory.getLogger(FopController.class);

    @RequestMapping(value = "/fetchServiceUrl", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String fetchServiceUrl(HttpServletRequest request) throws Exception {
        String resultURL = "";
        if (!StringUtil.isValidString(request.getParameter("appSecret"))) {
            logger.error("传入参数不正确，appSecret不为空");
        }
        if (!StringUtil.isValidString(CommonConstants.appSecret) ||
                !request.getParameter("appSecret").equals(CommonConstants.appSecret)) {
            logger.error("系统配置appSecret为空或不一致");
        }
        if (StringUtil.isValidString(CommonConstants.actualUrl)) {
            resultURL = CommonConstants.actualUrl;
            resultURL = "http://local.fangcang.com:8090/titan-fop-server/fopapi.shtml";
        } else {
            logger.error("配置的URL为空或不合法");
        }
        return resultURL;
    }

    @RequestMapping(value = "/fopapi", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    public String fopsdk(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        String methodName = request.getParameter("method");
        InterfaceConfigEnum configEnum = InterfaceConfigEnum.getURlConfigByKey(methodName);
        String url = getRequestBaseUrl(request) + InterfaceURlConfig.INTERFACE_URL_MAP.get(configEnum);
        //String url = "http://www.fangcang.org/titan-fop-dev3" + InterfaceURlConfig.INTERFACE_URL_MAP.get(configEnum); //发到213用这个
        //resetParameter(request, attr);
        logger.info("请求转发,url:"+url);
        attr.addAttribute("signValid", request.getAttribute("signValid"));
        attr.addAttribute("sessionValid", request.getAttribute("sessionValid"));
        attr.addAttribute("appSecret", request.getAttribute("appSecret"));
        attr.addAttribute("method", request.getParameter("method"));
        attr.addAttribute("appKey", request.getParameter("appKey"));
        attr.addAttribute("timeStamp", request.getParameter("timeStamp"));
        attr.addAttribute("format", request.getParameter("format"));
        attr.addAttribute("session", request.getParameter("session"));
        return "redirect:" + url;
    }

    public String getRequestBaseUrl(HttpServletRequest req) {
        String contextPath = req.getContextPath();
        String host = req.getServerName();
        if (req.getServerPort() != 80) {
            host += ":" + req.getServerPort();
        }
        String url = req.getScheme() + "://" + host + contextPath;
        return url;
    }

    public void resetParameter(HttpServletRequest request, RedirectAttributes attr) {
        for (String key : request.getParameterMap().keySet()) {
            try {
                attr.addAttribute(key, URLEncoder.encode(request.getParameterMap().get(key)[0], "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                logger.error("当前参数:{},编码失败，请注意", key, e);
            }
        }
    }

}
