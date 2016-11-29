package com.fangcang.titanjr.web.controller.admin;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fangcang.titanjr.common.enums.LoanCreditOrderEnum;
import com.fangcang.titanjr.dto.request.GetCreditInfoRequest;
import com.fangcang.titanjr.dto.request.GetCreditOrderCountRequest;
import com.fangcang.titanjr.dto.request.QueryPageCreditCompanyInfoRequest;
import com.fangcang.titanjr.dto.response.GetCreditInfoResponse;
import com.fangcang.titanjr.dto.response.PageCreditCompanyInfoResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
import com.fangcang.titanjr.web.controller.BaseController;
import com.fangcang.titanjr.web.pojo.CreditOrderPojo;
import com.fangcang.util.StringUtils;

/**
 * 信贷控制器
 * @author luoqinglong
 * @2016年11月15日
 */
@Controller
@RequestMapping("/admin")
public class MLoanController extends BaseController{
	private static final Log logger = LogFactory.getLog(MLoanController.class);
	
	@Resource
	private TitanFinancialLoanCreditService loanCreditService;
	
	/**
	 * 查授信申请
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/credit-order")
	public String queryCreditOrder(Model model){
		
		return "admin/loan/credit-order";
	}
	
	/**
	 * 表格数据
	 * @param creditOrderPojo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/credit-order-table")
	public String creditOrderTable(CreditOrderPojo creditOrderPojo,Model model){
		if(!StringUtils.isValidString(creditOrderPojo.getCompanyName())){
			creditOrderPojo.setCompanyName(null);
		}
		if(!StringUtils.isValidString(creditOrderPojo.getContactName())){
			creditOrderPojo.setContactName(null);
		}
		
		QueryPageCreditCompanyInfoRequest queryCreditCompanyInfoRequest  = new QueryPageCreditCompanyInfoRequest();
		
		queryCreditCompanyInfoRequest.setName(creditOrderPojo.getCompanyName());
		queryCreditCompanyInfoRequest.setContactName(creditOrderPojo.getContactName());
		queryCreditCompanyInfoRequest.setStatus(creditOrderPojo.getStatus());
		queryCreditCompanyInfoRequest.setCurrentPage(creditOrderPojo.getPageNo());
		
		PageCreditCompanyInfoResponse queryPageCreditCompanyInfoResponse = loanCreditService.queryPageCreditCompanyInfo(queryCreditCompanyInfoRequest);
		GetCreditOrderCountRequest countRequest = new GetCreditOrderCountRequest();
		countRequest.setStatus(LoanCreditOrderEnum.Status.TO_CHECK.getValue());
		
		model.addAttribute("pageCreditCompanyInfoDTO", queryPageCreditCompanyInfoResponse.getPageCreditCompanyInfoDTO());
		model.addAttribute("creditOrderCount", loanCreditService.getCreditOrderCount(countRequest).getCount());
		
		
		return "admin/loan/credit-order-table";
	}
	
	/**
	 * 查授信申请
	 * @return
	 */
	@RequestMapping(value = "/credit-order-detail")
	public String creditOrderDetail(String orderNo,String opt, Model model){
		if(!StringUtils.isValidString(orderNo)){
			model.addAttribute("errormsg", "参数错误");
			return "error";
		}
		GetCreditInfoRequest getCreditInfoRequest = new GetCreditInfoRequest();
		getCreditInfoRequest.setOrderNo(orderNo);
		
		GetCreditInfoResponse getCreditInfoResponse = loanCreditService.getCreditOrderInfo(getCreditInfoRequest);
		model.addAttribute("getCreditInfoResponse", getCreditInfoResponse);
		
		return "admin/loan/credit-order-detail";
	}
	
	/**
	 * 审核授信申请单
	 * @return
	 */
	@RequestMapping(value = "/check-credit-order")
	public String checkCreditOrder(){
		
		
		return "";	
	}
}
