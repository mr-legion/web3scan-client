package io.algostrategy.client.web3scan.impl;

import io.algostrategy.client.web3scan.exception.Web3scanApiException;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.concurrent.CompletableFuture;

/**
 * An adapter/wrapper that transform a response to the {@link CompletableFuture}.
 */
public class RetrofitCallbackAdapter<T> implements Callback<T> {

    private final CompletableFuture<T> future;

    public RetrofitCallbackAdapter(CompletableFuture<T> future) {
        this.future = future;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        try {
            future.complete(response.body());
        } catch (Exception e) {
            onFailure(call, e);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        future.completeExceptionally(new Web3scanApiException(t));
    }
}
