package test.fangcang.base;

import org.junit.Before;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class BaseTest {

	public static ClassPathXmlApplicationContext cfx = null;
	
	@Before
	public void setup(){
		cfx = new ClassPathXmlApplicationContext("classpath:spring/applicationContext.xml");
	}
}
