package com.fangcang.titanjr.facade.impl;

import javax.annotation.Resource;

import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.TradeDetailRequest;
import com.fangcang.titanjr.dto.response.TradeDetailResponse;
import org.apache.commons.collections.CollectionUtils;
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

                FinancialOrderRequest financialOrderRequest = new FinancialOrderRequest();
                financialOrderRequest.setMerchantcode(showPaymentRequest.getMerchantcode());
                financialOrderRequest.setOrderNo(showPaymentRequest.getPayOrderNo());
                FinancialOrderResponse financialOrderResponse = titanFinancialTradeService.queryFinanceOrderDetail(financialOrderRequest);
                TradeDetailRequest tradeDetailRequest = new TradeDetailRequest();
                tradeDetailRequest.setUserid(orgBindInfo.getUserid());
                tradeDetailRequest.setPayOrderNo(financialOrderResponse.getFinanceCode());
                TradeDetailResponse detailResponse = titanFinancialTradeService.getOrderTradeDetail(tradeDetailRequest);
                TransOrderDTO transOrderDTO = null;
                if (null != detailResponse.getTransOrders() && CollectionUtils.isNotEmpty(detailResponse.getTransOrders().getItemList())){
                    transOrderDTO = detailResponse.getTransOrders().getItemList().get(0);
                    if (detailResponse.getTransOrders().getItemList().size() > 1) {
                        for (int i = 1; i < detailResponse.getTransOrders().getItemList().size(); i++) {
                            if (DateUtil.compareDate(transOrderDTO.getCreatetime(),
                                    detailResponse.getTransOrders().getItemList().get(i).getCreatetime()) < 0) {
                                transOrderDTO = detailResponse.getTransOrders().getItemList().get(i);
                            }
                        }
                    }
                }
                if (transOrderDTO == null ||
                        OrderStatusEnum.ORDER_FAIL.getStatus().equals(transOrderDTO.getStatusid()) ||
                        OrderStatusEnum.ORDER_NO_EFFECT.getStatus().equals(transOrderDTO.getStatusid()) ||
                        (OrderStatusEnum.ORDER_IN_PROCESS.getStatus().equals(transOrderDTO.getStatusid()) &&
                            transOrderDTO.getTitanOrderPayDTO() == null && transOrderDTO.getTitanTransferDTO() == null)){
                    //展示在线支付按钮
                    showPaymentResponse.setShowStatus(1);
                } else {
                    //展示支付中按钮
                    showPaymentResponse.setShowStatus(2);
                }
                //验证其支付货币是CNY,只有返回true的才判定是否显示按钮
                //如果去金融查询工单出错，也认为不能进行在线支付
                if (detailResponse.isResult() && financialOrderResponse.getCurrency() != null
                        && "RMB".equals(financialOrderResponse.getCurrency().name())) {
                    showPaymentResponse.setResult(true);
                    return showPaymentResponse;
                } else {
                    log.error("当前不满足支付有要求，币种：" + financialOrderResponse.getCurrency().name() +
                            "，融数交易单查询结果：" + detailResponse.isResult());
                    showPaymentResponse.setReturnMessage("当前不满足支付有要求");
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
