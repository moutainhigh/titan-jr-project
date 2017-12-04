package com.titanjr.checkstand.controller;

import com.fangcang.titanjr.common.util.Tools;
import com.titanjr.checkstand.request.TitanPayCallbackRequest;
import com.titanjr.checkstand.util.DTOBuilderUtil;

import net.sf.json.JSONSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * 公共的controller
 * 
 */
public class BaseController implements Serializable {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -3544543746592691072L;

	private final static Logger logger = LoggerFactory.getLogger(BaseController.class);

    private ThreadLocal<Map<String, Object>> resultLocal = new ThreadLocal<Map<String,Object>>();
    private ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
    private ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
    
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
    	requestLocal.set(request);
    	responseLocal.set(response);
   }
    
    protected HttpServletRequest getRequest(){
    	
    	return requestLocal.get();
    }
    
    protected HttpServletResponse getResponse(){
    	
    	return responseLocal.get();
    }
    
    protected HttpSession getSession(){
    	return getRequest().getSession();
    }
    
    
    public String toJson(Object object){
    	return Tools.gsonToString(object);
    }
    
    public String toJson(){
    	
    	 Map<String, Object> result = resultLocal.get();
    	if(result == null ){
    		putSysError("返回值错误");
    	}
    	return JSONSerializer.toJSON(result).toString();
    }
    
    public Map<String, Object> putSuccess(){
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("code", 1);
    	result.put("msg", "操作成功");
    	resultLocal.set(result);
    	return result;
    	
    }
    
    public Map<String, Object> putSuccess(String msg){
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("code", 1);
    	result.put("msg",msg);
    	resultLocal.set(result);
    	return result;
    	
    }
    
    public Map<String, Object> putSuccess(String msg , Object data){
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("code", 1);
    	result.put("msg",msg);
    	result.put("data",data);
    	resultLocal.set(result);
    	return result;
    	
    }
    
    public Map<String, Object> putSysError(){
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("code", -1);
    	result.put("msg", "系统错误");
    	resultLocal.set(result);
    	return result;
    }
    
    public Map<String, Object> putSysError(String msg){
    	Map<String, Object> result = new HashMap<String, Object>();
    	result.put("code", -1);
    	result.put("msg", msg);
    	resultLocal.set(result);
    	return result;
    }

    /**
     * 重定向时设置参数
     * @param request
     * @param attr
     */
    public void resetParameter(HttpServletRequest request,RedirectAttributes attr){
        for (String key : request.getParameterMap().keySet()){
			try {
				attr.addAttribute(key, URLEncoder.encode(request.getParameterMap().get(key)[0],"UTF-8"));
			} catch (UnsupportedEncodingException e) {
				logger.error("当前参数:{}编码失败，请注意", key, e);
			}
		}
    }
    

    /**
     * 支付失败，回调泰坦金融收银台
     * @author Jerry
     * @date 2017年11月27日 上午10:48:00
     */
    protected String payFailedCallback(Model model){
    	TitanPayCallbackRequest payCallbackRequest = DTOBuilderUtil.getPayFailedCallbackRequest();
    	model.addAttribute("payCallbackRequest", payCallbackRequest);
    	return "callbackTitanjr/payFailedCallbackPage";
    }

}
