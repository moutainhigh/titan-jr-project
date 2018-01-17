package com.fangcang.titanjr.service.impl;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.dao.BenchmarkRateConfigDao;
import com.fangcang.titanjr.dao.TitanCashierDeskDao;
import com.fangcang.titanjr.dao.TitanRateConfigDao;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.PaySourceEnum;
import com.fangcang.titanjr.dto.bean.TitanRateDto;
import com.fangcang.titanjr.dto.request.CreateRateRecordRequest;
import com.fangcang.titanjr.dto.request.RateConfigRequest;
import com.fangcang.titanjr.dto.response.CreateRateRecordResponse;
import com.fangcang.titanjr.dto.response.RateConfigResponse;
import com.fangcang.titanjr.dto.response.RateRecordResponse;
import com.fangcang.titanjr.entity.BenchmarkRateConfig;
import com.fangcang.titanjr.entity.TitanCashierDesk;
import com.fangcang.titanjr.entity.TitanRateConfig;
import com.fangcang.titanjr.entity.TitanRateRecord;
import com.fangcang.titanjr.entity.parameter.TitanRateConfigParam;
import com.fangcang.titanjr.service.TitanFinancialRateService;
import com.fangcang.util.DateUtil;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;

@Service("titanFinancialRateService")
public class TitanFinancialRateServiceImpl implements TitanFinancialRateService {
	
	private static final Log logger = LogFactory.getLog(TitanFinancialRateServiceImpl.class);

	@Resource
	private TitanRateConfigDao titanRateConfigDao;
	
	@Resource
	private BenchmarkRateConfigDao benchmarkRateConfigDao;
	
	@Resource
    TitanCashierDeskDao titanCashierDeskDao;

	/**
	 * 查询费率配置信息
	 */
	@Override
	public RateConfigResponse getRateConfigInfos(RateConfigRequest req) {

		RateConfigResponse configResponse = new RateConfigResponse();
		configResponse.putSuccess();

		TitanRateConfigParam configParam = new TitanRateConfigParam();
		configParam.setUserid(req.getUserId());
		configParam.setDeskid(req.getDeskId());
		
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
	public BenchmarkRateConfig queryBenchmarkRateConfig(BenchmarkRateConfig benchmarkRateConfig) {
		
		BenchmarkRateConfig brConfigRespone = new BenchmarkRateConfig();
		brConfigRespone = benchmarkRateConfigDao.queryBenchmarkRateConfig(benchmarkRateConfig);
		return brConfigRespone;
		
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
	public BaseResponseDTO initRateData() {
		
		BaseResponseDTO baseResponseDTO = new BaseResponseDTO();
		List<TitanRateConfig> rateConfigList = new ArrayList<TitanRateConfig>();
		
		
		
		//按商家排序查询所有收银台
		List<TitanCashierDesk> titanCashierDeskkList = titanCashierDeskDao.queryAllCashierDeskOrderBy();
		if(CollectionUtils.isEmpty(titanCashierDeskkList)){
			logger.info("查询收银台列表为空");
			baseResponseDTO.putErrorResult("费率初始化失败，查询收银台列表为空");
			return baseResponseDTO;
		}
		logger.info(MessageFormat.format("总共查询到{0}条收银台记录", titanCashierDeskkList.size()));
		
		String userIdTemp = "";
		for (int i = 0; i < titanCashierDeskkList.size(); i++) {
			TitanCashierDesk titanCashierDesk = titanCashierDeskkList.get(i);
			if(i == 0){
				userIdTemp = titanCashierDesk.getUserid();
			}
			String userId = titanCashierDesk.getUserid();
			
			rateConfigList.add(buildPayRateConfig(titanCashierDesk));
			//为上一个商家增加提现收银台费率
			if(!userId.equals(userIdTemp)){
				titanCashierDesk = titanCashierDeskkList.get(i-1);
				titanCashierDesk.setDeskid(null);
				rateConfigList.add(buildPayRateConfig(titanCashierDesk));
				userIdTemp = userId;
			}
			//为最后一个商家增加提现收银台费率
			if(i == titanCashierDeskkList.size() -1){
				titanCashierDesk = titanCashierDeskkList.get(i);
				titanCashierDesk.setDeskid(null);
				rateConfigList.add(buildPayRateConfig(titanCashierDesk));
			}
		}
		logger.info(MessageFormat.format("总共构建出{0}条费率配置", rateConfigList.size()));
		
		//先删除费率表数据
		titanRateConfigDao.truncateRateConfig();
		logger.info("已清除原来的费率费率配置");
		//插入
		int insertResult = titanRateConfigDao.batchSaveRateConfigs(rateConfigList);
		logger.info(MessageFormat.format("初始化收银台费率成功，一共插入{0}条费率配置", insertResult));
		
		baseResponseDTO.putSuccess("初始化收银台费率成功，一共插入" + insertResult + "条费率配置");
		return baseResponseDTO;
		
	}
	
	/**
	 * 根据商家收银台配置费率
	 * @author Jerry
	 * @date 2017年11月22日 上午10:50:49
	 */
	private TitanRateConfig buildPayRateConfig(TitanCashierDesk titanCashierDesk) {
        TitanRateConfig rateConfig = new TitanRateConfig();
        rateConfig.setUserid(titanCashierDesk.getUserid());
        if(titanCashierDesk.getDeskid() == null || titanCashierDesk.getDeskid() == 0){//提现没有实际的收银台，但是要配置费率
        	rateConfig.setDeskid("TX");
        	rateConfig.setUsedfor(PaySourceEnum.WITHDRAW_PC.getDeskCode());
        	rateConfig.setDescription("提现收银台");
            rateConfig.setRatetype(CommonConstant.RATETYPE_FIXATION);
            rateConfig.setStandrate(5f);
            rateConfig.setExecutionrate(0f);
        }else{
    		rateConfig.setDeskid(String.valueOf(titanCashierDesk.getDeskid()));
    		rateConfig.setUsedfor(String.valueOf(titanCashierDesk.getUsedfor()));
            rateConfig.setDescription(titanCashierDesk.getDeskname());
            rateConfig.setRatetype(CommonConstant.RATETYPE_PERCENT);
            rateConfig.setStandrate(0.4f);
            rateConfig.setExecutionrate(0f);
        	if(titanCashierDesk.getUsedfor() != null){
        		if(titanCashierDesk.getUsedfor() == 5){
        			rateConfig.setStandrate(0f);//充值免费
        		}
        		if(titanCashierDesk.getUsedfor() == 6){
        			rateConfig.setExecutionrate(0.4f);//对外开放收银台收千分四
        		}
            }
        }
        rateConfig.setCreator("system");
        rateConfig.setCreatetime(new Date());
        rateConfig.setExpiration(DateUtil.stringToDate("2017-12-31"));

        return rateConfig;
    }

}
