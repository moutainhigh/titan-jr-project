package com.fangcang.titanjr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.merchant.api.MerchantFacade;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
import com.fangcang.titanjr.dao.LoanCompanyEnsureDao;
import com.fangcang.titanjr.dao.LoanCreditCompanyDao;
import com.fangcang.titanjr.dao.LoanCreditOrderDao;
import com.fangcang.titanjr.dao.LoanPersonEnsureDao;
import com.fangcang.titanjr.dto.bean.AppendInfo;
<<<<<<< HEAD
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
=======
import com.fangcang.titanjr.dto.bean.CreditCompanyInfoDTO;
import com.fangcang.titanjr.dto.request.ApplyLoanCreditRequest;
import com.fangcang.titanjr.dto.request.AuditCreidtOrderRequest;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.GetCreditInfoRequest;
import com.fangcang.titanjr.dto.request.GetCreditOrderCountRequest;
import com.fangcang.titanjr.dto.request.LoanCreditSaveRequest;
import com.fangcang.titanjr.dto.request.QueryPageCreditCompanyInfoRequest;
import com.fangcang.titanjr.dto.response.ApplyLoanCreditResponse;
import com.fangcang.titanjr.dto.response.AuditCreidtOrderResponse;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
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

		log.info("请求参数为,授信单号：" + req.getOrderNo() + ",当前机构：" + req.getOrgCode());

		GetCreditInfoResponse creditInfoResponse = new GetCreditInfoResponse();
		if (StringUtil.isValidString(req.getOrderNo())) {// 不为空才查询

			LoanCreditOrder loanCreditOrder = new LoanCreditOrder();
			loanCreditOrder.setOrderNo(req.getOrderNo());
			loanCreditOrder.setRsorgId(req.getOrgCode());
			List<LoanCreditOrder> creditOrders = loanCreditOrderDao
					.queryLoanCreditOrder(loanCreditOrder);

			if (CollectionUtils.isNotEmpty(creditOrders)) {// 只有仅存一个授信申请单才处理
				LoanCreditOrder creditOrder = creditOrders.get(0);

				LoanCreditOrderBean creditOrderBean = new LoanCreditOrderBean();

				creditInfoResponse.setOrderNo(creditOrder.getOrderNo());
				creditInfoResponse.setCreditOrder(creditOrderBean);
				creditOrderBean.setDayLimit(creditOrder.getDayLimit());//
				creditOrderBean.setAmount(creditOrder.getAmount());
				creditOrderBean.setActualAmount(creditOrder.getActualAmount());
				creditOrderBean.setReqTime(creditOrder.getReqTime());
				creditOrderBean.setRateTem(creditOrder.getRateTem());//
				creditOrderBean.setRspId(creditOrder.getRspId());//
				creditOrderBean.setRsorgId(creditOrder.getRsorgId());
				creditOrderBean.setCreateTime(DateUtil.dateToString(
						creditOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
				creditOrderBean.setUrlKey(creditOrder.getUrlKey());
				creditOrderBean.setStatus(creditOrder.getStatus());
				creditOrderBean.setAssureType(creditOrder.getAssureType());
				creditOrderBean.setFirstAuditTime(creditOrder
						.getFirstAuditTime());
				creditOrderBean
						.setLastAuditTime(creditOrder.getLastAuditTime());
				creditOrderBean.setAuditPass(creditOrder.getAuditPass());
				// creditOrderBean.setReqJson();//==审核时发起授信申请到融数，才补充进去；
				creditOrderBean.setOrgCode(creditOrder.getOrgCode());

				LoanCreditCompany loanCreditCompany = new LoanCreditCompany();
				loanCreditCompany.setCreditorderNo(creditOrder.getOrderNo());
				List<LoanCreditCompany> creditCompanies = loanCreditCompanyDao
						.queryLoanCreditCompany(loanCreditCompany);
				if (CollectionUtils.isNotEmpty(creditCompanies)) {
					LoanCreditCompany creditCompany = creditCompanies.get(0);

					LoanCreditCompanyBean companyBean = new LoanCreditCompanyBean();
					creditInfoResponse.setCreditCompany(companyBean);
					// 基本信息
					companyBean.setCreditOrderNo(creditOrder.getOrderNo());
					companyBean.setName(creditCompany.getName());
					companyBean.setStartDate(DateUtil
							.dateToString(creditCompany.getStartDate()));
					companyBean.setRegAddress(creditCompany.getRegAddress());
					companyBean.setOfficeAddress(creditCompany
							.getOfficeAddress());
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
					companyBean
							.setContactPhone(creditCompany.getContactPhone());
					companyBean.setWaterEmail(creditCompany.getWaterEmail());
					// 附加信息
					JSONObject jsonObject = (JSONObject) JSONSerializer
							.toJSON(creditCompany.getAppendInfo());
					companyBean.setAppendInfo((AppendInfo) JSONSerializer
							.toJava(jsonObject));
					// 证件图片
					companyBean.setLicenseUrl(creditCompany.getLicenseUrl());
					companyBean.setLegalNoUrl(creditCompany.getLegalNoUrl());
					companyBean.setOfficeNoUrl(creditCompany.getOfficeNoUrl());
					companyBean.setAccountUrl(creditCompany.getAccountUrl());
					companyBean.setCreditUrl(creditCompany.getCreditUrl());
					companyBean.setOfficeUrl(creditCompany.getOfficeUrl());
					companyBean.setWaterUrl(creditCompany.getWaterUrl());
					// 是否推送
					companyBean.setIsPush(creditCompany.getIsPush());
				} else {
					// 如果大于两个，报错,若为空，不处理
					if (creditCompanies.size() > 1) {
						log.error("授信机构信息只能有一个");
						creditInfoResponse.putErrorResult("COMPANY_ERROR",
								"授信机构信息只能有一个");
						return creditInfoResponse;
					}
				}

				LoanCompanyEnsure loanCompanyEnsure = new LoanCompanyEnsure();
				loanCompanyEnsure.setUserId(creditOrder.getRsorgId());
				List<LoanCompanyEnsure> companyEnsures = loanCompanyEnsureDao
						.queryLoanCompanyEnsure(loanCompanyEnsure);

				if (creditOrder.getAssureType() == 2
						&& CollectionUtils.isNotEmpty(companyEnsures)) {
					LoanCompanyEnsure companyEnsure = companyEnsures.get(0);
					LoanCompanyEnsureBean companyEnsureBean = new LoanCompanyEnsureBean();

					creditInfoResponse.setCompanyEnsure(companyEnsureBean);
					// 基本信息
					companyEnsureBean.setUserId(companyEnsure.getUserId());
					companyEnsureBean.setCompanyName(companyEnsure
							.getCompanyName());
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
					companyEnsureBean
							.setLegalPersonCertificateType(companyEnsure
									.getLegalPersonCertificateType());
					companyEnsureBean
							.setLegalPersonCertificateNumber(companyEnsure
									.getLegalPersonCertificateNumber());
					// 联系人
					companyEnsureBean.setContactName(companyEnsure
							.getContactName());
					companyEnsureBean.setContactPhone(companyEnsure
							.getContactPhone());
					// 图片证件
					companyEnsureBean.setBusinessLicenseUrl(companyEnsure
							.getBusinessLicenseUrl());
					companyEnsureBean.setOrgCodeCertificateUrl(companyEnsure
							.getOrgCodeCertificateUrl());
					companyEnsureBean.setTaxRegisterCodeUrl(companyEnsure
							.getTaxRegisterCodeUrl());
					companyEnsureBean.setLicenseUrl(companyEnsure
							.getLicenseUrl());
					companyEnsureBean.setLegalPersonUrl(companyEnsure
							.getLegalPersonUrl());
				}

				LoanPersonEnsure loanPersonEnsure = new LoanPersonEnsure();
				loanPersonEnsure.setOrderNo(creditOrder.getOrderNo());
				List<LoanPersonEnsure> personEnsures = loanPersonEnsureDao
						.queryLoanPersonEnsure(loanPersonEnsure);
				if (creditOrder.getAssureType() == 1 && null != personEnsures
						&& personEnsures.size() == 1) {
					LoanPersonEnsure personEnsure = personEnsures.get(0);
					LoanPersonEnsureBean ensureBean = new LoanPersonEnsureBean();
					creditInfoResponse.setLoanPersonEnsure(ensureBean);
					// 基本信息
					ensureBean.setOrderNo(creditOrder.getOrderNo());
					ensureBean.setPersonName(personEnsure.getPersonName());
					ensureBean.setNationalIdentityNumber(personEnsure
							.getNationalIdentityNumber());
					ensureBean.setMobilenNmber(personEnsure.getMobileNumber());
					ensureBean.setMarriageStatus(personEnsure
							.getMarriageStatus());
					ensureBean.setHaveChildren(personEnsure.getHaveChildren());
					ensureBean.setNativePlace(personEnsure.getNativePlace());
					ensureBean.setCurrentLiveaAdress(personEnsure
							.getCurrentLiveAddress());
					// 学历
					ensureBean.setGraduateSchool(personEnsure
							.getGraduateSchool());
					ensureBean.setHighestEducation(personEnsure
							.getHighestEducation());
					// 工作
					ensureBean.setYearsWorking(personEnsure.getYearsWorking());
					ensureBean.setWorkCompany(personEnsure.getWorkCompany());
					ensureBean.setOccupation(personEnsure.getOccupation());
					ensureBean
							.setWorktelePhone(personEnsure.getWorkTelephone());
					ensureBean
							.setOfficeAddress(personEnsure.getOfficeAddress());
					ensureBean.setIndustry(personEnsure.getIndustry());
					// 资产
					ensureBean.setCarPropertyType(personEnsure
							.getCarPropertyType());
					creditInfoResponse.getLoanPersonEnsure()
							.setHousePropertyType(
									personEnsure.getHousePropertyType());
					ensureBean
							.setOtherProperty(personEnsure.getOtherProperty());
					ensureBean.setPropertyRemark(personEnsure
							.getPropertyRemark());
					// 联系人
					ensureBean.setFirstContactName(personEnsure
							.getFirstContactName());
					creditInfoResponse.getLoanPersonEnsure()
							.setFirstContactPhone(
									personEnsure.getFirstContactPhone());
					ensureBean.setRelationToguarantee1(personEnsure
							.getRelationToGuarantee1());
					creditInfoResponse.getLoanPersonEnsure()
							.setSecondContactName(
									personEnsure.getSecondContactName());
					ensureBean.setSecondContactPhone(personEnsure
							.getSecondContactPhone());
					ensureBean.setRelationToguarantee2(personEnsure
							.getRelationToGuarantee2());
					// 图片信息
					ensureBean.setIdCardUrl(personEnsure.getIdCardUrl());
					ensureBean
							.setRegisteredUrl(personEnsure.getRegisteredUrl());
					ensureBean.setSpouseRegisteredUrl(personEnsure
							.getSpouseRegisteredUrl());
					ensureBean.setSpouseIdCardUrl(personEnsure
							.getSpouseIdCardUrl());
					ensureBean.setMarriageUrl(personEnsure.getMarriageUrl());
				}
			}
		}
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

		// 此处很多校验暂时未处理
		if (creditOrders == null || creditOrders.isEmpty()) {// 新增
			String orderNo = titanCodeCenterService.createLoanCreditOrderNo();
			LoanCreditOrder creditOrder = LoanTypeConvertUtil
					.getLoanCreditOrder(orderNo, req.getCreditOrder());
			if (creditOrder != null) {
				creditOrder.setOrderNo(orderNo);
				loanCreditOrderDao.saveLoanCreditOrder(creditOrder);
				// 只能单保存成功才操作下面的；
				LoanCreditCompany creditCompany = LoanTypeConvertUtil
						.getLoanCreditCompany(req.getCreditCompany());
				if (creditCompany != null) {
					creditCompany.setCreditorderNo(orderNo);
					loanCreditCompanyDao.saveCreditCompany(creditCompany);
				}
				LoanPersonEnsure personEnsure = LoanTypeConvertUtil
						.getLoanPersonEnsure(req.getLoanPersonEnsure());
				if (creditOrder.getAssureType() == 1 && null != personEnsure) {// 此时才保存
					// 通过orderNo关联
					personEnsure.setOrderNo(orderNo);
					loanPersonEnsureDao.saveLoanPersonEnsure(personEnsure);
				}
				LoanCompanyEnsure companyEnsure = LoanTypeConvertUtil
						.getLoanCompanyEnsure(req.getCompanyEnsure());
				if (creditOrder.getAssureType() == 2 && null != companyEnsure) {
					// 通过userid关联
					loanCompanyEnsureDao.saveCompanyEnsure(companyEnsure);
				}
			}
		} else {// 修改
			if (qCrditOrderInfo != null) {

				LoanCreditOrder creditOrder = LoanTypeConvertUtil
						.getLoanCreditOrder(qCrditOrderInfo.getOrderNo(),
								req.getCreditOrder());

				if (creditOrder != null) {
					loanCreditOrderDao.updateLoanCreditOrder(creditOrder);
				}

				// 以下三个针对非授信申请单相关信息，数据库存在并传过来的数据不为空，则更新，否则新增
				LoanCreditCompany loanCreditCompany = LoanTypeConvertUtil
						.getLoanCreditCompany(req.getCreditCompany());
				if (null != loanCreditCompany) {
					LoanCreditCompany creditCompanyQuery = new LoanCreditCompany();
					creditCompanyQuery.setCreditorderNo(qCrditOrderInfo
							.getOrderNo());
					List<LoanCreditCompany> creditCompanies = loanCreditCompanyDao
							.queryLoanCreditCompany(loanCreditCompany);
					loanCreditCompany.setCreditorderNo(qCrditOrderInfo
							.getOrderNo());
					if (CollectionUtils.isNotEmpty(creditCompanies)) {
						loanCreditCompanyDao
								.updateCreditCompany(loanCreditCompany);
					} else {
						loanCreditCompanyDao
								.saveCreditCompany(loanCreditCompany);
					}
				}

				LoanPersonEnsure personEnsure = LoanTypeConvertUtil
						.getLoanPersonEnsure(req.getLoanPersonEnsure());
				if (creditOrder.getAssureType() == 1 && null != personEnsure) {// 此时才处理
					LoanPersonEnsure personEnsureQuery = new LoanPersonEnsure();
					personEnsureQuery.setOrderNo(qCrditOrderInfo.getOrderNo());
					List<LoanPersonEnsure> loanPersonEnsures = loanPersonEnsureDao
							.queryLoanPersonEnsure(personEnsureQuery);
					// 通过orderNo关联
					personEnsure.setOrderNo(qCrditOrderInfo.getOrderNo());
					if (CollectionUtils.isNotEmpty(loanPersonEnsures)) {
						loanPersonEnsureDao
								.updateLoanPersonEnsure(personEnsure);
					} else {
						loanPersonEnsureDao.saveLoanPersonEnsure(personEnsure);
					}
				}

				LoanCompanyEnsure companyEnsure = LoanTypeConvertUtil
						.getLoanCompanyEnsure(req.getCompanyEnsure());
				if (creditOrder.getAssureType() == 2 && null != companyEnsure) {// 此时才处理
					LoanCompanyEnsure companyEnsureQuery = new LoanCompanyEnsure();
					companyEnsureQuery.setUserId(qCrditOrderInfo.getOrgCode());
					List<LoanCompanyEnsure> loanCompanyEnsures = loanCompanyEnsureDao
							.queryLoanCompanyEnsure(companyEnsureQuery);
					// 通过userid关联
					companyEnsure.setUserId(creditOrder.getOrgCode());
					if (CollectionUtils.isNotEmpty(loanCompanyEnsures)) {
						loanCompanyEnsureDao.updateCompanyEnsure(companyEnsure);
					} else {
						loanCompanyEnsureDao.saveCompanyEnsure(companyEnsure);
					}
				}
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
    	PageCreditCompanyInfoResponse response = new PageCreditCompanyInfoResponse();
    	LoanCreditOrderParam condition = new LoanCreditOrderParam();
    	condition.setName(req.getName());
    	condition.setStatus(req.getStatus());
    	PaginationSupport<CreditCompanyInfoDTO> paginationSupport = new PaginationSupport<CreditCompanyInfoDTO>();
    	paginationSupport.setCurrentPage(req.getCurrentPage());
    	paginationSupport.setOrderBy(" createTime desc ");
    	paginationSupport = loanCreditOrderDao.selectForPage(condition,paginationSupport);
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
