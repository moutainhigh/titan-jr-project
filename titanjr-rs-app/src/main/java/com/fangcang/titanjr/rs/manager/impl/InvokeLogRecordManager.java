package com.fangcang.titanjr.rs.manager.impl;

import java.lang.reflect.Method;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fangcang.redis.dao.LogDataDao;
import com.fangcang.redis.entity.LogData;
import com.fangcang.titanjr.common.util.Tools;
import com.fangcang.titanjr.rs.response.BaseResponse;
import com.fangcang.titanjr.rs.task.LogRecordTask;

/**
 * 调用日志记录的manger
 * 相当于切面类，异步记录接口调用日志
 * Created by zhaoshan on 2016/5/11.
 */
public class InvokeLogRecordManager {

    private static final Log log = LogFactory.getLog(InvokeLogRecordManager.class);

    private ThreadPoolTaskExecutor rsLogRecordExecutor;

    private LogDataDao logDataDao;

    public Object aroundRSInvokeExecution(ProceedingJoinPoint joinPoint) throws Exception {
        Object retVal = null;
        Date startDate = new Date();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        try {
            retVal = joinPoint.proceed();
            if (null == retVal) {
                log.error("aroundRSInvokeExecution.retVal is null, joinPoint=" + joinPoint);
                return null;
            }
        } catch (Throwable e) {
            log.error("执行切面方法异常", e);
        }

        try {
            LogRecordTask task = new LogRecordTask();
            task.setLogData(buildLogData(startDate,signature.getMethod(),joinPoint,retVal));
            task.setLogDataDao(logDataDao);
            rsLogRecordExecutor.execute(task);
        } catch (Throwable e) {
            log.error("新日志记录，将日志存储记录于redis失败", e);
        }
        return retVal;
    }

    private LogData buildLogData(Date startDate, Method method, JoinPoint joinPoint, Object retVal){
        LogData logData = new LogData();
        logData.setStart(startDate);
        logData.setEnd(new Date());
        logData.setIndex("titanjr-rs-app:" + method.getName());
        logData.setAdditional("titanjr");
        if (joinPoint.getArgs().length > 0) {
            logData.setRequest(Tools.gsonToString(joinPoint.getArgs()[0]));
        }
        if (null != retVal) {
            logData.setResponse(Tools.gsonToString(retVal));
        }
        logData.setStatus("1");
        if (retVal instanceof BaseResponse){
            BaseResponse resp = (BaseResponse) retVal;
            if (!resp.isSuccess()){
                logData.setStatus("0");
            }
        }
        return logData;
    }

    public void setRsLogRecordExecutor(ThreadPoolTaskExecutor rsLogRecordExecutor) {
        this.rsLogRecordExecutor = rsLogRecordExecutor;
    }

    public void setLogDataDao(LogDataDao logDataDao) {
        this.logDataDao = logDataDao;
    }
}
