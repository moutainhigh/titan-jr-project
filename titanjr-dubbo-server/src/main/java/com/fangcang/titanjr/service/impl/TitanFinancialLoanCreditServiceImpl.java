package com.fangcang.titanjr.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSON;
import net.sf.json.JSONSerializer;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.merchant.api.MerchantFacade;
import com.fangcang.merchant.query.dto.MerchantDetailQueryDTO;
import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.titanjr.common.enums.AuditResultEnum;
import com.fangcang.titanjr.common.enums.FileTypeEnum;
import com.fangcang.titanjr.common.enums.entity.LoanCompanyEnsureEnum;
import com.fangcang.titanjr.common.enums.entity.LoanCreditCompanyEnum;
import com.fangcang.titanjr.common.enums.entity.LoanCreditOrderEnum;
import com.fangcang.titanjr.common.enums.entity.LoanPersonEnsureEnum;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.FileHelp;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.LoanCompanyEnsureDao;
import com.fangcang.titanjr.dao.LoanCreditCompanyDao;
import com.fangcang.titanjr.dao.LoanCreditOpinionDao;
import com.fangcang.titanjr.dao.LoanCreditOrderDao;
import com.fangcang.titanjr.dao.LoanPersonEnsureDao;
import com.fangcang.titanjr.dto.bean.CreditCompanyInfoDTO;
import com.fangcang.titanjr.dto.bean.LoanCompanyAppendInfo;
import com.fangcang.titanjr.dto.bean.LoanCompanyEnsureBean;
import com.fangcang.titanjr.dto.bean.LoanCreditApplicationJsonDataBean;
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
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
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
		//校验申请单号是否存在
		LoanCreditOrder loanCreditOrderParam = new LoanCreditOrder();
		loanCreditOrderParam.setOrderNo(req.getOrderNo());
		List<LoanCreditOrder> loanCreditOrderList = loanCreditOrderDao.queryLoanCreditOrder(loanCreditOrderParam);
		
		if(CollectionUtils.isEmpty(loanCreditOrderList)){
			response.putErrorResult("["+req.getOrderNo()+"]授信申请记录不存在,请检查数据是否正确");
			return response;
		}
		LoanCreditOrder loanCreditOrder = loanCreditOrderList.get(0);
		Date now = new Date(); 
		LoanCreditOrder updateLoanCreditOrderParam = new LoanCreditOrder();
		boolean auditResult = false;
		if(req.getAuditResult()==AuditResultEnum.NO_PASS){
			auditResult = false;
		}else if(req.getAuditResult()==AuditResultEnum.PASS){
			updateLoanCreditOrderParam.setFirstAuditTime(now);
			auditResult = true;
		}
		
		//改申请单状态
		updateLoanCreditOrderParam.setOrderNo(req.getOrderNo());
		updateLoanCreditOrderParam.setStatus(req.getAuditResult().getStatus());
		loanCreditOrderDao.updateLoanCreditOrder(updateLoanCreditOrderParam);
		
		//添加审核记录
		LoanCreditOpinion loanCreditOpinion = new LoanCreditOpinion();
		loanCreditOpinion.setOrderNo(req.getOrderNo());
		loanCreditOpinion.setResult(req.getAuditResult().getStatus());
		loanCreditOpinion.setContent(req.getContent());
		loanCreditOpinion.setCreateTime(now);
		loanCreditOpinionDao.saveLoanCreditOpinion(loanCreditOpinion);
		
		//通过后给融数处理	
		if(auditResult){
			//1-上传申请附件
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
			LoanPersonEnsure loanPersonEnsure = null;
			if(loanCreditOrder.getAssureType()==LoanCreditOrderEnum.AssureType.PERSON.getType()){
				LoanPersonEnsure loanPersonEnsureParam = new LoanPersonEnsure();
				loanPersonEnsureParam.setOrderNo(req.getOrderNo());
				List<LoanPersonEnsure> loanPersonEnsureList = loanPersonEnsureDao.queryLoanPersonEnsure(loanPersonEnsureParam);
				if(CollectionUtils.isEmpty(loanPersonEnsureList)){
					response.putErrorResult("个人担保资料不存在，请补充担保资料");
					return response;
				}
				loanPersonEnsure = loanPersonEnsureList.get(0);
				ensureFilesList.add(loanPersonEnsure.getIdCardUrl());
				ensureFilesList.add(loanPersonEnsure.getRegisteredUrl());
				ensureFilesList.add(loanPersonEnsure.getSpouseRegisteredUrl());
				ensureFilesList.add(loanPersonEnsure.getSpouseIdCardUrl());
				ensureFilesList.add(loanPersonEnsure.getMarriageUrl());
			}
			
			//企业
			LoanCompanyEnsure loanCompanyEnsure = null;
			if(loanCreditOrder.getAssureType()==LoanCreditOrderEnum.AssureType.ENTERPRISE.getType()){
				LoanCompanyEnsure loanCompanyEnsureParam = new LoanCompanyEnsure();
				loanCompanyEnsureParam.setOrderNo(req.getOrderNo());
				List<LoanCompanyEnsure> loanCompanyEnsureList = loanCompanyEnsureDao.queryLoanCompanyEnsure(loanCompanyEnsureParam);
				if(CollectionUtils.isEmpty(loanCompanyEnsureList)){
					response.putErrorResult("企业担保资料不存在，请补充担保资料");
					return response;
				}
				loanCompanyEnsure = loanCompanyEnsureList.get(0);
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
				//2-提交企业资料
				OprsystemCreditCompanyRequest creditCompanyRequest = new OprsystemCreditCompanyRequest();
				creditCompanyRequest.setConstid(CommonConstant.RS_FANGCANG_CONST_ID);
				creditCompanyRequest.setUserid(loanCreditOrder.getOrgCode());
				
				creditCompanyRequest.setCompanyname(loanCreditCompany.getName());
				creditCompanyRequest.setBusinesslicense(loanCreditCompany.getLicense());
				
				creditCompanyRequest.setCertificatestartdate(DateUtil.dateToString(loanCreditCompany.getCertificateStartDate(), "yyyy-MM-dd"));
				creditCompanyRequest.setCertificateexpiredate(DateUtil.dateToString(loanCreditCompany.getCertificateExpireDate(), "yyyy-MM-dd"));
				creditCompanyRequest.setCompanytype(LoanCreditCompanyEnum.CompanyType.getEnumByType(loanCreditCompany.getCompanyType()).getDes());
				creditCompanyRequest.setRegistfinance(loanCreditCompany.getRegistFinance());
				creditCompanyRequest.setAddress(loanCreditCompany.getRegAddress());
				creditCompanyRequest.setTaxregcard(loanCreditCompany.getTaxRegNo());
				
				creditCompanyRequest.setCertificatetype(LoanCreditCompanyEnum.CertificateType.getEnumByKey(loanCreditCompany.getLegalceType()).getDes());
				creditCompanyRequest.setCertificatenumber(loanCreditCompany.getLegalNo());
				OprsystemCreditCompanyResponse oprsystemCreditCompanyResponse = rsCreditManager.oprsystemCreditCompany(creditCompanyRequest);
				if(oprsystemCreditCompanyResponse.isSuccess()==false){
					response.putErrorResult(oprsystemCreditCompanyResponse.getReturnMsg());
					return response;
				}
				//3-授信申请接口
				LoanCompanyAppendInfo companyAppendInfo = LoanTypeConvertUtil.getCompanyAppendInfo(loanCreditCompany.getAppendInfo());
				String creditApplicationJsonData = creditApplicationJson(loanCreditCompany,companyAppendInfo,loanPersonEnsure,loanCompanyEnsure);
				
				OrderMixserviceCreditapplicationRequest orderMixserviceCreditapplicationRequest = new OrderMixserviceCreditapplicationRequest();
				orderMixserviceCreditapplicationRequest.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
				orderMixserviceCreditapplicationRequest.setUserid(loanCreditOrder.getOrgCode());
				orderMixserviceCreditapplicationRequest.setUserorderid(req.getOrderNo());
				orderMixserviceCreditapplicationRequest.setAmount(loanCreditOrder.getAmount()!=null?loanCreditOrder.getAmount().toString():"0");
				orderMixserviceCreditapplicationRequest.setReqesttime(CommonConstant.RS_LOAN_CREDIT_REQUEST_TIME);
				orderMixserviceCreditapplicationRequest.setOrderplatformname(loanCreditCompany.getName());
				orderMixserviceCreditapplicationRequest.setRequestdate(DateUtil.dateToString(new Date()));
				orderMixserviceCreditapplicationRequest.setRatetemplrate(CommonConstant.RS_LOAN_CREDIT_RATETEMPL_RATE);
				orderMixserviceCreditapplicationRequest.setJsondata(creditApplicationJsonData);
				orderMixserviceCreditapplicationRequest.setUrlkey(rsFsFileUploadResponse.getUrlKey());
				orderMixserviceCreditapplicationRequest.setCreditype("2");
				
				//rsCreditManager.orderMixserviceCreditapplication(orderMixserviceCreditapplicationRequest);
				
				
			} catch (Exception e) {
				log.error("授信文件上传失败:"+Tools.gsonToString(req), e);
				response.putErrorResult("授信文件上传失败");
				return response;
			}finally{
				// 删除本地文件
				if(StringUtil.isValidString(orgCreditFileRootDir)){
					FileHelp.deleteFile(orgCreditFileRootDir);
				}
			}
		}	
		
		response.putSuccess();
		return response;
	}
	
	/**
	 * 组装授信申请jsondata
	 * @param loanCreditCompany
	 * @param companyAppendInfo
	 * @param loanPersonEnsure
	 * @param loanCompanyEnsure
	 * @return
	 */
	private String creditApplicationJson(LoanCreditCompany loanCreditCompany,LoanCompanyAppendInfo companyAppendInfo,LoanPersonEnsure loanPersonEnsure,LoanCompanyEnsure loanCompanyEnsure){
		
		LoanCreditApplicationJsonDataBean creditJsonData = new LoanCreditApplicationJsonDataBean();
		
		creditJsonData.setRootInstCd(CommonConstant.RS_FANGCANG_CONST_ID);
        creditJsonData.setPassportNumber("27889998987");///*****************无法提供
        creditJsonData.setWorkPhone(loanCreditCompany.getContactPhone());
        
        creditJsonData.setPlatformRegistTime(DateUtil.dateToString(loanCreditCompany.getRegDate()));
        creditJsonData.setPlatformOperaNo(loanCreditCompany.getOrgCode());
        creditJsonData.setOperatNumberEmployees(LoanCreditCompanyEnum.OrgSize.getEnumBySizeType(loanCreditCompany.getOrgSize()).getDes());
        creditJsonData.setLeaseTerm(companyAppendInfo.getCompanyLease().getBeginLeaseDate()+" 到 "+companyAppendInfo.getCompanyLease().getEndLeaseDate() );
        creditJsonData.setLeaseAddress(companyAppendInfo.getCompanyLease().getLeaseAddress());
        
        creditJsonData.setHousingArea(companyAppendInfo.getCompanyLease().getHousingArea());
        creditJsonData.setRental(companyAppendInfo.getCompanyLease().getRental()+"元/年");
        creditJsonData.setPaymentMethod(companyAppendInfo.getCompanyLease().getPaymentMethod());
        creditJsonData.setRemark(companyAppendInfo.getCompanyLease().getRemark());
		
        
        creditJsonData.setMainBusinessData(companyAppendInfo.getMainBusinessDatas());
        creditJsonData.setCooperationCompanyInfo(companyAppendInfo.getCooperationCompanyInfos());
        creditJsonData.setControllData(companyAppendInfo.getControllDatas());
        //个人担保
        if(loanPersonEnsure!=null){
        	creditJsonData.setApplicateName_p(loanPersonEnsure.getPersonName());
            creditJsonData.setApplicateCardId_p(loanPersonEnsure.getNationalIdentityNumber());
            creditJsonData.setWorkUnit_p(loanPersonEnsure.getWorkCompany());
            creditJsonData.setWorkStatus_p(loanPersonEnsure.getIndustry());
            creditJsonData.setOccupation_p(loanPersonEnsure.getOccupation());
            creditJsonData.setCourseOpenTime_p(LoanPersonEnsureEnum.YearsWorking.getEnumByType(loanPersonEnsure.getYearsWorking()).getDes());
            creditJsonData.setAnnualIncome_p("");//暂无提供
            creditJsonData.setPhoneNumber_p(loanPersonEnsure.getMobileNumber());
            creditJsonData.setFamilyFixed_p(loanPersonEnsure.getWorkTelephone());
            creditJsonData.setPlaceOfOrigin_p(loanPersonEnsure.getNativePlace());
            creditJsonData.setEmail_p("");//暂无提供
            creditJsonData.setAcademy_p(loanPersonEnsure.getGraduateSchool());
            creditJsonData.setEducation_p(LoanPersonEnsureEnum.HighestEducation.getEnumByType(loanPersonEnsure.getHighestEducation()).getDes());
            creditJsonData.setAddress_p(loanPersonEnsure.getCurrentLiveAddress());
            creditJsonData.setHouseNature_p(LoanPersonEnsureEnum.HousePropertyType.getEnumByType(loanPersonEnsure.getHousePropertyType()).getDes());
            creditJsonData.setMarriageStatus_p(LoanPersonEnsureEnum.MarriageStatus.getEnumByStatus(loanPersonEnsure.getMarriageStatus()).getDes());
            creditJsonData.setChildrenStatus_p(LoanPersonEnsureEnum.HaveChildren.getEnumByType(loanPersonEnsure.getHaveChildren()).getDes());
            creditJsonData.setFirstContactName_p(loanPersonEnsure.getFirstContactName());
            creditJsonData.setFirstContactPhone_p(loanPersonEnsure.getFirstContactPhone());
            creditJsonData.setFirstContactRelations_p(LoanPersonEnsureEnum.RelationToGuarantee.getEnumByType(loanPersonEnsure.getRelationToGuarantee1()).getDes());
            creditJsonData.setSecondContactName_p(loanPersonEnsure.getSecondContactName());
            creditJsonData.setSecondContactPhone_p(loanPersonEnsure.getSecondContactPhone());
            creditJsonData.setSecondContactRelations_p(LoanPersonEnsureEnum.RelationToGuarantee.getEnumByType(loanPersonEnsure.getRelationToGuarantee2()).getDes());
            creditJsonData.setRoomSituation_p(LoanPersonEnsureEnum.CarPropertyType.getEnumByType(loanPersonEnsure.getCarPropertyType()).getDes());
            creditJsonData.setCarBrandModel_p(loanPersonEnsure.getCarBrand());
            creditJsonData.setCarValue_p("");//暂无提供
            creditJsonData.setBuyCarYear_p("");//暂无提供
            creditJsonData.setOtherAssets(loanPersonEnsure.getOtherProperty());
            creditJsonData.setRelatedNote(loanPersonEnsure.getPropertyRemark());
        	
        }
        //企业担保
        if(loanCompanyEnsure!=null){
        	creditJsonData.setCompanyName_c(loanCompanyEnsure.getCompanyName());
            creditJsonData.setBusinessExpire_c("");//暂无提供
            creditJsonData.setCompanyType_c("");//暂无提供
            creditJsonData.setRegisteredCapital_c("");//暂无提供
            creditJsonData.setEmployeesNumber_c(LoanCreditCompanyEnum.OrgSize.getEnumBySizeType(loanCompanyEnsure.getEnterpriseScale()).getDes());
            creditJsonData.setRegisteredAddress_c(loanCompanyEnsure.getRegAddress());
            creditJsonData.setOfficeAddress_c(loanCompanyEnsure.getOfficeAddress());
            creditJsonData.setSetUpDate_c(DateUtil.dateToString(loanCompanyEnsure.getFoundDate()));
            creditJsonData.setBusinessLicenseNumber_c(loanCompanyEnsure.getBusinessLicense());
            creditJsonData.setTaxregCard_c(loanCompanyEnsure.getTaxRegisterCode());
            creditJsonData.setOrganizaCertificateNo_c(loanCompanyEnsure.getOrgCodeCertificate());
            creditJsonData.setPlatformOperaNo_c(loanCompanyEnsure.getRegisterAccount());
            creditJsonData.setPlatformRegistTime_c(DateUtil.dateToString(loanCompanyEnsure.getRegisterDate()));
            creditJsonData.setLegalPersonName_c(loanCompanyEnsure.getLegalPersonName());
            creditJsonData.setPersonCertificate_c(LoanCreditCompanyEnum.CertificateType.getEnumByKey(loanCompanyEnsure.getLegalPersonCertificateType()).getDes());
            creditJsonData.setApplicateCardId_c(loanCompanyEnsure.getLegalPersonCertificateNumber());
            creditJsonData.setPrimaryContactName_c(loanCompanyEnsure.getContactName());
            creditJsonData.setPrimaryContactPhoneNumber(loanCompanyEnsure.getContactPhone());
        	
        }
        JSON result = JSONSerializer.toJSON(creditJsonData);
        return result.toString();
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
