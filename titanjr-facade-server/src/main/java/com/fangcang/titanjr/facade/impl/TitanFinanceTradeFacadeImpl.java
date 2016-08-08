package com.fangcang.titanjr.facade.impl;

import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.dto.BaseResponseDTO.ReturnCode;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.request.PaymentUrlRequest;
import com.fangcang.titanjr.dto.response.PaymentUrlResponse;
import com.fangcang.titanjr.facade.TitanFinancialTradeFacade;
import com.fangcang.titanjr.request.TitanOrderPaymentRequest;
import com.fangcang.titanjr.response.TitanOrderPaymentResponse;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.util.StringUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("titanFinanceTradeFacade")
public class TitanFinanceTradeFacadeImpl implements TitanFinancialTradeFacade {

    private static final Log log = LogFactory.getLog(TitanFinanceTradeFacadeImpl.class);

    @Resource
    private TitanFinancialTradeService titanFinancialTradeService;

    @Resource
    private TitanFinancialOrganService titanFinancialOrganService;

    @Override
    public TitanOrderPaymentResponse getOrderPaymentUrl(TitanOrderPaymentRequest titanOrderPaymentRequest) {
        log.info("进入获取收银台地址：");
        TitanOrderPaymentResponse titanOrderPaymentResponse = new TitanOrderPaymentResponse();
        if (!GenericValidate.validate(titanOrderPaymentRequest)) {
            titanOrderPaymentResponse.putErrorResult(ReturnCode.CODE_PARAM_ERROR.getCode(), "请求参数校验错误");
            return titanOrderPaymentResponse;
        }
        try {
            //如果接收商家的recieveMerchantCode不为空，则去查询
            if (StringUtil.isValidString(titanOrderPaymentRequest.getRecieveMerchantCode())) {//查询是否有对应的商家,未对应商家直接返回
                OrgBindInfo orgBindInfo = new OrgBindInfo();
                orgBindInfo.setMerchantCode(titanOrderPaymentRequest.getRecieveMerchantCode());
                orgBindInfo = titanFinancialOrganService.queryOrgBindInfoByUserid(orgBindInfo);
                if (orgBindInfo == null) {
                    titanOrderPaymentResponse.putErrorResult("PAYEE_ERROR", "指定的收款方未开通泰坦金融账户");
                    return titanOrderPaymentResponse;
                }
            }
            PaymentUrlRequest paymentUrlRequest = convertToPaymentUrlRequest(titanOrderPaymentRequest);
            PaymentUrlResponse paymentUrlResponse = titanFinancialTradeService.getPaymentUrl(paymentUrlRequest);
            if (paymentUrlResponse != null && ReturnCode.CODE_SUCCESS.getCode()
                    .equals(paymentUrlResponse.getReturnCode())) {//调用成功
                titanOrderPaymentResponse.setResult(true);
                titanOrderPaymentResponse.setReturnCode(ReturnCode.CODE_SUCCESS.getCode());
                titanOrderPaymentResponse.setReturnMessage(paymentUrlResponse.getReturnCode());
                titanOrderPaymentResponse.setUrl(paymentUrlResponse.getUrl());
                return titanOrderPaymentResponse;
            } else {
                titanOrderPaymentResponse.putErrorResult("QUERY_URL_ERROR", "查询指定的收银台地址失败");
            }
        } catch (Exception e) {
            titanOrderPaymentResponse.putErrorResult(ReturnCode.CODE_SYS_ERROR.getCode(), "系统错误");
        }
        return titanOrderPaymentResponse;
    }

    private PaymentUrlRequest convertToPaymentUrlRequest(TitanOrderPaymentRequest titanOrderPaymentRequest) {
        PaymentUrlRequest paymentUrlRequest = new PaymentUrlRequest();
        paymentUrlRequest.setPayOrderNo(titanOrderPaymentRequest.getPayOrderNo());
        paymentUrlRequest.setMerchantcode(titanOrderPaymentRequest.getMerchantCode());
        if (titanOrderPaymentRequest.getPaySource() != null) {
            paymentUrlRequest.setPaySource(titanOrderPaymentRequest.getPaySource().getDeskCode());
        }
        paymentUrlRequest.setFcUserid(titanOrderPaymentRequest.getFcUserid());
        paymentUrlRequest.setOperater(titanOrderPaymentRequest.getOperater());
        paymentUrlRequest.setEscrowedDate(titanOrderPaymentRequest.getEscrowedDate());
        paymentUrlRequest.setRecieveMerchantCode(titanOrderPaymentRequest.getRecieveMerchantCode());
        paymentUrlRequest.setIsEscrowed(titanOrderPaymentRequest.getIsEscrowed().key);
        paymentUrlRequest.setBusinessOrderCode(titanOrderPaymentRequest.getBusinessOrderCode());
        paymentUrlRequest.setNotifyUrl(titanOrderPaymentRequest.getNotifyUrl());
        return paymentUrlRequest;
    }


}
