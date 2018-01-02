package com.fangcang.titanjr.facade.impl;

import com.fangcang.titanjr.common.bean.ValidateResponse;
import com.fangcang.titanjr.common.util.CommonConstant;
import com.fangcang.titanjr.common.util.GenericValidate;
import com.fangcang.titanjr.dto.BaseResponseDTO;
import com.fangcang.titanjr.dto.BaseResponseDTO.ReturnCode;
import com.fangcang.titanjr.dto.bean.AccountBalance;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.request.AccountBalanceRequest;
import com.fangcang.titanjr.dto.request.PaymentUrlRequest;
import com.fangcang.titanjr.dto.request.UpdateFreezeOrderRequest;
import com.fangcang.titanjr.dto.response.AccountBalanceResponse;
import com.fangcang.titanjr.dto.response.PaymentUrlResponse;
import com.fangcang.titanjr.facade.TitanFinancialTradeFacade;
import com.fangcang.titanjr.request.BalanceQueryRequest;
import com.fangcang.titanjr.request.TitanOrderPaymentRequest;
import com.fangcang.titanjr.request.UpdateFreezeRequest;
import com.fangcang.titanjr.response.BalanceQueryResponse;
import com.fangcang.titanjr.response.BaseResponse;
import com.fangcang.titanjr.response.TitanOrderPaymentResponse;
import com.fangcang.titanjr.service.TitanFinancialAccountService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUtilService;
import com.fangcang.util.StringUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("titanFinanceTradeFacade")
public class TitanFinanceTradeFacadeImpl implements TitanFinancialTradeFacade {

    private static final Log log = LogFactory.getLog(TitanFinanceTradeFacadeImpl.class);

    @Resource
    private TitanFinancialTradeService titanFinancialTradeService;

    @Resource
    private TitanFinancialOrganService titanFinancialOrganService;
    
    @Resource
    private TitanFinancialUtilService titanFinancialUtilService;

    @Resource
    private TitanFinancialAccountService titanFinancialAccountService;

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
            PaymentUrlResponse paymentUrlResponse = titanFinancialUtilService.getPaymentUrl(paymentUrlRequest);
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

    @Override
    public BalanceQueryResponse queryAccountBalance(BalanceQueryRequest balanceQueryRequest) {
        BalanceQueryResponse response = new BalanceQueryResponse();
        if (!StringUtil.isValidString(balanceQueryRequest.getTitanOrgCode())){
            response.putParamError();
            log.error("查询账户余额时金融机构编码不能为空");
            return response;
        }
        AccountBalanceRequest balanceRequest = new AccountBalanceRequest();
        balanceRequest.setRootinstcd(CommonConstant.RS_FANGCANG_CONST_ID);
        balanceRequest.setUserid(balanceQueryRequest.getTitanOrgCode());
        AccountBalanceResponse accountBalanceResponse = titanFinancialAccountService.queryAccountBalance(balanceRequest);
        if (!accountBalanceResponse.isResult() || CollectionUtils.isEmpty(accountBalanceResponse.getAccountBalance())){
            response.putErrorResult(accountBalanceResponse.getReturnMessage());
            log.error("查询账户余额失败");
            return response;
        } else {
            AccountBalance resultBalance = null;
            for (AccountBalance accountBalance : accountBalanceResponse.getAccountBalance()){
                if (CommonConstant.RS_FANGCANG_PRODUCT_ID.equals(accountBalance.getProductid())){
                    resultBalance = accountBalance;
                }
            }
            if (null == resultBalance){
                response.putErrorResult("NO_VALID_ACCOUNT","无合法的金融主账户");
                log.error("无合法的金融主账户");
                return response;
            }

            response.setResult(true);
            response.setAccountId(resultBalance.getFinaccountid());
            response.setBalanceFrozon(resultBalance.getBalancefrozon());
            response.setAmount(resultBalance.getAmount());
            response.setBalanceUsable(resultBalance.getBalanceusable());
        }
        return response;
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
    
    
    @Override
    public BaseResponse updateFreezeOrder(UpdateFreezeRequest updateFreezeRequest) {
    	
    	BaseResponse baseResponse = new BaseResponse();
    	UpdateFreezeOrderRequest updateFreezeOrderRequest = new UpdateFreezeOrderRequest();
    	
    	try {
    		
    		ValidateResponse res = GenericValidate.validateNew(updateFreezeRequest);
			if(!res.isSuccess()){
				log.error("参数错误 =====>> " + res.getReturnMessage());
	    		baseResponse.putErrorResult(res.getReturnMessage());
	    		return baseResponse;
			}
			BeanUtils.copyProperties(updateFreezeRequest, updateFreezeOrderRequest);
			
		} catch (Exception e) {
			
			log.error("异常", e);
    		baseResponse.putErrorResult("参数异常");
    		return baseResponse;
		}
    	
    	BaseResponseDTO baseResponseDTO = titanFinancialAccountService.updateFreezeOrder(updateFreezeOrderRequest);
    	
    	baseResponse.setResult(baseResponseDTO.isResult());
    	baseResponse.setReturnCode(baseResponseDTO.getReturnCode());
    	baseResponse.setReturnMessage(baseResponseDTO.getReturnMessage());
    	return baseResponse;
    }


}
