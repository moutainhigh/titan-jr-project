package com.fangcang.titanjr.dao.impl;

import com.fangcang.corenut.dao.impl.GenericDAOMyBatisImpl;
import com.fangcang.exception.DaoException;
import com.fangcang.titanjr.dao.DomainConfigDao;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zhaoshan on 2016/8/18.
 */
public class DomainConfigDaoImpl extends GenericDAOMyBatisImpl implements DomainConfigDao {

    private static final Log log = LogFactory.getLog(DomainConfigDaoImpl.class);

    @Override
    public String queryCurrentEnvDomain() {
        String fcVersion = System.getenv("FC_VERSION");
        log.info("当前环境变量" + fcVersion);
        try {
            Object domain = super.selectOne("com.fangcang.titanjr.dao.DomainConfigDao.queryCurrentEnvDomain", fcVersion);
            if (domain == null){
                log.error("查询得到当前环境的domain为空，无法完成回调" );
                return null;
            }
            log.error("查询得到当前环境的domain为空：" + domain);
            return domain.toString();
        } catch (Exception e) {
            throw new DaoException(e);
        }
    }
}
