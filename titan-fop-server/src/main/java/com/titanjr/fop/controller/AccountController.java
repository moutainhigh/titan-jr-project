package com.titanjr.fop.controller;

import com.titanjr.fop.dto.SHBalanceInfo;
import com.titanjr.fop.response.SessionGetResponse;
import com.titanjr.fop.response.WheatfieldBalanceGetlistResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

/**
 * Created by zhaoshan on 2017/12/21.
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController {


    @RequestMapping(value = "/getBalanceList", method ={RequestMethod.POST,RequestMethod.GET},produces = "text/json;charset=UTF-8")
    @ResponseBody
    public String fopsdk(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        WheatfieldBalanceGetlistResponse getlistResponse = new WheatfieldBalanceGetlistResponse();
        getlistResponse.setIs_success("true");
        getlistResponse.setShbalanceinfos(new ArrayList<SHBalanceInfo>());

        return toJson(getlistResponse);
    }

}
