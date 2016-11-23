package com.fangcang.titanjr.common.util;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

/**
 * json转换工具
 * 
 * @author wengxitao
 *
 */
public final class JsonConversionTool {
	private final static Gson gson = new Gson();

	public static <T> T toObject(String json, Class t) {
		if (json == null) {
			return null;
		}

		return (T) gson.fromJson(json, t);
	}

	public static String toJson(Object object) {
		if (object == null) {
			return null;
		}

		return gson.toJson(object);
	}

	public static String mergeJson(String... jsonStrs) {

		Map<String, String> summaryMap = new HashMap<String, String>();
		for (String str : jsonStrs) {
			Map<String, String> object = toObject(str, Map.class);
			summaryMap.putAll(object);
		}
		return toJson(summaryMap);
	}
}
