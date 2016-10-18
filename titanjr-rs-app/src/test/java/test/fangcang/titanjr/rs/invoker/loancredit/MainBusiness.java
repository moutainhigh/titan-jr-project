package test.fangcang.titanjr.rs.invoker.loancredit;

import java.math.BigDecimal;

/**
 * Created by zhaoshan on 2016/10/9.
 * 主营业务信息
 */
public class MainBusiness {
    //主要产品/服务
    private String businessName;
    //年销售规模
    private BigDecimal yearlySaleAmount;
    //占总销售额比例
    private String yearlySaleRatio;

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public BigDecimal getYearlySaleAmount() {
        return yearlySaleAmount;
    }

    public void setYearlySaleAmount(BigDecimal yearlySaleAmount) {
        this.yearlySaleAmount = yearlySaleAmount;
    }

    public String getYearlySaleRatio() {
        return yearlySaleRatio;
    }

    public void setYearlySaleRatio(String yearlySaleRatio) {
        this.yearlySaleRatio = yearlySaleRatio;
    }
}
