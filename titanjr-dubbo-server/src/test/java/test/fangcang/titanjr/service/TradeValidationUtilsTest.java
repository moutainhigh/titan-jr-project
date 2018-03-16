package test.fangcang.titanjr.service;

import com.Rop.api.ApiException;
import com.Rop.api.domain.Transorderinfo;
import com.alibaba.fastjson.JSONObject;
import com.fangcang.merchant.enums.VersionEnum;
import com.fangcang.titanjr.common.util.SMSTemplate;
import com.fangcang.titanjr.dao.TitanRefundDao;
import com.fangcang.titanjr.dao.TitanWithDrawReqDao;
import com.fangcang.titanjr.dto.bean.BankCardDTO;
import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.dto.request.BankCardRequest;
import com.fangcang.titanjr.dto.request.NotifyRefundRequest;
import com.fangcang.titanjr.dto.response.NotifyRefundResponse;
import com.fangcang.titanjr.dto.response.RefundOrderResponse;
import com.fangcang.titanjr.entity.TitanWithDrawReq;
import com.fangcang.titanjr.entity.parameter.TitanWithDrawReqParam;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.enums.SignTypeEnum;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.fangcang.titanjr.util.TradeValidationUtils;
import com.fangcang.util.DateUtil;
import com.fangcang.util.StringUtil;
import com.titanjr.fop.api.DefaultFopClient;
import com.titanjr.fop.constants.WithDrawChannelEnum;
import com.titanjr.fop.request.WheatfieldOrderServiceWithdrawserviceRequest;
import com.titanjr.fop.response.WheatfieldOrderServiceWithdrawserviceResponse;
import com.titanjr.fop.util.WebUtils;
import org.apache.commons.collections.CollectionUtils;
import org.junit.Before;
import org.junit.Test;
import test.fangcang.titanjr.SpringTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhaoshan on 2017/11/24.
 */
public class TradeValidationUtilsTest extends SpringTest {

    @Resource
    TradeValidationUtils validationUtils;

    @Resource
    TitanRefundDao titanRefundDao;

    @Resource
    TitanWithDrawReqDao titanWithDrawReqDao;

    @Resource
    TitanFinancialBankCardService titanFinancialBankCardService;

    private static String ropUrl = "http://local.fangcang.com:8090/titan-fop-server/";
    private static String appKey = "93A6626A-C082-4D25-B496-EA9CC6E90EDB";
    private static String appSecret = "6461B23C-3ABE-4BE2-8E2C-D3FF4B2F5415";
    protected DefaultFopClient fopClient = null;

    @Before
    public void initFopClient() {
        fopClient = new DefaultFopClient(ropUrl, appKey, appSecret);
    }

    @Test
    public void testWithDrawFix() {
        //已处理订单
        //TJR180309182035738  OP20180309182037003  2018-03-09 18:20:37  TJM10000744
        //TJR180309144940671  OP20180309144942002  2018-03-09 14:49:43  TJMS10027842
        //TJR180309175100993  OP20180309175102002  2018-03-09 17:51:02  TJM10000014
        //

        TitanWithDrawReqParam reqParam = new TitanWithDrawReqParam();
        reqParam.setUserorderid("TJR180309175100993");
        List<TitanWithDrawReq> withDrawReqs = titanWithDrawReqDao.queryList(reqParam);

        System.out.println(withDrawReqs.size());
        TitanWithDrawReq withDrawReq = withDrawReqs.get(0);

        WheatfieldOrderServiceWithdrawserviceRequest withdrawserviceRequest = new WheatfieldOrderServiceWithdrawserviceRequest();
        withdrawserviceRequest.setAmount(String.valueOf(withDrawReq.getAmount()));
        withdrawserviceRequest.setUserid(withDrawReq.getUserid());
        withdrawserviceRequest.setMerchantcode("M000016");
        withdrawserviceRequest.setCardno(withDrawReq.getBankcode());
        withdrawserviceRequest.setOrderdate(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        withdrawserviceRequest.setProductid("P000070");
        withdrawserviceRequest.setUserfee(0L);
        withdrawserviceRequest.setUserorderid(reqParam.getUserorderid());

        //查询绑卡信息 1：结算卡，2：其他卡, 3：提现卡, 4：结算提现一体卡)
        BankCardRequest bankCardRequest = new BankCardRequest();
        bankCardRequest.setUserId(withdrawserviceRequest.getUserid());
        List<BankCardDTO> bankCardDTOList = titanFinancialBankCardService.queryBankCardDTO(bankCardRequest);
        BankCardDTO cardDTO = null;
        for (BankCardDTO bankCardDTO : bankCardDTOList) {
            if (StringUtil.isValidString(withdrawserviceRequest.getCardno()) &&//传了卡号一定需要有相同卡
                    bankCardDTO.getAccountnumber().equals(withdrawserviceRequest.getCardno())) {
                cardDTO = bankCardDTO;
                break;
            }
            if (bankCardDTO.getAccountpurpose().equals("3") || bankCardDTO.getAccountpurpose().equals("1")
                    || bankCardDTO.getAccountpurpose().equals("4")) {
                cardDTO = bankCardDTO;
                break;
            }
        }
        //如果只有一张提现卡就用当前的卡
        if (cardDTO == null && CollectionUtils.isNotEmpty(bankCardDTOList) && bankCardDTOList.size() ==1){
            cardDTO = bankCardDTOList.get(0);
        }

        if (cardDTO == null) {
            logger.error("体现卡信息异常，无法继续操作，当前单号：" + reqParam.getUserorderid());
            return;
        }

        String paymentURL = "http://local.fangcang.com:8090/checkstand/payment.shtml";
//        paymentURL = "http://192.168.0.14:8090/checkstand/payment.shtml";
        //发起代付 返回状态
        //对应于TLAgentTradeRequest对象
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("merchantNo", "M000016");
        paramMap.put("orderNo", withdrawserviceRequest.getUserorderid() + "A");//客户单号
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
        //若提现渠道有设置则使用设置的
        if (withdrawserviceRequest.getWithDrawChannel().equals(WithDrawChannelEnum.RB_CHANNEL)) {
            paramMap.put("tradeCode", "300001");
        }
        try {
            //查询网关真实状态
            String rechargeResult = WebUtils.doPost(paymentURL, paramMap, 60000, 60000);
            Map resultMap = (Map) JSONObject.parse(rechargeResult);
            //1:"处理中",2:"失败",3:"成功"
            if (resultMap.get("errCode") != null || resultMap.get("status") == null) {
                logger.error("调用返回结果失败");
            }
            System.out.println(resultMap.get("status").toString());
        } catch (Exception e) {
            logger.error("发起提现的代付申请失败", e);
        }

    }

    @Test
    public void testValidOrgTradeInfo() {
        Date start = DateUtil.stringToDate("2018-02-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.stringToDate("2018-03-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        try {
            Map<String, List> result = validationUtils.validOrgTradeInfo(start, end, "141223100000056");
            System.out.println(result);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidRechargeOrder() {
        Date start = DateUtil.stringToDate("2018-02-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.stringToDate("2018-03-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        try {
            Map<String, List> result = validationUtils.validRechargeOrder(start, end, "141223100000056");
            System.out.println(result);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidTransferOrder() {
        Date start = DateUtil.stringToDate("2018-02-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.stringToDate("2018-03-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        try {
            Map<String, List> result = validationUtils.validTransferOrder(start, end, "141223100000056");
            System.out.println(result);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidWithDrawOrder() {
        Date start = DateUtil.stringToDate("2018-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.stringToDate("2018-03-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        try {
            Map<String, List> result = validationUtils.validWithDrawOrder(start, end, "TJM10000016");
        } catch (ApiException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testValidRefundOrder() {//验证退款
        Date start = DateUtil.stringToDate("2015-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.stringToDate("2018-03-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        try {
            Map<String, List> refundDTOList = validationUtils.validRefundOrder(start, end, "141223100000056");
            System.out.println(refundDTOList);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFixRefundFailOrder() {
        RefundDTO refundDTO = new RefundDTO();
        refundDTO.setRefundAmount("477100");
        refundDTO.setOrderNo("2017111016225100004");
        refundDTO.setUserOrderId("TJO171110162226260");
        refundDTO.setOrderTime("20171110162251");
        refundDTO.setVersion("v1.0");
//        RefundOrderResponse refundOrderResponse =  validationUtils.fixRefundFailOrder(refundDTO);

        NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
        notifyRefundRequest.setBusiCode(BusiCodeEnum.QueryRefund.getKey());
        notifyRefundRequest.setMerchantNo("M000016");
        notifyRefundRequest.setOrderNo("2017112713031500004");
        notifyRefundRequest.setRefundAmount("101700");
        //DateUtil.dateToString(DateUtil.stringToDate("2017-04-06 15:23:48","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss")
        notifyRefundRequest.setOrderTime("20171127130315");
        notifyRefundRequest.setRefundOrderno("OD20171201101923006");//OD20170502092341001,OD20170505142021001 ，OD2017060210124100122
        notifyRefundRequest.setVersion("v1.0");
        notifyRefundRequest.setSignType(SignTypeEnum.MD5.getKey());
//        NotifyRefundResponse notifyRefundResponse = validationUtils.notifyGatewayRefund(notifyRefundRequest);

    }

    @Test
    public void testQueryRefundList() {
        RefundDTO refundDTO = new RefundDTO();
        refundDTO.setOrderNo("2017112713031500004");
//        List<RefundDTO>  list = titanRefundDao.queryRefundDTO(refundDTO);
//        System.out.println(list);
    }

    @Test
    public void testQueryNotifyRefund() {
        NotifyRefundRequest notifyRefundRequest = new NotifyRefundRequest();
        notifyRefundRequest.setBusiCode(BusiCodeEnum.QueryRefund.getKey());
        notifyRefundRequest.setMerchantNo("M000016");
        notifyRefundRequest.setOrderNo("2017112713031500004");
        notifyRefundRequest.setRefundAmount("101700");
        //DateUtil.dateToString(DateUtil.stringToDate("2017-04-06 15:23:48","yyyy-MM-dd HH:mm:ss"),"yyyy-MM-dd HH:mm:ss")
        notifyRefundRequest.setOrderTime("20171127130315");
        notifyRefundRequest.setRefundOrderno("OD20171127130817008");//OD20170502092341001,OD20170505142021001 ，OD2017060210124100122
        notifyRefundRequest.setVersion("v1.1");
        notifyRefundRequest.setSignType(SignTypeEnum.MD5.getKey());
//        NotifyRefundResponse notifyRefundResponse = validationUtils.notifyGatewayRefund(notifyRefundRequest);
//        System.out.println(notifyRefundResponse);
    }
}
