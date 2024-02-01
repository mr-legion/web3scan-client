package io.algostrategy.client.web3scan.constant;

import lombok.experimental.UtilityClass;

/**
 * Constants used throughout API.
 */
@UtilityClass
public class Web3scanApiConstants {

    public static final String ETHERSCAN_API_URL = "https://api.etherscan.io";
    public static final String BSCSCAN_API_URL = "https://api.bscscan.com";

    /**
     * HTTP request param to be used for API-KEY authentication.
     */
    public static final String API_KEY_PARAM = "apikey";

    /**
     * Decorator to indicate that an endpoint requires authorization.
     */
    public static final String AUTHORIZATION_REQUIRED = "AUTHORIZATION";
    public static final String AUTHORIZATION_REQUIRED_PARAM = AUTHORIZATION_REQUIRED + ": #";
}
