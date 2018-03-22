/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName SysConfigDao.java
 * @author Jerry
 * @date 2017年11月30日 下午5:13:31  
 */
package com.titanjr.checkstand.dao;

import java.util.List;

import com.titanjr.checkstand.dto.AccountDownloadDTO;

/**
 * @author Jerry
 * @date 2017年11月30日 下午5:13:31  
 */
public interface AccountDownloadDao {
	
	/**
	 * 批量插入
	 * @author Jerry
	 * @date 2018年3月21日 下午6:21:40
	 */
    int batchSave(List<AccountDownloadDTO> accountDownloadList);

}
