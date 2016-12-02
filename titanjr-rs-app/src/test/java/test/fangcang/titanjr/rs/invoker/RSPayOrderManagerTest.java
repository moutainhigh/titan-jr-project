package test.fangcang.titanjr.rs.invoker;

import org.junit.Before;
import org.junit.Test;

import com.fangcang.titanjr.rs.dto.RSPayResultInfo;
import com.fangcang.titanjr.rs.manager.RSPayOrderManager;
import com.fangcang.titanjr.rs.request.RSPayOrderRequest;
import com.fangcang.titanjr.rs.response.PayResultResponse;
import com.fangcang.titanjr.rs.response.RSPayOrderResponse;

import test.fangcang.titanjr.rs.base.GenericTest;

public class RSPayOrderManagerTest extends GenericTest{
	  private static RSPayOrderManager rsPayOrderManager = null;

	    @Before
	    public void init(){
	    	rsPayOrderManager = (RSPayOrderManager)cfx.getBean("rsPayOrderManager");
	    }
	    
	    //间连支付
//	    @Test  
	    public void testGetPayPage() throws Exception{
	    	RSPayOrderRequest req = new RSPayOrderRequest();
	    	
	    	req.setMerchantNo("M000016");
//	    	2016042814532700001
	    	req.setOrderNo("2016051609544600001");
	    	req.setProductNo("");
	    	req.setProductName("充值订单");
	    	req.setProductDesc("测试充值请求订单");
	    	req.setProductNum("1");
	    	req.setOrderAmount("120000");
//	    	req.setPayType("1");
	    	req.setAmtType("01");
	    	req.setBankInfo("");
	    	req.setPayerAcount("");
	    	req.setPayerName("");
	    	req.setPayerPhone("");
	    	req.setPayerMail("");
	    	req.setPageUrl("www.fangcang.com");
	        req.setNotifyUrl("www.fangcang.com");
	        req.setOrderTime("20160429173327");
	        req.setOrderExpireTime("");
	        req.setOrderMark("1");
	        req.setExpand("");
	        req.setExpand2("");
	        req.setSignType("1");
	        req.setBusiCode("101");
	        req.setVersion("v1.0");
	        req.setCharset("1");
	        RSPayOrderResponse response =  rsPayOrderManager.getPayPage(req);
	        System.out.println(response.getPayPage());
	    }
	    
	    //直联支付
	    @Test
	    public void testGetPayBankPage() throws Exception{
	    	RSPayOrderRequest req = new RSPayOrderRequest();
	    	req.setMerchantNo("M000016");
	    	req.setOrderNo("2016060714313800001");
	    	req.setProductNo("");
	    	req.setProductName("充值订单");
	    	req.setProductDesc("测试充值请求订单");
	    	req.setProductNum("1");
	    	req.setOrderAmount("10000");
	    	req.setPayType("11");
	    	req.setAmtType("01");
	    	req.setBankInfo("icbc");
	    	req.setPayerAcount("6222021817003110653");
	    	req.setPayerName("方代康");
	    	req.setPayerPhone("");
	    	req.setPayerMail("");
	    	req.setPageUrl("www.fangcang.com");
	        req.setNotifyUrl("www.fangcang.com");
	        req.setOrderTime("20160607143138");
	        req.setOrderExpireTime("45");
	        req.setOrderMark("1");
	        req.setExpand("");
	        req.setExpand2("");
	        req.setSignType("1");
	        req.setBusiCode("101");
	        req.setVersion("v1.0");
	        req.setCharset("1");
	        RSPayOrderResponse response =  rsPayOrderManager.getPayPage(req);
	        System.out.println(response.getPayPage());
	    }
	    
	    //支付结果查询
//	    @Test
	    public void testPayResult() throws Exception{
	    	RSPayOrderRequest req = new RSPayOrderRequest();
	    	req.setMerchantNo("M000016");
	    	req.setOrderNo("2016050315532400001");
	        req.setOrderTime("20160503155324");
	        req.setSignType("1");
	        req.setBusiCode("102");
	        req.setVersion("v1.0");
	        PayResultResponse response =  rsPayOrderManager.queryPayResult(req);
	        RSPayResultInfo rsPayResultInfo = response.getRsRayResultInfo();
	        if(rsPayResultInfo !=null){
	        	System.out.println(rsPayResultInfo.getMerchantNo()+"---"+rsPayResultInfo.getOrderAmount());
	        	System.out.println(rsPayResultInfo.getSignMsg());
	        }
	        
	    }
	    
//	    @Test
	    public void testNotify(){
	    	RSPayOrderRequest req = new RSPayOrderRequest();
	    	req.setMerchantNo("M000016");
	    	req.setOrderNo("2016042914253000001");
	    	req.setPayOrderNo("153sdsdds");
	        req.setOrderTime("20160429142530");
	        req.setOrderAmount("1200");
	        req.setPayStatus("3");
	        req.setPayType("1");
	        req.setSignType("1");
	        req.setBusiCode("102");
	        req.setVersion("v1.0");
	        String msg = rsPayOrderManager.notifyResult(req);
	        System.out.println(msg);
	    }
}
