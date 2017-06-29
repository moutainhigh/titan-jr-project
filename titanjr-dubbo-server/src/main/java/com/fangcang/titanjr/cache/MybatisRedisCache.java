package com.fangcang.titanjr.cache;

import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.cache.Cache;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.util.SpringContextUtil;

/**
 * 实现mybatis二级缓存机制，替换现有机制
 * 
 * @author wengxitao
 *
 */
public class MybatisRedisCache implements Cache {
	private static final Log log = LogFactory.getLog(MybatisRedisCache.class);
	private final static long  MYBATIS_CACHE_TIME_OUT = 15*60;//15分钟
	
	private RedisTemplate<String, Object> redisTemplate;

	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	/**
	 * mapper 中的 namespace
	 */
	private String id;

	public MybatisRedisCache(String id) {
		this.id = id;
	}

	public RedisTemplate<String, Object> getRedisTemplate() {
		if (this.redisTemplate == null) {
			this.redisTemplate = (RedisTemplate<String, Object>) SpringContextUtil
					.getBean("redisTemplate");
		}
		return redisTemplate;
	}

	/**
	 * 清空缓存
	 */
	public void clear() {
		log.info("MybatisRedisCache clear redis key ,regex:[*"+id+"*]");
		Set<String> keys =  getRedisTemplate().keys("*"+id+"*");
		getRedisTemplate().delete(keys);
	}


	public Object getObject(final Object keyObj) {
		if(keyObj!=null){
			String key = keyObj.toString();
			Object value = getRedisTemplate().opsForValue().get(key);
			if(value!=null){
				log.info("get-value-----key:"+Tools.gsonToString(keyObj));
				return value;   
			}
			return null;
		}else{
			log.error("redis param error : key is null");
		}
		return null;
	}
	
	public void putObject(final Object key, final Object value) {
		log.info("put-value-------key:"+Tools.gsonToString(key));
		getRedisTemplate().opsForValue().set(key.toString(), value,MYBATIS_CACHE_TIME_OUT, TimeUnit.SECONDS);
	 
	}

	public Object removeObject(final Object key) {
		getRedisTemplate().delete(key.toString());
		return null;
	}
	
	public int getSize() {
		return getRedisTemplate().execute(new RedisCallback<Integer>() {
			public Integer doInRedis(RedisConnection connection)
					throws DataAccessException {
				return Integer.parseInt("" + connection.dbSize());
			}
		});
	}
	 
	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}
	
	public String getId() {
		return id;
	}
}