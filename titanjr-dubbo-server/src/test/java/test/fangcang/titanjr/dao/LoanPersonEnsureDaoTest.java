package test.fangcang.titanjr.dao;

import com.fangcang.titanjr.dao.LoanPersonEnsureDao;
import com.fangcang.titanjr.entity.LoanPersonEnsure;
import com.fangcang.util.DateUtil;
import org.junit.Test;
import test.fangcang.titanjr.SpringTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2016/11/16.
 */
public class LoanPersonEnsureDaoTest extends SpringTest {
    @Resource
    LoanPersonEnsureDao loanPersonEnsureDao;

    @Test
    public void testSavePersonEnsure() {
        LoanPersonEnsure loanPersonEnsure = new LoanPersonEnsure();
        loanPersonEnsure.setOrderNo("CR00021455");
        loanPersonEnsure.setPersonName("李思思");
        loanPersonEnsure.setNationalIdentityNumber("439201196802151025");
        loanPersonEnsure.setMobileNumber("18600000000");
        loanPersonEnsure.setMarriageStatus(2);
        loanPersonEnsure.setHaveChildren(1);
        loanPersonEnsure.setNativePlace("格尔木");
        loanPersonEnsure.setCurrentLiveAddress("广州");
        loanPersonEnsure.setGraduateSchool("西藏民族大学");
        loanPersonEnsure.setHighestEducation(6);
        loanPersonEnsure.setYearsWorking(16);
        loanPersonEnsure.setWorkCompany("创新科技");
        loanPersonEnsure.setOccupation("总监");
        loanPersonEnsure.setWorkTelephone("095680145687");
        loanPersonEnsure.setOfficeAddress("广州番禺");
        loanPersonEnsure.setIndustry("人工智能");
        loanPersonEnsure.setCarPropertyType(1);
        loanPersonEnsure.setHousePropertyType(2);
        loanPersonEnsure.setOtherProperty("百万股票");
        loanPersonEnsure.setPropertyRemark("有钱");
        loanPersonEnsure.setFirstContactName("二百五1");
        loanPersonEnsure.setFirstContactPhone("13000000000");
        loanPersonEnsure.setRelationToGuarantee1(4);
        loanPersonEnsure.setSecondContactName("二百五2");
        loanPersonEnsure.setSecondContactPhone("14000000000");
        loanPersonEnsure.setRelationToGuarantee2(5);
        loanPersonEnsure.setIdCardUrl("http1");
        loanPersonEnsure.setRegisteredUrl("http2");
        loanPersonEnsure.setSpouseRegisteredUrl("http3");
        loanPersonEnsure.setSpouseIdCardUrl("http4");
        loanPersonEnsure.setMarriageUrl("http5");
        loanPersonEnsureDao.saveLoanPersonEnsure(loanPersonEnsure);
    }

    @Test
    public void testQueryPersonEnsure() {
        LoanPersonEnsure loanPersonEnsure = new LoanPersonEnsure();
        loanPersonEnsure.setOrderNo("CR00021455");
        List<LoanPersonEnsure> list = loanPersonEnsureDao.queryLoanPersonEnsure(loanPersonEnsure);
        System.out.println(list);
    }

    @Test
    public void testUpdatePersonEnsure() {
        LoanPersonEnsure loanPersonEnsure = new LoanPersonEnsure();
        loanPersonEnsure.setOrderNo("CR00021455");
        loanPersonEnsure.setPersonName("李思思2");
        loanPersonEnsure.setNationalIdentityNumber("4392011968021510252");
        loanPersonEnsure.setMobileNumber("186000000002");
        loanPersonEnsure.setMarriageStatus(3);
        loanPersonEnsure.setHaveChildren(2);
        loanPersonEnsure.setNativePlace("格尔木2");
        loanPersonEnsure.setCurrentLiveAddress("广州2");
        loanPersonEnsure.setGraduateSchool("西藏民族大学2");
        loanPersonEnsure.setHighestEducation(7);
        loanPersonEnsure.setYearsWorking(162);
        loanPersonEnsure.setWorkCompany("创新科技2");
        loanPersonEnsure.setOccupation("总监2");
        loanPersonEnsure.setWorkTelephone("0956801456872");
        loanPersonEnsure.setOfficeAddress("广州番禺2");
        loanPersonEnsure.setIndustry("人工智能2");
        loanPersonEnsure.setCarPropertyType(2);
        loanPersonEnsure.setHousePropertyType(3);
        loanPersonEnsure.setOtherProperty("百万股票2");
        loanPersonEnsure.setPropertyRemark("有钱2");
        loanPersonEnsure.setFirstContactName("二百五12");
        loanPersonEnsure.setFirstContactPhone("130000000002");
        loanPersonEnsure.setRelationToGuarantee1(5);
        loanPersonEnsure.setSecondContactName("二百五22");
        loanPersonEnsure.setSecondContactPhone("140000000002");
        loanPersonEnsure.setRelationToGuarantee2(6);
        loanPersonEnsure.setIdCardUrl("http12");
        loanPersonEnsure.setRegisteredUrl("http22");
        loanPersonEnsure.setSpouseRegisteredUrl("http32");
        loanPersonEnsure.setSpouseIdCardUrl("http42");
        loanPersonEnsure.setMarriageUrl("http52");
        loanPersonEnsureDao.updateLoanPersonEnsure(loanPersonEnsure);
    }
}
