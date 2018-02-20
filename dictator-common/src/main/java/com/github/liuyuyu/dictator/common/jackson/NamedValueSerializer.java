package com.github.liuyuyu.dictator.common.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.liuyuyu.dictator.common.NamedValue;

import java.io.IOException;

/**
 * @author liuyuyu
 */
public class NamedValueSerializer extends JsonSerializer<NamedValue> {

    @Override
    public void serialize(NamedValue namedValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject(namedValue);
        jsonGenerator.writeFieldName(String.valueOf(namedValue.getKey()));
        jsonGenerator.writeString(String.valueOf(namedValue.get()));
        jsonGenerator.writeEndObject();
    }

    @Override
    public Class<NamedValue> handledType() {
        return NamedValue.class;
    }
}
