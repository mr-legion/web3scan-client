package io.algostrategy.client.web3scan.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.algostrategy.client.web3scan.Web3scanApiRestClient;
import io.algostrategy.client.web3scan.domain.Response;
import io.algostrategy.client.web3scan.domain.contract.ContractABI;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.util.List;

import static io.algostrategy.client.web3scan.impl.Web3scanApiServiceGenerator.executeSync;

/**
 * Implementation of REST API using Retrofit with synchronous/blocking method calls.
 */
@AllArgsConstructor
public class Web3scanApiRestClientImpl implements Web3scanApiRestClient {

    private static final ObjectMapper mapper = new ObjectMapper();

    private final Web3scanApiService bscscanApiService;

    // Contract endpoints

    @SneakyThrows
    @Override
    public Response<List<ContractABI>> getContractABI(String address) {
        Response<String> response = executeSync(bscscanApiService.getContractABI(address));
        List<ContractABI> contractABI = mapper.readValue(response.getData(), new TypeReference<List<ContractABI>>() {});
        return new Response<>(response.getStatus(), response.getMessage(), contractABI);
    }
}
