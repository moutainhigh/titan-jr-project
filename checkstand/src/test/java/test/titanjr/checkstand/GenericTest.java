package test.titanjr.checkstand;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 执行测试用例时需要：
 * 去掉pom.xml文件中的<exclude>debug_conf.properties</exclude> 或者注释掉
 * Created by zhaoshan on 2017/11/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class GenericTest extends AbstractJUnit4SpringContextTests {

}
