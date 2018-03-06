package com.titanjr.fop.util;

import com.titanjr.fop.dto.SHBalanceInfo;
import com.titanjr.fop.dto.Transorderinfo;
import com.titanjr.fop.exceptions.ApiException;
import com.titanjr.fop.response.FopResponse;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

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
        Map classMap = new HashMap();
        classMap.put("transorderinfos",Transorderinfo.class);
        classMap.put("shbalanceinfos", SHBalanceInfo.class);
        T result = (T) JSONObject.toBean(jsonObject, clazz,classMap);
        return result;
    }

    public Class<T> getResponseClass() {
        return this.clazz;
    }
}
