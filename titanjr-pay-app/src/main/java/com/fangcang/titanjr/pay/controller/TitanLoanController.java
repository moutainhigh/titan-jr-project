package com.fangcang.titanjr.pay.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.util.JsonConversionTool;
import com.fangcang.titanjr.pay.req.LoanNotifyReq;
import com.fangcang.titanjr.pay.services.listener.TitanCreditServiceListener;
import com.fangcang.titanjr.pay.services.listener.TitanLoanServiceListener;

/**
 * 贷款相关操作处理
 * 
 * @ClassName: TitanLoanController
 * @Description: TODO
 * @author: wengxitao
 * @date: 2016年11月2日 下午4:04:55
 */
@RequestMapping("/loan")
@Controller
public class TitanLoanController extends BaseController {

	private static final long serialVersionUID = 1L;
	
	private static final Log log = LogFactory
			.getLog(TitanLoanController.class);
	
	@Resource(name = "titanLoanService")
	private TitanLoanServiceListener titanLoanServiceListener;
	
	@Resource(name = "titanCreditService")
	private TitanCreditServiceListener titanCreditServiceListener;
	

	@ResponseBody
	@RequestMapping(value = "notify")
	public void loanNotify(LoanNotifyReq req) 
	{
		log.info("loan notify req = " + JsonConversionTool.toJson(req));
		
		Map<String, String> busMap = JsonConversionTool.toObject(
				req.getData(), Map.class);
		
	}

}
