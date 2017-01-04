package test.fangcang.titanjr.dao;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import test.fangcang.titanjr.SpringTest;

import com.fangcang.titanjr.entity.LoanRoomPackSpec;
import com.fangcang.util.DateUtil;

/**
 * Created by zhaoshan on 2016/11/16.
 */
public class LoanRoomPackSpecDaoTest extends SpringTest {
    //@Resource
   // LoanRoomPackSpecDao loanRoomPackSpecDao;

    @Test
    public void testSaveRoomPackSpec() {
        LoanRoomPackSpec loanRoomPackSpec = new LoanRoomPackSpec();
        loanRoomPackSpec.setOrderNo("LA0254657");
        loanRoomPackSpec.setAccount("6205002654009620005");
        loanRoomPackSpec.setAccountName("测试公司");
        loanRoomPackSpec.setBank("中国工商银行");
        loanRoomPackSpec.setBeginDate(new Date());
        loanRoomPackSpec.setContractUrl("http001");
        loanRoomPackSpec.setEndDate(new Date());
        loanRoomPackSpec.setHotelName("珠海长隆酒店");
        loanRoomPackSpec.setRoomNights(500);
       // loanRoomPackSpecDao.saveLoanRoomPaockSpec(loanRoomPackSpec);
    }

    @Test
    public void testQueryRoomPackSpec() {
        LoanRoomPackSpec loanRoomPackSpec = new LoanRoomPackSpec();
        loanRoomPackSpec.setOrderNo("LA0254657");
       // List<LoanRoomPackSpec> list = loanRoomPackSpecDao.queryLoanRoomPackSpec(loanRoomPackSpec);
        //System.out.println(list);
    }

    @Test
    public void testUpdateRoomPackSpec() {
        LoanRoomPackSpec loanRoomPackSpec = new LoanRoomPackSpec();
        loanRoomPackSpec.setOrderNo("LA0254657");
        loanRoomPackSpec.setAccount("6205002654009620005222");
        loanRoomPackSpec.setAccountName("测试公司222");
        loanRoomPackSpec.setBank("中国工商银行22");
        loanRoomPackSpec.setBeginDate(DateUtil.stringToDate("2016-01-01"));
        loanRoomPackSpec.setContractUrl("http00122");
        loanRoomPackSpec.setEndDate(DateUtil.stringToDate("2016-01-01"));
        loanRoomPackSpec.setHotelName("珠海长隆酒店22");
        loanRoomPackSpec.setRoomNights(5002);
       // loanRoomPackSpecDao.updateLoanRoomPackSpec(loanRoomPackSpec);
    }
}
