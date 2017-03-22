package test.fangcang.titanjr.service;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import test.fangcang.titanjr.SpringTest;

import com.fangcang.titanjr.service.TitanCoopService;


public class TitanSynOrgInfoServiceTest extends SpringTest {
	
	@Resource
	TitanCoopService  coopService;
	
	@Test
	public void test(){
		try {
			coopService.notifyCoopOrgInfo();
		} catch (Exception e) {
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		
	}
}
