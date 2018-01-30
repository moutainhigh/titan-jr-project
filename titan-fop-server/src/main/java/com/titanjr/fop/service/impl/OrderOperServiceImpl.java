package com.titanjr.fop.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fangcang.exception.ServiceException;
import com.fangcang.titanjr.common.enums.ReqstatusEnum;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.dto.bean.*;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.entity.TitanOrderPayreq;
import com.fangcang.titanjr.entity.parameter.TitanAccountDetailParam;
import com.fangcang.titanjr.entity.parameter.TitanOrderPayreqParam;
import com.fangcang.titanjr.entity.parameter.TitanWithDrawReqParam;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.titanjr.service.TitanSysconfigService;
import com.fangcang.util.DateUtil;
import com.fangcang.util.StringUtil;
import com.titanjr.fop.constants.FuncCodeEnum;
import com.titanjr.fop.constants.InterfaceURlConfig;
import com.titanjr.fop.constants.OrderNStatusEnum;
import com.titanjr.fop.dao.TitanAccountDao;
import com.titanjr.fop.dao.TitanOrderDao;
import com.titanjr.fop.dto.BalanceQueryDTO;
import com.titanjr.fop.dto.TitanAccountDetailDTO;
import com.titanjr.fop.dto.Transorderinfo;
import com.titanjr.fop.request.WheatfieldOrderOperRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceReturngoodsRequest;
import com.titanjr.fop.request.WheatfieldOrderTransferRequest;
import com.titanjr.fop.request.WheatfieldOrdernQueryRequest;
import com.titanjr.fop.response.WheatfieldOrderTransferResponse;
import com.titanjr.fop.service.OrderOperService;
import com.titanjr.fop.util.ResponseUtils;
import com.titanjr.fop.util.WebUtils;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by zhaoshan on 2018/1/3.
 */
@Service("orderOperService")
public class OrderOperServiceImpl implements OrderOperService {

    private final static Logger logger = LoggerFactory.getLogger(OrderOperServiceImpl.class);

    @Resource
    private TitanOrderService titanOrderService;

    @Resource
    private TitanOrderDao titanOrderDao;

    @Resource
    private TitanAccountDao titanAccountDao;

    @Resource
    private TitanSysconfigService titanSysconfigService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public String operateOrder(WheatfieldOrderOperRequest orderOperRequest) throws ServiceException {
        TransOrderRequest transOrderRequest = new TransOrderRequest();
        transOrderRequest.setUserorderid(orderOperRequest.getUserorderid());
        transOrderRequest.setUserid(orderOperRequest.getUserid());
        List<TransOrderDTO> transOrderDTOs = titanOrderService.queryTransOrder(transOrderRequest);
        //新增
        if ("1".equals(orderOperRequest.getOpertype())) {
            if (CollectionUtils.isNotEmpty(transOrderDTOs) && StringUtil.isValidString(transOrderDTOs.get(0).getOrderid())) {
                logger.error("不可重复落单，userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            DecimalFormat formater = new DecimalFormat();
            formater.applyPattern("000000");
            String result = DateUtil.dateToString(new Date(), "yyyyMMddHHmmsssss") + formater.format((long) (Math.random() * 1000));
            return result;
        }
        //修改
        if ("2".equals(orderOperRequest.getOpertype())) {
            if (CollectionUtils.isEmpty(transOrderDTOs) || transOrderDTOs.size() > 1) {
                logger.error("修改时不存在对应的交易单或存在多条，userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            if (!"0".equals(transOrderDTOs.get(0).getStatusid())) {
                logger.error("非处理中的订单无法修改,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            if (transOrderDTOs.get(0).getAmount().equals(orderOperRequest.getAmount())) {
                logger.error("不能修改订单金额,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            if (!transOrderDTOs.get(0).getUserrelateid().equals(orderOperRequest.getUserrelateid())) {
                logger.error("不能修改交易对方,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            transOrderDTOs.get(0).setGoodsname(orderOperRequest.getGoodsname());
            transOrderDTOs.get(0).setGoodsdetail(orderOperRequest.getGoodsdetail());
            transOrderDTOs.get(0).setAdjustcontent(orderOperRequest.getAdjustcontent());
            transOrderDTOs.get(0).setRemark(orderOperRequest.getRemark());
            boolean result = titanOrderService.updateTransOrder(transOrderDTOs.get(0));
            if (!result) {
                logger.error("修改交易单失败,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            return transOrderDTOs.get(0).getOrderid();
        }
        //查询
        if ("3".equals(orderOperRequest.getOpertype())) {
            if (CollectionUtils.isEmpty(transOrderDTOs) || transOrderDTOs.size() > 1) {
                logger.error("查询交易单不存在或存在多条，userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            return transOrderDTOs.get(0).getOrderid();
        }
        //取消
        if ("4".equals(orderOperRequest.getOpertype())) {
            if (CollectionUtils.isEmpty(transOrderDTOs) || transOrderDTOs.size() > 1) {
                logger.error("取消时交易单不存在或存在多条，userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            if (!"0".equals(transOrderDTOs.get(0).getStatusid())) {
                logger.error("非处理中的订单无法操作取消,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            //设置为失效
            logger.info("将订单设置为失效,userorderid：{}", orderOperRequest.getUserorderid());
            transOrderDTOs.get(0).setStatusid("10");
            titanOrderService.updateTransOrder(transOrderDTOs.get(0));
            return transOrderDTOs.get(0).getOrderid();
        }
        return null;
    }


    @Override
    public String operateRefundOrder(WheatfieldOrderServiceReturngoodsRequest returngoodsRequest) throws ServiceException {
        TransOrderRequest transOrderRequest = new TransOrderRequest();
        transOrderRequest.setUserorderid(returngoodsRequest.getUserorderid());
        transOrderRequest.setOrderid(returngoodsRequest.getOrderid());
        List<TransOrderDTO> transOrderDTOs = titanOrderService.queryTransOrder(transOrderRequest);
        if (CollectionUtils.isEmpty(transOrderDTOs)) {
            logger.error("不存在交易单，单号:{}", returngoodsRequest.getOrderid());
            return null;
        }
        int refundFlag = 1;
        for (TransOrderDTO transOrderDTO : transOrderDTOs) {
            if ("6".equals(transOrderDTO.getStatusid()) || "8".equals(transOrderDTO.getStatusid())) {
                refundFlag++;
            }
        }
        //只存在一个成功的交易才进行下一步退款
        if (refundFlag != 2) {
            logger.error("需退款的交易单状态不正确，单号:{}", returngoodsRequest.getOrderid());
            return null;
        }
        if (!returngoodsRequest.getAmount().equals(String.valueOf(transOrderDTOs.get(0).getTradeamount()))) {
            logger.error("退款金额和原单金额不一致，单号:{}", returngoodsRequest.getOrderid());
            return null;
        }
        //验证又有的退款单信息
        RefundDTO refundDTO = new RefundDTO();
        refundDTO.setOrderNo(returngoodsRequest.getOrderid());
        List<RefundDTO> refundDTOList = titanOrderDao.queryRefundDTOList(refundDTO);
        if (CollectionUtils.isNotEmpty(refundDTOList)) {
            logger.error("已存在退款单，无法退款，单号：{}", returngoodsRequest.getOrderid());
            return null;
        }
        //生成并返回单号
        DecimalFormat formater = new DecimalFormat();
        formater.applyPattern("00");
        StringBuffer stringBuffer = new StringBuffer("OD");
        stringBuffer.append(DateUtil.dateToString(new Date(), "yyyyMMddHHmmsssss"));
        stringBuffer.append(formater.format((long) (Math.random() * 10)));
        return stringBuffer.toString();
    }

    @Override
    public List<Transorderinfo> queryTradeOrderNew(WheatfieldOrdernQueryRequest ordernQueryRequest) throws ServiceException {
        //这里能查出充值，转账，提现三种单的状态
        List<Transorderinfo> transorderinfoList = new ArrayList<Transorderinfo>();
        if (StringUtil.isValidString(ordernQueryRequest.getFunccode())) {
            //充值
            if (ordernQueryRequest.getFunccode().equals(FuncCodeEnum.RECHARGE_4015.getKey())) {
                transorderinfoList.addAll(queryRechargeInfo(ordernQueryRequest));
            }
            //转账
            if (ordernQueryRequest.getFunccode().equals(FuncCodeEnum.TRANSFER_3001.getKey())) {
                transorderinfoList.addAll(queryTransferInfo(ordernQueryRequest));
            }
            //提现
            if (ordernQueryRequest.getFunccode().equals(FuncCodeEnum.WITHDRAW_4016.getKey())) {
                transorderinfoList.addAll(queryWithDrawInfo(ordernQueryRequest));
            }
        } else {
            transorderinfoList.addAll(queryRechargeInfo(ordernQueryRequest));
            transorderinfoList.addAll(queryTransferInfo(ordernQueryRequest));
            transorderinfoList.addAll(queryWithDrawInfo(ordernQueryRequest));
        }

        Collections.sort(transorderinfoList, new TransorderinfoComparator());
        return transorderinfoList;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
    public WheatfieldOrderTransferResponse accountBalanceTransfer(WheatfieldOrderTransferRequest orderTransferRequest) throws ServiceException {

        WheatfieldOrderTransferResponse transferResponse = new WheatfieldOrderTransferResponse();
        //查询转账单是否存在
        //由于事务隔离级别问题，此接口查不到转账单，建议dubbo-server将转账单下单和操作转账功能分开；
//        TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
//        titanTransferDTO.setRequestno(orderTransferRequest.getRequestno());
//        List<TitanTransferDTO> transOrderDTOs = titanOrderService.getTitanTransferDTOList(titanTransferDTO);
//        if (CollectionUtils.isEmpty(transOrderDTOs)) {
//            logger.error("不存在转账单，当前请求号:{}", orderTransferRequest.getRequestno());
//            ResponseUtils.getSysErrorResp(transferResponse);
//            return transferResponse;
//        }
        //查询转出方/转入方账户是否存在 TODO 建议验证mainorg
        //验证转出方可用余额是否足够
        BalanceQueryDTO balanceQueryDTO = new BalanceQueryDTO();
        balanceQueryDTO.setUserId(orderTransferRequest.getUserid());
        List<AccountBalance> payerAccounts = titanAccountDao.queryAccountBalanceList(balanceQueryDTO);
        AccountBalance payerAccount = null;
        AccountBalance payeeAccount = null;
        if (CollectionUtils.isNotEmpty(payerAccounts)) {//
            for (AccountBalance accountBalance : payerAccounts) {
                if (orderTransferRequest.getProductid().equals(accountBalance.getProductid())) {
                    payerAccount = accountBalance;
                    break;
                }
            }
        }
        balanceQueryDTO.setUserId(orderTransferRequest.getUserrelateid());
        List<AccountBalance> payeeAccounts = titanAccountDao.queryAccountBalanceList(balanceQueryDTO);
        if (CollectionUtils.isNotEmpty(payeeAccounts)) {//
            for (AccountBalance accountBalance : payeeAccounts) {
                if (orderTransferRequest.getInterproductid().equals(accountBalance.getProductid())) {
                    payeeAccount = accountBalance;
                    break;
                }
            }
        }
        //修改两个账户余额
        if (payerAccount != null && payeeAccount != null) {
            Long amount = Long.parseLong(orderTransferRequest.getAmount());
            Long payerUseable = Long.parseLong(payerAccount.getBalanceusable());
            if (amount > payerUseable ){
                logger.error("转账失败，余额不足，可用余额：{}，转账金额：{}",
                        payerUseable, amount);
                ResponseUtils.getSysErrorResp(transferResponse);
                return transferResponse;
            }
            Long payerAmount = Long.parseLong(payerAccount.getAmount());
            Long payerSettle = Long.parseLong(payerAccount.getBalancesettle());
            payerAccount.setBalanceusable(String.valueOf(payerUseable - amount));
            payerAccount.setAmount(String.valueOf(payerAmount - amount));
            payerAccount.setBalancesettle(String.valueOf(payerSettle - amount));
            int payerCount = titanAccountDao.updateAccountBalance(payerAccount);

            Long payeeUseable = Long.parseLong(payeeAccount.getBalanceusable());
            Long payeeAmount = Long.parseLong(payeeAccount.getAmount());
            Long payeeSettle = Long.parseLong(payeeAccount.getBalancesettle());
            payeeAccount.setBalanceusable(String.valueOf(payeeUseable + amount));
            payeeAccount.setAmount(String.valueOf(payeeAmount + amount));
            payeeAccount.setBalancesettle(String.valueOf(payeeSettle + amount));
            int payeeCount = titanAccountDao.updateAccountBalance(payeeAccount);

            if (payerCount > 0 && payeeCount > 0) {
                transferResponse.setIs_success("true");
                transferResponse.setOrderid(orderTransferRequest.getRequestno());
            } else {
                logger.error("转账操作失败，当前付款方：{}，收款方：{}",
                        JSONObject.toJSON(payerAccount), JSONObject.toJSON(payeeAccount));
                ResponseUtils.getSysErrorResp(transferResponse);
                return transferResponse;
            }

        } else {
            logger.error("收付款方账户异常，付款方:{}，收款方：{}", payerAccount, payeeAccount);
            ResponseUtils.getSysErrorResp(transferResponse);
            return transferResponse;
        }
        //记录日志
        return transferResponse;
    }

    //针对充值去上游查询交易单状态,验证本地交易状态
    private List<Transorderinfo> queryRechargeInfo(WheatfieldOrdernQueryRequest ordernQueryRequest) {
        List<Transorderinfo> orderInfoList = new ArrayList<Transorderinfo>();
        TitanOrderPayreqParam requestParam = new TitanOrderPayreqParam();
        //merchantcode,funccode这两个暂不处理，status从查出的结果中筛选
        if (null != ordernQueryRequest.getStarttime()) {
            requestParam.setStartTime(DateUtil.dateToString(ordernQueryRequest.getStarttime(), "yyyy-MM-dd HH:mm:ss"));
        }
        if (null != ordernQueryRequest.getEndtime()) {
            requestParam.setEndTime(DateUtil.dateToString(ordernQueryRequest.getEndtime(), "yyyy-MM-dd HH:mm:ss"));
        }
        if (StringUtil.isValidString(ordernQueryRequest.getOrderno())) {
            requestParam.setOrderNo(ordernQueryRequest.getOrderno());
        }
        if (null != ordernQueryRequest.getAmount()) {
            requestParam.setOrderAmount((double) ordernQueryRequest.getAmount().longValue());
        }
        //充值不需要设置此字段
        if (StringUtil.isValidString(ordernQueryRequest.getIntermerchantcode())) {
        }
        if (StringUtil.isValidString(ordernQueryRequest.getUserid())) {
            requestParam.setMerchantNo(ordernQueryRequest.getUserid());
        }

        List<TitanOrderPayreq> orderPayreqList = titanOrderService.queryOrderPayRequestList(requestParam);

        //获取网关地址
        String paymentURL = InterfaceURlConfig.checkstand_GateWayURL;
        for (TitanOrderPayreq payreq : orderPayreqList) {
            Transorderinfo transorderinfo = new Transorderinfo();
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("busiCode", "102");
            paramMap.put("signType", "1");
            paramMap.put("version", payreq.getVersion());
            paramMap.put("merchantNo", payreq.getMerchantNo());
            paramMap.put("orderNo", payreq.getOrderNo());
            paramMap.put("orderTime", payreq.getOrderTime());
            paramMap.put("signMsg", getPayQuerySign(paramMap));
            try {
                //查询网关真实状态
                String rechargeResult = WebUtils.doPost(paymentURL, paramMap, 60000, 60000);
                Map resultMap = (Map) JSONObject.parse(rechargeResult);
                //本地成功但是上游网关出不来结果,需报错通知
                if (resultMap.isEmpty() && payreq.getReqstatus().equals(ReqstatusEnum.RECHARFE_SUCCESS.getStatus())) {
                    logger.error("金融上游交易状态冲突，单号：{}", payreq.getOrderNo());
                    continue;
                }
                if (StringUtil.isValidString((String)resultMap.get("errCode"))) {
                    logger.error("查询上游交易状态错误，errMsg：{}，单号：{}", resultMap.get("errMsg"), payreq.getOrderNo());
                    return orderInfoList;
                }
                
                /*//查询本地记账状态
                TitanAccountDetailParam param = new TitanAccountDetailParam();
                param.setTransOrderId(Long.valueOf(payreq.getTransorderid()));
                List<TitanAccountDetailDTO> detailDTOList = titanOrderDao.selectAccountDetail(param);

                //原则上有且只有一条记账记录，需报错通知
                if (CollectionUtils.isEmpty(detailDTOList) || detailDTOList.size() != 1) {
                    logger.error("本地记账记录不存在，单号：{}", payreq.getOrderNo());
                    continue;
                }
                //金额不一致，需报错通知
                if (String.valueOf(detailDTOList.get(0).getSettleAmount()).equals(payreq.getOrderAmount())) {
                    logger.error("记账记录校验失败，单号：{}", payreq.getOrderNo());
                    continue;
                }*/

                if ("3".equals(resultMap.get("payStatsu"))) {
                    transorderinfo.setOrderno(payreq.getOrderNo());//单号
                    transorderinfo.setOrderstatus(OrderNStatusEnum.NORMAL_1.getKey());//正常
                    transorderinfo.setAmount(resultMap.get("payAmount").toString());//金额
                    transorderinfo.setFunccode(FuncCodeEnum.RECHARGE_4015.getKey());//充值
                    transorderinfo.setRequestno("");//请求号不存在,无法设置
                    transorderinfo.setOrderpackageno("");//设置userOrderNo，需关联查询
                    transorderinfo.setCreatedtime(resultMap.get("orderPayTime").toString());//设置支付时间
                    transorderinfo.setUpdatedtime(resultMap.get("orderPayTime").toString());//设置支付时间
                    transorderinfo.setErrorcode("");//不设置
                    transorderinfo.setErrormsg("");//不设置
                    transorderinfo.setMerchantcode("M1000016");
                    transorderinfo.setTranssumid("");//不设置
                    transorderinfo.setIntermerchantcode("order");//交易对方不设置
                    transorderinfo.setUserid(payreq.getMerchantNo());
                    orderInfoList.add(transorderinfo);
                }
            } catch (IOException e) {
                logger.error("当前订单获取最新状态失败", e);
            }

        }
        return orderInfoList;
    }

    //针对转账，查询本地转账记录和记账记录
    private Collection<? extends Transorderinfo> queryTransferInfo(WheatfieldOrdernQueryRequest ordernQueryRequest) {
        List<Transorderinfo> orderInfoList = new ArrayList<Transorderinfo>();
        TitanTransferDTO titanTransferDTO = new TitanTransferDTO();
        //merchantcode,funccode这两个暂不处理，status从查出的结果中筛选
        if (null != ordernQueryRequest.getStarttime()) {
            titanTransferDTO.setStartTime(ordernQueryRequest.getStarttime());
        }
        if (null != ordernQueryRequest.getEndtime()) {
            titanTransferDTO.setEndTime(ordernQueryRequest.getEndtime());
        }
        //原融数接口中不接受此参数，我们将此参数作为请求号设置进去
        if (StringUtil.isValidString(ordernQueryRequest.getOrderno())) {
            titanTransferDTO.setRequestno(ordernQueryRequest.getOrderno());
        }
        if (null != ordernQueryRequest.getAmount()) {
            titanTransferDTO.setAmount((double) ordernQueryRequest.getAmount().longValue());
        }
        //转账的收款方
        if (StringUtil.isValidString(ordernQueryRequest.getIntermerchantcode())) {
            titanTransferDTO.setUserrelateid(ordernQueryRequest.getIntermerchantcode());
        }
        if (StringUtil.isValidString(ordernQueryRequest.getUserid())) {
            titanTransferDTO.setUserid(ordernQueryRequest.getUserid());
        }
        List<TitanTransferDTO> transferDTOList = titanOrderService.getTitanTransferDTOList(titanTransferDTO);
        for (TitanTransferDTO transferDTO : transferDTOList) {

            //查询本地记账状态
            TitanAccountDetailParam param = new TitanAccountDetailParam();
            param.setTransOrderId(Long.valueOf(transferDTO.getTransorderid()));
            List<TitanAccountDetailDTO> detailDTOList = titanOrderDao.selectAccountDetail(param);

            //原则上有且只有一条记账记录，需报错通知
            if (CollectionUtils.isEmpty(detailDTOList) || detailDTOList.size() != 2) {
                logger.error("本地记账记录不存在，单号：{}", transferDTO.getRequestno());
                continue;
            }
            //金额不一致，需报错通知
            if (Double.valueOf(detailDTOList.get(0).getSettleAmount()).equals(transferDTO.getAmount())) {
                logger.error("记账记录校验失败，单号：{}", transferDTO.getRequestno());
                continue;
            }

            Transorderinfo transorderinfo = new Transorderinfo();
            transorderinfo.setOrderno("");//单号,OP开头没法设置
            transorderinfo.setOrderstatus(OrderNStatusEnum.NORMAL_1.getKey());//正常
            transorderinfo.setAmount(String.valueOf(transferDTO.getAmount()));//金额
            transorderinfo.setFunccode(FuncCodeEnum.TRANSFER_3001.getKey());//转账
            transorderinfo.setRequestno("");//请求号不存在,无法设置
            transorderinfo.setOrderpackageno("");//设置userOrderNo，需关联查询
            transorderinfo.setCreatedtime(DateUtil.dateToString(transferDTO.getCreatetime(),"yyyy-MM-dd HH:mm:ss"));//设置创建时间
            transorderinfo.setUpdatedtime(DateUtil.dateToString(transferDTO.getCreatetime(),"yyyy-MM-dd HH:mm:ss"));//设置创建时间
            transorderinfo.setErrorcode("");//不设置
            transorderinfo.setErrormsg("");//不设置
            transorderinfo.setMerchantcode("M1000016");
            transorderinfo.setTranssumid("");//不设置
            transorderinfo.setIntermerchantcode(transferDTO.getUserrelateid());//交易对方不设置
            transorderinfo.setUserid(transferDTO.getUserid());
            orderInfoList.add(transorderinfo);
        }
        return orderInfoList;
    }

    //针对提现，查询上游代付状态,验证本地提现单状态
    private Collection<? extends Transorderinfo> queryWithDrawInfo(WheatfieldOrdernQueryRequest ordernQueryRequest) {
        List<Transorderinfo> orderInfoList = new ArrayList<Transorderinfo>();
        TitanWithDrawReqParam withDrawReqParam = new TitanWithDrawReqParam();
        //merchantcode,funccode这两个暂不处理，status从查出的结果中筛选
        if (null != ordernQueryRequest.getStarttime()) {
            withDrawReqParam.setStartTime(ordernQueryRequest.getStarttime());
        }
        if (null != ordernQueryRequest.getEndtime()) {
            withDrawReqParam.setEndTime(ordernQueryRequest.getEndtime());
        }
        //原提现单是上游生成的OP开头的单，我们这边不传
        if (StringUtil.isValidString(ordernQueryRequest.getOrderno())) {

        }
        if (null != ordernQueryRequest.getAmount()) {
            withDrawReqParam.setAmount(ordernQueryRequest.getAmount().longValue());
        }
        //提现不需要设置此字段
        if (StringUtil.isValidString(ordernQueryRequest.getIntermerchantcode())) {

        }
        if (StringUtil.isValidString(ordernQueryRequest.getUserid())) {
            withDrawReqParam.setUserid(ordernQueryRequest.getUserid());
        }
        List<TitanWithDrawDTO> withDrawDTOList = titanOrderService.queryWithDrawDTOList(withDrawReqParam);
        String paymentURL = InterfaceURlConfig.checkstand_GateWayURL;//查询
        for (TitanWithDrawDTO withDrawDTO : withDrawDTOList){
            Transorderinfo transorderinfo = new Transorderinfo();
            Map<String, String> paramMap = new HashMap<String, String>();
            paramMap.put("merchantNo", "M1000016");
            paramMap.put("orderNo", withDrawDTO.getUserorderid());//TODO 需要根据提现查出代付交易
            paramMap.put("tradeCode", "1");//
            paramMap.put("tradeStatus", "2");//所有状态
            paramMap.put("queryType", "1");//表示代收还是代付
            paramMap.put("startDate", null);
            paramMap.put("endDate", null);
            try {
                //查询网关真实提现状态
                String withDrawResult = WebUtils.doPost(paymentURL, paramMap, 60000, 60000);
                Map resultMap = (Map) JSONObject.parse(withDrawResult);
                if (null == resultMap.get("details")){
                    logger.error("网关返回结果异常");
                    continue;
                }
                if (CollectionUtils.isEmpty((JSONArray)resultMap.get("details"))){
                    logger.error("查询到的详情为空");
                    continue;
                }
                JSONObject resDetailDTO = (JSONObject)((JSONArray)resultMap.get("details")).get(0);
                if (null == resDetailDTO || !StringUtil.isValidString(resDetailDTO.get("tradeStatus").toString())){
                    logger.error("交易状态不正确");
                    continue;
                }

                if ("3".equals(resDetailDTO.get("tradeStatus"))){
                    transorderinfo.setOrderstatus(OrderNStatusEnum.PAY_SUCC_4.getKey());//成功
                }
                if ("2".equals(resDetailDTO.get("tradeStatus"))){
                    transorderinfo.setOrderstatus(OrderNStatusEnum.PAY_FAIL_5.getKey());//失败
                    //针对失败，起任务平账，平账后状态变成CORRECT_6，交易已冲正；

                }
                if ("1".equals(resDetailDTO.get("tradeStatus"))){
                    transorderinfo.setOrderstatus(OrderNStatusEnum.SENDING_7.getKey());//处理中
                }
                transorderinfo.setOrderno("");//单号,OP开头没法设置
                transorderinfo.setAmount(resultMap.get("payAmount").toString());//金额
                transorderinfo.setFunccode(FuncCodeEnum.WITHDRAW_4016.getKey());//提现
                transorderinfo.setRequestno("");//请求号不存在,无法设置
                transorderinfo.setOrderpackageno("");//设置userOrderNo，需关联查询
                transorderinfo.setCreatedtime(resultMap.get("orderPayTime").toString());//设置支付时间
                transorderinfo.setUpdatedtime(resultMap.get("orderPayTime").toString());//设置支付时间
                transorderinfo.setErrorcode("");//不设置
                transorderinfo.setErrormsg("");//不设置
                transorderinfo.setMerchantcode("M1000016");
                transorderinfo.setTranssumid("");//不设置
                transorderinfo.setIntermerchantcode("");//不返回
                transorderinfo.setUserid(withDrawDTO.getUserid());
                orderInfoList.add(transorderinfo);
            } catch (IOException e) {
                logger.error("当前订单获取最新状态失败", e);
            }
        }
        return orderInfoList;
    }

    //获取支付查询的签名
    private String getPayQuerySign(Map paramMap) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("signType=").append(paramMap.get("signType"));
        stringBuffer.append("&version=").append(paramMap.get("version"));
        stringBuffer.append("&merchantNo=").append(paramMap.get("merchantNo"));
        stringBuffer.append("&orderNo=").append(paramMap.get("orderNo"));
        stringBuffer.append("&orderTime=").append(paramMap.get("orderTime"));
        stringBuffer.append("&key=").append("PCDEFOI8808TFC");
        return MD5.MD5Encode(stringBuffer.toString(), "UTF-8");
    }

    private static String getRefundQuerySign(Map paramMap) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("signType=").append(paramMap.get("signType"));
        stringBuffer.append("&version=").append(paramMap.get("version"));
        stringBuffer.append("&merchantNo=").append(paramMap.get("merchantNo"));
        stringBuffer.append("&refundOrderno=").append(paramMap.get("refundOrderno"));
        stringBuffer.append("&orderNo=").append(paramMap.get("orderNo"));
        stringBuffer.append("&orderTime=").append(paramMap.get("orderTime"));
        stringBuffer.append("&refundAmount=").append(paramMap.get("refundAmount"));
        stringBuffer.append("&key=PCDEFOI8808TFC");
        return MD5.MD5Encode(stringBuffer.toString(), "UTF-8");
    }

    class TransorderinfoComparator implements Comparator<Transorderinfo> {

        @Override
        public int compare(Transorderinfo first, Transorderinfo second) {
            Date firstTime = DateUtil.stringToDate(first.getCreatedtime(), "yyyy-MM-dd HH:mm:ss");
            Date secondTime = DateUtil.stringToDate(second.getCreatedtime(), "yyyy-MM-dd HH:mm:ss");
            if (DateUtil.compare(firstTime, secondTime) > 1)
                return -1;
            else if (DateUtil.compare(firstTime, secondTime) > 1)
                return 1;
            else {
                return 0;
            }
        }

    }

    public static void main(String[] args) {
        String jsonResp = "{\"details\":[{\"accountName\":\"AC545\",\"orderNo\":\"132456\"}]," +
                "\"orderNo\":\"OP002458700\",\"retCode\":\"001\",\"signMsg\":\"signMsg\"," +
                "\"tradeCode\":\"CT1548782\"}";
        Map resultMap = (Map) JSONObject.parse(jsonResp);
        System.out.println(resultMap);
    }


}
