package com.fangcang.titanjr.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.LoanOrderStatusEnum;
import com.fangcang.titanjr.common.enums.LoanProductEnum;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoListRequest;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoRequest;
import com.fangcang.titanjr.dto.request.GetOrgLoanStatInfoRequest;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoListResponse;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoResponse;
import com.fangcang.titanjr.dto.response.GetOrgLoanStatInfoResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanService;
import com.fangcang.titanjr.service.TitanSysconfigService;
import com.fangcang.titanjr.web.pojo.LoanQueryConditions;
import com.fangcang.util.StringUtil;

/**
 * 贷款请求控制器
 * 
 * @author wengxitao
 */
@Controller
@RequestMapping("loan")
public class FinancialLoanController extends BaseController {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(FinancialLoanCreditController.class);

	@Resource
	private TitanFinancialLoanService financialLoanService;

	@Resource
	private TitanSysconfigService sysconfigService;

	private final static Map<String, Object> statusMap = new HashMap<String, Object>();

	static {
		statusMap.put("loan-all-status", LoanOrderStatusEnum.values());
		statusMap.put("loan-all-orderby", "createTime,status");

		statusMap.put("loan-audit-status",
				new LoanOrderStatusEnum[] { LoanOrderStatusEnum.LOAN_REQ_ING });
		statusMap.put("loan-audit-orderby", "createTime");

		statusMap.put("loan-over-status",
				new LoanOrderStatusEnum[] { LoanOrderStatusEnum.LOAN_FINISH });
		statusMap.put("loan-over-orderby", "createTime");

		statusMap.put("loan-payment-status",
				new LoanOrderStatusEnum[] { LoanOrderStatusEnum.HAVE_LOAN,
						LoanOrderStatusEnum.LOAN_EXPIRY });
		statusMap.put("loan-payment-orderby", "createTime");

		statusMap
				.put("" + LoanProductEnum.ROOM_PACK.getCode(), "loan-roompack");
		statusMap.put("" + LoanProductEnum.OPERACTION.getCode(), "");

	}

	@RequestMapping(value = "/getLoanDetailsInfo", method = RequestMethod.GET)
	public String getLoanDetailsInfo(String orderNo, Model model) {
		if (!StringUtil.isValidString(orderNo)) {
			log.error("loan detail orderNo is null");
			model.addAttribute("errormsg", "查询贷款单号不能为空!");
			return "error";
		}

		GetLoanOrderInfoRequest req = new GetLoanOrderInfoRequest();
		req.setOrgCode(this.getUserId());
		req.setOrderNo(orderNo);

		GetLoanOrderInfoResponse infoResponse = financialLoanService
				.getLoanOrderInfo(req);

		if (infoResponse == null || infoResponse.getApplyOrderInfo() == null) {
			log.error(" get loan info response is null");
			model.addAttribute("errormsg", "查询贷款信息失败，请稍后再试!");
			return "error";
		}
		model.addAttribute("loanOrderInfo", infoResponse.getApplyOrderInfo());
		if (infoResponse.getApplyOrderInfo() != null) {
			model.addAttribute("loanSpecInfo", infoResponse.getApplyOrderInfo()
					.getLoanSpec());
		}

		return "/loan/product-info/"
				+ statusMap.get(String.valueOf(infoResponse.getApplyOrderInfo()
						.getProductType()));
	}

	@ResponseBody
	@RequestMapping(value = "/loanStatInfo", method = RequestMethod.GET)
	public String getLoanStatInfo() {
		log.info("get loan stat info ");
		GetOrgLoanStatInfoRequest req = new GetOrgLoanStatInfoRequest();

		req.setOrgCode(this.getUserId());

		GetOrgLoanStatInfoResponse loanStatInfoResponse = financialLoanService
				.getOrgLoanStatInfo(req);

		log.info("loanStatInfo = "
				+ JsonConversionTool.toJson(loanStatInfoResponse));

		return toJson(loanStatInfoResponse.getOrgLoanStatInfo());
	}

	@RequestMapping(value = "/getLoanInfoList", method = RequestMethod.GET)
	public String getLoanInfoList(LoanQueryConditions loanQueryConditions,
			Model model) {

		GetLoanOrderInfoListRequest req = new GetLoanOrderInfoListRequest();

		// 设置查询条件
		req.setOrgCode(this.getUserId());

		if (StringUtil.isValidString(loanQueryConditions.getCurrPage())) {
			req.setCurrentPage(Integer.parseInt(loanQueryConditions
					.getCurrPage()));
		}

		req.setBeginActualRepaymentDate(loanQueryConditions
				.getBeginActualRepaymentDate());
		req.setBeginCreateTime(loanQueryConditions.getBeginCreateTime());
		req.setBeginLastRepaymentDate(loanQueryConditions
				.getBeginLastRepaymentDate());
		req.setBeginRelMoneyTime(loanQueryConditions.getBeginRelMoneyTime());

		req.setEndActualRepaymentDate(loanQueryConditions
				.getEndActualRepaymentDate());
		req.setEndCreateTime(loanQueryConditions.getEndCreateTime());
		req.setEndLastRepaymentDate(loanQueryConditions
				.getEndLastRepaymentDate());
		req.setEndRelMoneyTime(loanQueryConditions.getEndRelMoneyTime());

		if (StringUtil.isValidString(loanQueryConditions.getProductType())) {
			req.setProductEnum(LoanProductEnum.getEnumByKey(Integer
					.parseInt(loanQueryConditions.getProductType())));
		}

		List<LoanOrderStatusEnum> statusList = new ArrayList<LoanOrderStatusEnum>(
				Arrays.asList((LoanOrderStatusEnum[]) statusMap
						.get(loanQueryConditions.getPageKey() + "-status")));

		if (StringUtil.isValidString(loanQueryConditions.getLoanStatus())) {
			LoanOrderStatusEnum tempEnum = LoanOrderStatusEnum
					.getEnumByStatus(Integer.parseInt(loanQueryConditions
							.getLoanStatus()));
			if (tempEnum != null) {
				statusList.clear();
				statusList.add(tempEnum);
			}
		}
		// 设置要过滤的状态
		req.setOrderStatusEnum(statusList.toArray(new LoanOrderStatusEnum[0]));
		// 设置排序条件
		Object orderBy = statusMap.get(loanQueryConditions.getPageKey()
				+ "-orderby");
		if (orderBy != null) {
			req.setOrderBy(orderBy.toString());
		}
		GetLoanOrderInfoListResponse infoListResponse = financialLoanService
				.getLoanOrderInfoList(req);

		model.addAttribute("totalCount", infoListResponse.getTotalCount());
		model.addAttribute("pageSize", infoListResponse.getPageSize());
		model.addAttribute("currentPage", infoListResponse.getCurrentPage());
		model.addAttribute("loanInfoList", infoListResponse.getApplyOrderInfo());

		return "/loan/loan-list/" + loanQueryConditions.getPageKey();
	}

}
