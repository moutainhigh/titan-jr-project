package com.fangcang.titanjr.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fangcang.titanjr.common.util.ThreadPoolUtil;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialBaseInfoService;

@WebServlet(urlPatterns="/init/initCashDesk")
public class InitCashDesk extends HttpServlet{

	private TitanCashierDeskService titanCashierDeskService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			titanCashierDeskService.initttMallCashDesk();
			response.getWriter().write("初始化收银台数据正在执行，请勿重复提交");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.doGet(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public void init(){
		titanCashierDeskService = (TitanCashierDeskService)WebApplicationContextUtils
          .getRequiredWebApplicationContext(getServletContext()).getBean(
                  "titanCashierDeskService");
	}

}
