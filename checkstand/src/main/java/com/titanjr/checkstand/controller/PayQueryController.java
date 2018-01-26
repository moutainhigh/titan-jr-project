package com.titanjr.checkstand.controller;

import java.util.Date;

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
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.DateUtil;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.constants.TLQrReturnCodeEnum;
import com.titanjr.checkstand.dto.TitanPayQueryDTO;
import com.titanjr.checkstand.request.RBQuickPayQueryRequest;
import com.titanjr.checkstand.request.TLNetBankPayQueryRequest;
import com.titanjr.checkstand.request.TLQrTradeQueryRequest;
import com.titanjr.checkstand.respnse.TLQrTradeQueryResponse;
import com.titanjr.checkstand.respnse.TitanPayQueryResponse;
import com.titanjr.checkstand.service.RBQuickPayService;
import com.titanjr.checkstand.service.TLCommonService;
import com.titanjr.checkstand.service.TLPayQueryService;
import com.titanjr.checkstand.strategy.StrategyFactory;
import com.titanjr.checkstand.strategy.query.QueryStrategy;
import com.titanjr.checkstand.util.CommonUtil;
import com.titanjr.checkstand.util.WebUtils;

/**
 * Created by zhaoshan on 2017/10/19.
 */
@Controller
@RequestMapping(value = "/query")
public class PayQueryController extends BaseController {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 6704651168109119912L;
	private final static Logger logger = LoggerFactory.getLogger(PayQueryController.class);
	
	@Resource
	private TLPayQueryService tlPayQueryService;
	
	@Resource
	private RBQuickPayService rbQuickPayService;

	@Resource
	private TLCommonService tlCommonService;
	
	@Resource
	private TitanOrderService titanOrderService;
	
	
	@RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public String entrance(HttpServletRequest request, RedirectAttributes attr, Model model) {
		
		String errorUrl = WebUtils.getRequestBaseUrl(request) + "/query/returnError.shtml";
		TitanPayQueryDTO payQueryDTO = WebUtils.switch2RequestDTO(TitanPayQueryDTO.class, request);
		ValidateResponse res = GenericValidate.validateNew(payQueryDTO);
		if (!res.isSuccess()){
			logger.error("【支付查询】参数错误：{}", res.getReturnMessage());
			return "redirect:" + errorUrl;
		}
		
		//查询充值单
		TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
		titanOrderPayDTO.setMerchantNo(payQueryDTO.getMerchantNo());
		titanOrderPayDTO.setOrderNo(payQueryDTO.getOrderNo());
		titanOrderPayDTO = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
		if(titanOrderPayDTO == null){
			logger.error("【支付查询】失败，查询充值单为空，orderNo={}", payQueryDTO.getOrderNo());
			return "redirect:" + errorUrl;
		}
		
        //根据支付方式获取查询策略，调对应的接口
		PayTypeEnum payTypeEnum = PayTypeEnum.getPayTypeEnum(titanOrderPayDTO.getPayType());
		if(payTypeEnum == null){
			logger.error("【支付查询】失败，获取payTypeEnum为空");
			return "redirect:" + errorUrl;
		}
		QueryStrategy payQueryStrategy =  StrategyFactory.getPayQueryStrategy(payTypeEnum);
		if(payQueryStrategy == null){
			logger.error("【支付查询】失败，获取相应的查询策略为空");
			return "redirect:" + errorUrl;
		}
		
        String redirectUrl = payQueryStrategy.redirectResult(request);
        super.resetParameter(request,attr);
        
        return "forward:" + redirectUrl;
        
    }
    
    
    /**
     * 【网银支付】网银订单查询
     * @author Jerry
     * @date 2017年11月29日 上午10:39:01
     */
    @RequestMapping(value = "/netBankPayQuery", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TitanPayQueryResponse NetBankPayQuery(HttpServletRequest request, RedirectAttributes attr, Model model) {
    	
    	TLNetBankPayQueryRequest tlNetBankPayQueryRequest = new TLNetBankPayQueryRequest();
    	TitanPayQueryResponse titanPayQueryResponse = new TitanPayQueryResponse();
    	
    	try {
    		
    		TitanPayQueryDTO payQueryDTO = WebUtils.switch2RequestDTO(TitanPayQueryDTO.class, request);
			tlNetBankPayQueryRequest.setMerchantId(SysConstant.TL_NETBANK_MERCHANT);
			tlNetBankPayQueryRequest.setOrderNo(payQueryDTO.getOrderNo());
			tlNetBankPayQueryRequest.setVersion(SysConstant.TL_NETBANK_PAY_QUERY_VERSION);
			tlNetBankPayQueryRequest.setSignType(SysConstant.TL_NETBANK_SIGNTYPE);
			tlNetBankPayQueryRequest.setOrderDatetime(payQueryDTO.getOrderTime());
			tlNetBankPayQueryRequest.setQueryDatetime(DateUtil.dateToString(new Date(), "yyyyMMddHHmmss"));
			tlNetBankPayQueryRequest.setRequestType(RequestTypeEnum.GATEWAY_PAY_QUERY_REFUND.getKey());
			
			titanPayQueryResponse = tlPayQueryService.netBankPayQuery(tlNetBankPayQueryRequest);
			return titanPayQueryResponse;
			
		} catch (Exception e) {
			
			logger.error("【通联-网银支付查询】发生异常：", e);
			titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanPayQueryResponse;
			
		}
        
    }
    
    
    /**
     * 通联扫码支付查询
     * @author Jerry
     * @date 2017年12月21日 上午10:37:14
     */
    @RequestMapping(value = "/qrCodePayQuery", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TitanPayQueryResponse qrCodePayQuery(HttpServletRequest request, RedirectAttributes attr, Model model) {
    	
    	TLQrTradeQueryRequest tlQrTradeQueryRequest = new TLQrTradeQueryRequest();
    	TitanPayQueryResponse titanPayQueryResponse = new TitanPayQueryResponse();
    	
    	try {
    		
    		TitanPayQueryDTO payQueryDTO = WebUtils.switch2RequestDTO(TitanPayQueryDTO.class, request);
			tlQrTradeQueryRequest.setCusid(SysConstant.TL_QRCODE_CUSTID);
			tlQrTradeQueryRequest.setVersion(SysConstant.TL_QRCODE_VERSION);
			tlQrTradeQueryRequest.setReqsn(payQueryDTO.getOrderNo());
			tlQrTradeQueryRequest.setRandomstr(CommonUtil.getValidatecode(8));
			tlQrTradeQueryRequest.setRequestType(RequestTypeEnum.PUBLIC_QUERY.getKey());
			
			//查询
			TLQrTradeQueryResponse tlQrTradeQueryResponse = tlCommonService.qrCodeTradeQuery(tlQrTradeQueryRequest);
			
			if(tlQrTradeQueryResponse == null){
				logger.error("【通联-扫码支付查询】tlQrTradeQueryResponse为空，请查看TLCommonService的报错日志");
				titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanPayQueryResponse;
			}
			if(!tlQrTradeQueryResponse.qrCodeResult()){
				logger.error("【通联-扫码支付查询】失败，retmsg：{}", tlQrTradeQueryResponse.getRetmsg());
				titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.build(tlQrTradeQueryResponse.getRetmsg()));
				return titanPayQueryResponse;
			}
			titanPayQueryResponse.setPayStatsu(TLQrReturnCodeEnum.getRsPayStatus(tlQrTradeQueryResponse.getTrxstatus()));
			
			return titanPayQueryResponse;
			
		} catch (Exception e) {
			
			logger.error("【通联-扫码支付查询】发生异常：", e);
			titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanPayQueryResponse;
			
		}
        
    }
    
    
    /**
     * 快捷支付结果查询
     * @author Jerry
     * @date 2018年1月5日 下午4:17:53
     */
    @ResponseBody
    @RequestMapping(value = "/quickPayQuery", method = {RequestMethod.GET, RequestMethod.POST})
    public TitanPayQueryResponse quickPayQuery(HttpServletRequest request, Model model) {
    	
    	TitanPayQueryResponse titanPayQueryResponse = new TitanPayQueryResponse();
    	
    	try {
    		
			TitanPayQueryDTO titanPayQueryDTO = WebUtils.switch2RequestDTO(TitanPayQueryDTO.class, request);
			RBQuickPayQueryRequest rbQuickPayQueryRequest = new RBQuickPayQueryRequest();
			rbQuickPayQueryRequest.setOrder_no(titanPayQueryDTO.getOrderNo());
			rbQuickPayQueryRequest.setMerchant_id(SysConstant.RB_QUICKPAY_MERCHANT);
			rbQuickPayQueryRequest.setVersion(SysConstant.RB_VERSION);
			rbQuickPayQueryRequest.setSign_type(SysConstant.RB_SIGN_TYPE);
			rbQuickPayQueryRequest.setRequestType(RequestTypeEnum.QUICK_PAY_QUERY.getKey());
			
			titanPayQueryResponse = rbQuickPayService.payQuery(rbQuickPayQueryRequest);
			return titanPayQueryResponse;
			
		} catch (Exception e) {
			
			logger.error("【融宝-快捷支付查询】异常：", e);
			titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanPayQueryResponse;
			
		}
        
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/returnError", method = {RequestMethod.GET, RequestMethod.POST})
    private TitanPayQueryResponse returnError(HttpServletRequest request, Model model) {
    	
    	TitanPayQueryResponse titanPayQueryResponse = new TitanPayQueryResponse();
    	titanPayQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
		return titanPayQueryResponse;
        
    }
	
}
