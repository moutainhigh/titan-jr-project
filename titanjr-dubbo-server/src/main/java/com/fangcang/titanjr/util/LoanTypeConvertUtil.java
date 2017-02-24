package com.fangcang.titanjr.util;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import com.fangcang.titanjr.common.enums.LoanOrderStatusEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.dto.bean.LoanApplyOrderBean;
import com.fangcang.titanjr.dto.bean.LoanCompanyAppendInfo;
import com.fangcang.titanjr.dto.bean.LoanCompanyEnsureBean;
import com.fangcang.titanjr.dto.bean.LoanCreditCompanyBean;
import com.fangcang.titanjr.dto.bean.LoanCreditOrderBean;
import com.fangcang.titanjr.dto.bean.LoanPersonEnsureBean;
import com.fangcang.titanjr.dto.bean.LoanRepaymentBean;
import com.fangcang.titanjr.dto.bean.LoanRoomPackSpecBean;
import com.fangcang.titanjr.dto.bean.LoanSpecificationBean;
import com.fangcang.titanjr.entity.LoanApplyOrder;
import com.fangcang.titanjr.entity.LoanCompanyEnsure;
import com.fangcang.titanjr.entity.LoanCreditCompany;
import com.fangcang.titanjr.entity.LoanCreditOrder;
import com.fangcang.titanjr.entity.LoanPersonEnsure;
import com.fangcang.titanjr.entity.LoanSpecification;
import com.fangcang.titanjr.rs.dto.TUserArepayment;
import com.fangcang.util.DateUtil;
import com.fangcang.util.StringUtil;

public final class LoanTypeConvertUtil {
	
	
	/**
	 * 融数跟房仓的状态映射
	 * @param rsStatus
	 * @return
	 */
	public static LoanOrderStatusEnum rsStatusMap(String rsStatus)
	{
		if ("贷款已结束".equals(rsStatus)) {
			return LoanOrderStatusEnum.LOAN_FINISH;
		} else if ("终审续议".startsWith(rsStatus) || "终审不通过".startsWith(rsStatus)) {
			return LoanOrderStatusEnum.AUDIT_FIAL;
		} else if ("待协议确认".equals(rsStatus) || "待放款".equals(rsStatus)) {
			return LoanOrderStatusEnum.AUDIT_PASS;
		}else if("已放款".equals(rsStatus)){
			return LoanOrderStatusEnum.HAVE_LOAN;
		}
		return null;
	}
	

	public static LoanRepaymentBean getLoanRepaymentBean(
			TUserArepayment arepayment) {
		if (arepayment == null) {
			return null;
		}

		LoanRepaymentBean repaymentBean = new LoanRepaymentBean();

		// 计算利息
		BigDecimal repaymentInterest = new BigDecimal(
				arepayment.getActiveinterest());
		repaymentInterest = repaymentInterest.add(new BigDecimal(arepayment
				.getActiveoverduefine()));
		repaymentInterest = repaymentInterest.add(new BigDecimal(arepayment
				.getActiveoverdueinterest()));

		// 计算还款总额
		BigDecimal totalAmount = new BigDecimal(arepayment.getActivecapital());
		totalAmount = totalAmount.add(repaymentInterest);

		repaymentBean.setRepaymentTotalAmount(totalAmount.longValue());
		repaymentBean.setRepaymentAmount(Long.parseLong(arepayment
				.getActivecapital()));
		repaymentBean.setRepaymentInterest(repaymentInterest.longValue());

		if (StringUtil.isValidString(arepayment.getActiverepaymentdate())) {

			repaymentBean
					.setRepaymentDate(arepayment.getActiverepaymentdate());
		}

		return repaymentBean;
	}

	public static LoanSpecificationBean getLoanSpecBean(
			LoanSpecification packSpec ) {
		if (packSpec == null) {
			return null;
		}

		LoanSpecificationBean packSpecBean = new LoanSpecificationBean();
		packSpecBean.setAccount(packSpec.getAccount());
		packSpecBean.setAccountName(packSpec.getAccountName());
		packSpecBean.setBank(packSpec.getBank());
		packSpecBean.setAccessory(packSpec.getAccessory());
		packSpecBean.setOrderNo(packSpec.getOrderNo());
		packSpecBean.setTitanCode(packSpec.getTitanCode());
		packSpecBean.setContent(packSpec.getContent());
		return packSpecBean;
	}
	
	
	public static LoanRoomPackSpecBean getLoanRoomPackSpecBean(
			LoanSpecification packSpec ) {
		if (packSpec == null) {
			return null;
		}

		LoanRoomPackSpecBean packSpecBean = new LoanRoomPackSpecBean();
		packSpecBean.setAccount(packSpec.getAccount());
		packSpecBean.setAccountName(packSpec.getAccountName());
		packSpecBean.setBank(packSpec.getBank());
		packSpecBean.setContractUrl(packSpec.getAccessory());
		packSpecBean.setLoanOrderNo(packSpec.getOrderNo());
		packSpecBean.setTitanCode(packSpec.getTitanCode());
		
		if(StringUtil.isValidString(packSpec.getContent()))
		{
			Map<String, String> map = JsonConversionTool.toObject(
					packSpec.getContent(), Map.class);

			packSpecBean.setHotleName(map.get("hotelName"));

			if (StringUtil.isValidString(map.get("roomNights"))) {
				packSpecBean.setRoomNights(Integer.valueOf(map
						.get("roomNights")));
			}
			if (StringUtil.isValidString(map.get("beginDate"))) {
				packSpecBean.setBeginDate(DateUtil.stringToDate(
						map.get("beginDate"), "yyyy-MM-dd"));
			}
			if (StringUtil.isValidString(map.get("endDate"))) {
				packSpecBean.setEndDate(DateUtil.stringToDate(
						map.get("endDate"), "yyyy-MM-dd"));
			}
		}
		return packSpecBean;
	}

	public static LoanApplyOrderBean getApplyOrderBean(
			LoanApplyOrder loanApplyOrder) {

		if (loanApplyOrder == null) {
			return null;
		}

		LoanApplyOrderBean applyOrderBean = new LoanApplyOrderBean();
		if (loanApplyOrder.getActualAmount() != null) {
			applyOrderBean.setActualAmount(""
					+ loanApplyOrder.getActualAmount());
		}
		applyOrderBean.setActualRepaymentDate(loanApplyOrder
				.getActualRepaymentDate());
		if (loanApplyOrder.getAmount() != null) {
			applyOrderBean.setAmount("" + loanApplyOrder.getAmount());
		}
		applyOrderBean.setCreateTime(loanApplyOrder.getCreateTime());
		applyOrderBean.setCreditOrderNo(loanApplyOrder.getCreditOrderNo());
		applyOrderBean.setErrorMsg(loanApplyOrder.getErrorMsg());
		applyOrderBean.setLastRepaymentDate(loanApplyOrder
				.getLastRepaymentDate());
		applyOrderBean.setOrderNo(loanApplyOrder.getOrderNo());
		applyOrderBean.setOrgCode(loanApplyOrder.getOrgCode());
		applyOrderBean.setProductId(loanApplyOrder.getProductId());
		applyOrderBean.setProductSpecId(loanApplyOrder.getProductSpecId());
		applyOrderBean.setProductType(loanApplyOrder.getProductType());
		applyOrderBean.setRate(loanApplyOrder.getRate());
		applyOrderBean.setRateTmp(loanApplyOrder.getRateTmp());
		applyOrderBean.setRelMoneyTime(loanApplyOrder.getRelMoneyTime());
		applyOrderBean.setRepaymentInterest(loanApplyOrder
				.getRepaymentInterest());
		applyOrderBean.setRepaymentPrincipal(loanApplyOrder
				.getRepaymentPrincipal());
		applyOrderBean.setRepaymentType(loanApplyOrder.getRepaymentType());
		applyOrderBean.setRsorgId(loanApplyOrder.getRsorgId());
		applyOrderBean.setRspId(loanApplyOrder.getRspId());
		applyOrderBean.setShouldCapital(loanApplyOrder.getShouldCapital());
		applyOrderBean.setShouldInterest(loanApplyOrder.getShouldInterest());
		applyOrderBean.setStatus(loanApplyOrder.getStatus());

		return applyOrderBean;

	}

	public static LoanCreditOrder createDefaultCreditOrder(String orderNo,
			String orgCode) {

		LoanCreditOrder creditOrder = new LoanCreditOrder();
		creditOrder.setOrderNo(orderNo);
		creditOrder.setOrgCode(orgCode);
		creditOrder.setCreateTime(new Date());
		creditOrder.setStatus(1);
		//TODO 引用全局变量
		creditOrder.setRateTem("RA201610141100001");
		creditOrder.setRspId(CommonConstant.RS_FANGCANG_PRODUCT_ID);
		creditOrder.setRsorgId(CommonConstant.RS_FANGCANG_CONST_ID);
		//TODO 引用全局变量
		creditOrder.setDayLimit(36);
		return creditOrder;
	}

	public static LoanPersonEnsureBean getLoanPersonEnsureBean(
			LoanPersonEnsure personEnsure) {
		LoanPersonEnsureBean ensureBean = new LoanPersonEnsureBean();
		// 基本信息
		ensureBean.setPersonName(personEnsure.getPersonName());
		ensureBean.setNationalIdentityNumber(personEnsure
				.getNationalIdentityNumber());
		ensureBean.setMobilenNmber(personEnsure.getMobileNumber());
		ensureBean.setMarriageStatus(personEnsure.getMarriageStatus());
		ensureBean.setHaveChildren(personEnsure.getHaveChildren());
		ensureBean.setNativePlace(personEnsure.getNativePlace());
		ensureBean.setCurrentLiveaAdress(personEnsure.getCurrentLiveAddress());
		// 学历
		ensureBean.setGraduateSchool(personEnsure.getGraduateSchool());
		ensureBean.setHighestEducation(personEnsure.getHighestEducation());
		// 工作
		ensureBean.setYearsWorking(personEnsure.getYearsWorking());
		ensureBean.setWorkCompany(personEnsure.getWorkCompany());
		ensureBean.setOccupation(personEnsure.getOccupation());
		ensureBean.setWorktelePhone(personEnsure.getWorkTelephone());
		ensureBean.setOfficeAddress(personEnsure.getOfficeAddress());
		ensureBean.setIndustry(personEnsure.getIndustry());
		// 资产
		ensureBean.setCarPropertyType(personEnsure.getCarPropertyType());
		ensureBean.setHousePropertyType(personEnsure.getHousePropertyType());
		ensureBean.setOtherProperty(personEnsure.getOtherProperty());
		ensureBean.setPropertyRemark(personEnsure.getPropertyRemark());
		// 联系人
		ensureBean.setFirstContactName(personEnsure.getFirstContactName());
		ensureBean.setFirstContactPhone(personEnsure.getFirstContactPhone());
		ensureBean.setRelationToguarantee1(personEnsure
				.getRelationToGuarantee1());
		ensureBean.setSecondContactName(personEnsure.getSecondContactName());
		ensureBean.setSecondContactPhone(personEnsure.getSecondContactPhone());
		ensureBean.setRelationToguarantee2(personEnsure
				.getRelationToGuarantee2());
		// 图片信息
		ensureBean.setIdCardUrl(personEnsure.getIdCardUrl());
		ensureBean.setRegisteredUrl(personEnsure.getRegisteredUrl());
		ensureBean
				.setSpouseRegisteredUrl(personEnsure.getSpouseRegisteredUrl());
		ensureBean.setSpouseIdCardUrl(personEnsure.getSpouseIdCardUrl());
		ensureBean.setMarriageUrl(personEnsure.getMarriageUrl());
		ensureBean.setCarBrand(personEnsure.getCarBrand());
		ensureBean.setCarPurchaseDate(personEnsure.getCarPurchaseDate());

		ensureBean.setCarWorth(personEnsure.getCarWorth());
		ensureBean.setEmail(personEnsure.getEmail());
		ensureBean.setYearIncome(personEnsure.getYearIncome());

		return ensureBean;
	}

	public static LoanCompanyEnsureBean getLoanCompanyEnsureBean(
			LoanCompanyEnsure companyEnsure) {

		LoanCompanyEnsureBean companyEnsureBean = new LoanCompanyEnsureBean();

		companyEnsureBean.setCompanyName(companyEnsure.getCompanyName());
		companyEnsureBean.setFoundDate(DateUtil.dateToString(companyEnsure
				.getFoundDate()));
		companyEnsureBean
				.setEnterpriseScale(companyEnsure.getEnterpriseScale());
		companyEnsureBean
				.setBusinessLicense(companyEnsure.getBusinessLicense());
		companyEnsureBean
				.setTaxRegisterCode(companyEnsure.getTaxRegisterCode());
		companyEnsureBean.setOrgCodeCertificate(companyEnsure
				.getOrgCodeCertificate());
		// 平台注册信息
		companyEnsureBean
				.setRegisterAccount(companyEnsure.getRegisterAccount());
		companyEnsureBean.setRegisterDate(DateUtil.dateToString(companyEnsure
				.getRegisterDate()));
		// 法人
		companyEnsureBean
				.setLegalPersonName(companyEnsure.getLegalPersonName());
		companyEnsureBean.setLegalPersonCertificateType(companyEnsure
				.getLegalPersonCertificateType());
		companyEnsureBean.setLegalPersonCertificateNumber(companyEnsure
				.getLegalPersonCertificateNumber());
		// 联系人
		companyEnsureBean.setContactName(companyEnsure.getContactName());
		companyEnsureBean.setContactPhone(companyEnsure.getContactPhone());
		// 图片证件
		companyEnsureBean.setBusinessLicenseUrl(companyEnsure
				.getBusinessLicenseUrl());
		companyEnsureBean.setOrgCodeCertificateUrl(companyEnsure
				.getOrgCodeCertificateUrl());
		companyEnsureBean.setTaxRegisterCodeUrl(companyEnsure
				.getTaxRegisterCodeUrl());
		companyEnsureBean.setLicenseUrl(companyEnsure.getLicenseUrl());
		companyEnsureBean.setLegalPersonUrl(companyEnsure.getLegalPersonUrl());
		companyEnsureBean.setRegAddress(companyEnsure.getRegAddress());
		companyEnsureBean.setOfficeAddress(companyEnsure.getOfficeAddress());

		companyEnsureBean.setCertificateExpireDate(DateUtil.dateToString(
				companyEnsure.getCertificateExpireDate(), "yyyy-MM-dd"));
		companyEnsureBean.setCertificateStartDate(DateUtil.dateToString(
				companyEnsure.getCertificateStartDate(), "yyyy-MM-dd"));
		companyEnsureBean.setRegistFinance(companyEnsure.getRegistFinance());
		companyEnsureBean.setCompanyType(companyEnsure.getCompanyType());

		return companyEnsureBean;

	}

	public static LoanCompanyAppendInfo getCompanyAppendInfo(String appendInfo) {
		LoanCompanyAppendInfo companyAppendInfo = JsonConversionTool.toObject(
				appendInfo, LoanCompanyAppendInfo.class);

		return companyAppendInfo;
	}

	public static LoanCreditCompanyBean getLoanCreditCompanyBean(
			LoanCreditCompany creditCompany) {
		LoanCreditCompanyBean companyBean = new LoanCreditCompanyBean();
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
		companyBean
				.setRegDate(DateUtil.dateToString(creditCompany.getRegDate()));
		// 法人
		companyBean.setEmpSize(creditCompany.getEmpSize());
		companyBean.setLegalName(creditCompany.getLegalName());
		companyBean.setLegalceType(creditCompany.getLegalceType());
		companyBean.setLegalNo(creditCompany.getLegalNo());
		// 联系人
		companyBean.setContactName(creditCompany.getContactName());
		companyBean.setContactPhone(creditCompany.getContactPhone());
		companyBean.setWaterEmail(creditCompany.getWaterEmail());

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

		companyBean.setCertificateExpireDate(DateUtil.dateToString(
				creditCompany.getCertificateExpireDate(), "yyyy-MM-dd"));
		companyBean.setCertificateStartDate(DateUtil.dateToString(
				creditCompany.getCertificateStartDate(), "yyyy-MM-dd"));

		companyBean.setCompanyType(creditCompany.getCompanyType());

		companyBean.setRegistFinance(creditCompany.getRegistFinance());

		return companyBean;
	}

	public static LoanCreditOrderBean getLoanCreditOrderBean(
			LoanCreditOrder qCrditOrderInfo) {
		LoanCreditOrderBean creditOrderBean = new LoanCreditOrderBean();
		creditOrderBean.setDayLimit(qCrditOrderInfo.getDayLimit());//
		creditOrderBean.setOrderNo(qCrditOrderInfo.getOrderNo());
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
		creditOrderBean.setFirstAuditor(qCrditOrderInfo.getFirstAuditor());
		creditOrderBean.setFirstAuditTime(qCrditOrderInfo.getFirstAuditTime());
		creditOrderBean.setLastAuditTime(qCrditOrderInfo.getLastAuditTime());
		creditOrderBean.setAuditPass(qCrditOrderInfo.getAuditPass());
		creditOrderBean.setOrgCode(qCrditOrderInfo.getOrgCode());
		creditOrderBean.setExpireTime(qCrditOrderInfo.getExpireTime());
		return creditOrderBean;
	}

	public static LoanCreditOrder getLoanCreditOrder(LoanCreditOrderBean lco) {
		// 传过来机构一定不能为空
		if (null == lco) {
			return null;
		}
		LoanCreditOrder loanCreditOrder = new LoanCreditOrder();
		loanCreditOrder.setDayLimit(lco.getDayLimit());//
		loanCreditOrder.setAmount(lco.getAmount());
		loanCreditOrder.setActualAmount(lco.getActualAmount());
		loanCreditOrder.setReqTime(lco.getReqTime());
		loanCreditOrder.setRateTem(lco.getRateTem());//
		loanCreditOrder.setRspId(lco.getRspId());//
		loanCreditOrder.setRsorgId(lco.getRsorgId());
		loanCreditOrder.setCreateTime(DateUtil.stringToDate(
				lco.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
		loanCreditOrder.setUrlKey(lco.getUrlKey());
		loanCreditOrder.setStatus(lco.getStatus());
		loanCreditOrder.setAssureType(lco.getAssureType());
		loanCreditOrder.setFirstAuditTime(lco.getFirstAuditTime());
		loanCreditOrder.setLastAuditTime(lco.getLastAuditTime());
		loanCreditOrder.setAuditPass(lco.getAuditPass());
		// loanCreditOrder.setReqJson();//==审核时发起授信申请到融数，才补充进去；
		loanCreditOrder.setOrgCode(lco.getOrgCode());
		return loanCreditOrder;
	}

	public static LoanCreditCompany getLoanCreditCompany(
			LoanCreditCompanyBean lcc, LoanCompanyAppendInfo appendInfo) {
		// 传过来机构一定不能为空
		if (null == lcc) {
			return null;
		}

		LoanCreditCompany loanCreditCompany = new LoanCreditCompany();
		// 基本信息

		loanCreditCompany.setName(lcc.getName());
		loanCreditCompany
				.setStartDate(DateUtil.stringToDate(lcc.getStartDate()));
		loanCreditCompany.setRegAddress(lcc.getRegAddress());
		loanCreditCompany.setOfficeAddress(lcc.getOfficeAddress());
		loanCreditCompany.setOrgSize(lcc.getOrgSize());
		loanCreditCompany.setLicense(lcc.getLicense());
		loanCreditCompany.setTaxRegNo(lcc.getTaxRegNo());
		// 泰坦云注册信息
		loanCreditCompany.setOrgCode(lcc.getOrgCode());
		loanCreditCompany.setRegAccount(lcc.getRegAccount());
		loanCreditCompany.setRegDate(DateUtil.stringToDate(lcc.getRegDate()));
		// 法人
		loanCreditCompany.setEmpSize(lcc.getEmpSize());
		loanCreditCompany.setLegalName(lcc.getLegalName());
		loanCreditCompany.setLegalceType(lcc.getLegalceType());
		loanCreditCompany.setLegalNo(lcc.getLegalNo());
		// 联系人
		loanCreditCompany.setContactName(lcc.getContactName());
		loanCreditCompany.setContactPhone(lcc.getContactPhone());
		loanCreditCompany.setWaterEmail(lcc.getWaterEmail());
		// 证件图片
		loanCreditCompany.setLicenseUrl(lcc.getLicenseUrl());
		loanCreditCompany.setLegalNoUrl(lcc.getLegalNoUrl());
		loanCreditCompany.setOfficeNoUrl(lcc.getOfficeNoUrl());
		loanCreditCompany.setAccountUrl(lcc.getAccountUrl());
		loanCreditCompany.setCreditUrl(lcc.getCreditUrl());
		loanCreditCompany.setOfficeUrl(lcc.getOfficeUrl());
		loanCreditCompany.setWaterUrl(lcc.getWaterUrl());
		loanCreditCompany.setTaxRegUrl(lcc.getTaxRegUrl());
		loanCreditCompany.setOrgCodeUrl(lcc.getOrgCodeUrl());
		// 是否推送
		loanCreditCompany.setIsPush(lcc.getIsPush());

		if (appendInfo != null) {
			loanCreditCompany.setAppendInfo(JsonConversionTool
					.toJson(appendInfo));
		}

		loanCreditCompany.setCertificateExpireDate(DateUtil.stringToDate(
				lcc.getCertificateExpireDate(), "yyyy-MM-dd"));
		loanCreditCompany.setCertificateStartDate(DateUtil.stringToDate(
				lcc.getCertificateStartDate(), "yyyy-MM-dd"));

		loanCreditCompany.setCompanyType(lcc.getCompanyType());

		loanCreditCompany.setRegistFinance(lcc.getRegistFinance());

		return loanCreditCompany;
	}

	public static LoanCompanyEnsure getLoanCompanyEnsure(
			LoanCompanyEnsureBean lc) {

		if (null == lc) {
			return null;
		}

		LoanCompanyEnsure loanCompanyEnsure = new LoanCompanyEnsure();
		// 基本信息
		loanCompanyEnsure.setOrgCode(lc.getOrgCode());
		loanCompanyEnsure.setCompanyName(lc.getCompanyName());
		loanCompanyEnsure
				.setFoundDate(DateUtil.stringToDate(lc.getFoundDate()));
		loanCompanyEnsure.setEnterpriseScale(lc.getEnterpriseScale());
		loanCompanyEnsure.setBusinessLicense(lc.getBusinessLicense());
		loanCompanyEnsure.setTaxRegisterCode(lc.getTaxRegisterCode());
		loanCompanyEnsure.setOrgCodeCertificate(lc.getOrgCodeCertificate());
		// 平台注册信息
		loanCompanyEnsure.setRegisterAccount(lc.getRegisterAccount());
		loanCompanyEnsure.setRegisterDate(DateUtil.stringToDate(lc
				.getRegisterDate()));
		// 法人
		loanCompanyEnsure.setLegalPersonName(lc.getLegalPersonName());
		loanCompanyEnsure.setLegalPersonCertificateType(lc
				.getLegalPersonCertificateType());
		loanCompanyEnsure.setLegalPersonCertificateNumber(lc
				.getLegalPersonCertificateNumber());
		// 联系人
		loanCompanyEnsure.setContactName(lc.getContactName());
		loanCompanyEnsure.setContactPhone(lc.getContactPhone());
		// 图片证件
		loanCompanyEnsure.setBusinessLicenseUrl(lc.getBusinessLicenseUrl());
		loanCompanyEnsure.setOrgCodeCertificateUrl(lc
				.getOrgCodeCertificateUrl());
		loanCompanyEnsure.setTaxRegisterCodeUrl(lc.getTaxRegisterCodeUrl());
		loanCompanyEnsure.setLicenseUrl(lc.getLicenseUrl());
		loanCompanyEnsure.setLegalPersonUrl(lc.getLegalPersonUrl());
		loanCompanyEnsure.setRegAddress(lc.getRegAddress());
		loanCompanyEnsure.setOfficeAddress(lc.getOfficeAddress());

		loanCompanyEnsure.setCertificateExpireDate(DateUtil.stringToDate(
				lc.getCertificateExpireDate(), "yyyy-MM-dd"));
		loanCompanyEnsure.setCertificateStartDate(DateUtil.stringToDate(
				lc.getCertificateStartDate(), "yyyy-MM-dd"));
		loanCompanyEnsure.setRegistFinance(lc.getRegistFinance());
		loanCompanyEnsure.setCompanyType(lc.getCompanyType());

		return loanCompanyEnsure;
	}

	public static LoanPersonEnsure getLoanPersonEnsure(LoanPersonEnsureBean lp) {
		if (null == lp) {
			return null;
		}
		LoanPersonEnsure loanPersonEnsure = new LoanPersonEnsure();
		// 基本信息
		loanPersonEnsure.setOrderNo(lp.getOrderNo());
		loanPersonEnsure.setPersonName(lp.getPersonName());
		loanPersonEnsure.setNationalIdentityNumber(lp
				.getNationalIdentityNumber());
		loanPersonEnsure.setMobileNumber(lp.getMobilenNmber());
		loanPersonEnsure.setMarriageStatus(lp.getMarriageStatus());
		loanPersonEnsure.setHaveChildren(lp.getHaveChildren());
		loanPersonEnsure.setNativePlace(lp.getNativePlace());
		loanPersonEnsure.setCurrentLiveAddress(lp.getCurrentLiveaAdress());
		// 学历
		loanPersonEnsure.setGraduateSchool(lp.getGraduateSchool());
		loanPersonEnsure.setHighestEducation(lp.getHighestEducation());
		// 工作
		loanPersonEnsure.setYearsWorking(lp.getYearsWorking());
		loanPersonEnsure.setWorkCompany(lp.getWorkCompany());
		loanPersonEnsure.setOccupation(lp.getOccupation());
		loanPersonEnsure.setWorkTelephone(lp.getWorktelePhone());
		loanPersonEnsure.setOfficeAddress(lp.getOfficeAddress());
		loanPersonEnsure.setIndustry(lp.getIndustry());
		// 资产
		loanPersonEnsure.setCarPropertyType(lp.getCarPropertyType());
		loanPersonEnsure.setHousePropertyType(lp.getHousePropertyType());
		loanPersonEnsure.setOtherProperty(lp.getOtherProperty());
		loanPersonEnsure.setPropertyRemark(lp.getPropertyRemark());
		// 联系人
		loanPersonEnsure.setFirstContactName(lp.getFirstContactName());
		loanPersonEnsure.setFirstContactPhone(lp.getFirstContactPhone());
		loanPersonEnsure.setRelationToGuarantee1(lp.getRelationToguarantee1());
		loanPersonEnsure.setSecondContactName(lp.getSecondContactName());
		loanPersonEnsure.setSecondContactPhone(lp.getSecondContactPhone());
		loanPersonEnsure.setRelationToGuarantee2(lp.getRelationToguarantee2());
		// 图片信息
		loanPersonEnsure.setIdCardUrl(lp.getIdCardUrl());
		loanPersonEnsure.setRegisteredUrl(lp.getRegisteredUrl());
		loanPersonEnsure.setSpouseRegisteredUrl(lp.getSpouseRegisteredUrl());
		loanPersonEnsure.setSpouseIdCardUrl(lp.getSpouseIdCardUrl());
		loanPersonEnsure.setMarriageUrl(lp.getMarriageUrl());
		loanPersonEnsure.setCarPurchaseDate(lp.getCarPurchaseDate());
		loanPersonEnsure.setCarBrand(lp.getCarBrand());

		loanPersonEnsure.setCarWorth(lp.getCarWorth());
		loanPersonEnsure.setEmail(lp.getEmail());
		loanPersonEnsure.setYearIncome(lp.getYearIncome());

		return loanPersonEnsure;
	}
}
