package com.fangcang.titanjr.service;

import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.request.CreateRateRecordRequest;
import com.fangcang.titanjr.dto.request.RateConfigRequest;
import com.fangcang.titanjr.dto.response.CreateRateRecordResponse;
import com.fangcang.titanjr.dto.response.RateConfigResponse;
import com.fangcang.titanjr.dto.response.RateRecordResponse;
import com.fangcang.titanjr.entity.BenchmarkRateConfig;

/**
 * @ClassName: TitanFinancialRateService
 * @Description: 费率相关服务定义
 * @author: wengxitao
 * @date: 2016年8月15日 上午9:51:28 
 */
public interface TitanFinancialRateService 
{
	/**
	 * @Title: getRateConfigInfo  
	 * @Description:查询费率配置信息 
	 * @param rateConfigRequest
	 * @return 
	 * @return: RateConfigResponse
	 */
	public RateConfigResponse getRateConfigInfos(RateConfigRequest req);
	
	/**
	 * 查询唯一的基准费率
	 * @author Jerry
	 * @date 2017年11月20日 下午2:58:58
	 * @param benchmarkRateConfig
	 * @return
	 */
	public BenchmarkRateConfig queryBenchmarkRateConfig(BenchmarkRateConfig benchmarkRateConfig);
	
	
//	/**
//	 * 
//	 * @Title: getRateConfigInfos 
//	 * @Description: 更新费率配置信息
//	 * @param req
//	 * @return
//	 * @return: UpdateRateConfigResponse
//	 */
//	public UpdateRateConfigResponse getRateConfigInfos(UpdateRateConfigRequest req);
//	
	/**
	 * 
	 * @Title: getRateRecordInfos 
	 * @Description: 获取费率的信息
	 * @param req
	 * @return
	 * @return: RateRecordResponse
	 */
	public RateRecordResponse getRateRecordInfos(RateConfigRequest req);
	
	/**
	 * 
	 * @Title: createRateRecord 
	 * @Description: 创建产生的费率信息
	 * @param req
	 * @return
	 * @return: CreateRateRecordResponse
	 */
	public CreateRateRecordResponse createRateRecord(CreateRateRecordRequest req);
	
	public BaseResponseDTO initRateData();
	
}
