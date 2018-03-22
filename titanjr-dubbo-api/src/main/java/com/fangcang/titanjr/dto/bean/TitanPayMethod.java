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
    
}