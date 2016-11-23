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

import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.dto.bean.LoanCompanyEnsureBean;
import com.fangcang.titanjr.dto.bean.LoanCompanyLeaseBean;
import com.fangcang.titanjr.dto.bean.LoanControllDataBean;
import com.fangcang.titanjr.dto.bean.LoanCooperationCompanyBean;
import com.fangcang.titanjr.dto.bean.LoanCreditCompanyBean;
import com.fangcang.titanjr.dto.bean.LoanCreditOrderBean;
import com.fangcang.titanjr.dto.bean.LoanMainBusinessDataBean;
import com.fangcang.titanjr.dto.bean.LoanPersonEnsureBean;
import com.fangcang.titanjr.dto.request.LoanCreditSaveRequest;
import com.fangcang.titanjr.dto.response.FTPConfigResponse;
import com.fangcang.titanjr.dto.response.GetCreditInfoResponse;
import com.fangcang.titanjr.dto.response.LoanCreditSaveResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
import com.fangcang.titanjr.service.TitanSysconfigService;

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

		// LoanCreditCompany company = new LoanCreditCompany();
		GetCreditInfoResponse company = new GetCreditInfoResponse();

		LoanCreditOrderBean creditOrder = new LoanCreditOrderBean();
		creditOrder.setAssureType(2);
		company.setCreditOrder(creditOrder);

		LoanCreditCompanyBean creditCompany = new LoanCreditCompanyBean();
		creditCompany.setName("taogegege");
		creditCompany.setContactName("xcxcxc");
		creditCompany.setContactPhone("13544411111");
		creditCompany.setOrgSize(2);
		creditCompany.setLegalceType(2);
		creditCompany.setLegalName("taoge");
		creditCompany.setTaxRegNo("32423423");
		creditCompany.setLicense("55555555555555");
		creditCompany.setRegAccount("3453453433#@333.com");
		creditCompany.setOrgCode("333333");
		creditCompany.setEmpSize(150);
		creditCompany.setLegalNo("228828383883");
		creditCompany.setOfficeAddress("河北/石家庄/长安区/3453");
		creditCompany.setRegAddress("内蒙古/呼和浩特/新城区/45345");
		creditCompany.setRegDate("2011-11-21");
		creditCompany.setStartDate("2012-12-11");

		creditCompany.setAccountUrl("account.jpg");
		creditCompany.setOfficeUrl("office.jpg");
		creditCompany.setCreditUrl("credit.jpg");
		creditCompany.setLegalNoUrl("legalno.jpg");
		creditCompany.setLicenseUrl("license.jpg");
		creditCompany.setOfficeNoUrl("officeno.jpg");
		creditCompany.setWaterUrl("water.jpg");
		creditCompany.setOrgCodeUrl("orgcode.jpg");
		creditCompany.setTaxRegUrl("taxregno.jpg");

		LoanCompanyLeaseBean companyLease = new LoanCompanyLeaseBean();
		companyLease.setLeaseType("1");
		companyLease.setBeginLeaseDate("2012-10");
		companyLease.setEndLeaseDate("2013-10");
		companyLease.setHousingArea("10");
		companyLease.setRemark("zczxcz");
		companyLease.setLeaseAddress("江西/南昌/东湖区/2345234523");
		companyLease.setPaymentMethod("dsafsdf");
		companyLease.setRental("xxxxx");

		creditCompany.setCompanyLease(companyLease);

		List<LoanControllDataBean> controllDatas = new ArrayList<LoanControllDataBean>();

		for (int i = 0; i < 5; i++) {
			LoanControllDataBean d = new LoanControllDataBean();
			d.setContributionAmount("1000" + i);
			d.setEquityRatio("22222" + i);
			d.setShareholderName("zxczxczcx" + i);
			controllDatas.add(d);
		}
		creditCompany.setControllDatas(controllDatas);

		List<LoanCooperationCompanyBean> cooperationCompanyInfos = new ArrayList<LoanCooperationCompanyBean>();

		for (int i = 0; i < 6; i++) {
			LoanCooperationCompanyBean d = new LoanCooperationCompanyBean();
			d.setCooperation("1");
			d.setCooperationName("zxcdsfds" + i);
			d.setCooperationYears("2011" + i);
			d.setSaleProportion("xczx" + i);
			d.setSettlement("zxczcxz" + i);
			d.setYearAnnualSale("rewrwqerwqe" + i);
			cooperationCompanyInfos.add(d);
		}
		creditCompany.setCooperationCompanyInfos(cooperationCompanyInfos);

		List<LoanMainBusinessDataBean> mainBusinessDatas = new ArrayList<LoanMainBusinessDataBean>();

		for (int i = 0; i < 3; i++) {
			LoanMainBusinessDataBean d = new LoanMainBusinessDataBean();
			d.setMainAnnualSale("adsfasd" + i);
			d.setMainProductsOrService("zxc" + i);
			d.setMainSaleProportion("444444" + i);
			mainBusinessDatas.add(d);
		}
		creditCompany.setMainBusinessDatas(mainBusinessDatas);

		company.setCreditCompany(creditCompany);

		LoanPersonEnsureBean loanPersonEnsure = new LoanPersonEnsureBean();
		loanPersonEnsure.setCarBrand("3asdfa");
		loanPersonEnsure.setCarPropertyType(2);
		loanPersonEnsure.setCarPurchaseDate("2012-12");
		loanPersonEnsure.setCurrentLiveaAdress("江西/南昌/东湖区/2345234523");
		loanPersonEnsure.setFirstContactName("asdfasd");
		loanPersonEnsure.setFirstContactPhone("1254122111");
		loanPersonEnsure.setGraduateSchool("3452345234");
		loanPersonEnsure.setHaveChildren(1);
		loanPersonEnsure.setHighestEducation(2);
		loanPersonEnsure.setHousePropertyType(2);
		loanPersonEnsure.setIdCardUrl("p_idcard.jpg");
		loanPersonEnsure.setIndustry("sdfasd");
		loanPersonEnsure.setMarriageStatus(2);
		loanPersonEnsure.setMarriageUrl("p_marriage.jpg");
		loanPersonEnsure.setMobilenNmber("12441111111");
		loanPersonEnsure.setNationalIdentityNumber("sadfas");
		loanPersonEnsure.setNativePlace("asdaaa");
		loanPersonEnsure.setOccupation("zxczxc");
		loanPersonEnsure.setOfficeAddress("内蒙古/呼和浩特/新城区/45345");
		loanPersonEnsure.setOrderNo("asfdasd");
		loanPersonEnsure.setOtherProperty("asdfasd");
		loanPersonEnsure.setPersonName("taogege");
		loanPersonEnsure.setPropertyRemark("aaaddd");
		loanPersonEnsure.setRegisteredUrl("p_registered.jpg");
		loanPersonEnsure.setRelationToguarantee1(1);
		loanPersonEnsure.setRelationToguarantee2(2);
		loanPersonEnsure.setSecondContactName("zczxc");
		loanPersonEnsure.setSecondContactPhone("12544444");
		loanPersonEnsure.setSpouseIdCardUrl("p_spouseidcard.jpg");
		loanPersonEnsure.setSpouseRegisteredUrl("p_spousereg.jpg");
		loanPersonEnsure.setWorkCompany("asdfasd");
		loanPersonEnsure.setWorktelePhone("asdfasdfa");
		loanPersonEnsure.setYearsWorking(1);

		company.setLoanPersonEnsure(loanPersonEnsure);

		LoanCompanyEnsureBean companyEnsure = new LoanCompanyEnsureBean();
		companyEnsure.setBusinessLicense("asdfa");
		companyEnsure.setBusinessLicenseUrl("c_bulicense.jpg");
		companyEnsure.setCompanyName("taogeg");
		companyEnsure.setContactPhone("zxczxcz");
		companyEnsure.setEnterpriseScale(1);
		companyEnsure.setFoundDate("2012-12-1");
		companyEnsure.setLegalPersonCertificateNumber("sadfas");
		companyEnsure.setLegalPersonCertificateType(2);
		companyEnsure.setLegalPersonName("asdfa");
		companyEnsure.setLegalPersonUrl("c_legalidcard.jpg");
		companyEnsure.setLicenseUrl("c_license.jpg");
		companyEnsure.setContactName("adddddddddd");
		companyEnsure.setOfficeAddress("内蒙古/呼和浩特/新城区/45345");
		companyEnsure.setOrgCodeCertificate("asdfasdfa");
		companyEnsure.setOrgCodeCertificateUrl("c_orgcode.jpg");
		companyEnsure.setRegAddress("内蒙古/呼和浩特/新城区/45345");
		companyEnsure.setRegisterAccount("dsfas");
		companyEnsure.setRegisterDate("2012-11-22");
		companyEnsure.setTaxRegisterCode("werqwe");
		companyEnsure.setTaxRegisterCodeUrl("c_taxregno.jpg");
		companyEnsure.setUserId("tdddddd");

		company.setCompanyEnsure(companyEnsure);
		// // 企业担保信息
		// private LoanCompanyEnsure companyEnsure;
		//
		// // 授信企业信息
		// private LoanCreditCompany creditCompany;

		return JsonConversionTool.toJson(company);
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

		LoanCreditCompanyBean companyBean = JsonConversionTool.toObject(
				companyData, LoanCreditCompanyBean.class);
		LoanCreditSaveRequest req = new LoanCreditSaveRequest();

		req.setCreditCompany(companyBean);

		LoanCreditSaveResponse saveResponse = financialLoanCreditService
				.saveCreditOrder(req);

		if (!saveResponse.isResult()) {
			putSysError(saveResponse.getReturnMessage());
		} else {
			putSuccess();
		}
		// Map<String, String> map1 =
		// JsonConversionTool.toObject(companyAppendData, Map.class);
		//
		//
		// String companyJson = JsonConversionTool.mergeJson(companyData);
		//
		//
		//
		// System.out.println(companyData);
		//
		//
		// Map<String, String> map = JsonConversionTool.toObject(companyData,
		// Map.class);
		//
		// System.out.println(map);
		//
		// System.out.println(companyAppendData);
		// if(companyAppendData != null)
		// {
		// Map<String, String> map1 =
		// JsonConversionTool.toObject(companyAppendData, Map.class);
		//
		// System.out.println(map1);
		// }
		//
		// System.out.println(companyEnsureData);
		// System.out.println(companyAccessoryData);

		return this.toJson();
	}

	@RequestMapping(value = "/submitCreditApply", method = RequestMethod.POST)
	public String submitCreditApply(String companyData,
			String companyAppendData, String companyEnsureData,
			String companyAccessoryData) {

		System.out.println(companyData);
		System.out.println(companyAppendData);
		System.out.println(companyEnsureData);
		System.out.println(companyAccessoryData);
		return "/loan/credit-apply/credit-apply-result";
	}
}
