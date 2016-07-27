package test.fangcang.titanjr.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Assert;
import org.junit.Test;

import test.fangcang.titanjr.SpringTest;

import com.fangcang.titanjr.common.enums.LoginSourceEnum;
import com.fangcang.titanjr.common.enums.SMSType;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum.CertificateType;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum.UserType;
import com.fangcang.titanjr.common.enums.entity.TitanOrgImageEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.exception.MessageServiceException;
import com.fangcang.titanjr.common.util.FtpUtil;
import com.fangcang.titanjr.common.util.ImageIOExtUtil;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.GetCheckCodeRequest;
import com.fangcang.titanjr.dto.request.OrganBindRequest;
import com.fangcang.titanjr.dto.request.OrganCheckRequest;
import com.fangcang.titanjr.dto.request.OrganImageUploadRequest;
import com.fangcang.titanjr.dto.request.OrganRegisterRequest;
import com.fangcang.titanjr.dto.request.OrganRegisterUpdateRequest;
import com.fangcang.titanjr.dto.request.UpdateCheckCodeRequest;
import com.fangcang.titanjr.dto.request.VerifyCheckCodeRequest;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.GetCheckCodeResponse;
import com.fangcang.titanjr.dto.response.OrganBindResponse;
import com.fangcang.titanjr.dto.response.OrganCheckResponse;
import com.fangcang.titanjr.dto.response.OrganImageUploadResponse;
import com.fangcang.titanjr.dto.response.OrganQueryCheckResponse;
import com.fangcang.titanjr.dto.response.OrganRegisterResponse;
import com.fangcang.titanjr.dto.response.OrganRegisterUpdateResponse;
import com.fangcang.titanjr.dto.response.VerifyCheckCodeResponse;
import com.fangcang.titanjr.service.TitanCodeCenterService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.google.gson.Gson;

public class TitanFinancialOrganServiceTest extends SpringTest{
	@Resource
	TitanFinancialOrganService titanFinancialOrganService;
	@Resource
	TitanCodeCenterService titanCodeCenterService;
	 
	//@Test
    public void queryFinancialOrgan(){
    	FinancialOrganQueryRequest request = new FinancialOrganQueryRequest();
    	//request.setUserId("U100003");
    	//request.setOrgId(10036);
    	request.setOrgCode("TJM10000090");
    	FinancialOrganResponse response = titanFinancialOrganService.queryFinancialOrgan(request);
    	String body = JSONSerializer.toJSON(response).toString();
    	System.out.println("---------"+body);
    }
	    
	public void getOrgId(){
		String orgCode = titanCodeCenterService.createOrgCode();
		System.out.println("orgCode-------"+orgCode);
	}
	
	public void getTitanCode(){
		String code = titanCodeCenterService.createTitanCode();
		System.out.println("-------"+code);
	}
 
	/**
	 * 上传图片
	 * @throws FileNotFoundException
	 * @throws GlobalServiceException
	 */
	//@Test
	public void testUploadImg() throws FileNotFoundException, GlobalServiceException{
		String oldfileName = "F:\\20160524164934.png";
		String newfileName = FtpUtil.createFileName()+ImageIOExtUtil.getFileSuffix(oldfileName);
		OrganImageUploadRequest organImageUploadRequest = new OrganImageUploadRequest();
		organImageUploadRequest.setFileName(newfileName);
		organImageUploadRequest.setImageType(TitanOrgImageEnum.ImageType.YYZZ.getKey());
		organImageUploadRequest.setFileBytes(file2byte(oldfileName));
		try {
			OrganImageUploadResponse response = titanFinancialOrganService.uploadFinancialOrganImages(organImageUploadRequest);
			System.out.println(JSONSerializer.toJSON(response).toString());
			Assert.assertFalse(response.isResult()==false);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
	}
	
	public static byte[] file2byte(String filePath)
	{
		byte[] buffer = null;
		try
		{
			File file = new File(filePath);
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1)
			{
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return buffer;
	}

	/***
	 * SAAS注册机构
	 */
	@Test
	public void testRegisterOrg_2(){
		OrganRegisterRequest organRegisterRequest = new OrganRegisterRequest();
		organRegisterRequest.setRegisterSource(LoginSourceEnum.SAAS.getKey());
		organRegisterRequest.setImageid("342,343,344");
		organRegisterRequest.setCertificateType(CertificateType.SFZ.getKey()+"");
		organRegisterRequest.setCertificateNumber("4304261990111331123");
		organRegisterRequest.setOrgName("春秋旅游");
		organRegisterRequest.setUserName("春秋旅游");
		organRegisterRequest.setUserloginname("luoqinglong@etxing.com");
		organRegisterRequest.setPassword("123456");
		organRegisterRequest.setMerchantCode("M100000061");
		organRegisterRequest.setMerchantname("春秋旅游");
		
		
		organRegisterRequest.setUserType(UserType.PERSONAL.getKey());
		
		try {
			OrganRegisterResponse response = titanFinancialOrganService.registerFinancialOrgan(organRegisterRequest);
			System.out.println(JSONSerializer.toJSON(response).toString());
			Assert.assertFalse(response.isResult()==false);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
		
	}
	
	/**
	 * 金服官网注册机构_
	 */
	//@Test
	public void testRegisterOrg_1(){
		OrganRegisterRequest organRegisterRequest = new OrganRegisterRequest();
		organRegisterRequest.setRegisterSource(LoginSourceEnum.TFS.getKey());
		organRegisterRequest.setImageid("32,33,34");
		organRegisterRequest.setCertificateType(CertificateType.SFZ.getKey()+"");
		organRegisterRequest.setCertificateNumber("430426199011345353");
		organRegisterRequest.setOrgName("罗庆龙333");
		organRegisterRequest.setUserName("罗庆龙333");
		organRegisterRequest.setUserloginname("13312534535");
		organRegisterRequest.setPassword("123456");
		
		organRegisterRequest.setUserType(UserType.PERSONAL.getKey());
		
		try {
			OrganRegisterResponse response = titanFinancialOrganService.registerFinancialOrgan(organRegisterRequest);
			
			System.out.println("----------"+JSONSerializer.toJSON(response).toString());
			Assert.assertFalse(response.isResult()==false);
		} catch (Exception e) {
			
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
	}
	//@Test 
	public void testRegisterOrg_2_enterprise(){
		OrganRegisterRequest organRegisterRequest = new OrganRegisterRequest();
		organRegisterRequest.setImageid("32,33,34");
		
		organRegisterRequest.setUserloginname("luoqinglong@etxing.com");
		organRegisterRequest.setPassword("123456");
		
		organRegisterRequest.setOrgName("深圳春秋科技有限公司");
		organRegisterRequest.setBuslince("430426199011133123456");
		organRegisterRequest.setUserName("深圳春秋科技有限公司");
		
		organRegisterRequest.setConnect("罗庆龙1");
		organRegisterRequest.setMobileTel("13352989767");
		organRegisterRequest.setMerchantCode("M100000061");
		organRegisterRequest.setMerchantname("春秋");
		organRegisterRequest.setRegisterSource(LoginSourceEnum.SAAS.getKey());
		
		organRegisterRequest.setUserType(UserType.ENTERPRISE.getKey());
		
		organRegisterRequest.setFcLoginUserName("chunqiu");
		
		try {
			OrganRegisterResponse response = titanFinancialOrganService.registerFinancialOrgan(organRegisterRequest);
			System.out.println(JSONSerializer.toJSON(response).toString());
			Assert.assertFalse(response.isResult()==false);
		} catch (Exception e) {
			
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
	}
	
	public void testRegisterOrg_3(){
		OrganRegisterRequest organRegisterRequest = new OrganRegisterRequest();
		organRegisterRequest.setRegisterSource(LoginSourceEnum.AUTO.getKey());
		organRegisterRequest.setImageid("32,33,34");
		organRegisterRequest.setCertificateType(CertificateType.SFZ.getKey()+"");
		organRegisterRequest.setCertificateNumber("430426199011115487");
		organRegisterRequest.setOrgName("罗庆龙");
		organRegisterRequest.setUserName("罗庆龙");
		organRegisterRequest.setUserloginname("13352989767");
		organRegisterRequest.setPassword("123456");
		
		organRegisterRequest.setUserType(UserType.PERSONAL.getKey());
		
		try {
			OrganRegisterResponse response = titanFinancialOrganService.registerFinancialOrgan(organRegisterRequest);
			System.out.println(JSONSerializer.toJSON(response).toString());
			Assert.assertFalse(response.isResult()==false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 审核
	 */
	//@Test
	public void testCheckOrg(){
		OrganCheckRequest organCheckRequest = new OrganCheckRequest();
		organCheckRequest.setOrgId(10102);
		organCheckRequest.setCheckstatus(0);
		organCheckRequest.setOperator("luoqinglong");
		organCheckRequest.setResultMsg("不清楚");
		try {
			OrganCheckResponse response = titanFinancialOrganService.checkFinancialOrgan(organCheckRequest);
			System.out.println(JSONSerializer.toJSON(response).toString());
			Assert.assertFalse(response.isResult()==false);
			
		} catch (GlobalServiceException e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		} catch (MessageServiceException e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
		
	}
	/**
	 * 重新机构信息修改
	 */
	
	public void testReg(){
		OrganRegisterUpdateRequest organRegisterUpdateRequest = new OrganRegisterUpdateRequest();
		organRegisterUpdateRequest.setUserType(TitanOrgEnum.UserType.PERSONAL.getKey());
		organRegisterUpdateRequest.setCertificateType("0");
		organRegisterUpdateRequest.setCertificateNumber("123456789");
		organRegisterUpdateRequest.setOrgName("罗小闲");
		organRegisterUpdateRequest.setOrgId(10068);
		try {
			OrganRegisterUpdateResponse response = titanFinancialOrganService.reRegisterOrgan(organRegisterUpdateRequest);
			System.out.println(JSONSerializer.toJSON(response).toString());
			Assert.assertFalse(response.isResult()==false);
		} catch (GlobalServiceException e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
	}
	/**
	 * 机构绑定，解绑
	 */
	//@Test
	public void testbindFinancialOrgan(){
		OrganBindRequest organBindRequest = new OrganBindRequest();
		organBindRequest.setMerchantCode("M122222");
		organBindRequest.setUserId("TJM10000090");
		organBindRequest.setOperator("luoqinglong");
		organBindRequest.setUserloginname("133529812333");
		organBindRequest.setFcuserid(100052);
		organBindRequest.setFcloginname("fangcang-loginname");
		organBindRequest.setOperateType(0);
		organBindRequest.setPassword("123456");
		try {
			OrganBindResponse response = titanFinancialOrganService.bindFinancialOrgan(organBindRequest);
			System.out.println(JSONSerializer.toJSON(response).toString());
			Assert.assertFalse(response.isResult()==false);
		} catch (GlobalServiceException e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		} catch (MessageServiceException e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
	}
	/***
	 * 账户审核查询
	 */ 
	//@Test
	public void testQueryCheckOrg(){
		FinancialOrganQueryRequest condition = new FinancialOrganQueryRequest();
		//condition.setCheckStatus(1);
		condition.setIsadmin(1);
		condition.setResultKey("FT");
		///condition.setIsoperator(0);
		condition.setUserloginname("1331111123");
		///condition.setOrgName("");
		//condition.setOrgType(1);
		condition.setUserType(1);
		condition.setRegStartTime("2016-04-19 00:00:00");
		condition.setRegEndTime("2016-05-19 00:00:00");
		
		OrganQueryCheckResponse response = titanFinancialOrganService.queryFinancialOrganForPage(condition);
		System.out.println(new Gson().toJson(response));
	}
	
	//@Test
	public void testQueryOrg(){
		FinancialOrganQueryRequest request = new FinancialOrganQueryRequest();
		request.setOrgCode("TJM10000090");
		FinancialOrganResponse response =  titanFinancialOrganService.queryFinancialOrgan(request);
		System.out.println(new Gson().toJson(response));
	}
	
	//@Test
	public void testCountOrg(){
		FinancialOrganQueryRequest financialOrganQueryRequest = new FinancialOrganQueryRequest();
		financialOrganQueryRequest.setResultKey("FT");
		financialOrganQueryRequest.setUserType(2);
		int count = titanFinancialOrganService.countOrg(financialOrganQueryRequest);
		System.out.println("---------------------"+count);
	}
	//@Test
	public void testgetCheckCode(){
		GetCheckCodeRequest getCheckCodeRequest = new GetCheckCodeRequest();
		getCheckCodeRequest.setMsgType(SMSType.REG_CODE.getType());
		getCheckCodeRequest.setReceiveAddress("luoqinglong0102@163.com");
		try {
			GetCheckCodeResponse response = titanFinancialOrganService.getCheckCode(getCheckCodeRequest);
			System.out.println(JSONSerializer.toJSON(response).toString());
			Assert.assertFalse(response.isResult()==false);
		} catch (GlobalServiceException e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		} 
		
	}
	@Test
	public void testverifyCheckCode(){
		VerifyCheckCodeRequest verifyCheckCodeRequest = new VerifyCheckCodeRequest();
		verifyCheckCodeRequest.setReceiveAddress("luoqinglong0102@163.com");
		verifyCheckCodeRequest.setInputCode("299534");
		VerifyCheckCodeResponse response = titanFinancialOrganService.verifyCheckCode(verifyCheckCodeRequest);
		System.out.println(JSONSerializer.toJSON(response).toString());
		Assert.assertFalse(response.isResult()==false);
		 
	}
	
	//@Test
	public void testupdateCheckCode(){
		UpdateCheckCodeRequest updateCheckCodeRequest = new UpdateCheckCodeRequest();
		updateCheckCodeRequest.setCodeId(1);
		updateCheckCodeRequest.setIsactive(0);
		try {
			BaseResponseDTO response = titanFinancialOrganService.useCheckCode(updateCheckCodeRequest);
			System.out.println(JSONSerializer.toJSON(response).toString());
			Assert.assertFalse(response.isResult()==false);
		} catch (GlobalServiceException e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		} 
		
	}
}
