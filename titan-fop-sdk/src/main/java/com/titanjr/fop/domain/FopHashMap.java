package com.titanjr.fop.domain;

import com.fangcang.util.StringUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by zhaoshan on 2017/12/20.
 */
public class FopHashMap extends HashMap<String, String> {


    public FopHashMap() {
    }

    public FopHashMap(Map<? extends String, ? extends String> var1) {
        super(var1);
    }

    public String put(String key, Object value) {
        String valueString;
        if (value == null) {
            valueString = null;
        } else if (value instanceof String) {
            valueString = (String) value;
        } else if (value instanceof Integer) {
            valueString = ((Integer) value).toString();
        } else if (value instanceof Long) {
            valueString = ((Long) value).toString();
        } else if (value instanceof Float) {
            valueString = ((Float) value).toString();
        } else if (value instanceof Double) {
            valueString = ((Double) value).toString();
        } else if (value instanceof Boolean) {
            valueString = ((Boolean) value).toString();
        } else if (value instanceof Date) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            format.setTimeZone(TimeZone.getTimeZone("GMT+8"));
            valueString = format.format((Date) value);
        } else {
            valueString = value.toString();
        }

        return this.put(key, valueString);
    }

    public String put(String key, String value) {
        if (StringUtil.isValidString(key) && StringUtil.isValidString(value)) {
            return super.put(key, value);
        }
        return null;
    }
}

