package com.fangcang.titanjr.pay.controller;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

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

}
