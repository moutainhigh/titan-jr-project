package com.fangcang.titanjr.job.executors;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fangcang.titanjr.common.enums.ConditioncodeEnum;
import com.fangcang.titanjr.common.enums.OrderExceptionEnum;
import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.enums.ReqstatusEnum;
import com.fangcang.titanjr.common.enums.TransferReqEnum;
import com.fangcang.titanjr.common.enums.TransfertypeEnum;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.OrderGenerateService;
import com.fangcang.titanjr.dto.bean.OrderExceptionDTO;
import com.fangcang.titanjr.dto.bean.RepairTransferDTO;
import com.fangcang.titanjr.dto.bean.TitanTransferDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.RechargeResultConfirmRequest;
import com.fangcang.titanjr.dto.request.RepairTransferRequest;
import com.fangcang.titanjr.dto.request.TransferRequest;
import com.fangcang.titanjr.dto.request.UnFreeBalanceBatchRequest;
import com.fangcang.titanjr.dto.request.UnFreezeRequest;
import com.fangcang.titanjr.dto.response.FreezeAccountBalanceResponse;
import com.fangcang.titanjr.dto.response.RepairTransferResponse;
import com.fangcang.titanjr.dto.response.TransferResponse;
import com.fangcang.titanjr.dto.response.UnFreezeResponse;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanOrderService;

/**
 * @author Administrator
 *
 */
public class RepairGDPTransferRunner implements Runnable{
	
	private static final Log log = LogFactory.getLog(UnFreezeTransOrderRunner.class);
	
	private TitanFinancialTradeService titanFinancialTradeService;
	
	public RepairGDPTransferRunner(TitanFinancialTradeService titanFinancialTradeService){
		this.titanFinancialTradeService = titanFinancialTradeService;
	}

    @Override
    public void run() {
    	this.repairGDPTransferRunner();
    }
    
    private void repairGDPTransferRunner(){
    	//查询订单失败，查询充值成功，转账单存在且未成功，或者转账单不存在，
    	RepairTransferRequest repairTransferRequest = new RepairTransferRequest();
    	titanFinancialTradeService.repairTransferOrder(repairTransferRequest);
    	
    }
    
}
