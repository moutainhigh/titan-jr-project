package com.fangcang.titanjr.common.factory;

import com.caucho.hessian.client.HessianProxyFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 由于spring4以上版本无法通过集成方式调用3.1.6版本的hessian服务
 * 所以需要通过工厂来获取
 * Created by zhaoshan on 2016/4/20.
 */
@Component
public class HessianProxyBeanFactory {
	
	HessianProxyFactory factory = new HessianProxyFactory();

	/**
	 * 已被拉取过的代理类的缓存
	 * 若已存在则直接获取，key是class对象。
	 */
	private static final Map<Class, Object> hessianProxyInstanceMap = new HashMap<Class, Object>();
	 
	@PostConstruct
	public void initRemote(){
		factory.setOverloadEnabled(true);
	}

	/**
	 * 根据类型和地址获取代理类
	 * @param clazz
	 * @param serviceURL
	 * @param <T>
     * @return
     */
	public <T> T getHessianProxyBean(Class<T> clazz, String serviceURL){
		if (hessianProxyInstanceMap.containsKey(clazz)){
			return (T) hessianProxyInstanceMap.get(clazz);
		}
		T proxyBean = null;
		try {
			proxyBean =  (T)factory.create(clazz, serviceURL);
			hessianProxyInstanceMap.put(clazz, proxyBean);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return proxyBean;
	}
}
