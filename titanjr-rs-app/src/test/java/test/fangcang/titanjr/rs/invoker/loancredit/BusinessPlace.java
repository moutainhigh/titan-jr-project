package test.fangcang.titanjr.rs.invoker.loancredit;

import java.math.BigDecimal;

/**
 * Created by zhaoshan on 2016/10/9.
 * 经营场所
 */
public class BusinessPlace {
    //自由还是租赁
    private Integer propertyType;
    //租赁期限
    private String leaseTime;
    //租赁地址
    private String leaseAddress;
    //建筑面积
    private String coveredArea;
    //租金
    private BigDecimal rentAmount;
    //支付方式
    private Integer payMethod;
    //备注
    private String remark;

    public Integer getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(Integer propertyType) {
        this.propertyType = propertyType;
    }

    public String getLeaseTime() {
        return leaseTime;
    }

    public void setLeaseTime(String leaseTime) {
        this.leaseTime = leaseTime;
    }

    public String getLeaseAddress() {
        return leaseAddress;
    }

    public void setLeaseAddress(String leaseAddress) {
        this.leaseAddress = leaseAddress;
    }

    public String getCoveredArea() {
        return coveredArea;
    }

    public void setCoveredArea(String coveredArea) {
        this.coveredArea = coveredArea;
    }

    public BigDecimal getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(BigDecimal rentAmount) {
        this.rentAmount = rentAmount;
    }

    public Integer getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(Integer payMethod) {
        this.payMethod = payMethod;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
