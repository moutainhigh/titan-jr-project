package test.fangcang.titanjr.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.junit.Assert;
import org.junit.Test;

import test.fangcang.titanjr.SpringTest;

import com.fangcang.titanjr.common.enums.ReqstatusEnum;
import com.fangcang.titanjr.common.enums.entity.TitanUserEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.dto.request.SaaSUserRoleRequest;
import com.fangcang.titanjr.dto.request.TitanRoleQueryRequest;
import com.fangcang.titanjr.dto.request.UserFreezeRequest;
import com.fangcang.titanjr.dto.request.UserInfoQueryRequest;
import com.fangcang.titanjr.dto.request.UserRegisterRequest;
import com.fangcang.titanjr.dto.request.UserRoleSetRequest;
import com.fangcang.titanjr.dto.response.RoleUserInfoPageResponse;
import com.fangcang.titanjr.dto.response.TitanRoleResponse;
import com.fangcang.titanjr.dto.response.UserFreezeResponse;
import com.fangcang.titanjr.dto.response.UserInfoResponse;
import com.fangcang.titanjr.dto.response.UserRegisterResponse;
import com.fangcang.titanjr.dto.response.UserRoleSetResponse;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.google.gson.Gson;

/**
 * Created by zhaoshan on 2016/5/10.
 */
public class TitanFinancialUserServiceTest extends SpringTest {

    @Resource
    TitanFinancialUserService titanFinancialUserService;
    
    @Resource
    TitanFinancialAccountService titanFinancialAccountService;
  
    @Resource
    TitanOrderService titanOrderService;

    //@Test
    public void registerTitanUserTest() throws Exception{
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setMerchantCode("M10000001");
        userRegisterRequest.setLoginUserName("18620352083");
        userRegisterRequest.setFcLoginUserName("fangcang");
        userRegisterRequest.setIsAdminUser(1);
        userRegisterRequest.setMobilePhone("18620352083");
        userRegisterRequest.setOrgCode("PM123465478");
        userRegisterRequest.setPassword("89ec83eaaee6de3aa3d12305b019b932");
        userRegisterRequest.setRegisterSource(1);
        userRegisterRequest.setUserId("PM123465478");
//        userRegisterRequest.setOperateTime(new Date());
        userRegisterRequest.setOperator("it-test");
//        userRegisterRequest.setRoleIdList(roleIdList);
        UserRegisterResponse response = titanFinancialUserService.registerFinancialUser(userRegisterRequest);
        System.out.println(response);
    }

    //@Test
    public void queryFinancialUserTest(){
        UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
        UserInfoResponse response = titanFinancialUserService.queryFinancialUser(userInfoQueryRequest);
        System.out.println(JSONSerializer.toJSON(response).toString());
    }
//    @Test
    public void querySaaSUser() throws GlobalServiceException{
    	SaaSUserRoleRequest request = new SaaSUserRoleRequest();
    	request.setMerchantCode("M10000222");
    	titanFinancialUserService.querySaaSUserRolePage(request);
    	
    }
    //@Test
	public void testPageRoleUserInfo(){
    	UserInfoQueryRequest userInfoQueryRequest = new UserInfoQueryRequest();
    	userInfoQueryRequest.setOrgCode("TJM10000084");
    	RoleUserInfoPageResponse response = titanFinancialUserService.queryRoleUserInfoPage(userInfoQueryRequest);
		System.out.println(JSONSerializer.toJSON(response).toString());
	}
    ///@Test
    public void queryTitanRoleTest(){
        TitanRoleQueryRequest titanRoleQueryRequest = new TitanRoleQueryRequest();
        titanRoleQueryRequest.setRoleCodes(new ArrayList<String>());
        titanRoleQueryRequest.getRoleCodes().add("PAY");
        TitanRoleResponse response = titanFinancialUserService.queryTitanRole(titanRoleQueryRequest);
        System.out.println(response.getTitanRoleDTOList());
    }

    //@Test
    public void setFinancialUserRoleTest(){
        UserRoleSetRequest userRoleSetRequest = new UserRoleSetRequest();
        userRoleSetRequest.setUserRoleIdMap(new HashMap<Long, List<Long>>());
        userRoleSetRequest.getUserRoleIdMap().put(37435L,new ArrayList<Long>());
        userRoleSetRequest.getUserRoleIdMap().get(37435L).add(38L);
        userRoleSetRequest.getUserRoleIdMap().get(37435L).add(39L);
        UserRoleSetResponse userRoleSetResponse;
		try {
			userRoleSetResponse = titanFinancialUserService.setFinancialUserRole(userRoleSetRequest);
			 System.out.println(userRoleSetResponse.getReturnCode());
		} catch (GlobalServiceException e) {
			e.printStackTrace();
		}
       
    }
    
   // @Test
    public void testFreezeUser(){
    	
    	try {
    		UserFreezeRequest userFreezeRequest = new UserFreezeRequest();
    		userFreezeRequest.setTfsUserId(10046);
    		userFreezeRequest.setStatus(TitanUserEnum.Status.FREEZE.getKey());
    		userFreezeRequest.setOperator("luoqinglong");
        	UserFreezeResponse response =  titanFinancialUserService.freezeUser(userFreezeRequest);
        	System.out.println(new Gson().toJson(response));
        	Assert.assertFalse(response.isResult()==false);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
    }
    
//   @Test
    public void testOrder(){
    	titanOrderService.updateTitanOrderPayreq("2016061711232000001",ReqstatusEnum.RECHARFE_SUCCESS.getStatus()+"");
    }
    
//    @Test
//   public void testUnFreezeOrder(){
//	   titanFinancialAccountService.testUnFreezeOrder(0, 1);
//   }
    
}
