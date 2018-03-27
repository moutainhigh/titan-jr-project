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
//        TJR180309182035738  OP20180309182037003  2018-03-09 18:20:37  TJM10000744
//        TJR180309144940671  OP20180309144942002  2018-03-09 14:49:43  TJMS10027842
//        TJR180309175100993  OP20180309175102002  2018-03-09 17:51:02  TJM10000014
//        TJR180309165541231  OP20180309165542004  2018-03-09 16:55:43  TJM10000666
//        TJR180309162838948  OP20180309162839004  2018-03-09 16:28:40  TJM10000010(测试单，处理失败户名校验不一致)
//        TJR180309125024324  OP20180309125025002  2018-03-09 12:50:26  TJM10000088
//        TJR180309230244762  OP20180309230246003  2018-03-09 23:02:46  TJM10000010（测试单不处理）
//        TJR180312195049366  OP20180312195051001  2018-03-12 19:50:51  TJM10000022
//        TJR180312104531926  OP20180312104533002  2018-03-12 10:45:33  TJM10000014
//        TJR180312180933552  OP20180312180934002  2018-03-12 18:09:35  TJM10000014
//        TJR180312092548519  OP20180312092550002  2018-03-12 09:25:50  TJM10000276
//        TJR180312092528741  OP20180312092529002  2018-03-12 09:25:30  TJM10000276
//        TJR180312185542212  OP20180312185544004  2018-03-12 18:55:44  TJM10000744
//        TJR180312174445874  OP20180312174447002  2018-03-12 17:44:47  TJM10000744
//        TJR180312182301509  OP20180312182302003  2018-03-12 18:23:03  TJM10000098
//        TJR180313092828381  OP20180313092830002  2018-03-13 09:28:31  TJMS10027884
//        TJR180313172217079  OP20180313172219004  2018-03-13 17:22:20  TJMS10027884
//        TJR180313220437224  OP20180313220439002  2018-03-13 22:04:39  TJM10000014
//        TJR180313220912032  OP20180313220914002  2018-03-13 22:09:15  TJM10000014
//        TJR180314190017692  OP20180314190018007  2018-03-14 19:00:19  TJM10000014
//        TJR180314165204003  OP20180314165206003  2018-03-14 16:52:07  TJMS10027884
//        TJR180314092652263  OP20180314092654002  2018-03-14 09:26:54  TJM10000276
//        TJR180314110658287  OP20180314110659002  2018-03-14 11:07:00  TJM10000014
//        TJR180314172844805  OP20180314172846001  2018-03-14 17:28:46  TJM10000744
//        TJR180315175908395  OP20180315175909002  2018-03-15 17:59:10  TJM10000744
//        TJR180316164110309  OP20180316164112004  2018-03-16 16:41:13  TJMS10027884
//        TJR180316163953243  OP20180316163955002  2018-03-16 16:39:56  TJMS10027884
//        TJR180316172725575  OP20180316172726005  2018-03-16 17:27:27  TJM10000022
//        TJR180317121946555  OP20180317121948002  2018-03-17 12:19:49  TJM10000068

        //========================2018-03-22新增处理================================
        //TJR180319160342115  OP20180319160343002  2018-03-19 16:03:44  TJM10000022
        //TJR180319140208669  OP20180319140210002  2018-03-19 14:02:11  TJM10000098
        //TJR180319115928527  OP20180319115930002  2018-03-19 11:59:31  TJMS10027842
        //TJR180319101253470  OP20180319101255004  2018-03-19 10:12:56  TJMS10027884
        //TJR180319181655605  OP20180319181656002  2018-03-19 18:16:57  TJM10000744
        //TJR180319205539440  OP20180319205540002  2018-03-19 20:55:41  TJM10000666
        //TJR180319125057637  OP20180319125059002  2018-03-19 12:50:59  TJM10000432
        //TJR180320104139746  OP20180320104141003  2018-03-20 10:41:41  TJM10000546
        //TJR180320104126934  OP20180320104127002  2018-03-20 10:41:28  TJM10000546
        //TJR180320104112761  OP20180320104114002  2018-03-20 10:41:14  TJM10000546
        //TJR180320104100764  OP20180320104101002  2018-03-20 10:41:02  TJM10000546
        //TJR180320104046820  OP20180320104047003  2018-03-20 10:40:48  TJM10000546
        //TJR180320104031114  OP20180320104032002  2018-03-20 10:40:33  TJM10000546
        //TJR180320104017637  OP20180320104019002  2018-03-20 10:40:20  TJM10000546
        //TJR180320104002419  OP20180320104003002  2018-03-20 10:40:04  TJM10000546
        //TJR180320103948703  OP20180320103949002  2018-03-20 10:39:50  TJM10000546
        //TJR180320103934423  OP20180320103936002  2018-03-20 10:39:36  TJM10000546
        //TJR180320103920404  OP20180320103922002  2018-03-20 10:39:22  TJM10000546
        //TJR180320103907519  OP20180320103909002  2018-03-20 10:39:09  TJM10000546
        //TJR180320103855793  OP20180320103857002  2018-03-20 10:38:57  TJM10000546
        //TJR180320092620133  OP20180320092622002  2018-03-20 09:26:23  TJM10000014
        //TJR180320164917031  OP20180320164918002  2018-03-20 16:49:19  TJM10000276

        //
        //TJR180321134415695  OP20180321134416002  2018-03-21 13:44:17  TJM10000540
        //TJR180321103357746  OP20180321103359002  2018-03-21 10:34:00  TJM10000440
        //TJR180321143513319  OP20180321143514002  2018-03-21 14:35:15  TJM10000014
        //TJR180321170954136  OP20180321170957002  2018-03-21 17:09:58  TJMS10027884
        //TJR180321183154904  OP20180321183156002  2018-03-21 18:31:56  TJM10000744

        //
        //TJR180322095806016  OP20180322095807002  2018-03-22 09:58:08  TJM10000022
        //TJR180322170212034  OP20180322170214002  2018-03-22 17:02:14  TJM10000022
        //TJR180322213413385  OP20180322213415002  2018-03-22 21:34:16  TJM10000744

        //
        //TJR180323101159295  OP20180323101201002  2018-03-23 10:12:01  TJM10000068
        //TJR180323153052245  OP20180323153053002  2018-03-23 15:30:54  TJM10000192
        //TJR180323202451712  OP20180323202452002  2018-03-23 20:24:53  TJM10000010(测试交易)
        //TJR180323231015754  OP20180323231017002  2018-03-23 23:10:18  TJM10000014

        //
        //TJR180324144111852  OP20180324144112002  2018-03-24 14:41:13  TJM10000022
        //TJR180324163653568  OP20180324163654002  2018-03-24 16:36:55  TJM10000546
        //TJR180324234356827  OP20180324234357002  2018-03-24 23:43:58  TJM10000014

        //
        //TJR180326100414782  OP20180326100416002  2018-03-26 10:04:17  TJMS10027884
        //TJR180326115137044  OP20180326115139002  2018-03-26 11:51:40  TJMS10028180
        //TJR180326184707152  OP20180326184709002  2018-03-26 18:47:09  TJM10000744
        //TJR180326164845974  OP20180326164848002  2018-03-26 16:48:48  TJMS10027842
        //TJR180326173346505  OP20180326173348002  2018-03-26 17:33:48  TJM10000014
        //TJR180326120307425  OP20180326120308002  2018-03-26 12:03:09  TJM10000060
        //TJR180326092717625  OP20180326092719002  2018-03-26 09:27:19  TJM10000098
        //TJR180326141507237  OP20180326141508002  2018-03-26 14:15:09  TJM10000666


        //execFixProcess("TJR180326141507237");
    }

    private void execFixProcess(String userOrderId) {
        TitanWithDrawReqParam reqParam = new TitanWithDrawReqParam();
        reqParam.setUserorderid(userOrderId);
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
        bankCardRequest.setStatus(1);
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
        if (cardDTO == null && CollectionUtils.isNotEmpty(bankCardDTOList) && bankCardDTOList.size() == 1) {
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
        paramMap.put("orderNo", withdrawserviceRequest.getUserorderid());//客户单号
        paramMap.put("tradeCode", "100014");
        //paramMap.put("submitTime", DateUtil.dateToString(new Date(), "yyyyMMddHHmmss"));//在checkstand处理，通联和融宝要求格式不同
        paramMap.put("bankInfo", cardDTO.getBankcode());
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
//        paramMap.put("idCode", cardDTO.getCertificatenumnumber());
//        paramMap.put("idType", "5");//设法区分身份证和回乡证getCertificatetype 不一定准
        paramMap.put("province",cardDTO.getBankprovince());
        paramMap.put("city",cardDTO.getBankcity());

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
    public void testValidAccountAmount() {
        try {
            boolean result = validationUtils.validAccountAmount("TJM10000016");
            System.out.println(result);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testValidOrgTradeInfo() {
        Date start = DateUtil.stringToDate("2015-02-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
        Date end = DateUtil.stringToDate("2018-03-31 23:59:59", "yyyy-MM-dd HH:mm:ss");
        try {
            Map<String, List> result = validationUtils.validOrgTradeInfo(start, end, "TJM10000016");
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
