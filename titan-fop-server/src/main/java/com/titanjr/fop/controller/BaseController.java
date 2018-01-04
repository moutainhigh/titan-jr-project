package com.titanjr.fop.controller;

import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.response.FopResponse;
import net.sf.json.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by zhaoshan on 2017/12/21.
 */
public class BaseController {

    private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();

    ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        requestLocal.set(request);
        responseLocal.set(response);
    }

    protected HttpServletRequest getRequest() {

        return requestLocal.get();
    }

    protected HttpServletResponse getResponse() {

        return responseLocal.get();
    }

    protected HttpSession getSession() {
        return getRequest().getSession();
    }


    public String toJson(Object object) {
        return JSONSerializer.toJSON(object).toString();
    }

    //签名和session处理
    public String validRequestSign(HttpServletRequest request, FopResponse fopResponse) {
        if (null != request.getParameter("signValid") &&
                request.getParameter("signValid").equals("false")) {
            fopResponse.setErrorCode(ReturnCodeEnum.CODE_SIGN_ERROR.getCode());
            fopResponse.setMsg(ReturnCodeEnum.CODE_SIGN_ERROR.getMsg());
            return toJson(fopResponse);
        }
        if (null != request.getParameter("sessionValid") &&
                request.getParameter("sessionValid").equals("false")){
            fopResponse.setErrorCode(ReturnCodeEnum.CODE_SESSION_ERROR.getCode());
            fopResponse.setMsg(ReturnCodeEnum.CODE_SESSION_ERROR.getMsg());
            return toJson(fopResponse);
        }
        return null;
    }

    //request转换为请求对象处理
    public String getConvertErrorResp(FopResponse fopResponse){
        logger.error("request转换为请求参数错误");
        fopResponse.setErrorCode(ReturnCodeEnum.CODE_CONVERT_ERROR.getCode());
        fopResponse.setMsg(ReturnCodeEnum.CODE_CONVERT_ERROR.getMsg());
        return toJson(fopResponse);
    }

    //统一系统异常
    public String getSysErrorResp(FopResponse fopResponse){
        logger.error("系统操作异常");
        fopResponse.setErrorCode(ReturnCodeEnum.CODE_SYS_ERROR.getCode());
        fopResponse.setMsg(ReturnCodeEnum.CODE_SYS_ERROR.getMsg());
        return toJson(fopResponse);
    }
}
