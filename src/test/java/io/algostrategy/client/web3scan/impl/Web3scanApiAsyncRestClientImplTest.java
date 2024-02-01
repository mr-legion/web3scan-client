package io.algostrategy.client.web3scan.impl;

import io.algostrategy.client.web3scan.Web3scanApiAsyncRestClient;
import io.algostrategy.client.web3scan.Web3scanApiClientFactory;
import io.algostrategy.client.web3scan.domain.contract.ContractABI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Web3scanApiAsyncRestClientImplTest {

    private Web3scanApiAsyncRestClient etherscanApiAsyncRestClient;
    private Web3scanApiAsyncRestClient bscscanApiAsyncRestClient;

    @BeforeEach
    public void setUp() {
        String etherscanApiKey = System.getenv("ETHERSCAN_API_KEY");
        String bscscanApiKey = System.getenv("BSCSCAN_API_KEY");
        this.etherscanApiAsyncRestClient = Web3scanApiClientFactory.newInstance(etherscanApiKey).newEtherscanAsyncRestClient();
        this.bscscanApiAsyncRestClient = Web3scanApiClientFactory.newInstance(bscscanApiKey).newBscscanAsyncRestClient();
    }

    // Etherscan

    @Test
    public void getContractABI_ShouldReturnABI_WhenUsingEtherscan() throws ExecutionException, InterruptedException {
        String address = "0xde342a3e269056fc3305f9e315f4c40d917ba521";
        List<ContractABI> abis = etherscanApiAsyncRestClient.getContractABI(address).get();
        assertThat(abis, is(not(empty())));
    }

    // Bscscan

    @Test
    public void getContractABI_ShouldReturnABI_WhenUsingBscscan() throws ExecutionException, InterruptedException {
        String address = "0x44ece1031e5b5e2d9169546cc10ea5c95ba96237";
        List<ContractABI> abis = bscscanApiAsyncRestClient.getContractABI(address).get();
        assertThat(abis, is(not(empty())));
    }
}