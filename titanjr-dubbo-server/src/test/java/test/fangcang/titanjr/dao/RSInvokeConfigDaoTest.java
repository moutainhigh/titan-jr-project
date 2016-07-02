package test.fangcang.titanjr.dao;


import java.util.List;

import javax.annotation.Resource;

import org.junit.Assert;
import org.junit.Test;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.rs.dao.RSInvokeConfigDao;
import com.fangcang.titanjr.rs.entity.RSInvokeConfig;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import test.fangcang.titanjr.SpringTest;

import com.fangcang.titanjr.rs.dao.RSInvokeConfigDao;
import com.fangcang.titanjr.rs.entity.RSInvokeConfig;

/**
* Created by zhaoshan on 2016/4/8.
*/

public class RSInvokeConfigDaoTest extends SpringTest {
	
	@Resource(name="rsInvokeConfigDao")
    RSInvokeConfigDao rsInvokeConfigDao;

    @Test
    public void testGetCfgs() {
        List<RSInvokeConfig> list = rsInvokeConfigDao.queryRSInvokeConfig();
        Assert.assertNotNull(list);
        System.out.println(list.size());
    }

//    private static RSInvokeConfigDao rsInvokeConfigDao ;
//
//    @Before
//    public void init(){
//        rsInvokeConfigDao = (RSInvokeConfigDao)cfx.getBean("rsInvokeConfigDao");
//    }
//
//    @Test
//    public void testGetCfgs(){
//        List<RSInvokeConfig> cfgs = rsInvokeConfigDao.queryRSInvokeConfig();
//        System.out.println(cfgs);
//    }

//    public static void main(String[] args) {
//        ClassPathXmlApplicationContext cfx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
//        RSInvokeConfigDao rsInvokeConfigDao = (RSInvokeConfigDao)cfx.getBean("rsInvokeConfigDao");
//        List<RSInvokeConfig> cfgs = rsInvokeConfigDao.queryRSInvokeConfig();
//        System.out.println(cfgs);
//        TitanFinancialOrganDao titanDaoTest = (TitanFinancialOrganDao)cfx.getBean("titanFinancialOrganDao");
//        List<TitanFinancialOrgan>  organList =titanDaoTest.queryOrganization(new TitanFinancialOrgan());
//        System.out.println(organList);
//        PaginationSupport<TitanFinancialOrgan> paginationSupport = new PaginationSupport<TitanFinancialOrgan>();
//        paginationSupport.setCurrentPage(1);
//        paginationSupport.setPageSize(2);
//        titanDaoTest.queryOrganForPage(new TitanFinancialOrgan(),paginationSupport);
//        System.out.println(paginationSupport);
//    }


}