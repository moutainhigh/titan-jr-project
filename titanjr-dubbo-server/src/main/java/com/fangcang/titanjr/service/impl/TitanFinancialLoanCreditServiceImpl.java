package com.fangcang.titanjr.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.merchant.api.MerchantFacade;
import com.fangcang.merchant.query.dto.MerchantDetailQueryDTO;
import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.titanjr.common.enums.AuditResultEnum;
import com.fangcang.titanjr.common.enums.FileTypeEnum;
import com.fangcang.titanjr.common.enums.entity.LoanCreditOrderEnum;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.FileHelp;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.dao.LoanCompanyEnsureDao;
import com.fangcang.titanjr.dao.LoanCreditCompanyDao;
import com.fangcang.titanjr.dao.LoanCreditOpinionDao;
import com.fangcang.titanjr.dao.LoanCreditOrderDao;
import com.fangcang.titanjr.dao.LoanPersonEnsureDao;
import com.fangcang.titanjr.dto.bean.CreditCompanyInfoDTO;
import com.fangcang.titanjr.dto.bean.LoanCompanyAppendInfo;
import com.fangcang.titanjr.dto.bean.LoanCompanyEnsureBean;
import com.fangcang.titanjr.dto.bean.LoanCreditCompanyBean;
import com.fangcang.titanjr.dto.bean.LoanCreditOpinionBean;
import com.fangcang.titanjr.dto.bean.LoanCreditOrderBean;
import com.fangcang.titanjr.dto.bean.LoanPersonEnsureBean;
import com.fangcang.titanjr.dto.request.ApplyLoanCreditRequest;
import com.fangcang.titanjr.dto.request.AuditCreidtOrderRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.GetAuditEvaluationRequest;
import com.fangcang.titanjr.dto.request.GetCreditInfoRequest;
import com.fangcang.titanjr.dto.request.GetCreditOrderCountRequest;
import com.fangcang.titanjr.dto.request.LoanCreditSaveRequest;
import com.fangcang.titanjr.dto.request.QueryPageCreditCompanyInfoRequest;
import com.fangcang.titanjr.dto.response.ApplyLoanCreditResponse;
import com.fangcang.titanjr.dto.response.AuditCreidtOrderResponse;
import com.fangcang.titanjr.dto.response.FTPConfigResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.GetAuditEvaluationResponse;
import com.fangcang.titanjr.dto.response.GetCreditInfoResponse;
import com.fangcang.titanjr.dto.response.GetCreditOrderCountResponse;
import com.fangcang.titanjr.dto.response.LoanCreditSaveResponse;
import com.fangcang.titanjr.dto.response.PageCreditCompanyInfoResponse;
import com.fangcang.titanjr.entity.LoanCompanyEnsure;
import com.fangcang.titanjr.entity.LoanCreditCompany;
import com.fangcang.titanjr.entity.LoanCreditOpinion;
import com.fangcang.titanjr.entity.LoanCreditOrder;
import com.fangcang.titanjr.entity.LoanPersonEnsure;
import com.fangcang.titanjr.entity.parameter.LoanCreditOrderParam;
import com.fangcang.titanjr.rs.dto.CreditApplicationJsonData;
import com.fangcang.titanjr.rs.manager.RSCreditManager;
import com.fangcang.titanjr.rs.manager.RSFileManager;
import com.fangcang.titanjr.rs.request.OprsystemCreditCompanyRequest;
import com.fangcang.titanjr.rs.request.OrderMixserviceCreditapplicationRequest;
import com.fangcang.titanjr.rs.request.RSFsFileUploadRequest;
import com.fangcang.titanjr.rs.response.OprsystemCreditCompanyResponse;
import com.fangcang.titanjr.rs.response.RSFsFileUploadResponse;
import com.fangcang.titanjr.service.TitanCodeCenterService;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanSysconfigService;
import com.fangcang.titanjr.util.LoanTypeConvertUtil;
import com.fangcang.util.DateUtil;
import com.fangcang.util.StringUtil;

/**
 * Created by zhaoshan on 2016/11/18.
 */
@Service("titanFinancialLoanCreditService")
public class TitanFinancialLoanCreditServiceImpl implements
		TitanFinancialLoanCreditService {

	private static final Log log = LogFactory
			.getLog(TitanFinancialLoanCreditServiceImpl.class);

	@Resource
	private LoanCreditOrderDao loanCreditOrderDao;

	@Resource
	private LoanCreditCompanyDao loanCreditCompanyDao;

	@Resource
	private LoanCompanyEnsureDao loanCompanyEnsureDao;

	@Resource
	private LoanPersonEnsureDao loanPersonEnsureDao;

	@Resource
	private TitanCodeCenterService titanCodeCenterService;

	@Resource
	private TitanFinancialOrganService titanFinancialOrganService;

	private MerchantFacade merchantFacade;

	@Resource
	private HessianProxyBeanFactory hessianProxyBeanFactory;

	@Resource
	private LoanCreditOpinionDao loanCreditOpinionDao;
	
	@Resource
	private RSCreditManager rsCreditManager;
	
	@Resource
	private RSFileManager rsFileManager;
	
	@Resource
	private TitanSysconfigService titanSysconfigService;
	
	@Override
	public AuditCreidtOrderResponse auditCreditOrder(AuditCreidtOrderRequest req) {
		AuditCreidtOrderResponse response = new AuditCreidtOrderResponse();
		if(!StringUtil.isValidString(req.getOrderNo())){
			response.putErrorResult("参数[授信申请单号(orderNo)]不能为空");
			return response;
		}
		if(req.getAuditResult()==null){
			response.putErrorResult("参数[审核状态(auditResult)]不能为空");
			return response;
		}
		//TODO 校验申请单号是否存在
		
		LoanCreditOrder loanCreditOrderParam = new LoanCreditOrder();
		loanCreditOrderParam.setOrderNo(req.getOrderNo());
		List<LoanCreditOrder> loanCreditOrderList = loanCreditOrderDao.queryLoanCreditOrder(loanCreditOrderParam);
		LoanCreditOrder loanCreditOrder = loanCreditOrderList.get(0);
		
		
		if(req.getAuditResult()==AuditResultEnum.NO_PASS){
			//TODO 数据库记录原因
			
			response.putSuccess();
			return response;
		}else if(req.getAuditResult()==AuditResultEnum.PASS){
			
			
			
			//2-上传申请附件
			//申请资料附件
			LoanCreditCompany loanCreditCompanyParam = new LoanCreditCompany();
			loanCreditCompanyParam.setCreditOrderNo(req.getOrderNo());
			List<LoanCreditCompany> loanCreditCompanyList = loanCreditCompanyDao.queryLoanCreditCompany(loanCreditCompanyParam);
			
			if(CollectionUtils.isEmpty(loanCreditCompanyList)){
				response.putErrorResult("申请授信单没有找到对应的企业资料");
				return response;
			}
			//授信申请公司文件
			List<String> companyFilesList = new ArrayList<String>();
			
			LoanCreditCompany loanCreditCompany = loanCreditCompanyList.get(0);
			
			companyFilesList.add(loanCreditCompany.getLicenseUrl());
			companyFilesList.add(loanCreditCompany.getLegalNoUrl());
			companyFilesList.add(loanCreditCompany.getOfficeNoUrl());
			companyFilesList.add(loanCreditCompany.getAccountUrl());
			companyFilesList.add(loanCreditCompany.getCreditUrl());
			companyFilesList.add(loanCreditCompany.getOfficeUrl());
			companyFilesList.add(loanCreditCompany.getWaterUrl());
			companyFilesList.add(loanCreditCompany.getTaxRegUrl());
			companyFilesList.add(loanCreditCompany.getOrgCodeUrl());
			
			//担保人文件 
			List<String> ensureFilesList = new ArrayList<String>();
			//个人
			if(loanCreditOrder.getAssureType()==LoanCreditOrderEnum.AssureType.PERSON.getType()){
				LoanPersonEnsure loanPersonEnsureParam = new LoanPersonEnsure();
				loanPersonEnsureParam.setOrderNo(req.getOrderNo());
				List<LoanPersonEnsure> loanPersonEnsureList = loanPersonEnsureDao.queryLoanPersonEnsure(loanPersonEnsureParam);
				if(CollectionUtils.isEmpty(loanPersonEnsureList)){
					response.putErrorResult("个人担保资料不存在，请补充担保资料");
					return response;
				}
				LoanPersonEnsure loanPersonEnsure = loanPersonEnsureList.get(0);
				ensureFilesList.add(loanPersonEnsure.getIdCardUrl());
				ensureFilesList.add(loanPersonEnsure.getRegisteredUrl());
				ensureFilesList.add(loanPersonEnsure.getSpouseRegisteredUrl());
				ensureFilesList.add(loanPersonEnsure.getSpouseIdCardUrl());
				ensureFilesList.add(loanPersonEnsure.getMarriageUrl());
			}
			
			//企业
			if(loanCreditOrder.getAssureType()==LoanCreditOrderEnum.AssureType.ENTERPRISE.getType()){
				LoanCompanyEnsure loanCompanyEnsureParam = new LoanCompanyEnsure();
				loanCompanyEnsureParam.setOrderNo(req.getOrderNo());
				List<LoanCompanyEnsure> loanCompanyEnsureList = loanCompanyEnsureDao.queryLoanCompanyEnsure(loanCompanyEnsureParam);
				if(CollectionUtils.isEmpty(loanCompanyEnsureList)){
					response.putErrorResult("企业担保资料不存在，请补充担保资料");
					return response;
				}
				LoanCompanyEnsure loanCompanyEnsure = loanCompanyEnsureList.get(0);
				ensureFilesList.add(loanCompanyEnsure.getBusinessLicenseUrl());
				ensureFilesList.add(loanCompanyEnsure.getOrgCodeCertificateUrl());
				ensureFilesList.add(loanCompanyEnsure.getTaxRegisterCodeUrl());
				ensureFilesList.add(loanCompanyEnsure.getLicenseUrl());
				ensureFilesList.add(loanCompanyEnsure.getLegalPersonUrl());
			}
			
			 
			//企业证件资料本地路径
			String orgCreditFileRootDir = "";
			try {
				
				//企业证件资料本地路径
				orgCreditFileRootDir = TitanFinancialLoanCreditServiceImpl.class.getClassLoader().getResource("").getPath()+"/tmp"+File.separator+FtpUtil.UPLOAD_PATH_CREDIT_APPLY+"/"+loanCreditOrder.getOrgCode();
				String orgCreditDir = "EnterpriseCreditPackage";
				String localEnterpriseDocumentInfoPath = orgCreditFileRootDir+"/"+orgCreditDir+"/"+"EnterpriseDocumentInfo";
				
				String localGuarantorDocumentsInfoPath =  orgCreditFileRootDir+"/"+orgCreditDir+"/"+"GuarantorDocumentsInfo";
				//先删除旧的临时文件
				FileHelp.deleteFile(orgCreditFileRootDir);
				
				FtpUtil.makeLocalDirectory(localEnterpriseDocumentInfoPath);
				FtpUtil.makeLocalDirectory(localGuarantorDocumentsInfoPath);
				
				
				//下载文件
				FTPConfigResponse ftpConfigResponse = titanSysconfigService.getFTPConfig();
				FtpUtil ftpUtil = new FtpUtil(ftpConfigResponse.getFtpServerIp(),ftpConfigResponse.getFtpServerPort(),ftpConfigResponse.getFtpServerUser(),ftpConfigResponse.getFtpServerPassword());
				ftpUtil.ftpLogin();
				//公司证件资料
				for(String file : companyFilesList){
					ftpUtil.downloadFile(file, localEnterpriseDocumentInfoPath, FtpUtil.baseLocation+FtpUtil.UPLOAD_PATH_CREDIT_APPLY+"/"+loanCreditOrder.getOrgCode());
				}
				//担保人证件资料
				for(String file : ensureFilesList){
					ftpUtil.downloadFile(file, localGuarantorDocumentsInfoPath, FtpUtil.baseLocation+FtpUtil.UPLOAD_PATH_CREDIT_APPLY+"/"+loanCreditOrder.getOrgCode());
				}
				
				ftpUtil.ftpLogOut();
				
				//压缩打包文件
				File srcZipFile = FileHelp.zipFile(orgCreditFileRootDir+"/"+orgCreditDir,orgCreditDir+"_src.zip");
				
				//加密
				String encryptFilePath = orgCreditFileRootDir+"/"+orgCreditDir+".zip";
				FileHelp.encryptRSFile(srcZipFile, encryptFilePath);
				
				//上传到融数
				RSFsFileUploadRequest rsFsFileUploadRequest = new RSFsFileUploadRequest();
				rsFsFileUploadRequest.setUserid(loanCreditOrder.getOrgCode());
				rsFsFileUploadRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
				rsFsFileUploadRequest.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID_230);
				rsFsFileUploadRequest.setType(FileTypeEnum.UPLOAD_FILE_73.getFileType());
				rsFsFileUploadRequest.setInvoiceDate(DateUtil.getCurrentDate());
				rsFsFileUploadRequest.setPath(encryptFilePath);
				rsFsFileUploadRequest.setBacth(loanCreditOrder.getOrgCode()+DateUtil.getCurrentDate().getTime());
				
				RSFsFileUploadResponse rsFsFileUploadResponse = rsFileManager.fsFileUpload(rsFsFileUploadRequest);
				if((!StringUtil.isValidString(rsFsFileUploadResponse.getUrlKey()))||rsFsFileUploadResponse.isSuccess()==false){
					response.putErrorResult(rsFsFileUploadResponse.getReturnMsg());
					return response;
				}
				System.out.println("urlkey："+rsFsFileUploadResponse.getUrlKey());
				//1-提交企业资料
				
				OprsystemCreditCompanyRequest creditCompanyRequest = new OprsystemCreditCompanyRequest();
				creditCompanyRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
				creditCompanyRequest.setUserid(loanCreditOrder.getOrgCode());
				
				creditCompanyRequest.setCompanyname(loanCreditCompany.getName());
				creditCompanyRequest.setBusinesslicense(loanCreditCompany.getLicense());
				//TODO 四个参数暂时都写死，后面再动态取
				creditCompanyRequest.setCertificatestartdate(DateUtil.dateToString(new Date(), "yyyy-MM-dd"));
				creditCompanyRequest.setCertificateexpiredate(DateUtil.dateToString(DateUtils.addYears(new Date(), 10), "yyyy-MM-dd"));
				creditCompanyRequest.setCompanytype("1");
				creditCompanyRequest.setRegistfinance("10000000");
				creditCompanyRequest.setAddress(loanCreditCompany.getRegAddress());
				creditCompanyRequest.setTaxregcard(loanCreditCompany.getTaxRegNo());
				
				creditCompanyRequest.setCertificatetype(loanCreditCompany.getLegalceType().toString());
				creditCompanyRequest.setCertificatenumber(loanCreditCompany.getLegalNo());
				OprsystemCreditCompanyResponse oprsystemCreditCompanyResponse = rsCreditManager.oprsystemCreditCompany(creditCompanyRequest);
				if(oprsystemCreditCompanyResponse.isSuccess()==false){
					response.putErrorResult(oprsystemCreditCompanyResponse.getReturnMsg());
					return response;
				}
				//3-授信申请接口
				OrderMixserviceCreditapplicationRequest orderMixserviceCreditapplicationRequest = new OrderMixserviceCreditapplicationRequest();
				
				
				orderMixserviceCreditapplicationRequest.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
				orderMixserviceCreditapplicationRequest.setUserid(loanCreditOrder.getOrgCode());
				orderMixserviceCreditapplicationRequest.setUserorderid(req.getOrderNo());
				orderMixserviceCreditapplicationRequest.setAmount(loanCreditOrder.getAmount()!=null?loanCreditOrder.getAmount().toString():"0");
				//orderMixserviceCreditapplicationRequest.setReqesttime(reqesttime);
				orderMixserviceCreditapplicationRequest.setOrderplatformname(loanCreditCompany.getName());
				orderMixserviceCreditapplicationRequest.setRequestdate(DateUtil.dateToString(new Date()));
				//orderMixserviceCreditapplicationRequest.setRatetemplrate(ratetemplrate);
				//orderMixserviceCreditapplicationRequest.setJsondata(jsondata);
				orderMixserviceCreditapplicationRequest.setUrlkey(rsFsFileUploadResponse.getUrlKey());
				orderMixserviceCreditapplicationRequest.setCreditype("2");
				//jsondata
				
				
//				LoanCreditCompany loanCreditCompany = new LoanCreditCompany();
//				loanCreditCompany.setCreditOrderNo(qCrditOrderInfo.getOrderNo());
//				List<LoanCreditCompany> creditCompanies = loanCreditCompanyDao
//						.queryLoanCreditCompany(loanCreditCompany);
//
//				if (CollectionUtils.isNotEmpty(creditCompanies)) {
//					LoanCreditCompany creditCompany = creditCompanies.get(0);
//
//					LoanCreditCompanyBean companyBean = LoanTypeConvertUtil
//							.getLoanCreditCompanyBean(creditCompany);
//					LoanCompanyAppendInfo companyAppendInfo = LoanTypeConvertUtil
//							.getCompanyAppendInfo(creditCompany.getAppendInfo());
//
//
//				}
				
				
				
				
				//rsCreditManager.orderMixserviceCreditapplication(orderMixserviceCreditapplicationRequest);
				
				
			} catch (Exception e) {
				e.printStackTrace();
				response.putErrorResult("上传失败");
				
			}finally{
				// 删除本地文件
				if(StringUtil.isValidString(orgCreditFileRootDir)){
					FileHelp.deleteFile(orgCreditFileRootDir);
				}
				
			}
			
			
			response.putSuccess();
			
			
			
			
		}
		
		return response;
	}
	
	private String json(LoanCompanyAppendInfo companyAppendInfo){
		
		CreditApplicationJsonData creditJsonData = new CreditApplicationJsonData();
		
		creditJsonData.setRootInstCd(CommonConstant.RS_FANGCANG_CONST_ID);
        creditJsonData.setPassportNumber("27889998987");///*****************无法提供
        creditJsonData.setWorkPhone("0755-88884444");
        
        creditJsonData.setPlatformRegistTime("2015-01-10 00:00:00");
        creditJsonData.setPlatformOperaNo("M10000001");
        creditJsonData.setOperatNumberEmployees("50");
        creditJsonData.setLeaseTerm("2");
        creditJsonData.setLeaseAddress("向荣大厦");
        creditJsonData.setHousingArea("1000");
        creditJsonData.setRental("130000");
        creditJsonData.setPaymentMethod("网上转账");
        creditJsonData.setRemark("经营信息");
		
		return "";
	}
	
	
	@Override
	public GetCreditInfoResponse getCreditOrderInfo(GetCreditInfoRequest req) {

		GetCreditInfoResponse creditInfoResponse = new GetCreditInfoResponse();

		LoanCreditOrder loanCreditOrder = new LoanCreditOrder();
		loanCreditOrder.setOrgCode(req.getOrgCode());
		loanCreditOrder.setOrderNo(req.getOrderNo());

		List<LoanCreditOrder> creditOrders = loanCreditOrderDao
				.queryLoanCreditOrder(loanCreditOrder);

		LoanCreditOrder qCrditOrderInfo = null;
		if (CollectionUtils.isNotEmpty(creditOrders)) {
			qCrditOrderInfo = creditOrders.get(0);
		}

		if (qCrditOrderInfo == null) {

			// // 以下针对首次进入授信审核带出所有参数列表值
			FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
			organQueryRequest.setUserId(req.getOrgCode());
			FinancialOrganResponse financialOrganResponse = titanFinancialOrganService
					.queryFinancialOrgan(organQueryRequest);

			MerchantDetailQueryDTO queryDTO = new MerchantDetailQueryDTO();
			queryDTO.setMerchantCode(financialOrganResponse
					.getFinancialOrganDTO().getOrgBindInfo().getMerchantCode());
			MerchantResponseDTO merchantResponseDTO = this.getMerchantFacade()
					.queryMerchantDetail(queryDTO);

			creditInfoResponse.setCreditCompany(new LoanCreditCompanyBean());
			creditInfoResponse.getCreditCompany().setRegAccount(
					merchantResponseDTO.getMerchantCode());
			creditInfoResponse.getCreditCompany().setRegDate(
					DateUtil.dateToString(merchantResponseDTO.getCreatedate()));
			creditInfoResponse.getCreditCompany().setName(
					financialOrganResponse.getFinancialOrganDTO().getOrgName());
			creditInfoResponse.getCreditCompany()
					.setLicense(
							financialOrganResponse.getFinancialOrganDTO()
									.getBuslince());

			return creditInfoResponse;
		}

		LoanCreditOrderBean creditOrderBean = LoanTypeConvertUtil
				.getLoanCreditOrderBean(qCrditOrderInfo);
		creditInfoResponse.setCreditOrder(creditOrderBean);

		LoanCreditCompany loanCreditCompany = new LoanCreditCompany();
		loanCreditCompany.setCreditOrderNo(qCrditOrderInfo.getOrderNo());
		List<LoanCreditCompany> creditCompanies = loanCreditCompanyDao
				.queryLoanCreditCompany(loanCreditCompany);

		if (CollectionUtils.isNotEmpty(creditCompanies)) {
			LoanCreditCompany creditCompany = creditCompanies.get(0);

			LoanCreditCompanyBean companyBean = LoanTypeConvertUtil
					.getLoanCreditCompanyBean(creditCompany);
			LoanCompanyAppendInfo companyAppendInfo = LoanTypeConvertUtil
					.getCompanyAppendInfo(creditCompany.getAppendInfo());

			creditInfoResponse.setCompanyAppendInfo(companyAppendInfo);
			creditInfoResponse.setCreditCompany(companyBean);

		}

		if (qCrditOrderInfo.getAssureType() != null
				&& qCrditOrderInfo.getAssureType() == 2) {

			LoanCompanyEnsure loanCompanyEnsure = new LoanCompanyEnsure();
			loanCompanyEnsure.setOrgCode(qCrditOrderInfo.getOrgCode());
			loanCompanyEnsure.setOrderNo(qCrditOrderInfo.getOrderNo());
			List<LoanCompanyEnsure> companyEnsures = loanCompanyEnsureDao
					.queryLoanCompanyEnsure(loanCompanyEnsure);
			if (CollectionUtils.isNotEmpty(companyEnsures)) {
				LoanCompanyEnsure companyEnsure = companyEnsures.get(0);

				LoanCompanyEnsureBean companyEnsureBean = LoanTypeConvertUtil
						.getLoanCompanyEnsureBean(companyEnsure);
				// 基本信息
				companyEnsureBean.setOrderNo(qCrditOrderInfo.getOrderNo());
				companyEnsureBean.setOrgCode(qCrditOrderInfo.getOrgCode());

				creditInfoResponse.setCompanyEnsure(companyEnsureBean);
			}

		} else if (qCrditOrderInfo.getAssureType() != null
				&& qCrditOrderInfo.getAssureType() == 1) {

			LoanPersonEnsure loanPersonEnsure = new LoanPersonEnsure();
			loanPersonEnsure.setOrgCode(qCrditOrderInfo.getOrgCode());
			loanPersonEnsure.setOrderNo(qCrditOrderInfo.getOrderNo());
			List<LoanPersonEnsure> personEnsures = loanPersonEnsureDao
					.queryLoanPersonEnsure(loanPersonEnsure);

			if (CollectionUtils.isNotEmpty(personEnsures)) {
				LoanPersonEnsure personEnsure = personEnsures.get(0);
				LoanPersonEnsureBean ensureBean = LoanTypeConvertUtil
						.getLoanPersonEnsureBean(personEnsure);
				ensureBean.setOrgCode(qCrditOrderInfo.getOrgCode());
				ensureBean.setOrderNo(qCrditOrderInfo.getOrderNo());
				creditInfoResponse.setLoanPersonEnsure(ensureBean);
			}
		}
		creditInfoResponse.setOrderNo(qCrditOrderInfo.getOrderNo());
		creditInfoResponse.putSuccess();
		return creditInfoResponse;
	}

	@Override
	public LoanCreditSaveResponse saveCreditOrder(LoanCreditSaveRequest req) {

		LoanCreditSaveResponse creditSaveResponse = new LoanCreditSaveResponse();

		if (req == null || (!StringUtil.isValidString(req.getOrgCode()))) {
			log.error("saveCreditOrder orgCode Can't be empty");
			// 参数有误，一定需要存在creditOrder
			creditSaveResponse.putParamError();
			return creditSaveResponse;
		}

		LoanCreditOrder loanCreditOrder = new LoanCreditOrder();
		loanCreditOrder.setOrgCode(req.getOrgCode());
		List<LoanCreditOrder> creditOrders = loanCreditOrderDao
				.queryLoanCreditOrder(loanCreditOrder);

		LoanCreditOrder qCrditOrderInfo = null;
		if (CollectionUtils.isNotEmpty(creditOrders)) {
			qCrditOrderInfo = creditOrders.get(0);
		}

		LoanCreditOrder creditOrder = LoanTypeConvertUtil
				.getLoanCreditOrder(req.getCreditOrder());

		String orderNo = null;

		if (creditOrder == null && qCrditOrderInfo == null) {

			orderNo = titanCodeCenterService.createLoanCreditOrderNo();
			creditOrder = LoanTypeConvertUtil.createDefaultCreditOrder(orderNo,
					req.getOrgCode());
			loanCreditOrderDao.saveLoanCreditOrder(creditOrder);

		} else if (creditOrder != null && qCrditOrderInfo == null) {

			orderNo = titanCodeCenterService.createLoanCreditOrderNo();
			creditOrder.setOrderNo(orderNo);
			loanCreditOrderDao.saveLoanCreditOrder(creditOrder);

		} else if (creditOrder != null && qCrditOrderInfo != null) {
			orderNo = qCrditOrderInfo.getOrderNo();
			creditOrder.setOrderNo(qCrditOrderInfo.getOrderNo());
			loanCreditOrderDao.updateLoanCreditOrder(creditOrder);

			// 更新批注状态为已修改
			if (creditOrder.getStatus() != null && creditOrder.getStatus() == 2) {
				LoanCreditOpinion loanCreditOpinion = new LoanCreditOpinion();
				loanCreditOpinion.setOrderNo(orderNo);
				loanCreditOpinion.setStatus(2);
				loanCreditOpinionDao.updateLoanCreditOpinion(loanCreditOpinion);
			}
		} else if (qCrditOrderInfo != null && orderNo == null) {
			if (creditOrder != null) {
				creditOrder.setOrderNo(orderNo);
			}
			orderNo = qCrditOrderInfo.getOrderNo();
		}

		// 以下三个针对非授信申请单相关信息，数据库存在并传过来的数据不为空，则更新，否则新增
		LoanCreditCompany loanCreditCompany = LoanTypeConvertUtil
				.getLoanCreditCompany(req.getCreditCompany(),
						req.getCompanyAppendInfo());

		if (loanCreditCompany == null && req.getCompanyAppendInfo() != null) {
			LoanCreditCompany creditCompanyQuery = new LoanCreditCompany();
			creditCompanyQuery.setCreditOrderNo(orderNo);
			List<LoanCreditCompany> creditCompanies = loanCreditCompanyDao
					.queryLoanCreditCompany(creditCompanyQuery);

			if (CollectionUtils.isNotEmpty(creditCompanies)) {
				creditCompanies.get(0).setAppendInfo(
						JsonConversionTool.toJson(req.getCompanyAppendInfo()));

				loanCreditCompanyDao
						.updateCreditCompany(creditCompanies.get(0));
			}
		}

		if (null != loanCreditCompany) {
			LoanCreditCompany creditCompanyQuery = new LoanCreditCompany();
			creditCompanyQuery.setCreditOrderNo(orderNo);
			List<LoanCreditCompany> creditCompanies = loanCreditCompanyDao
					.queryLoanCreditCompany(creditCompanyQuery);
			loanCreditCompany.setCreditOrderNo(orderNo);
			if (CollectionUtils.isNotEmpty(creditCompanies)) {
				loanCreditCompanyDao.updateCreditCompany(loanCreditCompany);
			} else {
				loanCreditCompanyDao.saveCreditCompany(loanCreditCompany);
			}
		}

		LoanPersonEnsure personEnsure = LoanTypeConvertUtil
				.getLoanPersonEnsure(req.getLoanPersonEnsure());
		if (null != personEnsure) {// 此时才处理
			LoanPersonEnsure personEnsureQuery = new LoanPersonEnsure();
			personEnsureQuery.setOrderNo(orderNo);
			personEnsureQuery.setOrgCode(req.getOrgCode());
			List<LoanPersonEnsure> loanPersonEnsures = loanPersonEnsureDao
					.queryLoanPersonEnsure(personEnsureQuery);
			// 通过orderNo关联
			personEnsure.setOrderNo(orderNo);
			personEnsure.setOrgCode(req.getOrgCode());

			LoanCreditOrder creditOrder2 = new LoanCreditOrder();
			creditOrder2.setOrderNo(orderNo);
			creditOrder2.setAssureType(1);
			loanCreditOrderDao.updateLoanCreditOrder(creditOrder2);

			if (CollectionUtils.isNotEmpty(loanPersonEnsures)) {
				loanPersonEnsureDao.updateLoanPersonEnsure(personEnsure);
			} else {
				loanPersonEnsureDao.saveLoanPersonEnsure(personEnsure);
			}
		}

		LoanCompanyEnsure companyEnsure = LoanTypeConvertUtil
				.getLoanCompanyEnsure(req.getCompanyEnsure());
		if (null != companyEnsure) {// 此时才处理
			LoanCompanyEnsure companyEnsureQuery = new LoanCompanyEnsure();
			companyEnsureQuery.setOrderNo(orderNo);
			companyEnsureQuery.setOrgCode(req.getOrgCode());
			List<LoanCompanyEnsure> loanCompanyEnsures = loanCompanyEnsureDao
					.queryLoanCompanyEnsure(companyEnsureQuery);
			// 通过userid关联
			companyEnsure.setOrderNo(orderNo);
			companyEnsure.setOrgCode(req.getOrgCode());

			LoanCreditOrder creditOrder2 = new LoanCreditOrder();
			creditOrder2.setOrderNo(orderNo);
			creditOrder2.setAssureType(2);
			loanCreditOrderDao.updateLoanCreditOrder(creditOrder2);

			if (CollectionUtils.isNotEmpty(loanCompanyEnsures)) {
				loanCompanyEnsureDao.updateCompanyEnsure(companyEnsure);
			} else {
				loanCompanyEnsureDao.saveCompanyEnsure(companyEnsure);
			}
		}

		creditSaveResponse.putSuccess();
		return creditSaveResponse;
	}

	@Override
	public ApplyLoanCreditResponse applyLoanCredit(ApplyLoanCreditRequest req) {

		return null;
	}

	@Override
	public PageCreditCompanyInfoResponse queryPageCreditCompanyInfo(
			QueryPageCreditCompanyInfoRequest req) {
		PageCreditCompanyInfoResponse response = new PageCreditCompanyInfoResponse();
		LoanCreditOrderParam condition = new LoanCreditOrderParam();
		condition.setName(req.getName());
		condition.setContactName(req.getContactName());
		condition.setStatus(req.getStatus());
		PaginationSupport<CreditCompanyInfoDTO> paginationSupport = new PaginationSupport<CreditCompanyInfoDTO>();
		paginationSupport.setCurrentPage(req.getCurrentPage());
		paginationSupport.setOrderBy(" createTime desc ");
		paginationSupport = loanCreditOrderDao.selectForPage(condition,
				paginationSupport);
		response.setPageCreditCompanyInfoDTO(paginationSupport);
		response.setResult(true);
		return response;

	}

	@Override
	public GetCreditOrderCountResponse getCreditOrderCount(
			GetCreditOrderCountRequest request) {
		GetCreditOrderCountResponse response = new GetCreditOrderCountResponse();
		LoanCreditOrderParam condition = new LoanCreditOrderParam();
		condition.setStatus(request.getStatus());
		int count = loanCreditOrderDao.getCreditOrderCount(condition);
		response.setCount(count);
		return response;
	}

	private MerchantFacade getMerchantFacade() {
		if (null == merchantFacade) {
			merchantFacade = hessianProxyBeanFactory.getHessianProxyBean(
					MerchantFacade.class,
					ProxyFactoryConstants.merchantServerUrl + "merchantFacade");
		}
		return merchantFacade;
	}

	/**
	 * 根据授信单号获取最新的评价信息
	 */
	@Override
	public GetAuditEvaluationResponse getAuditEvaluationInfo(
			GetAuditEvaluationRequest req) {

		GetAuditEvaluationResponse auditEvaluationResponse = new GetAuditEvaluationResponse();

		LoanCreditOpinion loanCreditOpinion = new LoanCreditOpinion();
		loanCreditOpinion.setOrderNo(req.getOrderNo());
		loanCreditOpinion.setStatus(1);
		List<LoanCreditOpinion> creditOpinions = loanCreditOpinionDao
				.queryLoanCreditOpinion(loanCreditOpinion);

		if (CollectionUtils.isNotEmpty(creditOpinions)) {
			loanCreditOpinion = creditOpinions.get(0);
			LoanCreditOpinionBean creditOpinionBean = new LoanCreditOpinionBean();
			creditOpinionBean.setContent(loanCreditOpinion.getContent());
			creditOpinionBean.setCreater(loanCreditOpinion.getCreater());
			creditOpinionBean.setCreateTime(loanCreditOpinion.getCreateTime());
			creditOpinionBean.setOrderNo(loanCreditOpinion.getOrderNo());
			creditOpinionBean.setResult(loanCreditOpinion.getResult());
			creditOpinionBean.setStatus(loanCreditOpinion.getStatus());
			auditEvaluationResponse.setCreditOpinionBean(creditOpinionBean);
		}
		return auditEvaluationResponse;
	}

}
