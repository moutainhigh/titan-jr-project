package com.fangcang.titanjr.entity.parameter;

public class TitanOrgParam {

    /**
     *
     */
    private static final long serialVersionUID = 6347102875964297912L;
    private Integer orgId;
    private String orgCode;
    private String titanCode;
    private String orgName;
    private String userId;
    private Integer certificateType;
    private String certificateNumber;
    private String busLince;
    private Integer statusId;
    private String productId;
    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getTitanCode() {
        return titanCode;
    }

    public void setTitanCode(String titanCode) {
        this.titanCode = titanCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCertificateType() {
        return certificateType;
    }

    public void setCertificateType(Integer certificateType) {
        this.certificateType = certificateType;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getBusLince() {
        return busLince;
    }

    public void setBusLince(String busLince) {
        this.busLince = busLince;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}