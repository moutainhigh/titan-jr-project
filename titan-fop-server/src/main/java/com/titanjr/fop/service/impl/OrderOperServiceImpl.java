package com.titanjr.fop.service.impl;

import com.fangcang.exception.ServiceException;
import com.fangcang.titanjr.dto.bean.TransOrderDTO;
import com.fangcang.titanjr.dto.request.TransOrderRequest;
import com.fangcang.titanjr.service.TitanOrderService;
import com.fangcang.util.DateUtil;
import com.titanjr.fop.request.WheatfieldOrderOperRequest;
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

    @Override
    public String operateOrder(WheatfieldOrderOperRequest orderOperRequest) throws ServiceException {
        TransOrderRequest transOrderRequest = new TransOrderRequest();
        transOrderRequest.setUserorderid(orderOperRequest.getUserorderid());
        transOrderRequest.setUserid(orderOperRequest.getUserid());
        List<TransOrderDTO> transOrderDTOs = titanOrderService.queryTransOrder(transOrderRequest);
        //新增
        if ("1".equals(orderOperRequest.getOpertype())){
            if (CollectionUtils.isNotEmpty(transOrderDTOs)){
                logger.error("新增时已存在交易单，userorderid：{}" , orderOperRequest.getUserorderid());
                return null;
            }
            DecimalFormat formater = new DecimalFormat();
            formater.applyPattern("000000");
            String result = DateUtil.dateToString(new Date(),"yyyyMMddHHmmsssss") + formater.format((long)(Math.random()*1000));
            return result;
        }
        //修改
        if ("2".equals(orderOperRequest.getOpertype())){
            if (CollectionUtils.isEmpty(transOrderDTOs) || transOrderDTOs.size() > 1){
                logger.error("修改时不存在对应的交易单或存在多条，userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            if (transOrderDTOs.get(0).getAmount().equals(orderOperRequest.getAmount())){
                logger.error("不能修改订单金额,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            if (!transOrderDTOs.get(0).getUserrelateid().equals(orderOperRequest.getUserrelateid())){
                logger.error("不能修改交易对方,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            transOrderDTOs.get(0).setGoodsname(orderOperRequest.getGoodsname());
            transOrderDTOs.get(0).setGoodsdetail(orderOperRequest.getGoodsdetail());
            transOrderDTOs.get(0).setAdjustcontent(orderOperRequest.getAdjustcontent());
            transOrderDTOs.get(0).setRemark(orderOperRequest.getRemark());
            boolean result = titanOrderService.updateTransOrder(transOrderDTOs.get(0));
            if (!result){
                logger.error("修改交易单失败,userorderid：{}", orderOperRequest.getUserorderid());
                return null;
            }
            return transOrderDTOs.get(0).getOrderid();
        }
        //
        if ("3".equals(orderOperRequest.getOpertype())){

        }

        return null;
    }

    public static void main(String[] args) {

    }
}
