package com.fangcang.titanjr.common.util.gson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class DoubleNullAdapter extends TypeAdapter<Double> {
	@Override
    public Double read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
            reader.nextNull();
            return null;
        }
        return reader.nextDouble();
    }
    @Override
    public void write(JsonWriter writer, Double value) throws IOException {
        if (value == null) {
            writer.value("");
            return;
        }
        writer.value(value);
    }
}
