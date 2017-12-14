package test.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.request.CashierDeskInitRequest;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.dto.response.CashierDeskResponse;
import com.fangcang.titanjr.service.TitanCashierDeskService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import test.fangcang.titanjr.SpringTest;

/**
 * Created by zhaoshan on 2016/5/18.
 */
public class TitanCashierDeskServiceTest extends SpringTest {

    /*@Resource
    TitanCashierDeskService titanCashierDeskService;*/
	@Autowired
    private ApplicationContext context;

    @Test
    public void initCashierDeskTest() {
    	//通过应用上下文bean，获取业务bean
    	TitanCashierDeskService titanCashierDeskService = (TitanCashierDeskService)context.getBean("titanCashierDeskService");
        
        CashierDeskInitRequest cashierDeskInitRequest = new CashierDeskInitRequest();
        cashierDeskInitRequest.setUserId("TJM10009993");
        cashierDeskInitRequest.setConstId("M000016");
        cashierDeskInitRequest.setOperator("system");
        try {
            titanCashierDeskService.initCashierDesk(cashierDeskInitRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryCashierDeskTest(){
    	//通过应用上下文bean，获取业务bean
    	TitanCashierDeskService titanCashierDeskService = (TitanCashierDeskService)context.getBean("titanCashierDeskService");
        CashierDeskQueryRequest request =  new CashierDeskQueryRequest();
        request.setUserId("TJM10000087");
//        request.setUsedFor(1);
        CashierDeskResponse response =  titanCashierDeskService.queryCashierDesk(request);
        System.out.println(response);
    }
}
