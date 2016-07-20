package com.fangcang.titanjr.facade.impl;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.fangcang.titanjr.dto.bean.AmtTypeEnum;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.request.FinancialOrderRequest;
import com.fangcang.titanjr.dto.request.PermissionRequest;
import com.fangcang.titanjr.dto.response.CheckPermissionResponse;
import com.fangcang.titanjr.dto.response.FinancialOrderResponse;
import com.fangcang.titanjr.facade.TitanFinancialPermissionFacade;
import com.fangcang.titanjr.request.AccountInfoRequest;
import com.fangcang.titanjr.request.CheckPermissionRequest;
import com.fangcang.titanjr.request.ShowPaymentRequest;
import com.fangcang.titanjr.response.CheckAccountResponse;
import com.fangcang.titanjr.response.PermissionResponse;
import com.fangcang.titanjr.response.ShowPaymentResponse;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.StringUtil;

@Service("titanFinancialPermissionFacade")
public class TitanFinancialPermissionFacadeImpl implements TitanFinancialPermissionFacade {

    private static final Log log = LogFactory.getLog(TitanFinancialPermissionFacadeImpl.class);

    @Resource
    private TitanFinancialUserService titanFinancialUserService;

    @Resource
    private TitanFinancialOrganService titanFinancialOrganService;

    @Resource
    private TitanFinancialTradeService titanFinancialTradeService;

    @Override
    public PermissionResponse isPermissionToPayment(CheckPermissionRequest checkPermissionRequest) {
        PermissionResponse permissionResponse = new PermissionResponse();
        permissionResponse.setResult(false);
        permissionResponse.setPermission(false);
        try {
            PermissionRequest permissionRequest = new PermissionRequest();
            permissionRequest.setFcuserid(checkPermissionRequest.getFcuserid());
            permissionRequest.setPermission(checkPermissionRequest.getPermission());
            CheckPermissionResponse checkResponse = titanFinancialUserService.checkUserPermission(permissionRequest);
            if (checkResponse.isPermission()) {
                permissionResponse.setPermission(true);
                permissionResponse.setResult(true);
            } else {
                permissionResponse.putErrorResult(checkResponse.getReturnCode(), checkResponse.getReturnMessage());
            }
        } catch (Exception e) {
            permissionResponse.setReturnMessage("查询用户授权操作失败");
            log.error("查询用户授权操作失败", e);
        }
        return permissionResponse;
    }

    @Override
    public ShowPaymentResponse isShowPaymentButton(ShowPaymentRequest showPaymentRequest) {
        ShowPaymentResponse showPaymentResponse = new ShowPaymentResponse();
        showPaymentResponse.setResult(false);
        if (showPaymentRequest != null && StringUtil.isValidString(showPaymentRequest.getMerchantcode())) {//验证是泰坦金融用户
            OrgBindInfo orgBindInfo = new OrgBindInfo();
            orgBindInfo.setMerchantCode(showPaymentRequest.getMerchantcode());
            orgBindInfo = titanFinancialOrganService.queryOrgBindInfoByUserid(orgBindInfo);
            if (orgBindInfo != null) {
                //验证其支付货币是CNY
                FinancialOrderRequest financialOrderRequest = new FinancialOrderRequest();
                financialOrderRequest.setMerchantcode(showPaymentRequest.getMerchantcode());
                financialOrderRequest.setOrderNo(showPaymentRequest.getPayOrderNo());
                FinancialOrderResponse financialOrderResponse = titanFinancialTradeService.queryFinanceOrderDetail(financialOrderRequest);
                if (financialOrderResponse.getCurrency() != null && "RMB".equals(financialOrderResponse.getCurrency().name())) {
                    showPaymentResponse.setResult(true);
                    return showPaymentResponse;
                } else {
                    log.error("当前仅支持人民币支付");
                    showPaymentResponse.setReturnMessage("当前仅支持人民币支付");
                }
            } else {
                log.error("当前商家未开通或绑定金服机构");
                showPaymentResponse.setReturnMessage("当前商家未开通或绑定金服机构");
            }
        } else {
            log.error("请求参数不合法");
            showPaymentResponse.setReturnMessage("请求参数不合法");
        }
        return showPaymentResponse;
    }

	@Override
	public CheckAccountResponse isFinanceAccount(
			AccountInfoRequest accountInfoRequest) {
		 CheckAccountResponse checkAccountResponse = new CheckAccountResponse();
		 checkAccountResponse.setResult(false);
		 if(accountInfoRequest !=null && !StringUtil.isValidString(accountInfoRequest.getMerchantCode())){
			 log.error("商家编码不能为空");
        	 checkAccountResponse.setReturnMessage("商家编码不能为空");
		 }
		 
		 OrgBindInfo orgBindInfo = new OrgBindInfo();
         orgBindInfo.setMerchantCode(accountInfoRequest.getMerchantCode());
         orgBindInfo = titanFinancialOrganService.queryOrgBindInfoByUserid(orgBindInfo);
         if (orgBindInfo == null) {
        	log.error("当前商家未开通或绑定金服机构");
         	checkAccountResponse.setReturnMessage("当前商家未开通或绑定金服机构");
         	return checkAccountResponse;
         } 
	            
         checkAccountResponse.setResult(true);
		 return checkAccountResponse;
	}


}
