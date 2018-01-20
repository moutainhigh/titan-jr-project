package com.titanjr.fop.util;

import com.titanjr.fop.response.FopResponse;
import net.sf.json.JSONSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhaoshan on 2017/12/28.
 */
public class HandlerApiExceptionResolver implements HandlerExceptionResolver {

    private static final Logger logger = LoggerFactory.getLogger("HandlerApiExceptionResolver");

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception exception) {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("exception", exception);

        PrintWriter writer;
        try {
            writer = response.getWriter();
        } catch (IOException e) {
            logger.error("执行异常：{}", e.getMessage(), e);
            return null;
        }

        FopResponse fopResponse = new FopResponse();
        fopResponse.setErrorCode("Param Valid Error");
        fopResponse.setMsg("参数校验异常");
        StringWriter sw = new StringWriter();
        exception.printStackTrace(new PrintWriter(sw));

        String json = JSONSerializer.toJSON(fopResponse).toString();
        assert json != null;
        writer.write(json);
        writer.flush();
        return null;

    }
}
