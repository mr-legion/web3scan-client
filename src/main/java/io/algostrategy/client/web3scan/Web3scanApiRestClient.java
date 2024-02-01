package io.algostrategy.client.web3scan;

import io.algostrategy.client.web3scan.domain.Response;
import io.algostrategy.client.web3scan.domain.contract.ContractABI;

import java.util.List;

/**
 * The API facade, supporting synchronous/blocking access REST API.
 */
public interface Web3scanApiRestClient {

    // Contract endpoints

    /**
     * Get contract ABI.
     *
     * @param address of contract
     * @return list of ABI
     */
    Response<List<ContractABI>> getContractABI(String address);
}