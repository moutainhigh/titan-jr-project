package com.fangcang.titanjr.common.util.gson;

import com.fangcang.titanjr.common.util.gson.DoubleNullAdapter;
import com.fangcang.titanjr.common.util.gson.IntegerNullAdapter;
import com.fangcang.titanjr.common.util.gson.LongNullAdapter;
import com.fangcang.titanjr.common.util.gson.StringNullAdapter;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class NullStringToEmptyAdapterFactory implements TypeAdapterFactory {

	@SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<T> rawType = (Class<T>) type.getRawType();
        if(rawType == String.class){
        	return (TypeAdapter<T>) new StringNullAdapter();
        }
        if(rawType == Integer.class){
        	return (TypeAdapter<T>) new IntegerNullAdapter();
        }
        if(rawType == Long.class){
        	return (TypeAdapter<T>) new LongNullAdapter();
        }
        if(rawType == Float.class||rawType == Double.class){
        	return (TypeAdapter<T>) new DoubleNullAdapter();
        }
        //其他类型不转
        return null;
    }
}
