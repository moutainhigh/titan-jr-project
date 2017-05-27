package com.fangcang.titanjr.service;

import java.util.concurrent.TimeUnit;

/**
 * 缓存
 * @author luoqinglong
 *
 */
public interface RedisCacheService {
	
	/***
	 * 保存redis值
	 * @param clazz 用于合成key,格式为：com-fangcang-titanjr-common-util-Wxutil
	 * @param key 业务key, 格式为：username ,最后合成的key 为：TITANJR:com-fangcang-titanjr-common-util-Wxutil:username
	 * @param value
	 */
	public void setValue(String key,Object value);
	
	/**
	 * 保存redis值
	 * @param clazz 用于合成key,格式为：com-fangcang-titanjr-common-util-Wxutil
	 * @param key 业务key, 格式为：username ,最后合成的key 为：TITANJR:com-fangcang-titanjr-common-util-Wxutil:username
	 * @param value
	 * @param timeout 失效时间，单位：分钟
	 */
	public void setValue(String key,Object value,long timeout);
	
	
	/**
	 * 保存redis值
	 * @param clazz 用于合成key,格式为：com-fangcang-titanjr-common-util-Wxutil
	 * @param key 业务key, 格式为：username ,最后合成的key 为：TITANJR:com-fangcang-titanjr-common-util-Wxutil:username
	 * @param value
	 * @param timeout 失效时间
	 * @param unit 失效时间单位
	 */
	public void setValue(String key,Object value,long timeout,TimeUnit unit);
	/***
	 * 
	 * @param clazz 用于合成key,格式为：com-fangcang-titanjr-common-util-Wxutil
	 * @param key 业务key, 格式为：username ,最后合成的key 为：TITANJR:com-fangcang-titanjr-common-util-Wxutil:username
	 * @return
	 */
	public Object getValue(String key);
	
	/**
	 * 删除 某个值
	 * @param clazz 用于合成key,格式为：com-fangcang-titanjr-common-util-Wxutil
	 * @param key 业务key, 格式为：username ,最后合成的key 为：TITANJR:com-fangcang-titanjr-common-util-Wxutil:username
	 */
	public void deleteValue(String key);
}
