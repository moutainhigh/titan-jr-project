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
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.util.SpringContextUtil;

/**
 * 初始化交易平台收银台
 * @author Jerry
 * @date 2017年10月26日 上午11:22:31
 */
@WebServlet("/initTradingPlatformCashierdesk")
public class InitTradingPlatformCashierdesk extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory.getLog(InitTradingPlatformCashierdesk.class);
	
	private TitanCashierDeskService titanCashierDeskService;
    
    public InitTradingPlatformCashierdesk() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		log.info("=========initTradingPlatformCashierdesk start===========");
		try {
			
			BaseResponseDTO baseResponseDTO = titanCashierDeskService.initTradingPlatformCashierdesk();
			log.info("=========" + baseResponseDTO.getReturnMessage() + "===========");
			response.getWriter().println("result: " + baseResponseDTO.isResult() + ", returnMsg: " + baseResponseDTO.getReturnMessage());
			
		} catch (Exception e) {
			log.error("initTradingPlatformCashierdesk error：" + e);
			response.getWriter().println("error: ");
			response.getWriter().println(e);
		}
		log.info("=========initTradingPlatformCashierdesk end===========");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}
	
	@Override
	public void init() throws ServletException {
		titanCashierDeskService = (TitanCashierDeskService) SpringContextUtil.getBean("titanCashierDeskService");
        super.init();
	}

}
