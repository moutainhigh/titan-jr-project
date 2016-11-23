package com.fangcang.titanjr.service.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dao.LoanCompanyEnsureDao;
import com.fangcang.titanjr.dao.LoanCreditCompanyDao;
import com.fangcang.titanjr.dao.LoanCreditOrderDao;
import com.fangcang.titanjr.dao.LoanPersonEnsureDao;
import com.fangcang.titanjr.dto.bean.CreditCompanyInfoDTO;
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
import com.fangcang.util.DateUtil;
import com.fangcang.util.StringUtil;

/**
 * Created by zhaoshan on 2016/11/18.
 */
public class TitanFinancialLoanCreditServiceImpl implements TitanFinancialLoanCreditService {

    @Resource
    private LoanCompanyEnsureDao loanCompanyEnsureDao;

    @Resource
    private LoanCreditCompanyDao loanCreditCompanyDao;

    @Resource
    private LoanCreditOrderDao loanCreditOrderDao;

    @Resource
    private LoanPersonEnsureDao loanPersonEnsureDao;

    @Resource
    private TitanCodeCenterService titanCodeCenterService;

    @Override
    public AuditCreidtOrderResponse auditCreditOrder(AuditCreidtOrderRequest req) {
        return null;
    }

    @Override
    public GetCreditInfoResponse getCreditOrderInfo(GetCreditInfoRequest req) {
        return null;
    }

    @Override
    public LoanCreditSaveResponse saveCreditOrder(LoanCreditSaveRequest req) {
        LoanCreditSaveResponse creditSaveResponse = new LoanCreditSaveResponse();
        //此处很多校验暂时未处理
        if (!StringUtil.isValidString(req.getOrderNo())) {//新增
            String orderNo = titanCodeCenterService.createLoanCreditOrderNo();
            LoanCreditOrder creditOrder = this.getLoanCreditOrder(req);
            if (creditOrder != null){
                creditOrder.setOrderNo(orderNo);
                loanCreditOrderDao.saveLoanCreditOrder(creditOrder);
                //只能单保存成功才操作下面的；
                LoanCreditCompany creditCompany = this.getLoanCreditCompany(req);
                if (creditCompany != null){
                    creditCompany.setCreditorderNo(orderNo);
                    loanCreditCompanyDao.saveCreditCompany(creditCompany);
                }
                LoanPersonEnsure personEnsure = this.getLoanPersonEnsure(req);
                if (creditOrder.getAssureType() == 1 && null != personEnsure){//此时才保存
                    //通过orderNo关联
                    personEnsure.setOrderNo(orderNo);
                    loanPersonEnsureDao.saveLoanPersonEnsure(personEnsure);
                }
                LoanCompanyEnsure companyEnsure = this.getLoanCompanyEnsure(req);
                if (creditOrder.getAssureType() == 2 && null != companyEnsure){
                    //通过userid关联
                    loanCompanyEnsureDao.saveCompanyEnsure(companyEnsure);
                }
            }
        } else {//修改
            LoanCreditOrder creditOrder = new LoanCreditOrder();
            creditOrder.setOrderNo(req.getOrderNo());
            List<LoanCreditOrder> creditOrders = loanCreditOrderDao.queryLoanCreditOrder(creditOrder);
            if (CollectionUtils.isNotEmpty(creditOrders)){
                creditOrder = this.getLoanCreditOrder(req);
                creditOrder.setOrderNo(req.getOrderNo());//考虑是否重设
                loanCreditOrderDao.updateLoanCreditOrder(creditOrder);

                //以下三个针对非授信申请单相关信息，数据库存在并传过来的数据不为空，则更新，否则新增
                LoanCreditCompany loanCreditCompany = this.getLoanCreditCompany(req);
                if (null != loanCreditCompany){
                    LoanCreditCompany creditCompanyQuery = new LoanCreditCompany();
                    creditCompanyQuery.setCreditorderNo(creditOrder.getOrderNo());
                    List<LoanCreditCompany> creditCompanies = loanCreditCompanyDao.queryLoanCreditCompany(loanCreditCompany);
                    loanCreditCompany.setCreditorderNo(creditOrder.getOrderNo());
                    if (CollectionUtils.isNotEmpty(creditCompanies)) {
                        loanCreditCompanyDao.updateCreditCompany(loanCreditCompany);
                    } else {
                        loanCreditCompanyDao.saveCreditCompany(loanCreditCompany);
                    }
                }

                LoanPersonEnsure personEnsure = this.getLoanPersonEnsure(req);
                if (creditOrder.getAssureType() == 1 && null != personEnsure){//此时才处理
                    LoanPersonEnsure personEnsureQuery = new LoanPersonEnsure();
                    personEnsureQuery.setOrderNo(creditOrder.getOrderNo());
                    List<LoanPersonEnsure> loanPersonEnsures = loanPersonEnsureDao.queryLoanPersonEnsure(personEnsureQuery);
                    //通过orderNo关联
                    personEnsure.setOrderNo(creditOrder.getOrderNo());
                    if (CollectionUtils.isNotEmpty(loanPersonEnsures)){
                        loanPersonEnsureDao.updateLoanPersonEnsure(personEnsure);
                    } else {
                        loanPersonEnsureDao.saveLoanPersonEnsure(personEnsure);
                    }
                }

                LoanCompanyEnsure companyEnsure = this.getLoanCompanyEnsure(req);
                if (creditOrder.getAssureType() == 2 && null != companyEnsure){//此时才处理
                    LoanCompanyEnsure companyEnsureQuery = new LoanCompanyEnsure();
                    companyEnsureQuery.setUserId(creditOrder.getRsorgId());
                    List<LoanCompanyEnsure> loanCompanyEnsures = loanCompanyEnsureDao.queryLoanCompanyEnsure(companyEnsureQuery);
                    //通过userid关联
                    companyEnsure.setUserId(creditOrder.getRsorgId());
                    if (CollectionUtils.isNotEmpty(loanCompanyEnsures)){
                        loanCompanyEnsureDao.updateCompanyEnsure(companyEnsure);
                    } else {
                        loanCompanyEnsureDao.saveCompanyEnsure(companyEnsure);
                    }
                }
            } else {
                //参数有误，一定需要存在creditOrder
                creditSaveResponse.putErrorResult("UPDATE ERROR","更新时需存在授信申请单");
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

	private LoanCreditOrder getLoanCreditOrder(LoanCreditSaveRequest loanCreditSaveRequest) {
        //传过来机构一定不能为空
        if (null == loanCreditSaveRequest.getCreditOrder() ||
                !StringUtil.isValidString(loanCreditSaveRequest.getCreditOrder().getOrgCode())) {
            return null;
        }
        LoanCreditOrder loanCreditOrder = new LoanCreditOrder();
        loanCreditOrder.setDayLimit(loanCreditSaveRequest.getCreditOrder().getDayLimit());//
        loanCreditOrder.setAmount(loanCreditSaveRequest.getCreditOrder().getAmount());
        loanCreditOrder.setActualAmount(loanCreditSaveRequest.getCreditOrder().getActualAmount());
        loanCreditOrder.setReqTime(loanCreditSaveRequest.getCreditOrder().getReqTime());
        loanCreditOrder.setRateTem(loanCreditSaveRequest.getCreditOrder().getRateTem());//
        loanCreditOrder.setRspId(loanCreditSaveRequest.getCreditOrder().getRspId());//
        loanCreditOrder.setRsorgId(loanCreditSaveRequest.getCreditOrder().getRsorgId());
        loanCreditOrder.setCreateTime(DateUtil.stringToDate(loanCreditSaveRequest.getCreditOrder().getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        loanCreditOrder.setUrlKey(loanCreditSaveRequest.getCreditOrder().getUrlKey());
        loanCreditOrder.setStatus(loanCreditSaveRequest.getCreditOrder().getStatus());
        loanCreditOrder.setAssureType(loanCreditSaveRequest.getCreditOrder().getAssureType());
        loanCreditOrder.setFirstAuditTime(loanCreditSaveRequest.getCreditOrder().getFirstAuditTime());
        loanCreditOrder.setLastAuditTime(loanCreditSaveRequest.getCreditOrder().getLastAuditTime());
        loanCreditOrder.setAuditPass(loanCreditSaveRequest.getCreditOrder().getAuditPass());
        //loanCreditOrder.setReqJson();//==需补充进去；
        loanCreditOrder.setOrgCode(loanCreditSaveRequest.getCreditOrder().getOrgCode());
        loanCreditOrder.setOrderNo(loanCreditSaveRequest.getOrderNo());
        return loanCreditOrder;
    }

    private LoanCreditCompany getLoanCreditCompany(LoanCreditSaveRequest loanCreditSaveRequest) {
        //传过来机构一定不能为空
        if (null == loanCreditSaveRequest.getCreditCompany() ||
                !StringUtil.isValidString(loanCreditSaveRequest.getCreditCompany().getOrgCode())) {
            return null;
        }
        LoanCreditCompany loanCreditCompany = new LoanCreditCompany();
        //基本信息
        loanCreditCompany.setCreditorderNo(loanCreditSaveRequest.getOrderNo());
        loanCreditCompany.setName(loanCreditSaveRequest.getCreditCompany().getName());
        loanCreditCompany.setStartDate(loanCreditSaveRequest.getCreditCompany().getStartDate());
        loanCreditCompany.setRegAddress(loanCreditSaveRequest.getCreditCompany().getRegAddress());
        loanCreditCompany.setOfficeAddress(loanCreditSaveRequest.getCreditCompany().getOfficeAddress());
        loanCreditCompany.setOrgSize(loanCreditSaveRequest.getCreditCompany().getOrgSize());
        loanCreditCompany.setLicense(loanCreditSaveRequest.getCreditCompany().getLicense());
        loanCreditCompany.setTaxRegNo(loanCreditSaveRequest.getCreditCompany().getTaxRegNo());
        //泰坦云注册信息
        loanCreditCompany.setOrgCode(loanCreditSaveRequest.getCreditCompany().getOrgCode());
        loanCreditCompany.setRegAccount(loanCreditSaveRequest.getCreditCompany().getRegAccount());
        loanCreditCompany.setRegDate(loanCreditSaveRequest.getCreditCompany().getRegDate());
        //法人
        loanCreditCompany.setEmpSize(loanCreditSaveRequest.getCreditCompany().getEmpSize());
        loanCreditCompany.setLegalName(loanCreditSaveRequest.getCreditCompany().getLegalName());
        loanCreditCompany.setLegalceType(loanCreditSaveRequest.getCreditCompany().getLegalceType());
        loanCreditCompany.setLegalNo(loanCreditSaveRequest.getCreditCompany().getLegalNo());
        //联系人
        loanCreditCompany.setContactName(loanCreditSaveRequest.getCreditCompany().getContactName());
        loanCreditCompany.setContactPhone(loanCreditSaveRequest.getCreditCompany().getContactPhone());
        loanCreditCompany.setWaterEmail(loanCreditSaveRequest.getCreditCompany().getWaterEmail());
        //附加信息
        loanCreditCompany.setAppendInfo(JSONSerializer.toJSON(loanCreditSaveRequest.getCreditCompany().getAppendInfo()).toString());
        //证件图片
        loanCreditCompany.setLicenseUrl(loanCreditSaveRequest.getCreditCompany().getLicenseUrl());
        loanCreditCompany.setLegalNoUrl(loanCreditSaveRequest.getCreditCompany().getLegalNoUrl());
        loanCreditCompany.setOfficeNoUrl(loanCreditSaveRequest.getCreditCompany().getOfficeNoUrl());
        loanCreditCompany.setAccountUrl(loanCreditSaveRequest.getCreditCompany().getAccountUrl());
        loanCreditCompany.setCreditUrl(loanCreditSaveRequest.getCreditCompany().getCreditUrl());
        loanCreditCompany.setOfficeUrl(loanCreditSaveRequest.getCreditCompany().getOfficeUrl());
        loanCreditCompany.setWaterUrl(loanCreditSaveRequest.getCreditCompany().getWaterUrl());
        //是否推送
        loanCreditCompany.setIsPush(loanCreditSaveRequest.getCreditCompany().getIsPush());
        return loanCreditCompany;
    }

    private LoanCompanyEnsure getLoanCompanyEnsure(LoanCreditSaveRequest loanCreditSaveRequest) {
        //如果userid为空，直接认为是无效
        if (null == loanCreditSaveRequest.getCompanyEnsure() || !StringUtil.isValidString(loanCreditSaveRequest.getCompanyEnsure().getUserId())) {
            return null;
        }
        LoanCompanyEnsure loanCompanyEnsure = new LoanCompanyEnsure();
        //基本信息
        loanCompanyEnsure.setUserId(loanCreditSaveRequest.getCompanyEnsure().getUserId());
        loanCompanyEnsure.setCompanyName(loanCreditSaveRequest.getCompanyEnsure().getCompanyName());
        loanCompanyEnsure.setFoundDate(loanCreditSaveRequest.getCompanyEnsure().getFoundDate());
        loanCompanyEnsure.setEnterpriseScale(loanCreditSaveRequest.getCompanyEnsure().getEnterpriseScale());
        loanCompanyEnsure.setBusinessLicense(loanCreditSaveRequest.getCompanyEnsure().getBusinessLicense());
        loanCompanyEnsure.setTaxRegisterCode(loanCreditSaveRequest.getCompanyEnsure().getTaxRegisterCode());
        loanCompanyEnsure.setOrgCodeCertificate(loanCreditSaveRequest.getCompanyEnsure().getOrgCodeCertificate());
        //平台注册信息
        loanCompanyEnsure.setRegisterAccount(loanCreditSaveRequest.getCompanyEnsure().getRegisterAccount());
        loanCompanyEnsure.setRegisterDate(loanCreditSaveRequest.getCompanyEnsure().getRegisterDate());
        //法人
        loanCompanyEnsure.setLegalPersonName(loanCreditSaveRequest.getCompanyEnsure().getLegalPersonName());
        loanCompanyEnsure.setLegalPersonCertificateType(loanCreditSaveRequest.getCompanyEnsure().getLegalPersonCertificateType());
        loanCompanyEnsure.setLegalPersonCertificateNumber(loanCreditSaveRequest.getCompanyEnsure().getLegalPersonCertificateNumber());
        //联系人
        loanCompanyEnsure.setContactName(loanCreditSaveRequest.getCompanyEnsure().getContactName());
        loanCompanyEnsure.setContactPhone(loanCreditSaveRequest.getCompanyEnsure().getContactPhone());
        //图片证件
        loanCompanyEnsure.setBusinessLicenseUrl(loanCreditSaveRequest.getCompanyEnsure().getBusinessLicenseUrl());
        loanCompanyEnsure.setOrgCodeCertificateUrl(loanCreditSaveRequest.getCompanyEnsure().getOrgCodeCertificateUrl());
        loanCompanyEnsure.setTaxRegisterCodeUrl(loanCreditSaveRequest.getCompanyEnsure().getTaxRegisterCodeUrl());
        loanCompanyEnsure.setLicenseUrl(loanCreditSaveRequest.getCompanyEnsure().getLicenseUrl());
        loanCompanyEnsure.setLegalPersonUrl(loanCreditSaveRequest.getCompanyEnsure().getLegalPersonUrl());
        return loanCompanyEnsure;
    }

    private LoanPersonEnsure getLoanPersonEnsure(LoanCreditSaveRequest loanCreditSaveRequest) {
        //如果userid为空，直接认为是无效
        if (null == loanCreditSaveRequest.getLoanPersonEnsure() ||
                !StringUtil.isValidString(loanCreditSaveRequest.getLoanPersonEnsure().getUserId())) {
            return null;
        }
        LoanPersonEnsure loanPersonEnsure = new LoanPersonEnsure();
        //基本信息
        loanPersonEnsure.setOrderNo(loanCreditSaveRequest.getOrderNo());
        loanPersonEnsure.setPersonName(loanCreditSaveRequest.getLoanPersonEnsure().getPersonName());
        loanPersonEnsure.setNationalIdentityNumber(loanCreditSaveRequest.getLoanPersonEnsure().getNationalIdentityNumber());
        loanPersonEnsure.setMobileNumber(loanCreditSaveRequest.getLoanPersonEnsure().getMobilenNmber());
        loanPersonEnsure.setMarriageStatus(loanCreditSaveRequest.getLoanPersonEnsure().getMarriageStatus());
        loanPersonEnsure.setHaveChildren(loanCreditSaveRequest.getLoanPersonEnsure().getHaveChildren());
        loanPersonEnsure.setNativePlace(loanCreditSaveRequest.getLoanPersonEnsure().getNativePlace());
        loanPersonEnsure.setCurrentLiveAddress(loanCreditSaveRequest.getLoanPersonEnsure().getCurrentLiveaAdress());
        //学历
        loanPersonEnsure.setGraduateSchool(loanCreditSaveRequest.getLoanPersonEnsure().getGraduateSchool());
        loanPersonEnsure.setHighestEducation(loanCreditSaveRequest.getLoanPersonEnsure().getHighestEducation());
        //工作
        loanPersonEnsure.setYearsWorking(loanCreditSaveRequest.getLoanPersonEnsure().getYearsWorking());
        loanPersonEnsure.setWorkCompany(loanCreditSaveRequest.getLoanPersonEnsure().getWorkCompany());
        loanPersonEnsure.setOccupation(loanCreditSaveRequest.getLoanPersonEnsure().getOccupation());
        loanPersonEnsure.setWorkTelephone(loanCreditSaveRequest.getLoanPersonEnsure().getWorktelePhone());
        loanPersonEnsure.setOfficeAddress(loanCreditSaveRequest.getLoanPersonEnsure().getOfficeAddress());
        loanPersonEnsure.setIndustry(loanCreditSaveRequest.getLoanPersonEnsure().getIndustry());
        //资产
        loanPersonEnsure.setCarPropertyType(loanCreditSaveRequest.getLoanPersonEnsure().getCarPropertyType());
        loanPersonEnsure.setHousePropertyType(loanCreditSaveRequest.getLoanPersonEnsure().getHousePropertyType());
        loanPersonEnsure.setOtherProperty(loanCreditSaveRequest.getLoanPersonEnsure().getOtherProperty());
        loanPersonEnsure.setPropertyRemark(loanCreditSaveRequest.getLoanPersonEnsure().getPropertyRemark());
        //联系人
        loanPersonEnsure.setFirstContactName(loanCreditSaveRequest.getLoanPersonEnsure().getFirstContactName());
        loanPersonEnsure.setFirstContactPhone(loanCreditSaveRequest.getLoanPersonEnsure().getFirstContactPhone());
        loanPersonEnsure.setRelationToGuarantee1(loanCreditSaveRequest.getLoanPersonEnsure().getRelationToguarantee1());
        loanPersonEnsure.setSecondContactName(loanCreditSaveRequest.getLoanPersonEnsure().getSecondContactName());
        loanPersonEnsure.setSecondContactPhone(loanCreditSaveRequest.getLoanPersonEnsure().getSecondContactPhone());
        loanPersonEnsure.setRelationToGuarantee2(loanCreditSaveRequest.getLoanPersonEnsure().getRelationToguarantee2());
        //图片信息
        loanPersonEnsure.setIdCardUrl(loanCreditSaveRequest.getLoanPersonEnsure().getIdCardUrl());
        loanPersonEnsure.setRegisteredUrl(loanCreditSaveRequest.getLoanPersonEnsure().getRegisteredUrl());
        loanPersonEnsure.setSpouseRegisteredUrl(loanCreditSaveRequest.getLoanPersonEnsure().getSpouseRegisteredUrl());
        loanPersonEnsure.setSpouseIdCardUrl(loanCreditSaveRequest.getLoanPersonEnsure().getSpouseIdCardUrl());
        loanPersonEnsure.setMarriageUrl(loanCreditSaveRequest.getLoanPersonEnsure().getMarriageUrl());
        return loanPersonEnsure;
    }
}
