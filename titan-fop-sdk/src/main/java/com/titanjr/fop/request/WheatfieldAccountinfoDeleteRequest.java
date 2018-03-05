package com.titanjr.fop.request;

import java.util.Map;

import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.WheatfieldAccountinfoDeleteResponse;


/***
 * 删除绑卡
 * @author luoqinglong
 * @date 2018年3月5日
 */
public class WheatfieldAccountinfoDeleteRequest implements FopRequest<WheatfieldAccountinfoDeleteResponse> {

	private FopHashMap udfParams;
	private String userid;
	private Long referuserid;
	private String role;
	private String accountnumber;
	private String usertype;
	private String constid;
	private String productid;

	
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

	public Long getReferuserid() {
		return referuserid;
	}

	public void setReferuserid(Long referuserid) {
		this.referuserid = referuserid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAccountnumber() {
		return accountnumber;
	}

	public void setAccountnumber(String accountnumber) {
		this.accountnumber = accountnumber;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getConstid() {
		return constid;
	}

	public void setConstid(String constid) {
		this.constid = constid;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	@Override
	public String getApiMethodName() {
		return "fangcang.wheatfield.accountinfo.delete";
	}

	@Override
	public Map<String, String> getTextParams() {
		FopHashMap localRopHashMap = new FopHashMap();
	    localRopHashMap.put("userid", this.userid);
	    localRopHashMap.put("referuserid", this.referuserid);
	    localRopHashMap.put("role", this.role);
	    localRopHashMap.put("accountnumber", this.accountnumber);
	    localRopHashMap.put("usertype", this.usertype);
	    localRopHashMap.put("constid", this.constid);
	    localRopHashMap.put("productid", this.productid);
	    if (this.udfParams != null) {
	      localRopHashMap.putAll(this.udfParams);
	    }
	    return localRopHashMap;
	}

	@Override
	public Class<WheatfieldAccountinfoDeleteResponse> getResponseClass() {
		return WheatfieldAccountinfoDeleteResponse.class;
	}

	@Override
	public void check() throws ApiRuleException {

	}

}
