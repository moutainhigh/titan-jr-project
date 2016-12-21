package com.fangcang.titanjr.pay.services;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONSerializer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import com.fangcang.titanjr.common.enums.BankCardEnum;
import com.fangcang.titanjr.common.enums.TitanMsgCodeEnum;
import com.fangcang.titanjr.common.enums.entity.TitanOrgEnum;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.dto.bean.FinancialOrganDTO;
import com.fangcang.titanjr.dto.bean.IdentityInfo;
import com.fangcang.titanjr.dto.bean.PayMethodConfigDTO;
import com.fangcang.titanjr.dto.bean.QuickPaymentData;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.OrderRequest;
import com.fangcang.titanjr.dto.request.OrderSaveAndBindCardRequest;
import com.fangcang.titanjr.dto.request.RechargeRequest;
import com.fangcang.titanjr.dto.request.TitanPaymentRequest;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.dto.response.FinancialOrganResponse;
import com.fangcang.titanjr.dto.response.OrderSaveAndBindCardResponse;
import com.fangcang.titanjr.dto.response.RechargeResponse;
import com.fangcang.titanjr.dto.response.TransOrderCreateResponse;
import com.fangcang.titanjr.enums.AmtTypeEnum;
import com.fangcang.titanjr.enums.BusiCodeEnum;
import com.fangcang.titanjr.enums.CharsetEnum;
import com.fangcang.titanjr.enums.OrderMarkEnum;
import com.fangcang.titanjr.enums.OrderTypeEnum;
import com.fangcang.titanjr.enums.PayTypeEnum;
import com.fangcang.titanjr.enums.SignTypeEnum;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.StringUtil;

/**
 * 
 * @author fangdaikang
 *
 */
@Component("quickPaymentService")
public class QuickPaymentService {
	
	@Resource
	private TitanOrderService titanOrderService;
	
	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;
	
	@Resource
	private TitanPaymentService titanPaymentService;
	
	@Resource
	private TitanFinancialOrganService titanFinancialOrganService;
	
	private static final Log log = LogFactory.getLog(QuickPaymentService.class);
	
	/**
	 * 查询是否实名认证，如果已经实名认证返回银行卡
	 * @param identityInfo
	 * @return
	 */
	public String queryCertification(IdentityInfo identityInfo){
		if(null == identityInfo 
				|| !StringUtil.isValidString(identityInfo.getCertificateNumber())
				|| !StringUtil.isValidString(identityInfo.getMerchantCode())
				|| !StringUtil.isValidString(identityInfo.getUserName())){
			
		}
		
		return null;
	}
	
	//快捷支付
	public RechargeResponse quickPayment(QuickPaymentData quickPaymentData){
		
		RechargeResponse rechargeResponse = new RechargeResponse();
		
		TransOrderRequest transOrderRequest = new TransOrderRequest();
        transOrderRequest.setPayorderno(quickPaymentData.getOrderNo());
		TransOrderDTO transOrderDTO = titanOrderService.queryTransOrderDTO(transOrderRequest);
		
		if(null == transOrderDTO){
			log.error("订单信息为空");
			rechargeResponse.putErrorResult(TitanMsgCodeEnum.QUERY_ORDER_FAIL);
			return rechargeResponse;
		}
		//获取相应的数据，身份信息,真实姓名
//		FinancialOrganQueryRequest organQueryRequest = new FinancialOrganQueryRequest();
//		organQueryRequest.setUserId(transOrderDTO.getUserid());
//		FinancialOrganResponse financialOrganResponse = titanFinancialOrganService.queryFinancialOrgan(organQueryRequest);
//		if(!financialOrganResponse.isResult()){
//			log.error("机构不存在");
//			rechargeResponse.putErrorResult(TitanMsgCodeEnum.ACCOUNT_NOT_EXIST);
//			return rechargeResponse;
//		}
		
//		FinancialOrganDTO financialOrganDTO = financialOrganResponse.getFinancialOrganDTO();
		if(CommonConstant.IS_BIND_CARD.equals(quickPaymentData.getIsBindCard())){
			OrderSaveAndBindCardRequest request  = converTOOrderSaveAndBindCardRequest(quickPaymentData, transOrderDTO);
//			request.setAccountName(financialOrganDTO.getOrgName());
//			request.setCertificateNumber(financialOrganDTO.getCertificateNumber());
			request.setAccountName("方代康");
			request.setCertificateNumber("422801199110012637");
			OrderSaveAndBindCardResponse orderSaveAndBindCardResponse = titanFinancialTradeService.saveTransOrderAndBindCard(request);
			if(!orderSaveAndBindCardResponse.isResult()){
				log.error("下单并绑卡失败:"+JSONSerializer.toJSON(orderSaveAndBindCardResponse));
				rechargeResponse.putErrorResult(orderSaveAndBindCardResponse.getReturnCode(), orderSaveAndBindCardResponse.getReturnMessage());
			    return rechargeResponse;
			}
			//更新本地数据
			transOrderDTO.setOrderid(orderSaveAndBindCardResponse.getOrderId());
			transOrderDTO.setOrdertypeid(request.getOrderTypeId());
			transOrderDTO.setProductid(request.getProductId());
			
			boolean updateTransOrder = titanOrderService.updateTransOrder(transOrderDTO);
			if(!updateTransOrder){
				log.error("保存落单信息失败:");
				rechargeResponse.putErrorResult(TitanMsgCodeEnum.RS_SUCCESS_SAVELOCA_FAIL);
				return rechargeResponse;
			}
			
		}else{
			TitanPaymentRequest titanPaymentRequest = converToTitanPaymentRequest(quickPaymentData, transOrderDTO);
			TransOrderCreateResponse transOrderCreateResponse = titanFinancialTradeService.createRsOrder(titanPaymentRequest);
			if(!transOrderCreateResponse.isResult()){
				log.error("融数下单失败:"+JSONSerializer.toJSON(transOrderCreateResponse));
				rechargeResponse.putErrorResult(TitanMsgCodeEnum.RS_ADD_ORDER_FAIL);
			    return rechargeResponse;
			}
			transOrderDTO.setOrderid(transOrderCreateResponse.getOrderNo());
		}
		
		//封装相应的数据调转融数的连连支付
		RechargeRequest rechargeRequest = convertToRechargeRequest(transOrderDTO);
		rechargeResponse = titanFinancialTradeService.packageRechargeData(rechargeRequest);
		if(!rechargeResponse.isResult()){
			log.error("封装充值信息失败");
			rechargeResponse.putErrorResult(TitanMsgCodeEnum.PACKAGE_RECHARGE_DATA_FAIL);
			return rechargeResponse;
		}
		rechargeResponse.putSuccess();
		return rechargeResponse;
	}
	
	private TitanPaymentRequest converToTitanPaymentRequest(QuickPaymentData quickPaymentData,TransOrderDTO transOrderDTO){
		TitanPaymentRequest titanPaymentRequest = new TitanPaymentRequest();
		
		titanPaymentRequest.setAdjustcontent(quickPaymentData.getAccountNumber());
		titanPaymentRequest.setBankInfo("");
		if(null !=transOrderDTO.getTradeamount()){
			BigDecimal amount = new BigDecimal(transOrderDTO.getTradeamount()).divide(new BigDecimal(100));
			titanPaymentRequest.setPayAmount(amount.toString());
		}
		titanPaymentRequest.setPayOrderNo(transOrderDTO.getPayorderno());
		titanPaymentRequest.setPayType(PayTypeEnum.Quick_Payment);
		titanPaymentRequest.setUserrelateid(transOrderDTO.getUserrelateid());
		//此处需要修改，收款方的productId
		titanPaymentRequest.setInterProductid(transOrderDTO.getProductid());
		titanPaymentRequest.setOrderTypeId(OrderTypeEnum.OrderType_QUICK.key);
		
		return titanPaymentRequest;
	}
	
	private OrderSaveAndBindCardRequest converTOOrderSaveAndBindCardRequest(QuickPaymentData quickPaymentData,TransOrderDTO transOrderDTO){
		OrderSaveAndBindCardRequest request = new OrderSaveAndBindCardRequest();
		request.setAccountNumber(quickPaymentData.getAccountNumber());
		request.setBankHead(quickPaymentData.getBankHead());
		request.setBankHeadName(quickPaymentData.getBankHeadName());
		//1对公，2是对私
		request.setAccountProperty(String.valueOf(2));
		request.setAccountPurpose(BankCardEnum.BankCardPurposeEnum.OTHER_CARD.getKey());
		request.setAccountTypeId(CommonConstant.ACCOUNT_TYPE_ID);
		request.setAmount(transOrderDTO.getTradeamount().toString());
		request.setCertificateType(String.valueOf(TitanOrgEnum.CertificateType.SFZ.getKey()));
		request.setConstId(CommonConstant.RS_FANGCANG_CONST_ID);
		request.setProductId(CommonConstant.RS_FANGCANG_PRODUCT_ID);
		//人民币
		request.setCurrency("CNY");
		request.setOrderTime(new Date());
		request.setOrderTypeId(OrderTypeEnum.OrderType_BindCard.getKey());
		request.setUserId(transOrderDTO.getUserid());
		request.setUserOrderId(transOrderDTO.getUserorderid());
	    return request;
	}
	
	
	private RechargeRequest convertToRechargeRequest(TransOrderDTO transOrderDTO){
		RechargeRequest rechargeRequest = new RechargeRequest();
		rechargeRequest.setAmtType(AmtTypeEnum.RMB.getKey());
		rechargeRequest.setBankInfo("");
		rechargeRequest.setBusiCode(BusiCodeEnum.MerchantOrder.getKey());
		rechargeRequest.setCharset(CharsetEnum.UTF_8.getKey());
		rechargeRequest.setNotifyUrl(transOrderDTO.getNotifyUrl());
		rechargeRequest.setOrderNo(transOrderDTO.getOrderid());
		rechargeRequest.setMerchantNo(transOrderDTO.getConstid());
		rechargeRequest.setPayType(PayTypeEnum.Quick_Payment.getKey());
		rechargeRequest.setOrderMark(OrderMarkEnum.InsideOrder.getKey());
		if(null !=transOrderDTO.getTradeamount()){
			rechargeRequest.setOrderAmount(transOrderDTO.getTradeamount().toString());
		}
	    rechargeRequest.setTransorderid(transOrderDTO.getTransid());
        rechargeRequest.setUserid(transOrderDTO.getUserid());
        rechargeRequest.setProductNum("1");
        rechargeRequest.setOrderTime(DateUtil.sdf5.format(new Date()));
        rechargeRequest.setSignType(SignTypeEnum.MD5.getKey());
        PayMethodConfigDTO payMethodConfigDTO = titanFinancialTradeService.getPayMethodConfigDTO(null);
		rechargeRequest.setNotifyUrl(payMethodConfigDTO.getNotifyurl());
		rechargeRequest.setPageUrl(payMethodConfigDTO.getPageurl());
		
		return rechargeRequest;
	}
	
	
	

}
