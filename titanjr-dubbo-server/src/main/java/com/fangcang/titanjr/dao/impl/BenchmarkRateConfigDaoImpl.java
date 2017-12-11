/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName BenchmarkRateConfigDaoImpl.java
 * @author Jerry
 * @date 2017年11月20日 下午2:53:37  
 */
package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.titanjr.dao.BenchmarkRateConfigDao;
import com.fangcang.titanjr.entity.BenchmarkRateConfig;

/**
 * 基准费率Dao实现
 * @author Jerry
 * @date 2017年11月20日 下午2:53:37  
 */
public class BenchmarkRateConfigDaoImpl extends GenericDAOMyBatisImpl implements
		BenchmarkRateConfigDao {

	@Override
	public BenchmarkRateConfig queryBenchmarkRateConfig(BenchmarkRateConfig benchmarkRateConfig) {
		
		return (BenchmarkRateConfig)super.selectOne(
				"com.fangcang.titanjr.dao.BenchmarkRateConfigDao.queryBenchmarkRateConfig", benchmarkRateConfig);
	}

}
