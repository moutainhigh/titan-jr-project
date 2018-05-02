package com.fangcang.titanjr.web.annotation;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoshan on 2017/12/15.
 */
public class AnnotationHandlerMethodExceptionResolver extends ExceptionHandlerExceptionResolver {

    private String defaultErrorView;

    @Override
    protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response, HandlerMethod handlerMethod, Exception exception) {

        if ( handlerMethod == null ){
            return null;
        }

        Method method = handlerMethod.getMethod();

        if (method == null){
            return null;
        }



        ModelAndView returnValue =  super.doResolveHandlerMethodException(request, response, handlerMethod, exception);

        ResponseBody responseBodyAnno = AnnotationUtils.findAnnotation(method, ResponseBody.class);

        if ( responseBodyAnno != null ){
            try{
                ResponseStatus responseStatusAnno = AnnotationUtils.findAnnotation(method, ResponseStatus.class);
                if (responseStatusAnno != null ){
                    HttpStatus responseStatus = responseStatusAnno.value();
                    String reason = responseStatusAnno.reason();
                    if (!StringUtils.hasText(reason)){
                        response.setStatus(responseStatus.value());
                    } else {
                        try {
                            response.sendError(responseStatus.value(), reason);
                        } catch (IOException e) {

                        }
                    }
                }
//                if (returnValue == null){
//                    ServiceResponse
//                }
                return handleResponseBody(returnValue,request,response);
            } catch (Exception e){

            }
        }

        return null;
    }


    private ModelAndView handleResponseBody(ModelAndView returnValue, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map value = returnValue.getModelMap();
        HttpInputMessage inputMessage = new ServletServerHttpRequest(request);
        List<MediaType> acceptMediaTypes = inputMessage.getHeaders().getAccept();
        if (acceptMediaTypes.isEmpty()){
            acceptMediaTypes = Collections.singletonList(MediaType.ALL);
        }
        MediaType.sortByQualityValue(acceptMediaTypes);
        return null;
    }


    public String getDefaultErrorView() {
        return defaultErrorView;
    }

    public void setDefaultErrorView(String defaultErrorView) {
        this.defaultErrorView = defaultErrorView;
    }
}
