package com.fangcang.titanjr.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fangcang.titanjr.service.TitanCashierDeskService;

/**
 * 机构增加微信公众号收银台
 * @author luoqinglong
 *
 */
@WebServlet(urlPatterns="/addWxPublicCashDesk")
public class InitWxPublicCashDeskServlet extends HttpServlet
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
			titanCashierDeskService.executeWxPublicInit();
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("微信公众号收银员增加成功!");
		} catch (Exception e) {
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("微信公众号收银员增加失败了哈!");
			e.printStackTrace();
		}
	}
	
	
	@Override
	public void init()
	{//从容器中拿到这个bean
		titanCashierDeskService = (TitanCashierDeskService) WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean("titanCashierDeskService");
	}
	
}
