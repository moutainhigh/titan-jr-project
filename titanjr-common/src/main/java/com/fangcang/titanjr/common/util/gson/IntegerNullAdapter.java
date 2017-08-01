package com.fangcang.titanjr.common.util.gson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class IntegerNullAdapter extends TypeAdapter<Integer> {
	@Override
    public Integer read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        return reader.nextInt();
    }
    @Override
    public void write(JsonWriter writer, Integer value) throws IOException {
        if (value == null) {
            writer.value("");
            return;
        }
        writer.value(value);
    }
}
