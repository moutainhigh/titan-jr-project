package com.titanjr.fop.request;

import java.util.Map;

import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldBatchqueryPersonResponse;

public class WheatfieldBatchqueryPersonRequest implements FopRequest<WheatfieldBatchqueryPersonResponse> {
	private FopHashMap udfParams;
	private String userid;
	private String certificatenumber;
	private String constid;
	private String statusid;
	private String personengname;
	private String mobiletel;
	private String certificatetype;

	public FopHashMap getUdfParams() {
		return udfParams;
	}

	public void setUdfParams(FopHashMap udfParams) {
		this.udfParams = udfParams;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCertificatenumber() {
		return certificatenumber;
	}

	public void setCertificatenumber(String certificatenumber) {
		this.certificatenumber = certificatenumber;
	}

	public String getConstid() {
		return constid;
	}

	public void setConstid(String constid) {
		this.constid = constid;
	}

	public String getStatusid() {
		return statusid;
	}

	public void setStatusid(String statusid) {
		this.statusid = statusid;
	}

	public String getPersonengname() {
		return personengname;
	}

	public void setPersonengname(String personengname) {
		this.personengname = personengname;
	}

	public String getMobiletel() {
		return mobiletel;
	}

	public void setMobiletel(String mobiletel) {
		this.mobiletel = mobiletel;
	}

	public String getCertificatetype() {
		return certificatetype;
	}

	public void setCertificatetype(String certificatetype) {
		this.certificatetype = certificatetype;
	}

	@Override
	public String getApiMethodName() {
		return "ruixue.wheatfield.batchquery.person";
	}

	@Override
	public Map<String, String> getTextParams() {

		FopHashMap localRopHashMap = new FopHashMap();
		localRopHashMap.put("userid", this.userid);
		localRopHashMap.put("certificatenumber", this.certificatenumber);
		localRopHashMap.put("constid", this.constid);
		localRopHashMap.put("statusid", this.statusid);
		localRopHashMap.put("personengname", this.personengname);
		localRopHashMap.put("mobiletel", this.mobiletel);
		localRopHashMap.put("certificatetype", this.certificatetype);
		if (this.udfParams != null) {
			localRopHashMap.putAll(this.udfParams);
		}
		return localRopHashMap;
	}

	@Override
	public Class<WheatfieldBatchqueryPersonResponse> getResponseClass() {

		return WheatfieldBatchqueryPersonResponse.class;
	}

	@Override
	public void check() throws ApiRuleException {

	}

}
