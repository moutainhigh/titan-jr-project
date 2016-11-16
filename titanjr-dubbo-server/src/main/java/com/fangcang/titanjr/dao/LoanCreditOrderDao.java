package com.fangcang.titanjr.dao;


import com.fangcang.titanjr.entity.LoanCreditOrder;

import java.util.List;

public interface LoanCreditOrderDao {

    /**
     * 插入
     * @param loanCreditOrder
     * @return
     */
    int saveLoanCreditOrder(LoanCreditOrder loanCreditOrder);

    /**
     * 查询
     * @param loanCreditOrder
     * @return
     */
    List<LoanCreditOrder> queryLoanCreditOrder(LoanCreditOrder loanCreditOrder);

    /**
     *
     * @param loanCreditOrder
     * @return
     */
    int updateLoanCreditOrder(LoanCreditOrder loanCreditOrder);
}
