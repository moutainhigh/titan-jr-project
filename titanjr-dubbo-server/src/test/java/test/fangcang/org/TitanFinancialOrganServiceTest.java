package test.fangcang.org;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.fangcang.titanjr.dto.request.RefundConfirmRequest;
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
	
	
	@Test

	public void testRefundComnfirm(){
		RefundConfirmRequest refundConfirm = new RefundConfirmRequest();
		refundConfirm.setUserId("141223100000056");
		refundConfirm.setOrderNo("2016120909455300001");
		try{
			titanFinancialRefundService.refundConfirm(refundConfirm);
		}catch(Exception e){
			e.printStackTrace();
		}
		

}
