package test.fangcang.titanjr.dao;

import com.fangcang.titanjr.dao.LoanCreditOpinionDao;
import com.fangcang.titanjr.entity.LoanCreditOpinion;
import com.fangcang.util.DateUtil;
import org.junit.Test;
import test.fangcang.titanjr.SpringTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2016/11/16.
 */
public class LoanCreditOpinionDaoTest extends SpringTest {
    @Resource
    LoanCreditOpinionDao loanCreditOpinionDao;

    @Test
    public void testSaveCreditOpinion() {
        LoanCreditOpinion loanCreditOpinion = new LoanCreditOpinion();
        loanCreditOpinion.setContent("营业执照不合法等等");
        loanCreditOpinion.setCreater("zhangsan");
        loanCreditOpinion.setResult(1);
        loanCreditOpinion.setCreateTime(new Date());
        loanCreditOpinion.setStatus(1);
        loanCreditOpinion.setOrderNo("CR00021456");
        loanCreditOpinionDao.saveLoanCreditOpinion(loanCreditOpinion);
    }

    @Test
    public void testQueryCreditOpinion() {
        LoanCreditOpinion loanCreditOpinion = new LoanCreditOpinion();
        List<LoanCreditOpinion> list = loanCreditOpinionDao.queryLoanCreditOpinion(loanCreditOpinion);
        System.out.println(list);
    }

    @Test
    public void testUpdateCreditOpinion() {
        LoanCreditOpinion loanCreditOpinion = new LoanCreditOpinion();
        loanCreditOpinion.setContent("营业执照不合法等等22");
        loanCreditOpinion.setCreater("zhangsan002");
        loanCreditOpinion.setResult(2);
        loanCreditOpinion.setCreateTime(DateUtil.stringToDate("2016-01-01"));
        loanCreditOpinion.setStatus(2);
        loanCreditOpinion.setOrderNo("CR00021456");
        loanCreditOpinionDao.updateLoanCreditOpinion(loanCreditOpinion);
    }
}
