package test.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.request.CashierDeskInitRequest;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.dto.response.CashierDeskResponse;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import org.junit.Test;
import test.fangcang.titanjr.SpringTest;

import javax.annotation.Resource;

/**
 * Created by zhaoshan on 2016/5/18.
 */
public class TitanCashierDeskServiceTest extends SpringTest {

    @Resource
    TitanCashierDeskService titanCashierDeskService;

    @Test
    public void initCashierDeskTest() {
        CashierDeskInitRequest cashierDeskInitRequest = new CashierDeskInitRequest();
        cashierDeskInitRequest.setUserId("TJM10000110");
        cashierDeskInitRequest.setConstId("M000016");
        cashierDeskInitRequest.setOperator("system");
        try {
            titanCashierDeskService.initCashierDesk(cashierDeskInitRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    @Test
    public void queryCashierDeskTest(){
        CashierDeskQueryRequest request =  new CashierDeskQueryRequest();
        request.setUserId("TJM10000087");
//        request.setUsedFor(1);
        CashierDeskResponse response =  titanCashierDeskService.queryCashierDesk(request);
        System.out.println(response);
    }
}
