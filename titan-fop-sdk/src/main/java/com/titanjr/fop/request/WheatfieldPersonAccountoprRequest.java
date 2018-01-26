package com.titanjr.fop.request;

import java.util.Map;

import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldPersonAccountoprResponse;

/**
 * 个人机构新增和修改
 * 
 * @author luoqinglong
 * @date 2018年1月23日
 */
public class WheatfieldPersonAccountoprRequest implements FopRequest<WheatfieldPersonAccountoprResponse> {

	private FopHashMap udfParams;
	private String productid;
	//private String persontype;
	//private String personchnname;
	//private String constid;
	private String remark;
	private String certificatetype;
	private String certificatenumber;
	private String mobiletel;
	private String userid;
	private String referuserid;
	private String username;
	//操作类型（1：新增，2：修改）
	private String opertype;

	
	public FopHashMap getUdfParams() {
		return udfParams;
	}

	public void setUdfParams(FopHashMap udfParams) {
		this.udfParams = udfParams;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getMobiletel() {
		return mobiletel;
	}

	public void setMobiletel(String mobiletel) {
		this.mobiletel = mobiletel;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getReferuserid() {
		return referuserid;
	}

	public void setReferuserid(String referuserid) {
		this.referuserid = referuserid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOpertype() {
		return opertype;
	}

	public void setOpertype(String opertype) {
		this.opertype = opertype;
	}

	@Override
	public String getApiMethodName() {
		return "ruixue.wheatfield.person.accountopr";
	}

	@Override
	public Map<String, String> getTextParams() {
		FopHashMap localRopHashMap = new FopHashMap();
	    localRopHashMap.put("productid", this.productid);
	    localRopHashMap.put("remark", this.remark);
	    localRopHashMap.put("certificatetype", this.certificatetype);
	    localRopHashMap.put("certificatenumber", this.certificatenumber);
	    localRopHashMap.put("mobiletel", this.mobiletel);
	    localRopHashMap.put("userid", this.userid);
	    localRopHashMap.put("referuserid", this.referuserid);
	    localRopHashMap.put("username", this.username);
	    localRopHashMap.put("opertype", this.opertype);
	    if (this.udfParams != null) {
	      localRopHashMap.putAll(this.udfParams);
	    }
	    return localRopHashMap;
	}

	@Override
	public Class<WheatfieldPersonAccountoprResponse> getResponseClass() {
		return WheatfieldPersonAccountoprResponse.class;
	}

	@Override
	public void check() throws ApiRuleException {

	}

}
