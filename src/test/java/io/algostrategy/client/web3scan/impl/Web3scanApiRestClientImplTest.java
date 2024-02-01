package io.algostrategy.client.web3scan.impl;

import io.algostrategy.client.web3scan.Web3scanApiClientFactory;
import io.algostrategy.client.web3scan.Web3scanApiRestClient;
import io.algostrategy.client.web3scan.domain.Response;
import io.algostrategy.client.web3scan.domain.contract.ContractABI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class Web3scanApiRestClientImplTest {

    private Web3scanApiRestClient etherscanApiRestClient;
    private Web3scanApiRestClient bscscanApiRestClient;

    @BeforeEach
    public void setUp() {
        String etherscanApiKey = System.getenv("ETHERSCAN_API_KEY");
        String bscscanApiKey = System.getenv("BSCSCAN_API_KEY");
        this.etherscanApiRestClient = Web3scanApiClientFactory.newInstance(etherscanApiKey).newEtherscanRestClient();
        this.bscscanApiRestClient = Web3scanApiClientFactory.newInstance(bscscanApiKey).newBscscanRestClient();
    }

    // Etherscan

    @Test
    public void getContractABI_ShouldReturnABI_WhenUsingEtherscan() {
        String address = "0xde342a3e269056fc3305f9e315f4c40d917ba521";
        Response<List<ContractABI>> response = etherscanApiRestClient.getContractABI(address);
        assertNotNull(response);
        assertThat(response.getData(), is(not(empty())));
    }

    // Bscscan

    @Test
    public void getContractABI_ShouldReturnABI_WhenUsingBscscan() {
        String address = "0x44ece1031e5b5e2d9169546cc10ea5c95ba96237";
        Response<List<ContractABI>> response = bscscanApiRestClient.getContractABI(address);
        assertNotNull(response);
        assertThat(response.getData(), is(not(empty())));
    }
}