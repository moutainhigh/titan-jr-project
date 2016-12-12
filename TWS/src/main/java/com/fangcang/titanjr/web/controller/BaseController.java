package com.fangcang.titanjr.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONSerializer;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.fangcang.titanjr.web.util.WebConstant;

/**
 * 公共的controller
 * 
 */
public class BaseController implements Serializable {

    private static final long serialVersionUID = -2808661158418693093L;

    ThreadLocal<Map<String, Object>> resultLocal = new ThreadLocal<Map<String,Object>>();
    ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
    ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();
    
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
    	return JSONSerializer.toJSON(object).toString();
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
     * 登录者的机构编码（融数）userid
     * @return
     */
    public String getUserId(){
        Object userId = getSession().getAttribute(WebConstant.SESSION_KEY_JR_USERID);
        if (null!= userId){
            return userId.toString();
        }
        return null;
    }
    /**
     * 登录者金融的用户id
     * @return
     */
    public String getTfsUserId(){
    	Object tfsUserId = getSession().getAttribute(WebConstant.SESSION_KEY_JR_TFS_USERID);
        if (null!= tfsUserId){
            return tfsUserId.toString();
        }
        return null;
    }
    
    /***
     * 登录者金融的登录用户名
     * @return
     */
    public String getUserName(){
    	Object userName = getSession().getAttribute(WebConstant.SESSION_KEY_JR_LOGIN_UESRNAME);
    	if (null!= userName){
            return userName.toString();
        }
        return null;
    }
    /**
     * 当前登录用户的SAAS 姓名
     * @return
     */
    public String getUserRealName(){

    	return null;
    }
}
