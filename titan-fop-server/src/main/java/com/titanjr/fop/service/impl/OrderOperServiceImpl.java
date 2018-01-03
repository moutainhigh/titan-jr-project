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

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by zhaoshan on 2018/1/3.
 */
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
                logger.error("");
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
                logger.error("");
                return null;
            }
            if (orderOperRequest.getAmount().equals())
        }

        return null;
    }

    public static void main(String[] args) {

    }
}
