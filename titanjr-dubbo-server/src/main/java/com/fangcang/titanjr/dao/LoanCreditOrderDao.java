package com.fangcang.titanjr.dao;


import java.util.List;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.CreditCompanyInfoDTO;
import com.fangcang.titanjr.entity.LoanCreditOrder;
import com.fangcang.titanjr.entity.parameter.LoanCreditOrderParam;

public interface LoanCreditOrderDao {

	/**
     * 查询
     * @param LoanCreditOrderParam
     * @return
     */
	PaginationSupport<CreditCompanyInfoDTO> selectForPage(LoanCreditOrderParam condition, PaginationSupport<CreditCompanyInfoDTO> paginationSupport) throws DaoException;
	
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
    
    /**
     * 授信申请数量
     * @param loanCreditOrderParam
     * @return
     */
    public int getCreditOrderCount(LoanCreditOrderParam loanCreditOrderParam);
}
