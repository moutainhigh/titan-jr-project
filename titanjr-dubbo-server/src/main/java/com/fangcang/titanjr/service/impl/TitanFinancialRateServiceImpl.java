package com.fangcang.titanjr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fangcang.titanjr.dao.TitanRateConfigDao;
import com.fangcang.titanjr.dto.bean.TitanRateDto;
import com.fangcang.titanjr.dto.request.CreateRateRecordRequest;
import com.fangcang.titanjr.dto.request.RateConfigRequest;
import com.fangcang.titanjr.dto.response.CreateRateRecordResponse;
import com.fangcang.titanjr.dto.response.RateConfigResponse;
import com.fangcang.titanjr.dto.response.RateRecordResponse;
import com.fangcang.titanjr.entity.TitanRateConfig;
import com.fangcang.titanjr.entity.TitanRateRecord;
import com.fangcang.titanjr.entity.parameter.TitanRateConfigParam;
import com.fangcang.titanjr.service.TitanFinancialRateService;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;

@Service("titanFinancialRateService")
public class TitanFinancialRateServiceImpl implements TitanFinancialRateService {

	@Resource
	private TitanRateConfigDao titanRateConfigDao;

	/**
	 * 查询费率配置信息
	 */
	@Override
	public RateConfigResponse getRateConfigInfos(RateConfigRequest req) {

		RateConfigResponse configResponse = new RateConfigResponse();
		configResponse.putSuccess();

		TitanRateConfigParam configParam = new TitanRateConfigParam();
		configParam.setUserid(req.getUserId());
		if (req.getPayType() != null) {
			configParam.setCashierItemType(Integer.parseInt(req.getPayType()
					.getItemCode()));
		}
		List<TitanRateConfig> rateConfigs = titanRateConfigDao
				.queryTitanRateConfigInfo(configParam);
		List<TitanRateDto> resultList = new ArrayList<TitanRateDto>();

		if (rateConfigs != null) {
			TitanRateDto titanRateDto = null;
			for (TitanRateConfig titanRateConfig : rateConfigs) {
				titanRateDto = new TitanRateDto();
				MyBeanUtil.copyProperties(titanRateDto, titanRateConfig);
				resultList.add(titanRateDto);
			}
		}
		configResponse.setRateInfoList(resultList);
		return configResponse;
	}

	@Override
	public RateRecordResponse getRateRecordInfos(RateConfigRequest req) {

		return null;
	}

	@Override
	public CreateRateRecordResponse createRateRecord(CreateRateRecordRequest req) {
		CreateRateRecordResponse createRateRecordResponse = new CreateRateRecordResponse();
		TitanRateRecord titanRateDto = new TitanRateRecord();
		MyBeanUtil.copyProperties(titanRateDto, req.getTitanRateRecordDto());
		if (StringUtil.isValidString(titanRateDto.getOrderNo())) {
			titanRateConfigDao.deleteRateRecordByOrderNo(titanRateDto
					.getOrderNo());
		}
		titanRateConfigDao.insertRateRecord(titanRateDto);

		createRateRecordResponse.putSuccess();
		return createRateRecordResponse;
	}

}
