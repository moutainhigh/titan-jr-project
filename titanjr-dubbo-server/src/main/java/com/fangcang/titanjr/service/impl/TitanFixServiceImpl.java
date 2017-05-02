package com.fangcang.titanjr.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fangcang.corenut.dao.PaginationSupport;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.ConfirmFinanceRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.entity.TitanOrderException;
import com.fangcang.titanjr.entity.parameter.TitanOrderExceptionParam;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFixService;
import com.fangcang.titanjr.service.TitanOrderService;

@Service("titanFixService")
public class TitanFixServiceImpl implements TitanFixService {
	private static final Log log = LogFactory.getLog(TitanFixServiceImpl.class);
	 
	@Resource
	private TitanOrderService titanOrderService;
	
	@Resource
	private TitanFinancialTradeService tradeService;
	
	
		
	@Override
	public void notifyTradeStatus() {
		Set<String> sendOrderIdSet = new HashSet<String>();
		int pageNo = 1;
		TitanOrderExceptionParam condition = new TitanOrderExceptionParam();
		condition.setType(OrderExceptionEnum.Notify_Client_Not_CallBack.getType());
		condition.setEndUpdateTime(new Date());
		condition.setEndFailState(CommonConstant.ORDER_EXCEPTION_MAX_FAIL_STATE);
		PaginationSupport<TitanOrderException> paginationSupport = new PaginationSupport<TitanOrderException>();
		paginationSupport.setOrderBy(" updatetime ");
		paginationSupport.setPageSize(20);
		paginationSupport.setCurrentPage(pageNo);
		paginationSupport = titanOrderService.selectOrderExceptionForPage(condition, paginationSupport);
		List<TitanOrderException> itemList = paginationSupport.getItemList();
		while (itemList.size()>0) {//循环查询
			log.info("fixStatusNotify ,the query pageNo is :"+pageNo);
			for(TitanOrderException item : paginationSupport.getItemList()){
				
				TransOrderRequest transOrderRequest = new TransOrderRequest();
				transOrderRequest.setOrderid(item.getOrderId());
				TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
			    ConfirmFinanceRequest req = new ConfirmFinanceRequest();
			    req.setTransOrderDTO(transOrderDTO);
			    req.setIsSaveLog(false);
			    boolean isDuplicate = false; 
			    try {
			    	if(!sendOrderIdSet.contains(item.getOrderId())){
			    		log.info(Tools.getStringBuilder().append("支付状态通知补偿,orderId:").append(transOrderDTO.getOrderid()).append(",userorderId:").append(transOrderDTO.getUserorderid()));
						tradeService.confirmFinance(req);
						sendOrderIdSet.add(item.getOrderId());
						isDuplicate = false;
			    	}else{
			    		isDuplicate = true;
			    	}
			    	
				} catch (Exception e) {
					log.error(Tools.getStringBuilder().append("支付状态通知补偿失败").append(",orderId:").append(transOrderDTO.getOrderid()).append(",userorderId:").append(transOrderDTO.getUserorderid()),e);
				}finally {
					//更新数据
					NotifyPolicy next = null;
					if(isDuplicate){//orderid重复记录，则不再通知
						next = NotifyPolicy.FailState_99;
					}else{
						next = NotifyPolicy.getNotifyPolicy(NotifyPolicy.getNotifyPolicy(item.getFailState()).getNextFailState());
					}
					
					OrderExceptionDTO orderExceptionDTO = new OrderExceptionDTO();
					orderExceptionDTO.setId(item.getId());
					orderExceptionDTO.setUpdateTime(NotifyPolicy.getNextTime(next));
					orderExceptionDTO.setFailState(next.getFailState());
					titanOrderService.updateOrderException(orderExceptionDTO);
					
				}
			}
			paginationSupport.setItemList(Collections.EMPTY_LIST);
			paginationSupport.setCurrentPage(++pageNo);
			paginationSupport = titanOrderService.selectOrderExceptionForPage(condition, paginationSupport);
			
			if(pageNo>paginationSupport.getTotalPage()){
				itemList = Collections.EMPTY_LIST;
			}else{
				itemList = paginationSupport.getItemList();
			}
		}

	}
	/***
	 * 通知重复策略
	 * @author luoqinglong
	 *
	 */
	public enum NotifyPolicy{
		FailState_0("0","2",1,"MINUTES","状态为0,通知第一次"),
		FailState_2("2","3",3,"MINUTES","状态为2,通知第二次"),
		FailState_3("3","4",10,"MINUTES","状态为3,通知第三次"),
		FailState_4("4","5",30,"MINUTES","状态为4,通知第四次"),
		FailState_5("5","6",2,"HOURS","状态为5,通知第五次"),
		FailState_6("6","99",12,"HOURS","状态为6,12小时后，再通知第六次"),
		FailState_99("99","0",0,"HOURS","状态为99,停止通知");
		
		/**
		 * 本次状态
		 */
		private String failState;
		/***
		 * 下次状态
		 */
		private String nextFailState;
		/**
		 * 两次的时间间隔
		 */
		private int rangeTime;
		/**
		 * 时间单位
		 */
		private String unit;
		
		private String des;
		
		private NotifyPolicy(String failState,String nextFailState,int rangeTime,String unit,String des){
			this.failState = failState;
			this.nextFailState = nextFailState;
			this.rangeTime = rangeTime;
			this.unit = unit;
			this.failState = failState;
			this.des = des;
		}
		
		/**
		 * 状态对应的策略
		 * @param currentFailState
		 * @return
		 */
		public static NotifyPolicy getNotifyPolicy(String currentFailState){
			for(NotifyPolicy item : values()){
				if(item.getFailState().equals(currentFailState)){
					return item;
				}
			}
			return FailState_99;
		}
		/**
		 * 当前策略的时间
		 * @param notifyPolicy
		 * @return
		 */
		public static Date getNextTime(NotifyPolicy notifyPolicy){
			Date now = new Date();
			if(notifyPolicy.getUnit().equals("MINUTES")){
				return DateUtils.addMinutes(now, notifyPolicy.getRangeTime());
			}
			return DateUtils.addHours(now, notifyPolicy.getRangeTime());
		}

		public String getFailState() {
			return failState;
		}

		public String getNextFailState() {
			return nextFailState;
		}

		public int getRangeTime() {
			return rangeTime;
		}

		public String getUnit() {
			return unit;
		}

		public String getDes() {
			return des;
		}
		
		
	}
	
}
