package com.titanjr.fop.service;

import com.fangcang.exception.ServiceException;
import com.titanjr.fop.dto.SHBalanceInfo;
import com.titanjr.fop.request.WheatfieldBalanceGetlistRequest;

import java.util.List;

/**
 * Created by zhaoshan on 2017/12/22.
 */
public interface AccountService {

    /**
     * 查询获取
     * @param balanceGetlistRequest
     * @return
     * @throws ServiceException
     */
    List<SHBalanceInfo> getAccountBalanceList(WheatfieldBalanceGetlistRequest balanceGetlistRequest) throws ServiceException;
}
