package com.fangcang.titanjr.redis.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.fangcang.titanjr.common.util.CommonConstant;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisCallback;
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

	@Resource(name="redisTemplate")
	private RedisTemplate<Serializable, String> serializableRedisTemplate;

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

	@Override
	public boolean hmSetValue(final String keyString, final Map<String, String> valueMap) {
		return serializableRedisTemplate.execute(new RedisCallback<Boolean>() {
			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = serializableRedisTemplate.getStringSerializer().serialize(Tools.getTitanRedisKey(keyString));
				Map<byte[], byte[]> valueByteMap = new HashMap<byte[], byte[]>();
				for (String vk :valueMap.keySet()){
					valueByteMap.put(serializableRedisTemplate.getStringSerializer().serialize(vk),
							serializableRedisTemplate.getStringSerializer().serialize(valueMap.get(vk)));
				}
				connection.hMSet(key, valueByteMap);
				return true;
			}
		});
	}

	@Override
	public Map<String, String> hmGetValue(final String keyString,final Set<String> keySet) {
		return serializableRedisTemplate.execute(new RedisCallback<Map<String, String> >() {
			@Override
			public Map<String, String> doInRedis(RedisConnection connection)
					throws DataAccessException {
				Map<String, String> result = new HashMap<String, String>();
				byte[] key = serializableRedisTemplate.getStringSerializer().serialize(Tools.getTitanRedisKey(keyString));
				if (connection.exists(key)) {
					for (String kv : keySet){
						List<byte[]> valueList = connection.hMGet(key,serializableRedisTemplate.getStringSerializer().serialize(kv));
						if (CollectionUtils.isNotEmpty(valueList)) {
							result.put(kv, serializableRedisTemplate.getStringSerializer().deserialize(valueList.get(0)));
						}
					}
				}
				return result;
			}
		});
	}


	@Override
	public Map<String, String> hmGetAll(final String keyString){
		return serializableRedisTemplate.execute(new RedisCallback<Map<String, String> >() {
			@Override
			public Map<String, String> doInRedis(RedisConnection connection)
					throws DataAccessException {
				Map<String, String> result = new HashMap<String, String>();
				byte[] key = serializableRedisTemplate.getStringSerializer().serialize(Tools.getTitanRedisKey(keyString));
				Map<byte[], byte[]> valueMap = connection.hGetAll(key);

				for (byte[] keys : valueMap.keySet()){
					result.put(serializableRedisTemplate.getStringSerializer().deserialize(keys),
							serializableRedisTemplate.getStringSerializer().deserialize(valueMap.get(keys)));
				}
				return result;
			}
		});
	}

}
