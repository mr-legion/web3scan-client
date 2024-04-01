package io.algostrategy.client.web3scan.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.algostrategy.client.web3scan.domain.Response;
import io.algostrategy.client.web3scan.exception.Web3scanApiException;
import io.algostrategy.client.web3scan.security.AuthenticationInterceptor;
import lombok.SneakyThrows;
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

    public static <S> S createService(String apiUrl, String apiKey, OkHttpClient client, Class<S> serviceClass) {

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(apiUrl)
                .addConverterFactory(converterFactory);

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

    @SneakyThrows
    public static <T> T readResponse(Response<String> response, TypeReference<T> valueTypeRef) {
        if (!response.getStatus()) throw new Web3scanApiException(response.getData());
        return mapper.readValue(response.getData(), valueTypeRef);
    }
}
