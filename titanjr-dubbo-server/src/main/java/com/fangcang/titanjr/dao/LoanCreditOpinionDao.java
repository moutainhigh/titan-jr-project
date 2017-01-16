package com.fangcang.titanjr.dao;

import com.fangcang.titanjr.entity.LoanCreditOpinion;

import java.util.List;

public interface LoanCreditOpinionDao {
    /**
     * 插入
     * @param loanCreditOpinion
     * @return
     */
    int saveLoanCreditOpinion(LoanCreditOpinion loanCreditOpinion);

    /**
     * 查询
     * @param loanCreditOpinion
     * @return
     */
    List<LoanCreditOpinion> queryLoanCreditOpinion(LoanCreditOpinion loanCreditOpinion);

    /**
     *
     * @param loanCreditOpinion
     * @return
     */
    int updateLoanCreditOpinion(LoanCreditOpinion loanCreditOpinion);
}
