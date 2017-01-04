package com.fangcang.titanjr.web.controller.admin;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.LoanCreditStatusEnum;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.request.AuditCreditOrderRequest;
import com.fangcang.titanjr.dto.request.GetAuditEvaluationRequest;
import com.fangcang.titanjr.dto.request.GetCreditInfoRequest;
import com.fangcang.titanjr.dto.request.GetCreditOrderCountRequest;
import com.fangcang.titanjr.dto.request.QueryPageCreditCompanyInfoRequest;
import com.fangcang.titanjr.dto.response.AuditCreidtOrderResponse;
import com.fangcang.titanjr.dto.response.GetAuditEvaluationResponse;
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
		countRequest.setStatus(LoanCreditStatusEnum.TO_CHECK.getStatus());
		model.addAttribute("pageCreditCompanyInfoDTO", queryPageCreditCompanyInfoResponse.getPageCreditCompanyInfoDTO());
		model.addAttribute("creditOrderToCheckCount", loanCreditService.getCreditOrderCount(countRequest).getCount());
		
		
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
		GetAuditEvaluationRequest getAuditEvaluationRequest = new GetAuditEvaluationRequest();
		getAuditEvaluationRequest.setOrderNo(orderNo);
		GetAuditEvaluationResponse getAuditEvaluationResponse = loanCreditService.getAuditEvaluationInfo(getAuditEvaluationRequest);
		
		
		model.addAttribute("getCreditInfoResponse", getCreditInfoResponse);
		model.addAttribute("operatorName", getSAASLoginName());
		if(getAuditEvaluationResponse.getCreditOpinionBean()!=null){
			getAuditEvaluationResponse.getCreditOpinionBean().setContent(Tools.replaceEnterKeyHTML(getAuditEvaluationResponse.getCreditOpinionBean().getContent()));
			model.addAttribute("creditOpinionBean", getAuditEvaluationResponse.getCreditOpinionBean());
		}
		
		GetCreditOrderCountRequest countRequest = new GetCreditOrderCountRequest();
		countRequest.setStatus(LoanCreditStatusEnum.TO_CHECK.getStatus());
		
		model.addAttribute("creditOrderToCheckCount", loanCreditService.getCreditOrderCount(countRequest).getCount());
		
		return "admin/loan/credit-order-detail";
	}
	
	/**
	 * 审核授信申请单
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/check-credit-order")
	public String checkCreditOrder(String orderNo,Integer auditResult,String content){
		if(StringUtils.isEmpty(orderNo)||auditResult==null){
			return toJson(putSysError("参数不能为空"));
		}
		AuditCreditOrderRequest auditCreidtOrderRequest = new AuditCreditOrderRequest();
		auditCreidtOrderRequest.setOperator(getSAASLoginName());
		auditCreidtOrderRequest.setOrderNo(orderNo);
		auditCreidtOrderRequest.setCheckState(auditResult);
		auditCreidtOrderRequest.setContent(content);
		auditCreidtOrderRequest.setOperator(getSAASLoginName());
		AuditCreidtOrderResponse auditCreidtOrderResponse = loanCreditService.auditCreditOrder(auditCreidtOrderRequest);
		if(auditCreidtOrderResponse.isResult()){
			putSuccess("审核成功");
		}else{
			putSysError(auditCreidtOrderResponse.getReturnMessage());
		}
		return toJson();	
	}
	
	/***
	 * 提交复审
	 * @param orderNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/check-commit-info")
	public String checkCommitInfo(String orderNo){
		if(StringUtils.isEmpty(orderNo)){
			return toJson(putSysError("参数不能为空"));
		}
		loanCreditService.asynPushCreditInfo(orderNo);
		putSuccess("正在重新提交复审,请稍后关注审核状态");
		return toJson();	
	}
}
