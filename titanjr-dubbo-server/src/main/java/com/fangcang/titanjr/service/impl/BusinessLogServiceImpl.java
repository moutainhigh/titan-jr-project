package com.fangcang.titanjr.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fangcang.log.model.BaseLogDto;
import com.fangcang.log.rocketmq.LogProducer;
import com.fangcang.titanjr.common.enums.OrderKindEnum;
import com.fangcang.titanjr.common.util.ThreadPoolUtil;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dao.TitanOperateLogDao;
import com.fangcang.titanjr.dto.bean.PayLog;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.AddPayLogRequest;
import com.fangcang.titanjr.dto.request.OperateLogRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.entity.TitanOperateLog;
import com.fangcang.titanjr.service.BusinessLogService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;

@Service("businessLogService")
public class BusinessLogServiceImpl implements BusinessLogService {

	private static final Log log = LogFactory.getLog(BusinessLogServiceImpl.class);
	@Resource
	private TitanOrderService orderService;
	
	@Resource
	private TitanOperateLogDao operateLogDao;
	
	@Override
	public void addPayLog(AddPayLogRequest addPayLogRequest) {
			if(!StringUtil.isValidString(addPayLogRequest.getOrderId())){
				return;
			}
			try {
				PayLog payLog = new PayLog();
				payLog.setBusinessType(addPayLogRequest.getPayStep().getBusinessType().getType());
		    	payLog.setBusinessDes(addPayLogRequest.getPayStep().getBusinessType().getDes());
		    	payLog.setStepInfo(addPayLogRequest.getPayStep().getInfo());
		    	payLog.setExtraInfo(addPayLogRequest.getExtraInfo());
		    	if(addPayLogRequest.getOrEnum().getType().equals(OrderKindEnum.UserOrderId.getType())){
					TransOrderRequest transOrderRequest = new TransOrderRequest();
					transOrderRequest.setUserorderid(addPayLogRequest.getOrderId());
					TransOrderDTO	orderDTO = orderService.queryTransOrderDTO(transOrderRequest);
					payLog.setTransOrderid(orderDTO.getTransid()+"");
		    	}else if(addPayLogRequest.getOrEnum().getType().equals(OrderKindEnum.TransOrderId.getType())){
		    		payLog.setTransOrderid(addPayLogRequest.getOrderId());
		    	}else if(addPayLogRequest.getOrEnum().getType().equals(OrderKindEnum.PayOrderNo.getType())){
		    		TransOrderRequest transOrderRequest = new TransOrderRequest();
					transOrderRequest.setPayorderno(addPayLogRequest.getOrderId());
					TransOrderDTO orderDTO = orderService.queryTransOrderDTO(transOrderRequest);
					payLog.setTransOrderid(orderDTO.getTransid()+"");
		    	}else if(addPayLogRequest.getOrEnum().getType().equals(OrderKindEnum.OrderId.getType())){
		    		TransOrderRequest transOrderRequest = new TransOrderRequest();
					transOrderRequest.setOrderid(addPayLogRequest.getOrderId());
					TransOrderDTO orderDTO = orderService.queryTransOrderDTO(transOrderRequest);
					payLog.setTransOrderid(orderDTO.getTransid()+"");
		    	}else{
		    		log.error(Tools.getStringBuilder().append("订单号类型错误，日志记录失败,参数orderId：").append(addPayLogRequest.getOrderId()).append(",OrderKindEnum:").append(addPayLogRequest.getOrEnum().getType()));
		    		return ;
		    	}
		    	
		    	payLog.setCreateTime(new Date());
		    	BaseLogDto payLogDto = new BaseLogDto(payLog);
		    	ThreadPoolUtil.excute(new LogThread(payLogDto));
		    	
			} catch (Exception e) {
				log.error("支付业务流程日志写失败，orderId:"+addPayLogRequest.getOrderId(),e);
			}

	}
	
	
	@Override
	public void addOperateLog(OperateLogRequest operateLogRequest) {
		TitanOperateLog entity = new TitanOperateLog();
		entity.setOperateType(operateLogRequest.getOperateType());
		entity.setOperateContent(operateLogRequest.getOperateContent());
		entity.setOperateTime(operateLogRequest.getOperateTime());
		entity.setOperator(operateLogRequest.getOperator());
		try {
			operateLogDao.insert(entity);
		} catch (Exception e) {
			log.error("操作日志失败，参数operateLogRequest:"+Tools.gsonToString(operateLogRequest),e);
		}
	}
	
	
	public class LogThread implements Runnable{
		private BaseLogDto payLogDto;
		
		public LogThread(BaseLogDto payLogDto){
			this.payLogDto = payLogDto;
		}
		
		@Override
		public void run() {
			try {
				LogProducer.sendMsg(payLogDto);
			} catch (Exception e) {
				log.error("支付业务流程日志写失败，payLogDto:"+Tools.gsonToString(payLogDto),e);
			}
		}
		
	}

}
	
