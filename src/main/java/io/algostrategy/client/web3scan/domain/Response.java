package io.algostrategy.client.web3scan.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response wrapper.
 *
 * @param <T> payload type
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {

    @JsonSerialize(using = StringBooleanSerializer.class)
    @JsonDeserialize(using = StringBooleanDeserializer.class)
    private Boolean status;

    private String message;

    @JsonProperty("result")
    private T data;
}
