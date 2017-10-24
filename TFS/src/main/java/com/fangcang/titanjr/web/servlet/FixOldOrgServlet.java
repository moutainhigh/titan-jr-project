package com.fangcang.titanjr.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fangcang.titanjr.service.TitanFinancialOrganService;

@WebServlet(urlPatterns="/fixOldOrg")
public class FixOldOrgServlet extends HttpServlet {
	
	private TitanFinancialOrganService orgService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String msg ;
		try {
			orgService.fixOldOrg();
			msg = "数据处理完成";
		} catch (Exception e) {
			e.printStackTrace();
			msg = "数据处理失败";
		}
		resp.setContentType("text/html");
		resp.setCharacterEncoding("utf-8");
		resp.getWriter().write(msg);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	@Override
	public void init()
	{
		orgService = (TitanFinancialOrganService) WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean("titanFinancialOrganService");
	}
}
