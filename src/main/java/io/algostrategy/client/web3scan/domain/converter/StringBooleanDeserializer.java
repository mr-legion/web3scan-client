package io.algostrategy.client.web3scan.domain.converter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Deserialize string boolean.
 */
public class StringBooleanDeserializer extends JsonDeserializer<Boolean> {

    @Override
    public Boolean deserialize(JsonParser jp, DeserializationContext ctx) throws IOException {
        return jp.getText() != null ? jp.getText().equals("1") : null;
    }
}
