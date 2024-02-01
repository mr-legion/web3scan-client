package io.algostrategy.client.web3scan.security;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static io.algostrategy.client.web3scan.constant.Web3scanApiConstants.API_KEY_PARAM;
import static io.algostrategy.client.web3scan.constant.Web3scanApiConstants.AUTHORIZATION_REQUIRED;

/**
 * A request interceptor that injects the API Key param into requests whenever required.
 */
public class AuthenticationInterceptor implements Interceptor {

    private final String apiKey;

    public AuthenticationInterceptor(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    @SuppressWarnings("NullableProblems")
    public Response intercept(Chain chain) throws IOException {

        Request original = chain.request();
        Request.Builder newRequestBuilder = original.newBuilder();

        boolean isAuthorizationRequired = original.header(AUTHORIZATION_REQUIRED) != null;
        newRequestBuilder.removeHeader(AUTHORIZATION_REQUIRED);

        // Endpoint requires authorization
        if (isAuthorizationRequired) {
            HttpUrl newUrl = original.url().newBuilder().addQueryParameter(API_KEY_PARAM, apiKey).build();
            newRequestBuilder.url(newUrl);
            newRequestBuilder.tag(String.class, apiKey);
        }

        // Build new request after adding the necessary authentication information
        Request newRequest = newRequestBuilder.build();
        return chain.proceed(newRequest);
    }
}