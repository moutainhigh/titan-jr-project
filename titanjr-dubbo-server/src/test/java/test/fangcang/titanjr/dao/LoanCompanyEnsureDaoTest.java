package test.fangcang.titanjr.dao;

import com.fangcang.titanjr.dao.LoanCompanyEnsureDao;
import com.fangcang.titanjr.entity.LoanCompanyEnsure;
import com.fangcang.util.DateUtil;
import org.junit.Test;
import test.fangcang.titanjr.SpringTest;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2016/11/4.
 */
public class LoanCompanyEnsureDaoTest extends SpringTest {

    @Resource
     LoanCompanyEnsureDao loanCompanyEnsureDao;

    @Test
    public void testSaveCompanyEnsure() {
        LoanCompanyEnsure loanCompanyEnsure = new LoanCompanyEnsure();
//        loanCompanyEnsure.setUserId("TJM000088");//哪个机构的担保
        loanCompanyEnsure.setCompanyName("测试担保公司名称");
        loanCompanyEnsure.setFoundDate(DateUtil.getDate(new Date(), -18));
        loanCompanyEnsure.setEnterpriseScale(3);
        loanCompanyEnsure.setBusinessLicense("L400025578445");
        loanCompanyEnsure.setTaxRegisterCode("R12003487846456");
        loanCompanyEnsure.setOrgCodeCertificate("C47006548621");
        loanCompanyEnsure.setRegisterAccount("zhangsan123456");
        loanCompanyEnsure.setRegisterDate(DateUtil.getDate(new Date(), -13));
        loanCompanyEnsure.setLegalPersonName("法人02");
        loanCompanyEnsure.setLegalPersonCertificateType(1);
        loanCompanyEnsure.setLegalPersonCertificateNumber("370154198312154878");
        loanCompanyEnsure.setContactName("联系人02");
        loanCompanyEnsure.setContactPhone("18600000000");
        loanCompanyEnsure.setBusinessLicenseUrl("http://LicenseUrl2");
        loanCompanyEnsure.setOrgCodeCertificateUrl("http://CodeCertificateUrl2");
        loanCompanyEnsure.setTaxRegisterCodeUrl("http://RegisterCodeUrl");
        loanCompanyEnsure.setLicenseUrl("http://LicenseUrl");
        loanCompanyEnsure.setLegalPersonUrl("http://LegalPersonUrl");
        loanCompanyEnsureDao.saveCompanyEnsure(loanCompanyEnsure);
    }

    @Test
    public void testQueryLoanCompanyEnsure(){
        LoanCompanyEnsure loanCompanyEnsure = new LoanCompanyEnsure();
        List<LoanCompanyEnsure> loanCompanyEnsures = loanCompanyEnsureDao.queryLoanCompanyEnsure(loanCompanyEnsure);
        System.out.println(loanCompanyEnsures);
    }

    @Test
    public void testUpdateLoanCompanyEnsure(){
        LoanCompanyEnsure loanCompanyEnsure = new LoanCompanyEnsure();
//        loanCompanyEnsure.setUserId("TJM000088");//哪个机构的担保
        loanCompanyEnsure.setCompanyName("测试担保公司名称003");
        loanCompanyEnsure.setFoundDate(DateUtil.getDate(new Date(), -17));
        loanCompanyEnsure.setEnterpriseScale(4);
        loanCompanyEnsure.setBusinessLicense("L4000255784451");
        loanCompanyEnsure.setTaxRegisterCode("R120034878464561");
        loanCompanyEnsure.setOrgCodeCertificate("C470065486211");
        loanCompanyEnsure.setRegisterAccount("zhangsan1234561");
        loanCompanyEnsure.setRegisterDate(DateUtil.getDate(new Date(), -11));
        loanCompanyEnsure.setLegalPersonName("法人021");
        loanCompanyEnsure.setLegalPersonCertificateType(1);
        loanCompanyEnsure.setLegalPersonCertificateNumber("370154198312154879");
        loanCompanyEnsure.setContactName("联系人03");
        loanCompanyEnsure.setContactPhone("18600000001");
        loanCompanyEnsure.setBusinessLicenseUrl("http://LicenseUrl21");
        loanCompanyEnsure.setOrgCodeCertificateUrl("http://CodeCertificateUrl21");
        loanCompanyEnsure.setTaxRegisterCodeUrl("http://RegisterCodeUrl1");
        loanCompanyEnsure.setLicenseUrl("http://LicenseUrl1");
        loanCompanyEnsure.setLegalPersonUrl("http://LegalPersonUrl1");
        int result = loanCompanyEnsureDao.updateCompanyEnsure(loanCompanyEnsure);
        System.out.println(result);
    }
}
