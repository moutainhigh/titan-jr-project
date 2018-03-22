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
import com.fangcang.titanjr.dto.bean.TitanOrderPayDTO;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.constants.TLQrReturnCodeEnum;
import com.titanjr.checkstand.dto.TitanRefundQueryDTO;
import com.titanjr.checkstand.request.RBQuickPayRefundQueryRequest;
import com.titanjr.checkstand.request.TLNetBankRefundQueryRequest;
import com.titanjr.checkstand.request.TLQrTradeQueryRequest;
import com.titanjr.checkstand.respnse.TLQrTradeQueryResponse;
import com.titanjr.checkstand.respnse.TitanOrderRefundResponse;
import com.titanjr.checkstand.respnse.TitanRefundQueryResponse;
import com.titanjr.checkstand.service.RBQuickPayService;
import com.titanjr.checkstand.service.TLCommonService;
import com.titanjr.checkstand.service.TLPayQueryService;
import com.titanjr.checkstand.service.TLRefundQueryService;
import com.titanjr.checkstand.strategy.StrategyFactory;
import com.titanjr.checkstand.strategy.query.QueryStrategy;
import com.titanjr.checkstand.util.CommonUtil;
import com.titanjr.checkstand.util.WebUtils;

/**
 * 退款查询
 * @author Jerry
 * @date 2017年12月5日 下午6:08:23
 */
@Controller
@RequestMapping(value = "/rfQuery")
public class RefundQueryController extends BaseController {

	/** 
	 * 
	 */
	private static final long serialVersionUID = 6704651168109119912L;
	private final static Logger logger = LoggerFactory.getLogger(RefundQueryController.class);
	
	@Resource
	private TLPayQueryService tlPayQueryService;
	
	@Resource
	private TLRefundQueryService tlRefundQueryService;
	
	@Resource
	private RBQuickPayService rbQuickPayService;
	
	@Resource
	private TLCommonService tlCommonService;
	
	@Resource
	private TitanOrderService titanOrderService;
	
	
	@RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public String entrance(HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {
		
		String errorUrl = WebUtils.getRequestBaseUrl(request) + "/rfQuery/returnError.shtml";
		try {
			
			TitanRefundQueryDTO refundQueryDTO = WebUtils.switch2RequestDTO(TitanRefundQueryDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(refundQueryDTO);
			if (!res.isSuccess()){
				logger.error("【退款查询】参数错误：{}", res.getReturnMessage());
				return "redirect:" + errorUrl;
			}
			
			//查询充值单
			TitanOrderPayDTO titanOrderPayDTO = new TitanOrderPayDTO();
			titanOrderPayDTO.setOrderNo(refundQueryDTO.getOrderNo());
			titanOrderPayDTO = titanOrderService.getTitanOrderPayDTO(titanOrderPayDTO);
			if(titanOrderPayDTO == null){
				logger.error("【退款查询】失败，查询充值单为空，orderNo={}，refundOrderNo={}", refundQueryDTO
						.getOrderNo(), refundQueryDTO.getRefundOrderno());
				return "redirect:" + errorUrl;
			}
			
			//根据支付方式获取查询策略，调对应的接口
			PayTypeEnum payTypeEnum = PayTypeEnum.getPayTypeEnum(titanOrderPayDTO.getPayType());
			if(payTypeEnum == null){
				logger.error("【退款查询】失败，获取payTypeEnum为空");
				return "redirect:" + errorUrl;
			}
			QueryStrategy refundQueryStrategy =  StrategyFactory.getRefundQueryStrategy(payTypeEnum);
			if(refundQueryStrategy == null){
				logger.error("【退款查询】失败，获取相应的退款查询策略为空");
				return "redirect:" + errorUrl;
			}
			
			String redirectUrl = refundQueryStrategy.redirectResult(request);
			if(!StringUtil.isValidString(redirectUrl)){
				logger.error("【退款查询】获取重定向地址失败");
				return super.payFailedCallback(model);
			}
			super.resetParameter(request,attr);
			
			//return "forward:" + redirectUrl;
			return "redirect:" + WebUtils.getRequestBaseUrl(request) + redirectUrl;
			
		} catch (Exception e) {
			
			logger.error("【退款查询】发生异常：", e);
			return "redirect:" + errorUrl;
			
		}
        
    }
    
    
    /**
     * 【网银支付】退款结果查询
     * @author Jerry
     * @date 2017年11月29日 上午10:39:01
     */
    @RequestMapping(value = "/netBankRefundQuery", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TitanRefundQueryResponse NetBankPayQuery(HttpServletRequest request, RedirectAttributes attr, Model model) {
    	
    	TLNetBankRefundQueryRequest tlNetBankRefundQueryRequest = new TLNetBankRefundQueryRequest();
    	TitanRefundQueryResponse titanRefundQueryResponse = new TitanRefundQueryResponse();
    	
    	try {
    		
			TitanRefundQueryDTO refundQueryDTO = WebUtils.switch2RequestDTO(TitanRefundQueryDTO.class, request);
			tlNetBankRefundQueryRequest.setMerchantId(SysConstant.TL_NETBANK_MERCHANT);
			tlNetBankRefundQueryRequest.setOrderNo(refundQueryDTO.getOrderNo());
			tlNetBankRefundQueryRequest.setMchtRefundOrderNo(refundQueryDTO.getRefundOrderno());
			tlNetBankRefundQueryRequest.setRefundAmount(refundQueryDTO.getRefundAmount());
			//tlNetBankRefundQueryRequest.setRefundDatetime("20171205145011");//非必填，退款请求会返回这个值
			tlNetBankRefundQueryRequest.setVersion(SysConstant.TL_NETBANK_REFUND_QUERY_VERSION);
			tlNetBankRefundQueryRequest.setSignType(SysConstant.TL_NETBANK_SIGNTYPE);
			tlNetBankRefundQueryRequest.setRequestType(RequestTypeEnum.GATEWAY_REFUNDQUERY.getKey());
			
			titanRefundQueryResponse = tlRefundQueryService.netBankRefundQuery(tlNetBankRefundQueryRequest);
			return titanRefundQueryResponse;
			
		} catch (Exception e) {
			
			logger.error("订单查询发生异常：{}", e);
			titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanRefundQueryResponse;
			
		}
        
    }
    
    
    /**
     * 扫码交易撤销、退款结果查询
     * @author Jerry
     * @date 2017年12月20日 下午2:10:58
     */
    @RequestMapping(value = "/qrOrderRefundQuery", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TitanRefundQueryResponse qrOrderRefundQuery(HttpServletRequest request, RedirectAttributes attr, Model model) {
    	
    	TLQrTradeQueryRequest tlQrTradeQueryRequest = new TLQrTradeQueryRequest();
    	TitanRefundQueryResponse titanRefundQueryResponse = new TitanRefundQueryResponse();
    	
    	try {
    		
    		TitanRefundQueryDTO refundQueryDTO = WebUtils.switch2RequestDTO(TitanRefundQueryDTO.class, request);
			tlQrTradeQueryRequest.setCusid(SysConstant.TL_QRCODE_CUSTID);
			tlQrTradeQueryRequest.setVersion(SysConstant.TL_QRCODE_VERSION);
			tlQrTradeQueryRequest.setReqsn(refundQueryDTO.getRefundOrderno());
			tlQrTradeQueryRequest.setRandomstr(CommonUtil.getValidatecode(8));
			tlQrTradeQueryRequest.setRequestType(RequestTypeEnum.PUBLIC_QUERY.getKey());
			
			TLQrTradeQueryResponse tlQrTradeQueryResponse = tlCommonService.qrCodeTradeQuery(tlQrTradeQueryRequest);
			
			if(tlQrTradeQueryResponse == null){
				logger.error("【通联-扫码支付退款查询】失败，请查看TLCommonService的报错日志");
				titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
				return titanRefundQueryResponse;
			}
			if(!tlQrTradeQueryResponse.qrCodeResult()){
				logger.error("【通联-扫码支付退款查询】失败，retmsg：{}", tlQrTradeQueryResponse.getRetmsg());
				titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.build(tlQrTradeQueryResponse.getRetmsg()+"，"+tlQrTradeQueryResponse.getErrmsg()));
				return titanRefundQueryResponse;
			}
			
			titanRefundQueryResponse.setRefundStatus(TLQrReturnCodeEnum.getRsRefundStatus(tlQrTradeQueryResponse.getTrxstatus()));
			
			return titanRefundQueryResponse;
			
		} catch (Exception e) {
			
			logger.error("订单查询发生异常：", e);
			titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanRefundQueryResponse;
			
		}
        
    }
    
    
    /**
     * 快捷支付退款查询
     * @author Jerry
     * @date 2018年1月8日 下午2:24:23
     */
    @RequestMapping(value = "/quickPayRefundQuery", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TitanRefundQueryResponse quickPayRefundQuery(HttpServletRequest request, RedirectAttributes attr, Model model) {
    	
    	RBQuickPayRefundQueryRequest rbQuickPayRefundQueryRequest = new RBQuickPayRefundQueryRequest();
    	TitanRefundQueryResponse titanRefundQueryResponse = new TitanRefundQueryResponse();
    	
    	try {
    		
    		TitanRefundQueryDTO refundQueryDTO = WebUtils.switch2RequestDTO(TitanRefundQueryDTO.class, request);
			rbQuickPayRefundQueryRequest.setMerchant_id(SysConstant.RB_MERCHANT);
			rbQuickPayRefundQueryRequest.setOrder_no(refundQueryDTO.getRefundOrderno());
			rbQuickPayRefundQueryRequest.setOrig_order_no(refundQueryDTO.getOrderNo());
			rbQuickPayRefundQueryRequest.setVersion(SysConstant.RB_QUICKPAY_VERSION);
			rbQuickPayRefundQueryRequest.setSign_type(SysConstant.RB_SIGN_TYPE);
			rbQuickPayRefundQueryRequest.setRequestType(RequestTypeEnum.QUICK_REFUND_QUERY.getKey());
			
			titanRefundQueryResponse = rbQuickPayService.refundQuery(rbQuickPayRefundQueryRequest);
			titanRefundQueryResponse.setOrderNo(refundQueryDTO.getOrderNo());
			titanRefundQueryResponse.setOrderTime(refundQueryDTO.getOrderTime());
			titanRefundQueryResponse.setRefundOrderno(refundQueryDTO.getRefundOrderno());
			
			return titanRefundQueryResponse;
			
		} catch (Exception e) {
			
			logger.error("订单查询发生异常：", e);
			titanRefundQueryResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanRefundQueryResponse;
			
		}
        
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/returnError", method = {RequestMethod.GET, RequestMethod.POST})
    private TitanOrderRefundResponse returnError(HttpServletRequest request, Model model) {
    	
		TitanOrderRefundResponse titanOrderRefundResponse = new TitanOrderRefundResponse();
		titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
		return titanOrderRefundResponse;
        
    }
	
}
