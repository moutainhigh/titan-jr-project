package com.fangcang.titanjr.util;

import net.sf.json.JSONSerializer;

import com.fangcang.titanjr.dto.bean.LoanCompanyEnsureBean;
import com.fangcang.titanjr.dto.bean.LoanCreditCompanyBean;
import com.fangcang.titanjr.dto.bean.LoanCreditOrderBean;
import com.fangcang.titanjr.dto.bean.LoanPersonEnsureBean;
import com.fangcang.titanjr.entity.LoanCompanyEnsure;
import com.fangcang.titanjr.entity.LoanCreditCompany;
import com.fangcang.titanjr.entity.LoanCreditOrder;
import com.fangcang.titanjr.entity.LoanPersonEnsure;
import com.fangcang.util.DateUtil;

public final class LoanTypeConvertUtil {

	public static LoanCreditOrder getLoanCreditOrder(String orderNo,
			LoanCreditOrderBean lco) {
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
		loanCreditOrder.setOrderNo(orderNo);
		return loanCreditOrder;
	}

	public static LoanCreditCompany getLoanCreditCompany(
			LoanCreditCompanyBean lcc) {
		// 传过来机构一定不能为空
		if (null == lcc) {
			return null;
		}

		LoanCreditCompany loanCreditCompany = new LoanCreditCompany();
		// 基本信息
		loanCreditCompany.setCreditorderNo(lcc.getCreditOrderNo());

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
		// 附加信息
		loanCreditCompany.setAppendInfo(JSONSerializer.toJSON(
				lcc.getAppendInfo()).toString());
		// 证件图片
		loanCreditCompany.setLicenseUrl(lcc.getLicenseUrl());
		loanCreditCompany.setLegalNoUrl(lcc.getLegalNoUrl());
		loanCreditCompany.setOfficeNoUrl(lcc.getOfficeNoUrl());
		loanCreditCompany.setAccountUrl(lcc.getAccountUrl());
		loanCreditCompany.setCreditUrl(lcc.getCreditUrl());
		loanCreditCompany.setOfficeUrl(lcc.getOfficeUrl());
		loanCreditCompany.setWaterUrl(lcc.getWaterUrl());
		// 是否推送
		loanCreditCompany.setIsPush(lcc.getIsPush());
		return loanCreditCompany;
	}

	public static LoanCompanyEnsure getLoanCompanyEnsure(
			LoanCompanyEnsureBean lc) {

		if (null == lc) {
			return null;
		}

		LoanCompanyEnsure loanCompanyEnsure = new LoanCompanyEnsure();
		// 基本信息
		loanCompanyEnsure.setUserId(lc.getUserId());
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
		return loanPersonEnsure;
	}
}
