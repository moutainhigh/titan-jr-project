package com.fangcang.titanjr.dao;

import java.util.List;

import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.request.PayMethodConfigRequest;
import com.fangcang.titanjr.entity.TitanPayMethodConfig;
import com.fangcang.titanjr.rs.entity.TitanPayMethod;

public interface TitanPayMethodConfigDao {

	 List<TitanPayMethodConfig> queryTitanPayMethod(PayMethodConfigRequest payMethodConfigRequest)throws DaoException;
}
