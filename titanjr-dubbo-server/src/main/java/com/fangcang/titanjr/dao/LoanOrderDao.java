package com.fangcang.titanjr.dao;

import com.fangcang.titanjr.entity.LoanApplyOrder;

import java.util.List;

public interface LoanOrderDao {
    /**
     * 插入
     * @param loanOrder
     * @return
     */
    int saveLoanApplyOrder(LoanApplyOrder loanOrder);

    /**
     * 查询
     * @param loanOrder
     * @return
     */
    List<LoanApplyOrder> queryLoanApplyOrder(LoanApplyOrder loanOrder);

    /**
     *
     * @param loanOrder
     * @return
     */
    int updateLoanApplyOrder(LoanApplyOrder loanOrder);
}
