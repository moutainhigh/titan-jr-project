package com.titanjr.fop.util;

import com.titanjr.fop.exceptions.ApiException;
import com.titanjr.fop.response.FopResponse;
import net.sf.json.JSONObject;

/**
 * Created by zhaoshan on 2017/12/19.
 */
public class ObjectJsonParser<T extends FopResponse> implements FopParser<T> {
    private Class<T> clazz;

    public ObjectJsonParser(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T parse(String resultJson) throws ApiException {
        JSONObject jsonObject = JSONObject.fromObject(resultJson);
        T result = (T) JSONObject.toBean(jsonObject, clazz);
        return result;
    }

    public Class<T> getResponseClass() {
        return this.clazz;
    }
}
