package com.titanjr.fop.service.impl;

import com.fangcang.exception.ServiceException;
import com.titanjr.fop.dto.SHBalanceInfo;
import com.titanjr.fop.request.WheatfieldBalanceGetlistRequest;
import com.titanjr.fop.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhaoshan on 2017/12/22.
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Override
    public List<SHBalanceInfo> getAccountBalanceList(WheatfieldBalanceGetlistRequest balanceGetlistRequest) throws ServiceException {

        return null;
    }
}
