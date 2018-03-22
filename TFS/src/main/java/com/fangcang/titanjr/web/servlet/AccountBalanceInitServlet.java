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

import com.fangcang.titanjr.dto.request.BalanceInfoRequest;
import com.fangcang.titanjr.service.TitanFinancialAccountService;

@WebServlet(urlPatterns="/accountBalanceInit")
public class AccountBalanceInitServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(AccountBalanceInitServlet.class);
	private TitanFinancialAccountService accountService;

	public AccountBalanceInitServlet() {
		super();
	}
	
	public void destroy() {
		super.destroy(); 
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String orgCode = (String)request.getParameter("orgCode");
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			if(orgCode==null||"".equals(orgCode)){
				accountService.initAllBalanceInfo();//批量同步账户时，只新增账户，不更新余额
			}else{
				BalanceInfoRequest balanceInfoRequest = new BalanceInfoRequest();
				balanceInfoRequest.setUserId(orgCode);
				accountService.synBalanceInfo(balanceInfoRequest,true);//单个账户同步时，则可以更新余额
			}
			
			response.getWriter().write("数据初始化完成");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			this.doGet(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public void init(){
		
		accountService = (TitanFinancialAccountService)WebApplicationContextUtils
          .getRequiredWebApplicationContext(getServletContext()).getBean(
                  "titanFinancialAccountService");
	}
}
