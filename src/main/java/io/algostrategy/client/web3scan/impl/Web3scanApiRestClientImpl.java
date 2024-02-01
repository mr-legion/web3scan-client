package io.algostrategy.client.web3scan.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import io.algostrategy.client.web3scan.Web3scanApiRestClient;
import io.algostrategy.client.web3scan.domain.Response;
import io.algostrategy.client.web3scan.domain.contract.ContractABI;
import lombok.AllArgsConstructor;

import java.util.List;

import static io.algostrategy.client.web3scan.impl.Web3scanApiServiceGenerator.executeSync;
import static io.algostrategy.client.web3scan.impl.Web3scanApiServiceGenerator.readResponse;

/**
 * Implementation of REST API using Retrofit with synchronous/blocking method calls.
 */
@AllArgsConstructor
public class Web3scanApiRestClientImpl implements Web3scanApiRestClient {

    private final Web3scanApiService bscscanApiService;

    // Contract endpoints

    @Override
    public List<ContractABI> getContractABI(String address) {
        Response<String> response = executeSync(bscscanApiService.getContractABI(address));
        return readResponse(response, new TypeReference<List<ContractABI>>() {});
    }
}
