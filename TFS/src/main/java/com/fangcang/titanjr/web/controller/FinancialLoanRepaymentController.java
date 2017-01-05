package com.fangcang.titanjr.web.controller;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.NumberUtil;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.RepaymentAmountComputeRequest;
import com.fangcang.titanjr.dto.request.RepaymentLoanRequest;
import com.fangcang.titanjr.dto.request.SynLoanOrderRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.RepaymentAmountComputeResponse;
import com.fangcang.titanjr.dto.response.RepaymentLoanResponse;
import com.fangcang.titanjr.dto.response.SynLoanOrderResponse;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialLoanService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.util.StringUtil;

@Controller
@RequestMapping("loan/repayment")
public class FinancialLoanRepaymentController extends BaseController {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(FinancialLoanRepaymentController.class);
	@Resource
	private TitanFinancialLoanService financialLoanService;

	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;

	@Resource
	private TitanFinancialOrganService titanFinancialOrganService;
	
	/**
	 * 还款成功后进入还款成功界面
	 * @param orderNo
	 * @param amount
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/repayment-success", method = RequestMethod.GET)
	public String repaymentSuccess(String orderNo , String amount, Model model) {
		
		model.addAttribute("repaymentUserName", this.getUserRealName());
		model.addAttribute("repaymentOrderNo", orderNo);
		model.addAttribute("repaymentDate", DateUtil.sdf4.format(new Date()));
		model.addAttribute("repaymentAmount",amount);
		return "loan/loan-repayment/repayment-success";
	}
	
	/**
	 * 进入还款界面
	 * @param orderNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/repaymentPer", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode = { CommonConstant.ROLECODE_PAY_38 })
	public String repaymentPer(String orderNo, Model model) {

		log.info(" repayment per [ orderNo= " + orderNo + "]");

		if (!StringUtil.isValidString(orderNo)) {
			log.error("repayment per request param  is null");
			model.addAttribute("errormsg", "还款单号不能为空！");
			return "error";
		}

		model.addAttribute("orderNo", orderNo);

		FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
		organQueryRequest.setUserId(this.getUserId());
		FinancialOrganResponse financialOrgan = titanFinancialOrganService
				.queryFinancialOrgan(organQueryRequest);
		if (financialOrgan.isResult()) {
			FinancialOrganDTO organDTO = financialOrgan.getFinancialOrganDTO();
			model.addAttribute("orgName", organDTO.getOrgName());
			model.addAttribute("titanCode", organDTO.getTitanCode());
		}
		return "loan/loan-repayment/repayment";
	}
	
	/**
	 * 根据贷款单号计算出用户应还款的金额及还款顺序
	 * @param orderNo
	 * @param amount
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkRepaymentOrder", method = RequestMethod.GET)
	public String checkRepaymentOrder(String orderNo, String amount) {

		log.info("check repayment order [ orderNo= " + orderNo + " "
				+ "amount =" + amount + "]");

		if (!StringUtil.isValidString(amount)
				|| !StringUtil.isValidString(orderNo)) {
			log.error("check repayment request param  is null");
			putSysError("请求参数不能为空!");
			return toJson();
		}

		RepaymentAmountComputeRequest req = new RepaymentAmountComputeRequest();
		req.setAmount(Long.valueOf(NumberUtil.covertToCents(amount)));
		req.setOrderNo(orderNo);
		req.setOrgCode(this.getUserId());

		RepaymentAmountComputeResponse computeResponse = financialLoanService
				.repaymentAmountCompute(req);

		if (computeResponse == null || !computeResponse.isResult()) {
			log.error("repayment order result fail! ");
			putSysError("计算还款信息失败，有可能是未生成还款计划，建议放款第二天后才能进行还款！");
			return toJson();
		}

		String result = toJson(computeResponse);

		log.info("repayment order compute result [ rsp= " + result + "]");

		return result;
	}

	/**
	 * 用户任性了有钱了，主动还款
	 */
	@ResponseBody
	@RequestMapping(value = "/repayment", method = RequestMethod.GET)
	public String repayment(String orderNo, String amount, Model model) {

		log.info(" repayment order [ orderNo= " + orderNo + " " + "amount ="
				+ amount + "]");

		if (!StringUtil.isValidString(amount)
				|| !StringUtil.isValidString(orderNo)) {
			log.error(" repayment request param  is null");
			putSysError("还款金额及单号不能为空！");
			return toJson();
		}

		AccountBalanceRequest accountBalanceRequest = new AccountBalanceRequest();
		accountBalanceRequest.setUserid(this.getUserId());
		AccountBalanceResponse balanceResponse = titanFinancialAccountService
				.queryAccountBalance(accountBalanceRequest);

		if (balanceResponse == null
				|| !balanceResponse.isResult()
				|| !CollectionUtils.isNotEmpty(balanceResponse
						.getAccountBalance())) {
			log.error("repayment query balance result fail !");
			putSysError("查询余额信息失败，还款不能成功!");
			return toJson();
		}

		AccountBalance balance = balanceResponse.getAccountBalance().get(0);

		long rAmount = Long.valueOf(NumberUtil.covertToCents(amount));
		long cAmount = Long.valueOf(balance.getBalanceusable());

		if (rAmount > cAmount) {
			log.error("repayment amount error!");
			putSysError("还款金额不能大于当前余额!");
			return toJson();
		}

		RepaymentLoanRequest req = new RepaymentLoanRequest();
		req.setOrderNo(orderNo);
		req.setAmount(rAmount);
		req.setOrgCode(this.getUserId());
		RepaymentLoanResponse rsp = financialLoanService.repaymentLoan(req);

		if (rsp == null || !rsp.isResult()) {
			log.error("repayment result fail!");
			putSysError("还款失败，请稍后再试！");
			return toJson();
		}
		
		//还款成功后需要同步一下贷款单信息，如果同步失败建议手工同步
		SynLoanOrderRequest synReq = new SynLoanOrderRequest();
		synReq.setOrderNo(orderNo);
		synReq.setOrgCode(this.getUserId());
		
		SynLoanOrderResponse synRsp = financialLoanService
				.synLoanOrderInfo(synReq);
		
		if(!synRsp.isResult())
		{
			putSysError("还款成功但是同步贷款单信息失败，请稍后再试！");
			return toJson();
		}
		
		putSuccess();
		return toJson();
	}
}
