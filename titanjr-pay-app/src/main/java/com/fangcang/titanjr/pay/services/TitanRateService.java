package com.fangcang.titanjr.pay.services;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.fangcang.titanjr.common.util.NumberUtil;
import com.fangcang.titanjr.dto.PaySourceEnum;
import com.fangcang.titanjr.dto.bean.TitanRateDto;
import com.fangcang.titanjr.dto.bean.TitanRateRecordDto;
import com.fangcang.titanjr.dto.request.AccountRequest;
import com.fangcang.titanjr.dto.request.CreateRateRecordRequest;
import com.fangcang.titanjr.dto.request.RateConfigRequest;
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;
import com.fangcang.titanjr.dto.response.AccountResponse;
import com.fangcang.titanjr.dto.response.RateConfigResponse;
import com.fangcang.titanjr.entity.BenchmarkRateConfig;
import com.fangcang.titanjr.enums.PayTypeEnum;
import com.fangcang.titanjr.pay.constant.TitanConstantDefine;
import com.fangcang.titanjr.pay.req.CreateTitanRateRecordReq;
import com.fangcang.titanjr.pay.req.TitanRateComputeReq;
import com.fangcang.titanjr.pay.rsp.TitanRateComputeRsp;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialRateService;
import com.fangcang.util.StringUtil;

/**
 * 
 * @author wengxitao
 *
 */
@Component
public class TitanRateService {
	@Resource
	private TitanFinancialRateService titanFinancialRateService;

	@Resource
	private TitanFinancialAccountService titanFinancialAccountService;

	/**
	 * 费率计算
	 * 
	 * @param computeReq
	 */
	public TitanRateComputeRsp rateCompute(TitanRateComputeReq computeReq) {
		TitanRateComputeRsp computeRsp = new TitanRateComputeRsp();

		RateConfigRequest req = new RateConfigRequest();
		req.setUserId(computeReq.getUserId());
		req.setDeskId(computeReq.getDeskId());
		if(!StringUtil.isValidString(computeReq.getDeskId())){
			req.setDeskId("TX");
		}
		//查询房仓费率配置
		RateConfigResponse rateConfigResponse = titanFinancialRateService
				.getRateConfigInfos(req);
		
		//查询基准费率配置
		BenchmarkRateConfig benchmarkRateConfig = new BenchmarkRateConfig();
		benchmarkRateConfig.setPaychannel(TitanConstantDefine.TLPAY_CHANNEL_CODE);//通联渠道
		if(PayTypeEnum.QUICK_PAY_NEW.getLinePayType().equals(computeReq.getPayType())){
			benchmarkRateConfig.setPaychannel(TitanConstantDefine.RBPAY_CHANNEL_CODE);//快捷支付对应融宝
		}
		benchmarkRateConfig.setPaytype(Integer.parseInt(computeReq.getPayType()));
		if(StringUtil.isValidString(computeReq.getCardType())){
			benchmarkRateConfig.setCardtype(Integer.parseInt(computeReq.getCardType()));
		}
		benchmarkRateConfig = titanFinancialRateService.queryBenchmarkRateConfig(benchmarkRateConfig);
		
		if (rateConfigResponse != null
				&& rateConfigResponse.getRateInfoList() != null
				&& rateConfigResponse.getRateInfoList().size() > 0
				&& benchmarkRateConfig != null) {

			TitanRateDto dto = rateConfigResponse.getRateInfoList().get(0);
			computeRsp.setExecutionRate("" + dto.getExecutionrate());
			computeRsp.setStandRate("" + dto.getStandrate());
			computeRsp.setBenchmarkRate("" + benchmarkRateConfig.getBmrate());
			computeRsp.setRateType(dto.getRatetype());
			
			BigDecimal amountBigDecimal = new BigDecimal(computeReq.getAmount());
			
			BigDecimal exBigDecimal = new BigDecimal(""
					+ dto.getExecutionrate());
			BigDecimal stBigDecimal = new BigDecimal("" + dto.getStandrate());
			BigDecimal brBigDecimal = new BigDecimal("" + benchmarkRateConfig.getBmrate());
		
			// 手续费类型1.百分比，2.金额每笔
			if (dto.getRatetype() == 1) {
				
				if (dto.getExecutionrate() > 0) {
					exBigDecimal = exBigDecimal.divide(new BigDecimal("100"));
				}

				if (dto.getStandrate() > 0) {
					stBigDecimal = stBigDecimal.divide(new BigDecimal("100"));
				}
				
				computeRsp.setExRateAmount(amountBigDecimal
						.multiply(exBigDecimal)
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

				computeRsp.setStRateAmount(amountBigDecimal
						.multiply(stBigDecimal)
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

				computeRsp.setAmount(amountBigDecimal
						.add(amountBigDecimal.multiply(exBigDecimal))
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString());

			} else if (dto.getRatetype() == 2) {

				computeRsp.setExRateAmount(exBigDecimal.setScale(2,
						BigDecimal.ROUND_HALF_UP).toString());

				computeRsp.setStRateAmount(stBigDecimal.setScale(2,
						BigDecimal.ROUND_HALF_UP).toString());

				computeRsp.setAmount(amountBigDecimal.add(exBigDecimal)
						.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
			}
			//基准费率类型  1.百分比，2.每笔固定值
			if(benchmarkRateConfig.getRatetype() == 1){
				if (benchmarkRateConfig.getBmrate() > 0) {
					brBigDecimal = brBigDecimal.divide(new BigDecimal("100"));
				}
				BigDecimal clRateAmount = amountBigDecimal.multiply(brBigDecimal)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal minRateAmount = new BigDecimal(benchmarkRateConfig.getMinrate()
						.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal maxRateAmount = new BigDecimal(benchmarkRateConfig.getMaxrate()
						.toString()).setScale(2, BigDecimal.ROUND_HALF_UP);
				if(minRateAmount.compareTo(clRateAmount) > 0){
					computeRsp.setBenchmarkRateAmount(minRateAmount.toString());
				}else if(maxRateAmount.compareTo(clRateAmount) == -1){
					computeRsp.setBenchmarkRateAmount(maxRateAmount.toString());
				}else{
					computeRsp.setBenchmarkRateAmount(clRateAmount.toString());
				}
				
				
			}else{
				computeRsp.setBenchmarkRateAmount(brBigDecimal.setScale(2,
						BigDecimal.ROUND_HALF_UP).toString());
			}
		}
		return computeRsp;
	}

	public void addRateRecord(CreateTitanRateRecordReq req) {

		CreateRateRecordRequest r = new CreateRateRecordRequest();
		TitanRateRecordDto dto = new TitanRateRecordDto();

		AccountRequest accountRequest = new AccountRequest();
		accountRequest.setUserid(req.getUserId());
		AccountResponse accountResponse = titanFinancialAccountService
				.getAccount(accountRequest);
		if (accountResponse != null && accountResponse.getAccountDTO() != null) {
			dto.setAccountCode(accountResponse.getAccountDTO().getAccountcode());

		}

		dto.setAmount(req.getAmount());
		dto.setCreateTime(new Timestamp(System.currentTimeMillis()));
		dto.setCreator(req.getCreator());
		dto.setCurrency(1);
		dto.setPayType(req.getPayType());
		dto.setRateType(req.getRateType());
		dto.setReceivablefee(req.getReceivablefee());
		dto.setReceivableRate(req.getReceivableRate());
		dto.setReceivedfee(req.getReceivedfee());
		dto.setReceivedRate(req.getReceivedRate());
		dto.setStandardRate(req.getStandardRate());
		dto.setStanderdfee(req.getStanderdfee());
		dto.setUsedFor(req.getUsedFor());
		dto.setUserId(req.getUserId());
		dto.setOrderNo(req.getOrderNo());
		r.setTitanRateRecordDto(dto);
		titanFinancialRateService.createRateRecord(r);
	}

	/**
	 * 设置费率信息
	 * 
	 * @param computeReq
	 * @param paymentRequest
	 * @return
	 */
	public TitanPaymentRequest setRateAmount(TitanRateComputeReq computeReq,
			TitanPaymentRequest paymentRequest) {
		
		computeReq.buildRequest(paymentRequest);

		// 执行费率计算
		TitanRateComputeRsp computeRsp = this.rateCompute(computeReq);
		// 设置费率信息
		if (computeRsp != null) {
			// 设置标准费率
			paymentRequest.setStandfee(NumberUtil.covertToCents(computeRsp
					.getStRateAmount()));
			// 设置应收费率
			paymentRequest.setReceivablefee(NumberUtil.covertToCents(computeRsp
					.getBenchmarkRateAmount()));
			// 设置执行费率
			paymentRequest.setReceivedfee(NumberUtil.covertToCents(computeRsp
					.getExRateAmount()));
			paymentRequest.setRateType(computeRsp.getRateType());

			if (StringUtil.isValidString(computeRsp.getStandRate())) {
				paymentRequest.setStandardrate(Float.parseFloat(computeRsp
						.getStandRate()));
			}

			if (StringUtil.isValidString(computeRsp.getExecutionRate())) {
				paymentRequest.setExecutionrate(Float.parseFloat(computeRsp
						.getExecutionRate()));
			}

			if (StringUtil.isValidString(computeRsp.getBenchmarkRate())) {
				paymentRequest.setReceivablerate(Float.parseFloat(computeRsp
						.getBenchmarkRate()));
			}

			// 财务付款，付款金额需要加上手续费（提现不会走到这）
			String paySource = paymentRequest.getPaySource();
			if (!PaySourceEnum.DISTRIBUTION_PC.getDeskCode().equals(paySource) 
					&& !PaySourceEnum.OPEN_PLATFORM_PC.getDeskCode().equals(paySource)
					&& !PaySourceEnum.TT_MALL_PC.getDeskCode().equals(paySource)
					&& !PaySourceEnum.TT_MALL_MOBILE.getDeskCode().equals(paySource)
					&& !PaySourceEnum.TRADING_PLATFORM_PC.getDeskCode().equals(paySource)
					&& !PaySourceEnum.RECHARGE.getDeskCode().equals(paySource)) {
				paymentRequest.setPayAmount(computeRsp.getAmount());
			}
		}
		return paymentRequest;
	}

}
