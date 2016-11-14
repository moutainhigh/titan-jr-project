package com.fangcang.titanjr.rs.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;
import com.fangcang.util.StringUtil;

/**
 * 个人贷款申请
 * @author luoqinglong
 * @2016年10月31日
 */
public class NewLoanApplyRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8881755759081181264L;
	/**
	 * 	机构码 融数分配
	 */
	@NotNull
	private String rootinstcd;
	/**
	 * 	交易流水
	 */
	@NotNull
	private String userorderid;
	/**
	 * 	申请金额
	 */
	@NotNull
	private String amount;
	/**
	 * 申请的机构(业务款接收方)
	 */
	@NotNull
	private String userrelateid;
	/**
	 * 	申请期限
	 */
	private String reqesttime;
	/**
	 * 	申请人姓名
	 */
	@NotNull
	private String username;
	/**
	 * 申请时间
	 */
	@NotNull
	private String requestdate;
	/**
	 * 	费率模板
	 */
	@NotNull
	private String ratetemplate;
	/**
	 * 
	 */
	private String remark;
	/**
	 * 申请明细信息json串
	 */
	@NotNull
	private String jsondata;
	/**
	 * 贷款申请资料上传urlkey
	 */
	@NotNull
	private String urlkey;
	
	@Override
	public void check() throws RSValidateException {
		//校验不能为空
		RequestValidationUtil.check(this);
		RequestValidationUtil.checkNotEmpty(getUserid(), "userid");
		RequestValidationUtil.checkNotEmpty(getProductid(), "productid");
	}
	
	/**
	 * 兼容父类的constid
	 * @return
	 */
	public String getRootinstcd() {
		if(StringUtil.isValidString(rootinstcd)){
			return rootinstcd;
		}
		return getConstid();
	}

	public void setRootinstcd(String rootinstcd) {
		this.setConstid(rootinstcd);
		this.rootinstcd = rootinstcd;
	}

	public String getUserorderid() {
		return userorderid;
	}
	public void setUserorderid(String userorderid) {
		this.userorderid = userorderid;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getUserrelateid() {
		return userrelateid;
	}
	public void setUserrelateid(String userrelateid) {
		this.userrelateid = userrelateid;
	}
	public String getReqesttime() {
		return reqesttime;
	}
	public void setReqesttime(String reqesttime) {
		this.reqesttime = reqesttime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRequestdate() {
		return requestdate;
	}
	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}
	public String getRatetemplate() {
		return ratetemplate;
	}
	public void setRatetemplate(String ratetemplate) {
		this.ratetemplate = ratetemplate;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getJsondata() {
		return jsondata;
	}
	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}
	public String getUrlkey() {
		return urlkey;
	}
	public void setUrlkey(String urlkey) {
		this.urlkey = urlkey;
	}
	
}
