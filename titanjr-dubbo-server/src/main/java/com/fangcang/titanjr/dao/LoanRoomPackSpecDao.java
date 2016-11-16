package com.fangcang.titanjr.dao;

import com.fangcang.titanjr.entity.LoanRoomPackSpec;

import java.util.List;

public interface LoanRoomPackSpecDao {
    /**
     * 插入
     * @param loanRoomPackSpec
     * @return
     */
    int saveLoanRoomPackSpec(LoanRoomPackSpec loanRoomPackSpec);

    /**
     * 查询
     * @param loanRoomPackSpec
     * @return
     */
    List<LoanRoomPackSpec> queryLoanRoomPackSpec(LoanRoomPackSpec loanRoomPackSpec);

    /**
     *
     * @param loanRoomPackSpec
     * @return
     */
    int updateLoanRoomPackSpec(LoanRoomPackSpec loanRoomPackSpec);
}
