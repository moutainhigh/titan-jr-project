package com.fangcang.titanjr.rs.request;

import javax.validation.constraints.NotNull;

import com.fangcang.titanjr.common.exception.RSValidateException;
import com.fangcang.titanjr.common.util.RequestValidationUtil;
import com.fangcang.util.StringUtil;

/***
 * 授信申请
 * @author luoqinglong
 * @2016年10月31日
 */
public class OrderMixserviceCreditapplicationRequest extends BaseRequest{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6468829522239666199L;
	/**
	 * 	第三方申请编号
	 */
	@NotNull
	private String userorderid;
	/**
	 * 	机构码 融数分配
	 */
	@NotNull
	private String rootinstcd;
	/**
	 * 申请金额 单位:分
	 */
	@NotNull
	private String amount;
	/**
	 * 关联用户id
	 */
	private String userrelateid;
	/**
	 * 申请期限 单位 月
	 */
	@NotNull
	private String reqesttime;
	/**
	 * 申请者单位名称
	 */
	@NotNull
	private String orderplatformname;
	/**
	 * 申请时间 yyyy-MM-dd HH:mm:ss
	 */
	@NotNull
	private String requestdate;
	/**
	 * 费率模板 为融数的费率模板id
	 */
	@NotNull
	private String ratetemplrate;
	/**
	 * 
	 */
	private String remark;
	/**
	 * 申请明细信息json串 如果为空 请传 {}
	 */
	@NotNull
	private String jsondata;
	/**
	 * 授信申请资料上传urlkey
	 */
	@NotNull
	private String urlkey;
	
	/**
	 * 企业申请类型：1 表示供应商授信申请2表示零售商授信申请(房仓的企业）)
	 */
	private String creditype;
	
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
	public String getOrderplatformname() {
		return orderplatformname;
	}
	public void setOrderplatformname(String orderplatformname) {
		this.orderplatformname = orderplatformname;
	}
	public String getRequestdate() {
		return requestdate;
	}
	public void setRequestdate(String requestdate) {
		this.requestdate = requestdate;
	}
	public String getRatetemplrate() {
		return ratetemplrate;
	}
	public void setRatetemplrate(String ratetemplrate) {
		this.ratetemplrate = ratetemplrate;
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
	public String getCreditype() {
		return creditype;
	}
	public void setCreditype(String creditype) {
		this.creditype = creditype;
	}
	
	
	
}
