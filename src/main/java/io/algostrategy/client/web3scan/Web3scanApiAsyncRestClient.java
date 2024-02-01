package io.algostrategy.client.web3scan;

import io.algostrategy.client.web3scan.domain.contract.ContractABI;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * The API facade, supporting asynchronous/non-blocking access Gate's REST API.
 */
public interface Web3scanApiAsyncRestClient {

    // Contract endpoints

    /**
     * Get contract ABI (asynchronous).
     *
     * @param address of contract
     * @return list of ABI
     */
    CompletableFuture<List<ContractABI>> getContractABI(String address);
}