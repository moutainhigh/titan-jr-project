package test.fangcang;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by zhaoshan on 2016/4/20.
 */
public class GenericTest {

    protected static ClassPathXmlApplicationContext cfx;

    @Before
    public void setUp() throws Exception {
        cfx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        System.out.println(cfx);
    }
    
    
}
