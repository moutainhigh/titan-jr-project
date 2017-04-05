package test.fangcang.pay;

import com.Rop.api.DefaultRopClient;
import com.Rop.api.request.WheatfieldOrderOperRequest;
import com.Rop.api.response.WheatfieldOrderOperResponse;
import com.fangcang.titanjr.common.util.HttpUtils;
import com.fangcang.titanjr.common.util.MD5;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.*;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class RSPayInvokeTest {

    private static String ropUrl = "https://testapi.open.ruixuesoft.com:30005/ropapi";
    private static String appKey = "F1A95B5E-3485-4CEB-8036-F2B9EC53EF65";
    private static String appSecret = "8B6E8EEF-48CC-4CCF-94C6-55C4AA2FE9F2";


    static DefaultRopClient ropClient = new DefaultRopClient(ropUrl, appKey,
            appSecret, "xml");

    private static String sessionKey = "1460355562856409835";

    public static void main(String[] args) {
//        createOrder();//==2016042517392700001  对应于  H0544878456346770329
        getPayPage();
    }

    private static String createOrder(){

        String strError = null;
        try {
            WheatfieldOrderOperRequest req = new WheatfieldOrderOperRequest();
            // 必输项
            req.setUserid("PM10000021");								// 接入机构中设置的用户ID
            req.setConstid("M000016");							// 机构码
			req.setOrdertypeid("B1");							// 基础业务为B1，扩展业务待定 M70001棉庄订金支付
            req.setProductid("P000070");						// 产品号
            req.setOpertype("1");								// 操作类型（修改：2,新增：1,取消4,查询3）
			req.setOrderdate(new Date());						// 订单日期
			req.setOrdertime(new Date());						// 订单时间
            req.setUserorderid("H0544878456346770329");							// 用户订单编号
			req.setAmount("200");								// 订单金额（若不存在商品概念则必填）
			req.setGoodsname("香港迪士尼酒店预订");							// 商品名称
			req.setGoodsdetail("预订3天2晚，5.11入住，5.14离店，已预付款");							// 商品描述
			req.setNumber(0);									// 商品数量
			req.setUnitprice(null);								// 单价
			req.setAdjusttype(null);							// 调整类型
			req.setAdjustcontent("");						// 调整内容
			req.setUserrelateid(null);							// 关联用户id（若有第三方则必须填写）

            WheatfieldOrderOperResponse rsp = ropClient.execute(req, sessionKey);
            if (rsp != null) {
                System.out.println("返回报文: \n" + rsp.getBody());
                if (rsp.isSuccess() != true) {
                    if (rsp.getSubMsg() != null && rsp.getSubMsg() != "") {
                        strError = rsp.getSubMsg();
                    } else {
                        strError = rsp.getMsg();
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "error";
        } finally {
        }
        return strError;
    }


    private static String getPayPage(){
        String sigStr = "merchantNo=M000016&orderNo=2016042517392700001&" +
                "orderAmount=200&payType=1&" +
                "orderTime=20160425173927&signType=1&" +
                "version=v1.0&key=12356780Poi)(*";
        String signatureStr = null;
        try {
            signatureStr = MD5.MD5Encode(sigStr, "UTF-8");
        } catch (Exception e) {
        }
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("merchantNo","M000016");
        paraMap.put("orderNo","2016042517392700001");
        paraMap.put("productNo","");
        paraMap.put("productName","充值订单");
        paraMap.put("productDesc","测试充值请求订单");
        paraMap.put("productNum","2");
        paraMap.put("orderAmount","200");
        paraMap.put("payType","1");
        paraMap.put("amtType","01");
        paraMap.put("bankInfo","");//间连可为空
        paraMap.put("payerAcount","");
        paraMap.put("payerName","");
        paraMap.put("payerPhone","");
        paraMap.put("payerMail","");
        paraMap.put("pageUrl","www.fangcang.com");
        paraMap.put("notifyUrl","www.fangcang.com");
        paraMap.put("orderTime","20160425173927");
        paraMap.put("orderExpireTime","");
        paraMap.put("orderMark","1");
        paraMap.put("expand","");
        paraMap.put("expand2","");
        paraMap.put("signType","1");
        paraMap.put("busiCode","101");//商户订单
        paraMap.put("version","v1.0");
        paraMap.put("charset","1");
        paraMap.put("signMsg", signatureStr);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost post = HttpUtils.buildPostForm("http://devrsjf.rongcapital.com.cn:8486/checkstand/payment", paraMap);
            HttpResponse response = httpclient.execute(post);
            String resultStr = EntityUtils.toString(response.getEntity());
            System.out.println(resultStr);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void getStatus(){
        String sigStr = "signType=1&version=v1.0&merchantNo=M000016&" +
                "orderNo=2016041114451400001&orderTime=20160411191733" +
                "&key=12356780Poi)(*";
        String signatureStr = null;
        try {
            signatureStr = MD5.MD5Encode(sigStr, "UTF-8");
        } catch (Exception e) {

        }
        Map<String,String> paraMap = new HashMap<String, String>();
        paraMap.put("busiCode","102");
        paraMap.put("signType","1");
        paraMap.put("version","v1.0");
        paraMap.put("merchantNo","M000016");
        paraMap.put("orderNo","2016041114451400001");
        paraMap.put("orderTime","20160411191733");
        paraMap.put("signMsg", signatureStr);
        try {
            DefaultHttpClient httpclient = new DefaultHttpClient();
            HttpPost post = HttpUtils.buildPostForm("http://devrsjf.rongcapital.com.cn:8486/checkstand/payment", paraMap);
            HttpResponse response = httpclient.execute(post);
            String resultStr = EntityUtils.toString(response.getEntity());
            System.out.println(resultStr);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   /* static void Main(String[] args)
    {
        //用于字符串和byte[]之间的互转
        UTF8Encoding utf8encoder = new UTF8Encoding();

        //产生一对公钥私钥
        RSACryptoServiceProvider rsaKeyGenerator = new RSACryptoServiceProvider(1024);
        string publickey = rsaKeyGenerator.ToXmlString(false);
        string privatekey = rsaKeyGenerator.ToXmlString(true);
                
        //使用公钥加密密码
        RSACryptoServiceProvider rsaToEncrypt = new RSACryptoServiceProvider();
        rsaToEncrypt.FromXmlString(publickey);
        string strPassword = "@123#abc$";
        Console.WriteLine("The original password is: {0}", strPassword);
        byte[] byEncrypted = rsaToEncrypt.Encrypt(utf8encoder.GetBytes(strPassword), false);
        Console.Write("Encoded bytes: ");
        foreach (Byte b in byEncrypted)
        {
            Console.Write("{0}", b.ToString("X"));
        }
        Console.Write("\n");
        Console.WriteLine("The encrypted code length is: {0}", byEncrypted.Length);

        //解密
        RSACryptoServiceProvider rsaToDecrypt = new RSACryptoServiceProvider();
        rsaToDecrypt.FromXmlString(privatekey);
        byte[] byDecrypted = rsaToDecrypt.Decrypt(byEncrypted, false);
        string strDecryptedPwd = utf8encoder.GetString(byDecrypted);
        Console.WriteLine("Decrypted Password is: {0}", strDecryptedPwd);
    }*/

}
