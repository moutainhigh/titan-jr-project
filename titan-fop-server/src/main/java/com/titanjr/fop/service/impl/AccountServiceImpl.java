package com.titanjr.fop.service.impl;

import com.fangcang.exception.ServiceException;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.titanjr.fop.dao.TitanAccountDao;
import com.titanjr.fop.dto.SHBalanceInfo;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.request.WheatfieldBalanceGetlistRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceAuthcodeserviceRequest;
import com.titanjr.fop.service.AccountService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoshan on 2017/12/22.
 */
@Component
public class AccountServiceImpl implements AccountService {

    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    TitanAccountDao titanAccountDao;

    @Autowired
    TitanFinancialOrganService titanFinancialOrganService;

    @Override
    public List<SHBalanceInfo> getAccountBalanceList(WheatfieldBalanceGetlistRequest balanceGetlistRequest) throws ServiceException {

        List<SHBalanceInfo> result = new ArrayList<SHBalanceInfo>();

        AccountBalanceRequest balanceRequest = new AccountBalanceRequest();
        balanceRequest.setUserid(balanceGetlistRequest.getUserid());
        balanceRequest.setRootinstcd(balanceGetlistRequest.getRootinstcd());
        List<AccountBalance> balanceList = titanAccountDao.queryAccountBalanceList(balanceRequest);

        if (CollectionUtils.isNotEmpty(balanceList)) {
            for (AccountBalance accountBalance : balanceList) {
                SHBalanceInfo balanceInfo = new SHBalanceInfo();
                balanceInfo.setUserid(accountBalance.getUserid());
                balanceInfo.setAmount(accountBalance.getAmount());
                balanceInfo.setBalancecredit(accountBalance.getBalancecredit());
                balanceInfo.setBalancefrozon(accountBalance.getBalancefrozon());
                balanceInfo.setBalanceoverlimit(accountBalance.getBalanceoverlimit());
                balanceInfo.setBalancesettle(accountBalance.getBalancesettle());
                balanceInfo.setBalanceusable(accountBalance.getBalanceusable());
                balanceInfo.setProductid(accountBalance.getProductid());
                result.add(balanceInfo);
            }
        }

        return result;
    }

    @Override
    public String freezeAccountBalance(WheatfieldOrderServiceAuthcodeserviceRequest authcodeserviceRequest) throws ServiceException {


        return null;
    }
}
