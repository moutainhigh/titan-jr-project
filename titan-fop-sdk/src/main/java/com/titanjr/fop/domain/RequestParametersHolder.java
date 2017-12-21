package com.titanjr.fop.domain;

/**
 * Created by zhaoshan on 2017/12/20.
 */
public class RequestParametersHolder {
    private FopHashMap protocalMustParams;
    private FopHashMap protocalOptParams;
    private FopHashMap applicationParams;

    public RequestParametersHolder() {
    }

    public FopHashMap getProtocalMustParams() {
        return this.protocalMustParams;
    }

    public void setProtocalMustParams(FopHashMap var1) {
        this.protocalMustParams = var1;
    }

    public FopHashMap getProtocalOptParams() {
        return this.protocalOptParams;
    }

    public void setProtocalOptParams(FopHashMap var1) {
        this.protocalOptParams = var1;
    }

    public FopHashMap getApplicationParams() {
        return this.applicationParams;
    }

    public void setApplicationParams(FopHashMap var1) {
        this.applicationParams = var1;
    }
}
