package com.fangcang.titanjr.dto.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 授信申请jsondata
 * @author luoqinglong
 * @2016年11月9日
 */
public class LoanCreditApplicationJsonDataBean implements Serializable {
	
		/**
	 * 
	 */
		private static final long serialVersionUID = -1581817784493163286L;
		//基本信息
		private String rootInstCd;//机构号码
		private String passportNumber;//护照号码
		private String workPhone;//工作电话
		
		
		//经营信息
		private String platformRegistTime;//平台注册日期
		private String platformOperaNo;//平台注册账号
		private String operatNumberEmployees;//员工人数
		private String leaseTerm;//租赁期限
		private String leaseAddress;//租赁地址
		private String housingArea;//建筑面积
		private String rental;//租金（元/年）
		private String paymentMethod;//支付方式
		private String remark;//备注
		
	    //股东信息 
		private List<LoanControllDataBean> controllData;
		//合作企业信息
		private List<LoanCooperationCompanyBean> cooperationCompanyInfo;
		//主营业务
		private List<LoanMainBusinessDataBean> mainBusinessData;
	    //企业担保人信息
	    //private CompanyGuarantee companyGuarantee;
	    private String companyName_c;// 企业名称
		private String businessExpire_c;// 营业期限
		private String companyType_c;// 企业类型
		private String registeredCapital_c;// 注册资本（元）
		private String employeesNumber_c;// 企业规模
		private String registeredAddress_c;// 注册地址
		private String officeAddress_c;// 办公地址
		private String setUpDate_c;// 成立日期
		private String businessLicenseNumber_c;// 营业执照号
		private String taxregCard_c;// 税务登记证号
		private String organizaCertificateNo_c;// 组织机构代码证号
		private String platformOperaNo_c;// 平台注册账号
		private String platformRegistTime_c;// 平台注册日期
		private String legalPersonName_c;// 法人姓名
		private String personCertificate_c;// 证件类型 1.身份证，2.护照
		private String applicateCardId_c;// 证件号码
		private String primaryContactName_c;// 联系人姓名
		private String primaryContactPhoneNumber;// 联系电话
		

	    //个人担保人信息
	    ///private PersonGuarantee personGuarantee;
		private String applicateName_p;//姓名
		private String  applicateCardId_p;//证件号码
		private String  workUnit_p;//工作单位
		private String  workStatus_p;//所处行业
		private String  occupation_p;//现任职务
		private String  courseOpenTime_p;//工作年限
		private String  annualIncome_p;//年收入
		private String  phoneNumber_p;//手机号码
		private String  familyFixed_p;//家庭固话
		private String  placeOfOrigin_p;//籍贯
		private String  email_p;//邮箱
		private String  academy_p;//毕业学校
		private String  education_p;//最高学历
		private String  address_p;//现住址
		private String  houseNature_p;//居住类型
		private String  marriageStatus_p;//婚姻状况
		private String  childrenStatus_p;//有无子女
		private String  firstContactName_p;//第一联系人
		private String  firstContactPhone_p;//第一联系人电话
		private String  firstContactRelations_p;//第一联系人关系
		private String  secondContactName_p;//第二联系人
		private String  secondContactPhone_p;//第二联系人电话
		private String  secondContactRelations_p;//第二联系人关系
		private String  roomSituation_p;//房产情况
		private String  carBrandModel_p;//车产品牌型号
		private String  carValue_p;//车产价值
		private String  buyCarYear_p;//购车年份
		private String  otherAssets;//其他资产情况
		private String  relatedNote;//相关说明
		public String getRootInstCd() {
			return rootInstCd;
		}
		public void setRootInstCd(String rootInstCd) {
			this.rootInstCd = rootInstCd;
		}
		public String getPassportNumber() {
			return passportNumber;
		}
		public void setPassportNumber(String passportNumber) {
			this.passportNumber = passportNumber;
		}
		public String getWorkPhone() {
			return workPhone;
		}
		public void setWorkPhone(String workPhone) {
			this.workPhone = workPhone;
		}
		public String getPlatformRegistTime() {
			return platformRegistTime;
		}
		public void setPlatformRegistTime(String platformRegistTime) {
			this.platformRegistTime = platformRegistTime;
		}
		public String getPlatformOperaNo() {
			return platformOperaNo;
		}
		public void setPlatformOperaNo(String platformOperaNo) {
			this.platformOperaNo = platformOperaNo;
		}
		public String getOperatNumberEmployees() {
			return operatNumberEmployees;
		}
		public void setOperatNumberEmployees(String operatNumberEmployees) {
			this.operatNumberEmployees = operatNumberEmployees;
		}
		public String getLeaseTerm() {
			return leaseTerm;
		}
		public void setLeaseTerm(String leaseTerm) {
			this.leaseTerm = leaseTerm;
		}
		public String getLeaseAddress() {
			return leaseAddress;
		}
		public void setLeaseAddress(String leaseAddress) {
			this.leaseAddress = leaseAddress;
		}
		public String getHousingArea() {
			return housingArea;
		}
		public void setHousingArea(String housingArea) {
			this.housingArea = housingArea;
		}
		public String getRental() {
			return rental;
		}
		public void setRental(String rental) {
			this.rental = rental;
		}
		public String getPaymentMethod() {
			return paymentMethod;
		}
		public void setPaymentMethod(String paymentMethod) {
			this.paymentMethod = paymentMethod;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		
		public List<LoanControllDataBean> getControllData() {
			return controllData;
		}
		public void setControllData(List<LoanControllDataBean> controllData) {
			this.controllData = controllData;
		}
		public List<LoanCooperationCompanyBean> getCooperationCompanyInfo() {
			return cooperationCompanyInfo;
		}
		public void setCooperationCompanyInfo(
				List<LoanCooperationCompanyBean> cooperationCompanyInfo) {
			this.cooperationCompanyInfo = cooperationCompanyInfo;
		}
		public List<LoanMainBusinessDataBean> getMainBusinessData() {
			return mainBusinessData;
		}
		public void setMainBusinessData(List<LoanMainBusinessDataBean> mainBusinessData) {
			this.mainBusinessData = mainBusinessData;
		}
		public String getCompanyName_c() {
			return companyName_c;
		}
		public void setCompanyName_c(String companyName_c) {
			this.companyName_c = companyName_c;
		}
		public String getBusinessExpire_c() {
			return businessExpire_c;
		}
		public void setBusinessExpire_c(String businessExpire_c) {
			this.businessExpire_c = businessExpire_c;
		}
		public String getCompanyType_c() {
			return companyType_c;
		}
		public void setCompanyType_c(String companyType_c) {
			this.companyType_c = companyType_c;
		}
		public String getRegisteredCapital_c() {
			return registeredCapital_c;
		}
		public void setRegisteredCapital_c(String registeredCapital_c) {
			this.registeredCapital_c = registeredCapital_c;
		}
		public String getEmployeesNumber_c() {
			return employeesNumber_c;
		}
		public void setEmployeesNumber_c(String employeesNumber_c) {
			this.employeesNumber_c = employeesNumber_c;
		}
		public String getRegisteredAddress_c() {
			return registeredAddress_c;
		}
		public void setRegisteredAddress_c(String registeredAddress_c) {
			this.registeredAddress_c = registeredAddress_c;
		}
		public String getOfficeAddress_c() {
			return officeAddress_c;
		}
		public void setOfficeAddress_c(String officeAddress_c) {
			this.officeAddress_c = officeAddress_c;
		}
		public String getSetUpDate_c() {
			return setUpDate_c;
		}
		public void setSetUpDate_c(String setUpDate_c) {
			this.setUpDate_c = setUpDate_c;
		}
		public String getBusinessLicenseNumber_c() {
			return businessLicenseNumber_c;
		}
		public void setBusinessLicenseNumber_c(String businessLicenseNumber_c) {
			this.businessLicenseNumber_c = businessLicenseNumber_c;
		}
		public String getTaxregCard_c() {
			return taxregCard_c;
		}
		public void setTaxregCard_c(String taxregCard_c) {
			this.taxregCard_c = taxregCard_c;
		}
		public String getOrganizaCertificateNo_c() {
			return organizaCertificateNo_c;
		}
		public void setOrganizaCertificateNo_c(String organizaCertificateNo_c) {
			this.organizaCertificateNo_c = organizaCertificateNo_c;
		}
		public String getPlatformOperaNo_c() {
			return platformOperaNo_c;
		}
		public void setPlatformOperaNo_c(String platformOperaNo_c) {
			this.platformOperaNo_c = platformOperaNo_c;
		}
		public String getPlatformRegistTime_c() {
			return platformRegistTime_c;
		}
		public void setPlatformRegistTime_c(String platformRegistTime_c) {
			this.platformRegistTime_c = platformRegistTime_c;
		}
		public String getLegalPersonName_c() {
			return legalPersonName_c;
		}
		public void setLegalPersonName_c(String legalPersonName_c) {
			this.legalPersonName_c = legalPersonName_c;
		}
		public String getPersonCertificate_c() {
			return personCertificate_c;
		}
		public void setPersonCertificate_c(String personCertificate_c) {
			this.personCertificate_c = personCertificate_c;
		}
		public String getApplicateCardId_c() {
			return applicateCardId_c;
		}
		public void setApplicateCardId_c(String applicateCardId_c) {
			this.applicateCardId_c = applicateCardId_c;
		}
		public String getPrimaryContactName_c() {
			return primaryContactName_c;
		}
		public void setPrimaryContactName_c(String primaryContactName_c) {
			this.primaryContactName_c = primaryContactName_c;
		}
		public String getPrimaryContactPhoneNumber() {
			return primaryContactPhoneNumber;
		}
		public void setPrimaryContactPhoneNumber(String primaryContactPhoneNumber) {
			this.primaryContactPhoneNumber = primaryContactPhoneNumber;
		}
		public String getApplicateName_p() {
			return applicateName_p;
		}
		public void setApplicateName_p(String applicateName_p) {
			this.applicateName_p = applicateName_p;
		}
		public String getApplicateCardId_p() {
			return applicateCardId_p;
		}
		public void setApplicateCardId_p(String applicateCardId_p) {
			this.applicateCardId_p = applicateCardId_p;
		}
		public String getWorkUnit_p() {
			return workUnit_p;
		}
		public void setWorkUnit_p(String workUnit_p) {
			this.workUnit_p = workUnit_p;
		}
		public String getWorkStatus_p() {
			return workStatus_p;
		}
		public void setWorkStatus_p(String workStatus_p) {
			this.workStatus_p = workStatus_p;
		}
		public String getOccupation_p() {
			return occupation_p;
		}
		public void setOccupation_p(String occupation_p) {
			this.occupation_p = occupation_p;
		}
		public String getCourseOpenTime_p() {
			return courseOpenTime_p;
		}
		public void setCourseOpenTime_p(String courseOpenTime_p) {
			this.courseOpenTime_p = courseOpenTime_p;
		}
		public String getAnnualIncome_p() {
			return annualIncome_p;
		}
		public void setAnnualIncome_p(String annualIncome_p) {
			this.annualIncome_p = annualIncome_p;
		}
		public String getPhoneNumber_p() {
			return phoneNumber_p;
		}
		public void setPhoneNumber_p(String phoneNumber_p) {
			this.phoneNumber_p = phoneNumber_p;
		}
		public String getFamilyFixed_p() {
			return familyFixed_p;
		}
		public void setFamilyFixed_p(String familyFixed_p) {
			this.familyFixed_p = familyFixed_p;
		}
		public String getPlaceOfOrigin_p() {
			return placeOfOrigin_p;
		}
		public void setPlaceOfOrigin_p(String placeOfOrigin_p) {
			this.placeOfOrigin_p = placeOfOrigin_p;
		}
		public String getEmail_p() {
			return email_p;
		}
		public void setEmail_p(String email_p) {
			this.email_p = email_p;
		}
		public String getAcademy_p() {
			return academy_p;
		}
		public void setAcademy_p(String academy_p) {
			this.academy_p = academy_p;
		}
		public String getEducation_p() {
			return education_p;
		}
		public void setEducation_p(String education_p) {
			this.education_p = education_p;
		}
		public String getAddress_p() {
			return address_p;
		}
		public void setAddress_p(String address_p) {
			this.address_p = address_p;
		}
		public String getHouseNature_p() {
			return houseNature_p;
		}
		public void setHouseNature_p(String houseNature_p) {
			this.houseNature_p = houseNature_p;
		}
		public String getMarriageStatus_p() {
			return marriageStatus_p;
		}
		public void setMarriageStatus_p(String marriageStatus_p) {
			this.marriageStatus_p = marriageStatus_p;
		}
		public String getChildrenStatus_p() {
			return childrenStatus_p;
		}
		public void setChildrenStatus_p(String childrenStatus_p) {
			this.childrenStatus_p = childrenStatus_p;
		}
		public String getFirstContactName_p() {
			return firstContactName_p;
		}
		public void setFirstContactName_p(String firstContactName_p) {
			this.firstContactName_p = firstContactName_p;
		}
		public String getFirstContactPhone_p() {
			return firstContactPhone_p;
		}
		public void setFirstContactPhone_p(String firstContactPhone_p) {
			this.firstContactPhone_p = firstContactPhone_p;
		}
		public String getFirstContactRelations_p() {
			return firstContactRelations_p;
		}
		public void setFirstContactRelations_p(String firstContactRelations_p) {
			this.firstContactRelations_p = firstContactRelations_p;
		}
		public String getSecondContactName_p() {
			return secondContactName_p;
		}
		public void setSecondContactName_p(String secondContactName_p) {
			this.secondContactName_p = secondContactName_p;
		}
		public String getSecondContactPhone_p() {
			return secondContactPhone_p;
		}
		public void setSecondContactPhone_p(String secondContactPhone_p) {
			this.secondContactPhone_p = secondContactPhone_p;
		}
		public String getSecondContactRelations_p() {
			return secondContactRelations_p;
		}
		public void setSecondContactRelations_p(String secondContactRelations_p) {
			this.secondContactRelations_p = secondContactRelations_p;
		}
		public String getRoomSituation_p() {
			return roomSituation_p;
		}
		public void setRoomSituation_p(String roomSituation_p) {
			this.roomSituation_p = roomSituation_p;
		}
		public String getCarBrandModel_p() {
			return carBrandModel_p;
		}
		public void setCarBrandModel_p(String carBrandModel_p) {
			this.carBrandModel_p = carBrandModel_p;
		}
		public String getCarValue_p() {
			return carValue_p;
		}
		public void setCarValue_p(String carValue_p) {
			this.carValue_p = carValue_p;
		}
		public String getBuyCarYear_p() {
			return buyCarYear_p;
		}
		public void setBuyCarYear_p(String buyCarYear_p) {
			this.buyCarYear_p = buyCarYear_p;
		}
		public String getOtherAssets() {
			return otherAssets;
		}
		public void setOtherAssets(String otherAssets) {
			this.otherAssets = otherAssets;
		}
		public String getRelatedNote() {
			return relatedNote;
		}
		public void setRelatedNote(String relatedNote) {
			this.relatedNote = relatedNote;
		}
		
		
}
