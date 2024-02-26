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

    /**
     * Creates a new synchronous/blocking etherscan REST client.
     */
    public static Web3scanApiRestClient newEtherscanRestClient(String apiKey) {
        return newEtherscanRestClient(apiKey, new OkHttpClient());
    }

    /**
     * Creates a new synchronous/blocking etherscan REST client.
     */
    public static Web3scanApiRestClient newEtherscanRestClient(String apiKey, OkHttpClient client) {
        Web3scanApiService service = createService(ETHERSCAN_API_URL, apiKey, client, Web3scanApiService.class);
        return new Web3scanApiRestClientImpl(service);
    }

    /**
     * Creates a new synchronous/blocking bscscan REST client.
     */
    public static Web3scanApiRestClient newBscscanRestClient(String apiKey) {
        return newBscscanRestClient(apiKey, new OkHttpClient());
    }

    /**
     * Creates a new synchronous/blocking bscscan REST client.
     */
    public static Web3scanApiRestClient newBscscanRestClient(String apiKey, OkHttpClient client) {
        Web3scanApiService service = createService(BSCSCAN_API_URL, apiKey, client, Web3scanApiService.class);
        return new Web3scanApiRestClientImpl(service);
    }
}
