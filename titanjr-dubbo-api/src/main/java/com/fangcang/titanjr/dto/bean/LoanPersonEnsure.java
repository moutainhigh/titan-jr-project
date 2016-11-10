package com.fangcang.titanjr.dto.bean;
/**
 * 个人担保实体对象
 * @author wengxitao
 *
 */
public class LoanPersonEnsure {

	private String orderNo;// 授信单号

	private String personName;// 姓名

	private String nationalIdentityNumber;// 身份证

	private String mobilenNmber;// 手机号码

	private int marriageStatus;// 1 未婚 2 已婚

	private int haveChildren;// 1 无子女 2 有子女

	private String nativePlace;// 籍贯

	private String currentLiveaAdress;// 现居住地址

	private String graduateSchool;// 毕业院校

	private int highestEducation;// 1 小学 2 初中 3高中 4 中专 5 大专 6 本科 6 硕士 7 博士

	private int yearsWorking;// 1 1-3 2 3-5 3 5-10 4 10以上

	private String workCompany; // 工作单位

	private String occupation;// 现任职务

	private String worktelePhone;// 工作电话

	private String officeAddress;// 办公地址

	private String industry;// 所处行业

	private int carPropertyType;// 车产情况，1 无车， 2有车无贷，3有车有贷

	private int housePropertyType;// 1 无房 2 有房有贷 3 有房无贷

	private String otherProperty;// 其他资产

	private String propertyRemark;// 补充说明

	private String firstContactName;// 第一联系人姓名

	private String firstContactPhone;// 第一联系人电话

	private int relationToguarantee1;// 1 父母 2 子女 3 配偶 4 朋友 5 同时 6 其他

	private String secondContactName;// 第二联系人姓名

	private String secondContactPhone;// 第二联系人电话

	private int relationToguarantee2;// 1 父母 2 子女 3 配偶 4 朋友 5 同时 6 其他

	private String idCardUrl;// 担保人身份证URL

	private String registeredUrl;// 户口本URL

	private String spouseRegisteredUrl;// 配偶户口本

	private String spouseIdCardUrl;// 配偶身份证

	private String marriageUrl;// 结婚证

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getNationalIdentityNumber() {
		return nationalIdentityNumber;
	}

	public void setNationalIdentityNumber(String nationalIdentityNumber) {
		this.nationalIdentityNumber = nationalIdentityNumber;
	}

	public String getMobilenNmber() {
		return mobilenNmber;
	}

	public void setMobilenNmber(String mobilenNmber) {
		this.mobilenNmber = mobilenNmber;
	}

	public int getMarriageStatus() {
		return marriageStatus;
	}

	public void setMarriageStatus(int marriageStatus) {
		this.marriageStatus = marriageStatus;
	}

	public int getHaveChildren() {
		return haveChildren;
	}

	public void setHaveChildren(int haveChildren) {
		this.haveChildren = haveChildren;
	}

	public String getNativePlace() {
		return nativePlace;
	}

	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}

	public String getCurrentLiveaAdress() {
		return currentLiveaAdress;
	}

	public void setCurrentLiveaAdress(String currentLiveaAdress) {
		this.currentLiveaAdress = currentLiveaAdress;
	}

	public String getGraduateSchool() {
		return graduateSchool;
	}

	public void setGraduateSchool(String graduateSchool) {
		this.graduateSchool = graduateSchool;
	}

	public int getHighestEducation() {
		return highestEducation;
	}

	public void setHighestEducation(int highestEducation) {
		this.highestEducation = highestEducation;
	}

	public int getYearsWorking() {
		return yearsWorking;
	}

	public void setYearsWorking(int yearsWorking) {
		this.yearsWorking = yearsWorking;
	}

	public String getWorkCompany() {
		return workCompany;
	}

	public void setWorkCompany(String workCompany) {
		this.workCompany = workCompany;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getWorktelePhone() {
		return worktelePhone;
	}

	public void setWorktelePhone(String worktelePhone) {
		this.worktelePhone = worktelePhone;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public int getCarPropertyType() {
		return carPropertyType;
	}

	public void setCarPropertyType(int carPropertyType) {
		this.carPropertyType = carPropertyType;
	}

	public int getHousePropertyType() {
		return housePropertyType;
	}

	public void setHousePropertyType(int housePropertyType) {
		this.housePropertyType = housePropertyType;
	}

	public String getOtherProperty() {
		return otherProperty;
	}

	public void setOtherProperty(String otherProperty) {
		this.otherProperty = otherProperty;
	}

	public String getPropertyRemark() {
		return propertyRemark;
	}

	public void setPropertyRemark(String propertyRemark) {
		this.propertyRemark = propertyRemark;
	}

	public String getFirstContactName() {
		return firstContactName;
	}

	public void setFirstContactName(String firstContactName) {
		this.firstContactName = firstContactName;
	}

	public String getFirstContactPhone() {
		return firstContactPhone;
	}

	public void setFirstContactPhone(String firstContactPhone) {
		this.firstContactPhone = firstContactPhone;
	}

	public int getRelationToguarantee1() {
		return relationToguarantee1;
	}

	public void setRelationToguarantee1(int relationToguarantee1) {
		this.relationToguarantee1 = relationToguarantee1;
	}

	public String getSecondContactName() {
		return secondContactName;
	}

	public void setSecondContactName(String secondContactName) {
		this.secondContactName = secondContactName;
	}

	public String getSecondContactPhone() {
		return secondContactPhone;
	}

	public void setSecondContactPhone(String secondContactPhone) {
		this.secondContactPhone = secondContactPhone;
	}

	public int getRelationToguarantee2() {
		return relationToguarantee2;
	}

	public void setRelationToguarantee2(int relationToguarantee2) {
		this.relationToguarantee2 = relationToguarantee2;
	}

	public String getIdCardUrl() {
		return idCardUrl;
	}

	public void setIdCardUrl(String idCardUrl) {
		this.idCardUrl = idCardUrl;
	}

	public String getRegisteredUrl() {
		return registeredUrl;
	}

	public void setRegisteredUrl(String registeredUrl) {
		this.registeredUrl = registeredUrl;
	}

	public String getSpouseRegisteredUrl() {
		return spouseRegisteredUrl;
	}

	public void setSpouseRegisteredUrl(String spouseRegisteredUrl) {
		this.spouseRegisteredUrl = spouseRegisteredUrl;
	}

	public String getSpouseIdCardUrl() {
		return spouseIdCardUrl;
	}

	public void setSpouseIdCardUrl(String spouseIdCardUrl) {
		this.spouseIdCardUrl = spouseIdCardUrl;
	}

	public String getMarriageUrl() {
		return marriageUrl;
	}

	public void setMarriageUrl(String marriageUrl) {
		this.marriageUrl = marriageUrl;
	}

}
