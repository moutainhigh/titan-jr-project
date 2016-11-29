package com.fangcang.titanjr.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fangcang.titanjr.common.enums.BusTypeEnum;
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
import com.fangcang.util.DateUtil;
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
			configParam.setBustype(BusTypeEnum.getBusTypeByItemType(req.getPayType().getItemCode()));
		}else{
			configParam.setBustype(BusTypeEnum.WITHDRAW_RATE.type);
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

	@Override
	public void initRateData() {
		List<String> userIdList = titanRateConfigDao.queryAllUserId();
		for(String userid :userIdList){
			 TitanRateConfig rateConfigB2B = new TitanRateConfig();
			 rateConfigB2B.setBustype(BusTypeEnum.B2B_RATE.type);//1表示付款费率
			 rateConfigB2B.setDescription("企业网银支付费率");
			 rateConfigB2B.setRatetype(2);//按笔收费
			 rateConfigB2B.setRsrate(10f);//千分之一点五
			 rateConfigB2B.setStandrate(10f);
			 rateConfigB2B.setExecutionrate(0f);
			 rateConfigB2B.setUserid(userid);
			 rateConfigB2B.setCreator("system");
			 rateConfigB2B.setCreatetime(new Date());
			 rateConfigB2B.setExpiration(DateUtil.getDate(new Date(), 6));
			 
	         TitanRateConfig rateConfigB2C = new TitanRateConfig();
	         rateConfigB2C.setBustype(BusTypeEnum.B2C_RATE.type);//1表示付款费率
	         rateConfigB2C.setDescription("个人网银支付费率");
	         rateConfigB2C.setRatetype(1);//按百分比
	         rateConfigB2C.setRsrate(0.2f);//千分之一点五
	         rateConfigB2C.setStandrate(0.3f);
	         rateConfigB2C.setExecutionrate(0f);
	         rateConfigB2C.setUserid(userid);
	         rateConfigB2C.setCreator("system");
	         rateConfigB2C.setCreatetime(new Date());
	         rateConfigB2C.setExpiration(DateUtil.getDate(new Date(), 6));
	         
	         
	         TitanRateConfig rateConfigCREDIT = new TitanRateConfig();
	         rateConfigCREDIT.setBustype(BusTypeEnum.CREDIT_RATE.type);//1表示付款费率
	         rateConfigCREDIT.setDescription("信用卡网银支付费率");
	         rateConfigCREDIT.setRatetype(1);//按百分比
	         rateConfigCREDIT.setRsrate(0.2f);//千分之一点五
	         rateConfigCREDIT.setStandrate(0.3f);
	         rateConfigCREDIT.setExecutionrate(0f);
	         rateConfigCREDIT.setUserid(userid);
	         rateConfigCREDIT.setCreator("system");
	         rateConfigCREDIT.setCreatetime(new Date());
	         rateConfigCREDIT.setExpiration(DateUtil.getDate(new Date(), 6));
	         
	         
	         TitanRateConfig rateConfigQR = new TitanRateConfig();
	         rateConfigQR.setBustype(BusTypeEnum.QR_RATE.type);//1表示付款费率
	         rateConfigQR.setDescription("第三方支付费率");
	         rateConfigQR.setRatetype(1);//按百分比
	         rateConfigQR.setRsrate(0.4f);//千分之一点五
	         rateConfigQR.setStandrate(0.4f);
	         rateConfigQR.setExecutionrate(0f);
	         rateConfigQR.setUserid(userid);
	         rateConfigQR.setCreator("system");
	         rateConfigQR.setCreatetime(new Date());
	         rateConfigQR.setExpiration(DateUtil.getDate(new Date(), 6));
	         
	         
	         TitanRateConfig rateConfigWITHDRAW = new TitanRateConfig();
	         rateConfigWITHDRAW.setBustype(BusTypeEnum.WITHDRAW_RATE.type);//1表示付款费率
	         rateConfigWITHDRAW.setDescription("账户提现费率");
	         rateConfigWITHDRAW.setRatetype(2);//按笔收费
	         rateConfigWITHDRAW.setRsrate(3f);//每笔3元
	         rateConfigWITHDRAW.setStandrate(5f);//每笔5元
	         rateConfigWITHDRAW.setExecutionrate(0f);
	         rateConfigWITHDRAW.setUserid(userid);
	         rateConfigWITHDRAW.setCreator("system");
	         rateConfigWITHDRAW.setCreatetime(new Date());
	         rateConfigWITHDRAW.setExpiration(DateUtil.getDate(new Date(), 6));
	         
	         List<TitanRateConfig> titanRateConfigs = new ArrayList<TitanRateConfig>();
	         titanRateConfigs.add(rateConfigB2B);
	         titanRateConfigs.add(rateConfigB2C);
	         titanRateConfigs.add(rateConfigCREDIT);
	         titanRateConfigs.add(rateConfigQR);
	         titanRateConfigs.add(rateConfigWITHDRAW);
	         titanRateConfigDao.batchSaveRateConfigs(titanRateConfigs);
		}
		
	}

}
