package com.fangcang.titanjr.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.dto.bean.LoanCompanyAppendInfo;
import com.fangcang.titanjr.dto.bean.LoanCompanyEnsureBean;
import com.fangcang.titanjr.dto.bean.LoanCompanyLeaseBean;
import com.fangcang.titanjr.dto.bean.LoanControllDataBean;
import com.fangcang.titanjr.dto.bean.LoanCooperationCompanyBean;
import com.fangcang.titanjr.dto.bean.LoanCreditCompanyBean;
import com.fangcang.titanjr.dto.bean.LoanCreditOrderBean;
import com.fangcang.titanjr.dto.bean.LoanMainBusinessDataBean;
import com.fangcang.titanjr.dto.bean.LoanPersonEnsureBean;
import com.fangcang.titanjr.dto.request.GetCreditInfoRequest;
import com.fangcang.titanjr.dto.request.LoanCreditSaveRequest;
import com.fangcang.titanjr.dto.response.FTPConfigResponse;
import com.fangcang.titanjr.dto.response.GetCreditInfoResponse;
import com.fangcang.titanjr.dto.response.LoanCreditSaveResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
import com.fangcang.titanjr.service.TitanSysconfigService;
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

	private static final Log log = LogFactory
			.getLog(FinancialLoanCreditController.class);

	@Resource
	private TitanSysconfigService sysconfigService;

	@Resource
	private TitanFinancialLoanCreditService financialLoanCreditService;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 授信申请企业页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/applyCredit", method = RequestMethod.GET)
	public String loanCreditApply() {
		return "/loan/credit-apply/framework";
	}

	/**
	 * 授信申请企业页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/applyCompany", method = RequestMethod.GET)
	public String creditCompanyApply() {
		return "/loan/credit-apply/company";
	}

	/**
	 * 授信担保
	 * 
	 * @return
	 */
	@RequestMapping(value = "/applyCompanyEnsure", method = RequestMethod.GET)
	public String creditCompanyEnsureApply() {
		return "/loan/credit-apply/company-ensure";
	}

	/**
	 * 授信企业附件信息
	 * 
	 * @return
	 */
	@RequestMapping(value = "/applyCompanyAppend", method = RequestMethod.GET)
	public String creditCompanyAppendApply() {
		return "/loan/credit-apply/company-append";
	}

	/**
	 * 授信附件
	 * 
	 * @return
	 */
	@RequestMapping(value = "/applyCompanyAccessory", method = RequestMethod.GET)
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

		PrintWriter out = getResponse().getWriter();

		String newName = this
				.getNewFileName(file.getOriginalFilename(), typeId);
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
							+ this.getUserId() + "/");

			log.info("list ftp files fileList=" + fileList);

			// 检查文件是否已经上传过，如果上传过则需要把旧的文件先干掉，在上传新的哦 亲
			if (fileList != null) {
				for (int i = 0; i < fileList.size(); i++) {
					if (fileList.get(i).indexOf(typeId + ".") != -1) {
						util.deleteFile(FtpUtil.UPLOAD_PATH_CREDIT_APPLY + "/"
								+ this.getUserId() + "/" + fileList.get(i));
					}
				}
			}

			util.uploadStream(newName, file.getInputStream(),
					FtpUtil.UPLOAD_PATH_CREDIT_APPLY + "/" + this.getUserId()
							+ "/");

			log.info("upload to ftp success fileName=" + newName);

			util.ftpLogOut();

			this.putSuccess("fileName", newName);

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
	public String getCreditData() {

		GetCreditInfoRequest req = new GetCreditInfoRequest();
		req.setOrgCode(this.getUserId());

		GetCreditInfoResponse creditInfoResponse = financialLoanCreditService
				.getCreditOrderInfo(req);

		return JsonConversionTool.toJson(creditInfoResponse);
	}

	private LoanCreditSaveRequest getLoanCreditSaveReq(String companyData,
			String companyAppendData, String companyEnsureData,
			String companyAccessoryData) {
		LoanCreditSaveRequest req = new LoanCreditSaveRequest();

		req.setOrgCode(this.getUserId());
		// 企业信息
		if (StringUtil.isValidString(companyData)) {
			LoanCreditCompanyBean companyBean = JsonConversionTool.toObject(
					companyData, LoanCreditCompanyBean.class);
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
				LoanCompanyLeaseBean leaseBean = JsonConversionTool.toObject(
						companyLease, LoanCompanyLeaseBean.class);
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
						.toObject(creditCompany, LoanCreditCompanyBean.class);
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
						.toObject(loanPersonEnsure, LoanPersonEnsureBean.class);
				req.setLoanPersonEnsure(loanPersonEnsureBean);
			}
			// 担保企业附件信息
			if ((assureType == null || "2".equals(assureType))
					&& StringUtil.isValidString(companyEnsure)) {

				companyEnsure = JsonConversionTool.mergeJson(companyEnsureJson,
						companyEnsure);
				LoanCompanyEnsureBean loanCompanyEnsureBean = JsonConversionTool
						.toObject(companyEnsure, LoanCompanyEnsureBean.class);
				req.setCompanyEnsure(loanCompanyEnsureBean);
			}
		}
		return req;
	}

	/**
	 * 保存授信数据
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/saveCreditData", method = RequestMethod.POST)
	public String saveCreditData(String companyData, String companyAppendData,
			String companyEnsureData, String companyAccessoryData) {

		LoanCreditSaveResponse saveResponse = financialLoanCreditService
				.saveCreditOrder(this.getLoanCreditSaveReq(companyData,
						companyAppendData, companyEnsureData,
						companyAccessoryData));

		if (!saveResponse.isResult()) {
			putSysError(saveResponse.getReturnMessage());
		} else {
			putSuccess();
		}
		return this.toJson();
	}

	@RequestMapping(value = "/submitCreditApply", method = RequestMethod.POST)
	public String submitCreditApply(String companyData,
			String companyAppendData, String companyEnsureData,
			String companyAccessoryData) {

		LoanCreditSaveRequest creditSaveRequest = this.getLoanCreditSaveReq(
				companyData, companyAppendData, companyEnsureData,
				companyAccessoryData);
		LoanCreditOrderBean creditOrder = new LoanCreditOrderBean();
		creditOrder.setStatus(2);
		creditSaveRequest.setCreditOrder(creditOrder);
		LoanCreditSaveResponse saveResponse = financialLoanCreditService
				.saveCreditOrder(creditSaveRequest);

		if (!saveResponse.isResult()) {
			putSysError(saveResponse.getReturnMessage());
		} else {
			putSuccess();
		}

		return "/loan/credit-apply/credit-apply-result";
	}
}
