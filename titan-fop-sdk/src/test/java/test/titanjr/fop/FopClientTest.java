package test.titanjr.fop;

import com.fangcang.util.DateUtil;
import com.titanjr.fop.api.DefaultFopClient;
import com.titanjr.fop.exceptions.ApiException;
import com.titanjr.fop.request.*;
import com.titanjr.fop.response.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhaoshan on 2017/12/20.
 */
public class FopClientTest extends BaseTest{

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
        getlistRequest.setUserid("TJM60024870");
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
        authcodeserviceRequest.setOrderno("2017122511384300001");//测试校验
        authcodeserviceRequest.setIntermerchantcode(null);
        authcodeserviceRequest.setOrdercount(1);
        authcodeserviceRequest.setProductid("P000070");
        authcodeserviceRequest.setPaychannelid(null);
        authcodeserviceRequest.setRequestno("tjrf180125102507005");
        authcodeserviceRequest.setStatus(2);
        authcodeserviceRequest.setFunccode("40171");
        authcodeserviceRequest.setMerchantcode("M000016");
        authcodeserviceRequest.setOrderamount(10l);
        authcodeserviceRequest.setOrderdate(null);
        authcodeserviceRequest.setErrorcode(null);
        authcodeserviceRequest.setBusitypeid(null);
        authcodeserviceRequest.setBankcode(null);
        authcodeserviceRequest.setConditioncode("2");
        authcodeserviceRequest.setOrderpackageno(null);
        authcodeserviceRequest.setRemark("测试单");
        authcodeserviceRequest.setUserid("TJM60024852");
        authcodeserviceRequest.setFeeamount(null);
        authcodeserviceRequest.setAmount(10L);
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
        thawauthcodeRequest.setAmount("30");
        thawauthcodeRequest.setAuthcode("s0BF9ddOdw5");
        thawauthcodeRequest.setFrozenuserorderid(null);
        thawauthcodeRequest.setProductid("P000070");
        thawauthcodeRequest.setRequestno("tjrf180125102507005");
        thawauthcodeRequest.setRequesttime(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
        try {
            WheatfieldOrderServiceThawauthcodeResponse response = fopClient.execute(thawauthcodeRequest, "1514456720670432636");
            System.out.println(response.getRetmsg());
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAccountTransfer(){
        WheatfieldOrderTransferRequest transferRequest = new WheatfieldOrderTransferRequest();
        transferRequest.setRequesttime(DateUtil.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
        transferRequest.setRequestno("OP2017012218302500002");
        transferRequest.setProductid("P000070");
        transferRequest.setAmount("20");
        transferRequest.setConditioncode("3");
        transferRequest.setIntermerchantcode("M1000016");
        transferRequest.setInterproductid("P000070");
        transferRequest.setMerchantcode("M1000016");
        transferRequest.setTransfertype("1");
        transferRequest.setUserfee("0");
        transferRequest.setUserid("TJM60024852");
        transferRequest.setUserrelateid("TJM60024870");
        try {
            WheatfieldOrderTransferResponse transferResponse = fopClient.execute(transferRequest,"1514456720670432636");
            System.out.println(transferResponse);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAccountWithDraw(){
        WheatfieldOrderServiceWithdrawserviceRequest withdrawserviceRequest = new WheatfieldOrderServiceWithdrawserviceRequest();
        withdrawserviceRequest.setAmount("1");
        withdrawserviceRequest.setUserid("TJMS60021833");
        withdrawserviceRequest.setMerchantcode("M000016");
        withdrawserviceRequest.setCardno("6226096555675449");
        withdrawserviceRequest.setOrderdate(DateUtil.dateToString(new Date(),"yyyy-MM-dd HH:mm:ss"));
        withdrawserviceRequest.setProductid("P000070");
        withdrawserviceRequest.setUserfee(0L);
        withdrawserviceRequest.setUserorderid("TJR180130114310693");
        try {
            WheatfieldOrderServiceWithdrawserviceResponse withdrawserviceResponse = fopClient.execute(withdrawserviceRequest,"1514456720670432636");
            System.out.println(withdrawserviceResponse);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("http://192.168.0.14:8090/titan-fop-server/fopapi.shtml");
    }
}
