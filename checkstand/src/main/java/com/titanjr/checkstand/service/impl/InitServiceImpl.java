/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName InitServiceImpl.java
 * @author Jerry
 * @date 2017年11月29日 下午6:17:45  
 */
package com.titanjr.checkstand.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dao.GateWayConfigDao;
import com.titanjr.checkstand.dto.GateWayConfigDTO;
import com.titanjr.checkstand.util.tlUtil.XmlTools;

/**
 * 初始化服务实现
 * @author Jerry
 * @date 2017年11月29日 下午6:17:45  
 */
public class InitServiceImpl {
	
	private final static Logger logger = LoggerFactory.getLogger(InitServiceImpl.class);
	
	@Resource
	private GateWayConfigDao gateWayConfigDao;
	
	
	public void init(){
		
		initXmlProvider();
		initGateWyUrlConfig();
		
	}
	
	
	/**
	 * 设置安全提供者
	 * @author Jerry
	 * @date 2017年12月27日 下午6:54:52
	 */
	private void initXmlProvider(){
		
		BouncyCastleProvider provider = new BouncyCastleProvider();
		XmlTools.initProvider(provider);
		
	}
	
	
	/**
	 * 初始化网关配置
	 * @author Jerry
	 * @date 2017年11月30日 下午6:26:24
	 */
	private void initGateWyUrlConfig(){
		
		logger.info("=============开始初始化网关配置=============");
		
		Map<String, GateWayConfigDTO> initMap = new HashMap<String, GateWayConfigDTO>();
		List<GateWayConfigDTO> configList  = gateWayConfigDao.queryGateWayConfigList();
		
		if(CollectionUtils.isEmpty(configList)){
			logger.error("初始化网关配置失败，未查询到配置信息");
			return;
		}
		for (int i = 0; i < configList.size(); i++) {
			GateWayConfigDTO gateWayConfig = configList.get(i);
			String key = gateWayConfig.getMerchantId() + "_" + gateWayConfig.getCombPayType() 
					+ "_" + gateWayConfig.getPayChannel() + "_" + gateWayConfig.getRequestType();
			initMap.put(key, gateWayConfig);
		}
		if(initMap.isEmpty()){
			logger.error("初始化网关配置失败，initMap为空");
			return;
		}
		SysConstant.gateWayConfigMap = initMap;
		
		logger.info("=============初始化网关配置成功=============");
		
	}

}
