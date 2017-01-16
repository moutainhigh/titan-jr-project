package com.fangcang.titanjr.dao;

import com.fangcang.titanjr.entity.PlatformOrderStat;
import com.fangcang.titanjr.entity.parameter.PlatformOrderStatConditons;

public interface PlatformOrderOprDao {

	public PlatformOrderStat queryPlatformStatInfo(
			PlatformOrderStatConditons conditons);

}
