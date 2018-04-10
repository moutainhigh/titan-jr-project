package com.fangcang.titanjr.util;

import java.io.*;
import java.util.*;

import com.Rop.api.ApiException;
import com.Rop.api.DefaultRopClient;
import com.Rop.api.domain.SHBalanceInfo;
import com.Rop.api.domain.Transorderinfo;
import com.Rop.api.request.WheatfieldBalanceGetlistRequest;
import com.Rop.api.request.WheatfieldOrderServiceReturngoodsRequest;
import com.Rop.api.request.WheatfieldOrdernQueryRequest;
import com.Rop.api.response.WheatfieldBalanceGetlistResponse;
import com.Rop.api.response.WheatfieldOrderServiceReturngoodsResponse;
import com.Rop.api.response.WheatfieldOrdernQueryResponse;
import com.fangcang.titanjr.common.enums.RSInvokeErrorEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.RSConvertFiled2ObjectUtil;
import com.fangcang.titanjr.common.util.httpclient.HttpClient;
import com.fangcang.titanjr.dao.*;
import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.dto.request.NotifyRefundRequest;
import com.fangcang.titanjr.dto.request.RefundOrderRequest;
import com.fangcang.titanjr.dto.response.NotifyRefundResponse;
import com.fangcang.titanjr.dto.response.RefundOrderResponse;
import com.fangcang.titanjr.entity.*;
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
import net.sf.json.JSONSerializer;
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
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

    private static String filePath = System.getProperty("user.home") + File.separator + "export.xlsx";

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

    @Resource
    private TitanOrgDao titanOrgDao;


    /**
     * 验证一个机构一段时间内所有交易单的状态，并将本地和远程(融数)核对不上的交易记录在excel中
     * @param startTime 验证开始时间
     * @param endTime 验证结束时间
     * @param orgCode 机构编码
     * @return 所有无法核对上的交易
     * @throws ApiException
     */
    public Map<String, List> validOrgTradeInfo(Date startTime, Date endTime, String orgCode) throws ApiException {
        Map<String, List> resultMap = new HashMap<String, List>();
        resultMap.putAll(this.validRechargeOrder(startTime, endTime, orgCode));
        resultMap.putAll(this.validTransferOrder(startTime, endTime, orgCode));
        resultMap.putAll(this.validWithDrawOrder(startTime, endTime, orgCode));
        resultMap.putAll(this.validRefundOrder(startTime, endTime, orgCode));
        return resultMap;
    }

    /**
     * "收入资金统计，充值合计：" + totalRecharge + ",账户转入合计：" + totalPayIn
     * "支出资金统计，账户转出合计：" + payOutTotal + ",提现合计："
     *                 + withDrawTotal + ",退款合计：" + refundTotal + ",账户余额：" + accountAmount
     * 验证收入和支出是否相等
     * @param orgCode
     * @return
     * @throws ApiException
     */
    public boolean validAccountAmount(String orgCode) throws ApiException {

        //判定包含的主账户和子账户数据，判定机构有多少子账户
        //只验证主账户P000070
        //计算充值金额
        TitanOrderPayreqParam requestParam = new TitanOrderPayreqParam();
        requestParam.setMerchantNo(orgCode);
        List<TitanOrderPayreq> titanOrderPayReqs = titanOrderPayreqDao.queryOrderPayRequestList(requestParam);
        log.info("本地充值：当前账户" + orgCode + "充值单记录数：" + titanOrderPayReqs.size());
        Long totalRecharge = 0l;
        for (TitanOrderPayreq payreq : titanOrderPayReqs) {
            if (payreq.getReqstatus() == 2) {//计算充值成功的
                totalRecharge += (long) (payreq.getOrderAmount().doubleValue());
            }
        }
        //计算转入金额
        TitanTransferReqParam titanTransferReqParam = new TitanTransferReqParam();
        titanTransferReqParam.setUserrelateid(orgCode);
        List<TitanTransferReq> localPayIn = titanTransferReqDao.queryTitanTransferReq(titanTransferReqParam);
        Long totalPayIn = 0l;
        for (TitanTransferReq payIn : localPayIn) {
            if (payIn.getStatus() == 2) {//转账成功的
                totalPayIn += (long) (payIn.getAmount().doubleValue());
            }
        }

        //计算转出金额
        titanTransferReqParam.setUserrelateid(null);
        titanTransferReqParam.setUserid(orgCode);
        List<TitanTransferReq> localPayOut = titanTransferReqDao.queryTitanTransferReq(titanTransferReqParam);
        Long payOutTotal = 0l;
        for (TitanTransferReq payOut : localPayOut) {
            if (payOut.getStatus() == 2) {//转账成功的
                payOutTotal += (long) (payOut.getAmount().doubleValue());
            }
        }

        //计算提现金额
        TitanWithDrawReqParam reqParam = new TitanWithDrawReqParam();
        reqParam.setUserid(orgCode);
        List<TitanWithDrawReq> withDrawReqs = titanWithDrawReqDao.queryList(reqParam);
        Long withDrawTotal = 0l;
        for (TitanWithDrawReq withDrawReq : withDrawReqs) {
            if (withDrawReq.getStatus() == 3) {//提现成功
                withDrawTotal += withDrawReq.getAmount();
            }
        }

        //计算退款金额
        RefundDTO refundQuery = new RefundDTO();
        refundQuery.setPayerMerchant(orgCode);//退款单中付款方为自己的
        List<RefundDTO> refundDTOList = titanRefundDao.queryRefundDTODetail(refundQuery);
        Long refundTotal = 0l;
        for (RefundDTO refundDTO : refundDTOList) {
            if (refundDTO.getStatus() == 2 && (refundDTO.getTransStatus() == 13 || refundDTO.getTransStatus() == 16)) {//先计算退款成功的
                refundTotal += Long.parseLong(refundDTO.getRefundAmount());
            }
        }

        //获取账户余额
        WheatfieldBalanceGetlistRequest getlistRequest = new WheatfieldBalanceGetlistRequest();
        getlistRequest.setRootinstcd("M000016");
        getlistRequest.setUserid(orgCode);

        WheatfieldBalanceGetlistResponse getlistResponse = TradeValidationUtils.ropClient.execute(getlistRequest, session);
        Long accountAmount = 0l;
        List<SHBalanceInfo> balanceInfos = getlistResponse.getShbalanceinfos();
        for (SHBalanceInfo balanceInfo : balanceInfos) {
            if ("P000070".equals(balanceInfo.getProductid())) {
                accountAmount = Long.parseLong(balanceInfo.getBalanceusable()) + Long.parseLong(balanceInfo.getBalancefrozon());
            }
        }

        //公式：充值+转入=转出+提现+退款+账户余额
        log.info("收入资金统计，充值合计：" + totalRecharge + ",账户转入合计：" + totalPayIn);
        Long totalIn = totalRecharge + totalPayIn;
        log.info("支出资金统计，账户转出合计：" + payOutTotal + ",提现合计："
                + withDrawTotal + ",退款合计：" + refundTotal + ",账户余额：" + accountAmount);
        Long totalOut = payOutTotal + withDrawTotal + refundTotal + accountAmount;
        log.info("收入总计：" + totalIn + "支出总计：" + totalOut);
        if (totalIn.equals(totalOut)) {
            return true;
        }
        return false;
    }

    /**
     * 验证系统所有虚拟账户的余额
     * 跟备付金进行核对
     * @return
     * @throws ApiException
     */
    public Map<String, Long> computeAllBalance() throws ApiException {
        List<TitanOrg> titanOrgs = titanOrgDao.queryTitanOrgWithTrans();
        Map<String, Long> accountAmountMap = new HashMap<String, Long>();
        TitanOrg commonOrg = new TitanOrg();
        commonOrg.setUserid("141223100000056");
//        titanOrgs.add(commonOrg);
        WheatfieldBalanceGetlistRequest getlistRequest = new WheatfieldBalanceGetlistRequest();
        getlistRequest.setRootinstcd("M000016");
        for (TitanOrg titanOrg : titanOrgs) {
            log.info("当前机构编码：" + titanOrg.getUserid());
            getlistRequest.setUserid(titanOrg.getUserid());
            WheatfieldBalanceGetlistResponse getlistResponse = TradeValidationUtils.ropClient.execute(getlistRequest, session);
            List<SHBalanceInfo> balanceInfos = getlistResponse.getShbalanceinfos();
            if (CollectionUtils.isNotEmpty(balanceInfos)) {
                for (SHBalanceInfo balanceInfo : balanceInfos) {
                    Long accountAmount = (Long.parseLong(balanceInfo.getBalanceusable()) +
                            Long.parseLong(balanceInfo.getBalancefrozon()));
                    if (!accountAmount.equals(Long.parseLong(balanceInfo.getAmount()))) {
                        log.info("存在可用余额+冻结金额不等于账户余额的账户：" + JSONSerializer.toJSON(balanceInfo));
                    }
                    if (accountAmountMap.containsKey(balanceInfo.getProductid())) {
                        accountAmount = accountAmountMap.get(balanceInfo.getProductid()) + accountAmount;
                    }
                    accountAmountMap.put(balanceInfo.getProductid(), accountAmount);
                }
            }
        }
        return accountAmountMap;
    }

    /**
     * 充值单校验逻辑,检验本地充值单和远程充值单是否一致
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ApiException
     */
    public Map<String, List> validRechargeOrder(Date startTime, Date endTime, String orgCode) throws ApiException {
        Map<String, List> resultMap = new HashMap<String, List>();
        //融数充值入某个账户（中间账户）需核实的单
        List<Transorderinfo> rechargeResult = new ArrayList<Transorderinfo>();
        //本地数据库充值需核实
        List<TitanOrderPayreq> localRechargeResult = new ArrayList<TitanOrderPayreq>();
        resultMap.put("rechargeResult", rechargeResult);
        resultMap.put("localRechargeResult", localRechargeResult);

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
        //融数充值充入合计
        long rechargeCount = 0l;
        //融数涉及状态集合
        Set<String> statusSet = new HashSet<String>();
        //融数涉及业务编码集合
        Set<String> funCodeSet = new HashSet<String>();

        for (Transorderinfo transorderinfo : rechargeInfo) {
            statusSet.add(transorderinfo.getOrderstatus());
            funCodeSet.add(transorderinfo.getFunccode());
            rechargeCount += Long.parseLong(transorderinfo.getAmount());
        }
        log.info("融数：包含的状态集合：" + statusSet + ",包含的财务代码集合：" + funCodeSet);
        log.info("融数：充值总金额：" + rechargeCount);
        TitanOrderPayreqParam requestParam = new TitanOrderPayreqParam();
        requestParam.setStartTime(DateUtil.dateToString(startTime, "yyyyMMddHHmmss"));
        requestParam.setEndTime(DateUtil.dateToString(endTime, "yyyyMMddHHmmss"));
        requestParam.setMerchantNo(orgCode);
        List<TitanOrderPayreq> titanOrderPayReqs = titanOrderPayreqDao.queryOrderPayRequestList(requestParam);
        log.info("本地充值：当前账户" + orgCode + "充值单记录数：" + titanOrderPayReqs.size());
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
        writeExcelRecharge(rechargeResult, localRechargeResult, orgCode);
        return resultMap;
    }


    /**
     * 简化只验证转出单
     * 转出分两种情况，一种是付款给别人，一种是退款给别人
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ApiException
     */
    public Map<String, List> validTransferOrder(Date startTime, Date endTime, String orgCode) throws ApiException {
        Map<String, List> resultMap = new HashMap<String, List>();
        //融数从某个账户（中间账户）转出需核实的单
        List<Transorderinfo> paidOutResult = new ArrayList<Transorderinfo>();
        //本地数据库转账转出需核实
        List<TitanTransferReq> localPayOutResult = new ArrayList<TitanTransferReq>();
        resultMap.put("paidOutResult", paidOutResult);
        resultMap.put("localPayOutResult", localPayOutResult);
        //融数转账转出合计
        long payOutCount = 0l;
        //融数涉及状态集合
        Set<String> statusSet = new HashSet<String>();
        //融数涉及业务编码集合
        Set<String> funCodeSet = new HashSet<String>();
        WheatfieldOrdernQueryRequest ordernQueryRequest = new WheatfieldOrdernQueryRequest();
        //中间账户转出记录"TJM10000022"
        ordernQueryRequest.setUserid(orgCode);
        //ordernQueryRequest.setStatus("1");
        ordernQueryRequest.setMerchantcode("M000016");
        ordernQueryRequest.setFunccode("3001");
        ordernQueryRequest.setStarttime(startTime);
        ordernQueryRequest.setEndtime(endTime);

        WheatfieldOrdernQueryResponse wheatfieldOrdernQueryResponse = TradeValidationUtils.ropClient.execute(ordernQueryRequest, session);
        List<Transorderinfo> paidOut = wheatfieldOrdernQueryResponse.getTransorderinfos();
        if (null == paidOut) {
            paidOut = Collections.emptyList();
        }
        log.info("融数转出：融数查询从当前账户:" + orgCode + "转出资金的总记录数：" + paidOut.size());
        for (Transorderinfo transorderinfo : paidOut) {
            statusSet.add(transorderinfo.getOrderstatus());
            funCodeSet.add(transorderinfo.getFunccode());
            payOutCount += Long.parseLong(transorderinfo.getAmount());
        }
        log.info("融数：包含的状态集合：" + statusSet + ",包含的财务代码集合：" + funCodeSet);
        log.info("融数：转出总金额" + payOutCount);

        TitanTransferReqParam titanTransferReqParam = new TitanTransferReqParam();
        titanTransferReqParam.setUserid(orgCode);
        titanTransferReqParam.setStartTime(startTime);
        titanTransferReqParam.setEndTime(endTime);
        List<TitanTransferReq> localPayOut = titanTransferReqDao.queryTitanTransferReq(titanTransferReqParam);
        log.info("本地转出：转账转出当前账户" + orgCode + "的记录数：" + localPayOut.size());
        log.info("=======================开始验证转出单==============================");
        for (Transorderinfo payout : paidOut) {
            boolean isMatch = false;
            for (TitanTransferReq req : localPayOut) {
                if (req.getRequestno().equals(payout.getRequestno()) && req.getStatus() == 2 &&
                        Double.valueOf(req.getAmount()).equals(Double.valueOf(payout.getAmount()))) {
                    isMatch = true;
                }
            }
            if (!isMatch) {
                log.info("融数转出单不存在本地对应，请求号：" + payout.getRequestno());
                paidOutResult.add(payout);
            }
        }
        for (TitanTransferReq req : localPayOut) {
            boolean isMatch = false;
            if (req.getStatus() != 2) {
                continue;
            }
            for (Transorderinfo payout : paidOut) {
                if (req.getRequestno().equals(payout.getRequestno()) &&
                        String.valueOf(req.getAmount()).equals(payout.getAmount())) {
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch) {
                log.info("本地转出单不存在融数对应，请求号：" + req.getRequestno());
                localPayOutResult.add(req);
            }
        }
        //写入excel
        writeExcelTransfer(paidOutResult, localPayOutResult, orgCode);
        return resultMap;
    }

    /**
     * 校验本地提现单和融数提现单是否有不一致
     *
     * @param startTime
     * @param endTime
     * @param orgCode
     */
    public Map<String, List> validWithDrawOrder(Date startTime, Date endTime, String orgCode) throws ApiException {
        Map<String, List> resultMap = new HashMap<String, List>();
        List<Transorderinfo> withDrawResult = new ArrayList<Transorderinfo>();
        List<TitanWithDrawReq> localWithDrawResult = new ArrayList<TitanWithDrawReq>();
        resultMap.put("withDrawResult", withDrawResult);
        resultMap.put("localWithDrawResult", localWithDrawResult);

        TitanWithDrawReqParam reqParam = new TitanWithDrawReqParam();
        reqParam.setStartTime(startTime);
        reqParam.setEndTime(endTime);
        reqParam.setUserid(orgCode);
        List<TitanWithDrawReq> withDrawReqs = titanWithDrawReqDao.queryList(reqParam);
        log.info("本地提现：本地当前账户" + orgCode + "提现的记录数：" + withDrawReqs.size());

        WheatfieldOrdernQueryRequest ordernQueryRequest = new WheatfieldOrdernQueryRequest();
        //中间账户退款（部分手工调账的）转入记录
        ordernQueryRequest.setIntermerchantcode(null);
        ordernQueryRequest.setUserid(orgCode);
        //ordernQueryRequest.setStatus("1");
        ordernQueryRequest.setMerchantcode("M000016");
        ordernQueryRequest.setFunccode("4016");
        ordernQueryRequest.setStarttime(startTime);
        ordernQueryRequest.setEndtime(endTime);
        WheatfieldOrdernQueryResponse wheatfieldOrdernQueryResponse = TradeValidationUtils.ropClient.execute(ordernQueryRequest, session);

        List<Transorderinfo> withDraw = wheatfieldOrdernQueryResponse.getTransorderinfos();
        if (null == withDraw) {
            withDraw = Collections.emptyList();
        }
        log.info("融数提现：融数查询当前账户:" + orgCode + "提现的总记录数：" + withDraw.size());

        log.info("=======================开始验证提现单==============================");
        Set<String> reqNoSet = new HashSet<String>();
        List<Transorderinfo> duplicateList = new ArrayList<Transorderinfo>();
        for (Transorderinfo transorderinfo : withDraw) {
            boolean isMatch = false;
            if (reqNoSet.contains(transorderinfo.getRequestno())) {
                log.info("提现单重复的记录:" + transorderinfo.getRequestno());
                duplicateList.add(transorderinfo);
            }
            reqNoSet.add(transorderinfo.getRequestno());
            for (TitanWithDrawReq req : withDrawReqs) {
                if (transorderinfo.getRequestno().equals(req.getUserorderid())
                        && transorderinfo.getAmount().equals(String.valueOf(req.getAmount()))
                        && transorderinfo.getOrderstatus().equals("4") && req.getStatus() == 3) {
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch) {
                withDrawResult.add(transorderinfo);
            }
        }

        for (TitanWithDrawReq req : withDrawReqs) {
            boolean isMatch = false;
            for (Transorderinfo transorderinfo : withDraw) {
                if (transorderinfo.getRequestno().equals(req.getUserorderid())
                        && transorderinfo.getAmount().equals(String.valueOf(req.getAmount()))
                        && transorderinfo.getOrderstatus().equals("4") && req.getStatus() == 3) {
                    isMatch = true;
                    break;
                }
            }
            if (!isMatch) {
                localWithDrawResult.add(req);
            }
        }
        //写入Excel
        writeExcelWithDraw(withDrawResult, localWithDrawResult, orgCode);
        return resultMap;
    }

    /**
     * 验证本地退款单是否跟远程匹配
     * 本地请求退款之前一定会先落一张单
     * TODO 本地退款单号可能被修改过
     *
     * @param startTime
     * @param endTime
     * @return
     * @throws ApiException
     */
    public Map<String, List> validRefundOrder(Date startTime, Date endTime, String orgCode) throws ApiException {
        Map<String, List> resultMap = new HashMap<String, List>();
        //经由账户成功退款记录，提款单号可能被更新；因此存在多次下退款单的情况
        List<RefundDTO> localRefundResult = new ArrayList<RefundDTO>();
        List<NotifyRefundResponse> refundResponseList = new ArrayList<NotifyRefundResponse>();
        resultMap.put("localRefundResult", localRefundResult);
        resultMap.put("refundResponseList", refundResponseList);
        RefundDTO refundQuery = new RefundDTO();
        refundQuery.setStartTime(startTime);
        refundQuery.setEndTime(endTime);
        refundQuery.setMerchantNo(orgCode);
        List<RefundDTO> refundDTOList = titanRefundDao.queryRefundDTODetail(refundQuery);
        if (refundDTOList == null) {
            refundDTOList = Collections.EMPTY_LIST;
        }
        log.info("本地退款：当前账户" + orgCode + "退出的记录数：" + refundDTOList.size());
        for (RefundDTO refundDTO : refundDTOList) {
            if (!StringUtil.isValidString(refundDTO.getRefundOrderno()) &&
                    Integer.parseInt(refundDTO.getRefundAmount()) > 100) {//无退款单号
                localRefundResult.add(refundDTO);
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
            notifyRefundRequest.setOrderTime(refundDTO.getOrderTime());
            notifyRefundRequest.setRefundOrderno(refundDTO.getRefundOrderno());
            notifyRefundRequest.setVersion(refundDTO.getVersion());
//            if ("v2.0".equals(refundDTO.getVersion())) {
//                notifyRefundRequest.setVersion("v1.1");
//            }
            notifyRefundRequest.setSignType(SignTypeEnum.MD5.getKey());
            NotifyRefundResponse notifyRefundResponse = notifyGatewayRefund(notifyRefundRequest);
            //查询失败
            if (StringUtil.isValidString(notifyRefundResponse.getErrMsg()) &&
                    Integer.parseInt(refundDTO.getRefundAmount()) > 100) {
                log.info("查询远程状态失败");
                localRefundResult.add(refundDTO);
                refundResponseList.add(notifyRefundResponse);
            }
            //当前单不匹配
            if (!notifyRefundResponse.getRefundStatus().equals(String.valueOf(refundDTO.getStatus())) ||
                    !notifyRefundResponse.getOrderAmount().equals(refundDTO.getRefundAmount())) {
                log.info("本地订单状态金额和远程不匹配");
                localRefundResult.add(refundDTO);
                refundResponseList.add(notifyRefundResponse);
            }
        }

        //记录退款信息写入excel
        writeExcelRefund(localRefundResult, refundResponseList, orgCode);
        return resultMap;
    }

    private void createSheetTitle(XSSFWorkbook xwb, File excelFile, String sheetName, String[] titleList) throws IOException {
        XSSFSheet xssfSheet = xwb.createSheet(sheetName);
        xssfSheet.createRow(0);
        int index = 0;
        while (index < titleList.length) {
            xssfSheet.getRow(0).createCell(index).setCellValue(titleList[index]);
            index++;
        }
        try (OutputStream outputStream = new FileOutputStream(excelFile)) {
            xwb.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            throw e;
        }
    }

    private void writeExcelRecharge(List<Transorderinfo> rechargeResult, List<TitanOrderPayreq> localRechargeResult, String orgCode) {
        log.info("充值异常记录写入excel开始，当前机构：" + orgCode);
        Integer startRow = 0;
        String[] titleList = {"商户号", "订单号/充值单ID",
                "充值请求号", "充值金额", "创建时间", "充值状态"};
        try {
            File excelFile = new File(filePath);
            XSSFWorkbook xwb;
            if (!excelFile.exists()) {
                xwb = new XSSFWorkbook();
                createSheetTitle(xwb, excelFile, "rechargeResult", titleList);
            } else {
                xwb = new XSSFWorkbook(new FileInputStream(excelFile));
                Sheet refundSheet = xwb.getSheet("rechargeResult");
                if (null != refundSheet) {
                    startRow = refundSheet.getLastRowNum();
                } else {
                    createSheetTitle(xwb, excelFile, "rechargeResult", titleList);
                }
            }
            xwb = new XSSFWorkbook(new FileInputStream(excelFile));
            Sheet rechargeSheet = xwb.getSheet("rechargeResult");
            for (int i = 0, j = localRechargeResult.size(); i < j; i++) {
                Row row = rechargeSheet.createRow(++startRow);
                row.createCell(0).setCellValue(orgCode);
                row.createCell(1).setCellValue(localRechargeResult.get(i).getOrderNo());
                row.createCell(2).setCellValue(localRechargeResult.get(i).getTransorderid());
                row.createCell(3).setCellValue(localRechargeResult.get(i).getOrderAmount());
                row.createCell(4).setCellValue(localRechargeResult.get(i).getOrderTime());
                row.createCell(5).setCellValue(String.valueOf(localRechargeResult.get(i).getReqstatus()));
            }

            for (int i = 0, j = rechargeResult.size(); i < j; i++) {
                Row row = rechargeSheet.createRow(++startRow);
                row.createCell(0).setCellValue(orgCode);
                row.createCell(1).setCellValue(rechargeResult.get(i).getOrderno());
                row.createCell(2).setCellValue(rechargeResult.get(i).getRequestno());
                row.createCell(3).setCellValue(rechargeResult.get(i).getAmount());
                row.createCell(4).setCellValue(rechargeResult.get(i).getCreatedtime());
                row.createCell(5).setCellValue(rechargeResult.get(i).getOrderstatus());
            }

            OutputStream stream = new FileOutputStream(excelFile);
            //写入数据
            xwb.write(stream);
            //关闭文件流
            stream.close();
        } catch (IOException e) {
            log.error("充值异常交易写入本地Excel失败", e);
        }
        log.info("充值异常记录写入excel成功，当前机构：" + orgCode);
    }

    private void writeExcelTransfer(List<Transorderinfo> paidOutResult, List<TitanTransferReq> localPayOutResult, String orgCode) {
        log.info("转账异常记录写入excel开始，当前机构：" + orgCode);
        Integer startRow = 0;
        String[] titleList = {"商户号", "收款方", "订单号/转账单ID",
                "转账请求号", "转账金额", "创建时间", "转账状态"};
        try {
            File excelFile = new File(filePath);
            XSSFWorkbook xwb;
            if (!excelFile.exists()) {
                xwb = new XSSFWorkbook();
                createSheetTitle(xwb, excelFile, "paidOutResult", titleList);
            } else {
                xwb = new XSSFWorkbook(new FileInputStream(excelFile));
                Sheet refundSheet = xwb.getSheet("paidOutResult");
                if (null != refundSheet) {
                    startRow = refundSheet.getLastRowNum();
                } else {
                    createSheetTitle(xwb, excelFile, "paidOutResult", titleList);
                }
            }
            xwb = new XSSFWorkbook(new FileInputStream(excelFile));
            Sheet refundSheet = xwb.getSheet("paidOutResult");
            for (int i = 0, j = localPayOutResult.size(); i < j; i++) {
                Row row = refundSheet.createRow(++startRow);
                row.createCell(0).setCellValue(orgCode);//付款方
                row.createCell(1).setCellValue(localPayOutResult.get(i).getUserrelateid());//收款方
                row.createCell(2).setCellValue(localPayOutResult.get(i).getTransferreqid());
                row.createCell(3).setCellValue(localPayOutResult.get(i).getRequestno());
                row.createCell(4).setCellValue(localPayOutResult.get(i).getAmount());
                row.createCell(5).setCellValue(null != localPayOutResult.get(i).getCreatetime() ?
                        DateUtil.dateToString(localPayOutResult.get(i).getCreatetime(), "yyyy-MM-dd HH:mm:ss") : "");
                row.createCell(6).setCellValue(String.valueOf(localPayOutResult.get(i).getStatus()));
            }

            for (int i = 0, j = paidOutResult.size(); i < j; i++) {
                Row row = refundSheet.createRow(++startRow);
                row.createCell(0).setCellValue(orgCode);
                row.createCell(1).setCellValue(paidOutResult.get(i).getOrderpackageno());//此值应该暂时拿不到
                row.createCell(2).setCellValue(paidOutResult.get(i).getOrderno());
                row.createCell(3).setCellValue(paidOutResult.get(i).getRequestno());
                row.createCell(4).setCellValue(paidOutResult.get(i).getAmount());
                row.createCell(5).setCellValue(paidOutResult.get(i).getCreatedtime());
                row.createCell(6).setCellValue(paidOutResult.get(i).getOrderstatus());
            }

            OutputStream stream = new FileOutputStream(excelFile);
            //写入数据
            xwb.write(stream);
            //关闭文件流
            stream.close();
        } catch (IOException e) {
            log.error("转账异常写入本地Excel失败", e);
        }
        log.info("转账异常记录写入excel成功，当前机构：" + orgCode);
    }

    private void writeExcelWithDraw(List<Transorderinfo> withDrawResult, List<TitanWithDrawReq> localWithDrawResult, String orgCode) {
        log.info("提现异常记录写入excel开始，当前机构：" + orgCode);
        Integer startRow = 0;
        String[] titleList = {"商户号", "订单号/提现单ID",
                "提现请求号", "提现金额", "创建时间", "提现状态"};
        try {
            File excelFile = new File(filePath);
            XSSFWorkbook xwb;
            if (!excelFile.exists()) {
                xwb = new XSSFWorkbook();
                createSheetTitle(xwb, excelFile, "withDrawResult", titleList);
            } else {
                xwb = new XSSFWorkbook(new FileInputStream(excelFile));
                Sheet refundSheet = xwb.getSheet("withDrawResult");
                if (null != refundSheet) {
                    startRow = refundSheet.getLastRowNum();
                } else {
                    createSheetTitle(xwb, excelFile, "withDrawResult", titleList);
                }
            }
            xwb = new XSSFWorkbook(new FileInputStream(excelFile));
            Sheet refundSheet = xwb.getSheet("withDrawResult");
            for (int i = 0, j = localWithDrawResult.size(); i < j; i++) {
                Row row = refundSheet.createRow(++startRow);
                row.createCell(0).setCellValue(orgCode);
                row.createCell(1).setCellValue(localWithDrawResult.get(i).getWithdrawreqid());
                row.createCell(2).setCellValue(localWithDrawResult.get(i).getUserorderid());
                row.createCell(3).setCellValue(localWithDrawResult.get(i).getAmount());
                row.createCell(4).setCellValue(null != localWithDrawResult.get(i).getCreatetime() ?
                        DateUtil.dateToString(localWithDrawResult.get(i).getCreatetime(), "yyyy-MM-dd HH:mm:ss") : "");
                row.createCell(5).setCellValue(String.valueOf(localWithDrawResult.get(i).getStatus()));
            }

            for (int i = 0, j = withDrawResult.size(); i < j; i++) {
                Row row = refundSheet.createRow(++startRow);
                row.createCell(0).setCellValue(orgCode);
                row.createCell(1).setCellValue(withDrawResult.get(i).getOrderno());
                row.createCell(2).setCellValue(withDrawResult.get(i).getRequestno());
                row.createCell(3).setCellValue(withDrawResult.get(i).getAmount());
                row.createCell(4).setCellValue(withDrawResult.get(i).getCreatedtime());
                row.createCell(5).setCellValue(withDrawResult.get(i).getOrderstatus());
            }

            OutputStream stream = new FileOutputStream(excelFile);
            //写入数据
            xwb.write(stream);
            //关闭文件流
            stream.close();
        } catch (IOException e) {
            log.error("提现异常写入本地Excel失败", e);
        }
        log.info("提现异常记录写入excel成功，当前机构：" + orgCode);
    }

    private void writeExcelRefund(List<RefundDTO> localRefundResult, List<NotifyRefundResponse> refundResponseList, String orgCode) {
        log.info("退款异常记录写入excel开始，当前机构：" + orgCode);
        Integer startRow = 0;
        String[] titleList = {"商户号", "订单号",
                "退款单号", "退款金额", "创建时间", "退款状态"
        };
        try {
            File excelFile = new File(filePath);
            XSSFWorkbook xwb;
            if (!excelFile.exists()) {
                xwb = new XSSFWorkbook();
                createSheetTitle(xwb, excelFile, "refundResult", titleList);
            } else {
                xwb = new XSSFWorkbook(new FileInputStream(excelFile));
                Sheet refundSheet = xwb.getSheet("refundResult");
                if (null != refundSheet) {
                    startRow = refundSheet.getLastRowNum();
                } else {
                    createSheetTitle(xwb, excelFile, "refundResult", titleList);
                }
            }
            xwb = new XSSFWorkbook(new FileInputStream(excelFile));
            Sheet refundSheet = xwb.getSheet("refundResult");
            for (int i = 0, j = localRefundResult.size(); i < j; i++) {
                Row row = refundSheet.createRow(++startRow);
                row.createCell(0).setCellValue(orgCode);
                row.createCell(1).setCellValue(localRefundResult.get(i).getOrderNo());
                row.createCell(2).setCellValue(localRefundResult.get(i).getRefundOrderno());
                row.createCell(3).setCellValue(localRefundResult.get(i).getRefundAmount());
                row.createCell(4).setCellValue(null != localRefundResult.get(i).getCreatetime() ?
                        DateUtil.dateToString(localRefundResult.get(i).getCreatetime(), "yyyy-MM-dd HH:mm:ss") : "");
                row.createCell(5).setCellValue(String.valueOf(localRefundResult.get(i).getStatus()));
            }

            for (int i = 0, j = refundResponseList.size(); i < j; i++) {
                Row row = refundSheet.createRow(++startRow);
                row.createCell(0).setCellValue(orgCode);
                row.createCell(1).setCellValue(refundResponseList.get(i).getOrderNo());
                row.createCell(2).setCellValue(refundResponseList.get(i).getRefundOrderno());
                row.createCell(3).setCellValue(refundResponseList.get(i).getOrderAmount());
                row.createCell(4).setCellValue(refundResponseList.get(i).getOrderTime());
                row.createCell(5).setCellValue(refundResponseList.get(i).getRefundStatus());
            }

            OutputStream stream = new FileOutputStream(excelFile);
            //写入数据
            xwb.write(stream);
            //关闭文件流
            stream.close();
        } catch (IOException e) {
            log.error("退款异常写入本地Excel失败", e);
        }
        log.info("退款异常记录写入excel开始，当前机构：" + orgCode);
    }

    /**
     * 去融数下退款单操作
     * 此方法可以修改成根据失败的退款单，创建新的退款单，同时重新发起一次退款
     *
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
     *
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
