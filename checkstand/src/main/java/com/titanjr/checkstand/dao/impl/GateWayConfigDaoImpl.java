/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName SysConfigDaoImpl.java
 * @author Jerry
 * @date 2017年11月30日 下午5:48:52  
 */
package com.titanjr.checkstand.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.titanjr.checkstand.dao.GateWayConfigDao;
import com.titanjr.checkstand.dto.GateWayConfigDTO;

/**
 * @author Jerry
 * @date 2017年11月30日 下午5:48:52  
 */
public class GateWayConfigDaoImpl extends GenericDAOMyBatisImpl implements
		GateWayConfigDao {
	
	private final static Logger logger = LoggerFactory.getLogger(GateWayConfigDaoImpl.class);

	@Override
	public List<GateWayConfigDTO> queryGateWayConfigList() {
		try {
            return super.selectList("com.titanjr.checkstand.dao.GateWayConfigDao.queryGateWayConfigList");
        } catch (Exception e) {
            logger.error("queryGateWayConfigList Error：" , e);
            throw new DaoException(e);
        }
	}

}
