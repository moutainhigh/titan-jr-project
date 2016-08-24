package com.fangcang.titanjr.pay.util;

import com.google.gson.Gson;

/**
 * json转换工具
 * 
 * @author wengxitao
 *
 */
public final class JsonConversionTool {
	private final static Gson gson = new Gson();

	public static <T> T toObject(String json, Class<T> t) {
		return gson.fromJson(json, t);
	}

	public static String toJson(String object) {
		return gson.toJson(object);
	}
	
}
