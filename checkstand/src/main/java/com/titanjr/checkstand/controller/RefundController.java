package com.titanjr.checkstand.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhaoshan on 2017/11/17.
 */
@Controller
@RequestMapping(value = "/refund")
public class RefundController extends BaseController {

    @RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public void entrance(HttpServletRequest request, Model model) throws Exception {
        //退款入口
        System.out.println("退款接口入口");
    }

}
