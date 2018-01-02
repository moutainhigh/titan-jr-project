/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName SysConfigDao.java
 * @author Jerry
 * @date 2017年11月30日 下午5:13:31  
 */
package com.titanjr.checkstand.dao;

import java.util.List;

import com.titanjr.checkstand.dto.GateWayConfigDTO;

/**
 * @author Jerry
 * @date 2017年11月30日 下午5:13:31  
 */
public interface GateWayConfigDao {
	
	/**
	 * 查询网关配置列表
	 * @author Jerry
	 * @date 2017年11月30日 下午5:47:09
	 * @return
	 */
	public List<GateWayConfigDTO> queryGateWayConfigList();

}
