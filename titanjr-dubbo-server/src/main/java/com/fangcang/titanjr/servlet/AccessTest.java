package com.fangcang.titanjr.servlet;

import com.fangcang.titanjr.dao.TitanSysConfigDao;
import com.fangcang.util.SpringContextUtil;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by zhaoshan on 2017/1/11.
 */
public class AccessTest extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TitanSysConfigDao titanSysConfigDao = (TitanSysConfigDao) SpringContextUtil.getBean("titanSysConfigDao");
        titanSysConfigDao.accessQueryTest();
        resp.getWriter().print("success");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
