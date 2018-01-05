package com.titanjr.fop.util;

import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.controller.BaseController;
import com.titanjr.fop.response.FopResponse;
import net.sf.json.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2018/1/5.
 */
public class ResponseUtils {

    private final static Logger logger = LoggerFactory.getLogger(ResponseUtils.class);

    //签名和session处理
    public static String validRequestSign(HttpServletRequest request, FopResponse fopResponse) {
        if (null != request.getParameter("signValid") &&
                request.getParameter("signValid").equals("false")) {
            fopResponse.setErrorCode(ReturnCodeEnum.CODE_SIGN_ERROR.getCode());
            fopResponse.setMsg(ReturnCodeEnum.CODE_SIGN_ERROR.getMsg());
            return JSONSerializer.toJSON(fopResponse).toString();
        }
        if (null != request.getParameter("sessionValid") &&
                request.getParameter("sessionValid").equals("false")){
            fopResponse.setErrorCode(ReturnCodeEnum.CODE_SESSION_ERROR.getCode());
            fopResponse.setMsg(ReturnCodeEnum.CODE_SESSION_ERROR.getMsg());
            return JSONSerializer.toJSON(fopResponse).toString();
        }
        return null;
    }

    //request转换为请求对象处理
    public static String getConvertErrorResp(FopResponse fopResponse){
        logger.error("request转换为请求参数错误");
        fopResponse.setErrorCode(ReturnCodeEnum.CODE_CONVERT_ERROR.getCode());
        fopResponse.setMsg(ReturnCodeEnum.CODE_CONVERT_ERROR.getMsg());
        return JSONSerializer.toJSON(fopResponse).toString();
    }

    //统一系统异常
    public static String getSysErrorResp(FopResponse fopResponse){
        logger.error("系统操作异常");
        fopResponse.setErrorCode(ReturnCodeEnum.CODE_SYS_ERROR.getCode());
        fopResponse.setMsg(ReturnCodeEnum.CODE_SYS_ERROR.getMsg());
        return JSONSerializer.toJSON(fopResponse).toString();
    }
}
