package io.algostrategy.client.web3scan.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import io.algostrategy.client.web3scan.Web3scanApiAsyncRestClient;
import io.algostrategy.client.web3scan.domain.Response;
import io.algostrategy.client.web3scan.domain.contract.ContractABI;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import static io.algostrategy.client.web3scan.impl.Web3scanApiServiceGenerator.readResponse;

/**
 * Implementation of REST API using Retrofit with asynchronous/non-blocking method calls.
 */
@AllArgsConstructor
public class Web3scanApiAsyncRestClientImpl implements Web3scanApiAsyncRestClient {

    private final Web3scanApiService web3scanApiService;

    // Contract endpoints

    @Override
    public CompletableFuture<List<ContractABI>> getContractABI(String address) {
        CompletableFuture<Response<String>> future = new CompletableFuture<>();
        web3scanApiService.getContractABI(address).enqueue(new RetrofitCallbackAdapter<>(future));
        return future.thenApply(response -> readResponse(response, new TypeReference<List<ContractABI>>() {}));
    }
}
