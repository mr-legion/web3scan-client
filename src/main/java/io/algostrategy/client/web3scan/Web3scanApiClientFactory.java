package io.algostrategy.client.web3scan;

import io.algostrategy.client.web3scan.impl.Web3scanApiRestClientImpl;
import io.algostrategy.client.web3scan.impl.Web3scanApiService;
import okhttp3.OkHttpClient;

import static io.algostrategy.client.web3scan.constant.Web3scanApiConstants.BSCSCAN_API_URL;
import static io.algostrategy.client.web3scan.constant.Web3scanApiConstants.ETHERSCAN_API_URL;
import static io.algostrategy.client.web3scan.impl.Web3scanApiServiceGenerator.createService;

/**
 * A factory for creating API client objects.
 */
public class Web3scanApiClientFactory {

    private final OkHttpClient client;
    private final String apiKey;

    public Web3scanApiClientFactory(String apiKey) {
        this(new OkHttpClient(), apiKey);
    }

    public Web3scanApiClientFactory(OkHttpClient client, String apiKey) {
        this.client = client;
        this.apiKey = apiKey;
    }

    /**
     * New instance with authentication.
     *
     * @return the API client factory
     */
    public static Web3scanApiClientFactory newInstance(String apiKey) {
        return new Web3scanApiClientFactory(apiKey);
    }

    /**
     * Creates a new synchronous/blocking etherscan REST client.
     */
    public Web3scanApiRestClient newEtherscanRestClient() {
        Web3scanApiService service = createService(ETHERSCAN_API_URL, Web3scanApiService.class, client, apiKey);
        return new Web3scanApiRestClientImpl(service);
    }

    /**
     * Creates a new synchronous/blocking bscscan REST client.
     */
    public Web3scanApiRestClient newBscscanRestClient() {
        Web3scanApiService service = createService(BSCSCAN_API_URL, Web3scanApiService.class, client, apiKey);
        return new Web3scanApiRestClientImpl(service);
    }
}
