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
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoListRequest;
import com.fangcang.titanjr.dto.request.GetOrgLoanStatInfoRequest;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoListResponse;
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

	private final static Map<String, LoanOrderStatusEnum[]> statusMap = new HashMap<String, LoanOrderStatusEnum[]>();

	static {
		statusMap.put("loan-all", LoanOrderStatusEnum.values());
		statusMap.put("loan-audit",
				new LoanOrderStatusEnum[] { LoanOrderStatusEnum.LOAN_REQ_ING });
		statusMap.put("loan-over",
				new LoanOrderStatusEnum[] { LoanOrderStatusEnum.LOAN_FINISH });

		statusMap.put("loan-payment",
				new LoanOrderStatusEnum[] { LoanOrderStatusEnum.HAVE_LOAN,
						LoanOrderStatusEnum.LOAN_EXPIRY });
	}

	/**
	 * 进入贷款主页
	 */
	@RequestMapping(value = "/loanMain", method = RequestMethod.GET)
	public String loanMain() {
		return "/loan/loan-main";
	}

	@ResponseBody
	@RequestMapping(value = "/loanStatInfo", method = RequestMethod.GET)
	public String getLoanStatInfo() {

		GetOrgLoanStatInfoRequest req = new GetOrgLoanStatInfoRequest();

		req.setOrgCode(this.getUserId());

		GetOrgLoanStatInfoResponse loanStatInfoResponse = financialLoanService
				.getOrgLoanStatInfo(req);

		return toJson(loanStatInfoResponse.getOrgLoanStatInfo());
	}

	@RequestMapping(value = "/getLoanInfoList", method = RequestMethod.GET)
	public String getLoanInfoList(LoanQueryConditions loanQueryConditions,
			Model model) {

		GetLoanOrderInfoListRequest req = new GetLoanOrderInfoListRequest();

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
				Arrays.asList(statusMap.get(loanQueryConditions.getPageKey())));

		if (StringUtil.isValidString(loanQueryConditions.getLoanStatus())) {
			LoanOrderStatusEnum tempEnum = LoanOrderStatusEnum
					.getEnumByStatus(Integer.parseInt(loanQueryConditions
							.getLoanStatus()));
			if (tempEnum != null) {
				statusList.clear();
				statusList.add(tempEnum);
			}
		}
		req.setOrderStatusEnum(statusList.toArray(new LoanOrderStatusEnum[0]));

		GetLoanOrderInfoListResponse infoListResponse = financialLoanService
				.getLoanOrderInfoList(req);

		model.addAttribute("totalCount", infoListResponse.getTotalCount());
		model.addAttribute("pageSize", infoListResponse.getPageSize());
		model.addAttribute("currentPage", infoListResponse.getCurrentPage());
		model.addAttribute("loanInfoList", infoListResponse.getApplyOrderInfo());

		return "/loan/loan-list/" + loanQueryConditions.getPageKey();
	}

}
