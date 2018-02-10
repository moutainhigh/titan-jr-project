package com.titanjr.fop.controller;

import com.alibaba.fastjson.JSONObject;
import com.fangcang.util.StringUtil;
import com.titanjr.fop.dto.Transorderinfo;
import com.titanjr.fop.request.WheatfieldOrderOperRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceReturngoodsRequest;
import com.titanjr.fop.request.WheatfieldOrderTransferRequest;
import com.titanjr.fop.request.WheatfieldOrdernQueryRequest;
import com.titanjr.fop.response.WheatfieldOrderOperResponse;
import com.titanjr.fop.response.WheatfieldOrderServiceReturngoodsResponse;
import com.titanjr.fop.response.WheatfieldOrderTransferResponse;
import com.titanjr.fop.response.WheatfieldOrdernQueryResponse;
import com.titanjr.fop.service.OrderOperService;
import com.titanjr.fop.util.BeanUtils;
import com.titanjr.fop.util.ResponseUtils;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhaoshan on 2018/1/3.
 */
@Controller
@RequestMapping(value = "/order")
public class OrderOperController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(OrderOperController.class);

    @Resource
    private OrderOperService orderOperService;

    @RequestMapping(value = "/orderOper", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String orderOper(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        logger.debug("请求参数为:{}", request.getParameterMap());
        WheatfieldOrderOperResponse operResponse = new WheatfieldOrderOperResponse();
        String validResult = ResponseUtils.validRequestSign(request, operResponse);
        if (null != validResult) {
            return validResult;
        }

        WheatfieldOrderOperRequest orderOperRequest = BeanUtils.switch2RequestDTO(WheatfieldOrderOperRequest.class, request);
        if (null == orderOperRequest) {
            return ResponseUtils.getConvertErrorResp(operResponse);
        }

        String orderNo = orderOperService.operateOrder(orderOperRequest);
        if (!StringUtil.isValidString(orderNo)) {
            return ResponseUtils.getSysErrorResp(operResponse);
        }
        logger.info("操作成功，当前订单号为：{}", orderNo);
        operResponse.setIs_success("true");
        operResponse.setOrderid(orderNo);
        return toJson(operResponse);
    }

    @RequestMapping(value = "/operateRefundOrder", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String operateRefundOrder(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        logger.debug("请求参数为:{}", request.getParameterMap());
        WheatfieldOrderServiceReturngoodsResponse returngoodsResponse = new WheatfieldOrderServiceReturngoodsResponse();
        String validResult = ResponseUtils.validRequestSign(request, returngoodsResponse);
        if (null != validResult) {
            return validResult;
        }

        WheatfieldOrderServiceReturngoodsRequest returngoodsRequest = BeanUtils.switch2RequestDTO(WheatfieldOrderServiceReturngoodsRequest.class, request);
        if (null == returngoodsRequest) {
            return ResponseUtils.getConvertErrorResp(returngoodsResponse);
        }

        String batchNo = orderOperService.operateRefundOrder(returngoodsRequest);
        if (!StringUtil.isValidString(batchNo)) {
            return ResponseUtils.getSysErrorResp(returngoodsResponse);
        }
        logger.info("操作成功，退款单号为：{}", batchNo);
        returngoodsResponse.setIs_success("true");
        returngoodsResponse.setBatchno(batchNo);
        return toJson(returngoodsResponse);
    }


    @RequestMapping(value = "/orderNQuery", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String orderNQuery(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        logger.debug("请求参数为:{}", request.getParameterMap());
        WheatfieldOrdernQueryResponse ordernQueryResponse = new WheatfieldOrdernQueryResponse();
        String validResult = ResponseUtils.validRequestSign(request, ordernQueryResponse);
        if (null != validResult) {
            return validResult;
        }

        WheatfieldOrdernQueryRequest ordernQueryRequest = BeanUtils.switch2RequestDTO(WheatfieldOrdernQueryRequest.class, request);
        if (null == ordernQueryRequest) {
            return ResponseUtils.getConvertErrorResp(ordernQueryResponse);
        }

        List<Transorderinfo> orderinfoList = orderOperService.queryTradeOrderNew(ordernQueryRequest);
        if (CollectionUtils.isEmpty(orderinfoList)) {
            return ResponseUtils.getSysErrorResp(ordernQueryResponse);
        }
        logger.info("操作成功，获取交易单列表：{}", orderinfoList);
        ordernQueryResponse.setTransorderinfos(orderinfoList);
        return toJson(ordernQueryResponse);
    }

    @RequestMapping(value = "/orderTransfer", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String orderTransfer(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        logger.debug("请求参数为:{}", request.getParameterMap());
        WheatfieldOrderTransferResponse orderTransferResponse = new WheatfieldOrderTransferResponse();
        String validResult = ResponseUtils.validRequestSign(request, orderTransferResponse);
        if (null != validResult) {
            return validResult;
        }

        WheatfieldOrderTransferRequest orderTransferRequest = BeanUtils.switch2RequestDTO(WheatfieldOrderTransferRequest.class, request);
        if (null == orderTransferRequest) {
            return ResponseUtils.getConvertErrorResp(orderTransferResponse);
        }

        orderTransferResponse = orderOperService.accountBalanceTransfer(orderTransferRequest);
        logger.info("操作成功，转账成功，返回结果为：{}", JSONObject.toJSON(orderTransferResponse));
        return toJson(orderTransferResponse);
    }
}
