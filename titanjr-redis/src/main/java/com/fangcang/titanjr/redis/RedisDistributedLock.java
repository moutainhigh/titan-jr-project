package com.fangcang.titanjr.redis;

import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 分布式锁工具
 * 
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
	 * 抢锁时长,超过该时间还未取到锁，就返回去锁失败，防止线程饥饿
	 */
	private int timeoutMsecs = 10 * 1000;
	
	/**
	 * 是否成功获取到锁
	 */
	private String lockValue = "";

	
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
	 * @return 返回空:未拿到锁，返回时间戳:拿到锁
	 */
	public synchronized boolean lock(String key) {
		int timeout = timeoutMsecs;
		// 如果获取锁失败则多次尝试并且设置超时时间为10秒
		while (timeout >= 0) {
			long expires = System.currentTimeMillis() + expireMsecs;

			// 如果返回true则标示成功了，已经拿到锁了嘻嘻
			if (checkLock(key, String.valueOf(expires))) {
				lockValue = get(key);
				log.info("thread name："+Thread.currentThread().getName()+",lock(): ---------------成功拿到分布式锁 ,分布式key:["+key+"]");
				return true;
			}
			// 获取锁的超时时间
			String value = get(key);
			

			// 如果锁的时间小于本地时间,则锁已经超时
			if (value != null
					&& Long.parseLong(value) < System.currentTimeMillis()) {

				// 对超时的锁进行重新设置时间并返回就的时间跟之前获取的时间进行一次比较
				String oldValueStr = this.getSet(key, String.valueOf(expires));

				// 主要是确认设置的新锁是成功的，并且新锁的超时时间已经更新成功
				if (oldValueStr != null && oldValueStr.equals(value)) {
					lockValue = get(key);
					log.info("thread name："+Thread.currentThread().getName()+",lock(): ---------------成功拿到分布式锁 ,分布式key:["+key+"]");
					return true;
				}else{
					log.info("thread name："+Thread.currentThread().getName()+",lock(): ---------------分布式锁超时,分布式key:["+key+"]");
				}
			}

			// 每次减去100毫秒，直到循环小于0时就退出循环
			timeout -= DEFAULT_ACQUIRY_RESOLUTION_MILLIS;

			try {
				// 每次等待100毫秒的间隔
				Thread.sleep(DEFAULT_ACQUIRY_RESOLUTION_MILLIS);
			} catch (InterruptedException e) {
				log.error("thread name："+Thread.currentThread().getName()+",lock():获取分布式redis锁时异常，分布式key：["+key+"]",e);
			}
		}
		log.error("thread name："+Thread.currentThread().getName()+",lock(): "+timeoutMsecs+" 毫秒内  获取分布式锁失败 ,分布式key:["+key+"]");
		return false;
	}

	 
	/**
	 * 解锁分布式锁
	 */
	public synchronized void unlock(String key) {
		if (redisTemplate != null && key != null) {
			String cacheValue = get(key);
			if(cacheValue!=null&&lockValue!=null&&lockValue.equals(cacheValue)){//只有锁持有者才能解锁
				redisTemplate.delete(key);
				log.info("thread name："+Thread.currentThread().getName()+",lock(): reids 成功解锁,key:["+key+"],lockValue:"+lockValue+",cacheValue:"+cacheValue);
			}else{
				log.error("thread name："+Thread.currentThread().getName()+",lock(): reids 解锁失败,key:["+key+"],lockValue:"+lockValue+",cacheValue:"+cacheValue);
			}
		}
	}
}
