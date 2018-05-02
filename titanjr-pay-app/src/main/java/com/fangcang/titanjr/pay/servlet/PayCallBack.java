package com.fangcang.titanjr.pay.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhaoshan on 2016/11/14.
 */
@WebServlet(urlPatterns="/payCallBack")
public class PayCallBack extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String financeCode = req.getParameter("payOrderCode");
        String merchantCode = req.getParameter("merchantCode");
        String titanPayOrderCode = req.getParameter("titanPayOrderCode");
        boolean payResult = false;
        String payResultStr = req.getParameter("payResult");
        if (isValidString(payResultStr) && "1".equals(payResultStr)) {
            payResult = true;
        }
        if (!isValidString(financeCode) || !isValidString(merchantCode) ||
                !isValidString(titanPayOrderCode)) {
            resp.getWriter().print("code=002&msg=请求参数不合法");
            return;
        }
        String callBackResult = titanPayCallback(financeCode, merchantCode, titanPayOrderCode, payResult);
        if ("success".equals(callBackResult)) {
            resp.getWriter().print("code=000&msg=成功");
        } else {
            resp.getWriter().print("code=003&msg=" + callBackResult);
        }
    }

    private String titanPayCallback(String financeCode, String merchantCode,
                                    String titanPayOrderCode, boolean payResult) {
        //do your own business logic
        return null;
    }

    private boolean isValidString(String paramString){
        return (null != paramString && !"".equals(paramString));
    }
}
