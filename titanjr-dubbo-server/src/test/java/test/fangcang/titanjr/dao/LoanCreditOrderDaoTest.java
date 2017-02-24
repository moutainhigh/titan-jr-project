package test.fangcang.titanjr.dao;

import com.fangcang.titanjr.dao.LoanCreditCompanyDao;
import com.fangcang.titanjr.dao.LoanCreditOrderDao;
import com.fangcang.titanjr.entity.LoanCreditCompany;
import com.fangcang.titanjr.entity.LoanCreditOrder;
import com.fangcang.util.DateUtil;
import org.junit.Test;
import test.fangcang.titanjr.SpringTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2016/11/16.
 */
public class LoanCreditOrderDaoTest extends SpringTest {
    @Resource
    LoanCreditOrderDao loanCreditOrderDao;

    @Test
    public void testSaveCreditOrder() {
        LoanCreditOrder loanCreditOrder = new LoanCreditOrder();
        loanCreditOrder.setDayLimit(90);
        loanCreditOrder.setAmount(1000000L);
        loanCreditOrder.setActualAmount(0l);
        loanCreditOrder.setReqTime(new Date());
        loanCreditOrder.setRateTem("RT0001");
        loanCreditOrder.setRspId("P000230");
        loanCreditOrder.setRsorgId("TJM0987833");
        loanCreditOrder.setCreateTime(new Date());
        loanCreditOrder.setUrlKey("usdf4648");
        loanCreditOrder.setStatus(1);
        loanCreditOrder.setAssureType(1);
        loanCreditOrder.setFirstAuditTime(new Date());
        loanCreditOrder.setLastAuditTime(new Date());
        loanCreditOrder.setAuditPass(new Date());
        loanCreditOrder.setReqJson("{json}");
        loanCreditOrder.setOrgCode("===");
        loanCreditOrder.setOrderNo("CR00021456");
        loanCreditOrderDao.saveLoanCreditOrder(loanCreditOrder);
    }

    @Test
    public void testQueryCreditOrder() {
        LoanCreditOrder loanCreditOrder = new LoanCreditOrder();
        loanCreditOrder.setRsorgId("TJM0987832");
        loanCreditOrder.setStatus(1);
        loanCreditOrder.setOrderNo("CR00021455");
        List<LoanCreditOrder> list = loanCreditOrderDao.queryLoanCreditOrder(loanCreditOrder);
        System.out.println(list);
    }

    @Test
    public void testUpdateCreditOrder() {
        LoanCreditOrder loanCreditOrder = new LoanCreditOrder();
        loanCreditOrder.setDayLimit(9022);
        loanCreditOrder.setAmount(1000022L);
//        loanCreditOrder.setActualAmount(022);
        loanCreditOrder.setReqTime(DateUtil.stringToDate("2016-01-01"));
        loanCreditOrder.setRateTem("RT00022");
        loanCreditOrder.setRspId("P0002322");
        loanCreditOrder.setRsorgId("TJM098722");
        loanCreditOrder.setCreateTime(DateUtil.stringToDate("2016-01-01"));
        loanCreditOrder.setUrlKey("usdf464822");
        loanCreditOrder.setStatus(2);
        loanCreditOrder.setAssureType(2);
        loanCreditOrder.setFirstAuditTime(DateUtil.stringToDate("2016-01-01"));
        loanCreditOrder.setLastAuditTime(DateUtil.stringToDate("2016-01-01"));
        loanCreditOrder.setAuditPass(DateUtil.stringToDate("2016-01-01"));
        loanCreditOrder.setReqJson("{json22}");
        loanCreditOrder.setOrderNo("CR00021455");
        loanCreditOrderDao.updateLoanCreditOrder(loanCreditOrder);
    }
}
