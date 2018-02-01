package com.fangcang.titanjr.dto.bean;


import java.util.Date;
import java.util.List;

public class TitanPayMethod implements java.io.Serializable {

    private static final long serialVersionUID = -8351127349246720200L;
    private Long id;
    private String name;
    private String gatewayURL;
    private String checkKey;
    private String titanjrCheckKey;
    //checkstand支付结果前台回调pay-app的地址
    private String csPayConfirmPageURL;
    //checkstand支付结果后台通知pay-app的地址
    private String csPayNoticeURL;
    //checkstand卡密鉴权前台回调pay-app的地址
    private String csCardAuthPageURL;
    //checkstand卡密鉴权后台通知pay-app的地址
    private String csCardAuthNoticeURL;
    private String creator;
    private Date createTime;

    private List<TitanPayMethodConfig> payMethodConfigList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGatewayURL() {
        return gatewayURL;
    }

    public void setGatewayURL(String gatewayURL) {
        this.gatewayURL = gatewayURL;
    }

    public String getCheckKey() {
        return checkKey;
    }

    public void setCheckKey(String checkKey) {
        this.checkKey = checkKey;
    }

    public String getTitanjrCheckKey() {
        return titanjrCheckKey;
    }

    public void setTitanjrCheckKey(String titanjrCheckKey) {
        this.titanjrCheckKey = titanjrCheckKey;
    }

	public String getCsPayConfirmPageURL() {
		return csPayConfirmPageURL;
	}

	public void setCsPayConfirmPageURL(String csPayConfirmPageURL) {
		this.csPayConfirmPageURL = csPayConfirmPageURL;
	}

	public String getCsPayNoticeURL() {
		return csPayNoticeURL;
	}

	public void setCsPayNoticeURL(String csPayNoticeURL) {
		this.csPayNoticeURL = csPayNoticeURL;
	}

	public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<TitanPayMethodConfig> getPayMethodConfigList() {
        return payMethodConfigList;
    }

    public void setPayMethodConfigList(List<TitanPayMethodConfig> payMethodConfigList) {
        this.payMethodConfigList = payMethodConfigList;
    }

	public String getCsCardAuthPageURL() {
		return csCardAuthPageURL;
	}

	public void setCsCardAuthPageURL(String csCardAuthPageURL) {
		this.csCardAuthPageURL = csCardAuthPageURL;
	}

	public String getCsCardAuthNoticeURL() {
		return csCardAuthNoticeURL;
	}

	public void setCsCardAuthNoticeURL(String csCardAuthNoticeURL) {
		this.csCardAuthNoticeURL = csCardAuthNoticeURL;
	}
}