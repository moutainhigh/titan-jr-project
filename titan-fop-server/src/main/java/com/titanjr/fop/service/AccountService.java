package com.titanjr.fop.service;

import com.fangcang.exception.ServiceException;
import com.titanjr.fop.dto.SHBalanceInfo;
import com.titanjr.fop.request.WheatfieldBalanceGetlistRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceAuthcodeserviceRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceThawauthcodeRequest;
import com.titanjr.fop.response.WheatfieldOrderServiceThawauthcodeResponse;

import java.util.List;

/**
 * Created by zhaoshan on 2017/12/22.
 */
public interface AccountService {

    /**
     * 查询获取账户余额列表
     * @param balanceGetlistRequest
     * @return
     * @throws ServiceException
     */
    List<SHBalanceInfo> getAccountBalanceList(WheatfieldBalanceGetlistRequest balanceGetlistRequest) throws ServiceException;


    /**
     * 冻结账户资金，返回null表示冻结失败
     * @param authcodeserviceRequest
     * @return
     * @throws ServiceException
     */
    String freezeAccountBalance(WheatfieldOrderServiceAuthcodeserviceRequest authcodeserviceRequest) throws ServiceException;

    /**
     * 解冻账户资金操作
     * @param thawauthcodeRequest
     * @return
     * @throws ServiceException
     */
    WheatfieldOrderServiceThawauthcodeResponse unFreezeAccountBalance(WheatfieldOrderServiceThawauthcodeRequest thawauthcodeRequest) throws ServiceException;
}
