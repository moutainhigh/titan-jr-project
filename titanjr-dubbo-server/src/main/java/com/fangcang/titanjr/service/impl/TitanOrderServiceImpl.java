package com.fangcang.titanjr.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.dao.TitanDynamicKeyDao;
import com.fangcang.titanjr.dao.TitanOrderExceptionDao;
import com.fangcang.titanjr.dao.TitanOrderPayreqDao;
import com.fangcang.titanjr.dao.TitanTransOrderDao;
import com.fangcang.titanjr.dao.TitanTransferReqDao;
import com.fangcang.titanjr.dao.TitanWithDrawReqDao;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanWithDrawDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.TransOrderResponse;
import com.fangcang.titanjr.entity.TitanDynamicKey;
import com.fangcang.titanjr.entity.TitanOrderException;
import com.fangcang.titanjr.entity.TitanOrderPayreq;
import com.fangcang.titanjr.entity.TitanTransOrder;
import com.fangcang.titanjr.entity.TitanTransferReq;
import com.fangcang.titanjr.entity.TitanWithDrawReq;
import com.fangcang.titanjr.entity.parameter.TitanOrderPayreqParam;
import com.fangcang.titanjr.entity.parameter.TitanTransferReqParam;
import com.fangcang.titanjr.entity.parameter.TitanWithDrawReqParam;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.MyBeanUtil;
import com.fangcang.util.StringUtil;

@Service("titanOrderService")
public class TitanOrderServiceImpl implements TitanOrderService {
	 
	private static final Log log = LogFactory.getLog(TitanOrderServiceImpl.class);
	
	@Resource
	private TitanTransOrderDao titanTransOrderDao;
	
	@Resource
	private TitanOrderPayreqDao titanOrderPayreqDao;
	
	@Resource
	private TitanWithDrawReqDao titanWithDrawReqDao;
	
	@Resource
	private TitanTransferReqDao titanTransferReqDao;
	
	@Resource
	private TitanDynamicKeyDao titanDynamicKeyDao;
	
	@Resource
	private TitanOrderExceptionDao titanOrderExceptionDao;
	
	
	
	@Override
	public List<TransOrderDTO> queryTransOrder(TransOrderRequest transOrderRequest) {

		return  titanTransOrderDao.selectTitanTransOrder(transOrderRequest);
	}
	
	@Override
	public int updateTitanOrderPayreq(String orderNo, String status) {
		try{
			if(StringUtil.isValidString(orderNo)&&StringUtil.isValidString(status)){
				TitanOrderPayreq titanOrderPayreq = new TitanOrderPayreq();
				titanOrderPayreq.setOrderNo(orderNo);
				titanOrderPayreq.setReqstatus(Integer.parseInt(status));
				return titanOrderPayreqDao.updateTitanOrderPayreqByOrderNo(titanOrderPayreq);
			}
		}catch(Exception e){
			log.error("更新充值单错误："+e.getMessage(),e);
		}
		return 0;
	}

	@Override
	public TitanTransferDTO getTitanTransferDTO(
			TitanTransferDTO titanTransferDTO) {
		try{
			List<TitanTransferDTO> titanTransferDTOList = getTitanTransferDTOList(titanTransferDTO);
			if(titanTransferDTOList !=null){
				return titanTransferDTOList.get(0);
			}
		}catch(Exception e){
			log.error("查询转账记录失败"+e.getMessage(),e);
		}
		return null;
	}
	
	
	

	@Override
	public TitanOrderPayDTO getTitanOrderPayDTO(
			TitanOrderPayDTO titanOrderPayDTO) {
		try{
			if(titanOrderPayDTO !=null){
				TitanOrderPayreqParam condition = new TitanOrderPayreqParam();
				condition.setTransorderid(titanOrderPayDTO.getTransorderid());
				condition.setOrderpayreqid(titanOrderPayDTO.getOrderpayreqid());
				condition.setMerchantNo(titanOrderPayDTO.getMerchantNo());
				condition.setOrderNo(titanOrderPayDTO.getOrderNo());
				PaginationSupport<TitanOrderPayreq> paginationSupport = new PaginationSupport<TitanOrderPayreq>();
				titanOrderPayreqDao.selectForPage(condition, paginationSupport);
				if(paginationSupport.getItemList()!=null && paginationSupport.getItemList().size()>0){
					TitanOrderPayreq titanOrderPayreq =  paginationSupport.getItemList().get(0);
					TitanOrderPayDTO orderPayDTO = new TitanOrderPayDTO();
					if(titanOrderPayreq !=null){
						MyBeanUtil.copyProperties(orderPayDTO, titanOrderPayreq);
						return orderPayDTO;
					}
				}
			}
		}catch(Exception e){
			log.error("查询充值单异常"+e.getMessage(),e);
		}
		
		return null;
	}

	@Override
	public TitanWithDrawDTO getTitanWithDrawDTO(TitanWithDrawDTO titanWithDrawDTO) {
		try{
			if(titanWithDrawDTO !=null){
				TitanWithDrawReqParam condition = new TitanWithDrawReqParam();
				condition.setWithdrawreqid(titanWithDrawDTO.getWithdrawreqid());
				condition.setTransorderid(titanWithDrawDTO.getTransorderid());
				condition.setUserorderid(titanWithDrawDTO.getUserorderid());
				PaginationSupport<TitanWithDrawReq> paginationSupport = new PaginationSupport<TitanWithDrawReq>();
				titanWithDrawReqDao.selectForPage(condition, paginationSupport);
				if(paginationSupport.getItemList() !=null && paginationSupport.getItemList().size()>0){
					TitanWithDrawDTO withDrawDTO = new TitanWithDrawDTO();
					TitanWithDrawReq TitanWithDrawReq = paginationSupport.getItemList().get(0);
					if(withDrawDTO !=null){
						MyBeanUtil.copyProperties(withDrawDTO, TitanWithDrawReq);
						return withDrawDTO;
					}
				}
			}
		}catch(Exception e){
			log.error("查询提现记录失败",e);
		}
		return null;
	}

	@Override
	public String getKeyByPayOrderNo(String payOrderNo) {
		try{
			TitanDynamicKey titanDynamicKey = new TitanDynamicKey();
			titanDynamicKey.setPayorderno(payOrderNo);
			return titanDynamicKeyDao.selectKeyByPayOrderNo(titanDynamicKey);
		}catch(Exception e){
			log.error("查询秘钥失败"+e.getMessage(),e);
		}
		return null;
	}

	@Override
	public TransOrderDTO queryTransOrderDTO(TransOrderRequest transOrderRequest) {
		try{
			List<TransOrderDTO> transOrderDTOList = this.queryTransOrder(transOrderRequest);
			if(transOrderDTOList !=null && transOrderDTOList.size()>0){
				return transOrderDTOList.get(0);
			}
		}catch(Exception e){
			log.error("查询TransOrderDTO失败"+e.getMessage(),e);
		}
		return null;
	}

	@Override
	public boolean updateTransOrder(TransOrderDTO transOrderDTO) {
		try{
			TitanTransOrder titanTransOrder = new TitanTransOrder();
			titanTransOrder.setTransid(transOrderDTO.getTransid());
			titanTransOrder.setStatusid(transOrderDTO.getStatusid());
			int row = titanTransOrderDao.update(titanTransOrder);
			if(row>0){
				return true;
			}
		}catch(Exception e){
			log.error("修改订单状态失败"+e.getMessage(),e);
		}
		return false;
	}

	@Override
	public List<TitanTransferDTO> getTitanTransferDTOList(
			TitanTransferDTO titanTransferDTO) {
		try{
			if(titanTransferDTO !=null){
				TitanTransferReqParam condition = new TitanTransferReqParam();
				condition.setUserrelateid(titanTransferDTO.getUserrelateid());
				condition.setTransferreqid(titanTransferDTO.getTransferreqid());
				condition.setTransorderid(titanTransferDTO.getTransorderid());
				condition.setRequestno(titanTransferDTO.getRequestno());
				PaginationSupport<TitanTransferReq> paginationSupport = new PaginationSupport<TitanTransferReq>();
				titanTransferReqDao.selectForPage(condition, paginationSupport);
				List<TitanTransferDTO> titanTransferDTOList = new ArrayList<TitanTransferDTO>();
				if(paginationSupport.getItemList() !=null && paginationSupport.getItemList().size()>0){
					for(TitanTransferReq titanTransferReq :paginationSupport.getItemList() ){
						TitanTransferDTO transferDTO = new TitanTransferDTO();
						MyBeanUtil.copyProperties(transferDTO, titanTransferReq);
						titanTransferDTOList.add(transferDTO);
					}
					return titanTransferDTOList;
				}
			}
		}catch(Exception e){
			log.error("查询转账记录失败"+e.getMessage(),e);
		}
		return null;
	}

	@Override
	public boolean saveOrderException(OrderExceptionDTO orderExceptionDTO) {
		if(orderExceptionDTO !=null){
			TitanOrderException titanOrderException = new TitanOrderException();
			try{
				MyBeanUtil.copyProperties(titanOrderException, orderExceptionDTO);
				return titanOrderExceptionDao.insertTitanOrderException(titanOrderException)>0?true:false;
			}catch(Exception e){
			    log.error("插入异常单错误"+e.getMessage(),e);
			}
		}
		return false;
	}

	@Override
	public boolean updateTransferOrder(TitanTransferDTO transferDTO) {
		TitanTransferReq titanTransferReq = new TitanTransferReq();
		titanTransferReq.setTransferreqid(transferDTO.getTransferreqid());
		titanTransferReq.setStatus(transferDTO.getStatus());
		int row = titanTransferReqDao.update(titanTransferReq);
		if(row<1){
			return false;
		}
		return true;
	}

	@Override
	public String confirmOrderStatus(String orderNo) {
		
		List<String> statusId =  titanTransOrderDao.confirmOrderStatus(orderNo);
		if(statusId.size()==1 && StringUtil.isValidString(statusId.get(0))){
			if(OrderStatusEnum.FREEZE_SUCCESS.getStatus().equals(statusId.get(0)) || OrderStatusEnum.ORDER_SUCCESS.getStatus().equals(statusId.get(0))){
				return "success";
			}else if(OrderStatusEnum.ORDER_FAIL.getStatus().equals(statusId.get(0))){
				return "fail";
			}else if(OrderStatusEnum.ORDER_NO_EFFECT.getStatus().equals(statusId.get(0))){
				return "no_effect";
			}else{
				return "process";
			}
		}
		return "exception";
	}
	
}
