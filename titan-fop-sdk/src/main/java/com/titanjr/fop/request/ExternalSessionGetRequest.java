package com.titanjr.fop.request;

import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.ExternalSessionGetResponse;

import java.util.Map;

/**
 * Created by zhaoshan on 2017/12/20.
 */
public class ExternalSessionGetRequest implements FopRequest<ExternalSessionGetResponse> {

    public ExternalSessionGetRequest() {

    }

    public String getApiMethodName() {
        return "ruixue.external.session.get";
    }

    public Map<String, String> getTextParams() {
        FopHashMap textParams = new FopHashMap();
        return textParams;
    }

    public Class<ExternalSessionGetResponse> getResponseClass() {
        return ExternalSessionGetResponse.class;
    }

    public void check() throws ApiRuleException {
        
    }
}
