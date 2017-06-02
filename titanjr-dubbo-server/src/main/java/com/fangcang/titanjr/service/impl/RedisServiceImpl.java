package com.fangcang.titanjr.service.impl;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.service.RedisService;

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
		
		this.setValue(Tools.getTitanRedisKey(key), value, timeout, TimeUnit.MINUTES);
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


}
