package test.fangcang.titanjr.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import test.fangcang.titanjr.SpringTest;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.dao.TitanSynOrgInfoDao;
import com.fangcang.titanjr.entity.TitanSynOrgInfo;
import com.fangcang.titanjr.entity.parameter.TitanSynOrgInfoParam;

public class TitanCoopDaoTest extends SpringTest{
	  
	 @Resource
	 TitanSynOrgInfoDao titanSynOrgInfoDao;
	 
	 @Test
	 public void testInsert(){
		 TitanSynOrgInfo entity = new TitanSynOrgInfo();
		 entity.setCoopType(4);
		 entity.setKvparam("aaa=111&bbb=2222&ccc=3333");
		 entity.setNotifyUrl("http://baidu.com");
		 entity.setCreateTime(new Date());
		 int count =0;
		 while(count<20){
			 int i = titanSynOrgInfoDao.insert(entity);
			 Assert.assertFalse(i==0);
			 count++;
		 }
		 
	 }
	 
	 
	 public void testPage(){
		 TitanSynOrgInfoParam param = new TitanSynOrgInfoParam();
		 PaginationSupport<TitanSynOrgInfo> page = new PaginationSupport<TitanSynOrgInfo>();
		 page =  titanSynOrgInfoDao.selectForPage(param, page);
		 Assert.assertFalse(page.getItemList()==null||page.getItemList().size()==0);
		 System.out.println("---size:"+page.getItemList().size());
	 }
	 
	 
	 public void testDelete(){
		List<Integer> idsIntegers = new ArrayList<Integer>();
		idsIntegers.add(6);
		idsIntegers.add(7);
		idsIntegers.add(8);
		idsIntegers.add(9);
		int count =  titanSynOrgInfoDao.deleteById(idsIntegers);
		 System.out.println("---删除记录条数:"+count);
	 }
}
