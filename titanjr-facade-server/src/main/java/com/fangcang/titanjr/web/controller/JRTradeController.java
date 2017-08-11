package com.fangcang.titanjr.web.controller;

import com.fangcang.titanjr.common.enums.TradeTypeEnum;
import com.fangcang.titanjr.dto.bean.*;
import com.fangcang.titanjr.dto.request.TradeDetailRequest;
import com.fangcang.titanjr.dto.response.TradeDetailResponse;
import com.fangcang.titanjr.rest.dto.*;
import com.fangcang.titanjr.rest.request.JRTransQueryRequest;
import com.fangcang.titanjr.rest.response.JRTransDetailResponse;
import com.fangcang.titanjr.rest.response.JRTransOrderListResponse;
import com.fangcang.titanjr.service.TitanFinancialTradeService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoshan on 2017/8/4.
 */
@RestController
@Api(value = "TradeApi")
public class JRTradeController {

    private static final Log log = LogFactory.getLog(JRTradeController.class);

    @Autowired
    private TitanFinancialTradeService titanFinancialTradeService;


    @RequestMapping(value = "/trade/queryTradeOrderList", method = RequestMethod.POST)
    @ApiOperation(value = "交易列表查询", produces = "application/json", httpMethod = "POST",
            response = JRTransOrderListResponse.class, notes = "交易记录列表查询")
    public JRTransOrderListResponse queryTradeOrderList(@ApiParam(required = true, name = "jrTransQueryRequest", value = "交易列表查询请求")
                                                   @RequestBody JRTransQueryRequest jrTransQueryRequest, HttpServletRequest request) {

        JRTransOrderListResponse response = new JRTransOrderListResponse();
        TradeDetailRequest detailRequest = new TradeDetailRequest();
        buildTradeDetailRequest(detailRequest,jrTransQueryRequest);
        TradeDetailResponse tradeDetailResponse = titanFinancialTradeService.getTradeDetail(detailRequest);

        List<JRTransOrderDTO> jrTransOrderDTOList = new ArrayList<JRTransOrderDTO>();
        response.setJrTransOrderDTOList(jrTransOrderDTOList);
        if (CollectionUtils.isEmpty(tradeDetailResponse.getTransOrders().getItemList())){
            log.info("无查询结果列表");
            response.putSuccess();
            return response;
        }
        for (TransOrderDTO transOrderDTO : tradeDetailResponse.getTransOrders().getItemList()){
            jrTransOrderDTOList.add(buildJRTransOrderDTO(transOrderDTO));
        }
        response.putSuccess();
        return response;
    }


    @RequestMapping(value = "/trade/queryTradeDetail", method = RequestMethod.POST)
    @ApiOperation(value = "交易详情查询", produces = "application/json", httpMethod = "POST",
            response = JRTransDetailResponse.class, notes = "交易详情查询接口")
    public JRTransDetailResponse queryTradeDetail(@ApiParam(required = true, name = "jrTransQueryRequest", value = "交易详情查询请求")
                                           @RequestBody JRTransQueryRequest jrTransQueryRequest, HttpServletRequest request) {
        JRTransDetailResponse response = new JRTransDetailResponse();
        TradeDetailRequest detailRequest = new TradeDetailRequest();
        buildTradeDetailRequest(detailRequest,jrTransQueryRequest);
        TradeDetailResponse tradeDetailResponse = titanFinancialTradeService.getOrderTradeDetail(detailRequest);
        List<JRTransDetailDTO> jrTransDetailDTOList = new ArrayList<JRTransDetailDTO>();
        response.setJrTransDetailDTOList(jrTransDetailDTOList);

        if (CollectionUtils.isEmpty(tradeDetailResponse.getTransOrders().getItemList())){
            log.info("无查询结果列表");
            response.putSuccess();
            return response;
        }
        for (TransOrderDTO transOrderDTO : tradeDetailResponse.getTransOrders().getItemList()){
            JRTransDetailDTO jrTransDetailDTO = buildJRTransOrderDTO(transOrderDTO);
            jrTransDetailDTOList.add(jrTransDetailDTO);
            if (null != transOrderDTO.getTitanOrderPayDTO()) {
                JRTitanOrderPayDTO jrTitanOrderPayDTO = buildJRTitanOrderPayDTO(transOrderDTO.getTitanOrderPayDTO());
                jrTransDetailDTO.setJrTitanOrderPayDTO(jrTitanOrderPayDTO);
            }
            if (null != transOrderDTO.getTitanTransferDTO()){
                JRTitanTransferDTO jrTitanTransferDTO = buildJRTitanTransferDTO(transOrderDTO.getTitanTransferDTO());
                jrTransDetailDTO.setJrTitanTransferDTO(jrTitanTransferDTO);
            }

            if (null != transOrderDTO.getTitanWithDrawDTO()){
                JRTitanWithDrawDTO jrTitanWithDrawDTO = buildJRTitanWithDrawDTO(transOrderDTO.getTitanWithDrawDTO());
                jrTransDetailDTO.setJrTitanWithDrawDTO(jrTitanWithDrawDTO);
            }

            if (null != transOrderDTO.getRefundDTO()){
                JRRefundDTO jrRefundDTO = buildJRRefundDTO(transOrderDTO.getRefundDTO());
                jrTransDetailDTO.setJrRefundDTO(jrRefundDTO);
            }

        }

        return response;
    }

    private JRRefundDTO buildJRRefundDTO(RefundDTO refundDTO) {
        JRRefundDTO jrRefundDTO = new JRRefundDTO();
        jrRefundDTO.setUserOrderId(refundDTO.getUserOrderId());
        jrRefundDTO.setStatus(refundDTO.getStatus());
        jrRefundDTO.setBusiCode(refundDTO.getBusiCode());
        jrRefundDTO.setBusinessInfo(refundDTO.getBusinessInfo());
        jrRefundDTO.setCreateTime(refundDTO.getCreatetime());
        jrRefundDTO.setCreator(refundDTO.getCreator());
        jrRefundDTO.setFee(refundDTO.getFee());
        jrRefundDTO.setNotifyUrl(refundDTO.getNotifyUrl());
        jrRefundDTO.setOrderNo(refundDTO.getOrderNo());
        jrRefundDTO.setOrderTime(refundDTO.getOrderTime());
        jrRefundDTO.setPayOrderNo(refundDTO.getPayOrderNo());
        jrRefundDTO.setRefundAmount(refundDTO.getRefundAmount());
        jrRefundDTO.setRefundOrderNo(refundDTO.getRefundOrderno());
        jrRefundDTO.setStatus(refundDTO.getStatus());
        jrRefundDTO.setTransferAmount(refundDTO.getTransferAmount());
        jrRefundDTO.setUserOrderId(refundDTO.getUserOrderId());
        return jrRefundDTO;
    }

    private JRTitanWithDrawDTO buildJRTitanWithDrawDTO(TitanWithDrawDTO titanWithDrawDTO) {
        JRTitanWithDrawDTO jrTitanWithDrawDTO = new JRTitanWithDrawDTO();
        jrTitanWithDrawDTO.setAmount(titanWithDrawDTO.getAmount());
        jrTitanWithDrawDTO.setBankCode(titanWithDrawDTO.getBankcode());
        jrTitanWithDrawDTO.setBankName(titanWithDrawDTO.getBankname());
        jrTitanWithDrawDTO.setCreateTime(titanWithDrawDTO.getCreatetime());
        jrTitanWithDrawDTO.setCreator(titanWithDrawDTO.getCreator());
        jrTitanWithDrawDTO.setOrderDate(titanWithDrawDTO.getOrderdate());
        jrTitanWithDrawDTO.setProductId(titanWithDrawDTO.getProductid());
        jrTitanWithDrawDTO.setReceivedFee(titanWithDrawDTO.getReceivedfee());
        jrTitanWithDrawDTO.setStatus(titanWithDrawDTO.getStatus());
        jrTitanWithDrawDTO.setUserFee(titanWithDrawDTO.getUserfee());
        jrTitanWithDrawDTO.setUserId(titanWithDrawDTO.getUserid());
        jrTitanWithDrawDTO.setUserOrderId(titanWithDrawDTO.getUserorderid());
        return jrTitanWithDrawDTO;
    }

    private JRTitanTransferDTO buildJRTitanTransferDTO(TitanTransferDTO titanTransferDTO) {
        JRTitanTransferDTO jrTitanTransferDTO = new JRTitanTransferDTO();
        jrTitanTransferDTO.setAmount(titanTransferDTO.getAmount());
        jrTitanTransferDTO.setCreateTime(titanTransferDTO.getCreatetime());
        jrTitanTransferDTO.setCreator(titanTransferDTO.getCreator());
        jrTitanTransferDTO.setInterProductId(titanTransferDTO.getInterproductid());
        jrTitanTransferDTO.setPayOrderNo(titanTransferDTO.getPayOrderNo());
        jrTitanTransferDTO.setProductId(titanTransferDTO.getProductid());
        jrTitanTransferDTO.setRemark(titanTransferDTO.getRemark());
        jrTitanTransferDTO.setRequestNo(titanTransferDTO.getRequestno());
        jrTitanTransferDTO.setRequestTime(titanTransferDTO.getRequesttime());
        jrTitanTransferDTO.setStatus(titanTransferDTO.getStatus());
        jrTitanTransferDTO.setUserFee(titanTransferDTO.getUserfee());
        jrTitanTransferDTO.setUserId(titanTransferDTO.getUserid());
        jrTitanTransferDTO.setUserRelateId(titanTransferDTO.getUserrelateid());
        return jrTitanTransferDTO;
    }

    private JRTitanOrderPayDTO buildJRTitanOrderPayDTO(TitanOrderPayDTO titanOrderPayDTO) {
        JRTitanOrderPayDTO jrTitanOrderPayDTO = new JRTitanOrderPayDTO();
        jrTitanOrderPayDTO.setAmtType(titanOrderPayDTO.getAmtType());
        jrTitanOrderPayDTO.setBankInfo(titanOrderPayDTO.getBankInfo());
        jrTitanOrderPayDTO.setOrderAmount(titanOrderPayDTO.getOrderAmount());
        jrTitanOrderPayDTO.setOrderExpireTime(titanOrderPayDTO.getOrderExpireTime());
        jrTitanOrderPayDTO.setOrderNo(titanOrderPayDTO.getOrderNo());
        jrTitanOrderPayDTO.setOrderTime(titanOrderPayDTO.getOrderTime());
        jrTitanOrderPayDTO.setPayType(titanOrderPayDTO.getPayType());
        jrTitanOrderPayDTO.setProductDesc(titanOrderPayDTO.getProductDesc());
        jrTitanOrderPayDTO.setProductName(titanOrderPayDTO.getProductName());
        jrTitanOrderPayDTO.setReceivedFee(titanOrderPayDTO.getReceivedfee());
        return jrTitanOrderPayDTO;
    }

    private void  buildTradeDetailRequest(TradeDetailRequest detailRequest, JRTransQueryRequest jrTransQueryRequest){

        detailRequest.setUserid(jrTransQueryRequest.getUserId());
        detailRequest.setBusinessordercode(jrTransQueryRequest.getBusinessOrderCode());
        detailRequest.setPayOrderNo(jrTransQueryRequest.getPayOrderNo());
        detailRequest.setTradeTypeEnum(TradeTypeEnum.getTradeTypeEnumByKey(jrTransQueryRequest.getTradeTypeEnum().getKey()));
        detailRequest.setStartTime(jrTransQueryRequest.getStartTime());
        detailRequest.setEndTime(jrTransQueryRequest.getEndTime());
        detailRequest.setAdmissionName(jrTransQueryRequest.getTradeTarget());
        detailRequest.setOrderAmount(jrTransQueryRequest.getOrderAmount());
        detailRequest.setUserOrderId(jrTransQueryRequest.getUserOrderId());
        detailRequest.setOrderOperator(jrTransQueryRequest.getOrderOperator());
        detailRequest.setIsEscrowedPayment(jrTransQueryRequest.getIsEscrowedPayment());
    }

    private JRTransDetailDTO buildJRTransOrderDTO(TransOrderDTO transOrderDTO){
        JRTransDetailDTO jrTransOrderDTO = new JRTransDetailDTO();
        jrTransOrderDTO.setAmount(transOrderDTO.getAmount());
        jrTransOrderDTO.setAmtType(transOrderDTO.getAmttype());
        jrTransOrderDTO.setBankCode(transOrderDTO.getBankcode());
        jrTransOrderDTO.setBankInfo(transOrderDTO.getBankInfo());
        jrTransOrderDTO.setBankName(transOrderDTO.getBankname());
        jrTransOrderDTO.setBusinessInfo(transOrderDTO.getBusinessinfo());
        jrTransOrderDTO.setBusinessOrderCode(transOrderDTO.getBusinessordercode());
        jrTransOrderDTO.setCreateTime(transOrderDTO.getCreatetime());
        jrTransOrderDTO.setCreator(transOrderDTO.getCreator());
        jrTransOrderDTO.setEscrowedDate(transOrderDTO.getEscrowedDate());
        jrTransOrderDTO.setGoodsCnt(transOrderDTO.getGoodscnt());
        jrTransOrderDTO.setGoodsDetail(transOrderDTO.getGoodsdetail());
        jrTransOrderDTO.setIsEscrowedPayment(transOrderDTO.getIsEscrowedPayment());
        jrTransOrderDTO.setLoanOrderNo(transOrderDTO.getLoanOrderNo());
        jrTransOrderDTO.setMerchantCode(transOrderDTO.getMerchantcode());
        jrTransOrderDTO.setNotifyUrl(transOrderDTO.getNotifyUrl());
        jrTransOrderDTO.setOrderDate(transOrderDTO.getOrderdate());
        jrTransOrderDTO.setOrderId(transOrderDTO.getOrderid());
        jrTransOrderDTO.setOrderTime(transOrderDTO.getOrdertime());
        jrTransOrderDTO.setOrderTypeId(transOrderDTO.getOrdertypeid());
        jrTransOrderDTO.setPayOrderNo(transOrderDTO.getPayorderno());
        jrTransOrderDTO.setPayerType(transOrderDTO.getPayerType());
        jrTransOrderDTO.setPayeeMerchant(transOrderDTO.getPayeemerchant());
        jrTransOrderDTO.setPayerMerchant(transOrderDTO.getPayermerchant());
        jrTransOrderDTO.setReceivedFee(transOrderDTO.getReceivedfee());
        jrTransOrderDTO.setRemark(transOrderDTO.getRemark());
        jrTransOrderDTO.setStatusId(transOrderDTO.getStatusid());
        jrTransOrderDTO.setTradeAmount(transOrderDTO.getTradeamount());
        jrTransOrderDTO.setTradeType(transOrderDTO.getTradeType());
        jrTransOrderDTO.setTransOrderType(transOrderDTO.getTransordertype());
        jrTransOrderDTO.setTransTarget(transOrderDTO.getTransTarget());
        jrTransOrderDTO.setUnitPrice(transOrderDTO.getUnitprice());
        jrTransOrderDTO.setUserOrderId(transOrderDTO.getUserorderid());
        return jrTransOrderDTO;
    }

}
