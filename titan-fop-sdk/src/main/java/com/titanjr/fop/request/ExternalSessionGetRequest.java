package com.titanjr.fop.request;

import com.titanjr.fop.domain.FopHashMap;
import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.ExternalSessionGetResponse;

import java.util.Map;

/**
 * Created by zhaoshan on 2017/12/20.
 */
public class ExternalSessionGetRequest extends BaseRequest implements FopRequest<ExternalSessionGetResponse> {

    private String extParam;

    public String getExtParam() {
        return extParam;
    }

    public void setExtParam(String extParam) {
        this.extParam = extParam;
    }

    public ExternalSessionGetRequest() {

    }

    public String getApiMethodName() {
        return "ruixue.external.session.get";
    }

    public Map<String, String> getTextParams() {
        FopHashMap textParams = new FopHashMap();
        textParams.put("extParam",extParam);
        return textParams;
    }

    public Class<ExternalSessionGetResponse> getResponseClass() {
        return ExternalSessionGetResponse.class;
    }

    public void check() throws ApiRuleException {
//        throw new ApiRuleException("excode", "exmsg");
    }
}
