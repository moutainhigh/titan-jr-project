package test.fangcang.titanjr;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;

import com.fangcang.titanjr.dao.TitanCityInfoDao;
import com.fangcang.titanjr.entity.TitanCityInfo;

public class TitanCityInfoServiceTest extends SpringTest{

	@Resource
	private TitanCityInfoDao tinCityInfoDao;
	
	@Test
	public void insert(){
		TitanCityInfo entity = new TitanCityInfo();
		entity.setCitycode("121103");
		entity.setCityname("深圳3");
		entity.setCountry("中国");
		entity.setCreatetime(new Date());
		entity.setDatatype(2);
		entity.setParentcode("1213");
		tinCityInfoDao.insert(entity);
	}
	
	public void update(){
		TitanCityInfo entity = new TitanCityInfo();
		entity.setCityinfoid(1);
		entity.setCitycode("4444");
		entity.setCityname("深圳4");
		entity.setCountry("中国4");
		entity.setModifytime(new Date());
		
		
		tinCityInfoDao.update(entity);
	}
}
