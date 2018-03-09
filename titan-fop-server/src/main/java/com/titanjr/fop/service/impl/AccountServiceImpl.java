package com.titanjr.fop.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.fangcang.exception.DaoException;
import com.fangcang.exception.ServiceException;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.SMSTemplate;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.BankCardDTO;
import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.BankCardRequest;
import com.fangcang.titanjr.dto.request.RecordRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.entity.TitanFundUnFreezereq;
import com.fangcang.titanjr.entity.parameter.TitanUnFundFreezereqParam;
import com.fangcang.titanjr.service.AccountRecordService;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;
import com.titanjr.fop.constants.BankCardMapper;
import com.titanjr.fop.constants.InterfaceURlConfig;
import com.titanjr.fop.dao.TitanAccountDao;
import com.titanjr.fop.dto.BalanceQueryDTO;
import com.titanjr.fop.dto.SHBalanceInfo;
import com.titanjr.fop.request.WheatfieldBalanceGetlistRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceAuthcodeserviceRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceThawauthcodeRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceWithdrawserviceRequest;
import com.titanjr.fop.response.WheatfieldOrderServiceThawauthcodeResponse;
import com.titanjr.fop.response.WheatfieldOrderServiceWithdrawserviceResponse;
import com.titanjr.fop.service.AccountService;
import com.titanjr.fop.service.CommonService;
import com.titanjr.fop.util.ResponseUtils;
import com.titanjr.fop.util.WebUtils;

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

    @Autowired
    TitanFinancialBankCardService titanFinancialBankCardService;

    @Autowired
    CommonService commonService;
    
    @Autowired
    AccountRecordService accountRecordService;
    
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
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public String freezeAccountBalance(WheatfieldOrderServiceAuthcodeserviceRequest authcodeserviceRequest) throws ServiceException {
        //查询冻结单当前是否存在以及状态
        FundFreezeDTO fundFreezeDTO = new FundFreezeDTO();
        fundFreezeDTO.setOrderNo(authcodeserviceRequest.getOrderno());
        List<FundFreezeDTO> freezeDTOList = titanOrderService.queryFundFreezeDTO(fundFreezeDTO);
        if (CollectionUtils.isNotEmpty(freezeDTOList)) {
            logger.error("当前交易单已存在冻结请求：{}", authcodeserviceRequest.getOrderno());
            return null;
        }
        //查询校验交易单是否存在，暂不校验交易单金额
        TransOrderRequest transOrderRequest = new TransOrderRequest();
        transOrderRequest.setOrderid(authcodeserviceRequest.getOrderno());
        List<TransOrderDTO> transOrderDTOList = titanOrderService.queryTransOrder(transOrderRequest);
        if (CollectionUtils.isEmpty(transOrderDTOList)) {
            logger.error("传入的交易冻结单号不存在，请验证后重试，单号为：{}", authcodeserviceRequest.getOrderno());
            return null;
        }
        //查询校验账户余额
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
        if (accUseable < authcodeserviceRequest.getAmount()) {
            logger.error("账户余额小于需冻结金额，账户余额：{}，冻结金额：{}", accUseable, authcodeserviceRequest.getAmount());
            return null;
        }
        
        RecordRequest recordRequest = new RecordRequest();
        recordRequest.setUserOrderId(authcodeserviceRequest.getRequestno());
        recordRequest.setAmount(authcodeserviceRequest.getAmount());
        recordRequest.setUserId(authcodeserviceRequest.getUserid());
        recordRequest.setProductId(authcodeserviceRequest.getProductid());
        BaseResponseDTO response = new BaseResponseDTO();
		try {
			response = accountRecordService.freeze(recordRequest);
		} catch (GlobalServiceException e) {
			logger.error("冻结记账异常,记账参数recordRequest："+Tools.gsonToString(recordRequest),e);
			response.putErrorResult("冻结失败");
		}
        if (!response.isResult()) {
        	 logger.error("冻结记账失败,冻结参数authcodeserviceRequest："+Tools.gsonToString(authcodeserviceRequest));
             return null;
        }
        //返回生成的验证码
        return getFreezeAuthCode();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public WheatfieldOrderServiceThawauthcodeResponse unFreezeAccountBalance(WheatfieldOrderServiceThawauthcodeRequest thawauthcodeRequest) throws ServiceException {
        WheatfieldOrderServiceThawauthcodeResponse thawauthcodeResponse = new WheatfieldOrderServiceThawauthcodeResponse();
        //查询冻结记录
        FundFreezeDTO fundFreezeDTO = new FundFreezeDTO();
        fundFreezeDTO.setAuthCode(thawauthcodeRequest.getAuthcode());
        fundFreezeDTO.setRequestNo(thawauthcodeRequest.getFrozenuserorderid());
        List<FundFreezeDTO> freezeDTOList = titanOrderService.queryFundFreezeDTO(fundFreezeDTO);
        //无正确的冻结单
        if (CollectionUtils.isEmpty(freezeDTOList) || freezeDTOList.size() > 1) {
        	logger.error("解冻时，该冻结记录不存在或者错误，冻结记录查询参数【授权码】:"+thawauthcodeRequest.getAuthcode()+",请求号："+thawauthcodeRequest.getRequestno()+",查询结果："+Tools.gsonToString(freezeDTOList));
        	thawauthcodeResponse.setMsg("该冻结记录不存在或者错误");
            ResponseUtils.getErrorResp(thawauthcodeResponse);
        }
        //查询解冻记录，若存在则有问题
        TitanUnFundFreezereqParam unFundFreezereqParam = new TitanUnFundFreezereqParam();
        unFundFreezereqParam.setRequestno(thawauthcodeRequest.getFrozenuserorderid());
        List<TitanFundUnFreezereq> unFreezereqList = titanAccountDao.queryUnFreezeRequest(unFundFreezereqParam);
        //已解冻或存在解冻单
        if (freezeDTOList.get(0).getStatus() == 2 || CollectionUtils.isNotEmpty(unFreezereqList)) {
        	logger.error("该冻结记录已经解冻，冻结查询参数："+Tools.gsonToString(thawauthcodeRequest));
        	thawauthcodeResponse.setMsg("该冻结记录已经解冻");
            ResponseUtils.getErrorResp(thawauthcodeResponse);
            return thawauthcodeResponse;
        }
        //解冻金额校验失败
        if (!thawauthcodeRequest.getAmount().equals(freezeDTOList.get(0).getAmount())) {
            logger.error("解冻金额校验失败，冻结金额：{}，解冻金额：{}", freezeDTOList.get(0).getAmount(), thawauthcodeRequest.getAmount());
            ResponseUtils.getSysErrorResp(thawauthcodeResponse);
            return thawauthcodeResponse;
        }
        //查询并校验账户冻结余额
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
        //更新账户解冻资金
        RecordRequest recordRequest = new RecordRequest();
        recordRequest.setUserOrderId(thawauthcodeRequest.getRequestno());
        recordRequest.setAmount(Long.valueOf(thawauthcodeRequest.getAmount()));
        recordRequest.setUserId(thawauthcodeRequest.getUserid());
        recordRequest.setProductId(thawauthcodeRequest.getProductid());
        BaseResponseDTO response = new BaseResponseDTO();
		try {
			response = accountRecordService.unFreeze(recordRequest);
			if (!response.isResult()) {
	        	 logger.error("解冻记账失败,解动参数thawauthcodeRequest："+Tools.gsonToString(thawauthcodeRequest));
	        	 thawauthcodeResponse.setMsg("解冻失败");
	        	 ResponseUtils.getErrorResp(thawauthcodeResponse);
	             return thawauthcodeResponse;
	        }
		} catch (GlobalServiceException e) {
			logger.error("解冻记账异常,记账参数recordRequest："+Tools.gsonToString(recordRequest),e);
			thawauthcodeResponse.setMsg("解冻异常");
       	 	ResponseUtils.getErrorResp(thawauthcodeResponse);
            return thawauthcodeResponse;
		}
        
        thawauthcodeResponse.setIs_success("true");
        thawauthcodeResponse.setRetcode("100000");
        thawauthcodeResponse.setRetmsg("success");
        return thawauthcodeResponse;
    }

    @Override
    public WheatfieldOrderServiceWithdrawserviceResponse accountBalanceWithDraw(WheatfieldOrderServiceWithdrawserviceRequest withdrawserviceRequest) throws ServiceException {
        WheatfieldOrderServiceWithdrawserviceResponse withdrawserviceResponse = new WheatfieldOrderServiceWithdrawserviceResponse();
        //判定账户存在性，金额和提现金额
        BalanceQueryDTO balanceQueryDTO = new BalanceQueryDTO();
        balanceQueryDTO.setUserId(withdrawserviceRequest.getUserid());
        balanceQueryDTO.setProductId(withdrawserviceRequest.getProductid());
        List<AccountBalance> balanceList = titanAccountDao.queryAccountBalanceList(balanceQueryDTO);
        if (CollectionUtils.isEmpty(balanceList) || balanceList.size() > 1) {
            logger.error("账户余额查询结果异常：{}", balanceList);
            ResponseUtils.getSysErrorResp(withdrawserviceResponse);
            return withdrawserviceResponse;
        }
        Long witDrawAmount = Long.parseLong(withdrawserviceRequest.getAmount());
        Long balanceSettle = Long.parseLong(balanceList.get(0).getBalancesettle());
        if (witDrawAmount > balanceSettle) {
            logger.error("账户可提现金额不足，可提现金额：{}，可用余额：{}",
                    balanceList.get(0).getBalancesettle(), withdrawserviceRequest.getAmount());
            ResponseUtils.getSysErrorResp(withdrawserviceResponse);
            return withdrawserviceResponse;
        }
        //校验提现单是否存在，由于事务一致性问题；
        // 提现落单和提现操作在同一个事务，应无法查到提现单；跟转账问题类似
//        TitanWithDrawReqParam withDrawReqParam = new TitanWithDrawReqParam();
//        withDrawReqParam.setUserorderid(withdrawserviceRequest.getUserorderid());
//        List<TitanWithDrawDTO> withDrawDTOList = titanOrderService.queryWithDrawDTOList(withDrawReqParam);
//        if (CollectionUtils.isEmpty(withDrawDTOList) || withDrawDTOList.size() > 1) {
//            logger.error("查询本地提现单异常：{}", withDrawDTOList);
//            ResponseUtils.getSysErrorResp(withdrawserviceResponse);
//            return withdrawserviceResponse;
//        }
        //查询绑卡信息 1：结算卡，2：其他卡, 3：提现卡, 4：结算提现一体卡)
        BankCardRequest bankCardRequest = new BankCardRequest();
        bankCardRequest.setUserId(withdrawserviceRequest.getUserid());
        List<BankCardDTO> bankCardDTOList = titanFinancialBankCardService.queryBankCardDTO(bankCardRequest);
        BankCardDTO cardDTO = null;
        for (BankCardDTO bankCardDTO : bankCardDTOList) {
            if (StringUtil.isValidString(withdrawserviceRequest.getCardno()) &&//传了卡号一定需要有相同卡
                    !bankCardDTO.getAccountnumber().equals(withdrawserviceRequest.getCardno())) {
                cardDTO = bankCardDTO;
                continue;
            }
            if (bankCardDTO.getAccountpurpose().equals("3") || bankCardDTO.getAccountpurpose().equals("1")
                    || bankCardDTO.getAccountpurpose().equals("4")) {
                cardDTO = bankCardDTO;
                break;
            }
        }
        if (cardDTO == null) {
            logger.error("本地绑卡信息异常，机构号：{}", withdrawserviceRequest.getUserid());
            ResponseUtils.getSysErrorResp(withdrawserviceResponse);
            return withdrawserviceResponse;
        }

        //获取网关地址
        String paymentURL = InterfaceURlConfig.checkstand_GateWayURL;
//        paymentURL = "http://192.168.0.14:8090/checkstand/payment.shtml";
        //发起代付 返回状态
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("merchantNo", "M000016");
        paramMap.put("orderNo", withdrawserviceRequest.getUserorderid());//客户单号
        paramMap.put("tradeCode", "100014");
        //paramMap.put("submitTime", DateUtil.dateToString(new Date(), "yyyyMMddHHmmss"));//在checkstand处理，通联和融宝要求格式不同
        paramMap.put("bankInfo", BankCardMapper.getBankCardByCode(cardDTO.getBankcode()).getBankInfo());
        paramMap.put("busiCode", "105");
        paramMap.put("accountType", "00");//默认银行卡
        paramMap.put("accountNo", cardDTO.getAccountnumber());
        paramMap.put("accountName", cardDTO.getAccountname());
        if (cardDTO.getAccountproperty().equals("1")) {//对公
            paramMap.put("accountProperty", "1");
        }
        if (cardDTO.getAccountproperty().equals("2")) {//对私
            paramMap.put("accountProperty", "0");
        }
        paramMap.put("tradeAmount", withdrawserviceRequest.getAmount());
        paramMap.put("currency", "CNY");
        paramMap.put("idCode", cardDTO.getCertificatenumnumber());
        paramMap.put("idType", null);//设法区分身份证和回乡证getCertificatetype 不一定准
        try {
            //查询网关真实状态
            String rechargeResult = WebUtils.doPost(paymentURL, paramMap, 60000, 60000);
            Map resultMap = (Map) JSONObject.parse(rechargeResult);
            //1:"处理中",2:"失败",3:"成功"
            if(resultMap.get("errCode") != null || resultMap.get("status") == null){
            	ResponseUtils.getSysErrorResp(withdrawserviceResponse);
                return withdrawserviceResponse;
            }
            withdrawserviceResponse.setStatus(resultMap.get("status").toString());
            if (resultMap.get("status").equals("3") || resultMap.get("status").equals("1")) {
                withdrawserviceResponse.setIs_success("true");
                //需要改账户余额
                Long withDrawAmount = Long.parseLong(withdrawserviceRequest.getAmount());
                Long accSettle = Long.parseLong(balanceList.get(0).getBalancesettle());
                Long usableAmount = Long.parseLong(balanceList.get(0).getBalanceusable());
                Long amount = Long.parseLong(balanceList.get(0).getAmount());
                balanceList.get(0).setAmount(String.valueOf(amount - withDrawAmount));
                balanceList.get(0).setBalanceusable(String.valueOf(usableAmount - withDrawAmount));
                balanceList.get(0).setBalancesettle(String.valueOf(accSettle - withDrawAmount));
                int count = titanAccountDao.updateAccountBalance(balanceList.get(0));
                if (count < 1) {
                    //这种情况应需立即处理；
                    withdrawserviceResponse.setStatus("4");
                    commonService.sendSMSMessage(SMSTemplate.WITHDRAW_UPDATE_FAIL, balanceList);
                }
            }
        } catch (DaoException e) {//这种情况应需立即处理；
            logger.error("更新账户余额异常", e);
            ResponseUtils.getSysErrorResp(withdrawserviceResponse);
            commonService.sendSMSMessage(SMSTemplate.WITHDRAW_UPDATE_FAIL, e);
            return withdrawserviceResponse;
        } catch (Exception e) {
            logger.error("上游发起代付提现失败", e);
            ResponseUtils.getSysErrorResp(withdrawserviceResponse);
            commonService.sendSMSMessage(SMSTemplate.WITHDRAW_UPDATE_FAIL, e);
            return withdrawserviceResponse;
        }
        return withdrawserviceResponse;
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
