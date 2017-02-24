package com.fangcang.titanjr.entity;

import java.io.Serializable;
import java.util.Date;

public class TitanPayMethodConfig implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//对应于payMethod表中的key，外键关联',
	  private Integer paymethodid;
	  //'配置给金服平台哪个商家使用',
	  private String userid;
	  //'前台页面通知地址'
	  private String pageurl;
	  //'后台服务通知地址'
	  private String notifyurl;
	  //'创建人'
	  private String createor;
	  //'创建时间'
	  private Date createTime;
	public Integer getPaymethodid() {
		return paymethodid;
	}
	public void setPaymethodid(Integer paymethodid) {
		this.paymethodid = paymethodid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPageurl() {
		return pageurl;
	}
	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}
	public String getNotifyurl() {
		return notifyurl;
	}
	public void setNotifyurl(String notifyurl) {
		this.notifyurl = notifyurl;
	}
	public String getCreateor() {
		return createor;
	}
	public void setCreateor(String createor) {
		this.createor = createor;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
