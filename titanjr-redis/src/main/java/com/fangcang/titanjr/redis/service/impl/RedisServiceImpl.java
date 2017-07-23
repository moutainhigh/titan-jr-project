package com.fangcang.titanjr.redis.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.redis.RedisDistributedLock;
import com.fangcang.titanjr.redis.service.RedisService;

@Service("redisService")
public class RedisServiceImpl implements RedisService {
	
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
	public boolean lock(String key) {
		RedisDistributedLock lock = new RedisDistributedLock(redisTemplate);
		
		return lock.lock(key);
	}
	 
}
