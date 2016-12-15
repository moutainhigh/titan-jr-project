package com.fangcang.titanjr.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.LoanCompanyAppendInfo;
import com.fangcang.titanjr.dto.bean.LoanCompanyEnsureBean;
import com.fangcang.titanjr.dto.bean.LoanCompanyLeaseBean;
import com.fangcang.titanjr.dto.bean.LoanControllDataBean;
import com.fangcang.titanjr.dto.bean.LoanCooperationCompanyBean;
import com.fangcang.titanjr.dto.bean.LoanCreditCompanyBean;
import com.fangcang.titanjr.dto.bean.LoanCreditOrderBean;
import com.fangcang.titanjr.dto.bean.LoanMainBusinessDataBean;
import com.fangcang.titanjr.dto.bean.LoanPersonEnsureBean;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.GetAuditEvaluationRequest;
import com.fangcang.titanjr.dto.request.GetCreditInfoRequest;
import com.fangcang.titanjr.dto.request.LoanCreditSaveRequest;
import com.fangcang.titanjr.dto.request.OrgUpdateRequest;
import com.fangcang.titanjr.dto.response.FTPConfigResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.GetAuditEvaluationResponse;
import com.fangcang.titanjr.dto.response.GetCreditInfoResponse;
import com.fangcang.titanjr.dto.response.LoanCreditSaveResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanSysconfigService;
import com.fangcang.titanjr.web.annotation.AccessPermission;
import com.fangcang.util.StringUtil;

/**
 * 贷款授信控制器
 * 
 * @author wengxitao
 *
 */
@Controller
@RequestMapping("loan/credit")
public class FinancialLoanCreditController extends BaseController {

	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(FinancialLoanCreditController.class);

	@Resource
	private TitanSysconfigService sysconfigService;

	@Resource
	private TitanFinancialLoanCreditService financialLoanCreditService;

	@Resource
	private TitanFinancialOrganService financialOrganService;

	/**
	 * 进入贷款主页
	 */
	@RequestMapping(value = "/checkCreditStatus", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_LOAN_42})
	public String checkCreditStatus(Model model) {

		log.info("check credit status userID = " + this.getUserId());

		GetCreditInfoRequest req = new GetCreditInfoRequest();
		req.setOrgCode(this.getUserId());

		GetCreditInfoResponse creditInfoResponse = financialLoanCreditService
				.getCreditOrderInfo(req);

		log.info("get credit data = "
				+ JsonConversionTool.toJson(creditInfoResponse));

		FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
		organQueryRequest.setOrgCode(this.getUserId());

		FinancialOrganResponse financialOrganResponse = financialOrganService
				.queryFinancialOrgan(organQueryRequest);

		if (financialOrganResponse.getFinancialOrganDTO() != null) {

			FinancialOrganDTO financialOrganDTO = financialOrganResponse
					.getFinancialOrganDTO();

			log.info("get Organ info ="
					+ JsonConversionTool.toJson(financialOrganDTO));

			model.addAttribute("userType", financialOrganDTO.getUserType());

			model.addAttribute("lastUpdateDate",
					financialOrganDTO.getLastUpdateDate());

			model.addAttribute("maxLoanAmount",
					financialOrganDTO.getMaxLoanAmount());
		}

		model.addAttribute("creditOrder", creditInfoResponse.getCreditOrder());

		if (creditInfoResponse.getCreditOrder() != null) {
			GetAuditEvaluationRequest request = new GetAuditEvaluationRequest();
			request.setOrderNo(creditInfoResponse.getCreditOrder().getOrderNo());

			GetAuditEvaluationResponse auditEvaluationResponse = financialLoanCreditService
					.getAuditEvaluationInfo(request);

			log.info("get audit evaluation info ="
					+ JsonConversionTool.toJson(auditEvaluationResponse));

			model.addAttribute("creditOpinion",
					auditEvaluationResponse.getCreditOpinionBean());
		}

		return "/loan/loan-main";
	}

	@ResponseBody
	@RequestMapping(value = "/updateLoanAmountDate", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_LOAN_42})
	public String updateLoanAmountDate() {
		OrgUpdateRequest orgUpdateRequest = new OrgUpdateRequest();
		orgUpdateRequest.setLastUpdateDate(new Date());
		orgUpdateRequest.setOrgCode(this.getUserId());
		try {
			financialOrganService.updateOrg(orgUpdateRequest);
		} catch (GlobalServiceException e) {
			log.error("", e);
		}
		this.putSuccess();
		return this.toJson();
	}

	/**
	 * 授信协议
	 * 
	 * @return
	 */
	@RequestMapping(value = "/creditProtocol", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_LOAN_42})
	public String loanCreditProtocol() {
		return "/loan/credit-apply/credit-protocol";
	}

	/**
	 * 授信申请企业页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/applyCredit", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_LOAN_42})
	public String loanCreditApply(Model model) {
		
		GetCreditInfoRequest req = new GetCreditInfoRequest();
		req.setOrgCode(this.getUserId());

		GetCreditInfoResponse creditInfoResponse = financialLoanCreditService
				.getCreditOrderInfo(req);

		log.info("get credit data = "
				+ JsonConversionTool.toJson(creditInfoResponse));

		model.addAttribute("creditOrder", creditInfoResponse.getCreditOrder());

		if (creditInfoResponse.getCreditOrder() != null) {
			GetAuditEvaluationRequest request = new GetAuditEvaluationRequest();
			request.setOrderNo(creditInfoResponse.getCreditOrder().getOrderNo());

			GetAuditEvaluationResponse auditEvaluationResponse = financialLoanCreditService
					.getAuditEvaluationInfo(request);

			log.info("get audit evaluation info ="
					+ JsonConversionTool.toJson(auditEvaluationResponse));

			model.addAttribute("creditOpinion",
					auditEvaluationResponse.getCreditOpinionBean());
		}
		return "/loan/credit-apply/framework";
	}

	/**
	 * 授信申请企业页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/applyCompany", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_LOAN_42})
	public String creditCompanyApply() {
		return "/loan/credit-apply/company";
	}

	/**
	 * 授信担保
	 * 
	 * @return
	 */
	@RequestMapping(value = "/applyCompanyEnsure", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_LOAN_42})
	public String creditCompanyEnsureApply() {
		return "/loan/credit-apply/company-ensure";
	}

	/**
	 * 授信企业附件信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/applyCompanyAppend", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_LOAN_42})
	public String creditCompanyAppendApply() {
		return "/loan/credit-apply/company-append";
	}

	/**
	 * 授信附件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/applyCompanyAccessory", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_LOAN_42})
	public String creditCompanyAccessoryApply() {
		return "/loan/credit-apply/company-accessory";
	}

	@RequestMapping(value = "/upload")
	public void upload(
			@RequestParam(value = "credit_file", required = false) MultipartFile file,
			String typeId) throws IOException {

		getResponse().setCharacterEncoding("utf-8"); // 这里不设置编码会有乱码
		getResponse().setContentType("text/html;charset=utf-8");
		getResponse().setHeader("Cache-Control", "no-cache");

		String dir = "";
		String fileName = typeId;
		if (StringUtil.isValidString(typeId)) {
			if (typeId.indexOf("/") != -1) {
				String[] sp = typeId.split("/");
				if (sp.length > 1) {
					dir = sp[0] + "/";
					fileName = sp[1];
				}
			}
		}

		PrintWriter out = getResponse().getWriter();

		String newName = this.getNewFileName(file.getOriginalFilename(),
				fileName);
		FtpUtil util = null;
		try {

			FTPConfigResponse configResponse = sysconfigService.getFTPConfig();

			// util = new FtpUtil("192.168.2.100", 21, "fangcang168",
			// "fangcang168");

			util = new FtpUtil(configResponse.getFtpServerIp(),
					configResponse.getFtpServerPort(),
					configResponse.getFtpServerUser(),
					configResponse.getFtpServerPassword());

			util.ftpLogin();

			log.info("login ftp success fileName=" + newName);

			List<String> fileList = util
					.listFiles(FtpUtil.UPLOAD_PATH_CREDIT_APPLY + "/"
							+ this.getUserId() + "/" + dir);

			log.info("list ftp files fileList=" + fileList);

			// 检查文件是否已经上传过，如果上传过则需要把旧的文件先干掉，在上传新的哦 亲
			if (fileList != null) {
				for (int i = 0; i < fileList.size(); i++) {
					if (fileList.get(i).indexOf(fileName + ".") != -1) {
						util.deleteFile(FtpUtil.UPLOAD_PATH_CREDIT_APPLY + "/"
								+ this.getUserId() + "/" + dir
								+ fileList.get(i));
					}
				}
			}

			util.uploadStream(newName, file.getInputStream(),
					FtpUtil.UPLOAD_PATH_CREDIT_APPLY + "/" + this.getUserId()
							+ "/" + dir);

			log.info("upload to ftp success fileName=" + newName);

			util.ftpLogOut();

			this.putSuccess("fileName",
					this.getNewFileName(file.getOriginalFilename(), typeId));

		} catch (Exception e) {
			this.putSysError();
			log.error("", e);
		} finally {
			if (util != null) {
				try {
					util.ftpLogOut();
				} catch (Exception e) {
					this.putSysError();
					log.error("", e);
				}
			}
		}

		out.print(toJson());
		out.flush();
		out.close();
	}

	private String getNewFileName(String filename, String newName) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return newName + "." + filename.substring(dot + 1);
			}
		}
		return filename;
	}

	@ResponseBody
	@RequestMapping(value = "/delAccessory")
	public String delAccessory(String typeId, String fileName)
			throws IOException {

		FtpUtil util = null;

		try {
			FTPConfigResponse configResponse = sysconfigService.getFTPConfig();

			util = new FtpUtil(configResponse.getFtpServerIp(),
					configResponse.getFtpServerPort(),
					configResponse.getFtpServerUser(),
					configResponse.getFtpServerPassword());

			util.ftpLogin();
			log.info("login ftp success fileName=" + fileName);

			util.deleteFile(FtpUtil.UPLOAD_PATH_CREDIT_APPLY + "/"
					+ this.getUserId() + "/" + fileName);

			log.info("delete file success fileName=" + fileName);

			util.ftpLogOut();

		} catch (Exception e) {
			this.putSysError();
			log.error("", e);
		} finally {
			if (util != null) {
				try {
					util.ftpLogOut();
				} catch (Exception e) {
					this.putSysError();
					log.error("", e);
				}
			}
		}

		this.putSuccess();
		return this.toJson();
	}

	@ResponseBody
	@RequestMapping(value = "/getCreditData", method = RequestMethod.GET)
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_LOAN_42})
	public String getCreditData() {

		log.info("get credit data userId=" + this.getUserId());

		GetCreditInfoRequest req = new GetCreditInfoRequest();
		req.setOrgCode(this.getUserId());

		GetCreditInfoResponse creditInfoResponse = financialLoanCreditService
				.getCreditOrderInfo(req);

		String jsonData = JsonConversionTool.toJson(creditInfoResponse);

		log.info("get credit data resp=" + jsonData);

		return jsonData;
	}

	private LoanCreditSaveRequest getLoanCreditSaveReq(String companyData,
			String companyAppendData, String companyEnsureData,
			String companyAccessoryData) {

		log.info("create credit save request companyData=" + companyData);
		log.info("create credit save request companyAppendData="
				+ companyAppendData);
		log.info("create credit save request companyEnsureData="
				+ companyEnsureData);
		log.info("create credit save request companyAccessoryData="
				+ companyAccessoryData);

		try {

			LoanCreditSaveRequest req = new LoanCreditSaveRequest();

			req.setOrgCode(this.getUserId());

			// 企业信息
			if (StringUtil.isValidString(companyData)) {
				LoanCreditCompanyBean companyBean = JsonConversionTool
						.toObject(companyData, LoanCreditCompanyBean.class);
				req.setCreditCompany(companyBean);
			}

			// 企业附加信息
			if (StringUtil.isValidString(companyAppendData)) {
				LoanCompanyAppendInfo appendInfo = new LoanCompanyAppendInfo();

				Map<String, String> jsonMap = JsonConversionTool.toObject(
						companyAppendData, Map.class);

				Object contorlObj = jsonMap.get("controllDatas");

				String controllDatasJson = contorlObj instanceof List ? JsonConversionTool
						.toJson(contorlObj) : JsonConversionTool
						.toJson(new Object[] { contorlObj });

				Object cooObj = jsonMap.get("cooperationCompanyInfos");

				String cooperationCompanyInfosJson = cooObj instanceof List ? JsonConversionTool
						.toJson(cooObj) : JsonConversionTool
						.toJson(new Object[] { cooObj });

				Object mainObj = jsonMap.get("mainBusinessDatas");

				String mainBusinessDatasJson = mainObj instanceof List ? JsonConversionTool
						.toJson(mainObj) : JsonConversionTool
						.toJson(new Object[] { mainObj });

				String companyLease = JsonConversionTool.toJson(jsonMap
						.get("companyLease"));

				if (StringUtil.isValidString(controllDatasJson)) {
					List<LoanControllDataBean> controllDataBeans = JsonConversionTool
							.toObjectList(controllDatasJson,
									LoanControllDataBean.class);
					if (CollectionUtils.isNotEmpty(controllDataBeans)) {
						appendInfo.setControllDatas(controllDataBeans);
					}
				}

				if (StringUtil.isValidString(cooperationCompanyInfosJson)) {
					List<LoanCooperationCompanyBean> cooperationBeans = JsonConversionTool
							.toObjectList(cooperationCompanyInfosJson,
									LoanCooperationCompanyBean.class);
					if (CollectionUtils.isNotEmpty(cooperationBeans)) {
						appendInfo.setCooperationCompanyInfos(cooperationBeans);
					}
				}

				if (StringUtil.isValidString(mainBusinessDatasJson)) {
					List<LoanMainBusinessDataBean> mainBusinessBeans = JsonConversionTool
							.toObjectList(mainBusinessDatasJson,
									LoanMainBusinessDataBean.class);
					if (CollectionUtils.isNotEmpty(mainBusinessBeans)) {
						appendInfo.setMainBusinessDatas(mainBusinessBeans);
					}
				}

				if (StringUtil.isValidString(companyLease)) {
					LoanCompanyLeaseBean leaseBean = JsonConversionTool
							.toObject(companyLease, LoanCompanyLeaseBean.class);
					if (leaseBean != null) {
						appendInfo.setCompanyLease(leaseBean);
					}
				}
				req.setCompanyAppendInfo(appendInfo);
			}
			String assureType = null;
			// 企业担保信息
			if (StringUtil.isValidString(companyEnsureData)) {
				Map<String, String> jsonMap = JsonConversionTool.toObject(
						companyEnsureData, Map.class);
				assureType = jsonMap.get("assureType");
				if (StringUtil.isValidString(assureType)) {
					String loanPersonEnsure = JsonConversionTool.toJson(jsonMap
							.get("loanPersonEnsure"));
					String companyEnsure = JsonConversionTool.toJson(jsonMap
							.get("companyEnsure"));
					// 自然人担保
					if ("1".equals(assureType)) {
						if (StringUtil.isValidString(loanPersonEnsure)) {
							LoanPersonEnsureBean loanPersonEnsureBean = JsonConversionTool
									.toObject(loanPersonEnsure,
											LoanPersonEnsureBean.class);
							req.setLoanPersonEnsure(loanPersonEnsureBean);
						}
						// 企业担保
					} else if ("2".equals(assureType)) {
						if (StringUtil.isValidString(companyEnsure)) {
							LoanCompanyEnsureBean loanCompanyEnsureBean = JsonConversionTool
									.toObject(companyEnsure,
											LoanCompanyEnsureBean.class);
							req.setCompanyEnsure(loanCompanyEnsureBean);
						}
					}
				}
			}

			// 企业担保信息
			if (StringUtil.isValidString(companyAccessoryData)) {
				Map<String, String> jsonMap = JsonConversionTool.toObject(
						companyAccessoryData, Map.class);

				assureType = jsonMap.get("assureType");

				String creditCompany = JsonConversionTool.toJson(jsonMap
						.get("creditCompany"));
				String loanPersonEnsure = JsonConversionTool.toJson(jsonMap
						.get("loanPersonEnsure"));
				String companyEnsure = JsonConversionTool.toJson(jsonMap
						.get("companyEnsure"));
				// 贷款企业信息
				if (StringUtil.isValidString(creditCompany)) {

					creditCompany = JsonConversionTool.mergeJson(companyData,
							creditCompany);
					LoanCreditCompanyBean companyBean = JsonConversionTool
							.toObject(creditCompany,
									LoanCreditCompanyBean.class);
					req.setCreditCompany(companyBean);
				}

				String loanPersonEnsureJson = null;
				String companyEnsureJson = null;
				if (StringUtil.isValidString(companyEnsureData)) {
					jsonMap = JsonConversionTool.toObject(companyEnsureData,
							Map.class);
					loanPersonEnsureJson = JsonConversionTool.toJson(jsonMap
							.get("loanPersonEnsure"));
					companyEnsureJson = JsonConversionTool.toJson(jsonMap
							.get("companyEnsure"));
				}
				// 担保自然人附件信息
				if ((assureType == null || "1".equals(assureType))
						&& StringUtil.isValidString(loanPersonEnsure)) {

					loanPersonEnsure = JsonConversionTool.mergeJson(
							loanPersonEnsureJson, loanPersonEnsure);
					LoanPersonEnsureBean loanPersonEnsureBean = JsonConversionTool
							.toObject(loanPersonEnsure,
									LoanPersonEnsureBean.class);
					req.setLoanPersonEnsure(loanPersonEnsureBean);
				}
				// 担保企业附件信息
				if ((assureType == null || "2".equals(assureType))
						&& StringUtil.isValidString(companyEnsure)) {

					companyEnsure = JsonConversionTool.mergeJson(
							companyEnsureJson, companyEnsure);
					LoanCompanyEnsureBean loanCompanyEnsureBean = JsonConversionTool
							.toObject(companyEnsure,
									LoanCompanyEnsureBean.class);
					req.setCompanyEnsure(loanCompanyEnsureBean);
				}
			}
			return req;
		} catch (Exception ex) {
			log.error("", ex);
		}

		return null;
	}

	/**
	 * 保存授信数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveCreditData", method = RequestMethod.POST)
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_LOAN_42})
	public String saveCreditData(String companyData, String companyAppendData,
			String companyEnsureData, String companyAccessoryData) {

		LoanCreditSaveRequest creditSaveRequest = this.getLoanCreditSaveReq(
				companyData, companyAppendData, companyEnsureData,
				companyAccessoryData);

		log.info("save credit applay req["
				+ JsonConversionTool.toJson(creditSaveRequest) + "]");

		LoanCreditSaveResponse saveResponse = financialLoanCreditService
				.saveCreditOrder(creditSaveRequest);

		log.info("save credit applay resp["
				+ JsonConversionTool.toJson(saveResponse) + "]");

		if (!saveResponse.isResult()) {
			putSysError(saveResponse.getReturnMessage());
		} else {
			putSuccess();
		}
		return this.toJson();
	}

	@RequestMapping(value = "/submitCreditApply", method = RequestMethod.POST)
	@AccessPermission(allowRoleCode={CommonConstant.ROLECODE_LOAN_42})
	public String submitCreditApply(String companyData,
			String companyAppendData, String companyEnsureData,
			String companyAccessoryData, Model model) {

		LoanCreditSaveRequest creditSaveRequest = this.getLoanCreditSaveReq(
				companyData, companyAppendData, companyEnsureData,
				companyAccessoryData);

		log.info("submit credit apply req["
				+ JsonConversionTool.toJson(creditSaveRequest) + "]");

		if (creditSaveRequest == null) {
			model.addAttribute("errorMsg", "授信单提交失败,请重试！");
			return "/loan/credit-apply/credit-apply-result";
		}

		LoanCreditOrderBean creditOrder = new LoanCreditOrderBean();
		creditOrder.setStatus(2);
		creditOrder.setReqTime(new Date());
		creditSaveRequest.setCreditOrder(creditOrder);
		creditSaveRequest.setOrgCode(this.getUserId());
		LoanCreditSaveResponse saveResponse = financialLoanCreditService
				.saveCreditOrder(creditSaveRequest);

		log.info("submit credit apply result = "
				+ JsonConversionTool.toJson(saveResponse));

		if (!saveResponse.isResult()) {
			model.addAttribute("errorMsg", "授信单提交失败,请重试！");
		}

		model.addAttribute("reqTime",
				DateUtil.sdf4.format(creditOrder.getReqTime()));
		return "/loan/credit-apply/credit-apply-result";
	}
}
