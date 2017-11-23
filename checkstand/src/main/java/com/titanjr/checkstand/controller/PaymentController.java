package com.titanjr.checkstand.controller;

import com.titanjr.checkstand.constants.PayTypeEnum;
import com.titanjr.checkstand.dao.GateWayPayDao;
import com.titanjr.checkstand.dto.GateWayPayDTO;
import com.titanjr.checkstand.request.GateWayPayRequest;
import com.titanjr.checkstand.strategy.PayRequestStrategy;
import com.titanjr.checkstand.strategy.StrategyFactory;
import com.titanjr.checkstand.util.WebUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoshan on 2017/10/19.
 */
@Controller
@RequestMapping(value = "/pay")
public class PaymentController extends BaseController {


    @Resource
    private GateWayPayDao gateWayPayDao;

    /**
     * 所有支付入口，进入后分配到具体的支付接口返回不同的参数
     * @param request
     * @param attr
     * @throws Exception
     */
    @RequestMapping(value = "/entrance", method = {RequestMethod.GET, RequestMethod.POST})
    public String entrance(HttpServletRequest request, RedirectAttributes attr) throws Exception {
        //付款入口,根据付款类型来判定走到具体哪个接口
        //需根据得到的参数类型来做校验
        PayTypeEnum payTypeEnum = PayTypeEnum.getPayTypeEnum(request.getParameter("payType"));
        PayRequestStrategy payRequestStrategy =  StrategyFactory.getPayRequestStrategy(payTypeEnum);
        String redirectUrl = payRequestStrategy.redirectResult(request);
        super.resetParameter(request,attr);
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/gateWayPay", method = {RequestMethod.GET, RequestMethod.POST})
    public String batchQuery(HttpServletRequest request, Model model) throws Exception {
        GateWayPayDTO payDTO = WebUtils.switch2RequestDTO(GateWayPayDTO.class, request);
        GateWayPayRequest payRequest = new GateWayPayRequest();
        payRequest.setGateWayPayDTO(payDTO);
        payRequest.validate();
        List<GateWayPayDTO> gateWayPayDTOList = new ArrayList<GateWayPayDTO>();
        gateWayPayDTOList.add(payDTO);
        gateWayPayDao.batchSaveGateWayPayDTO(gateWayPayDTOList);
        return "gateWayPay";
    }


    @ResponseBody
    @RequestMapping(value = "/QRCodePay", method = {RequestMethod.GET, RequestMethod.POST})
    public String payPage(HttpServletRequest request, Model model) throws Exception {

        return "QRCodePayJsonString";
    }




}
