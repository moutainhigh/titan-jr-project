package com.fangcang.titanjr.pay.task;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.service.TitanFinancialTradeService;

/**
 * 付款结果通知
 * 
 * @author wengxitao
 *
 */
public final class TitanPayResultNotifyTask implements Runnable {

	private static final Log log = LogFactory
			.getLog(TitanPayResultNotifyTask.class);

	private String taskId;

	private TransOrderDTO transOrderDTO = null;

	private TitanFinancialTradeService titanFinancialTradeService;

	public TitanPayResultNotifyTask(
			TitanFinancialTradeService titanFinancialTradeService) {
		this.titanFinancialTradeService = titanFinancialTradeService;
	}

	public void setTransOrderDTO(TransOrderDTO transOrderDTO) {
		this.transOrderDTO = transOrderDTO;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	@Override
	public void run() {
		try {
			log.info("begin execute taskId=" + taskId);

			if (titanFinancialTradeService == null) {
				log.error("trade service is null.");
				return;
			}

			if (transOrderDTO == null) {
				log.error("order dto is null.");
				return;
			}

			titanFinancialTradeService.confirmFinance(transOrderDTO);

			log.info("end execute taskId=" + taskId);

		} catch (Exception ex) {
			log.error("", ex);
		}
	}
}
