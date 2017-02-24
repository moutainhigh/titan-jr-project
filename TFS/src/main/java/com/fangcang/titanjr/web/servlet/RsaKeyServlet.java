package com.fangcang.titanjr.web.servlet;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpRequest;

import com.fangcang.titanjr.common.util.rsa.JsRSAUtil;
import com.fangcang.titanjr.web.controller.FinancialOrganController;



public class RsaKeyServlet extends HttpServlet{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(RsaKeyServlet.class);

	
	public RsaKeyServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); 
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init(ServletConfig config) throws ServletException {
		try
		{
			ServletContext application = config.getServletContext();
			if(application.getAttribute("publicKey")==null){
				log.info("【----------RSA save in Application--------------start】");
				
				RSAPublicKey rsap = (RSAPublicKey) JsRSAUtil.generateKeyPair().getPublic();
				//RSAPublicKey rsap = (RSAPublicKey)JsRSAUtil.getKeyPair().getPublic();
				PrivateKey privateKey =	JsRSAUtil.getKeyPair().getPrivate();
//				log.info("【----------RSA私钥:--------------】"+privateKey);
				PublicKey publicKey =	JsRSAUtil.getKeyPair().getPublic();
//				log.info("【----------RSA公钥:--------------】"+publicKey);
				application.setAttribute("module", rsap.getModulus().toString(16));
				application.setAttribute("empoent", rsap.getPublicExponent().toString(16));
				application.setAttribute("privateKey", privateKey);
				application.setAttribute("publicKey", publicKey);
				log.info("【----------将RSA秘钥读取出来放到上下文作用域中--------------完毕】");
			}			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
