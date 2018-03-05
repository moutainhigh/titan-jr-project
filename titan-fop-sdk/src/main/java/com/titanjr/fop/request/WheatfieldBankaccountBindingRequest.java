package com.titanjr.fop.request;

import java.util.Date;
import java.util.Map;

import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldBankaccountBindingResponse;

/***
 * 绑卡
 * 
 * @author luoqinglong
 * @date 2018年3月2日
 */
public class WheatfieldBankaccountBindingRequest implements FopRequest<WheatfieldBankaccountBindingResponse> {

	private FopHashMap udfParams;
	private String accountnumber;
	private String certificatetype;
	private String userid;
	private String certificatenumnumber;
	private Date openaccountdate;
	private String accountproperty;
	private String bindid;
	private String bankheadname;
	private String relatedcard;
	private String accountpurpose;
	private String relatid;
	private String req_sn;
	private String submit_time;
	private String referuserid;
	private String bank_branch;
	private String bank_code;
	private String openaccountdescription;
	private String remark;
	private String bank_province;
	private String bankbranchname;
	private String currency;
	private String constid;
	private String usertype;
	private String accounttypeid;
	private String bank_city;
	private String productid;
	private String role;
	private String account_name;

	public FopHashMap getUdfParams() {
		return udfParams;
	}

	public void setUdfParams(FopHashMap udfParams) {
		this.udfParams = udfParams;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getCertificatetype() {
		return certificatetype;
	}

	public void setCertificatetype(String certificatetype) {
		this.certificatetype = certificatetype;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCertificatenumnumber() {
		return certificatenumnumber;
	}

	public void setCertificatenumnumber(String certificatenumnumber) {
		this.certificatenumnumber = certificatenumnumber;
	}

	public Date getOpenaccountdate() {
		return openaccountdate;
	}

	public void setOpenaccountdate(Date openaccountdate) {
		this.openaccountdate = openaccountdate;
	}

	public String getAccountproperty() {
		return accountproperty;
	}

	public void setAccountproperty(String accountproperty) {
		this.accountproperty = accountproperty;
	}

	public String getBindid() {
		return bindid;
	}

	public void setBindid(String bindid) {
		this.bindid = bindid;
	}

	public String getBankheadname() {
		return bankheadname;
	}

	public void setBankheadname(String bankheadname) {
		this.bankheadname = bankheadname;
	}

	public String getRelatedcard() {
		return relatedcard;
	}

	public void setRelatedcard(String relatedcard) {
		this.relatedcard = relatedcard;
	}

	public String getAccountpurpose() {
		return accountpurpose;
	}

	public void setAccountpurpose(String accountpurpose) {
		this.accountpurpose = accountpurpose;
	}

	public String getRelatid() {
		return relatid;
	}

	public void setRelatid(String relatid) {
		this.relatid = relatid;
	}

	public String getReq_sn() {
		return req_sn;
	}

	public void setReq_sn(String req_sn) {
		this.req_sn = req_sn;
	}

	public String getSubmit_time() {
		return submit_time;
	}

	public void setSubmit_time(String submit_time) {
		this.submit_time = submit_time;
	}

	public String getReferuserid() {
		return referuserid;
	}

	public void setReferuserid(String referuserid) {
		this.referuserid = referuserid;
	}

	public String getBank_branch() {
		return bank_branch;
	}

	public void setBank_branch(String bank_branch) {
		this.bank_branch = bank_branch;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getOpenaccountdescription() {
		return openaccountdescription;
	}

	public void setOpenaccountdescription(String openaccountdescription) {
		this.openaccountdescription = openaccountdescription;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBank_province() {
		return bank_province;
	}

	public void setBank_province(String bank_province) {
		this.bank_province = bank_province;
	}

	public String getBankbranchname() {
		return bankbranchname;
	}

	public void setBankbranchname(String bankbranchname) {
		this.bankbranchname = bankbranchname;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getConstid() {
		return constid;
	}

	public void setConstid(String constid) {
		this.constid = constid;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getAccounttypeid() {
		return accounttypeid;
	}

	public void setAccounttypeid(String accounttypeid) {
		this.accounttypeid = accounttypeid;
	}

	public String getBank_city() {
		return bank_city;
	}

	public void setBank_city(String bank_city) {
		this.bank_city = bank_city;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}

	@Override
	public String getApiMethodName() {
		
		return "fangcang.wheatfield.bankaccount.binding";
	}

	@Override
	public Map<String, String> getTextParams() {
		FopHashMap localRopHashMap = new FopHashMap();
	    localRopHashMap.put("accountnumber", this.accountnumber);
	    localRopHashMap.put("certificatetype", this.certificatetype);
	    localRopHashMap.put("userid", this.userid);
	    localRopHashMap.put("certificatenumnumber", this.certificatenumnumber);
	    localRopHashMap.put("openaccountdate", this.openaccountdate);
	    localRopHashMap.put("accountproperty", this.accountproperty);
	    localRopHashMap.put("bindid", this.bindid);
	    localRopHashMap.put("bankheadname", this.bankheadname);
	    localRopHashMap.put("relatedcard", this.relatedcard);
	    localRopHashMap.put("accountpurpose", this.accountpurpose);
	    localRopHashMap.put("relatid", this.relatid);
	    localRopHashMap.put("req_sn", this.req_sn);
	    localRopHashMap.put("submit_time", this.submit_time);
	    localRopHashMap.put("referuserid", this.referuserid);
	    localRopHashMap.put("bank_branch", this.bank_branch);
	    localRopHashMap.put("bank_code", this.bank_code);
	    localRopHashMap.put("openaccountdescription", this.openaccountdescription);
	    localRopHashMap.put("remark", this.remark);
	    localRopHashMap.put("bank_province", this.bank_province);
	    localRopHashMap.put("bankbranchname", this.bankbranchname);
	    localRopHashMap.put("currency", this.currency);
	    localRopHashMap.put("constid", this.constid);
	    localRopHashMap.put("usertype", this.usertype);
	    localRopHashMap.put("accounttypeid", this.accounttypeid);
	    localRopHashMap.put("bank_city", this.bank_city);
	    localRopHashMap.put("productid", this.productid);
	    localRopHashMap.put("role", this.role);
	    localRopHashMap.put("account_name", this.account_name);
	    if (this.udfParams != null) {
	      localRopHashMap.putAll(this.udfParams);
	    }
	    return localRopHashMap;
	}

	@Override
	public Class<WheatfieldBankaccountBindingResponse> getResponseClass() {
		return WheatfieldBankaccountBindingResponse.class;
	}

	@Override
	public void check() throws ApiRuleException {

	}

}
