package com.fangcang.titanjr.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.enums.LoanProductEnum;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.dto.bean.LoanApplyInfo;
import com.fangcang.titanjr.dto.bean.LoanRoomPackSpecBean;
import com.fangcang.titanjr.dto.bean.LoanSpecBean;
import com.fangcang.titanjr.dto.bean.LoanSpecificationBean;
import com.fangcang.titanjr.dto.bean.OrgCheckDTO;
import com.fangcang.titanjr.dto.request.ConfirmLoanOrderIsAvailableRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.GetLoanOrderInfoRequest;
import com.fangcang.titanjr.dto.request.OfflineLoanApplyRequest;
import com.fangcang.titanjr.dto.request.SynLoanOrderRequest;
import com.fangcang.titanjr.dto.response.ConfirmLoanOrderIsAvailableResponse;
import com.fangcang.titanjr.dto.response.FTPConfigResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.GetLoanOrderInfoResponse;
import com.fangcang.titanjr.dto.response.OfflineLoanApplyResponse;
import com.fangcang.titanjr.dto.response.OrganQueryCheckResponse;
import com.fangcang.titanjr.dto.response.SynLoanOrderResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanSysconfigService;
import com.fangcang.util.StringUtil;

/**
 * 线下贷款处理
 * 
 * @author Administrator
 */
@Controller
@RequestMapping("loan/offline")
public class FinancialLoanOfflineController extends BaseController {

	/**
	 */
	private static final long serialVersionUID = 1L;

	private static final Log log = LogFactory
			.getLog(FinancialLoanApplyController.class);

	@Resource
	private TitanSysconfigService sysconfigService;

	@Resource
	private TitanFinancialLoanService titanFinancialLoanService;

	@Resource
	private TitanFinancialOrganService financialOrganService;

	@RequestMapping("/page")
	public String loanOrderOfflinePer(Model model) {
		
		FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
		organQueryRequest.setCurrentPage(1);
		organQueryRequest.setPageSize(10000);
		OrganQueryCheckResponse checkResponse = financialOrganService
				.queryFinancialOrganForPage(organQueryRequest);

		PaginationSupport<OrgCheckDTO> paginationSupport = checkResponse
				.getPaginationSupport();

		model.addAttribute("orgList", paginationSupport.getItemList());

		return "/loan/loan-offline/loan-syn";
	}

	@ResponseBody
	@RequestMapping("/checkOfflineLoanOrder")
	public String checkOfflineLoanOrder(String orderNo, String orgCode) {
		if (!StringUtil.isValidString(orderNo)
				|| !StringUtil.isValidString(orgCode)) {
			putSysError("贷款单号和机构编码非法!");
			return toJson();
		}

		FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
		organQueryRequest.setOrgCode(orgCode);

		FinancialOrganResponse financialOrganResponse = financialOrganService
				.queryFinancialOrgan(organQueryRequest);

		if (financialOrganResponse == null
				|| financialOrganResponse.getFinancialOrganDTO() == null) {
			putSysError("机构编号不存在!");
			return toJson();
		}

		 GetLoanOrderInfoRequest lreq = new GetLoanOrderInfoRequest();
		 lreq.setOrderNo(orderNo);
		 lreq.setOrgCode(orgCode);
		
		 GetLoanOrderInfoResponse infoResponse =
		 titanFinancialLoanService.getLoanOrderInfo(lreq);
		 if(infoResponse != null && infoResponse.getApplyOrderInfo()!=null)
		 {
		 putSysError("该单号在系统中已经存在！");
		 return toJson();
		 }

		ConfirmLoanOrderIsAvailableRequest req = new ConfirmLoanOrderIsAvailableRequest();
		req.setOrderNo(orderNo);
		req.setOrgCode(orgCode);

		ConfirmLoanOrderIsAvailableResponse availableResponse = titanFinancialLoanService
				.confirmLoanOrderIsAvailable(req);

		putSuccess();

		if (availableResponse == null || !availableResponse.isResult()) {
			putSysError("贷款单确认失败！");
		}

		return toJson();
	}

	/**
	 * 申请贷款
	 * 
	 * @param info
	 * @return
	 */
	@ResponseBody
	@RequestMapping("apply")
	public String loanApply(LoanApplyInfo info) {

		try {

			LoanSpecBean loanSpecBeanReq = null;

			OfflineLoanApplyRequest request = new OfflineLoanApplyRequest();

			if (info.getProductType().equals(
					"" + LoanProductEnum.ROOM_PACK.getCode())) {
				request.setProductType(LoanProductEnum.ROOM_PACK);
				// 申请贷款
				LoanRoomPackSpecBean loanSpecBean = new LoanRoomPackSpecBean();
				loanSpecBean.setAmount("0");
				loanSpecBean.setOrderNo(info.getLoanOrderNo());
				loanSpecBean.setAccount(info.getAccount());
//				loanSpecBean.setTitanCode(info.getTitanCode());
				loanSpecBean.setAccountName(info.getAccountName());
				loanSpecBean.setBank(info.getBank());
				loanSpecBean.setBeginDate(DateUtil.sdf.parse(info
						.getBeginDate()));
				loanSpecBean.setAccessory(info.getContactNames());
				loanSpecBean.setEndDate(DateUtil.sdf.parse(info.getEndDate()));
				loanSpecBean.setHotleName(info.getHotelName());
				if (StringUtil.isValidString(info.getRoomNights())) {
					loanSpecBean.setRoomNights(Integer.parseInt(info
							.getRoomNights()));
				}
				loanSpecBeanReq = loanSpecBean;
			} else {

				request.setProductType(LoanProductEnum.OPERACTION);
				LoanSpecificationBean specificationBean = new LoanSpecificationBean();
				specificationBean.setAccount(info.getAccount());
//				specificationBean.setTitanCode(info.getTitanCode());
				specificationBean.setAccountName(info.getAccountName());
				specificationBean.setBank(info.getBank());
				specificationBean.setAccessory(info.getContactNames());
				specificationBean.setContent(info.getContent());
				specificationBean.setOrderNo(info.getLoanOrderNo());
				loanSpecBeanReq = specificationBean;
			}
			request.setLcanSpec(loanSpecBeanReq);
			request.setOrgCode(info.getOrgCode());
			OfflineLoanApplyResponse response = titanFinancialLoanService
					.offlineLoanApply(request);
			if (!response.isResult()) {
				this.putSysError(response.getReturnMessage());
				return toJson();
			}

			SynLoanOrderRequest req = new SynLoanOrderRequest();
			req.setOrderNo(info.getLoanOrderNo());
			req.setOrgCode(info.getOrgCode());

			SynLoanOrderResponse orderResponse = titanFinancialLoanService
					.synLoanOrderInfo(req);

			if (!orderResponse.isResult()) {
				this.putSysError(orderResponse.getReturnMessage());
				return toJson();
			}

			Map<String, String> jsonMap = new HashMap<String, String>();
			jsonMap.put("orderNo", response.getOrderNo());
			jsonMap.put("orderCreateTime", response.getOrderCreateTime());
			this.putSuccess("贷款申请已经提交成功", jsonMap);
		} catch (Exception e) {
			this.putSysError();
			log.error("包房贷申请失败", e);
		}
		return toJson();
	}

	@RequestMapping(value = "/upload")
	public void upload(
			@RequestParam(value = "compartment_contract", required = false) MultipartFile file,
			String loanApplyOrderNo, String fileName, String orgCode)
			throws IOException {

		getResponse().setCharacterEncoding("utf-8"); // 这里不设置编码会有乱码
		getResponse().setContentType("text/html;charset=utf-8");
		getResponse().setHeader("Cache-Control", "no-cache");

		PrintWriter out = getResponse().getWriter();

		boolean check = checkFileType(file.getOriginalFilename());
		if (!check) {
			this.putSysError("文件格式不正确");
			out.print(toJson());
			out.flush();
			out.close();
			return;
		}

		String newName = this.getNewFileName(file.getOriginalFilename(),
				fileName);
		FtpUtil util = null;
		try {

			FTPConfigResponse configResponse = sysconfigService.getFTPConfig();

			util = new FtpUtil(configResponse.getFtpServerIp(),
					configResponse.getFtpServerPort(),
					configResponse.getFtpServerUser(),
					configResponse.getFtpServerPassword());

			util.ftpLogin();

			log.info("login ftp success fileName=" + newName);

			List<String> fileList = util
					.listFiles(FtpUtil.UPLOAD_PATH_LOAN_APPLY + "/" + orgCode
							+ "/" + loanApplyOrderNo + "/");

			log.info("list ftp files fileList=" + fileList);

			// 检查文件是否已经上传过，如果上传过则需要把旧的文件先干掉，在上传新的哦 亲
			if (fileList != null) {
				for (int i = 0; i < fileList.size(); i++) {
					if (fileList.get(i).indexOf(fileName + ".") != -1) {
						util.deleteFile(FtpUtil.UPLOAD_PATH_LOAN_APPLY + "/"
								+ orgCode + "/" + loanApplyOrderNo + "/"
								+ fileList.get(i));
					}
				}
			}

			util.uploadStream(newName, file.getInputStream(),
					FtpUtil.UPLOAD_PATH_LOAN_APPLY + "/" + orgCode + "/"
							+ loanApplyOrderNo + "/");

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

	private boolean checkFileType(String filename) {
		if (filename == null || !StringUtil.isValidString(filename)) {
			return false;
		}
		int dot = filename.lastIndexOf('.');
		if ((dot > -1) && (dot < (filename.length() - 1))) {
			String suffix = filename.substring(dot + 1).toLowerCase();

			if (suffix.equals("pdf") || suffix.equals("jpg")
					|| suffix.equals("jpeg") || suffix.equals("png")
					|| suffix.equals("zip") || suffix.equals("rar")) {
				return true;
			}
		}
		return false;
	}

}
