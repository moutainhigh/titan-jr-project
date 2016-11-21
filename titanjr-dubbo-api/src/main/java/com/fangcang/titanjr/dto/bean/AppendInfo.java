package com.fangcang.titanjr.dto.bean;

import java.util.List;

/**
 * Created by zhaoshan on 2016/11/18.
 */
public class AppendInfo {
    private List<LoanControllData> controllDatas;
    private List<LoanCooperationCompanyInfo> cooperationCompanyInfos;
    private List<LoanMainBusinessData> mainBusinessDatas;

    public List<LoanControllData> getControllDatas() {
        return controllDatas;
    }

    public void setControllDatas(List<LoanControllData> controllDatas) {
        this.controllDatas = controllDatas;
    }

    public List<LoanCooperationCompanyInfo> getCooperationCompanyInfos() {
        return cooperationCompanyInfos;
    }

    public void setCooperationCompanyInfos(
            List<LoanCooperationCompanyInfo> cooperationCompanyInfos) {
        this.cooperationCompanyInfos = cooperationCompanyInfos;
    }

    public List<LoanMainBusinessData> getMainBusinessDatas() {
        return mainBusinessDatas;
    }

    public void setMainBusinessDatas(
            List<LoanMainBusinessData> mainBusinessDatas) {
        this.mainBusinessDatas = mainBusinessDatas;
    }

}
