/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AccountDownloadDaoImpl.java
 * @author Jerry
 * @date 2018年3月21日 下午6:23:45  
 */
package com.titanjr.checkstand.dao.impl;

import java.util.List;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.titanjr.checkstand.dao.AccountDownloadDao;
import com.titanjr.checkstand.dto.AccountDownloadDTO;

/**
 * @author Jerry
 * @date 2018年3月21日 下午6:23:45  
 */
public class AccountDownloadDaoImpl extends GenericDAOMyBatisImpl implements
		AccountDownloadDao {

	@Override
	public int batchSave(List<AccountDownloadDTO> accountDownloadList) {
		try {
            return super.insertEntity("com.titanjr.checkstand.dao.AccountDownloadDao.batchSave", accountDownloadList);
        } catch (Exception e) {
            logger.error("batchSave AccountDownloadInfo Error" , e);
            throw new DaoException(e);
        }
	}

}
