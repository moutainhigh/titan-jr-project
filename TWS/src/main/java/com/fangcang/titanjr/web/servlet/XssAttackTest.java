package com.fangcang.titanjr.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Xss攻击测试
 * @author jerry
 */
@WebServlet(urlPatterns="/xssAttackTest")
public class XssAttackTest extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	String paramData = req.getParameter("paramData");
		String headerData = req.getHeader("headerData");
		
		resp.getWriter().print("paramData--->" + paramData + "\n" + "headerData--->" + headerData);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	BufferedReader br = req.getReader();
    	String str, wholeStr = "";
    	while((str = br.readLine()) != null){
    	wholeStr += str;
    	}
    	resp.getWriter().println("paramData--->" + wholeStr);
    	
    	String headerData = req.getHeader("headerData");
		
    	resp.getWriter().print("headerData--->" + headerData);
        
    }
}
