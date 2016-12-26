package com.fangcang.titanjr.pay.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fangcang.titanjr.common.enums.LoanOrderStatusEnum;
import com.fangcang.titanjr.common.enums.LoanCreditStatusEnum;
import com.fangcang.titanjr.common.exception.GlobalServiceException;
import com.fangcang.titanjr.common.util.MD5;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.pay.req.LoanNotifyReq;
import com.fangcang.titanjr.pay.req.NotifyDataObject;
import com.fangcang.titanjr.pay.services.listener.TitanCreditServiceListener;
import com.fangcang.titanjr.pay.services.listener.TitanLoanServiceListener;
import com.fangcang.titanjr.service.TitanFinancialLoanCreditService;
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
	
	@Resource
	private TitanLoanServiceListener titanLoanServiceListener;
	
	@Resource
	private TitanCreditServiceListener titanCreditServiceListener;
	@Resource
	private TitanFinancialLoanCreditService titanFinancialLoanCreditService;
	/**
	 * 融数授信审核和放款通知接口
	 * @param req
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/notify", produces = {"application/json;charset=UTF-8"})
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
		String mtoken = TOKEN_KEY_STRING+req.getCreate_time();
		String myToken = MD5.MD5Encode(mtoken).toUpperCase();
		if(!myToken.equals(req.getToken())){
			result.put("result", "-1");
			result.put("resultMsg", "token不正确");
			log.error("融数通知接口notify.action请求的信息,token不正确。明码token:"+mtoken+",本地md5后myToken："+myToken+",请求参数LoanNotifyReq："+Tools.gsonToString(req));
			return JSONSerializer.toJSON(result).toString();
		}
		
		//data全部转为jsonArray
		List<NotifyDataObject> notifyDataBeanList = new ArrayList<NotifyDataObject>();
		try {
			JSONObject dataJsonObject = JSONObject.fromObject(req.getData());
			NotifyDataObject dataBean = (NotifyDataObject)JSONObject.toBean(dataJsonObject, NotifyDataObject.class);
			notifyDataBeanList.add(dataBean);
			
		} catch (Exception e) {
			JSONArray dataJsonArray = JSONArray.fromObject(req.getData());
			notifyDataBeanList.addAll(JSONArray.toList(dataJsonArray, NotifyDataObject.class));
		}
		//开始循环处理
		List<Map<String, Object>> dataReasult = new ArrayList<Map<String, Object>>();
		for(NotifyDataObject item : notifyDataBeanList){
			if(!StringUtil.isValidString(item.getStatus())){
				item.setStatus(req.getStatus());
			}
			if(!StringUtil.isValidString(item.getMsg())){
				item.setMsg(req.getMsg());
			}
			dataReasult.add(oneNotify(item, req.getCreate_time()));
		}
		result.put("dataResult", dataReasult);
		result.put("result", "0");
		result.put("resultMsg", "处理完成");
		return JSONSerializer.toJSON(result).toString();
	}
	
	private Map<String, Object> oneNotify(NotifyDataObject notifyDataObject,String createTime){
		Map<String, Object> result = new HashMap<String, Object>();
		if((!StringUtil.isValidString(notifyDataObject.getSign()))||(!StringUtil.isValidString(notifyDataObject.getType()))||(!StringUtil.isValidString(notifyDataObject.getOrderNo()))||(!StringUtil.isValidString(notifyDataObject.getBuessNo()))){
			result.put("dataState", "-1");
			result.put("dataMsg", "参数不能为空");
			return result;
		}
		
		String string = notifyDataObject.getOrderNo()+notifyDataObject.getBuessNo()+TOKEN_KEY_STRING+createTime;
		String dataSign = MD5.MD5Encode(string).toUpperCase();
		
		if(!dataSign.equals(notifyDataObject.getSign())){
			log.error("融数通知接口notify.action请求的信息,sign不正确。明文的sign:"+string+",本地md5后dataSign："+dataSign+"请求参数notifyDataObject："+Tools.gsonToString(notifyDataObject)+",createTime:"+createTime);
			result.put("dataState", "-1");
			result.put("dataMsg", "sign不正确");
			return result;
		}
		//所有验证都通过
		log.info("融数通知接口notify.action所有参数通过校验，该请求是合法请求。请求参数notifyDataObject："+Tools.gsonToString(notifyDataObject)+",createTime:"+createTime);
		
		try {
			if(notifyDataObject.getBuessNo().startsWith("CR")){
				// 1、授信申请单
				int state = 1;
				if("31".equals(notifyDataObject.getStatus())){//终审通过
					//协议确认
					titanCreditServiceListener.agreementConfirm(notifyDataObject.getBuessNo());
				}else if("41".equals(notifyDataObject.getStatus())){//开通
					state = LoanCreditStatusEnum.REVIEW_PASS.getStatus();
					titanCreditServiceListener.creditSucceed(notifyDataObject.getBuessNo(),state);
				}else if(notifyDataObject.getStatus().endsWith("2")||notifyDataObject.getStatus().endsWith("0")){//不通过
					state = LoanCreditStatusEnum.NO_PASS.getStatus();
					titanCreditServiceListener.creditFailure(notifyDataObject.getBuessNo(),state, notifyDataObject.getMsg());
				}else{
					//其他状态暂时不处理。
				}
				result.put("dataState", "0");
				result.put("dataMsg", "通知处理成功");
			}else if(notifyDataObject.getBuessNo().startsWith("D")){
				// 2、贷款申请单
				int state = 0;
				boolean resultState = false;
				if("31".equals(notifyDataObject.getStatus())){//贷款订单终审通过
					state = LoanOrderStatusEnum.AUDIT_PASS.getKey();
					resultState = titanLoanServiceListener.loanSucceed(notifyDataObject.getBuessNo(),state);
				}else if("51".equals(notifyDataObject.getStatus())){//成功放款
					state = LoanOrderStatusEnum.HAVE_LOAN.getKey();
					resultState = titanLoanServiceListener.loanSucceed(notifyDataObject.getBuessNo(),state);
				}else if(notifyDataObject.getStatus().endsWith("2")||notifyDataObject.getStatus().endsWith("0")){//不通过
					if(notifyDataObject.getStatus().startsWith("2")||notifyDataObject.getStatus().startsWith("3")||notifyDataObject.getStatus().startsWith("4")){
						state = LoanOrderStatusEnum.AUDIT_FIAL.getKey();
					}else if(notifyDataObject.getStatus().startsWith("5")){
						state = LoanOrderStatusEnum.LENDING_FAIL.getKey();
					}else{
						state = LoanOrderStatusEnum.LENDING_FAIL.getKey();
					}
					resultState = titanLoanServiceListener.loanFailure(notifyDataObject.getBuessNo(),state, notifyDataObject.getMsg());
				}else{
					//其他状态暂时不处理。
				}
				if(resultState){
					result.put("dataState", "0");
					result.put("dataMsg", "通知处理成功");
				}else{
					result.put("dataState", "-1");
					result.put("dataMsg", "通知处理失败");
				}
				
			}else {
				log.info("通知不处理，无效订单:"+Tools.gsonToString(notifyDataObject)+",createTime:"+createTime);
				result.put("dataState", "-1");
				result.put("dataMsg", "通知不处理，无效订单");
			}
			log.info("贷款订单通知处理完成,"+notifyDataObject.getBuessNo()+",result:"+Tools.gsonToString(result));
			return result;
		} catch (GlobalServiceException e) {
			log.error("通知处理失败,参数notifyDataObject："+Tools.gsonToString(notifyDataObject)+",createTime:"+createTime, e); 
			result.put("dataState", "-1");
			result.put("dataMsg", "通知处理失败");
			return result;
		}
	}
}
