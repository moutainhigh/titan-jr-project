
package com.titanjr.fop.util;

import com.fangcang.redis.dao.LogDataDao;
import com.fangcang.redis.entity.LogData;
import com.fangcang.titanjr.common.util.Tools;
import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.response.FopResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * 调用日志记录器,将所所有调用过程日志记录，
 * 评估如果日志需要入库，需修改此处将日志入库
 * 加上调用异常时处理，即便调用异常也能记录日志
 */
public class InvokeLogRecorder {

    private static final Log log = LogFactory.getLog(InvokeLogRecorder.class);

    private ThreadPoolTaskExecutor logRecorderExecutor;

    private LogDataDao logDataDao;

    public Object aroundInvokeExecution(ProceedingJoinPoint joinPoint) throws Exception {
        Object retVal = null;
        Object response;
        Exception exception = null;
        Date startDate = new Date();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        try {
            retVal = joinPoint.proceed();
            if (null == retVal) {
                log.error("aroundInvokeExecution.retVal is null, joinPoint=" + joinPoint);
                response = new FopResponse();
                ((FopResponse) response).setErrorCode(ReturnCodeEnum.CODE_SYS_ERROR.getCode());
                ((FopResponse) response).setMsg(ReturnCodeEnum.CODE_SYS_ERROR.getCode() + "，返回结果为空");
            } else {
                response = retVal;
            }
        } catch (Throwable e) {
            log.error("InvokeLogRecorder执行切面方法异常", e);
            response = new FopResponse();
            ((FopResponse) response).setErrorCode(e.getLocalizedMessage());
            ((FopResponse) response).setMsg(e.getMessage());
            ((FopResponse) response).setBody(Tools.gsonToString(e.getStackTrace()));
            if (e instanceof Exception) {
                exception = (Exception) e;
            } else {
                exception = new Exception("未知异常", e);
            }
        }

        try {
            buildLogData(startDate, signature.getMethod(), joinPoint, response);
        } catch (Throwable e) {
            log.error("InvokeLogRecorder将日志存储记录于redis失败", e);
        }
        if (null != exception) {
            throw exception;
        }
        return retVal;
    }

    private void buildLogData(Date startDate, Method method, JoinPoint joinPoint, Object retVal) {
        String request = "";
        if (joinPoint.getArgs().length > 0) {
            request = Tools.gsonToString(joinPoint.getArgs()[0]);
        }
        String response = "";
        if (null != retVal) {
            response = Tools.gsonToString(retVal);
        }
        String status = "1";
        if (retVal instanceof FopResponse) {//判定返回是否成功
            FopResponse resp = (FopResponse) retVal;
            if (!resp.isSuccess()) {
                status = "0";
            }
        }
        syncLogELK(startDate, new Date(), "titan-fop-server:" + method.getName(), request, response, status);
    }

    public void syncLogELK(Date startDate, Date endDate, String index, String request, String response, String status) {
        LogData logData = new LogData();
        logData.setStart(startDate);
        logData.setEnd(endDate);
        logData.setIndex(index);
        logData.setAdditional("titanjr");
        logData.setDocType("titanjr");
        logData.setRequest(request);
        logData.setResponse(response);
        logData.setStatus(status);

        LogRecordTask task = new LogRecordTask();
        task.setLogData(logData);
        task.setLogDataDao(logDataDao);
        logRecorderExecutor.execute(task);
    }

    public void setLogRecorderExecutor(ThreadPoolTaskExecutor logRecorderExecutor) {
        this.logRecorderExecutor = logRecorderExecutor;
    }

    public void setLogDataDao(LogDataDao logDataDao) {
        this.logDataDao = logDataDao;
    }
}
