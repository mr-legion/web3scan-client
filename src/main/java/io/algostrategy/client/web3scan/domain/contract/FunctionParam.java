package io.algostrategy.client.web3scan.domain.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Function param.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FunctionParam {
    private String name;
    private String type;
    private String internalType;
    private Boolean indexed;
}
