package com.fangcang.titanjr.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fangcang.titanjr.service.TitanCashierDeskService;

@WebServlet(urlPatterns="/loan/InitLoanCashDesk")
public class InitLoanCashDeskServlet extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private TitanCashierDeskService titanCashierDeskService;

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		try {
			titanCashierDeskService.executeLoanDeskInit();
			
			response.getWriter().write("初始化成功!");
		} catch (Exception e) {
			
			response.getWriter().write("初始化失败了哈!");
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void init()
	{//从容器中拿到这个bean
		titanCashierDeskService = (TitanCashierDeskService) WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean("titanCashierDeskService");
	}
	
}
