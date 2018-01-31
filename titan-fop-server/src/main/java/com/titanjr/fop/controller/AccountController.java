package com.titanjr.fop.controller;

import com.fangcang.util.StringUtil;
import com.titanjr.fop.dto.SHBalanceInfo;
import com.titanjr.fop.request.WheatfieldBalanceGetlistRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceAuthcodeserviceRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceThawauthcodeRequest;
import com.titanjr.fop.request.WheatfieldOrderServiceWithdrawserviceRequest;
import com.titanjr.fop.response.WheatfieldBalanceGetlistResponse;
import com.titanjr.fop.response.WheatfieldOrderServiceAuthcodeserviceResponse;
import com.titanjr.fop.response.WheatfieldOrderServiceThawauthcodeResponse;
import com.titanjr.fop.response.WheatfieldOrderServiceWithdrawserviceResponse;
import com.titanjr.fop.service.AccountService;
import com.titanjr.fop.util.BeanUtils;
import com.titanjr.fop.util.ResponseUtils;
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
import java.util.List;

/**
 * Created by zhaoshan on 2017/12/21.
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(AccountController.class);

    @Resource
    private AccountService accountService;

    @RequestMapping(value = "/getBalanceList", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String getBalanceList(HttpServletRequest request, RedirectAttributes attr) throws Exception {

        WheatfieldBalanceGetlistResponse getlistResponse = new WheatfieldBalanceGetlistResponse();
        String validResult = ResponseUtils.validRequestSign(request, getlistResponse);
        if (null != validResult) {
            return validResult;
        }
        getlistResponse.setIs_success("true");

        WheatfieldBalanceGetlistRequest getlistRequest = BeanUtils.switch2RequestDTO(WheatfieldBalanceGetlistRequest.class, request);
        if (null == getlistRequest) {
            return ResponseUtils.getConvertErrorResp(getlistResponse);
        }
        List<SHBalanceInfo> balanceInfos = accountService.getAccountBalanceList(getlistRequest);

        if (CollectionUtils.isEmpty(balanceInfos)) {
            logger.info("查询账户余额为空，userid：{}", getlistRequest.getUserid());
        }
        getlistResponse.setShbalanceinfos(balanceInfos);

        return toJson(getlistResponse);
    }

    @RequestMapping(value = "/freezeAccountBalance", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String freezeAccountBalance(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        WheatfieldOrderServiceAuthcodeserviceResponse authcodeserviceResponse = new WheatfieldOrderServiceAuthcodeserviceResponse();
        String validResult = ResponseUtils.validRequestSign(request, authcodeserviceResponse);
        if (null != validResult) {
            return validResult;
        }
        WheatfieldOrderServiceAuthcodeserviceRequest authcodeserviceRequest = BeanUtils.switch2RequestDTO(WheatfieldOrderServiceAuthcodeserviceRequest.class, request);
        if (null == authcodeserviceRequest) {
            return ResponseUtils.getConvertErrorResp(authcodeserviceResponse);
        }
        String authCode = accountService.freezeAccountBalance(authcodeserviceRequest);
        if (!StringUtil.isValidString(authCode)) {
            return ResponseUtils.getSysErrorResp(authcodeserviceResponse);
        }
        authcodeserviceResponse.setIs_success("true");
        authcodeserviceResponse.setAuthcode(authCode);
        logger.info("生成的冻结码为：{}", authCode);
        return toJson(authcodeserviceResponse);
    }

    @RequestMapping(value = "/unFreezeAccountBalance", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String unFreezeAccountBalance(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        WheatfieldOrderServiceThawauthcodeResponse thawauthcodeResponse = new WheatfieldOrderServiceThawauthcodeResponse();
        String validResult = ResponseUtils.validRequestSign(request, thawauthcodeResponse);
        if (null != validResult) {
            return validResult;
        }
        WheatfieldOrderServiceThawauthcodeRequest thawauthcodeRequest = BeanUtils.switch2RequestDTO(WheatfieldOrderServiceThawauthcodeRequest.class, request);
        if (null == thawauthcodeRequest) {
            return ResponseUtils.getConvertErrorResp(thawauthcodeResponse);
        }
        thawauthcodeResponse = accountService.unFreezeAccountBalance(thawauthcodeRequest);
        if (null == thawauthcodeResponse) {
            return ResponseUtils.getSysErrorResp(thawauthcodeResponse);
        }
        return toJson(thawauthcodeResponse);
    }

    @RequestMapping(value = "/accountWithDraw", method = {RequestMethod.POST, RequestMethod.GET}, produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String accountWithDraw(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        WheatfieldOrderServiceWithdrawserviceResponse withdrawserviceResponse = new WheatfieldOrderServiceWithdrawserviceResponse();
        String validResult = ResponseUtils.validRequestSign(request, withdrawserviceResponse);
        if (null != validResult) {
            return validResult;
        }
        WheatfieldOrderServiceWithdrawserviceRequest withdrawserviceRequest = BeanUtils.switch2RequestDTO(WheatfieldOrderServiceWithdrawserviceRequest.class, request);
        if (null == withdrawserviceRequest) {
            return ResponseUtils.getConvertErrorResp(withdrawserviceResponse);
        }
        withdrawserviceResponse = accountService.accountBalanceWithDraw(withdrawserviceRequest);
        if (null == withdrawserviceResponse) {
            return ResponseUtils.getSysErrorResp(withdrawserviceResponse);
        }
        return toJson(withdrawserviceResponse);
    }

}
