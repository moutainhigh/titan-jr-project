package test.fangcang.user;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import com.fangcang.titanjr.dto.request.PayPasswordRequest;
import com.fangcang.titanjr.dto.response.PayPasswordResponse;
import com.fangcang.titanjr.service.TitanFinancialUserService;

import test.fangcang.GenericTest;

public class TitanUserServiceTest extends GenericTest{
	@Resource
	private static TitanFinancialUserService titanFinancialUserService= null;
	

    @Before
    public void init(){
    	titanFinancialUserService = (TitanFinancialUserService)cfx.getBean("titanFinancialUserService");
    }
    
	//测试交易密码
    @Test
    public void testSaveOrUpdatePayPassword(){
    	PayPasswordRequest payPasswordRequest = new PayPasswordRequest();
//    	payPasswordRequest.setOldPassword("123456");
    	payPasswordRequest.setPayPassword("123456");
    	payPasswordRequest.setTfsuserid("10046");
    	PayPasswordResponse payPasswordResponse = titanFinancialUserService.saveOrUpdatePayPassword(payPasswordRequest);
        System.out.println(payPasswordResponse.getReturnCode()+"----"+payPasswordResponse.getReturnMessage());
        System.out.println("--------"+payPasswordResponse.isSaveSuccess());
    }
    
    //房仓设置密码 
    @Test
    public void testFcSaveOrUpdatePayPassword(){
    	PayPasswordRequest payPasswordRequest = new PayPasswordRequest();
    	payPasswordRequest.setPayPassword("123456");
    	payPasswordRequest.setFcuserid("20000");
    	PayPasswordResponse payPasswordResponse = titanFinancialUserService.saveOrUpdatePayPassword(payPasswordRequest);
        System.out.println(payPasswordResponse.getReturnCode()+"----"+payPasswordResponse.getReturnMessage());
        System.out.println("--------"+payPasswordResponse.isSaveSuccess());
    }
    
	
}
