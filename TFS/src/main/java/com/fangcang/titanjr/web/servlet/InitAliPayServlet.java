package com.fangcang.titanjr.web.servlet;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fangcang.titanjr.common.util.ThreadPoolUtil;
import com.fangcang.titanjr.dto.bean.ItemBankDTO;
import com.fangcang.titanjr.dto.request.PaymentItemRequest;
import com.fangcang.titanjr.service.TitanCashierDeskService;

@WebServlet(urlPatterns="/alipay/InitAliPay")
public class InitAliPayServlet extends HttpServlet
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
		try 
		{
			response.setContentType("text/html");
			response.setCharacterEncoding("utf-8");
			PaymentItemRequest<ItemBankDTO> itemRequest = new PaymentItemRequest<ItemBankDTO>();
			itemRequest.setType("9");
			ItemBankDTO item = new ItemBankDTO();
			item.setBankmark("支付宝");
			item.setBankname("alipay");
			item.setCreator("system");
			item.setCreatetime(new Date());
			itemRequest.setItem(item);
			titanCashierDeskService.addModelOfPayment(itemRequest);
			response.getWriter().write("初始化数据正在执行");
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	@Override
	public void init()
	{//从容器中拿到这个bean
		titanCashierDeskService = (TitanCashierDeskService) WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext()).getBean("titanCashierDeskService");
	}
	
}
