package com.fangcang.titanjr.rs.manager.impl;

import com.Rop.api.DefaultRopClient;
import com.Rop.api.request.ExternalSessionGetRequest;
import com.Rop.api.response.ExternalSessionGetResponse;
import com.fangcang.titanjr.rs.dao.CallBackConfigDao;
import com.fangcang.titanjr.rs.dao.RSInvokeConfigDao;
import com.fangcang.titanjr.rs.dao.RSPayMethodDao;
import com.fangcang.titanjr.rs.entity.RSInvokeConfig;
import com.fangcang.titanjr.rs.entity.TitanCallBackConfig;
import com.fangcang.titanjr.rs.entity.TitanPayMethod;
import com.fangcang.titanjr.rs.entity.TitanPayMethodConfig;
import com.fangcang.titanjr.rs.util.RSInvokeConstant;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by zhaoshan on 2016/4/8.
 */
public class RSInvokeInitManagerImpl {

    private static final Log log = LogFactory.getLog(RSInvokeInitManagerImpl.class);

    private RSInvokeConfigDao rsInvokeConfigDao;

    private RSPayMethodDao rsPayMethodDao;

    private CallBackConfigDao callBackConfigDao;

    public void initRopClient() {
        //测试时使用
//        String ropUrl = "https://testapi.open.ruixuesoft.com:30005/ropapi";
//        String appKey = "F1A95B5E-3485-4CEB-8036-F2B9EC53EF65";
//        String appSecret = "8B6E8EEF-48CC-4CCF-94C6-55C4AA2FE9F2";
//        RSInvokeConstant.ropClient = new DefaultRopClient(ropUrl, appKey, appSecret, "xml");
//        RSInvokeConstant.sessionKey = "1460355562856409835";
//        RSInvokeConstant.defaultMerchant = "M10020809";
//        RSInvokeConstant.defaultRoleId = 1301L;
    	System.setProperty("sun.net.client.defaultConnectTimeout", "5000");  
    	System.setProperty("sun.net.client.defaultReadTimeout", "5000");
//        正式上线之后使用
        List<RSInvokeConfig> rsInvokeConfigs = rsInvokeConfigDao.queryRSInvokeConfig();
        if (!CollectionUtils.isEmpty(rsInvokeConfigs)) {
            String ropUrl = rsInvokeConfigs.get(0).getInvokeURL();
            String appKey = rsInvokeConfigs.get(0).getAppKey();
            String appSecret = rsInvokeConfigs.get(0).getAppSecret();
            RSInvokeConstant.ropClient = new DefaultRopClient(ropUrl, appKey, appSecret, "xml");
            RSInvokeConstant.defaultMerchant = rsInvokeConfigs.get(0).getDefaultMerchant();
            RSInvokeConstant.defaultRoleId = rsInvokeConfigs.get(0).getDefaultRoleId();
        }
        initSessionKey(RSInvokeConstant.ropClient);

        //用新的方式查询
        List<TitanPayMethod> payMethods = rsPayMethodDao.queryAllTitanPayMethod();
        if (!CollectionUtils.isEmpty(payMethods)) {
            RSInvokeConstant.gateWayURL = payMethods.get(0).getGatewayURL();
            RSInvokeConstant.rsCheckKey = payMethods.get(0).getCheckKey();
            RSInvokeConstant.titanjrCheckKey = payMethods.get(0).getTitanjrCheckKey();
            for (TitanPayMethodConfig config : payMethods.get(0).getPayMethodConfigList()) {
                RSInvokeConstant.payMethodConfigMap.put(config.getUserId(), config);
            }
        }
        log.info("当前构建的sessionkey是：" + RSInvokeConstant.sessionKey);
        List<TitanCallBackConfig> callBackConfigs = callBackConfigDao.queryAllCallBackConfig();
        for (TitanCallBackConfig callBackConfig : callBackConfigs) {
            RSInvokeConstant.callBackConfigMap.put(callBackConfig.getPaySource(),callBackConfig.getCallBackURL());
        }
    }

    public void initSessionKey(DefaultRopClient ropClient) {
        try {
            ExternalSessionGetRequest sessionGetReq = new ExternalSessionGetRequest();
            ExternalSessionGetResponse sessionGetRsp = ropClient.execute(sessionGetReq);
            RSInvokeConstant.sessionKey = sessionGetRsp.getSession();
        } catch (Exception e) {
            log.error("初始化加载sessionkey失败", e);
        }
    }

    public RSInvokeConfigDao getRsInvokeConfigDao() {
        return rsInvokeConfigDao;
    }

    public void setRsInvokeConfigDao(RSInvokeConfigDao rsInvokeConfigDao) {
        this.rsInvokeConfigDao = rsInvokeConfigDao;
    }

    public void setRsPayMethodDao(RSPayMethodDao rsPayMethodDao) {
        this.rsPayMethodDao = rsPayMethodDao;
    }

    public void setCallBackConfigDao(CallBackConfigDao callBackConfigDao) {
        this.callBackConfigDao = callBackConfigDao;
    }
}
