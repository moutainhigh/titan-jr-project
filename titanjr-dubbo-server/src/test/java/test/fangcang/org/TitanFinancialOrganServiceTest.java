package test.fangcang.org;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialBankCardService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;

import test.fangcang.base.BaseTest;

public class TitanFinancialOrganServiceTest extends BaseTest{
	
	@Resource
	private TitanFinancialOrganService titanOrgService;
	
	  @Before
    public void init(){
		  titanOrgService = (TitanFinancialOrganService)cfx.getBean("titanOrgService");    
    }
	
	@Test
	public void testTitanOpenOrg() throws Exception{
	}
	

}
