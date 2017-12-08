package com.fangcang.titanjr.pay.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.service.TitanFinancialRateService;
import com.fangcang.util.SpringContextUtil;

/**
 * 初始化房仓费率配置，初始化基准费率
 * 先删除后插入，可重复执行
 * @author Jerry
 * @date 2017年11月22日 上午10:15:31
 */
@WebServlet("/initRateConfig")
public class InitRateConfig extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Log logger = LogFactory.getLog(InitRateConfig.class);
	
	private TitanFinancialRateService titanFinancialRateService;
    
    public InitRateConfig() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.info("=========initRateConfig start===========");
		try {
			
			BaseResponseDTO baseResponseDTO = titanFinancialRateService.initRateData();
			
			response.setContentType("text/html;charset=utf-8"); 
			response.setCharacterEncoding("utf-8");
			response.getWriter().println("result: " + baseResponseDTO.isResult() + ", returnMsg: " + baseResponseDTO.getReturnMessage());
			
		} catch (Exception e) {
			logger.error("initRateConfig throws exception：", e);
			response.getWriter().println("error: ");
			response.getWriter().println(e.getMessage());
		}
		logger.info("=========initRateConfig end===========");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	@Override
	public void init() throws ServletException {
		titanFinancialRateService = (TitanFinancialRateService) SpringContextUtil.getBean("titanFinancialRateService");
        super.init();
	}

}
