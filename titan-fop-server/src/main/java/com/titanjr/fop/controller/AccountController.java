package com.titanjr.fop.controller;

import com.titanjr.fop.dto.SHBalanceInfo;
import com.titanjr.fop.request.WheatfieldBalanceGetlistRequest;
import com.titanjr.fop.response.WheatfieldBalanceGetlistResponse;
import com.titanjr.fop.service.AccountService;
import com.titanjr.fop.util.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoshan on 2017/12/21.
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(OrderOperController.class);

    @Resource
    private AccountService accountService;

    @RequestMapping(value = "/getBalanceList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getBalanceList(HttpServletRequest request, RedirectAttributes attr) throws Exception {

        WheatfieldBalanceGetlistResponse getlistResponse = new WheatfieldBalanceGetlistResponse();
        String validResult = validRequestSign(request, getlistResponse);
        if (null != validResult) {
            return validResult;
        }
        getlistResponse.setIs_success("true");

        WheatfieldBalanceGetlistRequest getlistRequest = BeanUtils.switch2RequestDTO(WheatfieldBalanceGetlistRequest.class,request);
        if (null == getlistRequest){
            return getConvertErrorResp(getlistResponse);
        }
        List<SHBalanceInfo> balanceInfos = accountService.getAccountBalanceList(getlistRequest);

        if (CollectionUtils.isEmpty(balanceInfos)){
            logger.error("查询账户余额为空，userid：{}" , getlistRequest.getUserid());
        }
        getlistResponse.setShbalanceinfos(balanceInfos);

        return toJson(getlistResponse);
    }

}
