package com.titanjr.fop.service.impl;

import com.fangcang.exception.ServiceException;
import com.fangcang.titanjr.dto.bean.RefundDTO;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.DateUtil;
import com.titanjr.fop.dao.TitanOrderDao;
import com.titanjr.fop.request.WheatfieldOrderOperRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceReturngoodsRequest;
import com.titanjr.fop.service.OrderOperService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2018/1/3.
 */
@Component
public class OrderOperServiceImpl implements OrderOperService {

    private final static Logger logger = LoggerFactory.getLogger(OrderOperServiceImpl.class);

    @Resource
    TitanOrderService titanOrderService;

    @Resource
    TitanOrderDao titanOrderDao;

    @Override
    public String operateOrder(WheatfieldOrderOperRequest orderOperRequest) throws ServiceException {
        TransOrderRequest transOrderRequest = new TransOrderRequest();
        transOrderRequest.setUserorderid(orderOperRequest.getUserorderid());
        transOrderRequest.setUserid(orderOperRequest.getUserid());
        List<TransOrderDTO> transOrderDTOs = titanOrderService.queryTransOrder(transOrderRequest);
        //新增
        if ("1".equals(orderOperRequest.getOpertype())) {
            if (CollectionUtils.isNotEmpty(transOrderDTOs)) {
                logger.error("新增时已存在交易单，userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            DecimalFormat formater = new DecimalFormat();
            formater.applyPattern("000000");
            String result = DateUtil.dateToString(new Date(), "yyyyMMddHHmmsssss") + formater.format((long) (Math.random() * 1000));
            return result;
        }
        //修改
        if ("2".equals(orderOperRequest.getOpertype())) {
            if (CollectionUtils.isEmpty(transOrderDTOs) || transOrderDTOs.size() > 1) {
                logger.error("修改时不存在对应的交易单或存在多条，userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            if (!"0".equals(transOrderDTOs.get(0).getStatusid())) {
                logger.error("非处理中的订单无法修改,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            if (transOrderDTOs.get(0).getAmount().equals(orderOperRequest.getAmount())) {
                logger.error("不能修改订单金额,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            if (!transOrderDTOs.get(0).getUserrelateid().equals(orderOperRequest.getUserrelateid())) {
                logger.error("不能修改交易对方,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            transOrderDTOs.get(0).setGoodsname(orderOperRequest.getGoodsname());
            transOrderDTOs.get(0).setGoodsdetail(orderOperRequest.getGoodsdetail());
            transOrderDTOs.get(0).setAdjustcontent(orderOperRequest.getAdjustcontent());
            transOrderDTOs.get(0).setRemark(orderOperRequest.getRemark());
            boolean result = titanOrderService.updateTransOrder(transOrderDTOs.get(0));
            if (!result) {
                logger.error("修改交易单失败,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            return transOrderDTOs.get(0).getOrderid();
        }
        //查询
        if ("3".equals(orderOperRequest.getOpertype())) {
            if (CollectionUtils.isEmpty(transOrderDTOs) || transOrderDTOs.size() > 1) {
                logger.error("查询交易单不存在或存在多条，userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            return transOrderDTOs.get(0).getOrderid();
        }
        //取消
        if ("4".equals(orderOperRequest.getOpertype())) {
            if (CollectionUtils.isEmpty(transOrderDTOs) || transOrderDTOs.size() > 1) {
                logger.error("取消时交易单不存在或存在多条，userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            if (!"0".equals(transOrderDTOs.get(0).getStatusid())) {
                logger.error("非处理中的订单无法操作取消,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            //设置为失效
            logger.info("将订单设置为失效,userorderid：{}", orderOperRequest.getUserorderid());
            transOrderDTOs.get(0).setStatusid("10");
            titanOrderService.updateTransOrder(transOrderDTOs.get(0));
            return transOrderDTOs.get(0).getOrderid();
        }
        return null;
    }


    @Override
    public String operateRefundOrder(WheatfieldOrderServiceReturngoodsRequest returngoodsRequest) throws ServiceException {
        TransOrderRequest transOrderRequest = new TransOrderRequest();
        transOrderRequest.setUserorderid(returngoodsRequest.getUserorderid());
        transOrderRequest.setOrderid(returngoodsRequest.getOrderid());
        List<TransOrderDTO> transOrderDTOs = titanOrderService.queryTransOrder(transOrderRequest);
        if (CollectionUtils.isEmpty(transOrderDTOs)) {
            logger.error("不存在交易单，单号:{}", returngoodsRequest.getOrderid());
            return null;
        }
        int refundFlag = 1;
        for (TransOrderDTO transOrderDTO : transOrderDTOs) {
            if ("6".equals(transOrderDTO.getStatusid()) || "8".equals(transOrderDTO.getStatusid())) {
                refundFlag++;
            }
        }
        //只存在一个成功的交易才进行下一步退款
        if (refundFlag != 2) {
            logger.error("需退款的交易单状态不正确，单号:{}", returngoodsRequest.getOrderid());
            return null;
        }
        if (!returngoodsRequest.getAmount().equals(String.valueOf(transOrderDTOs.get(0).getTradeamount()))){
            logger.error("退款金额和原单金额不一致，单号:{}", returngoodsRequest.getOrderid());
            return null;
        }
        //验证又有的退款单信息
        RefundDTO refundDTO = new RefundDTO();
        refundDTO.setOrderNo(returngoodsRequest.getOrderid());
        List<RefundDTO> refundDTOList = titanOrderDao.queryRefundDTOList(refundDTO);
        if (CollectionUtils.isNotEmpty(refundDTOList)) {
            logger.error("已存在退款单，无法退款，单号：{}", returngoodsRequest.getOrderid());
            return null;
        }
        //生成并返回单号
        DecimalFormat formater = new DecimalFormat();
        formater.applyPattern("00");
        StringBuffer stringBuffer = new StringBuffer("OD");
        stringBuffer.append(DateUtil.dateToString(new Date(), "yyyyMMddHHmmsssss"));
        stringBuffer.append(formater.format((long) (Math.random() * 10)));
        return stringBuffer.toString();
    }

}
