package test.fangcang.titanjr.rs.invoker;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.Rop.api.request.WheatfieldAccountCheckRequest;
import com.Rop.api.response.WheatfieldAccountCheckResponse;
import com.fangcang.titanjr.rs.manager.RSAccountManager;
import com.fangcang.titanjr.rs.manager.RSOrganizationManager;
import com.fangcang.titanjr.rs.request.AccountFlowQueryRequest;
import com.fangcang.titanjr.rs.request.AccountFreezeRequest;
import com.fangcang.titanjr.rs.request.AccountUnFreezeRequest;
import com.fangcang.titanjr.rs.request.CompOrgInfoQueryRequest;
import com.fangcang.titanjr.rs.request.CompanyOrgRegRequest;
import com.fangcang.titanjr.rs.request.CompanyOrgUpdateRequest;
import com.fangcang.titanjr.rs.request.DestoryAccountRequest;
import com.fangcang.titanjr.rs.request.OrgStatusQueryRequest;
import com.fangcang.titanjr.rs.request.PersOrgInfoQueryRequest;
import com.fangcang.titanjr.rs.request.PersonOrgRegRequest;
import com.fangcang.titanjr.rs.request.PersonOrgUpdateRequest;
import com.fangcang.titanjr.rs.response.AccountFlowQueryResponse;
import com.fangcang.titanjr.rs.response.AccountFreezeResponse;
import com.fangcang.titanjr.rs.response.AccountUnFreezeResponse;
import com.fangcang.titanjr.rs.response.CompOrgInfoQueryResponse;
import com.fangcang.titanjr.rs.response.CompanyOrgUpdateResponse;
import com.fangcang.titanjr.rs.response.DestoryAccountResponse;
import com.fangcang.titanjr.rs.response.OrgStatusQueryResponse;
import com.fangcang.titanjr.rs.response.PersOrgInfoQueryResponse;
import com.fangcang.titanjr.rs.response.PersonOrgRegResponse;
import com.fangcang.titanjr.rs.response.PersonOrgUpdateResponse;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.fangcang.titanjr.rs.base.GenericTest;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class RSAccountManagerTest extends GenericTest {

    private static RSAccountManager rsAccountManager = null;

    @Before
    public void init(){
    	rsAccountManager = (RSAccountManager)cfx.getBean("rsAccountManager");
    }

    //冻结个人账户,userType 为2 冻结个人账户
//    @Test 
    public void testFreezeAccount(){
    	AccountFreezeRequest req= new AccountFreezeRequest();
		req.setUserid("TJM10000108");								// 接入机构中设置的用户ID
		req.setConstid("M000016");				// 机构码
	    req.setProductid("P000070");			// 产品号	
		req.setUsertype("2");
		AccountFreezeResponse response = rsAccountManager.freezeAccount(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus());
    }
    
    //冻结个人账户,userType 为1 冻结商户账户
//    @Test 
    public void testOrgFreezeAccount(){
    	AccountFreezeRequest req= new AccountFreezeRequest();
		
    	req.setUserid("PM10000029");					// 接入机构中设置的用户ID  不能重复
        req.setUsertype("1");					// 用户类型(1：商户 )
        req.setConstid("M000016");				// 机构码
        req.setProductid("P000070");			// 产品号
		AccountFreezeResponse response = rsAccountManager.freezeAccount(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus());
    }
    
    //解冻个人账户  unFreezeAccount
//    @Test 
    public void testUnFreezeAccount(){
    	AccountUnFreezeRequest req= new AccountUnFreezeRequest();
		
		req.setUserid("PP10000030");								// 接入机构中设置的用户ID
		req.setConstid("M000016");				// 机构码
	    req.setProductid("P000070");			// 产品号
		req.setUsertype("2");
		
		AccountUnFreezeResponse response = rsAccountManager.unFreezeAccount(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus());
    }
    
    
  //解冻机构账户  unFreezeAccount
//    @Test 
    public void testUnFreezeOrgAccount(){
    	AccountUnFreezeRequest req= new AccountUnFreezeRequest();
		
    	req.setUserid("PM10000029");					// 接入机构中设置的用户ID  不能重复
        req.setUsertype("1");					// 用户类型(1：商户 )
        req.setConstid("M000016");				// 机构码
        req.setProductid("P000070");			// 产品号
		
		AccountUnFreezeResponse response = rsAccountManager.unFreezeAccount(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus());
    }
    
    //销毁机构账户  destoryAccount
//    @Test 
    public void testDestoryOrgAccount(){
    	DestoryAccountRequest req= new DestoryAccountRequest();
		
        req.setUserid("PM10000029");					// 接入机构中设置的用户ID  不能重复
        req.setUsertype("1");					// 用户类型(1：商户 )
        req.setConstid("M000016");				// 机构码
        req.setProductid("P000070");			// 产品号
        req.setRole("");
        req.setReferuserid("");
		
        DestoryAccountResponse response = rsAccountManager.destoryAccount(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus());
    }
    
    //销毁个人账户  destoryAccount
//    @Test 
    public void testDestoryPersonAccount(){
    	DestoryAccountRequest req= new DestoryAccountRequest();
		
    	req.setUserid("PP10000030");								// 接入机构中设置的用户ID
    	req.setConstid("M000016");				// 机构码
        req.setProductid("P000070");			// 产品号
		req.setUsertype("2");
        req.setRole("");
        req.setReferuserid("");
		
        DestoryAccountResponse response = rsAccountManager.destoryAccount(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus());
    }
    
    
    //查询账户流水  
    @Test 
    public void testQueryAccountFlow() throws ParseException{
    	AccountFlowQueryRequest req= new AccountFlowQueryRequest();
    	req.setRootinstcd("M000016");							//机构码
    	req.setUserid("PM10000021");
    	// 接入机构中设置的用户ID
        req.setProductid("P000070");			// 产品号						//产品号		
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = format.parse("2005-10-1 12:55:03");
        Date endDate = format.parse("2016-04-27 10:06:03");
    	req.setCreatedtimefrom(startDate);						//记录创建开始时间
    	req.setCreatedtimeto(endDate);						//记录创建结束时间			
    	req.setQuerytype("1");									//查询类型（1:根据账户查询 2:根据交易记录查询）
    	req.setRequestid("");								//交易记录编码（查询类型为2时，必须入参）
    	
    	AccountFlowQueryResponse response = rsAccountManager.queryAccountFlow(req);
		System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus());
		if(response.getAccountFlow() !=null && response.getAccountFlow().size()>0){
			System.out.print("------------------"+response.getAccountFlow().size());
			System.out.print("------------------"+response.getAccountFlow().get(0).getProductid());
		}
		
    }
	
}
