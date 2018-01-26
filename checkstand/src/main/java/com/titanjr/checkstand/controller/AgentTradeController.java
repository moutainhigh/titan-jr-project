/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AgentTradeController.java
 * @author Jerry
 * @date 2017年12月25日 上午10:02:15  
 */
package com.titanjr.checkstand.controller;

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
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.TLAgentInfoRequestDTO;
import com.titanjr.checkstand.dto.TLAgentPayTransDTO;
import com.titanjr.checkstand.dto.TLAgentQueryTransDTO;
import com.titanjr.checkstand.dto.TitanAgentDownloadDTO;
import com.titanjr.checkstand.dto.TitanAgentPayDTO;
import com.titanjr.checkstand.dto.TitanAgentQueryDTO;
import com.titanjr.checkstand.request.TLAgentTradeRequest;
import com.titanjr.checkstand.respnse.TitanAgentPayResponse;
import com.titanjr.checkstand.respnse.TitanAgentQueryResponse;
import com.titanjr.checkstand.service.TLAgentTradeService;
import com.titanjr.checkstand.strategy.StrategyFactory;
import com.titanjr.checkstand.strategy.agent.AgentTradeStrategy;
import com.titanjr.checkstand.util.WebUtils;

/**
 * 代收付
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
	
	
	@RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public String entrance(HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {
        
		String tradeCode = request.getParameter("tradeCode");
		
        //根据交易代码来判定走到具体哪个接口
		AgentTradeStrategy agentTradeStrategy =  StrategyFactory.getAgentTradeStrategy(tradeCode);
        if(agentTradeStrategy == null){
        	logger.error("【账户交易】获取业务策略为空，tradeCode={}", tradeCode);
        	return super.payFailedCallback(model);
        }
        String redirectUrl = agentTradeStrategy.redirectResult(request);
        super.resetParameter(request,attr);
        
        return "redirect:" + redirectUrl;
        
    }
	
	
	/**
	 * 单笔实时代付
	 * @author Jerry
	 * @date 2017年12月27日 下午5:36:40
	 */
	@RequestMapping(value = "/agentPay", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public TitanAgentPayResponse agentPay(HttpServletRequest request, Model model) throws Exception {
		
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
			agentInfo.setLEVEL("5");
			agentInfo.setDATA_TYPE("2");
			agentInfo.setVERSION("03");
			agentInfo.setMERCHANT_ID(SysConstant.TL_AGENT_MERCHANT);
			tlAgentTradeRequest.setINFO(agentInfo);
			
			TLAgentPayTransDTO trans= new TLAgentPayTransDTO();
			trans.setBUSINESS_CODE(SysConstant.WITHDRAW_CODE);
			trans.setMERCHANT_ID(SysConstant.TL_AGENT_MERCHANT);
			trans.setSUBMIT_TIME(agentPayDTO.getSubmitTime());
			trans.setACCOUNT_NAME(agentPayDTO.getAccountName());
			trans.setACCOUNT_NO(agentPayDTO.getAccountNo());
			trans.setACCOUNT_PROP(agentPayDTO.getAccountProperty());
			trans.setACCOUNT_TYPE(agentPayDTO.getAccountType());
			trans.setAMOUNT(agentPayDTO.getTradeAmount());
			trans.setBANK_CODE(agentPayDTO.getBankInfo());
			trans.setCURRENCY(agentPayDTO.getCurrency());
			tlAgentTradeRequest.addTrx(trans);
			tlAgentTradeRequest.setRequestType(RequestTypeEnum.AGENT_TRADE.getKey());
			
			titanAgentPayResponse = tlAgentTradeService.agentPay(tlAgentTradeRequest);
			return titanAgentPayResponse;
			
		} catch (Exception e) {
			
			logger.error("【通联-代付请求】发生异常：", e);
			titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanAgentPayResponse;
			
		}
        
    }
	
	
	/**
	 * 交易查询
	 * @author Jerry
	 * @date 2017年12月27日 下午6:10:35
	 */
	@RequestMapping(value = "/agentQuery", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
    public TitanAgentQueryResponse agentQuery(HttpServletRequest request, Model model) throws Exception {
		
		TitanAgentQueryResponse titanAgentQueryResponse = new TitanAgentQueryResponse();
		
		try {
    		
			TitanAgentQueryDTO agentQueryDTO = WebUtils.switch2RequestDTO(TitanAgentQueryDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(agentQueryDTO);
			if (!res.isSuccess()){
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
			agentInfo.setLEVEL("5");
			agentInfo.setDATA_TYPE("2");
			agentInfo.setVERSION("03");
			agentInfo.setMERCHANT_ID(SysConstant.TL_AGENT_MERCHANT);
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
			
			titanAgentQueryResponse = tlAgentTradeService.agentQuery(tlAgentTradeRequest);
			return titanAgentQueryResponse;
			
		} catch (Exception e) {
			
			logger.error("【通联-账户交易查询】发生异常：", e);
			titanAgentQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanAgentQueryResponse;
			
		}
        
    }
	
	
	@RequestMapping(value = "/agentDownload", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public String agentDownload(HttpServletRequest request, Model model) throws Exception {
		
		TitanAgentPayResponse titanAgentPayResponse = new TitanAgentPayResponse();
		
		try {
    		
			TitanAgentDownloadDTO titanAgentDownloadDTO = WebUtils.switch2RequestDTO(TitanAgentDownloadDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(titanAgentDownloadDTO);
			if (!res.isSuccess()){
				logger.error("【通联-对账文件下载】参数错误：{}", res.getReturnMessage());
				/*titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return titanAgentPayResponse;*/
				return "PARAM ERROR";
			}
			
			TLAgentTradeRequest tlAgentTradeRequest = new TLAgentTradeRequest();
			
			TLAgentInfoRequestDTO agentInfo  = new TLAgentInfoRequestDTO();
			agentInfo.setTRX_CODE(titanAgentDownloadDTO.getTradeCode());
			agentInfo.setREQ_SN(SysConstant.TL_AGENT_MERCHANT+"-"+String.valueOf(System.currentTimeMillis()));
			agentInfo.setUSER_NAME(SysConstant.USER_NAME);
			agentInfo.setUSER_PASS(SysConstant.USER_PWD);
			agentInfo.setLEVEL("5");
			agentInfo.setDATA_TYPE("2");
			agentInfo.setVERSION("03");
			tlAgentTradeRequest.setINFO(agentInfo);
			
			TLAgentQueryTransDTO trans= new TLAgentQueryTransDTO();//对象共用
			trans.setSTATUS(Integer.parseInt(titanAgentDownloadDTO.getTradeStatus()));
			trans.setMERCHANT_ID(SysConstant.TL_AGENT_MERCHANT);
			trans.setTYPE(Integer.parseInt(titanAgentDownloadDTO.getQueryType())) ;
			trans.setSTART_DAY(titanAgentDownloadDTO.getStartDate());
			trans.setEND_DAY(titanAgentDownloadDTO.getEndDate());
			tlAgentTradeRequest.addTrx(trans);
			
			tlAgentTradeService.agentDownload(tlAgentTradeRequest);
			
			/*titanAgentPayResponse = tlAgentTradeService.agentPay(tlAgentTradeRequest);
			return titanAgentPayResponse;*/
			return "SUCCESS";
			
		} catch (Exception e) {
			
			logger.error("【通联-对账文件下载】发生异常：", e);
			titanAgentPayResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			//return titanAgentPayResponse;
			return "ERROR";
		}
        
    }

}
