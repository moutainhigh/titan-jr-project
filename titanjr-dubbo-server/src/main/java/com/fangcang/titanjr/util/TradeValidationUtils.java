package com.fangcang.titanjr.util;

import java.util.*;

import com.Rop.api.ApiException;
import com.Rop.api.DefaultRopClient;
import com.Rop.api.domain.Transorderinfo;
import com.Rop.api.request.WheatfieldOrderServiceReturngoodsRequest;
import com.Rop.api.request.WheatfieldOrdernQueryRequest;
import com.Rop.api.response.WheatfieldOrderServiceReturngoodsResponse;
import com.Rop.api.response.WheatfieldOrdernQueryResponse;
import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.RSConvertFiled2ObjectUtil;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.dao.TitanOrderPayreqDao;
import com.fangcang.titanjr.dao.TitanRefundDao;
import com.fangcang.titanjr.dao.TitanTransferReqDao;
import com.fangcang.titanjr.dao.TitanWithDrawReqDao;
import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.dto.request.NotifyRefundRequest;
import com.fangcang.titanjr.dto.request.RefundOrderRequest;
import com.fangcang.titanjr.dto.response.NotifyRefundResponse;
import com.fangcang.titanjr.dto.response.RefundOrderResponse;
import com.fangcang.titanjr.entity.TitanOrderPayreq;
import com.fangcang.titanjr.entity.TitanRefund;
import com.fangcang.titanjr.entity.TitanTransferReq;
import com.fangcang.titanjr.entity.TitanWithDrawReq;
import com.fangcang.titanjr.entity.parameter.TitanOrderPayreqParam;
import com.fangcang.titanjr.entity.parameter.TitanTransferReqParam;
import com.fangcang.titanjr.entity.parameter.TitanWithDrawReqParam;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.enums.SignTypeEnum;
import com.fangcang.titanjr.rs.request.RSRefundRequest;
import com.fangcang.titanjr.rs.response.RsRefundResponse;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.DateUtil;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("tradeValidationUtils")
public class TradeValidationUtils {

    private static final Log log = LogFactory.getLog(TradeValidationUtils.class);

    private static String ropUrl = "https://api.open.ruixuesoft.com:30005/ropapi";
    private static String appKey = "93A6626A-C082-4D25-B496-EA9CC6E90EDB";
    private static String appSecret = "DC368712-18A4-4290-9A58-FF995DC161DC";
    private static String session = "1478056836773639888";


    private static DefaultRopClient ropClient = new DefaultRopClient(ropUrl, appKey, appSecret, "json");


    @Resource
    private TitanOrderService titanOrderService;

    @Resource
    private TitanOrderPayreqDao titanOrderPayreqDao;

    @Resource
    private TitanTransferReqDao titanTransferReqDao;

    @Resource
    private TitanWithDrawReqDao titanWithDrawReqDao;

    @Resource
    private TitanRefundDao titanRefundDao;

    /**
     * 充值单校验逻辑,检验本地充值单和远程充值单是否一致
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ApiException
     */
    public List<Transorderinfo> validOrderPayRequest(Date startTime, Date endTime) throws ApiException {

        WheatfieldOrdernQueryRequest ordernQueryRequest = new WheatfieldOrdernQueryRequest();
        //ordernQueryRequest.setUserid("141223100000056");
        //ordernQueryRequest.setStatus("1");
        ordernQueryRequest.setMerchantcode("M000016");
        ordernQueryRequest.setFunccode("4015");
        ordernQueryRequest.setStarttime(startTime);
        ordernQueryRequest.setEndtime(endTime);
        WheatfieldOrdernQueryResponse wheatfieldOrdernQueryResponse = TradeValidationUtils.ropClient.execute(ordernQueryRequest, session);
        List<Transorderinfo> transorderinfoList = wheatfieldOrdernQueryResponse.getTransorderinfos();

        Set<String> statusList = new HashSet<String>();
        Set<String> funCodeList = new HashSet<String>();

        for (Transorderinfo transorderinfo : transorderinfoList) {
            statusList.add(transorderinfo.getOrderstatus());
            funCodeList.add(transorderinfo.getFunccode());
        }

        //查询本地所有充值单
        TitanOrderPayreqParam requestParam = new TitanOrderPayreqParam();
        requestParam.setStartTime(DateUtil.dateToString(startTime, "yyyyMMddHHmmss"));
        requestParam.setEndTime(DateUtil.dateToString(endTime, "yyyyMMddHHmmss"));
        List<TitanOrderPayreq> titanOrderPayreqs = titanOrderPayreqDao.queryOrderPayRequestList(requestParam);

        List<Transorderinfo> result = new ArrayList<Transorderinfo>();
        List<TitanOrderPayreq> payreqList = new ArrayList<TitanOrderPayreq>();

        //校验本地充值单和真实是否一致
        for (TitanOrderPayreq payreq : titanOrderPayreqs) {
            boolean isValid = false;
            boolean hasMatched = false;
            for (Transorderinfo transorderinfo : transorderinfoList) {
                if (transorderinfo.getOrderno().equals(payreq.getOrderNo())) {
                    hasMatched = true;
                    if (payreq.getReqstatus() == 2) {//如果本地成功
                        //交易不成功
                        if ("1".equals(transorderinfo.getOrderstatus())) {
                            isValid = true;
                        } else {
                            result.add(transorderinfo);
                            payreqList.add(payreq);
                        }
                        break;
                    }
                }
            }
            //如果无匹配的，判定状态是否有问题
            if (!hasMatched) {
                if (payreq.getReqstatus() != 2) {
                    isValid = true;
                } else {
                    payreqList.add(payreq);
                }
            }
        }
        for (Transorderinfo transorderinfo : transorderinfoList) {
            boolean hasMatched = false;
            for (TitanOrderPayreq payreq : titanOrderPayreqs) {
                if (transorderinfo.getOrderno().equals(payreq.getOrderNo())) {
                    hasMatched = true;
                }
            }
            if (!hasMatched) {
                result.add(transorderinfo);
            }
        }

        return result;
    }

    public void validRemoteOrders(){

    }


    /**
     * 验证本地退款单是否跟远程匹配
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ApiException
     */
    public List<Transorderinfo> validRefundOrderInfo(Date startTime, Date endTime) throws ApiException {
        List<Transorderinfo> result = new ArrayList<Transorderinfo>();
        Map<String, RefundDTO> refundResult = new HashMap<String, RefundDTO>();
        Map<String, NotifyRefundResponse> refundResponseMap = new HashMap<String, NotifyRefundResponse>();
        RefundDTO refundQuery = new RefundDTO();
        refundQuery.setStartTime(startTime);
        refundQuery.setEndTime(endTime);
//        refundQuery.setMerchantNo("141223100000056");
        List<RefundDTO> refundDTOList = titanRefundDao.queryRefundDTODetail(refundQuery);

        for (RefundDTO refundDTO : refundDTOList) {
            if (!StringUtil.isValidString(refundDTO.getRefundOrderno()) &&
                    Integer.parseInt(refundDTO.getRefundAmount()) > 100) {
                refundResult.put(refundDTO.getOrderNo(), refundDTO);
                continue;
            }
            if (Integer.parseInt(refundDTO.getRefundAmount()) < 100) {
                continue;
            }
            NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
            notifyRefundRequest.setBusiCode(BusiCodeEnum.QueryRefund.getKey());
            notifyRefundRequest.setMerchantNo("M000016");
            notifyRefundRequest.setOrderNo(refundDTO.getOrderNo());
            notifyRefundRequest.setRefundAmount(refundDTO.getRefundAmount());
            //DateUtil.dateToString(DateUtil.stringToDate("2017-04-06 15:23:48","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss")
            notifyRefundRequest.setOrderTime(refundDTO.getOrderTime());
            notifyRefundRequest.setRefundOrderno(refundDTO.getRefundOrderno());//OD20170502092341001,OD20170505142021001 ，OD2017060210124100122
            notifyRefundRequest.setVersion(refundDTO.getVersion());
//            if ("v2.0".equals(refundDTO.getVersion())) {
//                notifyRefundRequest.setVersion("v1.1");
//            }
            notifyRefundRequest.setSignType(SignTypeEnum.MD5.getKey());
            NotifyRefundResponse notifyRefundResponse = notifyGatewayRefund(notifyRefundRequest);
            if (StringUtil.isValidString(notifyRefundResponse.getErrMsg()) &&
                    Integer.parseInt(refundDTO.getRefundAmount()) > 100) {
                refundResult.put(refundDTO.getOrderNo(), refundDTO);
            } else {
                if (String.valueOf(refundDTO.getStatus()).equals(notifyRefundResponse.getRefundStatus()) &&
                        ("0".equals(notifyRefundResponse.getRefundStatus()) || "2".equals(notifyRefundResponse.getRefundStatus()))) {

                } else {
                    if (Integer.parseInt(refundDTO.getRefundAmount()) > 100) {
                        refundResult.put(refundDTO.getOrderNo(), refundDTO);
                        refundResponseMap.put(notifyRefundResponse.getOrderNo(), notifyRefundResponse);
                    }
                }
            }
        }

        for (RefundDTO refundDTO : refundResult.values()) {
            if (!refundResponseMap.containsKey(refundDTO.getOrderNo())) {
                continue;
            }
            if (refundDTO.getStatus() == 0 && (refundDTO.getTransStatus() == 13 || refundDTO.getTransStatus() == 16) &&
                    "2".equals(refundResponseMap.get(refundDTO.getOrderNo()).getRefundStatus())) {
                //TODO 更新
                System.out.println(refundDTO.getOrderNo());
                RefundDTO updateRefund = new RefundDTO();
                updateRefund.setOrderNo(refundDTO.getOrderNo());
                updateRefund.setStatus(2);
//                titanRefundDao.updateRefundDTO(updateRefund);
            }
        }


        return result;
    }

    /**
     * 因退款需要先转账所以同时验证退款，校验充值和转账，将充值和转账全部查出，先只验证中间账户
     * 中间账户充值资金 = 中间账户转出资金 + 退款成功金额
     * 得到：有问题的退款单，有问题的充值单，有问题的转账单
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ApiException
     */
    public List<Transorderinfo> validPayAndTransfer(Date startTime, Date endTime, String orgCode) throws ApiException {
        //融数充值入某个账户（中间账户）需核实的单
        List<Transorderinfo> rechargeResult = new ArrayList<Transorderinfo>();
        //融数从某个账户（中间账户）转出需核实的单
        List<Transorderinfo> paidOutResult = new ArrayList<Transorderinfo>();
        //融数转入某个账户（中间账户）需核实的单
        List<Transorderinfo> paidInResult = new ArrayList<Transorderinfo>();

        List<Transorderinfo> withDrawResult = new ArrayList<Transorderinfo>();

        //本地数据库充值需核实
        List<TitanOrderPayreq> localRechargeResult = new ArrayList<TitanOrderPayreq>();
        //本地数据库转账转入需核实
        List<TitanTransferReq> localPayInResult = new ArrayList<TitanTransferReq>();
        //本地数据库转账转出需核实
        List<TitanTransferReq> localPayOutResult = new ArrayList<TitanTransferReq>();
        //本地退款需核实
        List<RefundDTO> localRefundResult = new ArrayList<RefundDTO>();

        List<TitanWithDrawReq> localWithDrawResult = new ArrayList<TitanWithDrawReq>();

        //融数充值充入合计
        long rechargeCount = 0l;
        //融数转账转出合计
        long payOutCount = 0l;
        //融数转账转入合计
        long payInCount = 0l;

        //融数涉及状态集合
        Set<String> statusSet = new HashSet<String>();
        //融数涉及业务编码集合
        Set<String> funCodeSet = new HashSet<String>();

        WheatfieldOrdernQueryRequest ordernQueryRequest = new WheatfieldOrdernQueryRequest();
        //中间账户充值进账记录
        ordernQueryRequest.setUserid(orgCode);
        //ordernQueryRequest.setStatus("1");
        ordernQueryRequest.setMerchantcode("M000016");
        ordernQueryRequest.setFunccode("4015");
        ordernQueryRequest.setStarttime(startTime);
        ordernQueryRequest.setEndtime(endTime);
        WheatfieldOrdernQueryResponse wheatfieldOrdernQueryResponse = TradeValidationUtils.ropClient.execute(ordernQueryRequest, session);
        List<Transorderinfo> rechargeInfo = wheatfieldOrdernQueryResponse.getTransorderinfos();
        if (null == rechargeInfo) {
            rechargeInfo = Collections.emptyList();
        }
        log.info("融数充值：融数查询充值入当前账户:" + orgCode + "的总记录数：" + rechargeInfo.size());

        //中间账户转出记录"TJM10000022"
        ordernQueryRequest.setUserid(orgCode);
        //ordernQueryRequest.setStatus("1");
        ordernQueryRequest.setMerchantcode("M000016");
        ordernQueryRequest.setFunccode("3001");
        ordernQueryRequest.setStarttime(startTime);
        ordernQueryRequest.setEndtime(endTime);
        wheatfieldOrdernQueryResponse = TradeValidationUtils.ropClient.execute(ordernQueryRequest, session);
        List<Transorderinfo> paidOut = wheatfieldOrdernQueryResponse.getTransorderinfos();
        if (null == paidOut) {
            paidOut = Collections.emptyList();
        }
        log.info("融数转出：融数查询从当前账户:" + orgCode + "转出资金的总记录数：" + paidOut.size());

        //中间账户退款（部分手工调账的）转入记录
        ordernQueryRequest.setIntermerchantcode(orgCode);
        ordernQueryRequest.setUserid(null);
        //ordernQueryRequest.setStatus("1");
        ordernQueryRequest.setMerchantcode("M000016");
        ordernQueryRequest.setFunccode("3001");
        ordernQueryRequest.setStarttime(startTime);
        ordernQueryRequest.setEndtime(endTime);
        wheatfieldOrdernQueryResponse = TradeValidationUtils.ropClient.execute(ordernQueryRequest, session);
        List<Transorderinfo> paidIn = wheatfieldOrdernQueryResponse.getTransorderinfos();
        if (null == paidIn) {
            paidIn = Collections.emptyList();
        }
        log.info("融数转入：融数查询转账转入当前账户:" + orgCode + "的总记录数：" + paidIn.size());

        //中间账户退款（部分手工调账的）转入记录
        ordernQueryRequest.setIntermerchantcode(null);
        ordernQueryRequest.setUserid(orgCode);
        //ordernQueryRequest.setStatus("1");
        ordernQueryRequest.setMerchantcode("M000016");
        ordernQueryRequest.setFunccode("4016");
        ordernQueryRequest.setStarttime(startTime);
        ordernQueryRequest.setEndtime(endTime);
        wheatfieldOrdernQueryResponse = TradeValidationUtils.ropClient.execute(ordernQueryRequest, session);
        List<Transorderinfo> withDraw = wheatfieldOrdernQueryResponse.getTransorderinfos();
        if (null == withDraw) {
            withDraw = Collections.emptyList();
        }
        log.info("融数提现：融数查询当前账户:" + orgCode + "提现的总记录数：" + withDraw.size());

        for (Transorderinfo transorderinfo : rechargeInfo) {
            statusSet.add(transorderinfo.getOrderstatus());
            funCodeSet.add(transorderinfo.getFunccode());
            rechargeCount += Long.parseLong(transorderinfo.getAmount());
        }

        for (Transorderinfo transorderinfo : paidOut) {
            statusSet.add(transorderinfo.getOrderstatus());
            funCodeSet.add(transorderinfo.getFunccode());
            payOutCount += Long.parseLong(transorderinfo.getAmount());
        }

        for (Transorderinfo transorderinfo : paidIn) {
            statusSet.add(transorderinfo.getOrderstatus());
            funCodeSet.add(transorderinfo.getFunccode());
            payInCount += Long.parseLong(transorderinfo.getAmount());
        }
        log.info("融数：包含的状态集合：" + statusSet + ",包含的财务代码集合：" + funCodeSet);
        log.info("融数：充值总金额：" + rechargeCount + "，转出总金额" + payOutCount + "，转入总金额：" + payInCount);

        TitanOrderPayreqParam requestParam = new TitanOrderPayreqParam();
        requestParam.setStartTime(DateUtil.dateToString(startTime, "yyyyMMddHHmmss"));
        requestParam.setEndTime(DateUtil.dateToString(endTime, "yyyyMMddHHmmss"));
        requestParam.setMerchantNo(orgCode);
        List<TitanOrderPayreq> titanOrderPayReqs = titanOrderPayreqDao.queryOrderPayRequestList(requestParam);
        log.info("本地充值：当前账户" + orgCode + "充值单记录数：" + titanOrderPayReqs.size());

        TitanTransferReqParam titanTransferReqParam = new TitanTransferReqParam();
        titanTransferReqParam.setUserrelateid(orgCode);
        List<TitanTransferReq> localPayIn = titanTransferReqDao.queryTitanTransferReq(titanTransferReqParam);
        log.info("本地转入：转账转入当前账户" + orgCode + "的记录数：" + localPayIn.size());

        titanTransferReqParam.setUserrelateid(null);
        titanTransferReqParam.setUserid(orgCode);
        List<TitanTransferReq> localPayOut = titanTransferReqDao.queryTitanTransferReq(titanTransferReqParam);
        log.info("本地转出：转账转出当前账户" + orgCode + "的记录数：" + localPayOut.size());

        TitanWithDrawReqParam reqParam = new TitanWithDrawReqParam();
        reqParam.setStartTime(startTime);
        reqParam.setEndTime(endTime);
        reqParam.setUserid(orgCode);
        List<TitanWithDrawReq> withDrawReqs = titanWithDrawReqDao.queryList(reqParam);
        log.info("本地提现：本地当前账户" + orgCode + "提现的记录数：" + withDrawReqs.size());

        //经由账户成功退款记录，提款单号可能被更新；因此存在多次下退款单的情况
        RefundDTO refundQuery = new RefundDTO();
        refundQuery.setStartTime(startTime);
        refundQuery.setEndTime(endTime);
        refundQuery.setMerchantNo(orgCode);
        List<RefundDTO> refundDTOList = titanRefundDao.queryRefundDTODetail(refundQuery);
        if (refundDTOList == null) {
            refundDTOList = Collections.EMPTY_LIST;
        }
        log.info("本地退款：当前账户" + orgCode + "退出的记录数：" + refundDTOList.size());

        log.info("=======================开始验证充值单==============================");
        //校验本地充值单和真实是否一致
        for (TitanOrderPayreq payReq : titanOrderPayReqs) {
            boolean hasMatched = false;
            for (Transorderinfo transorderinfo : rechargeInfo) {
                if (transorderinfo.getOrderno().equals(payReq.getOrderNo())) {
                    if ("1".equals(transorderinfo.getOrderstatus()) && payReq.getReqstatus() == 2) {//如果本地成功
                        hasMatched = true;
                    } else {//不匹配
                        rechargeResult.add(transorderinfo);
                        localRechargeResult.add(payReq);
                    }
                    break;
                }
            }
            //如果无匹配的，但是本地是成功的需记录，本地不成功，就忽略
            if (!hasMatched) {
                if (payReq.getReqstatus() == 2) {
                    localRechargeResult.add(payReq);
                }
            }
        }

        for (Transorderinfo transorderinfo : rechargeInfo) {
            boolean hasMatched = false;
            for (TitanOrderPayreq payReq : titanOrderPayReqs) {
                if (transorderinfo.getOrderno().equals(payReq.getOrderNo()) &&
                        "1".equals(transorderinfo.getOrderstatus()) && payReq.getReqstatus() == 2) {
                    hasMatched = true;
                }
            }
            if (!hasMatched) {
                rechargeResult.add(transorderinfo);
            }
        }

        log.info("=======================开始验证转出单==============================");
        for (Transorderinfo payout : paidOut) {
            boolean isMatch = false;
            for (TitanTransferReq req : localPayOut){
                if (req.getRequestno().equals(payout.getRequestno()) && req.getStatus() == 2 &&
                        String.valueOf(req.getAmount()).equals(payout.getAmount())){
                    isMatch = true;
                }
            }
            if (!isMatch){
                log.info("融数转出单不存在本地对应，请求号：" + payout.getRequestno());
                paidOutResult.add(payout);
            }
        }

        for (TitanTransferReq req : localPayOut){
            boolean isMatch = false;
            if (req.getStatus() != 2){
                continue;
            }
            for (Transorderinfo payout : paidOut){
                if (req.getRequestno().equals(payout.getRequestno()) &&
                        req.getAmount().equals(Double.parseDouble(payout.getAmount()))){
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch){
                log.info("本地转出单不存在融数对应，请求号：" + req.getRequestno());
                localPayOutResult.add(req);
            }
        }

        log.info("=======================开始验证转入单==============================");
        for (Transorderinfo payin : paidIn) {
            boolean isMatch = false;
            for (TitanTransferReq req : localPayIn){
                if (req.getRequestno().equals(payin.getRequestno()) && req.getStatus() == 2 &&
                        req.getAmount().equals(Double.parseDouble(payin.getAmount()))){
                    isMatch = true;
                }
            }
            if (!isMatch){
                log.info("融数转入单不存在本地对应，请求号：" + payin.getRequestno());
                paidInResult.add(payin);
            }
        }
        for (TitanTransferReq req : localPayIn){
            boolean isMatch = false;
            if (req.getStatus() != 2){
                continue;
            }
            for (Transorderinfo payin : paidIn){
                if (req.getRequestno().equals(payin.getRequestno()) &&
                        req.getAmount().equals(Double.parseDouble(payin.getAmount()))){
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch){
                log.info("本地转入单不存在融数对应，请求号：" + req.getRequestno());
                localPayInResult.add(req);
            }
        }
        log.info("=======================开始验证提现单==============================");
        Set<String> reqNoSet = new HashSet<String>();
        List<Transorderinfo> duplicateList = new ArrayList<Transorderinfo>();
        for (Transorderinfo transorderinfo : withDraw){
            boolean isMatch = false;
            if (duplicateList.contains(transorderinfo.getRequestno())){
                log.info("提现单重复的记录:" + transorderinfo.getRequestno());
                duplicateList.add(transorderinfo);
            }
            reqNoSet.add(transorderinfo.getRequestno());
            for (TitanWithDrawReq req : withDrawReqs) {
                if (transorderinfo.getRequestno().equals(req.getUserorderid())
                        && transorderinfo.getAmount().equals(String.valueOf(req.getAmount()))
                        && transorderinfo.getOrderstatus().equals("4") && req.getStatus() == 3){
                    isMatch = true;
                    break;
                }
            }
            if ( !isMatch ){
                withDrawResult.add(transorderinfo);
            }
        }

        for (TitanWithDrawReq req : withDrawReqs){
            boolean isMatch = false;
            for (Transorderinfo transorderinfo : withDraw) {
                if (transorderinfo.getRequestno().equals(req.getUserorderid())
                        && transorderinfo.getAmount().equals(String.valueOf(req.getAmount()))
                        && transorderinfo.getOrderstatus().equals("4") && req.getStatus() == 3){
                    isMatch = true;
                    break;
                }
            }
            if ( !isMatch ){
                localWithDrawResult.add(req);
            }
        }

        log.info("=======================开始验证本地退款单============================");
        //针对一张单，如果发生退款，当前商家作为收款方：转账转出，作为付款方，转账转入
        //退款匹配逻辑,产生了退款单一定执行了转账，TODO 退款单可能会被修改
        for (RefundDTO refDTO : refundDTOList) {
            NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
            notifyRefundRequest.setBusiCode(BusiCodeEnum.QueryRefund.getKey());
            notifyRefundRequest.setMerchantNo("M000016");
            notifyRefundRequest.setOrderNo(refDTO.getOrderNo());
            notifyRefundRequest.setRefundAmount(refDTO.getRefundAmount());
            //DateUtil.dateToString(DateUtil.stringToDate("2017-04-06 15:23:48","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss")
            notifyRefundRequest.setOrderTime(refDTO.getOrderTime());
            notifyRefundRequest.setRefundOrderno(refDTO.getRefundOrderno());//OD20170502092341001,OD20170505142021001 ，OD2017060210124100122
            notifyRefundRequest.setVersion(refDTO.getVersion());
            notifyRefundRequest.setSignType(SignTypeEnum.MD5.getKey());
            NotifyRefundResponse notifyRefundResponse = notifyGatewayRefund(notifyRefundRequest);
            if (!notifyRefundResponse.isResult() || !notifyRefundResponse.getRefundStatus().
                    equals(String.valueOf(refDTO.getStatus()))){
                localRefundResult.add(refDTO);
                continue;
            }
            boolean transferStatus = false;
            //只验证退转账金额，暂不验证退手续费
            if (orgCode.equals(refDTO.getPayeeMerchant())) {
                for (Transorderinfo payout : paidOut) {
                    String createDate = DateUtil.dateToString(refDTO.getCreatetime(), "yyyy-MM-dd HH:mm:ss");
                    if (compareTime(createDate, payout.getCreatedtime())) {//时间很接近
                        //转账未处理时候
                        if (!transferStatus && payout.getAmount().equals(refDTO.getTransferAmount())) {
                            transferStatus = true;
                        }
                    }
                }
            }
            //表明此退款单不存在合适的转账单
            if (!transferStatus) {
                localRefundResult.add(refDTO);
            }
        }

        return rechargeResult;

//        Map<String, String> transferRechargeMap = new HashMap<String, String>();
//        //首先验证有充值和转出对不上的记录,有充值，无对应转账记录的
//        for (Transorderinfo transorderinfo : rechargeInfo) {
//            List<TitanTransferReq> transferReqList = titanTransferReqDao.queryTransferByOrderNo(transorderinfo.getOrderno());
//            if (transferReqList.isEmpty() || transferReqList.size() != 1) {
//                rechargeResult.add(transorderinfo);
//                continue;
//            }
//            TitanTransferReq transferReq = transferReqList.get(0);
//            //能查出来的充值交易状态一定等于1？
//            if (transferReq.getStatus() != 2 && "1".equals(transorderinfo.getOrderstatus())) {
//                rechargeResult.add(transorderinfo);
//                continue;
//            }
//            boolean isMatched = false;
//            for (Transorderinfo out : paidOut) {
//                if (transferReq.getRequestno().equals(out.getRequestno()) &&
//                        transorderinfo.getAmount().equals(out.getAmount())) {
//                    isMatched = true;
//                    transferRechargeMap.put(out.getRequestno(), transorderinfo.getOrderno());
//                    break;
//                }
//            }
//
//            if (!isMatched) {
//                rechargeResult.add(transorderinfo);
//            }
//
//        }
//
//        //有转账，无正确的充值记录的
//        for (Transorderinfo out : paidOut) {
//            /*TitanTransferReqParam titanTransferReqParam = new TitanTransferReqParam();
//            titanTransferReqParam.setRequestno(out.getRequestno());
//			List<TitanTransferReq>  transferReqList = titanTransferReqDao.queryTitanTransferReq(titanTransferReqParam);
//			if (transferReqList.isEmpty() || transferReqList.size() != 1){
//				paidOutResult.add(out);
//				continue;
//			}
//			TitanTransferReq transferReq = transferReqList.get(0);
//			TransOrderRequest transOrderRequest = new TransOrderRequest();
//			transOrderRequest.setTransid(transferReq.getTransorderid());
//			List<TransOrderDTO> transOrderDTOList = titanOrderService.queryTransOrder(transOrderRequest);
//			if (transOrderDTOList.isEmpty() || transOrderDTOList.size() != 1){
//				paidOutResult.add(out);
//				continue;
//			}
//			TransOrderDTO transOrderDTO = transOrderDTOList.get(0);*/
//            boolean isMatched = false;
//            for (Transorderinfo transorderinfo : rechargeInfo) {
//                if (transferRechargeMap.containsKey(out.getRequestno()) &&
//                        transferRechargeMap.get(out.getRequestno()).equals(transorderinfo.getOrderno())) {
//                    isMatched = true;
//                    break;
//                }
//            }
//
//            if (!isMatched) {
//                paidOutResult.add(out);
//            }
//        }

        //TODO 反向根据本地验证远程，校验或者本地存在远程不存在，或本地远程状态对不上；


    }

    /**
     * 去融数下退款单操作
     * 此方法可以修改成根据失败的退款单，创建新的退款单，同时重新发起一次退款
     * @param refundDTO 数据库中查询出的退款DTO
     * @return
     */
    public RefundOrderResponse fixRefundFailOrder(RefundDTO refundDTO) {
        RefundOrderResponse refundOrderResponse = new RefundOrderResponse();
        RSRefundRequest refundRequest = new RSRefundRequest();
        refundRequest.setAmount(refundDTO.getRefundAmount());
        refundRequest.setOrderid(refundDTO.getOrderNo());
        refundRequest.setUserorderid(refundDTO.getUserOrderId());
        refundRequest.setOrderitemid("");
        RsRefundResponse refundResponse = addRsRefundOrder(refundRequest);

        if (!CommonConstant.OPERATE_SUCCESS.equals(refundResponse.getOperateStatus())) {
            log.error("下退款单失败:" + refundResponse.getReturnCode() + ":" + refundResponse.getReturnMsg());
            refundOrderResponse.putErrorResult(TitanMsgCodeEnum.RS_ADD_REFUND_ORDER_FAIL);
            return refundOrderResponse;
        }
        log.info("新落单的退款单号：" + refundOrderResponse.getRefundOrderNo());
        //TODO 本地保存或更新订单的过程

        //重新申请退款
        NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
        notifyRefundRequest.setBusiCode(BusiCodeEnum.MerchantRefund.getKey());
        notifyRefundRequest.setMerchantNo("M000016");
        notifyRefundRequest.setOrderNo(refundDTO.getOrderNo());
        notifyRefundRequest.setRefundAmount(refundDTO.getRefundAmount());
        //DateUtil.dateToString(DateUtil.stringToDate("2017-04-06 15:23:48","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss")
        notifyRefundRequest.setOrderTime(refundDTO.getOrderTime());
        notifyRefundRequest.setRefundOrderno(refundResponse.getRefundOrderNo());//OD20170502092341001,OD20170505142021001 ，OD2017060210124100122
        notifyRefundRequest.setVersion(refundDTO.getVersion());
        notifyRefundRequest.setSignType(SignTypeEnum.MD5.getKey());
        NotifyRefundResponse notifyRefundResponse = notifyGatewayRefund(notifyRefundRequest);

        System.out.println("重新发起退款申请的退款结果：" + notifyRefundResponse.getRefundStatus());
        refundOrderResponse.putSuccess();
        refundOrderResponse.setRefundOrderNo(refundResponse.getRefundOrderNo());
        return refundOrderResponse;
    }

    /**
     * 通知网关退款，可以退款申请或者退款查询
     * @param notifyRefundRequest
     * @return
     */
    public NotifyRefundResponse notifyGatewayRefund(NotifyRefundRequest notifyRefundRequest) {
        NotifyRefundResponse notifyRefundResponse = new NotifyRefundResponse();
        if (null == notifyRefundRequest) {
            notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
            return notifyRefundResponse;
        }
        List<NameValuePair> params = getGatewayParam(notifyRefundRequest);
        System.out.println(params);
        String response;
        HttpPost httpPost = new HttpPost("http://checkstand.rongcapital.cn:8485/checkstand/payment");
        try {
            HttpResponse resp = HttpClient.httpRequest(params, httpPost);
            if (null != resp) {
                HttpEntity entity = resp.getEntity();
                response = EntityUtils.toString(entity);
                log.info("退款返回信息：" + response);
                notifyRefundResponse = RSConvertFiled2ObjectUtil.convertField2Object(NotifyRefundResponse.class, response);
                notifyRefundResponse.putSuccess();
                if (StringUtil.isValidString(notifyRefundResponse.getErrCode())
                        || StringUtil.isValidString(notifyRefundResponse.getErrMsg())
                        || !StringUtil.isValidString(notifyRefundResponse.getRefundOrderno())) {
                    log.error("退款申请/查询返回结果异常:" + notifyRefundResponse.getErrMsg());
                    notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
                }
                return notifyRefundResponse;
            }
        } catch (ParseException e) {
            notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
            log.error("网关退款异常", e);
        } catch (Exception e) {
            notifyRefundResponse.putErrorResult(TitanMsgCodeEnum.RS_NOTIFY_REFUND_FAIL);
            log.error("网关退款异常", e);
        }
        return notifyRefundResponse;
    }

    // 去融数落退款单，返回退款单号
    private RsRefundResponse addRsRefundOrder(RSRefundRequest refundRequest) {
        RsRefundResponse response = new RsRefundResponse();
        try {
            WheatfieldOrderServiceReturngoodsRequest req = new WheatfieldOrderServiceReturngoodsRequest();
            MyBeanUtil.copyBeanProperties(req, refundRequest);
            WheatfieldOrderServiceReturngoodsResponse returngoodsResponse = ropClient.execute(req, session);
            if (returngoodsResponse != null) {
                log.info("调用去融数下退款单返回报文: " + returngoodsResponse.getBody());
                String errorMsg;
                if (returngoodsResponse.isSuccess() != true) {
                    if (StringUtil.isValidString(returngoodsResponse.getSubMsg())) {
                        errorMsg = returngoodsResponse.getSubMsg();
                    } else {
                        errorMsg = returngoodsResponse.getMsg();
                    }
                    response.setReturnCode(returngoodsResponse.getErrorCode());
                    response.setReturnMsg(errorMsg);
                    log.error("调用融数下退款单失败：" + errorMsg);
                } else {
                    response.setSuccess(true);
                    response.setOperateStatus(returngoodsResponse.getIs_success());
                    response.setReturnMsg(returngoodsResponse.getMsg());
                    response.setRefundOrderNo(returngoodsResponse.getBatchno());
                    if (returngoodsResponse.getIs_success().equals(CommonConstant.OPERATE_SUCCESS)) {
                        response.setReturnCode(RSInvokeErrorEnum.INVOKE_SUCCESS.returnCode);
                        response.setReturnMsg(RSInvokeErrorEnum.INVOKE_SUCCESS.returnMsg);
                    }
                }
            } else {
                response.setReturnCode(RSInvokeErrorEnum.RETURN_EMPTY.returnCode);
                response.setReturnMsg(RSInvokeErrorEnum.RETURN_EMPTY.returnMsg);
            }

        } catch (Exception e) {
            response.setReturnCode(RSInvokeErrorEnum.UNKNOWN_ERROR.returnCode);
            response.setReturnMsg(RSInvokeErrorEnum.UNKNOWN_ERROR.returnMsg);
            log.error("下退款单失败：" + e.getMessage(), e);
        }
        return response;
    }

    //获取网关参数
    private List<NameValuePair> getGatewayParam(NotifyRefundRequest notifyRefundRequest) {
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("merchantNo", notifyRefundRequest.getMerchantNo()));
        params.add(new BasicNameValuePair("busiCode", notifyRefundRequest.getBusiCode()));
        params.add(new BasicNameValuePair("orderNo", notifyRefundRequest.getOrderNo()));
        params.add(new BasicNameValuePair("refundAmount", notifyRefundRequest.getRefundAmount()));
        params.add(new BasicNameValuePair("orderTime", notifyRefundRequest.getOrderTime()));//
        params.add(new BasicNameValuePair("refundOrderno", notifyRefundRequest.getRefundOrderno()));
        params.add(new BasicNameValuePair("version", notifyRefundRequest.getVersion()));
        params.add(new BasicNameValuePair("signType", notifyRefundRequest.getSignType()));
        String signMsg = getMD5Sign(getSignStr(notifyRefundRequest), "UTF-8");
        params.add(new BasicNameValuePair("signMsg", signMsg));
        return params;
    }

    //获取签名字符串
    private String getSignStr(NotifyRefundRequest notifyRefundRequest) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("signType=" + notifyRefundRequest.getSignType());
        stringBuffer.append("&version=" + notifyRefundRequest.getVersion());
        stringBuffer.append("&merchantNo=" + notifyRefundRequest.getMerchantNo());
        stringBuffer.append("&refundOrderno=" + notifyRefundRequest.getRefundOrderno());
        stringBuffer.append("&orderNo=" + notifyRefundRequest.getOrderNo());
        stringBuffer.append("&orderTime=" + notifyRefundRequest.getOrderTime());
        stringBuffer.append("&refundAmount=" + notifyRefundRequest.getRefundAmount());
        stringBuffer.append("&key=PCDEFOI8808TFC");
        return stringBuffer.toString();
    }

    //MD5编码
    private String getMD5Sign(String str, String charset) {
        return MD5.MD5Encode(str, charset);
    }

    /**
     * 比较充值和转账字串中的时间间隔
     * 只是针对当前交易量不多的情景有效
     * source < target 并且 target-source 小于5s 返回true;
     *
     * @param source 充值金账户时间
     * @param target 转账转出时间
     * @return
     */
    private boolean compareTime(String source, String target) {
        Date first = DateUtil.stringToDate(source, "yyyy-MM-dd HH:mm:ss");
        Date second = DateUtil.stringToDate(target, "yyyy-MM-dd HH:mm:ss");
        //DateUtil.compareInMS(first, second) <= 0
        if (Math.abs(second.getTime() - first.getTime()) < 5 * 1000) {
            return true;
        }
        return false;
    }
}
