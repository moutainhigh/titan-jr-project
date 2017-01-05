package com.fangcang.titanjr.web.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.LoanCreditStatusEnum;
import com.fangcang.titanjr.common.enums.LoanOrderStatusEnum;
import com.fangcang.titanjr.common.enums.LoanProductEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.dto.bean.LoanApplyOrderBean;
import com.fangcang.titanjr.dto.bean.LoanCreditOrderBean;
import com.fangcang.titanjr.dto.request.CancelLoanRequest;
import com.fangcang.titanjr.dto.request.GetCreditInfoRequest;
import com.fangcang.titanjr.dto.request.GetHistoryRepaymentListRequest;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoListRequest;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoRequest;
import com.fangcang.titanjr.dto.request.GetOrgLoanStatInfoRequest;
import com.fangcang.titanjr.dto.response.CancelLoanResponse;
import com.fangcang.titanjr.dto.response.GetCreditInfoResponse;
import com.fangcang.titanjr.dto.response.GetHistoryRepaymentListResponse;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoListResponse;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoResponse;
import com.fangcang.titanjr.dto.response.GetOrgLoanStatInfoResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
import com.fangcang.titanjr.service.TitanFinancialLoanService;
import com.fangcang.titanjr.service.TitanSysconfigService;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.titanjr.web.pojo.LoanQueryConditions;
import com.fangcang.util.DateUtil;
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

	@Resource
	private TitanFinancialLoanCreditService financialLoanCreditService;

	private final static Map<String, Object> initDataMap = new HashMap<String, Object>();

	static {
		initDataMap.put("loan-all-status", LoanOrderStatusEnum.values());
		initDataMap.put("loan-all-orderby", "createTime desc");

		initDataMap.put("loan-audit-status", new LoanOrderStatusEnum[] {
				LoanOrderStatusEnum.LOAN_REQ_ING,
				LoanOrderStatusEnum.WAIT_AUDIT, LoanOrderStatusEnum.AUDIT_PASS,
				LoanOrderStatusEnum.LENDING_ING });

		initDataMap.put("loan-audit-orderby", "createTime desc");

		initDataMap.put("loan-over-status",
				new LoanOrderStatusEnum[] { LoanOrderStatusEnum.LOAN_FINISH });
		initDataMap.put("loan-over-orderby", "lastrepaymentdate desc");

		initDataMap.put("loan-payment-status",
				new LoanOrderStatusEnum[] { LoanOrderStatusEnum.HAVE_LOAN,
						LoanOrderStatusEnum.LOAN_EXPIRY });
		initDataMap
				.put("loan-payment-orderby", "status desc , actualrepaymentdate");

		// initDataMap.put("" + LoanProductEnum.ROOM_PACK.getCode(),
		// "loan-spec");
		// initDataMap.put("" + LoanProductEnum.OPERACTION.getCode(),
		// "loan-spec");

	}

	/**
	 * 获取具体贷款的详细信息
	 * 
	 * @param orderNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getLoanDetailsInfo", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode = { CommonConstant.ROLECODE_LOAN_42 })
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

		// Object pageKey = initDataMap.get(String.valueOf(infoResponse
		// .getApplyOrderInfo().getProductType()));
		// if (pageKey == null || "".equals(pageKey)) {
		// log.error("product type pageKey is null");
		// model.addAttribute("errormsg", "产品类型不支持查看详情，请确认！");
		// return "error";
		// }
		model.addAttribute("loanOrderInfo", infoResponse.getApplyOrderInfo());
		if (infoResponse.getApplyOrderInfo() != null) {
			model.addAttribute("loanSpecInfo", infoResponse.getApplyOrderInfo()
					.getLoanSpec());
		}

		return "/loan/product-info/loan-spec";
	}

	/**
	 * 获取某个贷款单号的还款列表
	 * 
	 * @param orderNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRepaymentList", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode = { CommonConstant.ROLECODE_LOAN_42 })
	public String getRepaymentList(String orderNo) {
		log.info("get repayment List info ");

		GetHistoryRepaymentListRequest listRequest = new GetHistoryRepaymentListRequest();
		listRequest.setOrderNo(orderNo);
		listRequest.setOrgCode(this.getUserId());

		GetHistoryRepaymentListResponse listResponse = financialLoanService
				.getHistoryRepaymentList(listRequest);

		log.info("repayment List = "
				+ JsonConversionTool.toJson(listResponse
						.getLoanRepaymentInfos()));

		return toJson(listResponse.getLoanRepaymentInfos());
	}

	/**
	 * 检查用户是否授信
	 * 
	 * @return
	 */
	private boolean checkUserIsCredit() {
		GetCreditInfoRequest req = new GetCreditInfoRequest();
		req.setOrgCode(this.getUserId());

		GetCreditInfoResponse response = financialLoanCreditService
				.getCreditOrderInfo(req);

		if (response == null || !response.isResult()
				|| response.getCreditOrder() == null) {

			log.error("query credit fail!");
			return false;
		}

		LoanCreditOrderBean creditOrderBean = response.getCreditOrder();

		if (LoanCreditStatusEnum.REVIEW_PASS.getStatus() == creditOrderBean
				.getStatus()) {
			return true;
		}

		log.error("credit status exception!");

		return false;
	}

	@ResponseBody
	@RequestMapping(value = "/loanStatInfo", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode = { CommonConstant.ROLECODE_VIEW_39 })
	public String getLoanStatInfo() {

		if (!checkUserIsCredit()) {
			putSysError("用户未进行贷款授信！");
			return toJson();
		}

		log.info("get loan stat info ");
		GetOrgLoanStatInfoRequest req = new GetOrgLoanStatInfoRequest();

		req.setOrgCode(this.getUserId());

		GetOrgLoanStatInfoResponse loanStatInfoResponse = financialLoanService
				.getOrgLoanStatInfo(req);

		log.info("loanStatInfo = "
				+ JsonConversionTool.toJson(loanStatInfoResponse));

		return toJson(loanStatInfoResponse.getOrgLoanStatInfo());
	}

	/**
	 * 取消具体的一笔贷款
	 * 
	 * @param orderNo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/stopLoan", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode = { CommonConstant.ROLECODE_LOAN_42 })
	public String stopLoan(String orderNo) {
		log.info("stop loan orderNo=" + orderNo);

		CancelLoanRequest req = new CancelLoanRequest();
		req.setOrderNo(orderNo);
		req.setOrgCode(this.getUserId());
		CancelLoanResponse loanResponse = financialLoanService.cancelLoan(req);

		log.info("stopLoan = " + JsonConversionTool.toJson(loanResponse));

		if (loanResponse != null && loanResponse.isResult()) {
			putSuccess();
		} else {
			putSysError("取消贷款单失败，请稍后再试！");
		}

		return toJson();
	}

	/**
	 * 查询某个用户的贷款列表
	 * 
	 * @param loanQueryConditions
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getLoanInfoList", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode = { CommonConstant.ROLECODE_LOAN_42 })
	public String getLoanInfoList(LoanQueryConditions loanQueryConditions,
			Model model) {

		if (loanQueryConditions == null
				|| !StringUtil.isValidString(loanQueryConditions.getPageKey())) {
			log.error("page key is null");
			model.addAttribute("errormsg", "错误的查询方式，请确认!");
			return "error";
		}

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
		// 按照套路给查询分配过滤的状态
		List<LoanOrderStatusEnum> statusList = new ArrayList<LoanOrderStatusEnum>(
				Arrays.asList((LoanOrderStatusEnum[]) initDataMap
						.get(loanQueryConditions.getPageKey() + "-status")));
		// 如果页面指定了要查询的状态，那么就需要按照页面的要求来
		if (StringUtil.isValidString(loanQueryConditions.getLoanStatus())) {
			
			String status[] = loanQueryConditions.getLoanStatus().split(",");
			statusList.clear();
			for (int i = 0; i < status.length; i++) {
				LoanOrderStatusEnum tempEnum = LoanOrderStatusEnum
						.getEnumByStatus(Integer.parseInt(status[i]));
				if (tempEnum != null) {

					statusList.add(tempEnum);
				}
			}
		}
		// 设置要过滤的状态
		req.setOrderStatusEnum(statusList.toArray(new LoanOrderStatusEnum[0]));
		// 设置排序条件
		Object orderBy = initDataMap.get(loanQueryConditions.getPageKey()
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

	private String formatNumber(Object amount) {
		if (amount == null) {
			amount = "0";
		}
		NumberFormat format = NumberFormat.getCurrencyInstance(Locale.CHINA);
		return format.format(Float.valueOf(amount.toString()) / 100);
	}

	/**
	 * 导出excel
	 * 
	 * @param loanQueryConditions
	 * @param request
	 * @param response
	 */
	@RequestMapping("exportExcel")
	public void exportExcel(LoanQueryConditions loanQueryConditions,
			HttpServletRequest request, HttpServletResponse response) {

		// 生成提示信息，
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = "贷款记录";
		OutputStream outputStream = null;
		try {
			codedFileName = java.net.URLEncoder.encode(codedFileName, "UTF-8");// 进行转码，使其支持中文文件名
			response.setHeader("content-disposition", "attachment;filename="
					+ codedFileName + ".xls");

			GetLoanOrderInfoListRequest req = new GetLoanOrderInfoListRequest();

			// 设置查询条件
			req.setOrgCode(this.getUserId());

			req.setPageSize(10000);

			req.setCurrentPage(1);

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
			// 按照套路给查询分配过滤的状态
			List<LoanOrderStatusEnum> statusList = new ArrayList<LoanOrderStatusEnum>(
					Arrays.asList((LoanOrderStatusEnum[]) initDataMap
							.get(loanQueryConditions.getPageKey() + "-status")));
			// 如果页面指定了要查询的状态，那么就需要按照页面的要求来
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
			req.setOrderStatusEnum(statusList
					.toArray(new LoanOrderStatusEnum[0]));
			// 设置排序条件
			Object orderBy = initDataMap.get(loanQueryConditions.getPageKey()
					+ "-orderby");
			if (orderBy != null) {
				req.setOrderBy(orderBy.toString());
			}
			GetLoanOrderInfoListResponse infoListResponse = financialLoanService
					.getLoanOrderInfoList(req);

			// 产生工作簿对象
			HSSFWorkbook workbook = new HSSFWorkbook();

			// 产生工作表对象
			HSSFSheet sheet = workbook.createSheet();
			HSSFRow head = sheet.createRow(0);
			head.createCell(0).setCellValue("编号");
			head.createCell(1).setCellValue("贷款申请时间");
			head.createCell(2).setCellValue("贷款额度(元)");
			head.createCell(3).setCellValue("已还本金(元)");
			head.createCell(4).setCellValue("已付利息(元)");
			head.createCell(5).setCellValue("剩余本金(元)");
			head.createCell(6).setCellValue("贷款类型");
			head.createCell(7).setCellValue("还款方式");
			head.createCell(8).setCellValue("贷款状态");

			if (infoListResponse != null && infoListResponse.isResult()) {

				List<LoanApplyOrderBean> applyOrderBeans = infoListResponse
						.getApplyOrderInfo();
				LoanApplyOrderBean loanApplyOrderBean = null;
				int index = 0;

				for (int i = 0; i < applyOrderBeans.size(); i++) {
					loanApplyOrderBean = applyOrderBeans.get(i);
					HSSFRow row = sheet.createRow(index + 1);// 创建一行

					row.createCell(0).setCellValue(index + 1);

					row.createCell(1).setCellValue(
							DateUtil.dateToString(
									loanApplyOrderBean.getCreateTime(),
									"yyyy-MM-dd HH:mm:ss"));

					row.createCell(2).setCellValue(
							formatNumber(loanApplyOrderBean.getActualAmount()));
					row.createCell(3).setCellValue(
							formatNumber(loanApplyOrderBean
									.getRepaymentPrincipal()));
					row.createCell(4).setCellValue(
							formatNumber(loanApplyOrderBean
									.getRepaymentInterest()));
					row.createCell(5)
							.setCellValue(
									formatNumber(loanApplyOrderBean
											.getShouldCapital()));

					row.createCell(6).setCellValue(
							LoanProductEnum.getEnumByKey(
									loanApplyOrderBean.getProductType())
									.getDesc());
					row.createCell(7).setCellValue("按日计利，随借随还");
					row.createCell(8).setCellValue(
							LoanOrderStatusEnum.getEnumByStatus(
									loanApplyOrderBean.getStatus()).getDesc());
					index++;

				}
			}
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			log.info("生成excel文件成功");
		} catch (UnsupportedEncodingException e1) {
			log.error("编码错误", e1);
		} catch (Exception e) {
			log.error("发生未知异常", e);
		} finally {
			if (outputStream != null) {
				try {
					outputStream.flush();
					outputStream.close();
				} catch (IOException e) {
					log.error("关闭异常流失败", e);
				}
			}
		}
	}

}
