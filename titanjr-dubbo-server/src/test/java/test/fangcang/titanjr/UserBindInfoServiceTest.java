package test.fangcang.titanjr;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dao.TitanUserBindInfoDao;
import com.fangcang.titanjr.entity.TitanUserBindInfo;
import com.fangcang.titanjr.entity.parameter.TitanUserBindInfoParam;

/**
* Created by zhaoshan on 2016/4/8.
*/
public class UserBindInfoServiceTest extends SpringTest {
	
	@Resource(name="titanUserBindInfoDao")
	TitanUserBindInfoDao titanUserBindInfoDao;

	@Test
    public void pageUserBindInfo(){
    	PaginationSupport<TitanUserBindInfo> paginationSupport = new PaginationSupport<TitanUserBindInfo>();
    	paginationSupport.setCurrentPage(1);
    	paginationSupport.setPageSize(2);
    	TitanUserBindInfoParam condition = new TitanUserBindInfoParam();
    	titanUserBindInfoDao.selectForPage(condition, paginationSupport);
    	System.out.println("-------"+paginationSupport.getTotalCount());
    }
	
    @Test
	public void insertUserBindInfo(){
		TitanUserBindInfo entity = new TitanUserBindInfo();
		entity.setTfsuserid(10004);
		entity.setCreatetime(new Date());
		entity.setFcuserid(200034L);
		entity.setCreator("admin3");
		entity.setFcloginname("fangcang3");
		entity.setIsactive(1);
		entity.setUsername("集散4");
		int count = titanUserBindInfoDao.insert(entity);
		System.out.println(count+"-----------");
		Assert.assertTrue(count>0);
	}
	
	
	public void updateUserBindInfo(){
		TitanUserBindInfo entity = new TitanUserBindInfo();
		entity.setTfsuserid(10003);
		entity.setModifytime(new Date());
		entity.setFcuserid(20003L);
		entity.setModifior("admin3");
		entity.setFcloginname("fangcang");
		entity.setIsactive(1);
		entity.setUsername("集散33");
		int count = titanUserBindInfoDao.update(entity);
		Assert.assertTrue(count>0);
	}
}
