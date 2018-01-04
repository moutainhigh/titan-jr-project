package com.titanjr.fop.controller;

import com.fangcang.util.StringUtil;
import com.titanjr.fop.dto.SHBalanceInfo;
import com.titanjr.fop.request.WheatfieldBalanceGetlistRequest;
import com.titanjr.fop.request.WheatfieldOrderOperRequest;
import com.titanjr.fop.response.WheatfieldBalanceGetlistResponse;
import com.titanjr.fop.response.WheatfieldOrderOperResponse;
import com.titanjr.fop.service.OrderOperService;
import com.titanjr.fop.util.BeanUtils;
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
    OrderOperService orderOperService;

    @RequestMapping(value = "/orderOper", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String orderOper(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        logger.debug("请求参数为:{}", request.getParameterMap());
        WheatfieldOrderOperResponse operResponse = new WheatfieldOrderOperResponse();
        String validResult = validRequestSign(request, operResponse);
        if (null != validResult) {
            return validResult;
        }

        WheatfieldOrderOperRequest orderOperRequest = BeanUtils.switch2RequestDTO(WheatfieldOrderOperRequest.class, request);
        if (null == orderOperRequest) {
            return getConvertErrorResp(operResponse);
        }

        String orderNo = orderOperService.operateOrder(orderOperRequest);
        if (!StringUtil.isValidString(orderNo)) {
            return getSysErrorResp(operResponse);
        }
        logger.info("操作成功，当前订单号为：{}", orderNo);
        operResponse.setIs_success("true");
        operResponse.setOrderid(orderNo);
        return toJson(operResponse);
    }


}
