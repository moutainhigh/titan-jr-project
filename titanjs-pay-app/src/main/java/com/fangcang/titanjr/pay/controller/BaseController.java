package com.fangcang.titanjr.pay.controller;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONSerializer;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;

/**
 * 公共的controller
 * 
 */
public class BaseController implements Serializable {

	private static final long serialVersionUID = 1L;

	ThreadLocal<HttpServletRequest> requestLocal = new ThreadLocal<HttpServletRequest>();
	ThreadLocal<HttpServletResponse> responseLocal = new ThreadLocal<HttpServletResponse>();

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request,
			HttpServletResponse response) {
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

	public String toMsgJson(TitanMsgCodeEnum codeEnum, Object appendData)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", codeEnum.getCode());
		map.put("resultMsg", codeEnum.getResMsg());
		map.put("data", appendData);
		return JSONSerializer.toJSON(map).toString();
	}
	
	
	public String toMsgJson(TitanMsgCodeEnum codeEnum)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", codeEnum.getCode());
		map.put("resultMsg", codeEnum.getResMsg());
		return JSONSerializer.toJSON(map).toString();
	}

}
