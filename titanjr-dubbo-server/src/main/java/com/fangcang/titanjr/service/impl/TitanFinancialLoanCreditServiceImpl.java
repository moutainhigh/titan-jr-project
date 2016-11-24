package com.fangcang.titanjr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.merchant.api.MerchantFacade;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.dao.LoanCompanyEnsureDao;
import com.fangcang.titanjr.dao.LoanCreditCompanyDao;
import com.fangcang.titanjr.dao.LoanCreditOrderDao;
import com.fangcang.titanjr.dao.LoanPersonEnsureDao;
import com.fangcang.titanjr.dto.bean.CreditCompanyInfoDTO;
import com.fangcang.titanjr.dto.bean.LoanCompanyAppendInfo;
import com.fangcang.titanjr.dto.bean.LoanCompanyEnsureBean;
import com.fangcang.titanjr.dto.bean.LoanCreditCompanyBean;
import com.fangcang.titanjr.dto.bean.LoanCreditOrderBean;
import com.fangcang.titanjr.dto.bean.LoanPersonEnsureBean;
import com.fangcang.titanjr.dto.request.ApplyLoanCreditRequest;
import com.fangcang.titanjr.dto.request.AuditCreidtOrderRequest;
import com.fangcang.titanjr.dto.request.GetCreditInfoRequest;
import com.fangcang.titanjr.dto.request.GetCreditOrderCountRequest;
import com.fangcang.titanjr.dto.request.LoanCreditSaveRequest;
import com.fangcang.titanjr.dto.request.QueryPageCreditCompanyInfoRequest;
import com.fangcang.titanjr.dto.response.ApplyLoanCreditResponse;
import com.fangcang.titanjr.dto.response.AuditCreidtOrderResponse;
import com.fangcang.titanjr.dto.response.GetCreditInfoResponse;
import com.fangcang.titanjr.dto.response.GetCreditOrderCountResponse;
import com.fangcang.titanjr.dto.response.LoanCreditSaveResponse;
import com.fangcang.titanjr.dto.response.PageCreditCompanyInfoResponse;
import com.fangcang.titanjr.entity.LoanCompanyEnsure;
import com.fangcang.titanjr.entity.LoanCreditCompany;
import com.fangcang.titanjr.entity.LoanCreditOrder;
import com.fangcang.titanjr.entity.LoanPersonEnsure;
import com.fangcang.titanjr.entity.parameter.LoanCreditOrderParam;
import com.fangcang.titanjr.service.TitanCodeCenterService;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
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

	@Override
	public AuditCreidtOrderResponse auditCreditOrder(AuditCreidtOrderRequest req) {
		return null;
	}

	@Override
	public GetCreditInfoResponse getCreditOrderInfo(GetCreditInfoRequest req) {

		GetCreditInfoResponse creditInfoResponse = new GetCreditInfoResponse();

		LoanCreditOrder loanCreditOrder = new LoanCreditOrder();
		loanCreditOrder.setOrgCode(req.getOrgCode());

		List<LoanCreditOrder> creditOrders = loanCreditOrderDao
				.queryLoanCreditOrder(loanCreditOrder);

		LoanCreditOrder qCrditOrderInfo = null;
		if (CollectionUtils.isNotEmpty(creditOrders)) {
			qCrditOrderInfo = creditOrders.get(0);
		}

		if (qCrditOrderInfo == null) {
			return creditInfoResponse;
		}

		LoanCreditOrderBean creditOrderBean = new LoanCreditOrderBean();

		creditOrderBean.setDayLimit(qCrditOrderInfo.getDayLimit());//
		creditOrderBean.setAmount(qCrditOrderInfo.getAmount());
		creditOrderBean.setActualAmount(qCrditOrderInfo.getActualAmount());
		creditOrderBean.setReqTime(qCrditOrderInfo.getReqTime());
		creditOrderBean.setRateTem(qCrditOrderInfo.getRateTem());//
		creditOrderBean.setRspId(qCrditOrderInfo.getRspId());//
		creditOrderBean.setRsorgId(qCrditOrderInfo.getRsorgId());
		creditOrderBean.setCreateTime(DateUtil.dateToString(
				qCrditOrderInfo.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		creditOrderBean.setUrlKey(qCrditOrderInfo.getUrlKey());
		creditOrderBean.setStatus(qCrditOrderInfo.getStatus());
		creditOrderBean.setAssureType(qCrditOrderInfo.getAssureType());
		creditOrderBean.setFirstAuditTime(qCrditOrderInfo.getFirstAuditTime());
		creditOrderBean.setLastAuditTime(qCrditOrderInfo.getLastAuditTime());
		creditOrderBean.setAuditPass(qCrditOrderInfo.getAuditPass());
		// creditOrderBean.setReqJson(qCrditOrderInfo.getReqJson());
		creditOrderBean.setOrgCode(qCrditOrderInfo.getOrgCode());

		creditInfoResponse.setCreditOrder(creditOrderBean);

		LoanCreditCompany loanCreditCompany = new LoanCreditCompany();
		loanCreditCompany.setCreditOrderNo(qCrditOrderInfo.getOrderNo());
		List<LoanCreditCompany> creditCompanies = loanCreditCompanyDao
				.queryLoanCreditCompany(loanCreditCompany);

		if (CollectionUtils.isNotEmpty(creditCompanies)) {
			LoanCreditCompany creditCompany = creditCompanies.get(0);

			LoanCreditCompanyBean companyBean = new LoanCreditCompanyBean();
			creditInfoResponse.setCreditCompany(companyBean);
			// 基本信息
			companyBean.setName(creditCompany.getName());
			companyBean.setStartDate(DateUtil.dateToString(creditCompany
					.getStartDate()));
			companyBean.setRegAddress(creditCompany.getRegAddress());
			companyBean.setOfficeAddress(creditCompany.getOfficeAddress());
			companyBean.setOrgSize(creditCompany.getOrgSize());
			companyBean.setLicense(creditCompany.getLicense());
			companyBean.setTaxRegNo(creditCompany.getTaxRegNo());
			// 泰坦云注册信息
			companyBean.setOrgCode(creditCompany.getOrgCode());
			companyBean.setRegAccount(creditCompany.getRegAccount());
			companyBean.setRegDate(DateUtil.dateToString(creditCompany
					.getRegDate()));
			// 法人
			companyBean.setEmpSize(creditCompany.getEmpSize());
			companyBean.setLegalName(creditCompany.getLegalName());
			companyBean.setLegalceType(creditCompany.getLegalceType());
			companyBean.setLegalNo(creditCompany.getLegalNo());
			// 联系人
			companyBean.setContactName(creditCompany.getContactName());
			companyBean.setContactPhone(creditCompany.getContactPhone());
			companyBean.setWaterEmail(creditCompany.getWaterEmail());

			LoanCompanyAppendInfo appendInfo = JsonConversionTool.toObject(
					creditCompany.getAppendInfo(), LoanCompanyAppendInfo.class);

			creditInfoResponse.setCompanyAppendInfo(appendInfo);
			// 证件图片
			companyBean.setLicenseUrl(creditCompany.getLicenseUrl());
			companyBean.setLegalNoUrl(creditCompany.getLegalNoUrl());
			companyBean.setOfficeNoUrl(creditCompany.getOfficeNoUrl());
			companyBean.setAccountUrl(creditCompany.getAccountUrl());
			companyBean.setCreditUrl(creditCompany.getCreditUrl());
			companyBean.setOfficeUrl(creditCompany.getOfficeUrl());
			companyBean.setWaterUrl(creditCompany.getWaterUrl());
			companyBean.setTaxRegUrl(creditCompany.getTaxRegUrl());
			companyBean.setOrgCodeUrl(creditCompany.getOrgCodeUrl());
			// 是否推送
			companyBean.setIsPush(creditCompany.getIsPush());
		}

		if (qCrditOrderInfo.getAssureType() == 2) {

			LoanCompanyEnsure loanCompanyEnsure = new LoanCompanyEnsure();
			loanCompanyEnsure.setOrgCode(qCrditOrderInfo.getOrgCode());
			loanCompanyEnsure.setOrderNo(qCrditOrderInfo.getOrderNo());
			List<LoanCompanyEnsure> companyEnsures = loanCompanyEnsureDao
					.queryLoanCompanyEnsure(loanCompanyEnsure);
			if (CollectionUtils.isNotEmpty(companyEnsures)) {
				LoanCompanyEnsure companyEnsure = companyEnsures.get(0);

				LoanCompanyEnsureBean companyEnsureBean = new LoanCompanyEnsureBean();

				creditInfoResponse.setCompanyEnsure(companyEnsureBean);
				// 基本信息
				companyEnsureBean.setOrderNo(qCrditOrderInfo.getOrderNo());
				companyEnsureBean.setOrgCode(qCrditOrderInfo.getOrgCode());
				companyEnsureBean
						.setCompanyName(companyEnsure.getCompanyName());
				companyEnsureBean.setFoundDate(DateUtil
						.dateToString(companyEnsure.getFoundDate()));
				companyEnsureBean.setEnterpriseScale(companyEnsure
						.getEnterpriseScale());
				companyEnsureBean.setBusinessLicense(companyEnsure
						.getBusinessLicense());
				companyEnsureBean.setTaxRegisterCode(companyEnsure
						.getTaxRegisterCode());
				companyEnsureBean.setOrgCodeCertificate(companyEnsure
						.getOrgCodeCertificate());
				// 平台注册信息
				companyEnsureBean.setRegisterAccount(companyEnsure
						.getRegisterAccount());
				companyEnsureBean.setRegisterDate(DateUtil
						.dateToString(companyEnsure.getRegisterDate()));
				// 法人
				companyEnsureBean.setLegalPersonName(companyEnsure
						.getLegalPersonName());
				companyEnsureBean.setLegalPersonCertificateType(companyEnsure
						.getLegalPersonCertificateType());
				companyEnsureBean.setLegalPersonCertificateNumber(companyEnsure
						.getLegalPersonCertificateNumber());
				// 联系人
				companyEnsureBean
						.setContactName(companyEnsure.getContactName());
				companyEnsureBean.setContactPhone(companyEnsure
						.getContactPhone());
				// 图片证件
				companyEnsureBean.setBusinessLicenseUrl(companyEnsure
						.getBusinessLicenseUrl());
				companyEnsureBean.setOrgCodeCertificateUrl(companyEnsure
						.getOrgCodeCertificateUrl());
				companyEnsureBean.setTaxRegisterCodeUrl(companyEnsure
						.getTaxRegisterCodeUrl());
				companyEnsureBean.setLicenseUrl(companyEnsure.getLicenseUrl());
				companyEnsureBean.setLegalPersonUrl(companyEnsure
						.getLegalPersonUrl());
				companyEnsureBean.setRegAddress(companyEnsure.getRegAddress());
				companyEnsureBean.setOfficeAddress(companyEnsure
						.getOfficeAddress());
			}

		} else if (qCrditOrderInfo.getAssureType() == 1) {

			LoanPersonEnsure loanPersonEnsure = new LoanPersonEnsure();
			loanPersonEnsure.setOrgCode(qCrditOrderInfo.getOrgCode());
			loanPersonEnsure.setOrderNo(qCrditOrderInfo.getOrderNo());
			List<LoanPersonEnsure> personEnsures = loanPersonEnsureDao
					.queryLoanPersonEnsure(loanPersonEnsure);

			if (CollectionUtils.isNotEmpty(personEnsures)) {
				LoanPersonEnsure personEnsure = personEnsures.get(0);
				LoanPersonEnsureBean ensureBean = new LoanPersonEnsureBean();
				creditInfoResponse.setLoanPersonEnsure(ensureBean);
				// 基本信息
				ensureBean.setOrgCode(qCrditOrderInfo.getOrgCode());
				ensureBean.setOrderNo(qCrditOrderInfo.getOrderNo());
				ensureBean.setPersonName(personEnsure.getPersonName());
				ensureBean.setNationalIdentityNumber(personEnsure
						.getNationalIdentityNumber());
				ensureBean.setMobilenNmber(personEnsure.getMobileNumber());
				ensureBean.setMarriageStatus(personEnsure.getMarriageStatus());
				ensureBean.setHaveChildren(personEnsure.getHaveChildren());
				ensureBean.setNativePlace(personEnsure.getNativePlace());
				ensureBean.setCurrentLiveaAdress(personEnsure
						.getCurrentLiveAddress());
				// 学历
				ensureBean.setGraduateSchool(personEnsure.getGraduateSchool());
				ensureBean.setHighestEducation(personEnsure
						.getHighestEducation());
				// 工作
				ensureBean.setYearsWorking(personEnsure.getYearsWorking());
				ensureBean.setWorkCompany(personEnsure.getWorkCompany());
				ensureBean.setOccupation(personEnsure.getOccupation());
				ensureBean.setWorktelePhone(personEnsure.getWorkTelephone());
				ensureBean.setOfficeAddress(personEnsure.getOfficeAddress());
				ensureBean.setIndustry(personEnsure.getIndustry());
				// 资产
				ensureBean
						.setCarPropertyType(personEnsure.getCarPropertyType());
				ensureBean.setHousePropertyType(personEnsure
						.getHousePropertyType());
				ensureBean.setOtherProperty(personEnsure.getOtherProperty());
				ensureBean.setPropertyRemark(personEnsure.getPropertyRemark());
				// 联系人
				ensureBean.setFirstContactName(personEnsure
						.getFirstContactName());
				ensureBean.setFirstContactPhone(personEnsure
						.getFirstContactPhone());
				ensureBean.setRelationToguarantee1(personEnsure
						.getRelationToGuarantee1());
				ensureBean.setSecondContactName(personEnsure
						.getSecondContactName());
				ensureBean.setSecondContactPhone(personEnsure
						.getSecondContactPhone());
				ensureBean.setRelationToguarantee2(personEnsure
						.getRelationToGuarantee2());
				// 图片信息
				ensureBean.setIdCardUrl(personEnsure.getIdCardUrl());
				ensureBean.setRegisteredUrl(personEnsure.getRegisteredUrl());
				ensureBean.setSpouseRegisteredUrl(personEnsure
						.getSpouseRegisteredUrl());
				ensureBean
						.setSpouseIdCardUrl(personEnsure.getSpouseIdCardUrl());
				ensureBean.setMarriageUrl(personEnsure.getMarriageUrl());

			}
		}

		creditInfoResponse.setOrderNo(qCrditOrderInfo.getOrderNo());

		// // 以下针对首次进入授信审核带出所有参数列表值
		// FinancialOrganQueryRequest organQueryRequest = new
		// FinancialOrganQueryRequest();
		// organQueryRequest.setUserId(req.getOrgCode());
		// FinancialOrganResponse financialOrganResponse =
		// titanFinancialOrganService
		// .queryFinancialOrgan(organQueryRequest);
		//
		// if (null == creditOrderBean) {
		// creditInfoResponse.setCreditOrder(new LoanCreditOrderBean());
		// creditOrderBean.setDayLimit(90);// 返回后需更新
		// creditOrderBean.setOrgCode(req.getOrgCode());
		// creditOrderBean.setAmount(financialOrganResponse
		// .getFinancialOrganDTO().getMaxLoanAmount());
		// creditOrderBean.setRateTem("RA201610141100001");//
		// 费率模板需配置在t_tfs_sysconfig
		// creditOrderBean.setRspId(CommonConstant.RS_FANGCANG_PRODUCT_ID);
		// creditOrderBean.setRsorgId(req.getOrgCode());
		// }
		//
		// MerchantDetailQueryDTO queryDTO = new MerchantDetailQueryDTO();
		// queryDTO.setMerchantCode(financialOrganResponse.getFinancialOrganDTO()
		// .getOrgBindInfo().getMerchantCode());
		// MerchantResponseDTO merchantResponseDTO = this.getMerchantFacade()
		// .queryMerchantDetail(queryDTO);
		//
		// if (null == companyBean) {
		// creditInfoResponse
		// .setCreditCompany(new LoanCreditCompanyBean());
		// companyBean.setRegAccount(merchantResponseDTO.getMerchantCode());
		// companyBean.setRegDate(DateUtil.dateToString(merchantResponseDTO
		// .getCreatedate()));
		// companyBean.setName(financialOrganResponse.getFinancialOrganDTO()
		// .getOrgName());
		// companyBean.setLicense(financialOrganResponse
		// .getFinancialOrganDTO().getBuslince());
		// }
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
		condition.setStatus(req.getStatus());
		PaginationSupport<CreditCompanyInfoDTO> paginationSupport = new PaginationSupport<CreditCompanyInfoDTO>();
		paginationSupport.setCurrentPage(req.getCurrentPage());
		paginationSupport.setOrderBy(" createTime desc ");
		paginationSupport = loanCreditOrderDao.selectForPage(condition,
				paginationSupport);
		response.setPageCreditCompanyInfoDTO(paginationSupport);
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

}
