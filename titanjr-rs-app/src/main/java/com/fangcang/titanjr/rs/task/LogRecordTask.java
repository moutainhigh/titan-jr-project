package com.fangcang.titanjr.rs.task;

import com.fangcang.redis.dao.LogDataDao;
import com.fangcang.redis.entity.LogData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zhaoshan on 2016/5/11.
 */
public class LogRecordTask implements Runnable{

    private static final Log log = LogFactory.getLog(LogRecordTask.class);

    private LogDataDao logDataDao;

    private LogData logData;

    @Override
    public void run() {
        log.info("begin to record logData to redis cache.");
        try {
            logDataDao.saveValue(logData);
        } catch (Exception e) {
            log.error("saveLogData to redis cache error!", e);
        }
    }

    public void setLogDataDao(LogDataDao logDataDao) {
        this.logDataDao = logDataDao;
    }

    public void setLogData(LogData logData) {
        this.logData = logData;
    }
}
