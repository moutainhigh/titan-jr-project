package test.titanjr.checkstand.validate;

import com.fangcang.titanjr.redis.service.RedisService;
import com.titanjr.checkstand.constants.OperateTypeEnum;
import com.titanjr.checkstand.util.AccessLimiter;
import org.junit.Test;
import test.titanjr.checkstand.GenericTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhaoshan on 2017/11/16.
 */

public class RateLimiterTest extends GenericTest{

    @Resource
    AccessLimiter accessLimiter;

    @Resource
    private RedisService redisService;

    @Test
    public void testLimiterSet() throws Exception{

        for (int i = 0; i < 4; i++) {
            System.out.println(accessLimiter.accessFrequency(OperateTypeEnum.PAY_REQUEST));
        }
        Thread.sleep(1200);
        System.out.println(accessLimiter.accessFrequency(OperateTypeEnum.PAY_REQUEST));
        System.out.println(accessLimiter.accessFrequency(OperateTypeEnum.PAY_REQUEST));
        System.out.println(accessLimiter.accessFrequency(OperateTypeEnum.PAY_REQUEST));
        System.out.println(accessLimiter.accessFrequency(OperateTypeEnum.PAY_REQUEST));

        System.out.println("------------------------");
        System.out.println(accessLimiter.accessInterval(OperateTypeEnum.PAY_REQUEST,"201711171150060003"));
        Thread.sleep(1200);
        System.out.println(accessLimiter.accessInterval(OperateTypeEnum.PAY_REQUEST,"201711171150060003"));
        Thread.sleep(700);
        System.out.println(accessLimiter.accessInterval(OperateTypeEnum.PAY_REQUEST,"201711171150060003"));
    }

    public void redisTest(){
        Map<String, String> ukmap = new HashMap<String, String>();
        ukmap.put("key1","123");
        ukmap.put("key2","123");
        redisService.hmSetValue("userkey",ukmap);
        Set<String> keys = new HashSet<String>();
        keys.add("key1");
        Map<String, String> result = redisService.hmGetValue("userkey",keys);
        keys.add("key2");
        result = redisService.hmGetValue("userkey",keys);
        System.out.println(result);
    }

}
