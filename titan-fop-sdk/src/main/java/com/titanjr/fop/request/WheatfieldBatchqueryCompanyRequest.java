package com.titanjr.fop.request;

import java.util.Map;

import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldBatchqueryCompanyResponse;

public class WheatfieldBatchqueryCompanyRequest implements FopRequest<WheatfieldBatchqueryCompanyResponse> {
	private FopHashMap udfParams;
	private String constid;
	private String buslince;
	private String status;
	private String userid;
	private String companyname;

	public FopHashMap getUdfParams() {
		return udfParams;
	}

	public void setUdfParams(FopHashMap udfParams) {
		this.udfParams = udfParams;
	}

	public String getConstid() {
		return constid;
	}

	public void setConstid(String constid) {
		this.constid = constid;
	}

	public String getBuslince() {
		return buslince;
	}

	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	@Override
	public String getApiMethodName() {
		return "ruixue.wheatfield.batchquery.company";
	}

	@Override
	public Map<String, String> getTextParams() {
		FopHashMap localRopHashMap = new FopHashMap();
		localRopHashMap.put("constid", this.constid);
		localRopHashMap.put("buslince", this.buslince);
		localRopHashMap.put("status", this.status);
		localRopHashMap.put("userid", this.userid);
		localRopHashMap.put("companyname", this.companyname);
		if (this.udfParams != null) {
			localRopHashMap.putAll(this.udfParams);
		}
		return localRopHashMap;
	}

	@Override
	public Class<WheatfieldBatchqueryCompanyResponse> getResponseClass() {
		return WheatfieldBatchqueryCompanyResponse.class;
	}

	@Override
	public void check() throws ApiRuleException {

	}

}
