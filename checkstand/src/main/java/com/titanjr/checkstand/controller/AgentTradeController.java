/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AgentTradeController.java
 * @author Jerry
 * @date 2017年12月25日 上午10:02:15  
 */
package com.titanjr.checkstand.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.util.DateUtil;
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.CertificateEnum;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.RBAgentPayContentDTO;
import com.titanjr.checkstand.dto.TLAgentInfoRequestDTO;
import com.titanjr.checkstand.dto.TLAgentPayTransDTO;
import com.titanjr.checkstand.dto.TLAgentQueryTransDTO;
import com.titanjr.checkstand.dto.TitanAgentDownloadDTO;
import com.titanjr.checkstand.dto.TitanAgentPayDTO;
import com.titanjr.checkstand.dto.TitanAgentQueryDTO;
import com.titanjr.checkstand.request.RBAgentDownloadRequest;
import com.titanjr.checkstand.request.RBAgentPayQueryRequest;
import com.titanjr.checkstand.request.RBAgentPayRequest;
import com.titanjr.checkstand.request.TLAgentTradeRequest;
import com.titanjr.checkstand.respnse.RSResponse;
import com.titanjr.checkstand.respnse.TitanAgentPayResponse;
import com.titanjr.checkstand.respnse.TitanAgentQueryResponse;
import com.titanjr.checkstand.service.RBAgentTradeService;
import com.titanjr.checkstand.service.TLAgentTradeService;
import com.titanjr.checkstand.strategy.StrategyFactory;
import com.titanjr.checkstand.strategy.agent.AgentTradeStrategy;
import com.titanjr.checkstand.util.WebUtils;

/**
 * 代付
 * @author Jerry
 * @date 2017年12月25日 上午10:02:15  
 */
@Controller
@RequestMapping(value = "/agent")
public class AgentTradeController extends BaseController {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -2708852830402809515L;
	private static final Logger logger = LoggerFactory.getLogger(AgentTradeController.class);
	
	@Resource
	private TLAgentTradeService tlAgentTradeService;
	
	@Resource
	private RBAgentTradeService rbAgentTradeService;
	
	
	@RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public String entrance(HttpServletRequest request, RedirectAttributes attr, Model model) {
        
		try {
			
			String tradeCode = request.getParameter("tradeCode");
			
			AgentTradeStrategy agentTradeStrategy =  StrategyFactory.getAgentTradeStrategy(tradeCode);
			if(agentTradeStrategy == null){
				logger.error("【账户交易】获取代付策略为空，tradeCode={}", tradeCode);
				return super.payFailedCallback(model);
			}
			String redirectUrl = agentTradeStrategy.redirectResult(request);
			if(!StringUtil.isValidString(redirectUrl)){
				logger.error("【账户交易】获取跳转路径为空，tradeCode={}", tradeCode);
				return super.payFailedCallback(model);
			}
			
			super.resetParameter(request,attr);
			return "forward:" + redirectUrl;
			
		} catch (Exception e) {
			
			logger.error("【账户交易】发生异常：", e);
			return super.payFailedCallback(model);
			
		}
        
    }
	
	
	/**
	 * 通联单笔实时代付
	 * @author Jerry
	 * @date 2017年12月27日 下午5:36:40
	 */
	@RequestMapping(value = "/tlAgentPay", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public TitanAgentPayResponse tlAgentPay(HttpServletRequest request, Model model) {
		
		TitanAgentPayResponse titanAgentPayResponse = new TitanAgentPayResponse();
		
		try {
    		
			TitanAgentPayDTO agentPayDTO = WebUtils.switch2RequestDTO(TitanAgentPayDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(agentPayDTO);
			if (!res.isSuccess()){
				logger.error("【通联-代付请求】参数错误：{}", res.getReturnMessage());
				titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return titanAgentPayResponse;
			}
			
			TLAgentTradeRequest tlAgentTradeRequest = new TLAgentTradeRequest();
			
			TLAgentInfoRequestDTO agentInfo  = new TLAgentInfoRequestDTO();
			agentInfo.setTRX_CODE(agentPayDTO.getTradeCode());
			agentInfo.setREQ_SN(agentPayDTO.getOrderNo());
			agentInfo.setUSER_NAME(SysConstant.USER_NAME);
			agentInfo.setUSER_PASS(SysConstant.USER_PWD);
			agentInfo.setLEVEL(SysConstant.TL_AGENT_LEVEL);
			agentInfo.setDATA_TYPE(SysConstant.TL_AGENT_DATA_TYPE);
			agentInfo.setVERSION(SysConstant.TL_AGENT_VERSION);
			tlAgentTradeRequest.setINFO(agentInfo);
			
			TLAgentPayTransDTO trans= new TLAgentPayTransDTO();
			trans.setBUSINESS_CODE(SysConstant.TL_WITHDRAW_CODE);
			trans.setMERCHANT_ID(SysConstant.TL_AGENT_MERCHANT);
			trans.setSUBMIT_TIME(DateUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
			trans.setACCOUNT_NAME(agentPayDTO.getAccountName());
			trans.setACCOUNT_NO(agentPayDTO.getAccountNo());
			trans.setACCOUNT_PROP(agentPayDTO.getAccountProperty());
			trans.setACCOUNT_TYPE(agentPayDTO.getAccountType());
			trans.setAMOUNT(agentPayDTO.getTradeAmount());
			trans.setBANK_CODE(agentPayDTO.getBankInfo());
			trans.setCURRENCY(agentPayDTO.getCurrency());
			trans.setPROVINCE(agentPayDTO.getProvince());
			trans.setID_TYPE(agentPayDTO.getIdType());
			trans.setID(agentPayDTO.getIdCode());
			trans.setCITY(agentPayDTO.getCity());
			tlAgentTradeRequest.addTrx(trans);
			tlAgentTradeRequest.setRequestType(RequestTypeEnum.AGENT_TRADE.getKey());
			titanAgentPayResponse = tlAgentTradeService.tlAgentPay(tlAgentTradeRequest);
			return titanAgentPayResponse;
			
		} catch (Exception e) {
			
			logger.error("【通联-代付请求】发生异常：", e);
			titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanAgentPayResponse;
			
		}
        
    }
	
	
	/**
	 * 融宝代付
	 * @author Jerry
	 * @date 2018年2月6日 下午3:32:48
	 */
	@RequestMapping(value = "/rbAgentPay", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public TitanAgentPayResponse rbAgentPay(HttpServletRequest request, Model model) {
		
		TitanAgentPayResponse titanAgentPayResponse = new TitanAgentPayResponse();
		
		try {
    		
			TitanAgentPayDTO agentPayDTO = WebUtils.switch2RequestDTO(TitanAgentPayDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(agentPayDTO);
			if (!res.isSuccess()){
				logger.error("【融宝-代付请求】参数错误：{}", res.getReturnMessage());
				titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return titanAgentPayResponse;
			}
			Map<String, Object> map = this.bulidContent(agentPayDTO);
			if(map.get("error") != null){
				titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.build((String)map.get("error")));
				return titanAgentPayResponse;
			}
			if(map.get("success") == null){
				logger.error("【融宝-代付请求】构建content失败");
				titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanAgentPayResponse;
			}
			RBAgentPayContentDTO content = (RBAgentPayContentDTO)map.get("success");
			
			RBAgentPayRequest rbAgentPayRequest = new RBAgentPayRequest();
			rbAgentPayRequest.setMerchant_id(SysConstant.RB_MERCHANT);
			rbAgentPayRequest.setCharset(SysConstant.RB_CHARSET_CODE);
			rbAgentPayRequest.setTrans_time(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			//rbAgentPayRequest.setNotify_url();
			rbAgentPayRequest.setBatch_no(agentPayDTO.getOrderNo());
			rbAgentPayRequest.setBatch_count("1");
			rbAgentPayRequest.setBatch_amount(((float)Integer.parseInt(agentPayDTO.getTradeAmount())/100)+"");
			rbAgentPayRequest.setPay_type("1");
			rbAgentPayRequest.setPay_sight("51");//需要确认
			rbAgentPayRequest.setContent(content);
			rbAgentPayRequest.setSign_type(SysConstant.RB_SIGN_TYPE);
			rbAgentPayRequest.setVersion(SysConstant.RB_AGENT_VERSION);
			rbAgentPayRequest.setRequestType(RequestTypeEnum.AGENT_PAY.getKey());
			
			titanAgentPayResponse = rbAgentTradeService.agentPay(rbAgentPayRequest);
			
			return titanAgentPayResponse;
			
		} catch (Exception e) {
			
			logger.error("【通联-代付请求】发生异常：", e);
			titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanAgentPayResponse;
			
		}
        
    }
	
	
	/**
	 * 通联交易查询
	 * @author Jerry
	 * @date 2017年12月27日 下午6:10:35
	 */
	@RequestMapping(value = "/tlAgentQuery", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public TitanAgentQueryResponse tlAgentQuery(HttpServletRequest request, Model model) {
		
		TitanAgentQueryResponse titanAgentQueryResponse = new TitanAgentQueryResponse();
		
		try {
    		
			TitanAgentQueryDTO agentQueryDTO = WebUtils.switch2RequestDTO(TitanAgentQueryDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(agentQueryDTO);
			if (!res.isSuccess()){
				logger.error("【通联-账户交易查询】参数错误：{}", res.getReturnMessage());
				titanAgentQueryResponse.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return titanAgentQueryResponse;
			}
			res = agentQueryDTO.validateForTL();
			if(!res.isSuccess()){
				logger.error("【通联-账户交易查询】参数错误：{}", res.getReturnMessage());
				titanAgentQueryResponse.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return titanAgentQueryResponse;
			}
			
			TLAgentTradeRequest tlAgentTradeRequest = new TLAgentTradeRequest();
			
			TLAgentInfoRequestDTO agentInfo  = new TLAgentInfoRequestDTO();
			agentInfo.setTRX_CODE(agentQueryDTO.getTradeCode());
			agentInfo.setREQ_SN(agentQueryDTO.getOrderNo());
			agentInfo.setUSER_NAME(SysConstant.USER_NAME);
			agentInfo.setUSER_PASS(SysConstant.USER_PWD);
			agentInfo.setLEVEL(SysConstant.TL_AGENT_LEVEL);
			agentInfo.setDATA_TYPE(SysConstant.TL_AGENT_DATA_TYPE);
			agentInfo.setVERSION(SysConstant.TL_AGENT_VERSION);
			tlAgentTradeRequest.setINFO(agentInfo);
			
			TLAgentQueryTransDTO trans = new TLAgentQueryTransDTO();
			trans.setMERCHANT_ID(SysConstant.TL_AGENT_MERCHANT);
			trans.setQUERY_SN(agentQueryDTO.getOrderNo());
			trans.setSTATUS(Integer.parseInt(agentQueryDTO.getTradeStatus()));
			trans.setTYPE(Integer.parseInt(agentQueryDTO.getQueryType()));
			if(agentQueryDTO.getOrderNo()==null || "".equals(agentQueryDTO.getOrderNo())){
				trans.setSTART_DAY(agentQueryDTO.getStartDate());
				trans.setEND_DAY(agentQueryDTO.getEndDate());
			}
			tlAgentTradeRequest.addTrx(trans);
			tlAgentTradeRequest.setRequestType(RequestTypeEnum.AGENT_TRADE.getKey());
			
			titanAgentQueryResponse = tlAgentTradeService.agentQuery(tlAgentTradeRequest);
			return titanAgentQueryResponse;
			
		} catch (Exception e) {
			
			logger.error("【通联-账户交易查询】发生异常：", e);
			titanAgentQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanAgentQueryResponse;
			
		}
        
    }
	
	
	/**
	 * 融宝单笔代付查询
	 * @author Jerry
	 * @date 2018年2月7日 上午11:33:24
	 */
	@RequestMapping(value = "/rbAgentQuery", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public TitanAgentQueryResponse rbAgentQuery(HttpServletRequest request, Model model) {
		
		TitanAgentQueryResponse titanAgentQueryResponse = new TitanAgentQueryResponse();
		
		try {
    		
			TitanAgentQueryDTO agentQueryDTO = WebUtils.switch2RequestDTO(TitanAgentQueryDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(agentQueryDTO);
			if (!res.isSuccess()){
				logger.error("【融宝-代付查询】参数错误：{}", res.getReturnMessage());
				titanAgentQueryResponse.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return titanAgentQueryResponse;
			}
			res = agentQueryDTO.validateForRB();
			if(!res.isSuccess()){
				logger.error("【融宝-代付查询】参数错误：{}", res.getReturnMessage());
				titanAgentQueryResponse.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return titanAgentQueryResponse;
			}
			
			RBAgentPayQueryRequest rbAgentPayQueryRequest = new RBAgentPayQueryRequest();
			rbAgentPayQueryRequest.setMerchant_id(SysConstant.RB_MERCHANT);
			rbAgentPayQueryRequest.setBatch_no(agentQueryDTO.getOrderNo());
			rbAgentPayQueryRequest.setCharset(SysConstant.RB_CHARSET_CODE);
			rbAgentPayQueryRequest.setDetail_no(agentQueryDTO.getNumber());
			rbAgentPayQueryRequest.setTrans_time(agentQueryDTO.getTransDate());
			rbAgentPayQueryRequest.setSign_type(SysConstant.RB_SIGN_TYPE);
			rbAgentPayQueryRequest.setVersion(SysConstant.RB_AGENT_VERSION);
			rbAgentPayQueryRequest.setRequestType(RequestTypeEnum.AGENT_PAY_QUERY.getKey());
			
			titanAgentQueryResponse = rbAgentTradeService.agentQuery(rbAgentPayQueryRequest);
			
			return titanAgentQueryResponse;
			
		} catch (Exception e) {
			
			logger.error("【融宝-代付查询】发生异常：", e);
			titanAgentQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanAgentQueryResponse;
			
		}
        
    }
	
	
	/**
	 * 通联对账文件下载
	 * @author Jerry
	 * @date 2018年2月7日 上午11:33:42
	 */
	@RequestMapping(value = "/tlAgentDownload", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public RSResponse tlAgentDownload(HttpServletRequest request, Model model) {
		
		RSResponse response = new RSResponse();
		
		try {
    		
			TitanAgentDownloadDTO titanAgentDownloadDTO = WebUtils.switch2RequestDTO(TitanAgentDownloadDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(titanAgentDownloadDTO);
			if (!res.isSuccess()){
				logger.error("【通联-对账文件下载】参数错误：{}", res.getReturnMessage());
				response.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return response;
			}
			res = titanAgentDownloadDTO.validateForTL();
			if(!res.isSuccess()){
				logger.error("【通联-对账文件下载】参数错误：{}", res.getReturnMessage());
				response.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return response;
			}
			
			TLAgentTradeRequest tlAgentTradeRequest = new TLAgentTradeRequest();
			
			TLAgentInfoRequestDTO agentInfo  = new TLAgentInfoRequestDTO();
			agentInfo.setTRX_CODE(titanAgentDownloadDTO.getTradeCode());
			agentInfo.setREQ_SN(titanAgentDownloadDTO.getSerialNo());
			agentInfo.setUSER_NAME(SysConstant.USER_NAME);
			agentInfo.setUSER_PASS(SysConstant.USER_PWD);
			agentInfo.setLEVEL(SysConstant.TL_AGENT_LEVEL);
			agentInfo.setDATA_TYPE(SysConstant.TL_AGENT_DATA_TYPE);
			agentInfo.setVERSION(SysConstant.TL_AGENT_VERSION);
			tlAgentTradeRequest.setINFO(agentInfo);
			
			TLAgentQueryTransDTO trans= new TLAgentQueryTransDTO();//对象共用
			trans.setSTATUS(Integer.parseInt(titanAgentDownloadDTO.getTradeStatus()));
			trans.setMERCHANT_ID(SysConstant.TL_AGENT_MERCHANT);
			trans.setTYPE(Integer.parseInt(titanAgentDownloadDTO.getQueryType())) ;
			trans.setSTART_DAY(titanAgentDownloadDTO.getStartDate());
			trans.setEND_DAY(titanAgentDownloadDTO.getEndDate());
			tlAgentTradeRequest.addTrx(trans);
			tlAgentTradeRequest.setRequestType(RequestTypeEnum.AGENT_TRADE.getKey());
			
			response = tlAgentTradeService.agentDownload(tlAgentTradeRequest);
			return response;
			
		} catch (Exception e) {
			
			logger.error("【通联-对账文件下载】发生异常：", e);
			response.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return response;
		}
        
    }
	
	
	@RequestMapping(value = "/rbAgentDownload")
	@ResponseBody
	public RSResponse rbAgentDownload(HttpServletRequest request, Model model) {
		
		RSResponse response = new RSResponse();
		
		try {
    		
			TitanAgentDownloadDTO titanAgentDownloadDTO = WebUtils.switch2RequestDTO(TitanAgentDownloadDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(titanAgentDownloadDTO);
			if (!res.isSuccess()){
				logger.error("【融宝-对账文件下载】参数错误：{}", res.getReturnMessage());
				response.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return response;
			}
			res = titanAgentDownloadDTO.validateForRB();
			if(!res.isSuccess()){
				logger.error("【融宝-对账文件下载】参数错误：{}", res.getReturnMessage());
				response.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return response;
			}
			
			RBAgentDownloadRequest rbAgentDownloadRequest = new RBAgentDownloadRequest();
			rbAgentDownloadRequest.setMerchant_id(SysConstant.RB_MERCHANT);
			rbAgentDownloadRequest.setTradeDate(titanAgentDownloadDTO.getTradeDate());
			rbAgentDownloadRequest.setRequestType(RequestTypeEnum.AGENT_DOWNLOAD.getKey());
			
			response = rbAgentTradeService.agentDownload(rbAgentDownloadRequest);
			return response;
			
		} catch (Exception e) {
			
			logger.error("【融宝-对账文件下载】发生异常：", e);
			response.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return response;
		}
        
    }
	
	
	/**
	 * 构建融宝代付所需的对象
	 * @author Jerry
	 * @date 2018年2月6日 下午2:29:25
	 * @return key：error/success  value：错误信息/构建的对象
	 */
	private Map<String, Object> bulidContent(TitanAgentPayDTO agentPayDTO){
		
		Map<String, Object> map = new HashMap<String, Object>();
		boolean isCompany = false;
		boolean isMore = false;
		
		RBAgentPayContentDTO content = new RBAgentPayContentDTO();
		content.setOrderNo(agentPayDTO.getOrderNo());
		content.setNumber(agentPayDTO.getNumber());
		content.setAccountNo(agentPayDTO.getAccountNo());
		content.setAccountName(agentPayDTO.getAccountName());
		content.setBankName(agentPayDTO.getBankName());
		content.setAmount(((float)Integer.parseInt(agentPayDTO.getTradeAmount())/100)+"");//单位：元
		content.setCurrency(agentPayDTO.getCurrency());
		if("1".equals(agentPayDTO.getAccountProperty())){
			isCompany = true;
			content.setAccountProperty("公");
		}else{
			content.setAccountProperty("私");
			if(Integer.parseInt(agentPayDTO.getTradeAmount()) > 50000*100){
				isMore = true;
			}
		}
		//对公付款及对私大于5万的付款请务必填写分行、支行、省、市的详细信息
		if(isCompany || isMore){
			logger.info("=========>对公付款或者对私大于5万");
			if(!StringUtil.isValidString(agentPayDTO.getBranchBank()) 
					|| !StringUtil.isValidString(agentPayDTO.getSubBank())
					|| !StringUtil.isValidString(agentPayDTO.getProvince())
					|| !StringUtil.isValidString(agentPayDTO.getCity())){
				logger.error("【融宝-代付请求】参数错误：{}", "对公付款及对私大于5万的付款必须填写分行、支行、省、市的详细信息");
				map.put("error", "对公付款及对私大于5万的付款必须填写分行、支行、省、市的详细信息");
				return map;
			}
			content.setBankName(agentPayDTO.getBranchBank());
			content.setSubBank(agentPayDTO.getSubBank());
			content.setProvince(agentPayDTO.getProvince());
			content.setCity(agentPayDTO.getCity());
		}
		if(StringUtil.isValidString(agentPayDTO.getPhone())){
			content.setPhone(agentPayDTO.getPhone());
		}
		if(StringUtil.isValidString(agentPayDTO.getIdType())){
			if(StringUtil.isValidString(agentPayDTO.getIdCode())){
				if(CertificateEnum.ID_CARD.code.equals(agentPayDTO.getIdType())){
					content.setCertificateType(CertificateEnum.ID_CARD.name);
				}
				content.setCertificateNo(agentPayDTO.getIdCode());
			}
		}
		if(StringUtil.isValidString(agentPayDTO.getProtocolNo())){
			content.setProtocolNo(agentPayDTO.getProtocolNo());
		}
		
		map.put("success", content);
		return map;
		
	}

}
