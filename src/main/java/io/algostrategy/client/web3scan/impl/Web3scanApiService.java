package io.algostrategy.client.web3scan.impl;

import io.algostrategy.client.web3scan.domain.Response;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

import static io.algostrategy.client.web3scan.constant.Web3scanApiConstants.AUTHORIZATION_REQUIRED_PARAM;

/**
 * REST API URL mappings.
 */
public interface Web3scanApiService {

    // Contract endpoints

    @Headers(AUTHORIZATION_REQUIRED_PARAM)
    @GET("/api?module=contract&action=getabi")
    Call<Response<String>> getContractABI(@Query(value = "address") String address);
}
