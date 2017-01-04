package test.fangcang.titanjr.dao;

import com.fangcang.titanjr.dao.LoanOrderDao;
import com.fangcang.titanjr.entity.LoanApplyOrder;
import com.fangcang.util.DateUtil;
import org.junit.Test;
import test.fangcang.titanjr.SpringTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2016/11/16.
 */
public class LoanOrderDaoTest extends SpringTest {
    @Resource
    LoanOrderDao loanOrderDao;

    @Test
    public void testSaveApplyOrder() {
        LoanApplyOrder loanApplyOrder = new LoanApplyOrder();
        loanApplyOrder.setOrderNo("LA0254657");
        loanApplyOrder.setCreditOrderNo("CR00021456");
        loanApplyOrder.setOrgCode("TJM0987833");
        loanApplyOrder.setAmount(100000L);
        loanApplyOrder.setActualAmount(800000L);
        loanApplyOrder.setRate(0.0005f);
        loanApplyOrder.setRspId("P000230");
        loanApplyOrder.setRsorgId("TJM0987833");
        loanApplyOrder.setProductType(1);
        loanApplyOrder.setProductId("P000230");
        loanApplyOrder.setCreateTime(new Date());
        loanApplyOrder.setRateTmp("RT01345");
        loanApplyOrder.setStatus(1);
        loanApplyOrder.setErrorMsg("mmmm");
        loanApplyOrder.setRelMoneyTime(new Date());
        loanApplyOrder.setProductSpecId("154541");
        loanApplyOrder.setRepaymentType(1);
        loanApplyOrder.setActualRepaymentDate(new Date());
        loanApplyOrder.setLastRepaymentDate(new Date());
        loanApplyOrder.setRepaymentPrincipal(600000L);
        loanApplyOrder.setRepaymentInterest(20000L);
        loanApplyOrder.setShouldCapital(60000L);
        loanApplyOrder.setShouldInterest(20000L);

        loanOrderDao.saveLoanApplyOrder(loanApplyOrder);
    }

    @Test
    public void testQueryApplyOrder() {
        LoanApplyOrder loanApplyOrder = new LoanApplyOrder();
        loanApplyOrder.setOrderNo("LA0254657");
       // List<LoanApplyOrder> list = loanOrderDao.queryLoanApplyOrder(loanApplyOrder);
      //  System.out.println(list);
    }

    @Test
    public void testUpdateApplyOrder() {
        LoanApplyOrder loanApplyOrder = new LoanApplyOrder();
        loanApplyOrder.setOrderNo("LA0254657");
        loanApplyOrder.setCreditOrderNo("CR0002145622");
        loanApplyOrder.setOrgCode("TJM098783322");
        loanApplyOrder.setAmount(10000022L);
        loanApplyOrder.setActualAmount(80000022L);
        loanApplyOrder.setRate(0.00052f);
        loanApplyOrder.setRspId("P00023022");
        loanApplyOrder.setRsorgId("TJM098783322");
        loanApplyOrder.setProductType(2);
        loanApplyOrder.setProductId("P00023022");
        loanApplyOrder.setCreateTime(DateUtil.stringToDate("2016-01-01"));
        loanApplyOrder.setRateTmp("RT0134522");
        loanApplyOrder.setStatus(2);
        loanApplyOrder.setErrorMsg("mmmm22");
        loanApplyOrder.setRelMoneyTime(DateUtil.stringToDate("2016-01-01"));
        loanApplyOrder.setProductSpecId("15454122");
        loanApplyOrder.setRepaymentType(2);
        loanApplyOrder.setActualRepaymentDate(DateUtil.stringToDate("2016-01-01"));
        loanApplyOrder.setLastRepaymentDate(DateUtil.stringToDate("2016-01-01"));
        loanApplyOrder.setRepaymentPrincipal(6000002L);
        loanApplyOrder.setRepaymentInterest(200002L);
        loanApplyOrder.setShouldCapital(600002L);
        loanApplyOrder.setShouldInterest(200002L);
        loanOrderDao.updateLoanApplyOrder(loanApplyOrder);
    }
}
