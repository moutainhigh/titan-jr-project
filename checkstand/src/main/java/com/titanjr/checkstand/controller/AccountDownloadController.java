/** 
 * CopyRright ©2017 深圳市天下房仓科技有限公司 All Right Reserved.
 * 
 * @fileName AccountBillsDownloadController.java
 * @author Jerry
 * @date 2018年3月15日 下午4:48:57  
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
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.TLAgentInfoRequestDTO;
import com.titanjr.checkstand.dto.TLAgentQueryTransDTO;
import com.titanjr.checkstand.dto.TitanAccountDownloadDTO;
import com.titanjr.checkstand.dto.TitanAgentDownloadDTO;
import com.titanjr.checkstand.dto.TitanGatewayDownloadDTO;
import com.titanjr.checkstand.dto.TitanQrcodeDownloadDTO;
import com.titanjr.checkstand.request.RBAgentDownloadRequest;
import com.titanjr.checkstand.request.TLAgentTradeRequest;
import com.titanjr.checkstand.request.TLGatewayPayDownloadRequest;
import com.titanjr.checkstand.request.TLQrCodeDownloadRequest;
import com.titanjr.checkstand.respnse.RSResponse;
import com.titanjr.checkstand.service.AccountDownloadService;
import com.titanjr.checkstand.strategy.StrategyFactory;
import com.titanjr.checkstand.strategy.download.AccountDownloadStrategy;
import com.titanjr.checkstand.util.CommonUtil;
import com.titanjr.checkstand.util.WebUtils;

/**
 * 对账文件下载
 * @author Jerry
 * @date 2018年3月15日 下午4:48:57  
 */
@RequestMapping(value = "/download")
@Controller
public class AccountDownloadController extends BaseController {

	/** 
	 * 
	 */
	private static final long serialVersionUID = -5451307881452814780L;
	private static final Logger logger = LoggerFactory.getLogger(AccountDownloadController.class);
	
	@Resource
	private AccountDownloadService accountDownloadService;
	
	
	/**
	 * 对账文件下载入口，进入后分配到具体下载通道
	 * @author Jerry
	 * @date 2018年3月15日 下午4:52:06
	 */
    @RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public String entrance(HttpServletRequest request, RedirectAttributes attr, Model model) {
        
    	try {
			
			String tradeCode = request.getParameter("tradeCode");
			
			AccountDownloadStrategy accountDownloadStrategy =  StrategyFactory.getAccountDownloadStrategy(tradeCode);
			if(accountDownloadStrategy == null){
				logger.error("【对账文件下载】获取对账文件下载策略为空，tradeCode={}", tradeCode);
				return super.payFailedCallback(model);
			}
			String redirectUrl = accountDownloadStrategy.redirectResult(request);
			if(!StringUtil.isValidString(redirectUrl)){
				logger.error("【对账文件下载】获取跳转路径为空，tradeCode={}", tradeCode);
				return super.payFailedCallback(model);
			}
			
			super.resetParameter(request,attr);
			//return "forward:" + redirectUrl;
			return "redirect:" + WebUtils.getRequestBaseUrl(request) + redirectUrl;
			
		} catch (Exception e) {
			
			logger.error("【对账文件下载】发生异常：", e);
			return super.payFailedCallback(model);
			
		}
        
    }
    
    
    /**
     * 通联网关支付对账文件下载
     * @author Jerry
     * @date 2018年3月16日 上午10:50:22
     */
    @RequestMapping(value = "/tlGatewayPayDownload")
	@ResponseBody
	public RSResponse tlGatewayPayDownload(HttpServletRequest request, Model model) {
		
		RSResponse response = new RSResponse();
		
		try {
			
			TitanGatewayDownloadDTO titanGatewayDownloadDTO = WebUtils.switch2RequestDTO(
					TitanGatewayDownloadDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(titanGatewayDownloadDTO);
			if (!res.isSuccess()){
				logger.error("【通联-网关支付对账文件下载】参数错误：{}", res.getReturnMessage());
				response.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return response;
			}
			
			TLGatewayPayDownloadRequest tlGatewayPayDownloadRequest = new TLGatewayPayDownloadRequest();
			tlGatewayPayDownloadRequest.setMchtCd(SysConstant.TL_NETBANK_MERCHANT);
			tlGatewayPayDownloadRequest.setSettleDate(titanGatewayDownloadDTO.getSettlDate());
			tlGatewayPayDownloadRequest.setRequestType(RequestTypeEnum.GATEWAY_DOWNLOAD.getKey());
			
			response = accountDownloadService.tlGatewayPayDownload(tlGatewayPayDownloadRequest);
			return response;
			
		} catch (Exception e) {
			
			logger.error("【通联-网关支付对账文件下载】发生异常：", e);
			response.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return response;
		}
       
    }
    
    
    /**
     * 通联扫码/公众号支付对账文件下载
     * @author Jerry
     * @date 2018年3月16日 上午10:50:48
     */
    @RequestMapping(value = "/tlQrcodeDownload")
	@ResponseBody
	public RSResponse tlQrcodeDownload(HttpServletRequest request, Model model) {
		
		RSResponse response = new RSResponse();
		
		try {
			
			TitanQrcodeDownloadDTO titanQrcodeDownloadDTO = WebUtils.switch2RequestDTO(
					TitanQrcodeDownloadDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(titanQrcodeDownloadDTO);
			if (!res.isSuccess()){
				logger.error("【通联-扫码/公众号对账文件下载】参数错误：{}", res.getReturnMessage());
				response.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return response;
			}
			
			TLQrCodeDownloadRequest tlQrCodeDownloadRequest = new TLQrCodeDownloadRequest();
			tlQrCodeDownloadRequest.setCusid(SysConstant.TL_QRCODE_CUSTID);
			tlQrCodeDownloadRequest.setDate(titanQrcodeDownloadDTO.getTradeDate());
			tlQrCodeDownloadRequest.setRandomstr(CommonUtil.getValidatecode(8));
			tlQrCodeDownloadRequest.setRequestType(RequestTypeEnum.QRCODE_DOWNLOAD.getKey());
			
			response = accountDownloadService.tlQrCodePayDownload(tlQrCodeDownloadRequest);
			return response;
			
		} catch (Exception e) {
			
			logger.error("【通联-扫码/公众号对账文件下载】发生异常：", e);
			response.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return response;
		}
       
   }
    
    
    /**
     * 通联账户交易对账文件下载
     * @author Jerry
     * @date 2018年3月26日 下午3:12:53
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
			trans.setCONTFEE(titanAgentDownloadDTO.getContFee());
			tlAgentTradeRequest.addTrx(trans);
			tlAgentTradeRequest.setRequestType(RequestTypeEnum.AGENT_TRADE.getKey());
			
			response = accountDownloadService.tlAgentDownload(tlAgentTradeRequest);
			return response;
			
		} catch (Exception e) {
			
			logger.error("【通联-对账文件下载】发生异常：", e);
			response.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return response;
		}
        
    }
	
	
	@RequestMapping(value = "/rbAccountDownload")
	@ResponseBody
	public RSResponse rbAccountDownload(HttpServletRequest request, Model model) {
		
		RSResponse response = new RSResponse();
		
		try {
    		
			TitanAccountDownloadDTO titanAccountDownloadDTO = WebUtils.switch2RequestDTO(TitanAccountDownloadDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(titanAccountDownloadDTO);
			if (!res.isSuccess()){
				logger.error("【融宝-对账文件下载】参数错误：{}", res.getReturnMessage());
				response.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return response;
			}
			
			RBAgentDownloadRequest rbAgentDownloadRequest = new RBAgentDownloadRequest();
			rbAgentDownloadRequest.setMerchant_id(SysConstant.RB_MERCHANT);
			rbAgentDownloadRequest.setTradeDate(titanAccountDownloadDTO.getTradeDate());
			rbAgentDownloadRequest.setRequestType(RequestTypeEnum.AGENT_DOWNLOAD.getKey());
			
			response = accountDownloadService.rbAccountDownload(rbAgentDownloadRequest);
			return response;
			
		} catch (Exception e) {
			
			logger.error("【融宝-对账文件下载】发生异常：", e);
			response.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return response;
		}
        
    }

}
