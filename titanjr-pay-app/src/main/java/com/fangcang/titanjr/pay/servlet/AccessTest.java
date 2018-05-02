package com.fangcang.titanjr.pay.servlet;

import com.fangcang.titanjr.service.TitanSysconfigService;
import com.fangcang.util.SpringContextUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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
    /**
	 * 
	 */
	private static final long serialVersionUID = -3697545694347639978L;

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
        titanSysconfigService = (TitanSysconfigService) SpringContextUtil.getBean("titanSysconfigService");
        super.init();
    }
}
