package com.fangcang.titanjr.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.fangcang.util.SpringContextUtil;

@WebServlet(urlPatterns="/BatchUpdatePersonalCard")
public class BatchUpatePersonalCard extends HttpServlet
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	 @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 resp.setContentType("text/html;charset=UTF-8");
		 resp.setCharacterEncoding("UTF-8");
		 try {
			TitanFinancialBankCardService titanFinancialBankCardService = (TitanFinancialBankCardService) SpringContextUtil
					.getBean("titanFinancialBankCardService");
			BaseResponseDTO baseResponseDTO = titanFinancialBankCardService.batchUpdatePersonalCard();
			
			resp.getWriter().write("result：" + baseResponseDTO.isResult() + "，returnMsg：" + baseResponseDTO.getReturnMessage());
		 } catch (Exception e) {
			
			resp.getWriter().write("批量更新对私卡信息异常");
			e.printStackTrace();
		 }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
	
}
