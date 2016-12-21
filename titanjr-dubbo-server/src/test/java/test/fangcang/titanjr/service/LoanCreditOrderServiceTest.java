package test.fangcang.titanjr.service;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.junit.Assert;
import org.junit.Test;

import test.fangcang.titanjr.SpringTest;

import com.fangcang.titanjr.common.enums.LoanCreditStatusEnum;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.request.AuditCreditOrderRequest;
import com.fangcang.titanjr.dto.request.GetCreditOrderCountRequest;
import com.fangcang.titanjr.dto.request.QueryPageCreditCompanyInfoRequest;
import com.fangcang.titanjr.dto.response.AuditCreidtOrderResponse;
import com.fangcang.titanjr.dto.response.GetCreditOrderCountResponse;
import com.fangcang.titanjr.dto.response.PageCreditCompanyInfoResponse;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;

/**
 * 
 * @author luoqinglong
 * @date   2016年11月22日
 */
public class LoanCreditOrderServiceTest extends SpringTest {
	
	@Resource
	TitanFinancialLoanCreditService loanCreditService;
	
	
	public void testgetCreditOrderCount(){
		try{
			GetCreditOrderCountRequest request = new GetCreditOrderCountRequest();
			request.setStatus(1);;
			GetCreditOrderCountResponse response = loanCreditService.getCreditOrderCount(request);
			System.out.println(JSONSerializer.toJSON(response).toString());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
	}
	
	
	public void testqueryPageCreditCompanyInfo(){
		try{
			QueryPageCreditCompanyInfoRequest req = new QueryPageCreditCompanyInfoRequest();
			//req.setName("测试");
			//req.setStatus(1);;
			req.setCurrentPage(1);
			PageCreditCompanyInfoResponse response = loanCreditService.queryPageCreditCompanyInfo(req);
			System.out.println("================"+JSONSerializer.toJSON(response).toString());
			Assert.assertFalse(response.isResult()==false);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
	}
	
	@Test
	public void testauditCreditOrder(){

		AuditCreidtOrderRequest request = new AuditCreidtOrderRequest();
		request.setAuditResult(LoanCreditStatusEnum.PASS);
		request.setOrderNo("CR20161128112423613722");
		try {
			AuditCreidtOrderResponse response = loanCreditService.auditCreditOrder(request);
			System.out.println(Tools.gsonToString(response));
			Assert.assertFalse(response.isResult()==false);
		} catch (Exception e) {
			e.printStackTrace();
			
			Assert.assertFalse(1==1);
		}
		
	}
	
}
