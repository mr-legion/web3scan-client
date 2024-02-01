package io.algostrategy.client.web3scan.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.algostrategy.client.web3scan.exception.Web3scanApiException;
import io.algostrategy.client.web3scan.security.AuthenticationInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;

/**
 * Generates a Bscscan API implementation based on @see {@link Web3scanApiService}.
 */
public class Web3scanApiServiceGenerator {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final Converter.Factory converterFactory = JacksonConverterFactory.create(mapper);

    static {
        mapper.enable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL);
    }

    public static <S> S createService(String apiUrl, Class<S> serviceClass, OkHttpClient client, String apiKey) {

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(converterFactory);

        // `adaptedClient` will use its own interceptor, but share thread pool etc. with the 'parent' client
        AuthenticationInterceptor authInterceptor = new AuthenticationInterceptor(apiKey);
        OkHttpClient.Builder newBuilder = client.newBuilder();
        newBuilder.interceptors().add(0, authInterceptor);
        OkHttpClient adaptedClient = newBuilder.build();
        retrofitBuilder.client(adaptedClient);

        Retrofit retrofit = retrofitBuilder.build();
        return retrofit.create(serviceClass);
    }

    /**
     * Execute a REST call and block until the response is received.
     */
    public static <T> T executeSync(Call<T> call) {
        try {
            return call.execute().body();
        } catch (IOException e) {
            throw new Web3scanApiException(e);
        }
    }
}