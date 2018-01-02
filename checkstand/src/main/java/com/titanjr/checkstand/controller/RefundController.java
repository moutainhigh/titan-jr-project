package com.titanjr.checkstand.controller;

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
import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.constants.RSErrorCodeEnum;
import com.titanjr.checkstand.constants.RequestTypeEnum;
import com.titanjr.checkstand.constants.SysConstant;
import com.titanjr.checkstand.dto.TitanRefundDTO;
import com.titanjr.checkstand.request.TLQrOrderRefundRequest;
import com.titanjr.checkstand.request.TLNetBankOrderRefundRequest;
import com.titanjr.checkstand.respnse.TitanOrderRefundResponse;
import com.titanjr.checkstand.service.TLOrderRefundService;
import com.titanjr.checkstand.strategy.StrategyFactory;
import com.titanjr.checkstand.strategy.refund.OrderRefundStrategy;
import com.titanjr.checkstand.util.CommonUtil;
import com.titanjr.checkstand.util.WebUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2017/11/17.
 */
@Controller
@RequestMapping(value = "/refund")
public class RefundController extends BaseController {
    /** 
	 * 
	 */
	private static final long serialVersionUID = -7373722251042176114L;
	private final static Logger logger = LoggerFactory.getLogger(RefundController.class);
	
	@Resource
	private TLOrderRefundService tlOrderRefundService;
	

	@RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public String entrance(HttpServletRequest request, RedirectAttributes attr, Model model) throws Exception {

		//查询订单，获取支付方式
        
        //根据支付方式获取退款调用策略，调对应的接口
		PayTypeEnum payTypeEnum = PayTypeEnum.QR_WECHAT_URL;
		OrderRefundStrategy refundStrategy =  StrategyFactory.getRefundStrategy(payTypeEnum);
        String redirectUrl = refundStrategy.redirectResult(request);
        super.resetParameter(request,attr);
        
        return "redirect:" + redirectUrl;
		
    }
	
	
	/**
	 * 网银支付订单退款
	 * @author Jerry
	 * @date 2017年12月18日 下午6:17:07
	 */
	@RequestMapping(value = "/netBankOrderRefund", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public TitanOrderRefundResponse NetBankOrderRefund(HttpServletRequest request, RedirectAttributes attr, Model model) {
    	
    	TitanOrderRefundResponse titanOrderRefundResponse = new TitanOrderRefundResponse();
    	TLNetBankOrderRefundRequest tlNetBankOrderRefundRequest = new TLNetBankOrderRefundRequest();
    	
    	try {
    		
    		TitanRefundDTO refundDTO = WebUtils.switch2RequestDTO(TitanRefundDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(refundDTO);
			if (!res.isSuccess()){
				logger.error("参数错误：{}", res.getReturnMessage());
				titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return titanOrderRefundResponse;
			}
			
			tlNetBankOrderRefundRequest.setMerchantId(refundDTO.getMerchantNo());
			tlNetBankOrderRefundRequest.setOrderNo(refundDTO.getOrderNo());
			tlNetBankOrderRefundRequest.setRefundAmount(Integer.parseInt(refundDTO.getRefundAmount()));//校验
			tlNetBankOrderRefundRequest.setMchtRefundOrderNo(refundDTO.getRefundOrderno());
			tlNetBankOrderRefundRequest.setVersion("v2.3");
			tlNetBankOrderRefundRequest.setSignType("0");
			tlNetBankOrderRefundRequest.setOrderDatetime(refundDTO.getOrderTime());
			tlNetBankOrderRefundRequest.setRequestType(RequestTypeEnum.GATEWAY_PAY_QUERY_REFUND.getKey());
			
			titanOrderRefundResponse = tlOrderRefundService.netBankOrderRefund(tlNetBankOrderRefundRequest);
			return titanOrderRefundResponse;
			
		} catch (Exception e) {
			
			logger.error("网银退款发生异常：{}", e);
			titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanOrderRefundResponse;
			
		}
        
    }
	
	
	/**
	 * 第三方扫码支付-交易撤销、退款
	 * @author Jerry
	 * @date 2017年12月18日 下午6:17:30
	 */
	@RequestMapping(value = "/qrOrderRefund", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
	public TitanOrderRefundResponse qrOrderRefund(HttpServletRequest request, RedirectAttributes attr, Model model) {
    	
    	TitanOrderRefundResponse titanOrderRefundResponse = new TitanOrderRefundResponse();
    	TLQrOrderRefundRequest tlQrOrderRefundRequest = new TLQrOrderRefundRequest();
    	
    	try {
        	
    		TitanRefundDTO refundDTO = WebUtils.switch2RequestDTO(TitanRefundDTO.class, request);
			ValidateResponse res = GenericValidate.validateNew(refundDTO);
			if (!res.isSuccess()){
				logger.error("参数错误：{}", res.getReturnMessage());
				titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.build(res.getReturnMessage()));
				return titanOrderRefundResponse;
			}
            
            tlQrOrderRefundRequest.setCusid(SysConstant.QRCODE_CUSTID);
            tlQrOrderRefundRequest.setVersion("11");
            tlQrOrderRefundRequest.setReqsn(refundDTO.getRefundOrderno());
            tlQrOrderRefundRequest.setOldreqsn(refundDTO.getOrderNo());
            tlQrOrderRefundRequest.setTrxamt(refundDTO.getRefundAmount());
            tlQrOrderRefundRequest.setRandomstr(CommonUtil.getValidatecode(8));
            tlQrOrderRefundRequest.setRequestType(RequestTypeEnum.PUBLIC_CANCEL.getKey());//撤销 
            //查询订单，根据订单时间判定是撤销还是退款
            if(true){
            	tlQrOrderRefundRequest.setRequestType(RequestTypeEnum.PUBLIC_REFUND.getKey());//退款
            }
            
            titanOrderRefundResponse = tlOrderRefundService.qrCodeOrderRefund(tlQrOrderRefundRequest);
        	return titanOrderRefundResponse;
			
		} catch (Exception e) {
			
			logger.error("扫码交易退款发生异常：{}", e);
			titanOrderRefundResponse.putErrorResult(RSErrorCodeEnum.SYSTEM_ERROR);
			return titanOrderRefundResponse;
			
		}
        
    }

}
