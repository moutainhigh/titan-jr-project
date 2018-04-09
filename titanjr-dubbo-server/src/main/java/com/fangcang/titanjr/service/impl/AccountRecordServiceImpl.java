package com.fangcang.titanjr.service.impl;



import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderKindEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.TitanAccountDetailDao;
import com.fangcang.titanjr.dao.TitanBalanceInfoDao;
import com.fangcang.titanjr.dao.TitanDepositDetailDao;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.request.RecordRequest;
import com.fangcang.titanjr.dto.request.RecordTransferRequest;
import com.fangcang.titanjr.entity.TitanAccountDetail;
import com.fangcang.titanjr.entity.TitanBalanceInfo;
import com.fangcang.titanjr.entity.TitanDepositDetail;
import com.fangcang.titanjr.entity.parameter.TitanAccountDetailParam;
import com.fangcang.titanjr.entity.parameter.TitanBalanceInfoParam;
import com.fangcang.titanjr.enums.TradeTypeAccountDetailEnum;
import com.fangcang.titanjr.service.AccountRecordService;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.github.pagehelper.StringUtil;

import net.sf.json.JSONSerializer;

@Service("accountRecordService")
public class AccountRecordServiceImpl implements AccountRecordService {
	
	private static final Logger LOGGER = Logger.getLogger(AccountRecordServiceImpl.class);
	
	@Resource
	private TitanBalanceInfoDao balanceInfoDao;
	
	@Resource
	private TitanAccountDetailDao accountDetailDao;
	
	@Resource
	private TitanDepositDetailDao depositDetailDao;
	
	@Resource
    private TitanFinancialUtilService titanFinancialUtilService;
	
	/**
	 * 更新余额
	 */
	private void updateBalanceInfo(TitanAccountDetail accountDetail){
		
		TitanBalanceInfoParam param = new TitanBalanceInfoParam();
		param.setAccountcode(accountDetail.getAccountCode());
		List<TitanBalanceInfo> balanceInfoList = balanceInfoDao.queryList(param);
		TitanBalanceInfo balanceInfo = balanceInfoList.get(0);
		
		balanceInfo.setSettleamount(balanceInfo.getSettleamount()+accountDetail.getSettleAmount());
		balanceInfo.setUsablelimit(balanceInfo.getSettleamount());//两个字段的意思是相同的
		balanceInfo.setFrozonamount(balanceInfo.getFrozonamount()+accountDetail.getFrozonAmount());
		balanceInfo.setTotalamount(balanceInfo.getSettleamount()+balanceInfo.getFrozonamount());
		balanceInfo.setCreditamount(accountDetail.getCreditAmount());
		//检查账户余额，如果小于零，则发送异常邮件。
		if(balanceInfo.getSettleamount()<0){
			LOGGER.error("账户余额错误,记账时,可提现余额小于0,账户变动记录accountDetail:"+Tools.gsonToString(accountDetail));
			String transOrderId = String.valueOf(accountDetail.getTransOrderId());
			OrderKindEnum orderKind = OrderKindEnum.TransOrderId;
			if(StringUtil.isNotEmpty(accountDetail.getUserOrderId())){
				transOrderId = accountDetail.getUserOrderId();
				orderKind = OrderKindEnum.UserOrderId;
			}
			titanFinancialUtilService.saveOrderException(transOrderId,orderKind, OrderExceptionEnum.AccountRecord_Settleamount_Error, JSONSerializer.toJSON(accountDetail).toString());
		}else{//大于0才修改账户余额
			balanceInfoDao.update(balanceInfo);
		}
		
		//保存本次记账后的总金额
		accountDetail.setTotalCreditAmount(balanceInfo.getCreditamount());
		accountDetail.setTotalFrozonAmount(balanceInfo.getFrozonamount());
		accountDetail.setTotalSettleAmount(balanceInfo.getSettleamount());
		
		accountDetailDao.insert(accountDetail);
		
	}
	/***
	 * 检查是否已经过记账
	 * @return true：已经记过账，false:未记账
	 */
	private boolean checkIsRecord(RecordRequest recordRequest,int tradeType){
		TitanAccountDetailParam param = new TitanAccountDetailParam();
		param.setTransOrderId(recordRequest.getTransOrderId());
		param.setUserOrderId(recordRequest.getUserOrderId());
		param.setOrgCode(recordRequest.getUserId());
		param.setProductId(recordRequest.getProductId());
		param.setTradeType(tradeType);
		param.setStatus(1);
		
		List<TitanAccountDetail> accountDetailList = accountDetailDao.selectList(param);
		if(CollectionUtils.isNotEmpty(accountDetailList)){
			LOGGER.error("该笔交易已经记账，无需重复记账，记账参数recordRechargeRequest："+Tools.gsonToString(recordRequest)+",记账类型tradeType:"+tradeType);
			return true;
		}
		return false;
		
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public BaseResponseDTO recharge(RecordRequest recordRequest) {
		LOGGER.info("[充值]记账,请求参数recordRechargeRequest:"+Tools.gsonToString(recordRequest));
		BaseResponseDTO responseDTO = new BaseResponseDTO();
		int tradeType = TradeTypeAccountDetailEnum.RECHARGE.getTradeType();
		// 交易验证
		if(checkIsRecord(recordRequest,tradeType)){
			responseDTO.putErrorResult("该笔交易已经记账");
			return responseDTO;
		}
		
		TitanBalanceInfoParam balanceInfoParam = new TitanBalanceInfoParam();
		balanceInfoParam.setUserid(recordRequest.getUserId());
		balanceInfoParam.setProductid(recordRequest.getProductId());
		List<TitanBalanceInfo> balanceInfoList = balanceInfoDao.queryList(balanceInfoParam);
		if(CollectionUtils.isEmpty(balanceInfoList)){
			LOGGER.error("充值记账失败，账户不存在。参数："+Tools.gsonToString(recordRequest));
			responseDTO.putErrorResult("记账失败，账户不存在");
			return responseDTO;
		}
		TitanBalanceInfo balanceInfo = balanceInfoList.get(0);
		TitanAccountDetail accountDetail = new TitanAccountDetail();
		accountDetail.setAccountCode(balanceInfo.getAccountcode());
		accountDetail.setTransOrderId(recordRequest.getTransOrderId());
		accountDetail.setUserOrderId(recordRequest.getUserOrderId());
		accountDetail.setTradeType(tradeType);
		accountDetail.setOrgCode(recordRequest.getUserId());
		accountDetail.setProductId(recordRequest.getProductId());
		accountDetail.setCreditAmount(0L);
		accountDetail.setFrozonAmount(0L);
		accountDetail.setSettleAmount(recordRequest.getAmount());
		accountDetail.setStatus(1);
		accountDetail.setRemark("充值");
		accountDetail.setCreateTime(new Date());
		updateBalanceInfo(accountDetail);
		//备付金
		TitanDepositDetail depositDetail = new TitanDepositDetail();
		depositDetail.setAccountcode(CommonConstant.DEPOSIT_ACCOUNT_CODE);//固定账户
		depositDetail.setTransorderid(recordRequest.getTransOrderId());
		depositDetail.setAmount(recordRequest.getAmount());
		depositDetail.setTradetype(tradeType);
		depositDetail.setFee(0L);
		depositDetail.setStatus(1);
		depositDetail.setCreatetime(new Date());
		depositDetailDao.insert(depositDetail);
		
		responseDTO.putSuccess("记账成功");
		return responseDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public BaseResponseDTO transfer(RecordTransferRequest recordTransferRequest) throws GlobalServiceException {
		LOGGER.info("[转账]记账,请求参数recordTransferRequest:"+Tools.gsonToString(recordTransferRequest));
		BaseResponseDTO responseDTO = new BaseResponseDTO();
		int tradeType = TradeTypeAccountDetailEnum.TRANSFER.getTradeType();
		// 交易验证
		RecordRequest recordRequest = new RecordRequest();
		recordRequest.setUserId(recordTransferRequest.getUserId());
		recordRequest.setProductId(recordTransferRequest.getProductId());
		recordRequest.setTransOrderId(recordTransferRequest.getTransOrderId());
		recordRequest.setUserOrderId(recordTransferRequest.getUserOrderId());
		//判断转出是否已经记账
		if(checkIsRecord(recordRequest,tradeType)){
			responseDTO.putErrorResult("该笔交易已经记账");
			return responseDTO;
		}
		//1-转出
		TitanBalanceInfoParam balanceInfoParam = new TitanBalanceInfoParam();
		balanceInfoParam.setUserid(recordTransferRequest.getUserId());
		balanceInfoParam.setProductid(recordTransferRequest.getProductId());
		List<TitanBalanceInfo> balanceInfoList = balanceInfoDao.queryList(balanceInfoParam);
		if(CollectionUtils.isEmpty(balanceInfoList)){
			LOGGER.error("[转账]转出记账失败，转出账户不存在。参数："+Tools.gsonToString(recordTransferRequest));
			responseDTO.putErrorResult("记账失败，转出账户不存在");
			return responseDTO;
		}
		TitanBalanceInfo balanceInfo = balanceInfoList.get(0);
		TitanAccountDetail accountDetail = new TitanAccountDetail();
		accountDetail.setAccountCode(balanceInfo.getAccountcode());
		accountDetail.setTransOrderId(recordTransferRequest.getTransOrderId());
		accountDetail.setUserOrderId(recordTransferRequest.getUserOrderId());
		accountDetail.setTradeType(tradeType);
		accountDetail.setOrgCode(recordTransferRequest.getUserId());
		accountDetail.setProductId(recordTransferRequest.getProductId());
		accountDetail.setCreditAmount(0L);
		accountDetail.setFrozonAmount(0L);
		accountDetail.setSettleAmount(-recordTransferRequest.getAmount());
		accountDetail.setStatus(1);
		accountDetail.setCreateTime(new Date());
		accountDetail.setRemark("转账");
		updateBalanceInfo(accountDetail);
		
		//2-转入
		TitanBalanceInfoParam balanceInfoInParam = new TitanBalanceInfoParam();
		balanceInfoInParam.setUserid(recordTransferRequest.getRelateUserId());
		balanceInfoInParam.setProductid(recordTransferRequest.getRelateProductId());
		List<TitanBalanceInfo> balanceInfoInList = balanceInfoDao.queryList(balanceInfoInParam);
		if(CollectionUtils.isEmpty(balanceInfoInList)){
			LOGGER.error("[转账]转入记账失败，转入账户不存在。参数："+Tools.gsonToString(recordTransferRequest));
			throw new GlobalServiceException("记账失败，转入账户不存在,账户为userId："+recordTransferRequest.getRelateUserId()+",productId:"+recordTransferRequest.getRelateProductId());
		}
		TitanBalanceInfo balanceInfoIn = balanceInfoInList.get(0);
		TitanAccountDetail accountDetailIn = new TitanAccountDetail();
		accountDetailIn.setAccountCode(balanceInfoIn.getAccountcode());
		accountDetailIn.setTransOrderId(recordTransferRequest.getTransOrderId());
		accountDetailIn.setUserOrderId(recordTransferRequest.getUserOrderId());
		accountDetailIn.setTradeType(tradeType);
		accountDetailIn.setOrgCode(recordTransferRequest.getRelateUserId());
		accountDetailIn.setProductId(recordTransferRequest.getRelateProductId());
		accountDetailIn.setCreditAmount(0L);
		accountDetailIn.setFrozonAmount(0L);
		accountDetailIn.setSettleAmount(recordTransferRequest.getAmount());
		accountDetailIn.setStatus(1);
		accountDetailIn.setCreateTime(new Date());
		accountDetailIn.setRemark("转账");
		updateBalanceInfo(accountDetailIn);
		responseDTO.putSuccess("记账成功");
		return responseDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public BaseResponseDTO freeze(RecordRequest recordRequest) {
		LOGGER.info("[冻结]记账,请求参数recordRequest:"+Tools.gsonToString(recordRequest));
		BaseResponseDTO responseDTO = new BaseResponseDTO();
		int tradeType = TradeTypeAccountDetailEnum.FREEZEE.getTradeType();
		//同一个订单允许多次冻结，解冻
		//if(checkIsRecord(recordRequest,tradeType)){
		//	responseDTO.putErrorResult("该笔交易已经记账");
		//	return responseDTO;
		//}
		TitanBalanceInfoParam balanceInfoParam = new TitanBalanceInfoParam();
		balanceInfoParam.setUserid(recordRequest.getUserId());
		balanceInfoParam.setProductid(recordRequest.getProductId());
		List<TitanBalanceInfo> balanceInfoList = balanceInfoDao.queryList(balanceInfoParam);
		if(CollectionUtils.isEmpty(balanceInfoList)){
			LOGGER.error("[冻结]记账失败，账户不存在。参数："+Tools.gsonToString(recordRequest));
			responseDTO.putErrorResult("记账失败，账户不存在");
			return responseDTO;
		}
		TitanBalanceInfo balanceInfo = balanceInfoList.get(0);
		TitanAccountDetail accountDetail = new TitanAccountDetail();
		accountDetail.setAccountCode(balanceInfo.getAccountcode());
		accountDetail.setTransOrderId(recordRequest.getTransOrderId());
		accountDetail.setUserOrderId(recordRequest.getUserOrderId());
		accountDetail.setTradeType(tradeType);
		accountDetail.setOrgCode(recordRequest.getUserId());
		accountDetail.setProductId(recordRequest.getProductId());
		accountDetail.setCreditAmount(0L);
		accountDetail.setFrozonAmount(recordRequest.getAmount());
		accountDetail.setSettleAmount(-recordRequest.getAmount());
		accountDetail.setStatus(1);
		accountDetail.setCreateTime(new Date());
		accountDetail.setRemark("冻结");
		updateBalanceInfo(accountDetail);
		responseDTO.putSuccess("记账成功");
		return responseDTO;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public BaseResponseDTO unFreeze(RecordRequest recordRequest) {
		LOGGER.info("[解冻]记账,请求参数recordRequest:"+Tools.gsonToString(recordRequest));
		BaseResponseDTO responseDTO = new BaseResponseDTO();
		int tradeType = TradeTypeAccountDetailEnum.UNFREEZEE.getTradeType();
		////同一个订单允许多次冻结，解冻
//		if(checkIsRecord(recordRequest,tradeType)){
//			responseDTO.putErrorResult("该笔交易已经记账");
//			return responseDTO;
//		}
		TitanBalanceInfoParam balanceInfoParam = new TitanBalanceInfoParam();
		balanceInfoParam.setUserid(recordRequest.getUserId());
		balanceInfoParam.setProductid(recordRequest.getProductId());
		List<TitanBalanceInfo> balanceInfoList = balanceInfoDao.queryList(balanceInfoParam);
		if(CollectionUtils.isEmpty(balanceInfoList)){
			LOGGER.error("[解冻]记账失败，账户不存在。参数："+Tools.gsonToString(recordRequest));
			responseDTO.putErrorResult("记账失败，账户不存在");
			return responseDTO;
		}
		TitanBalanceInfo balanceInfo = balanceInfoList.get(0);
		TitanAccountDetail accountDetail = new TitanAccountDetail();
		accountDetail.setAccountCode(balanceInfo.getAccountcode());
		accountDetail.setTransOrderId(recordRequest.getTransOrderId());
		accountDetail.setUserOrderId(recordRequest.getUserOrderId());
		accountDetail.setTradeType(tradeType);
		accountDetail.setOrgCode(recordRequest.getUserId());
		accountDetail.setProductId(recordRequest.getProductId());
		accountDetail.setCreditAmount(0L);
		accountDetail.setFrozonAmount(-recordRequest.getAmount());
		accountDetail.setSettleAmount(recordRequest.getAmount());
		accountDetail.setStatus(1);
		accountDetail.setCreateTime(new Date());
		accountDetail.setRemark("解冻");
		updateBalanceInfo(accountDetail);
		responseDTO.putSuccess("记账成功");
		return responseDTO;

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, rollbackFor = Exception.class)
	public BaseResponseDTO withdraw(RecordRequest recordRequest) {
		LOGGER.info("[提现]记账,请求参数recordRequest:"+Tools.gsonToString(recordRequest));
		BaseResponseDTO responseDTO = new BaseResponseDTO();
		int tradeType = TradeTypeAccountDetailEnum.WITHDRAW.getTradeType();
		//交易验证,判断转出是否已经记账
		if(checkIsRecord(recordRequest,tradeType)){
			responseDTO.putErrorResult("该笔交易已经记账");
			return responseDTO;
		}
		
		//主账户扣款记账
		TitanBalanceInfoParam balanceInfoParam = new TitanBalanceInfoParam();
		balanceInfoParam.setUserid(recordRequest.getUserId());
		balanceInfoParam.setProductid(recordRequest.getProductId());
		List<TitanBalanceInfo> balanceInfoList = balanceInfoDao.queryList(balanceInfoParam);
		if(CollectionUtils.isEmpty(balanceInfoList)){
			LOGGER.error("[提现]记账失败，账户不存在。参数："+Tools.gsonToString(recordRequest));
			responseDTO.putErrorResult("记账失败，账户不存在");
			return responseDTO;
		}
		TitanBalanceInfo balanceInfo = balanceInfoList.get(0);
		TitanAccountDetail accountDetail = new TitanAccountDetail();
		accountDetail.setAccountCode(balanceInfo.getAccountcode());
		accountDetail.setTransOrderId(recordRequest.getTransOrderId());
		accountDetail.setUserOrderId(recordRequest.getUserOrderId());
		accountDetail.setTradeType(tradeType);
		accountDetail.setOrgCode(recordRequest.getUserId());
		accountDetail.setProductId(recordRequest.getProductId());
		accountDetail.setCreditAmount(0L);
		accountDetail.setFrozonAmount(0L);
		accountDetail.setSettleAmount(-(recordRequest.getAmount()+recordRequest.getFee()));//到卡金额+手续费
		accountDetail.setStatus(1);
		accountDetail.setCreateTime(new Date());
		accountDetail.setRemark("提现");
		updateBalanceInfo(accountDetail);
		
		if(recordRequest.getFee()>0){//一般情况不存在，因为手续费是通过转账记得
			//手续费记账
			TitanBalanceInfoParam feeBalanceInfoParam = new TitanBalanceInfoParam();
			feeBalanceInfoParam.setUserid(CommonConstant.RS_FANGCANG_USER_ID);
			feeBalanceInfoParam.setProductid(CommonConstant.RS_FANGCANG_PRODUCT_ID_229);
			List<TitanBalanceInfo> feeBalanceInfoList = balanceInfoDao.queryList(feeBalanceInfoParam);
			
			TitanAccountDetail accountDetailFee = new TitanAccountDetail();
			accountDetailFee.setAccountCode(feeBalanceInfoList.get(0).getAccountcode());
			accountDetailFee.setTransOrderId(recordRequest.getTransOrderId());
			accountDetailFee.setUserOrderId(recordRequest.getUserOrderId());
			accountDetailFee.setTradeType(tradeType);
			accountDetailFee.setOrgCode(CommonConstant.RS_FANGCANG_USER_ID);
			accountDetailFee.setProductId(CommonConstant.RS_FANGCANG_PRODUCT_ID_229);
			accountDetailFee.setCreditAmount(0L);
			accountDetailFee.setFrozonAmount(0L);
			accountDetailFee.setSettleAmount(recordRequest.getFee());
			accountDetailFee.setStatus(1);
			accountDetailFee.setCreateTime(new Date());
			accountDetailFee.setRemark("提现手续费");
			updateBalanceInfo(accountDetailFee);
		}
		
		
		//备付金
		TitanDepositDetail depositDetail = new TitanDepositDetail();
		depositDetail.setAccountcode(CommonConstant.DEPOSIT_ACCOUNT_CODE);//固定账户
		depositDetail.setTransorderid(recordRequest.getTransOrderId());
		depositDetail.setAmount(-recordRequest.getAmount());//
		depositDetail.setTradetype(tradeType);
		depositDetail.setFee(0L);
		depositDetail.setStatus(1);
		depositDetail.setCreatetime(new Date());
		depositDetailDao.insert(depositDetail);
		responseDTO.putSuccess("记账成功");
		return responseDTO;
		
	}

	@Override
	public BaseResponseDTO refund(RecordRequest recordRequest) {
		LOGGER.info("[退款]记账,请求参数recordRequest:"+Tools.gsonToString(recordRequest));
		BaseResponseDTO responseDTO = new BaseResponseDTO();
		int tradeType = TradeTypeAccountDetailEnum.REFUND.getTradeType();
		//交易验证,判断转出是否已经记账
		if(checkIsRecord(recordRequest,tradeType)){
			responseDTO.putErrorResult("该笔交易已经记账");
			return responseDTO;
		}
		
		//主账户扣款记账
		TitanBalanceInfoParam balanceInfoParam = new TitanBalanceInfoParam();
		balanceInfoParam.setUserid(recordRequest.getUserId());
		balanceInfoParam.setProductid(recordRequest.getProductId());
		List<TitanBalanceInfo> balanceInfoList = balanceInfoDao.queryList(balanceInfoParam);
		if(CollectionUtils.isEmpty(balanceInfoList)){
			LOGGER.error("[退款]记账失败，账户不存在。参数："+Tools.gsonToString(recordRequest));
			responseDTO.putErrorResult("记账失败，账户不存在");
			return responseDTO;
		}
		TitanBalanceInfo balanceInfo = balanceInfoList.get(0);
		TitanAccountDetail accountDetail = new TitanAccountDetail();
		accountDetail.setAccountCode(balanceInfo.getAccountcode());
		accountDetail.setTransOrderId(recordRequest.getTransOrderId());
		accountDetail.setUserOrderId(recordRequest.getUserOrderId());
		accountDetail.setTradeType(tradeType);
		accountDetail.setOrgCode(recordRequest.getUserId());
		accountDetail.setProductId(recordRequest.getProductId());
		accountDetail.setCreditAmount(0L);
		accountDetail.setFrozonAmount(0L);
		accountDetail.setSettleAmount(-recordRequest.getAmount());
		accountDetail.setStatus(1);
		accountDetail.setCreateTime(new Date());
		accountDetail.setRemark("退款");
		updateBalanceInfo(accountDetail);
		
		//备付金记账
		TitanDepositDetail depositDetail = new TitanDepositDetail();
		depositDetail.setAccountcode(CommonConstant.DEPOSIT_ACCOUNT_CODE);//固定账户
		depositDetail.setTransorderid(recordRequest.getTransOrderId());
		depositDetail.setAmount(-recordRequest.getAmount());
		depositDetail.setTradetype(tradeType);
		depositDetail.setFee(0L);
		depositDetail.setStatus(1);
		depositDetail.setCreatetime(new Date());
		depositDetailDao.insert(depositDetail);
		responseDTO.putSuccess("记账成功");
		return responseDTO;
	}
	
	
	@Override
	public BaseResponseDTO refundBack(RecordRequest recordRequest) throws GlobalServiceException {
		LOGGER.info("[退款金额退回]记账,请求参数recordRequest:"+Tools.gsonToString(recordRequest));
		BaseResponseDTO responseDTO = new BaseResponseDTO();
		int tradeType = TradeTypeAccountDetailEnum.REFUND.getTradeType();
		//交易验证,判断转出是否已经记账
		if(!checkIsRecord(recordRequest,tradeType)){
			LOGGER.error("该笔退款无[退款记账]，无法退回退款，请确认.请求参数:"+Tools.gsonToString(recordRequest));
			responseDTO.putErrorResult("该笔交易无退款记账");
			return responseDTO;
		}
		int recordTradeType = TradeTypeAccountDetailEnum.REFUND_BACK.getTradeType();
		//是否已经有退款退回记账
		if(checkIsRecord(recordRequest,recordTradeType)){
			responseDTO.putErrorResult("该笔交易已经记账");
			return responseDTO;
		}
		//主账户
		TitanBalanceInfoParam balanceInfoParam = new TitanBalanceInfoParam();
		balanceInfoParam.setUserid(recordRequest.getUserId());
		balanceInfoParam.setProductid(recordRequest.getProductId());
		List<TitanBalanceInfo> balanceInfoList = balanceInfoDao.queryList(balanceInfoParam);
		if(CollectionUtils.isEmpty(balanceInfoList)){
			LOGGER.error("[退款金额退回]记账失败，账户不存在。参数："+Tools.gsonToString(recordRequest));
			responseDTO.putErrorResult("记账失败，账户不存在");
			return responseDTO;
		}
		TitanBalanceInfo balanceInfo = balanceInfoList.get(0);
		TitanAccountDetail accountDetail = new TitanAccountDetail();
		accountDetail.setAccountCode(balanceInfo.getAccountcode());
		accountDetail.setTransOrderId(recordRequest.getTransOrderId());
		accountDetail.setUserOrderId(recordRequest.getUserOrderId());
		accountDetail.setTradeType(recordTradeType);
		accountDetail.setOrgCode(recordRequest.getUserId());
		accountDetail.setProductId(recordRequest.getProductId());
		accountDetail.setCreditAmount(0L);
		accountDetail.setFrozonAmount(0L);
		accountDetail.setSettleAmount(recordRequest.getAmount());
		accountDetail.setStatus(1);
		accountDetail.setCreateTime(new Date());
		accountDetail.setRemark("退款金额退回");
		updateBalanceInfo(accountDetail);
		
		//备付金记账
		TitanDepositDetail depositDetail = new TitanDepositDetail();
		depositDetail.setAccountcode(CommonConstant.DEPOSIT_ACCOUNT_CODE);//固定账户
		depositDetail.setTransorderid(recordRequest.getTransOrderId());
		depositDetail.setAmount(recordRequest.getAmount());
		depositDetail.setTradetype(recordTradeType);
		depositDetail.setFee(0L);
		depositDetail.setStatus(1);
		depositDetail.setCreatetime(new Date());
		depositDetailDao.insert(depositDetail);
		responseDTO.putSuccess("记账成功");
		return responseDTO;
		
	}
	
	@Override
	public BaseResponseDTO withdrawBack(RecordRequest recordRequest) throws GlobalServiceException {
		//TODO 
		
		
		
		
		return null;
	}
	
}
