package com.titanjr.fop.exceptions;

/**
 * Created by zhaoshan on 2017/12/19.
 */
public class ApiRuleException extends ApiException {

    public ApiRuleException(String errCode, String errMsg) {
        super(errCode, errMsg);
    }
}