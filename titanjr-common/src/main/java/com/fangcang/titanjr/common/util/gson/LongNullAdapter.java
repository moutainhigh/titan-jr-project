package com.fangcang.titanjr.common.util.gson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class LongNullAdapter extends TypeAdapter<Long> {
	@Override
    public Long read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        return reader.nextLong();
    }
    @Override
    public void write(JsonWriter writer, Long value) throws IOException {
        if (value == null) {
            writer.value("");
            return;
        }
        writer.value(value);
    }
}
