package com.titanjr.fop.service.impl;

import com.fangcang.exception.ServiceException;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.entity.TitanFundUnFreezereq;
import com.fangcang.titanjr.entity.parameter.TitanUnFundFreezereqParam;
import com.fangcang.titanjr.service.TitanOrderService;
import com.titanjr.fop.dao.TitanAccountDao;
import com.titanjr.fop.dto.BalanceQueryDTO;
import com.titanjr.fop.dto.SHBalanceInfo;
import com.titanjr.fop.request.WheatfieldBalanceGetlistRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceAuthcodeserviceRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceThawauthcodeRequest;
import com.titanjr.fop.response.WheatfieldOrderServiceThawauthcodeResponse;
import com.titanjr.fop.service.AccountService;
import com.titanjr.fop.util.ResponseUtils;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by zhaoshan on 2017/12/22.
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    TitanAccountDao titanAccountDao;

    @Autowired
    TitanOrderService titanOrderService;

    @Override
    public List<SHBalanceInfo> getAccountBalanceList(WheatfieldBalanceGetlistRequest balanceGetlistRequest) throws ServiceException {

        List<SHBalanceInfo> result = new ArrayList<SHBalanceInfo>();

        BalanceQueryDTO balanceQueryDTO = new BalanceQueryDTO();
        balanceQueryDTO.setUserId(balanceGetlistRequest.getUserid());
        List<AccountBalance> balanceList = titanAccountDao.queryAccountBalanceList(balanceQueryDTO);

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
        //查询冻结单当前是否存在以及状态
        FundFreezeDTO fundFreezeDTO = new FundFreezeDTO();
        fundFreezeDTO.setOrderNo(authcodeserviceRequest.getOrderno());
        List<FundFreezeDTO> freezeDTOList = titanOrderService.queryFundFreezeDTO(fundFreezeDTO);
        if (CollectionUtils.isNotEmpty(freezeDTOList)) {
            logger.error("当前交易单已存在冻结请求：{}", authcodeserviceRequest.getOrderno());
            return null;
        }

        BalanceQueryDTO balanceQueryDTO = new BalanceQueryDTO();
        balanceQueryDTO.setUserId(authcodeserviceRequest.getUserid());
        balanceQueryDTO.setProductId(authcodeserviceRequest.getProductid());
        List<AccountBalance> balanceList = titanAccountDao.queryAccountBalanceList(balanceQueryDTO);
        if (CollectionUtils.isEmpty(balanceList) || balanceList.size() > 1) {
            logger.error("账户余额查询结果异常：{}", balanceList);
            return null;
        }
        Long accFrozen = Long.parseLong(balanceList.get(0).getBalancefrozon());
        Long accUseable = Long.parseLong(balanceList.get(0).getBalanceusable());
        balanceList.get(0).setBalancefrozon(String.valueOf(accFrozen + authcodeserviceRequest.getAmount()));
        balanceList.get(0).setBalanceusable(String.valueOf(accUseable - authcodeserviceRequest.getAmount()));
        int count = titanAccountDao.updateAccountBalance(balanceList.get(0));
        if (count < 1) {
            logger.error("账户余额更新异常");
            return null;
        }
        //返回生成的验证码
        return getFreezeAuthCode();
    }

    @Override
    public WheatfieldOrderServiceThawauthcodeResponse unFreezeAccountBalance(WheatfieldOrderServiceThawauthcodeRequest thawauthcodeRequest) throws ServiceException {
        WheatfieldOrderServiceThawauthcodeResponse thawauthcodeResponse = new WheatfieldOrderServiceThawauthcodeResponse();
        //查询冻结记录
        FundFreezeDTO fundFreezeDTO = new FundFreezeDTO();
        fundFreezeDTO.setAuthCode(thawauthcodeRequest.getAuthcode());
        fundFreezeDTO.setRequestNo(thawauthcodeRequest.getRequestno());
        List<FundFreezeDTO> freezeDTOList = titanOrderService.queryFundFreezeDTO(fundFreezeDTO);
        //无正确的冻结单
        if (CollectionUtils.isEmpty(freezeDTOList) || freezeDTOList.size() > 1) {
            ResponseUtils.getSysErrorResp(thawauthcodeResponse);
            return thawauthcodeResponse;
        }
        //查询解冻记录，若存在则有问题
        TitanUnFundFreezereqParam unFundFreezereqParam = new TitanUnFundFreezereqParam();
        unFundFreezereqParam.setRequestno(thawauthcodeRequest.getRequestno());
        List<TitanFundUnFreezereq> unFreezereqList = titanAccountDao.queryUnFreezeRequest(unFundFreezereqParam);
        //已解冻或存在解冻单
        if (freezeDTOList.get(0).getStatus() == 2 || CollectionUtils.isNotEmpty(unFreezereqList)) {
            ResponseUtils.getSysErrorResp(thawauthcodeResponse);
            return thawauthcodeResponse;
        }
        //查询并更新数据库修改账户余额
        BalanceQueryDTO balanceQueryDTO = new BalanceQueryDTO();
        balanceQueryDTO.setUserId(freezeDTOList.get(0).getUserId());
        balanceQueryDTO.setProductId(freezeDTOList.get(0).getProductId());
        List<AccountBalance> balanceList = titanAccountDao.queryAccountBalanceList(balanceQueryDTO);
        if (CollectionUtils.isEmpty(balanceList) || balanceList.size() > 1) {
            logger.error("账户余额查询结果异常：{}", balanceList);
            ResponseUtils.getSysErrorResp(thawauthcodeResponse);
            return thawauthcodeResponse;
        }
        Long accFrozen = Long.parseLong(balanceList.get(0).getBalancefrozon());
        Long accUseable = Long.parseLong(balanceList.get(0).getBalanceusable());
        if (accFrozen < Long.parseLong(thawauthcodeRequest.getAmount())) {
            logger.error("冻结余额小于需解冻金额，冻结余额：{}，解冻金额：{}", accUseable, thawauthcodeRequest.getAmount());
            ResponseUtils.getSysErrorResp(thawauthcodeResponse);
            return thawauthcodeResponse;
        }
        balanceList.get(0).setBalancefrozon(String.valueOf(accFrozen - Long.parseLong(thawauthcodeRequest.getAmount())));
        balanceList.get(0).setBalanceusable(String.valueOf(accUseable + Long.parseLong(thawauthcodeRequest.getAmount())));
        int count = titanAccountDao.updateAccountBalance(balanceList.get(0));
        if (count < 1) {
            logger.error("账户余额更新异常");
            ResponseUtils.getSysErrorResp(thawauthcodeResponse);
            return thawauthcodeResponse;
        }
        thawauthcodeResponse.setIs_success("true");
        thawauthcodeResponse.setRetcode("100000");
        thawauthcodeResponse.setRetmsg("success");
        return thawauthcodeResponse;
    }

    private String getFreezeAuthCode() {
        List<Integer> asciiList = new ArrayList<Integer>();
        for (int i = 48; i < 58; i++) {//0-9
            asciiList.add(i);
        }
        for (int i = 65; i < 91; i++) {//A-Z
            asciiList.add(i);
        }
        for (int i = 97; i < 123; i++) {//a-z
            asciiList.add(i);
        }
        Collections.shuffle(asciiList);//乱序
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < 11; i++) {
            int index = (int) (Math.random() * 62);
            stringBuffer.append((char) asciiList.get(index).intValue());
        }
        logger.info("生成的冻结code为：{}", stringBuffer.toString());
        return stringBuffer.toString();
    }
}
