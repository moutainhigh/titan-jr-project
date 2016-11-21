package test.fangcang.titanjr.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import test.fangcang.titanjr.SpringTest;

import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.request.HelpRequest;
import com.fangcang.titanjr.dto.request.HelpTypeRequest;
import com.fangcang.titanjr.dto.request.QueryPageHelpWordRequest;
import com.fangcang.titanjr.dto.response.QueryPageHelpResponse;
import com.fangcang.titanjr.dto.response.QueryPageHelpWordResponse;
import com.fangcang.titanjr.service.TitanHelpService;

public class TitanHelpServiceTest extends SpringTest{
	@Resource
	private TitanHelpService helpService;
	
	
	public void testaddHelp(){
		HelpRequest helpRequest = new HelpRequest();
		helpRequest.setHelpTitle("SAAS员工账号和金融员工账号的区别");
		helpRequest.setHelpContent("答：SAAS员工账号和金融员工账号的区别");
		helpRequest.setIsShow(1);
		helpRequest.setHelpType(1);
		helpRequest.setOperator("luoqinglong");
		try {
			BaseResponseDTO response = helpService.addHelp(helpRequest);
			System.out.println("-------------response:"+Tools.gsonToString(response));
			Assert.assertTrue(response.isResult());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
	}
	
	public void testupdateHelp(){
		HelpRequest helpRequest = new HelpRequest();
		helpRequest.setHelpId(3);
		helpRequest.setHelpTitle("金融账户的结构是什么样的2");
		helpRequest.setHelpContent("答：金融账户的结构是什么样的2");
		helpRequest.setIsShow(1);
		helpRequest.setHelpType(1);
		helpRequest.setOperator("luoqinglong2");
		try {
			BaseResponseDTO response = helpService.updateHelp(helpRequest);
			System.out.println(Tools.gsonToString(response));
			Assert.assertTrue(response.isResult());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
	}
	
	public void testaddHelpType(){
		HelpTypeRequest helpTypeRequest = new HelpTypeRequest();
		helpTypeRequest.setName("123");
		helpTypeRequest.setIconimg("1234");
		helpTypeRequest.setIsShow(1);
		helpTypeRequest.setOrderNo(111);
		helpTypeRequest.setOperator("luoqinglong123");
		try {
			BaseResponseDTO response = helpService.addHelpType(helpTypeRequest);
			System.out.println(Tools.gsonToString(response));
			Assert.assertTrue(response.isResult());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
	}
	
	
	public void testupdateHelpType(){
		HelpTypeRequest helpTypeRequest = new HelpTypeRequest();
		helpTypeRequest.setHelpType(9);
		helpTypeRequest.setName("123");
		helpTypeRequest.setIconimg("4444");
		helpTypeRequest.setIsShow(0);
		helpTypeRequest.setOrderNo(100);
		helpTypeRequest.setOperator("luoqinglong100");
		try {
			BaseResponseDTO response = helpService.updateHelpType(helpTypeRequest);
			System.out.println(Tools.gsonToString(response));
			Assert.assertTrue(response.isResult());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertFalse(1==1);
		}
	}
	
	@Test
	public void testqueryPageHelpWord(){
		/*QueryPageHelpWordRequest queryPageHelpWordRequest = new QueryPageHelpWordRequest();
		queryPageHelpWordRequest.setWord("金融");
		queryPageHelpWordRequest.setCurrentPage(1);
		queryPageHelpWordRequest.setIsShow(1);
		queryPageHelpWordRequest.setOrderBy(" orderno asc ");
		QueryPageHelpWordResponse response = helpService.queryPageHelpWord(queryPageHelpWordRequest);
		
		System.out.println(Tools.gsonToString(response));
		Assert.assertTrue(response.isResult());*/
		
	}
	
	
	public void testQueryPageHelp(){
		HelpRequest queryPageHelpRequest = new HelpRequest();
		queryPageHelpRequest.setHelpType(1);
		queryPageHelpRequest.setIsShow(1);
		
		QueryPageHelpResponse response = helpService.queryPageHelp(queryPageHelpRequest);
		System.out.println(Tools.gsonToString(response));
		Assert.assertFalse(response.isResult()==false);
		
	}
	
	 
}
