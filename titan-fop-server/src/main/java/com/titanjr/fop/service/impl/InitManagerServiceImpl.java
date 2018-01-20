/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName InitManagerServiceImpl.java
 * @author Jerry
 * @date 2018年1月19日 下午5:49:04  
 */
package com.titanjr.fop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.titanjr.fop.constants.InterfaceURlConfig;
import com.titanjr.fop.dao.TitanSysConfigDao;
import com.titanjr.fop.entity.TitanSysConfig;


/**
 * @author Jerry
 * @date 2018年1月19日 下午5:49:04  
 */
public class InitManagerServiceImpl {
	
	private final static String OBJ_KEY_TITANPAYMETHOD = "TitanPayMethod";
	private final static Logger logger = LoggerFactory.getLogger(InitManagerServiceImpl.class);
	
	@Resource(name="titanSysConfigDao")
	private TitanSysConfigDao titanSysConfigDao;
	
	
	public void init() {
		
		logger.info("======开始初始化系统参数=============");
		
		TitanSysConfig param = new TitanSysConfig();
		param.setObjKey(OBJ_KEY_TITANPAYMETHOD);
		List<TitanSysConfig> list = titanSysConfigDao.querySysConfigList(param);
		if(CollectionUtils.isNotEmpty(list)){
			for(TitanSysConfig item : list){
				//checkstand支付网关
				if(item.getCfgKey().equals("CSPayMethod_gatewayURL")){
					InterfaceURlConfig.checkstand_GateWayURL = item.getCfgValue();
					break;
				}
			}
			
		}
		logger.info("======系统参数初始化完毕=============");
		
	}

}
