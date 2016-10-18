package test.fangcang.titanjr.rs.invoker.loancredit;

import java.math.BigDecimal;

/**
 * Created by zhaoshan on 2016/10/9.
 * 股东信息列表
 */
public class StockHolder {
    //股东名称
    private String name;
    //出资金额
    private BigDecimal contributionAmount;
    //股权占比
    private String equityRatio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getContributionAmount() {
        return contributionAmount;
    }

    public void setContributionAmount(BigDecimal contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public String getEquityRatio() {
        return equityRatio;
    }

    public void setEquityRatio(String equityRatio) {
        this.equityRatio = equityRatio;
    }
}

