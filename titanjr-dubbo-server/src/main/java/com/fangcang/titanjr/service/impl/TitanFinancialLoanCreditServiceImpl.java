package com.fangcang.titanjr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.merchant.api.MerchantFacade;
import com.fangcang.merchant.query.dto.MerchantDetailQueryDTO;
import com.fangcang.merchant.response.dto.MerchantResponseDTO;
import com.fangcang.titanjr.common.factory.HessianProxyBeanFactory;
import com.fangcang.titanjr.common.factory.ProxyFactoryConstants;
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

	@Resource
	private LoanCreditOpinionDao loanCreditOpinionDao;

	@Override
	public AuditCreidtOrderResponse auditCreditOrder(AuditCreidtOrderRequest req) {
		return null;
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
