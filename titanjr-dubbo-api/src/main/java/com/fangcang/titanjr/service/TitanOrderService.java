package com.fangcang.titanjr.service;

import java.util.List;

import com.fangcang.titanjr.dto.bean.FundFreezeDTO;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanWithDrawDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.TransOrderResponse;

public interface TitanOrderService {

	/**
	 * 根据财务单，查询本地是否有对应的 落单
	 * 根据业务单号查询金服的交易工单
	 * @param transOrderRequest
	 * @return
	 */
	public List<TransOrderDTO> queryTransOrder(TransOrderRequest transOrderRequest);
	
	/**
	 * 获取TransOrderDTO
	 * @param transOrderRequest
	 * @return
	 */
	public TransOrderDTO queryTransOrderDTO(TransOrderRequest transOrderRequest);
	
	
	/**
	 * 查询需要解冻
	 * @return
	 */
	public List<FundFreezeDTO> queryFundFreezeDTO(FundFreezeDTO fundFreezeDTO);
	
	/**
	 * 更新支付单 状态
	 * @param orderNo 业务订单号
	 * @param status  支付状态码  支付成功:2支付失败:3
	 * @return
	 */
	public int updateTitanOrderPayreq(String orderNo,String status);
	
	/**
	 * 获取转账
	 * @param titanTransferDTO
	 * @return
	 * @author fangdaikang
	 */
	public TitanTransferDTO getTitanTransferDTO(TitanTransferDTO titanTransferDTO);
	
	/**
	 * 获取转账记录
	 * @param titanTransferDTO
	 * @return
	 * @author fangdaikang
	 */
	public List<TitanTransferDTO> getTitanTransferDTOList(TitanTransferDTO titanTransferDTO);
	
	/**
	 * 充值记录
	 * @param titanOrderPayDTO
	 * @return
	 */
	public TitanOrderPayDTO getTitanOrderPayDTO(TitanOrderPayDTO titanOrderPayDTO);
	
	/**
	 * 提现记录
	 * @param titanWithDrawDTO
	 * @return
	 */
	public TitanWithDrawDTO getTitanWithDrawDTO(TitanWithDrawDTO titanWithDrawDTO);
	
	/**
	 * 查询加密的私钥
	 * @param payOrderNo
	 * @return
	 * @author fangdaikang
	 */
	public String getKeyByPayOrderNo(String payOrderNo);
	
	/**
	 * 修改订单状态
	 * @param transOrderDTO
	 * @return
	 * @author fangdaikang
	 */
	public boolean updateTransOrder(TransOrderDTO transOrderDTO);

	/**
	 * 保存单异常信息
	 * @param orderExceptionDTO
	 * @return
	 * @author fangdaikang
	 */
	public boolean saveOrderException(OrderExceptionDTO orderExceptionDTO);
	
	/**
	 * 修改转账单的状态
	 * @param transferDTO
	 * @return
	 */
	public boolean updateTransferOrder(TitanTransferDTO transferDTO);
	
	
	/**
	 * 确认订单是否支付成功
	 * @return
	 */
	public String confirmOrderStatus(String orderNo);

}
