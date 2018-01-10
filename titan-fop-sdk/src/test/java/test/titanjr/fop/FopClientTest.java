package test.titanjr.fop;

import com.fangcang.util.DateUtil;
import com.titanjr.fop.api.DefaultFopClient;
import com.titanjr.fop.exceptions.ApiException;
import com.titanjr.fop.request.*;
import com.titanjr.fop.response.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * Created by zhaoshan on 2017/12/20.
 */
public class FopClientTest {

    private static String ropUrl = "https://open.fangcang.com:19010/titan-fop-server";
    private static String appKey = "93A6626A-C082-4D25-B496-EA9CC6E90EDB";
    private static String appSecret = "DC368712-18A4-4290-9A58-FF995DC161DC";
    private DefaultFopClient fopClient = null;

    @Before
    public void initFopClient() {
        fopClient = new DefaultFopClient(ropUrl, appKey, appSecret);
    }

    @Test
    public void testGetSession() {

        ExternalSessionGetRequest sessionGetReq = new ExternalSessionGetRequest();
        sessionGetReq.setExtParam("扩展参数");
        try {
            ExternalSessionGetResponse sessionGetRsp = fopClient.execute(sessionGetReq);
            System.out.println(sessionGetRsp.getSession());
        } catch (ApiException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    @Test
    public void testGetBalanceInfo() {

        WheatfieldBalanceGetlistRequest getlistRequest = new WheatfieldBalanceGetlistRequest();
        getlistRequest.setUserid("TJM10000118");
        getlistRequest.setRootinstcd("M000016");
        try {
            WheatfieldBalanceGetlistResponse getlistResponse = fopClient.execute(getlistRequest, "1514456720670432636");
            System.out.println(getlistResponse.getShbalanceinfos());
        } catch (ApiException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }

    @Test
    public void testOperOrder() {
        WheatfieldOrderOperRequest operRequest = new WheatfieldOrderOperRequest();
        operRequest.setAdjustcontent(null);
        operRequest.setAdjusttype(null);
        operRequest.setAmount("100");
        operRequest.setConstid("M000016");
        operRequest.setGoodsdetail("测试商品详情-入住离店日期");
        operRequest.setGoodsname("测试商品");
        operRequest.setNumber(1);
        operRequest.setOpertype("1");
        operRequest.setOrderdate(DateUtil.stringToDate("2018-01-08 10:21:34", "yyyy-MM-dd HH:mm:ss"));
        operRequest.setOrdertime(DateUtil.stringToDate("2018-01-08 10:21:34", "yyyy-MM-dd HH:mm:ss"));
        operRequest.setOrdertypeid("B");
        operRequest.setProductid("P000070");
        operRequest.setRemark("remark");
        operRequest.setUnitprice("100");
        operRequest.setUserid("TJM10000118");
        operRequest.setUserorderid("TJO180108102134572");
        operRequest.setUserrelateid("TJM10000001");
        try {
            WheatfieldOrderOperResponse response = fopClient.execute(operRequest, "1514456720670432636");
            System.out.println(response.getOrderid());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testOperRefundOrder() {
        WheatfieldOrderServiceReturngoodsRequest returngoodsRequest = new WheatfieldOrderServiceReturngoodsRequest();
        returngoodsRequest.setOrderid("2018010415564500001");
        returngoodsRequest.setOrderitemid("12432534");
        returngoodsRequest.setAmount("100");
        returngoodsRequest.setUserorderid("TJO180104155632150");
        try {
            WheatfieldOrderServiceReturngoodsResponse response = fopClient.execute(returngoodsRequest, "1514456720670432636");
            System.out.println(response.getBatchno());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFreezeOrder() {
        WheatfieldOrderServiceAuthcodeserviceRequest authcodeserviceRequest = new WheatfieldOrderServiceAuthcodeserviceRequest();
        authcodeserviceRequest.setOrderno("2018010415564500001");
        authcodeserviceRequest.setIntermerchantcode(null);
        authcodeserviceRequest.setOrdercount(1);
        authcodeserviceRequest.setProductid("P000070");
        authcodeserviceRequest.setPaychannelid(null);
        authcodeserviceRequest.setRequestno("tjrf180104155707005");
        authcodeserviceRequest.setStatus(2);
        authcodeserviceRequest.setFunccode("40171");
        authcodeserviceRequest.setMerchantcode("M000016");
        authcodeserviceRequest.setOrderamount(100l);
        authcodeserviceRequest.setOrderdate(null);
        authcodeserviceRequest.setErrorcode(null);
        authcodeserviceRequest.setBusitypeid(null);
        authcodeserviceRequest.setBankcode(null);
        authcodeserviceRequest.setConditioncode("2");
        authcodeserviceRequest.setOrderpackageno(null);
        authcodeserviceRequest.setRemark("测试单");
        authcodeserviceRequest.setUserid("TJM60024852");
        authcodeserviceRequest.setFeeamount(null);
        authcodeserviceRequest.setAmount(100L);
        authcodeserviceRequest.setReferuserid(null);
        authcodeserviceRequest.setProfit(null);
        authcodeserviceRequest.setTradeflowno(null);
        authcodeserviceRequest.setUseripaddress("218.17.13.199");
        authcodeserviceRequest.setUserfee(0l);
        authcodeserviceRequest.setRequesttime(new Date());
        try {
            WheatfieldOrderServiceAuthcodeserviceResponse response = fopClient.execute(authcodeserviceRequest, "1514456720670432636");
            System.out.println(response.getAuthcode());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUnFreezeOrder() {
        WheatfieldOrderServiceThawauthcodeRequest thawauthcodeRequest = new WheatfieldOrderServiceThawauthcodeRequest();
        thawauthcodeRequest.setRootinstcd("M000016");
        thawauthcodeRequest.setUserid("TJM60024852");
        thawauthcodeRequest.setConditioncode("2");
        thawauthcodeRequest.setAmount("100");
        thawauthcodeRequest.setAuthcode("RngUiny6jA");
        thawauthcodeRequest.setFrozenuserorderid(null);
        thawauthcodeRequest.setProductid("P000070");
        thawauthcodeRequest.setRequestno("tjrf180104155707005");
        thawauthcodeRequest.setRequesttime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        try {
            WheatfieldOrderServiceThawauthcodeResponse response = fopClient.execute(thawauthcodeRequest, "1514456720670432636");
            System.out.println(response.getRetmsg());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

}
