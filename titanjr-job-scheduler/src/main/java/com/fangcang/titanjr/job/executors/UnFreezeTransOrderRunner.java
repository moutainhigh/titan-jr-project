package com.fangcang.titanjr.job.executors;

import java.text.ParseException;
import java.util.Date;

import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.dto.request.UnFreeBalanceBatchRequest;
import com.fangcang.titanjr.dto.request.UnFreezeRequest;
import com.fangcang.titanjr.dto.response.UnFreezeResponse;
import com.fangcang.titanjr.service.TitanFinancialAccountService;

/**
 * Created by zhaoshan on 2016/6/20.
 */
public class UnFreezeTransOrderRunner implements Runnable {
	
	private TitanFinancialAccountService titanFinancialAccountService;
	
	public UnFreezeTransOrderRunner(TitanFinancialAccountService titanFinancialAccountService){
		this.titanFinancialAccountService = titanFinancialAccountService;
	}

    @Override
    public void run() {
    	unFreezeOrder(0, 100);
    }
    
    private String unFreezeOrder(int offset,int rows){
    	if(rows<100){
    		return "success";
    	}
    	UnFreezeRequest unFreezeRequest = new UnFreezeRequest();
    	unFreezeRequest.setOffset(offset);
    	unFreezeRequest.setRows(rows);
    	Date date = new Date();
    	String dateStr = DateUtil.sdf.format(date);
		try {
			unFreezeRequest.setUnFreezeDate(DateUtil.sdf.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		UnFreezeResponse unFreezeResponse =  titanFinancialAccountService.queryUnFreezeData(unFreezeRequest);
		if(unFreezeResponse.getFundFreezeDTO() !=null && unFreezeResponse.getFundFreezeDTO().size()>0){
			rows = unFreezeResponse.getFundFreezeDTO().size();
			//调用解冻操作
			UnFreeBalanceBatchRequest unFreeBalanceBatchRequest = new UnFreeBalanceBatchRequest();
			unFreeBalanceBatchRequest.setFundFreezeDTOList(unFreezeResponse.getFundFreezeDTO());
			titanFinancialAccountService.unfreezeAccountBalanceBatch(unFreeBalanceBatchRequest);
			unFreezeOrder((offset+1)*rows,rows);
		}
		return "success";
    }
    
}
