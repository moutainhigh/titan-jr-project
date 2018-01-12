package com.titanjr.fop.api;

import com.fangcang.util.StringUtil;
import com.titanjr.fop.constants.ReturnCodeEnum;
import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.domain.RequestParametersHolder;
import com.titanjr.fop.exceptions.ApiException;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.request.FopRequest;
import com.titanjr.fop.response.FopResponse;
import com.titanjr.fop.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by zhaoshan on 2017/12/19.
 */
public class DefaultFopClient implements FopClient {

    private final static Logger logger = LoggerFactory.getLogger(DefaultFopClient.class);

    private static final String APP_KEY = "app_key";
    private static final String FORMAT = "format";
    private static final String METHOD = "method";
    private static final String TIMESTAMP = "timestamp";
    private static final String SIGN = "sign";
    private static final String SESSION = "session";
    //初始服务地址
    private String initServerUrl;
    //服务地址
    private String serverUrl;
    //key
    private String appKey;
    //安全签名
    private String appSecret;
    //参数格式
    private String format;
    //签名方式
    private String signMethod;
    //连接超时设置
    private int connectTimeout;
    //读取超时设置
    private int readTimeout;
    private Boolean needCheckRequest;

    public DefaultFopClient(String serverUrl, String appKey, String appSecret) {
        this.format = "json";
        this.signMethod = "md5";
        this.connectTimeout = 0;
        this.readTimeout = 0;
        this.needCheckRequest = Boolean.valueOf(true);
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.serverUrl = serverUrl;
        this.initServerUrl = serverUrl;
    }

    public DefaultFopClient(String serverUrl, String appKey, String appSecret, String format) {
        this(serverUrl, appKey, appSecret);
        this.format = format;
    }

    public DefaultFopClient(String serverUrl, String appKey, String appSecret, String format, int connectTimeout, int readTimeout) {
        this(serverUrl, appKey, appSecret, format);
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
    }

    public DefaultFopClient(String serverUrl, String appKey, String appSecret, String format, int connectTimeout, int readTimeout, String signMethod) {
        this(serverUrl, appKey, appSecret, format, connectTimeout, readTimeout);
        this.signMethod = signMethod;
    }

    public <T extends FopResponse> T execute(FopRequest<T> fopRequest) throws ApiException {
        return this.execute(fopRequest, null);
    }

    @Override
    public <T extends FopResponse> T execute(FopRequest<T> fopRequest, String session) throws ApiException {
        String apiMethodName = fopRequest.getApiMethodName();
        logger.info("current request interface:{}", apiMethodName);
        //暂只支持json格式
        Object fopParser = new ObjectJsonParser(fopRequest.getResponseClass());

        T res = (T) this._execute(fopRequest, (FopParser) fopParser, session);

        return res;
    }

    private <T extends FopResponse> T _execute(FopRequest<T> fopRequest, FopParser<T> fopParser, String session) throws ApiException {

        T fopResponse = null;

        if (this.needCheckRequest.booleanValue()) {
            try {
                fopRequest.check();
            } catch (ApiRuleException ruleException) {
                try {
                    fopResponse = (T) fopRequest.getResponseClass().newInstance();
                } catch (InstantiationException ex1) {
                    throw new ApiException(ex1);
                } catch (IllegalAccessException ex2) {
                    throw new ApiException(ex2);
                }

                fopResponse.setErrorCode(ruleException.getErrCode());
                fopResponse.setMsg(ruleException.getErrMsg());
                return fopResponse;
            }
        }

        Map responseMap = this.doPost(fopRequest, session);

        if (responseMap == null) {
            fopResponse.setErrorCode("result empty");
            fopResponse.setMsg("result empty");
            return fopResponse;
        } else {
            try {
                //解析返回的参数，使用对应的解析器
                fopResponse = fopParser.parse((String) responseMap.get("rsp"));
                fopResponse.setBody((String) responseMap.get("rsp"));
            } catch (RuntimeException ex) {
                logger.error("runtime exception, response info:{}", responseMap.get("rsp"));
                throw ex;
            }

            fopResponse.setParams((HashMap) responseMap.get("textParams"));
            if (!fopResponse.isSuccess()) {
                logger.error("request fail,");
            }
            return fopResponse;
        }
    }

    private <T extends FopResponse> Map<String, Object> doPost(FopRequest<T> fopRequest, String session) throws ApiException {
        this.appSecret = this.appSecret.toUpperCase();
        HashMap response = new HashMap();
        RequestParametersHolder parametersHolder = new RequestParametersHolder();
        //应用接口参数
        FopHashMap appParamMap = new FopHashMap(fopRequest.getTextParams());
        parametersHolder.setApplicationParams(appParamMap);
        //接口协议参数
        FopHashMap protocalParams = new FopHashMap();
        protocalParams.put("method", fopRequest.getApiMethodName());
        protocalParams.put("appKey", this.appKey);
        Long current = Long.valueOf((new Date()).getTime());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        protocalParams.put("timeStamp", dateFormat.format(new Date(current.longValue())));
        parametersHolder.setProtocalMustParams(protocalParams);
        //可选参数
        FopHashMap optParams = new FopHashMap();
        optParams.put("format", this.format);
        if (!fopRequest.getApiMethodName().toString().equals("ruixue.external.session.get")) {
            optParams.put("session", session);
        }
        if ("ruixue.fs.fileurl.get".equals(fopRequest.getApiMethodName())) {
            optParams.put("directoryname", this.appKey);
        }
        parametersHolder.setProtocalOptParams(optParams);

        try {
            if ("md5".equals(this.signMethod)) {
                protocalParams.put("sign", FopUtils.signFopRequest(parametersHolder, this.appSecret));
            }
        } catch (IOException iex) {
            logger.error("签名计算错误", iex);
            throw new ApiException(iex);
        }

        //现在可用同一个地址
        this.serverUrl = WebUtils.getActualUrl(this.initServerUrl, this.appKey, this.appSecret, session);
        if (!StringUtil.isValidString(this.serverUrl)) {
            logger.error(ReturnCodeEnum.SERVICE_URL_ERROR.getMsg());
            throw new ApiException(ReturnCodeEnum.SERVICE_URL_ERROR.getMsg());
        }
        StringBuffer serviceURL = new StringBuffer(this.serverUrl);
        try {
            String protocalStr = WebUtils.buildQuery(parametersHolder.getProtocalMustParams(), "UTF-8");
            String optStr = WebUtils.buildQuery(parametersHolder.getProtocalOptParams(), "UTF-8");
            serviceURL.append("?");
            serviceURL.append(protocalStr);
            if (optStr != null & optStr.length() > 0) {
                serviceURL.append("&");
                serviceURL.append(optStr);
            }
        } catch (IOException iox) {
            throw new ApiException(iox);
        }

        String result;

        try {
            result = WebUtils.doPost(serviceURL.toString(), appParamMap, this.connectTimeout, this.readTimeout);
        } catch (IOException iox) {
            throw new ApiException(iox);
        }
        //返回的json
        response.put("rsp", result);
        response.put("textParams", appParamMap);
        response.put("protocalMustParams", protocalParams);
        response.put("protocalOptParams", optParams);
        response.put("url", serviceURL.toString());
        return response;
    }
}
