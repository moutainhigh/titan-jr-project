package com.fangcang.titanjr.web.servlet;

import java.io.IOException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fangcang.titanjr.common.util.rsa.JsRSAUtil;
import com.fangcang.titanjr.service.TitanFinancialBaseInfoService;
import com.fangcang.titanjr.service.TitanFinancialRateService;

@WebServlet(urlPatterns="/servlet/initRateData")
public class InitRateData extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(InitRateData.class);
	private TitanFinancialRateService titanFinancialRateService;
	
	
	public InitRateData() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(titanFinancialRateService == null)
		{
			titanFinancialRateService = (TitanFinancialRateService) WebApplicationContextUtils
					.getRequiredWebApplicationContext(request.getServletContext())
					.getBean("titanFinancialRateService");
		}
		titanFinancialRateService.initRateData();

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}	
	public void doService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init(ServletConfig config) throws ServletException {
	}
}
