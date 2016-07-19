package com.fangcang.titanjr.web.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.fangcang.titanjr.web.util.CommonConstant;

/**
 * 公共的controller
 * 需维护公共的方法，获取applicationContext等
 */
public class BaseController implements Serializable {
	protected String returnCode;
    /**
     * 返回的信息
     */
    protected String returnMessage;
    private static final Log log = LogFactory.getLog(BaseController.class);

    private static final long serialVersionUID = -2808661158418693093L;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    protected Map<String, Object> result = new HashMap<String, Object>();
    
    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession(true);
    }
    
    public String toJson(Object object){
    	return JSONSerializer.toJSON(object).toString();
    }
    
    public String toJson(){
    	if(result.isEmpty()){
    		putSysError("返回值错误");
    	}
    	return JSONSerializer.toJSON(result).toString();
    }
    
    public Map<String, Object> putSuccess(){
    	result.put("code", 1);
    	result.put("msg", "操作成功");
    	return result;
    	
    }
    
    public Map<String, Object> putSuccess(String msg){
    	result.put("code", 1);
    	result.put("msg",msg);
    	return result;
    	
    }
    public Map<String, Object> putSysError(){
    	result.put("code", -1);
    	result.put("msg", "系统错误");
    	return result;
    }
    
    public Map<String, Object> putSysError(String msg){
    	result.put("code", -1);
    	result.put("msg", msg);
    	return result;
    }
    /***
     * 获取saas当前登录用户名
     * @return
     */
    protected String getSAASLoginName(){
    	String op = (String)session.getAttribute(CommonConstant.SESSION_KEY_LOGIN_USER_NAME);
    	if(op==null){
    		return "";
    	}
    	return op;
    }
    /**
     * 登录者的机构编码（融数）
     * @return
     */
    public String getUserId(){
        Object userId = session.getAttribute(CommonConstant.SESSION_KEY_JR_USERID);
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
    	Object tfsUserId = session.getAttribute(CommonConstant.SESSION_KEY_JR_TFS_USERID);
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
    	Object userName = session.getAttribute(CommonConstant.SESSION_KEY_JR_LOGIN_UESRNAME);
    	if (null!= userName){
            return userName.toString();
        }
        return null;
    }
    
    public String getUserRealName(){
    	Object userRealName = session.getAttribute(CommonConstant.SESSION_KEY_LOGIN_USER_NAME);
    	if(null !=userRealName){
    		return userRealName.toString();
    	}
    	return null;
    }
}
