package com.titanjr.fop.request;

import com.titanjr.fop.exceptions.ApiRuleException;
import com.titanjr.fop.response.FopResponse;

import java.util.Map;

/**
 * Created by zhaoshan on 2017/12/19.
 */
public interface FopRequest<T extends FopResponse>  {

    String getApiMethodName();

    Map<String, String> getTextParams();

    Class<T> getResponseClass();

    void check() throws ApiRuleException;
}
