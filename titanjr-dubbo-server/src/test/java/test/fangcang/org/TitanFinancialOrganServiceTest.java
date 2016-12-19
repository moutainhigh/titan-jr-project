package test.fangcang.org;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialRefundService;

import test.fangcang.base.BaseTest;

public class TitanFinancialOrganServiceTest extends BaseTest{
	
	@Resource
	private TitanFinancialOrganService titanOrgService;
	
	@Resource
	TitanFinancialRefundService titanFinancialRefundService;
	
	  @Before
    public void init(){
		  titanOrgService = (TitanFinancialOrganService)cfx.getBean("titanOrgService");  
		  titanFinancialRefundService = (TitanFinancialRefundService)cfx.getBean("titanFinancialRefundService");
    }
	
//	@Test
	public void testTitanOpenOrg() throws Exception{
		titanOrgService.test();
	}
	
	@Test
	public void testRefundComnfirm(){
		titanFinancialRefundService.refundConfirm(null);
	}
	

}
