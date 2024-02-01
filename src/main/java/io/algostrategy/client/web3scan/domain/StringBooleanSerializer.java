package io.algostrategy.client.web3scan.domain;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * Serialize string boolean.
 */
public class StringBooleanSerializer extends JsonSerializer<Boolean> {

    @Override
    public void serialize(Boolean value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String booleanStr = null;
        if (value != null) {
            booleanStr = value ? "1" : "0";
        }
        gen.writeString(booleanStr);
    }
}
