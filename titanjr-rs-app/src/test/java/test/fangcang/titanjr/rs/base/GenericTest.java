package test.fangcang.titanjr.rs.base;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhaoshan on 2016/4/15.
 */
public class GenericTest {
    protected static ClassPathXmlApplicationContext cfx;

    @Before
    public void setUp() throws Exception {
        cfx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-rs-service.xml");
        System.out.println(cfx);
    }

}
