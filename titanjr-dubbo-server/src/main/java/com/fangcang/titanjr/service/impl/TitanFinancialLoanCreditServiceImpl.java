package com.fangcang.titanjr.service.impl;

import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.fangcang.titanjr.dao.LoanCompanyEnsureDao;
import com.fangcang.titanjr.dao.LoanCreditCompanyDao;
import com.fangcang.titanjr.dao.LoanCreditOrderDao;
import com.fangcang.titanjr.dao.LoanPersonEnsureDao;
import com.fangcang.titanjr.dto.bean.AppendInfo;
import com.fangcang.titanjr.dto.request.*;
import com.fangcang.titanjr.dto.response.*;
import com.fangcang.titanjr.entity.LoanCompanyEnsure;
import com.fangcang.titanjr.entity.LoanCreditCompany;
import com.fangcang.titanjr.entity.LoanCreditOrder;
import com.fangcang.titanjr.entity.LoanPersonEnsure;
import com.fangcang.titanjr.service.TitanCodeCenterService;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.util.DateUtil;
import com.fangcang.util.StringUtil;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by zhaoshan on 2016/11/18.
 */
public class TitanFinancialLoanCreditServiceImpl implements TitanFinancialLoanCreditService {

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

    @Override
    public AuditCreidtOrderResponse auditCreditOrder(AuditCreidtOrderRequest req) {
        return null;
    }

    @Override
    public GetCreditInfoResponse getCreditOrderInfo(GetCreditInfoRequest req) {
        GetCreditInfoResponse creditInfoResponse = new GetCreditInfoResponse();
        if (StringUtil.isValidString(req.getOrderNo())){
            //返回失败
        }
        LoanCreditOrder loanCreditOrder = new LoanCreditOrder();
        loanCreditOrder.setOrderNo(req.getOrderNo());
        loanCreditOrder.setRsorgId(req.getOrgCode());
        List<LoanCreditOrder> creditOrders = loanCreditOrderDao.queryLoanCreditOrder(loanCreditOrder);
        if (null != creditOrders && creditOrders.size() == 1){//按出错处理
            LoanCreditOrder creditOrder = creditOrders.get(0);
            creditInfoResponse.setOrderNo(creditOrder.getOrderNo());
            creditInfoResponse.setCreditOrder(new com.fangcang.titanjr.dto.bean.LoanCreditOrder());

            creditInfoResponse.getCreditOrder().setDayLimit(creditOrder.getDayLimit());//
            creditInfoResponse.getCreditOrder().setAmount(creditOrder.getAmount());
            creditInfoResponse.getCreditOrder().setActualAmount(creditOrder.getActualAmount());
            creditInfoResponse.getCreditOrder().setReqTime(creditOrder.getReqTime());
            creditInfoResponse.getCreditOrder().setRateTem(creditOrder.getRateTem());//
            creditInfoResponse.getCreditOrder().setRspId(creditOrder.getRspId());//
            creditInfoResponse.getCreditOrder().setRsorgId(creditOrder.getRsorgId());
            creditInfoResponse.getCreditOrder().setCreateTime(DateUtil.dateToString(creditOrder.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
            creditInfoResponse.getCreditOrder().setUrlKey(creditOrder.getUrlKey());
            creditInfoResponse.getCreditOrder().setStatus(creditOrder.getStatus());
            creditInfoResponse.getCreditOrder().setAssureType(creditOrder.getAssureType());
            creditInfoResponse.getCreditOrder().setFirstAuditTime(creditOrder.getFirstAuditTime());
            creditInfoResponse.getCreditOrder().setLastAuditTime(creditOrder.getLastAuditTime());
            creditInfoResponse.getCreditOrder().setAuditPass(creditOrder.getAuditPass());
            //creditInfoResponse.getCreditOrder().setReqJson();//==审核时发起授信申请到融数，才补充进去；
            creditInfoResponse.getCreditOrder().setOrgCode(creditOrder.getOrgCode());

            LoanCreditCompany loanCreditCompany = new LoanCreditCompany();
            loanCreditCompany.setCreditorderNo(creditOrder.getOrderNo());
            List<LoanCreditCompany> creditCompanies = loanCreditCompanyDao.queryLoanCreditCompany(loanCreditCompany);
            if (null != creditCompanies && creditCompanies.size() == 1){
                creditInfoResponse.setCreditCompany(new com.fangcang.titanjr.dto.bean.LoanCreditCompany());
                //基本信息
                creditInfoResponse.getCreditCompany().setCreditOrderNo(creditOrder.getOrderNo());
                creditInfoResponse.getCreditCompany().setName(creditCompanies.get(0).getName());
                creditInfoResponse.getCreditCompany().setStartDate(creditCompanies.get(0).getStartDate());
                creditInfoResponse.getCreditCompany().setRegAddress(creditCompanies.get(0).getRegAddress());
                creditInfoResponse.getCreditCompany().setOfficeAddress(creditCompanies.get(0).getOfficeAddress());
                creditInfoResponse.getCreditCompany().setOrgSize(creditCompanies.get(0).getOrgSize());
                creditInfoResponse.getCreditCompany().setLicense(creditCompanies.get(0).getLicense());
                creditInfoResponse.getCreditCompany().setTaxRegNo(creditCompanies.get(0).getTaxRegNo());
                //泰坦云注册信息
                creditInfoResponse.getCreditCompany().setOrgCode(creditCompanies.get(0).getOrgCode());
                creditInfoResponse.getCreditCompany().setRegAccount(creditCompanies.get(0).getRegAccount());
                creditInfoResponse.getCreditCompany().setRegDate(creditCompanies.get(0).getRegDate());
                //法人
                creditInfoResponse.getCreditCompany().setEmpSize(creditCompanies.get(0).getEmpSize());
                creditInfoResponse.getCreditCompany().setLegalName(creditCompanies.get(0).getLegalName());
                creditInfoResponse.getCreditCompany().setLegalceType(creditCompanies.get(0).getLegalceType());
                creditInfoResponse.getCreditCompany().setLegalNo(creditCompanies.get(0).getLegalNo());
                //联系人
                creditInfoResponse.getCreditCompany().setContactName(creditCompanies.get(0).getContactName());
                creditInfoResponse.getCreditCompany().setContactPhone(creditCompanies.get(0).getContactPhone());
                creditInfoResponse.getCreditCompany().setWaterEmail(creditCompanies.get(0).getWaterEmail());
                //附加信息
                JSONObject jsonObject = (JSONObject) JSONSerializer.toJSON(creditCompanies.get(0).getAppendInfo());
                creditInfoResponse.getCreditCompany().setAppendInfo((AppendInfo)JSONSerializer.toJava(jsonObject));
                //证件图片
                creditInfoResponse.getCreditCompany().setLicenseUrl(creditCompanies.get(0).getLicenseUrl());
                creditInfoResponse.getCreditCompany().setLegalNoUrl(creditCompanies.get(0).getLegalNoUrl());
                creditInfoResponse.getCreditCompany().setOfficeNoUrl(creditCompanies.get(0).getOfficeNoUrl());
                creditInfoResponse.getCreditCompany().setAccountUrl(creditCompanies.get(0).getAccountUrl());
                creditInfoResponse.getCreditCompany().setCreditUrl(creditCompanies.get(0).getCreditUrl());
                creditInfoResponse.getCreditCompany().setOfficeUrl(creditCompanies.get(0).getOfficeUrl());
                creditInfoResponse.getCreditCompany().setWaterUrl(creditCompanies.get(0).getWaterUrl());
                //是否推送
                creditInfoResponse.getCreditCompany().setIsPush(creditCompanies.get(0).getIsPush());
            } else {

            }

            LoanCompanyEnsure loanCompanyEnsure = new LoanCompanyEnsure();
            loanCompanyEnsure.setUserId(creditOrder.getRsorgId());
            List<LoanCompanyEnsure> companyEnsures = loanCompanyEnsureDao.queryLoanCompanyEnsure(loanCompanyEnsure);
            if (null != companyEnsures && companyEnsures.size() == 1){
                creditInfoResponse.setCompanyEnsure(new com.fangcang.titanjr.dto.bean.LoanCompanyEnsure());
                //基本信息
                creditInfoResponse.getCompanyEnsure().setUserId(companyEnsures.get(0).getUserId());
                creditInfoResponse.getCompanyEnsure().setCompanyName(companyEnsures.get(0).getCompanyName());
                creditInfoResponse.getCompanyEnsure().setFoundDate(companyEnsures.get(0).getFoundDate());
                creditInfoResponse.getCompanyEnsure().setEnterpriseScale(companyEnsures.get(0).getEnterpriseScale());
                creditInfoResponse.getCompanyEnsure().setBusinessLicense(companyEnsures.get(0).getBusinessLicense());
                creditInfoResponse.getCompanyEnsure().setTaxRegisterCode(companyEnsures.get(0).getTaxRegisterCode());
                creditInfoResponse.getCompanyEnsure().setOrgCodeCertificate(companyEnsures.get(0).getOrgCodeCertificate());
                //平台注册信息
                creditInfoResponse.getCompanyEnsure().setRegisterAccount(companyEnsures.get(0).getRegisterAccount());
                creditInfoResponse.getCompanyEnsure().setRegisterDate(companyEnsures.get(0).getRegisterDate());
                //法人
                creditInfoResponse.getCompanyEnsure().setLegalPersonName(companyEnsures.get(0).getLegalPersonName());
                creditInfoResponse.getCompanyEnsure().setLegalPersonCertificateType(companyEnsures.get(0).getLegalPersonCertificateType());
                creditInfoResponse.getCompanyEnsure().setLegalPersonCertificateNumber(companyEnsures.get(0).getLegalPersonCertificateNumber());
                //联系人
                creditInfoResponse.getCompanyEnsure().setContactName(companyEnsures.get(0).getContactName());
                creditInfoResponse.getCompanyEnsure().setContactPhone(companyEnsures.get(0).getContactPhone());
                //图片证件
                creditInfoResponse.getCompanyEnsure().setBusinessLicenseUrl(companyEnsures.get(0).getBusinessLicenseUrl());
                creditInfoResponse.getCompanyEnsure().setOrgCodeCertificateUrl(companyEnsures.get(0).getOrgCodeCertificateUrl());
                creditInfoResponse.getCompanyEnsure().setTaxRegisterCodeUrl(companyEnsures.get(0).getTaxRegisterCodeUrl());
                creditInfoResponse.getCompanyEnsure().setLicenseUrl(companyEnsures.get(0).getLicenseUrl());
                creditInfoResponse.getCompanyEnsure().setLegalPersonUrl(companyEnsures.get(0).getLegalPersonUrl());
            } else {

            }

            LoanPersonEnsure loanPersonEnsure = new LoanPersonEnsure();
            loanPersonEnsure.setOrderNo(creditOrder.getOrderNo());
            List<LoanPersonEnsure> personEnsures = loanPersonEnsureDao.queryLoanPersonEnsure(loanPersonEnsure);
            if (null != personEnsures && personEnsures.size() == 1){
                creditInfoResponse.setLoanPersonEnsure(new com.fangcang.titanjr.dto.bean.LoanPersonEnsure());
                //基本信息
                creditInfoResponse.getLoanPersonEnsure().setOrderNo(creditOrder.getOrderNo());
                creditInfoResponse.getLoanPersonEnsure().setPersonName(personEnsures.get(0).getPersonName());
                creditInfoResponse.getLoanPersonEnsure().setNationalIdentityNumber(personEnsures.get(0).getNationalIdentityNumber());
                creditInfoResponse.getLoanPersonEnsure().setMobilenNmber(personEnsures.get(0).getMobileNumber());
                creditInfoResponse.getLoanPersonEnsure().setMarriageStatus(personEnsures.get(0).getMarriageStatus());
                creditInfoResponse.getLoanPersonEnsure().setHaveChildren(personEnsures.get(0).getHaveChildren());
                creditInfoResponse.getLoanPersonEnsure().setNativePlace(personEnsures.get(0).getNativePlace());
                creditInfoResponse.getLoanPersonEnsure().setCurrentLiveaAdress(personEnsures.get(0).getCurrentLiveAddress());
                //学历
                creditInfoResponse.getLoanPersonEnsure().setGraduateSchool(personEnsures.get(0).getGraduateSchool());
                creditInfoResponse.getLoanPersonEnsure().setHighestEducation(personEnsures.get(0).getHighestEducation());
                //工作
                creditInfoResponse.getLoanPersonEnsure().setYearsWorking(personEnsures.get(0).getYearsWorking());
                creditInfoResponse.getLoanPersonEnsure().setWorkCompany(personEnsures.get(0).getWorkCompany());
                creditInfoResponse.getLoanPersonEnsure().setOccupation(personEnsures.get(0).getOccupation());
                creditInfoResponse.getLoanPersonEnsure().setWorktelePhone(personEnsures.get(0).getWorkTelephone());
                creditInfoResponse.getLoanPersonEnsure().setOfficeAddress(personEnsures.get(0).getOfficeAddress());
                creditInfoResponse.getLoanPersonEnsure().setIndustry(personEnsures.get(0).getIndustry());
                //资产
                creditInfoResponse.getLoanPersonEnsure().setCarPropertyType(personEnsures.get(0).getCarPropertyType());
                creditInfoResponse.getLoanPersonEnsure().setHousePropertyType(personEnsures.get(0).getHousePropertyType());
                creditInfoResponse.getLoanPersonEnsure().setOtherProperty(personEnsures.get(0).getOtherProperty());
                creditInfoResponse.getLoanPersonEnsure().setPropertyRemark(personEnsures.get(0).getPropertyRemark());
                //联系人
                creditInfoResponse.getLoanPersonEnsure().setFirstContactName(personEnsures.get(0).getFirstContactName());
                creditInfoResponse.getLoanPersonEnsure().setFirstContactPhone(personEnsures.get(0).getFirstContactPhone());
                creditInfoResponse.getLoanPersonEnsure().setRelationToguarantee1(personEnsures.get(0).getRelationToGuarantee1());
                creditInfoResponse.getLoanPersonEnsure().setSecondContactName(personEnsures.get(0).getSecondContactName());
                creditInfoResponse.getLoanPersonEnsure().setSecondContactPhone(personEnsures.get(0).getSecondContactPhone());
                creditInfoResponse.getLoanPersonEnsure().setRelationToguarantee2(personEnsures.get(0).getRelationToGuarantee2());
                //图片信息
                creditInfoResponse.getLoanPersonEnsure().setIdCardUrl(personEnsures.get(0).getIdCardUrl());
                creditInfoResponse.getLoanPersonEnsure().setRegisteredUrl(personEnsures.get(0).getRegisteredUrl());
                creditInfoResponse.getLoanPersonEnsure().setSpouseRegisteredUrl(personEnsures.get(0).getSpouseRegisteredUrl());
                creditInfoResponse.getLoanPersonEnsure().setSpouseIdCardUrl(personEnsures.get(0).getSpouseIdCardUrl());
                creditInfoResponse.getLoanPersonEnsure().setMarriageUrl(personEnsures.get(0).getMarriageUrl());
            } else {

            }
        } else {

        }
        FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
        organQueryRequest.setUserId(req.getOrgCode());
        FinancialOrganResponse financialOrganResponse = titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);

        if (null == creditInfoResponse.getCreditOrder()){
            creditInfoResponse.setCreditOrder(new com.fangcang.titanjr.dto.bean.LoanCreditOrder());
//            creditInfoResponse.getCreditOrder().setDayLimit();
//            creditInfoResponse.getCreditOrder().setOrgCode();
//            creditInfoResponse.getCreditOrder().setAmount();
//            creditInfoResponse.getCreditOrder().setRateTem();
//            creditInfoResponse.getCreditOrder().setRspId();
            creditInfoResponse.getCreditOrder().setRsorgId(req.getOrgCode());
        }

        if (null == creditInfoResponse.getCreditCompany()){
            creditInfoResponse.setCreditCompany(new com.fangcang.titanjr.dto.bean.LoanCreditCompany());
//            creditInfoResponse.getCreditCompany().setRegAccount();
//            creditInfoResponse.getCreditCompany().setRegDate();
            creditInfoResponse.getCreditCompany().setName(financialOrganResponse.getFinancialOrganDTO().getOrgName());
            creditInfoResponse.getCreditCompany().setLicense(financialOrganResponse.getFinancialOrganDTO().getBuslince());
        }

        return creditInfoResponse;
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
    public QueryCreditCompanyInfoResponse queryCreditCompanyInfo(QueryCreditCompanyInfoRequest req) {
        return null;
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
        //loanCreditOrder.setReqJson();//==审核时发起授信申请到融数，才补充进去；
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
