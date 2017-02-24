package test.fangcang.titanjr.dao;

import com.fangcang.titanjr.dao.LoanCreditCompanyDao;
import com.fangcang.titanjr.entity.LoanCreditCompany;
import com.fangcang.util.DateUtil;
import org.junit.Test;
import test.fangcang.titanjr.SpringTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2016/11/5.
 */
public class LoanCreditCompanyDaoTest  extends SpringTest {

    @Resource
    LoanCreditCompanyDao loanCreditCompanyDao;

    @Test
    public void testSaveCompanyEnsure() {
        LoanCreditCompany loanCreditCompany = new LoanCreditCompany();
//        loanCreditCompany.setCreditorderNo("CR001");
        loanCreditCompany.setName("测试公司");
        loanCreditCompany.setStartDate(DateUtil.getDate(new Date(), -20));
        loanCreditCompany.setRegAddress("深圳市龙华新区");
        loanCreditCompany.setOfficeAddress("福田区时代金融中心");
        loanCreditCompany.setOrgSize(5);
        loanCreditCompany.setLicense("L100220445487434");
        loanCreditCompany.setTaxRegNo("T45782321");
        loanCreditCompany.setOrgCode("O1215454522");
        loanCreditCompany.setRegAccount("ceshi001");
        loanCreditCompany.setRegDate(DateUtil.getDate(new Date(), -16));
        loanCreditCompany.setEmpSize(1548);
        loanCreditCompany.setLegalName("法人A1");
        loanCreditCompany.setLegalceType(1);
        loanCreditCompany.setLegalNo("439214195806255986");
        loanCreditCompany.setContactName("联系人A1");
        loanCreditCompany.setContactPhone("18800000000");
        loanCreditCompany.setWaterEmail("shanglv@rongshu.com");
        loanCreditCompany.setAppendInfo("{data}");
        loanCreditCompany.setLicenseUrl("setLicenseUrl");
        loanCreditCompany.setLegalNoUrl("setLegalNoUrl");
        loanCreditCompany.setOfficeNoUrl("setOfficeNoUrl");
        loanCreditCompany.setAccountUrl("setAccountUrl");
        loanCreditCompany.setCreditUrl("setCreditUrl");
        loanCreditCompany.setOfficeUrl("setOfficeUrl");
        loanCreditCompany.setWaterUrl("setWaterUrl");
        loanCreditCompany.setIsPush(0);
        loanCreditCompanyDao.saveCreditCompany(loanCreditCompany);
    }

    @Test
    public void testQueryCompanyEnsure() {
        LoanCreditCompany loanCreditCompany = new LoanCreditCompany();
        //loanCreditCompany.setCreditorderNo("CR001");
        List<LoanCreditCompany> list = loanCreditCompanyDao.queryLoanCreditCompany(loanCreditCompany);
        System.out.println(list);
    }

    @Test
    public void testUpdateCompanyEnsure() {
        LoanCreditCompany loanCreditCompany = new LoanCreditCompany();
//        loanCreditCompany.setCreditorderNo("CR001");
        loanCreditCompany.setName("测试公司-");
        loanCreditCompany.setStartDate(DateUtil.getDate(new Date(), -10));
        loanCreditCompany.setRegAddress("深圳市龙华新区-");
        loanCreditCompany.setOfficeAddress("福田区时代金融中心-");
        loanCreditCompany.setOrgSize(4);
        loanCreditCompany.setLicense("L100220445487434-");
        loanCreditCompany.setTaxRegNo("T45782321-");
        loanCreditCompany.setOrgCode("O1215454522-");
        loanCreditCompany.setRegAccount("ceshi001-");
        loanCreditCompany.setRegDate(DateUtil.getDate(new Date(), -16));
        loanCreditCompany.setEmpSize(1549);
        loanCreditCompany.setLegalName("法人A1-");
        loanCreditCompany.setLegalceType(1);
        loanCreditCompany.setLegalNo("439214195806255986-");
        loanCreditCompany.setContactName("联系人A1-");
        loanCreditCompany.setContactPhone("18800000000-");
        loanCreditCompany.setWaterEmail("shanglv@rongshu.com-");
        loanCreditCompany.setAppendInfo("{data}-");
        loanCreditCompany.setLicenseUrl("setLicenseUrl-");
        loanCreditCompany.setLegalNoUrl("setLegalNoUrl-");
        loanCreditCompany.setOfficeNoUrl("setOfficeNoUrl-");
        loanCreditCompany.setAccountUrl("setAccountUrl-");
        loanCreditCompany.setCreditUrl("setCreditUrl-");
        loanCreditCompany.setOfficeUrl("setOfficeUrl-");
        loanCreditCompany.setWaterUrl("setWaterUrl-");
        loanCreditCompany.setIsPush(1);
        loanCreditCompanyDao.updateCreditCompany(loanCreditCompany);
    }
}
