package com.fangcang.titanjr.cache;

import static org.hamcrest.CoreMatchers.nullValue;

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
	
	
	private RedisTemplate<String, Object> redisTemplate;

	/** The ReadWriteLock. */
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
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
	 * 清空key
	 */
	public void clear() {
		
		getRedisTemplate().execute(new RedisCallback<String>() {
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				log.info("MybatisRedisCache clear redis key ,regex:[*"+id+"*]");
				connection.openPipeline();
				Set<byte[]> bs = connection.keys(("*" +id+"*").getBytes());
				connection.del(bs.toArray(new byte[0][0]));
				return "ok";
			}
		});
	}


	public Object getObject(final Object keyObj) {
		if(keyObj!=null){
			String key = keyObj.toString();
			Object value = getRedisTemplate().opsForValue().get(key);
			if(value!=null){
				//Object object = jdkSerializer.deserialize((byte[])value);
				log.info("get-value-----key:"+Tools.gsonToString(keyObj)+",---value--"+Tools.gsonToString(value));
				return value;   
			}
			return null;
		}else{
			log.error("redis param error : key is null");
		}
		return null;
	}
	
	public void putObject(final Object key, final Object value) {
		final long liveTime = 15*60;//15分钟
		log.info("put-value-------key:"+Tools.gsonToString(key)+",---value--"+Tools.gsonToString(value));
		getRedisTemplate().opsForValue().set(key.toString(), value,liveTime, TimeUnit.SECONDS);
	 
	}

	public Object removeObject(final Object arg0) {
		getRedisTemplate().expire(arg0.toString(), 0, TimeUnit.SECONDS);
		return nullValue();
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