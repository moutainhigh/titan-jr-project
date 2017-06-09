package com.fangcang.titanjr.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.service.RedisService;
import com.fangcang.titanjr.util.RedisDistributedLock;

@Service("redisService")
public class RedisServiceImpl implements RedisService {
	
	private static final Log log = LogFactory.getLog(RedisServiceImpl.class);
	/***
	 * 缓存默认过期时间
	 */
	private static final long REDIS_DEFAULT_TIMEOUT_15_MINUTES = 15L;
	
	@Resource(name="redisTemplate")
	private RedisTemplate<String, Object> redisTemplate;
	
	
	@Override
	public void setValue( String key, Object value) {
		
		this.setValue(key, value, REDIS_DEFAULT_TIMEOUT_15_MINUTES);
	}

	@Override
	public void setValue(String key, Object value, long timeout) {
		
		this.setValue(key, value, timeout, TimeUnit.MINUTES);
	}

	@Override
	public void setValue(String key, Object value, long timeout, TimeUnit unit) {
		redisTemplate.opsForValue().set(Tools.getTitanRedisKey(key), value, timeout,unit);
	}

	@Override
	public Object getValue(String key) {
		return redisTemplate.opsForValue().get(Tools.getTitanRedisKey(key));
	}

	@Override
	public void deleteValue(String key) {
		redisTemplate.expire(key, 0, TimeUnit.MINUTES);
	}

	@Override
	public String lock(String key) {
		RedisDistributedLock lock = new RedisDistributedLock(redisTemplate);
		return lock.lock(key);
	}

	@Override
	public void unlock(String key,String lockValue) {
		String cacheValue = get(key);
		log.info("redis 开始解锁---,key:"+key+",lockValue:"+lockValue+",cacheValue:"+cacheValue);
		if(cacheValue!=null&&lockValue!=null&&lockValue.equals(cacheValue)){//只有锁持有者才能解锁
			redisTemplate.delete(key);
			log.info("redis 成功解锁,key:"+key+",lockValue:"+lockValue+",cacheValue:"+cacheValue);
		}
		
	}

	private String get(final String key) {
		Object obj = null;
		obj = redisTemplate.execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection) {
				StringRedisSerializer serializer = new StringRedisSerializer();
				byte[] data = connection.get(serializer.serialize(key));
				if (data == null) {
					return null;
				}
				return serializer.deserialize(data);
			}
		});
		return obj != null ? obj.toString() : null;
	}
}
