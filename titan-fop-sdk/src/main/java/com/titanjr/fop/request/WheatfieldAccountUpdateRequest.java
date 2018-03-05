package com.titanjr.fop.request;

import java.util.Map;

import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldAccountUpdateResponse;

public class WheatfieldAccountUpdateRequest implements FopRequest<WheatfieldAccountUpdateResponse> {
	private FopHashMap udfParams;
	private String accountnumber;
	private String accountid;
	private String bankcity;
	private String hankbranchname;
	private String constid;
	private String accountrealname;
	private String productid;
	private String certificatetype;
	private String certificatenumber;
	private String hankbranch;
	private String bankhead;
	private String userid;
	private String hankheadname;
	private String usertype;
	private String role;
	private String bankprovinec;

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

	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	public String getBankcity() {
		return bankcity;
	}

	public void setBankcity(String bankcity) {
		this.bankcity = bankcity;
	}

	public String getHankbranchname() {
		return hankbranchname;
	}

	public void setHankbranchname(String hankbranchname) {
		this.hankbranchname = hankbranchname;
	}

	public String getConstid() {
		return constid;
	}

	public void setConstid(String constid) {
		this.constid = constid;
	}

	public String getAccountrealname() {
		return accountrealname;
	}

	public void setAccountrealname(String accountrealname) {
		this.accountrealname = accountrealname;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getCertificatetype() {
		return certificatetype;
	}

	public void setCertificatetype(String certificatetype) {
		this.certificatetype = certificatetype;
	}

	public String getCertificatenumber() {
		return certificatenumber;
	}

	public void setCertificatenumber(String certificatenumber) {
		this.certificatenumber = certificatenumber;
	}

	public String getHankbranch() {
		return hankbranch;
	}

	public void setHankbranch(String hankbranch) {
		this.hankbranch = hankbranch;
	}

	public String getBankhead() {
		return bankhead;
	}

	public void setBankhead(String bankhead) {
		this.bankhead = bankhead;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getHankheadname() {
		return hankheadname;
	}

	public void setHankheadname(String hankheadname) {
		this.hankheadname = hankheadname;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getBankprovinec() {
		return bankprovinec;
	}

	public void setBankprovinec(String bankprovinec) {
		this.bankprovinec = bankprovinec;
	}

	@Override
	public String getApiMethodName() {
		return "fangcang.wheatfield.account.update";
	}

	@Override
	public Map<String, String> getTextParams() {
		FopHashMap localRopHashMap = new FopHashMap();
		localRopHashMap.put("accountnumber", this.accountnumber);
		localRopHashMap.put("accountid", this.accountid);
		localRopHashMap.put("bankcity", this.bankcity);
		localRopHashMap.put("hankbranchname", this.hankbranchname);
		localRopHashMap.put("constid", this.constid);
		localRopHashMap.put("accountrealname", this.accountrealname);
		localRopHashMap.put("productid", this.productid);
		localRopHashMap.put("certificatetype", this.certificatetype);
		localRopHashMap.put("certificatenumber", this.certificatenumber);
		localRopHashMap.put("hankbranch", this.hankbranch);
		localRopHashMap.put("bankhead", this.bankhead);
		localRopHashMap.put("userid", this.userid);
		localRopHashMap.put("hankheadname", this.hankheadname);
		localRopHashMap.put("usertype", this.usertype);
		localRopHashMap.put("role", this.role);
		localRopHashMap.put("bankprovinec", this.bankprovinec);
		if (this.udfParams != null) {
			localRopHashMap.putAll(this.udfParams);
		}
		return localRopHashMap;
	}

	@Override
	public Class<WheatfieldAccountUpdateResponse> getResponseClass() {
		return WheatfieldAccountUpdateResponse.class;
	}

	@Override
	public void check() throws ApiRuleException {

	}

}
