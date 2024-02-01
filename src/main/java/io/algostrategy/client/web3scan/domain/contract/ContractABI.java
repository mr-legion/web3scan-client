package io.algostrategy.client.web3scan.domain.contract;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Contract ABI.
 */
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractABI {
    private String name;
    private String type;
    private String stateMutability;
    private List<FunctionParam> inputs;
    private List<FunctionParam> outputs;
}
