package test.fangcang.titanjr.rs.invoker.loancredit;

import java.util.List;

/**
 * Created by zhaoshan on 2016/10/9.
 */
public class CreditJsonData {
    //股东信息
    private List<StockHolder> stockHolders;
    //主营业务
    private List<MainBusiness> mainBusinesses;
    //合作企业信息
    private List<CooperativeEnterprise> cooperativeEnterprises;

    //经营场所信息
    private BusinessPlace businessPlace;

    //企业担保人信息
    private CompanyGuarantee companyGuarantee;

    //个人担保人信息
    private PersonGuarantee personGuarantee;

    public List<StockHolder> getStockHolders() {
        return stockHolders;
    }

    public void setStockHolders(List<StockHolder> stockHolders) {
        this.stockHolders = stockHolders;
    }

    public List<MainBusiness> getMainBusinesses() {
        return mainBusinesses;
    }

    public void setMainBusinesses(List<MainBusiness> mainBusinesses) {
        this.mainBusinesses = mainBusinesses;
    }

    public List<CooperativeEnterprise> getCooperativeEnterprises() {
        return cooperativeEnterprises;
    }

    public void setCooperativeEnterprises(List<CooperativeEnterprise> cooperativeEnterprises) {
        this.cooperativeEnterprises = cooperativeEnterprises;
    }

    public BusinessPlace getBusinessPlace() {
        return businessPlace;
    }

    public void setBusinessPlace(BusinessPlace businessPlace) {
        this.businessPlace = businessPlace;
    }

    public CompanyGuarantee getCompanyGuarantee() {
        return companyGuarantee;
    }

    public void setCompanyGuarantee(CompanyGuarantee companyGuarantee) {
        this.companyGuarantee = companyGuarantee;
    }

    public PersonGuarantee getPersonGuarantee() {
        return personGuarantee;
    }

    public void setPersonGuarantee(PersonGuarantee personGuarantee) {
        this.personGuarantee = personGuarantee;
    }
}
