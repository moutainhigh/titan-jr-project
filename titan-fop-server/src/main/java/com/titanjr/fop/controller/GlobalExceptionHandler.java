package com.titanjr.fop.controller;

import com.fangcang.exception.DaoException;
import com.fangcang.exception.ServiceException;
import com.titanjr.fop.response.FopResponse;
import net.sf.json.JSONSerializer;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2018/1/24.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public String serviceErrorHandler(HttpServletRequest req, ServiceException ex) throws Exception {
        FopResponse fopResponse = new FopResponse();
        fopResponse.setErrorCode("Service Exception");
        fopResponse.setMsg(ex.getMessage());
        String json = JSONSerializer.toJSON(fopResponse).toString();
        return json;
    }

    @ExceptionHandler(value = DaoException.class)
    @ResponseBody
    public String daoErrorHandler(HttpServletRequest req, DaoException ex) throws Exception {
        FopResponse fopResponse = new FopResponse();
        fopResponse.setErrorCode("Dao Exception");
        fopResponse.setMsg(ex.getMessage());
        String json = JSONSerializer.toJSON(fopResponse).toString();
        return json;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String daoErrorHandler(HttpServletRequest req, Exception ex) throws Exception {
        FopResponse fopResponse = new FopResponse();
        fopResponse.setErrorCode("Exception");
        fopResponse.setMsg(ex.getMessage());
        String json = JSONSerializer.toJSON(fopResponse).toString();
        return json;
    }

}
