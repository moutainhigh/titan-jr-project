package com.fangcang.titanjr.dao;


import com.fangcang.titanjr.entity.LoanCreditCompany;

import java.util.List;

/**
 * Created by zhaoshan on 2016/11/4.
 */
public interface LoanCreditCompanyDao {
    /**
     * 保存
     * @param loanCreditCompany
     * @return
     */
    int saveCreditCompany(LoanCreditCompany loanCreditCompany);

    /**
     *
     * @param loanCreditCompany
     * @return
     */
    List<LoanCreditCompany> queryLoanCreditCompany(LoanCreditCompany loanCreditCompany);

    /**
     *
     * @param loanCreditCompany
     * @return
     */
    int updateCreditCompany(LoanCreditCompany loanCreditCompany);
}
