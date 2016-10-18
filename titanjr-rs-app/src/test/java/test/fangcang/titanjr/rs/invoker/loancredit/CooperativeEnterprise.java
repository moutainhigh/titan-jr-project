package test.fangcang.titanjr.rs.invoker.loancredit;

import java.math.BigDecimal;

/**
 * Created by zhaoshan on 2016/10/9.
 * 合作企业信息
 */
public class CooperativeEnterprise {
    //合作企业名称
    private String companyName;
    //年交易额
    private BigDecimal yearlyTradeAmount;
    //占总销售额比例
    private String yearlyTradeRatio;
    //结算方式
    private Integer settlementMethod;
    //合作年限
    private String cooperativeYears;
    //合作关系，分销商 供应商。
    private Integer cooperativeType;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getYearlyTradeAmount() {
        return yearlyTradeAmount;
    }

    public void setYearlyTradeAmount(BigDecimal yearlyTradeAmount) {
        this.yearlyTradeAmount = yearlyTradeAmount;
    }

    public String getYearlyTradeRatio() {
        return yearlyTradeRatio;
    }

    public void setYearlyTradeRatio(String yearlyTradeRatio) {
        this.yearlyTradeRatio = yearlyTradeRatio;
    }

    public Integer getSettlementMethod() {
        return settlementMethod;
    }

    public void setSettlementMethod(Integer settlementMethod) {
        this.settlementMethod = settlementMethod;
    }

    public String getCooperativeYears() {
        return cooperativeYears;
    }

    public void setCooperativeYears(String cooperativeYears) {
        this.cooperativeYears = cooperativeYears;
    }

    public Integer getCooperativeType() {
        return cooperativeType;
    }

    public void setCooperativeType(Integer cooperativeType) {
        this.cooperativeType = cooperativeType;
    }
}
