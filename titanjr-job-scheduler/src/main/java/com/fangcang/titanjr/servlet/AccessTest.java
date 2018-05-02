package com.fangcang.titanjr.servlet;

import com.fangcang.titanjr.service.TitanSysconfigService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhaoshan on 2017/1/12.
 */
@WebServlet(urlPatterns="/accessTest")
public class AccessTest extends HttpServlet {

    private static final Log log = LogFactory.getLog(AccessTest.class);

    private TitanSysconfigService titanSysconfigService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("begin invoke remote service");
        titanSysconfigService.serviceAccessTest();
        resp.getWriter().print("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        titanSysconfigService = (TitanSysconfigService) WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext()).getBean(
                        "titanSysconfigService");
        super.init();
    }
}
