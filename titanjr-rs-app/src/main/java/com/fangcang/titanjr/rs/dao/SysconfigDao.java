package com.fangcang.titanjr.rs.dao;

import java.util.List;
import java.util.Map;

import com.fangcang.titanjr.rs.entity.Sysconfig;


public interface SysconfigDao {
	List<Sysconfig> query(Map<String, Object> condition);
}
