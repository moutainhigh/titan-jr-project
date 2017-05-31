package com.fangcang.titanjr.util;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 分布式锁工具,仅在service层用,不能在controller
 * 
 * @author xtweng
 *
 */
public class RedisDistributedLock implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3322602705007536708L;

	private static final Log log = LogFactory.getLog(RedisDistributedLock.class);
	
	private RedisTemplate<String, Object> redisTemplate = null;

	private static final int DEFAULT_ACQUIRY_RESOLUTION_MILLIS = 100;

	private int expireMsecs = 60 * 1000;

	/**
	 * 锁等待时间，防止线程饥饿
	 */
	private int timeoutMsecs = 10 * 1000;
	
	/**
	 * 是否成功获取到锁
	 */
	private boolean isGetLock = false;

	
	public RedisDistributedLock(RedisTemplate<String, Object> redisTemplate) {
		this.redisTemplate = redisTemplate;
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
		return redisTemplate;
	}

	private String getSet(final String key, final String value) {
		Object obj = null;
		try {
			obj = redisTemplate.execute(new RedisCallback<Object>() {
				public Object doInRedis(RedisConnection connection)
						throws DataAccessException {
					StringRedisSerializer serializer = new StringRedisSerializer();
					byte[] ret = connection.getSet(serializer.serialize(key),
							serializer.serialize(value));
					return serializer.deserialize(ret);
				}
			});
		} catch (Exception e) {
		}
		return obj != null ? (String) obj : null;
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

	private boolean checkLock(final String key, final String value) {

		boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				StringRedisSerializer serializer = new StringRedisSerializer();
				return connection.setNX(key.getBytes(),
						serializer.serialize(value));
			}
		});
		return result;
	}

	/**
	 * 根据key申请一个小锁
	 * 
	 * @param key
	 * @return false:未拿到锁，true:拿到锁
	 */
	public synchronized boolean lock(String key) {

		int timeout = timeoutMsecs;
		// 如果获取锁失败则多次尝试并且设置超时时间为10秒
		while (timeout >= 0) {
			long expires = System.currentTimeMillis() + expireMsecs;

			// 如果返回true则标示成功了，已经拿到锁了嘻嘻
			if (checkLock(key, String.valueOf(expires))) {
				isGetLock = true;
				return isGetLock;
			}
			// 获取锁的超时时间
			String value = get(key);

			// 如果锁的时间小于本地时间则标示锁已经超时了
			if (value != null
					&& Long.parseLong(value) < System.currentTimeMillis()) {

				// 对超时的锁进行重新设置时间并返回就的时间跟之前获取的时间进行一次比较
				String oldValueStr = this.getSet(key, String.valueOf(expires));

				// 主要是确认设置的新锁是成功的，并且新锁的超时时间已经更新成功
				if (oldValueStr != null && oldValueStr.equals(value)) {
					isGetLock = true;
					return isGetLock;
				}
			}

			// 每次减去100毫秒，直到循环小于0时就退出循环
			timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLIS;

			try {
				log.info("lock 未取到分布式锁 ，等待100毫秒,分布式key:"+key);
				// 每次等待100毫秒的间隔
				Thread.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
			} catch (InterruptedException e) {
				log.error("获取分布式redis锁时异常，锁key为："+key,e);
			}
		}
		return isGetLock;
	}

	/**
	 * 干掉锁
	 */
	public synchronized void unlock(String key) {
		if (redisTemplate != null && key != null) {
			if(isGetLock){
				redisTemplate.delete(key);
			}
			
		}
	}
}
