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
