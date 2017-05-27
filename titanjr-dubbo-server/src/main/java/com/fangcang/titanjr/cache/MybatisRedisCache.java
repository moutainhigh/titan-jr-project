package com.fangcang.titanjr.dao.cache;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;

import com.fangcang.util.SpringContextUtil;

/**
 * 实现mybatis二级缓存机制，替换现有机制
 * 
 * @author wengxitao
 *
 */
public class MybatisRedisCache implements Cache {

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
				
				connection.openPipeline();
				Set<byte[]> bs = connection.keys(("*" +id+"*").getBytes());
//				connection.
				connection.del(bs.toArray(new byte[0][0]));
//				connection.keys(("*" + id +"*".getBytes()));
//				connection.flushDb();
				return "ok";
			}
		});
	}

	public String getId() {
		return id;
	}

	public Object getObject(final Object arg0) {
		return getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] key = serialize(arg0);
				byte[] value = connection.get(key);
				if (value == null) {
					return null;
				}
				return unserizlize(value);
			}
		});
	}

	public ReadWriteLock getReadWriteLock() {
		return readWriteLock;
	}

	public int getSize() {
		return getRedisTemplate().execute(new RedisCallback<Integer>() {
			public Integer doInRedis(RedisConnection connection)
					throws DataAccessException {
				return Integer.parseInt("" + connection.dbSize());
			}
		});
	}

	public void putObject(final Object arg0, final Object arg1) {
		final long liveTime = 86400;
		getRedisTemplate().execute(new RedisCallback<Long>() {
			public Long doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] keyb = serialize(arg0);
				byte[] valueb = serialize(arg1);
				connection.set(keyb, valueb);
				if (liveTime > 0) {

					connection.expire(keyb, liveTime);
				}
				return 1L;
			}
		});

	}

	public Object removeObject(final Object arg0) {

		return getRedisTemplate().execute(new RedisCallback<Object>() {
			public Object doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] keyb = serialize(arg0);
				byte[] result = connection.get(keyb);
				if (result != null) {
					connection.expire(serialize(arg0), 0);
				}
				return unserizlize(result);
			}
		});
	}

	// 序列化
	private static byte[] serialize(Object obj) {
		ObjectOutputStream obi = null;
		ByteArrayOutputStream bai = null;
		try {
			bai = new ByteArrayOutputStream();
			obi = new ObjectOutputStream(bai);
			obi.writeObject(obj);
			byte[] byt = bai.toByteArray();
			return byt;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 反序列化
	private static Object unserizlize(byte[] byt) {
		ObjectInputStream oii = null;
		ByteArrayInputStream bis = null;
		bis = new ByteArrayInputStream(byt);
		try {
			oii = new ObjectInputStream(bis);
			Object obj = oii.readObject();
			return obj;
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

}