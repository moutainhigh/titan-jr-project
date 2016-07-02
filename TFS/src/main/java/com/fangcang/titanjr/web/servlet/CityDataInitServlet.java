package com.fangcang.titanjr.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fangcang.titanjr.common.util.ThreadPoolUtil;
import com.fangcang.titanjr.service.TitanFinancialBaseInfoService;

@WebServlet(urlPatterns="/CityInfo/cityDataInitServlet")
public class CityDataInitServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(CityDataInitServlet.class);
	private TitanFinancialBaseInfoService titanFinancialBaseInfoService;

	public CityDataInitServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy(); 
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			CityInfoThread cityInfoThread = new CityInfoThread(titanFinancialBaseInfoService);
			ThreadPoolUtil.excute(cityInfoThread);
			response.getWriter().write("初始化数据正在执行");
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
		
		titanFinancialBaseInfoService = (TitanFinancialBaseInfoService)WebApplicationContextUtils
          .getRequiredWebApplicationContext(getServletContext()).getBean(
                  "titanFinancialBaseInfoService");
	}

}
