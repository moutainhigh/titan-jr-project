package com.fangcang.titanjr.dao;

import com.fangcang.titanjr.entity.LoanCompanyEnsure;

import java.util.List;

/**
 * Created by zhaoshan on 2016/11/4.
 */
public interface LoanCompanyEnsureDao {

    /**
     * 插入
     * @param loanCompanyEnsure
     * @return
     */
    int saveCompanyEnsure(LoanCompanyEnsure loanCompanyEnsure);

    /**
     * 查询
     * @param loanCompanyEnsure
     * @return
     */
    List<LoanCompanyEnsure> queryLoanCompanyEnsure(LoanCompanyEnsure loanCompanyEnsure);

    /**
     *
     * @param loanCompanyEnsure
     * @return
     */
    int updateCompanyEnsure(LoanCompanyEnsure loanCompanyEnsure);
}
