package com.fangcang.titanjr.facade.impl;

import java.util.List;

import com.fangcang.titanjr.common.enums.OrderStatusEnum;
import com.fangcang.titanjr.common.util.DateUtil;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.dto.bean.OrgBindInfo;
import com.fangcang.titanjr.dto.bean.OrgBindInfoDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.CashierDeskQueryRequest;
import com.fangcang.titanjr.dto.request.PermissionRequest;
import com.fangcang.titanjr.dto.request.TradeDetailRequest;
import com.fangcang.titanjr.dto.response.CashierDeskResponse;
import com.fangcang.titanjr.dto.response.CheckPermissionResponse;
import com.fangcang.titanjr.dto.response.TradeDetailResponse;
import com.fangcang.titanjr.facade.TitanFinancialPermissionFacade;
import com.fangcang.titanjr.request.AccountInfoRequest;
import com.fangcang.titanjr.request.CheckPermissionRequest;
import com.fangcang.titanjr.request.ShowPaymentRequest;
import com.fangcang.titanjr.response.CheckAccountResponse;
import com.fangcang.titanjr.response.PermissionResponse;
import com.fangcang.titanjr.response.ShowPaymentResponse;
import com.fangcang.titanjr.service.TitanCashierDeskService;
import com.fangcang.titanjr.service.TitanFinancialOrganService;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.fangcang.titanjr.service.TitanFinancialUserService;
import com.fangcang.util.StringUtil;

import net.sf.json.JSONSerializer;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("titanFinancialPermissionFacade")
public class TitanFinancialPermissionFacadeImpl implements TitanFinancialPermissionFacade {

    private static final Log log = LogFactory.getLog(TitanFinancialPermissionFacadeImpl.class);

    @Resource
    private TitanFinancialUserService titanFinancialUserService;

    @Resource
    private TitanFinancialOrganService titanFinancialOrganService;

    @Resource
    private TitanFinancialTradeService titanFinancialTradeService;

    @Resource
    private TitanCashierDeskService titanCashierDeskService;

    @Override
    public PermissionResponse isPermissionToPayment(CheckPermissionRequest checkPermissionRequest) {
        log.info("验证该用户是否有支付权限:"+JSONSerializer.toJSON(checkPermissionRequest));
    	PermissionResponse permissionResponse = new PermissionResponse();
        permissionResponse.setResult(false);
        permissionResponse.setPermission(false);
        try {
            PermissionRequest permissionRequest = new PermissionRequest();
            permissionRequest.setFcuserid(checkPermissionRequest.getFcuserid());
            permissionRequest.setMerchantcode(checkPermissionRequest.getMerchantcode());
            permissionRequest.setPermission(checkPermissionRequest.getPermission());
            CheckPermissionResponse checkResponse = titanFinancialUserService.checkUserPermission(permissionRequest);
            log.info("验证该用户是否有支付权限结果:"+JSONSerializer.toJSON(checkResponse));
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
    	log.info("验证用户是否开通金融账户的请求参数："+JSONSerializer.toJSON(showPaymentRequest));
        ShowPaymentResponse showPaymentResponse = new ShowPaymentResponse();
        showPaymentResponse.setResult(false);
        if (showPaymentRequest != null && StringUtil.isValidString(showPaymentRequest.getMerchantcode())) {//验证是泰坦金融用户
            OrgBindInfo orgBindInfo = new OrgBindInfo();
            orgBindInfo.setMerchantCode(showPaymentRequest.getMerchantcode());
            orgBindInfo = titanFinancialOrganService.queryOrgBindInfoByUserid(orgBindInfo);
            if (orgBindInfo != null) {
                TradeDetailRequest tradeDetailRequest = new TradeDetailRequest();
                tradeDetailRequest.setUserid(orgBindInfo.getUserid());
                tradeDetailRequest.setPayOrderNo(showPaymentRequest.getPayOrderNo());
                tradeDetailRequest.setNeedLoan(true);
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
                            (transOrderDTO.getTitanOrderPayDTO() == null || transOrderDTO.getTitanOrderPayDTO().getReqstatus() != 2) &&
                            (transOrderDTO.getTitanTransferDTO() == null || transOrderDTO.getTitanTransferDTO().getStatus() != 2))){
                    //展示在线支付按钮
                    showPaymentResponse.setShowStatus(1);
                }  else {
                    if (OrderStatusEnum.PROGRESS_ING.getStatus().equals(transOrderDTO.getStatusid())){
                        //展示贷款申请中
                        showPaymentResponse.setShowStatus(3);
                    } else {//展示支付中按钮
                        showPaymentResponse.setShowStatus(2);
                    }
                }
                showPaymentResponse.setResult(true);
            } else {
                log.error("当前商家未开通或绑定金服机构，请求参数："+Tools.gsonToString(showPaymentRequest));
                showPaymentResponse.setReturnMessage("当前商家未开通或绑定金服机构");
            }
        } else {
            log.error("请求参数不合法，请求参数："+Tools.gsonToString(showPaymentRequest));
            showPaymentResponse.setReturnMessage("请求参数不合法");
        }
        return showPaymentResponse;
    }

    @Override
    public CheckAccountResponse isFinanceAccount(AccountInfoRequest accountInfoRequest) {
        log.info("验证账户的请求参数" + JSONSerializer.toJSON(accountInfoRequest));
        CheckAccountResponse checkAccountResponse = new CheckAccountResponse();
        checkAccountResponse.setResult(false);
        if (accountInfoRequest != null && !StringUtil.isValidString(accountInfoRequest.getMerchantCode())) {
            log.error("商家编码不能为空");
            checkAccountResponse.setReturnMessage("商家编码不能为空");
            return checkAccountResponse;
        }

        OrgBindInfoDTO orgBindDTO = new OrgBindInfoDTO();
        if (accountInfoRequest.getMerchantCode().startsWith("TJM")) {
            orgBindDTO.setUserid(accountInfoRequest.getMerchantCode());
        } else {
            orgBindDTO.setMerchantCode(accountInfoRequest.getMerchantCode());
        }
        orgBindDTO.setResultKey("PASS");
        orgBindDTO.setBindStatus(1);
        List<OrgBindInfoDTO> orgBindDTOList = titanFinancialOrganService.queryOrgBindInfoDTO(orgBindDTO);
        if (null == orgBindDTOList || orgBindDTOList.size() != 1 || orgBindDTOList.get(0) == null) {
            log.error("验证账户---> 错误信息：当前商家未开通或绑定金服机构，请求参数[accountInfoRequest]："+Tools.gsonToString(accountInfoRequest));
            checkAccountResponse.setReturnMessage("当前商家未开通或绑定金服机构");
            return checkAccountResponse;
        }
        //如果收银台支付来源不为空，则需要验证收银台是否打开状态
        if (null != accountInfoRequest.getPaySourceEnum()) {
            CashierDeskQueryRequest cashierDeskQueryRequest = new CashierDeskQueryRequest();
            cashierDeskQueryRequest.setUsedFor(Integer.valueOf(accountInfoRequest.getPaySourceEnum().getDeskCode()));
            cashierDeskQueryRequest.setUserId(orgBindDTOList.get(0).getUserid());
            CashierDeskResponse cashierDeskResponse = titanCashierDeskService.queryCashierDesk(cashierDeskQueryRequest);
            if (!cashierDeskResponse.isResult() || //返回失败
                    CollectionUtils.isEmpty(cashierDeskResponse.getCashierDeskDTOList()) || //返回结果为空
                    cashierDeskResponse.getCashierDeskDTOList().get(0).getIsOpen() == 0 ){ //收银台关闭状态
                log.error("验证收银台---> 错误信息：当前收银台不存在或处于关闭状态，请求参数[accountInfoRequest]："+Tools.gsonToString(accountInfoRequest));
                checkAccountResponse.setReturnMessage("当前收银台不存在或处于关闭状态");
                return checkAccountResponse;
            }
        }
        checkAccountResponse.setResult(true);
        return checkAccountResponse;
    }


}
