package com.titanjr.fop.api;

import com.titanjr.fop.constants.InterfaceConfigEnum;
import com.titanjr.fop.constants.InterfaceURlConfig;
import com.titanjr.fop.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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


    @RequestMapping(value = "/fopapi", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    public ModelAndView fopsdk(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        String methodName = request.getParameter("method");
        InterfaceConfigEnum configEnum = InterfaceConfigEnum.getURlConfigByKey(methodName);
        String url = getRequestBaseUrl(request) + InterfaceURlConfig.INTERFACE_URL_MAP.get(configEnum);
        resetParameter(request, attr);
        attr.addAttribute("signValid", request.getAttribute("signValid"));
        return new ModelAndView(new RedirectView(url));
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
