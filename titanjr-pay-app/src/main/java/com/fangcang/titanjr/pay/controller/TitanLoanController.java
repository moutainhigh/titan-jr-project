package com.fangcang.titanjr.pay.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.pay.req.LoanNotifyReq;
import com.fangcang.titanjr.pay.services.listener.TitanCreditServiceListener;
import com.fangcang.titanjr.pay.services.listener.TitanLoanServiceListener;
import com.fangcang.util.StringUtil;

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
	/***
	 * 通知的key
	 */
	private static final String TOKEN_KEY_STRING = "4d7079570ef19ed949717aac81251eb1";
	
	private static final Log log = LogFactory
			.getLog(TitanLoanController.class);
	
	@Resource(name = "titanLoanService")
	private TitanLoanServiceListener titanLoanServiceListener;
	
	@Resource(name = "titanCreditService")
	private TitanCreditServiceListener titanCreditServiceListener;
	
	/**
	 * 融数授信审核和放款通知接口
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "notify")
	public String loanNotify(LoanNotifyReq req) 
	{
		Map<String, Object> result = new HashMap<String, Object>();
		log.info("融数通知接口notify.action请求的信息,LoanNotifyReq："+Tools.gsonToString(req));
		//校验数据合法性
		if(!StringUtil.isValidString(req.getCreate_time())){
			result.put("result", "-1");
			result.put("resultMsg", "Create_time不能为空");
			return JSONSerializer.toJSON(result).toString();
		}
		if(!StringUtil.isValidString(req.getToken())){
			result.put("result", "-1");
			result.put("resultMsg", "token不能为空");
			return JSONSerializer.toJSON(result).toString();
		}
		//判断token是否相同
		String myToken = MD5.MD5Encode(TOKEN_KEY_STRING+req.getCreate_time()).toUpperCase();
		if(!myToken.equals(req.getToken())){
			result.put("result", "-1");
			result.put("resultMsg", "token不正确");
			log.error("融数通知接口notify.action请求的信息,token不正确。请求参数LoanNotifyReq："+Tools.gsonToString(req));
			return JSONSerializer.toJSON(result).toString();
		}
		
		//判断sign是否相同
		JSONObject data = JSONObject.fromObject(req.getData());
		String orderNo = data.getString("orderNo");
		String buessNo = data.getString("buessNo");
		String userId = data.getString("userId");
		String type = data.getString("type");
		String sign = data.getString("sign");
						
		if((!StringUtil.isValidString(sign))||(!StringUtil.isValidString(type))||(!StringUtil.isValidString(orderNo))||(!StringUtil.isValidString(buessNo))||(!StringUtil.isValidString(userId))){
			result.put("result", "-1");
			result.put("resultMsg", "参数不能为空");
			return JSONSerializer.toJSON(result).toString();
		}
		String dataSign = MD5.MD5Encode(orderNo+buessNo+userId+req.getCreate_time()).toUpperCase();
		
		if(!dataSign.equals(sign)){
			log.error("融数通知接口notify.action请求的信息,sign不正确。请求参数LoanNotifyReq："+Tools.gsonToString(req));
			result.put("result", "-1");
			result.put("resultMsg", "sign不正确");
			return JSONSerializer.toJSON(result).toString();
		}
		//所有验证都通过
		
		
		
		
		
		
		
		 
		return "";
	}

}
