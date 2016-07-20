package test.fangcang.titanjr.rs.invoker;

import com.Rop.api.request.WheatfieldAccountCheckRequest;
import com.Rop.api.response.WheatfieldAccountCheckResponse;
import com.fangcang.titanjr.rs.manager.RSOrganizationManager;
import com.fangcang.titanjr.rs.request.CompOrgInfoQueryRequest;
import com.fangcang.titanjr.rs.request.CompanyOrgRegRequest;
import com.fangcang.titanjr.rs.request.CompanyOrgUpdateRequest;
import com.fangcang.titanjr.rs.request.OrgStatusQueryRequest;
import com.fangcang.titanjr.rs.request.PersOrgInfoQueryRequest;
import com.fangcang.titanjr.rs.request.PersonOrgRegRequest;
import com.fangcang.titanjr.rs.request.PersonOrgUpdateRequest;
import com.fangcang.titanjr.rs.response.CompOrgInfoQueryResponse;
import com.fangcang.titanjr.rs.response.CompanyOrgRegResponse;
import com.fangcang.titanjr.rs.response.CompanyOrgUpdateResponse;
import com.fangcang.titanjr.rs.response.OrgStatusQueryResponse;
import com.fangcang.titanjr.rs.response.PersOrgInfoQueryResponse;
import com.fangcang.titanjr.rs.response.PersonOrgRegResponse;
import com.fangcang.titanjr.rs.response.PersonOrgUpdateResponse;
import com.fasterxml.jackson.databind.JsonSerializable;

import net.sf.json.JSONSerializer;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.fangcang.titanjr.rs.base.GenericTest;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class OrganizationManagerTest extends GenericTest {

    private static RSOrganizationManager rsOrganizationManager = null;

    @Before
    public void init(){
        rsOrganizationManager = (RSOrganizationManager)cfx.getBean("rsOrganizationManager");
    }

//    注册机构信息 success
//    @Test
    public void testRegisterComOrg(){
        CompanyOrgRegRequest req = new CompanyOrgRegRequest();
        // 必输项
        req.setCompanyname("集散公司万岁");				// 企业名称
//        req.setBuslince("YP454986456RYY");					// 营业执照
        req.setBuslince("YP454986456RVS");
        req.setUsertype("1");					// 用户类型(1：商户 )
        req.setUserid("TJM10000108");					// 接入机构中设置的用户ID  不能重复
        req.setConstid("M000016");				// 机构码
        req.setProductid("P000070");			// 产品号
        req.setUsername("天下旅行");					// 用户名称，及接入机构的用户名

        // 非必输项
        req.setAcuntopnlince("");			//开户许可证
        req.setAddress("");					//地址
        req.setBusplacectf("");				//经营场所实地认证
        req.setCompanycode("");				//企业编号
        req.setConnect("");					//联系方式
        req.setCorporateidentity("");		//法人身份证
        req.setCorporatename("");			//法人姓名
        req.setLoancard("");					//贷款卡
        req.setMcc("");							//MCC码
        req.setOrgancertificate("");			//组织结构代码证
        req.setPost("");						//邮编
        req.setRemark("");					//备注
        req.setRole("");						//角色号
        req.setShortname("");				//企业简称
        req.setTaxregcardf("");					//税务登记证1
        req.setTaxregcards("");					//税务登记证2

        CompanyOrgRegResponse response= rsOrganizationManager.resigterCompanyOrg(req);
        System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
        System.out.print("------------------"+response.getOperateStatus());
    }
    
//    注册个人信息 success
//    @Test
    public void testRegisterPersOrg(){
    	PersonOrgRegRequest req = new PersonOrgRegRequest();
        // 必输项
    	req.setPersonchnname("张三s3");						// 中文姓名（当操作类型是2：修改时，此项目为可选）
		req.setCertificatetype("0");						// 证件类型,0身份证;1护照;2军官证;3士兵证;4回乡证;
															// 5户口本;6外国护照;7其它（当操作类型是2：修改时，此项目为可选）
		req.setCertificatenumber("411381196802185626");						// 证件号（当操作类型是2：修改时，此项目为可选）
		req.setUserid("PP10000096");								// 接入机构中设置的用户ID
		req.setConstid("M000016");							// 机构码
		req.setProductid("P000070");						// 产品号
		req.setOpertype("1");								// 操作类型（1：新增，2：修改）
		// 非必输项
		req.setAddress("");									//地址
		req.setBirthday("");								//生日
		req.setEmail("");									//邮箱
		req.setFixtel("");									//固定电话号码
		req.setMobiletel("");								//手机号码
		req.setPersonengname("");							//英文名
		req.setPersonsex("");								//性别
		req.setPersontype("1");								//个人类别 默认 1
		req.setPost("");									//邮编
		req.setRemark("");									//备注
		req.setRole("");									//角色号
		req.setUsername("");								//用户名
    	PersonOrgRegResponse response = rsOrganizationManager.resigterPersonOrg(req);
    	System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
    	System.out.print("------------------"+response.getOperateStatus());
    }
    
  //路更新机构信息 success
//    @Test  
    public void updateCompanyOrg(){
    	CompanyOrgUpdateRequest req = new CompanyOrgUpdateRequest();
        // 必输项
		req.setCompanyname("深圳市天下旅行有限公司");				// 企业名称
	   
	    req.setUsertype("1");					// 用户类型(1：商户 )
	    req.setUserid("PM10000029");					// 接入机构中设置的用户ID  不能重复
        req.setConstid("M000016");				// 机构码
        req.setProductid("P000070");			// 产品号
	    req.setUsername("天下旅行");	
	    req.setOpertype("1");							// 更新类型（1.更新）
		// 非必输项
		req.setAcuntopnlince("");					//开户许可证
		req.setAddress("");							//地址
		//	req.setBuslince("xxx");							//营业执照
	    req.setBuslince("YP454986456RVY");					// 营业执照
		req.setBusplacectf("");						//经营场所实地认证
		req.setCompanycode("");						//企业编号
		req.setConnect("13303068549");							//联系方式
		req.setCorporateidentity("");				//法人身份证
		req.setCorporatename("");					//法人姓名
		req.setLoancard("");							//贷款卡
		req.setMcc("");									//Mcc码
		req.setOrgancertificate("");					//组织结构代码
		req.setPost("");								//邮编
		req.setRemark("");							//备注
		req.setRole("");								//角色号
		req.setShortname("");						//企业简称
		req.setTaxregcardf("");							//税务登记证1
		req.setTaxregcards("");							//税务登记证2
        CompanyOrgUpdateResponse response = rsOrganizationManager.updateCompanyOrg(req);
    	System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
    	System.out.print("------------------"+response.getOperateStatus());
    }
    
    
  //查询机构状态 success
//    @Test
    public void testqueryOrgStatus(){
    	OrgStatusQueryRequest req = new OrgStatusQueryRequest();
    	req.setUserid("TJM10000010");						// 用户ID
		req.setUsertype("1");						// 用户类型(1：商户，2：普通用户)
		req.setConstid("M000016");				// 机构码
	    req.setProductid("P000070");			// 产品号
		// 非必输参数
		req.setRole("");							// 角色号
		req.setStatusid("1");
		req.setReferuserid("");
		OrgStatusQueryResponse response = rsOrganizationManager.queryOrgStatus(req);
    	System.out.print("查询机构状态-----------"+response.getReturnCode()+"----------"+response.getReturnMsg());
    	System.out.print("------------------"+response.getOperateStatus());
    }
    
    //更新个人信息 success
//    @Test 
    public void testUpdatePersonOrg(){
    	PersonOrgUpdateRequest req = new PersonOrgUpdateRequest();
    	req.setUserid("PP10000030");								// 接入机构中设置的用户ID
	    req.setConstid("M000016");				// 机构码
        req.setProductid("P000070");			// 产品号
    	req.setOpertype("2");                        //操作类型               
    	req.setPersonchnname("李四");                  //中文姓名（当操作类型是2：修改时，此项目为可选）            
    	req.setCertificatenumber("");              //证件类别             
	   
    	req.setCertificatetype("");         //证件类型,0身份证;1护照;2军官证;3士兵证;4回乡证; // 5户口本;6外国护照;7其它（当操作类型是2：修改时，此项目为可选）                        
    	req.setBirthday("");                 //生日             
    	req.setMobiletel("");                 //电话             
    	req.setAddress("");                   //地址           
    	req.setRemark("");                     //备注    
    	req.setPersontype("");                  //类型，默认1，现在也只有1            
    	req.setUsername("");                        //用户名       
    	req.setFixtel("");                         //固定电话号码     
    	req.setPost("");                          //邮编    
    	req.setRole("");                           //角色   
    	req.setEmail("");                          //邮箱  
    	req.setPersonsex("2");                     //性别  
    	PersonOrgUpdateResponse response = rsOrganizationManager.updatePersonOrg(req);
    	System.out.print("------------------"+response.getReturnCode()+"----------"+response.getReturnMsg());
    	System.out.print("------------------"+response.getOperateStatus());
    }
    
    // 查询机构信息 success
//    @Test 
    public void testQueryCompOrgInfo(){
    	CompOrgInfoQueryRequest req= new CompOrgInfoQueryRequest();
		req.setUserid("TJM10000010");					// 接入机构中设置的用户ID  不能重复
		req.setConstid("M000016");				// 机构码
	    req.setProductid("P000070");			// 产品号	
		req.setAcuntopnlince("");						// 开户许可证
		req.setBuslince("");							// 营业执照
		req.setCompanycode("");							// 企业编号
		req.setCompanyname("");							// 企业名称			
		req.setCorporateidentity("");					// 法人身份证
		req.setCorporatename("");						// 法人姓名
//		req.setCreatedendtime(new Date());				// 创建查询结束时间（格式 YYYYMMDD）
//		req.setCreatedstarttime(new Date());			// 创建查询开始时间（格式 YYYYMMDD）
		req.setOrgancertificate("");					// 组织结构代码证
		req.setShortname("");							// 企业简称
		req.setStatus("");								// 状态
		CompOrgInfoQueryResponse response = rsOrganizationManager.queryCompOrgInfo(req);
		System.out.print("--------查询机构信息----------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus());
		System.out.print("------------------"+JSONSerializer.toJSON(response));
		if(response.getCompanyOrgList()!=null && response.getCompanyOrgList().size()>0){
			System.out.println(response.getCompanyOrgList().get(0).getCompanyname());
		}
		System.out.print("------------------"+response.getOperateStatus());
		
    }
    
    //查询个人信息 success
    @Test 
    public void testQueryPersOrgInfo(){
    	PersOrgInfoQueryRequest req = new PersOrgInfoQueryRequest();
    	req.setConstid("M000016");				// 机构码
        req.setProductid("P000070");			// 产品号			//机构号
		req.setUserid("TJM10000010");
		PersOrgInfoQueryResponse response = rsOrganizationManager.queryPersOrgInfo(req);
		System.out.print("------------------"+JSONSerializer.toJSON(response));
		System.out.print("---------查询个人信息---------"+response.getReturnCode()+"----------"+response.getReturnMsg());
		System.out.print("------------------"+response.getOperateStatus());
		if(response.getPersonOrgList()!=null && response.getPersonOrgList().size()>0){
			System.out.println(response.getPersonOrgList().get(0).getCertificatenumber());
		}
    }
    
    
}
