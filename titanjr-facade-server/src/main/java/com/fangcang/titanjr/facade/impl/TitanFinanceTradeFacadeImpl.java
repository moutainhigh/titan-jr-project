package com.fangcang.titanjr.facade.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fangcang.titanjr.dto.BaseResponseDTO.ReturnCode;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.request.FinancialOrganQueryRequest;
import com.fangcang.titanjr.dto.request.PaymentUrlRequest;
import com.fangcang.titanjr.dto.response.PaymentUrlResponse;
import com.fangcang.titanjr.facade.TitanFinancialTradeFacade;
import com.fangcang.titanjr.request.TitanOrderPaymentRequest;
import com.fangcang.titanjr.response.TitanOrderPaymentResponse;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.util.StringUtil;

@Service("titanFinanceTradeFacade")
public class TitanFinanceTradeFacadeImpl implements TitanFinancialTradeFacade{

	@Resource
	private TitanFinancialTradeService titanFinancialTradeService;
	
	@Resource
	private TitanFinancialOrganService titanFinancialOrganService;
	
	@Override
	public TitanOrderPaymentResponse getOrderPaymentUrl(TitanOrderPaymentRequest titanOrderPaymentRequest) {
		TitanOrderPaymentResponse titanOrderPaymentResponse = new TitanOrderPaymentResponse();
		try{
			if(titanOrderPaymentRequest !=null){
				//如果接收商家的recieveMerchantCode不为空，则去查询
				if(StringUtil.isValidString(titanOrderPaymentRequest.getRecieveMerchantCode())){//查询是否有对应的商家,未对应商家直接返回
					OrgBindInfo orgBindInfo = new OrgBindInfo();
					orgBindInfo.setMerchantCode(titanOrderPaymentRequest.getRecieveMerchantCode());
					orgBindInfo = titanFinancialOrganService.queryOrgBindInfoByUserid(orgBindInfo);
					if(orgBindInfo ==null){
						titanOrderPaymentResponse.putSysError();
						return titanOrderPaymentResponse;
					}
				}
				
				PaymentUrlRequest paymentUrlRequest = convertToPaymentUrlRequest(titanOrderPaymentRequest);
				PaymentUrlResponse paymentUrlResponse = titanFinancialTradeService.getPaymentUrl(paymentUrlRequest);
				if(paymentUrlResponse !=null){
					if(ReturnCode.CODE_SUCCESS.getCode().equals(paymentUrlResponse.getReturnCode())){//调用成功
						titanOrderPaymentResponse.setResult(true);
						titanOrderPaymentResponse.setReturnCode(ReturnCode.CODE_SUCCESS.getCode());
						titanOrderPaymentResponse.setReturnMessage(paymentUrlResponse.getReturnCode());
						titanOrderPaymentResponse.setUrl(paymentUrlResponse.getUrl());
						return titanOrderPaymentResponse;
					}
				}
			}
		}catch(Exception e){
			
		}
		titanOrderPaymentResponse.putErrorResult(ReturnCode.CODE_SYS_ERROR.getCode(), "系统错误");
		return titanOrderPaymentResponse;
	}
	
	private PaymentUrlRequest convertToPaymentUrlRequest(TitanOrderPaymentRequest titanOrderPaymentRequest){
		PaymentUrlRequest paymentUrlRequest  = new PaymentUrlRequest();
		paymentUrlRequest.setPayOrderNo(titanOrderPaymentRequest.getPayOrderNo());
		paymentUrlRequest.setMerchantcode(titanOrderPaymentRequest.getMerchantCode());
		if(titanOrderPaymentRequest.getPaySource()!=null){
			paymentUrlRequest.setPaySource(titanOrderPaymentRequest.getPaySource().getDeskCode());
		}
		paymentUrlRequest.setFcUserid(titanOrderPaymentRequest.getFcUserid());
		paymentUrlRequest.setOperater(titanOrderPaymentRequest.getOperater());
		paymentUrlRequest.setEscrowedDate(titanOrderPaymentRequest.getEscrowedDate());
		paymentUrlRequest.setRecieveMerchantCode(titanOrderPaymentRequest.getRecieveMerchantCode());
		paymentUrlRequest.setIsEscrowed(titanOrderPaymentRequest.getIsEscrowed().key);
		return paymentUrlRequest;
	}
	
	
	

}
