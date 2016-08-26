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
		if (json == null) {
			return null;
		}

		return gson.fromJson(json, t);
	}

	public static String toJson(Object object) {
		if (object == null) {
			return null;
		}

		return gson.toJson(object);
	}

}
