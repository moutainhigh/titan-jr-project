package com.fangcang.titanjr.dao;

import com.fangcang.titanjr.entity.TitanSysconfig;
import com.fangcang.titanjr.entity.parameter.TitanSysconfigParam;
import com.sun.tools.javac.util.List;

public interface TitanSysconfigDao {
	List<TitanSysconfig> query(TitanSysconfigParam titanSysconfigParam);
}
