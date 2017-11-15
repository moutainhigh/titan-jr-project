package com.fangcang.titanjr.pay.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.pay.req.TitanRateComputeReq;
import com.fangcang.titanjr.pay.rsp.TitanRateComputeRsp;
import com.fangcang.titanjr.pay.services.TitanRateService;
import com.fangcang.util.StringUtil;

/**
 * 费率控制器
 * 
 * @author wengxitao
 */
@Controller
@RequestMapping("/rate")
public class TitanRateController extends BaseController {

	private static final long serialVersionUID = 1L;

	@Resource
	private TitanRateService titanRateService;

	@ResponseBody
	@RequestMapping(value = "/rateCompute", method = { RequestMethod.GET })
	public String rateCompute(String userId, String amount, String deskId) {

		if (!StringUtil.isValidString(userId)
				|| !StringUtil.isValidString(amount)
				|| !StringUtil.isValidString(deskId)) {
			return this.toMsgJson(TitanMsgCodeEnum.PARAMETER_VALIDATION_FAILED);
		}

		TitanRateComputeReq computeReq = new TitanRateComputeReq();
		computeReq.setAmount(amount);
		computeReq.setDeskId(deskId);
		computeReq.setUserId(userId);
		
		TitanRateComputeRsp computeRsp = titanRateService
				.rateCompute(computeReq);

		if (computeRsp != null) {
			return this.toMsgJson(TitanMsgCodeEnum.TITAN_SUCCESS, computeRsp);
		}
		return null;
	}
}
