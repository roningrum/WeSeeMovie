package com.roningrum.weseemovie.data.source.remote.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.roningrum.weseemovie.data.source.remote.StatusResponse;

import static com.roningrum.weseemovie.data.source.remote.StatusResponse.EMPTY;
import static com.roningrum.weseemovie.data.source.remote.StatusResponse.ERROR;
import static com.roningrum.weseemovie.data.source.remote.StatusResponse.SUCCESS;

public class ApiResponse<T> {

    @NonNull
    public final StatusResponse status;

    @Nullable
    public final String message;

    @Nullable
    public final T body;

    ApiResponse(@NonNull StatusResponse status, @Nullable T body, @Nullable String message) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public static <T> ApiResponse<T> success(@Nullable T body) {
        return new ApiResponse<>(SUCCESS, body, null);
    }

    public static <T> ApiResponse<T> empty(String msg, @Nullable T body) {
        return new ApiResponse<>(EMPTY, body, msg);
    }

    public static <T> ApiResponse<T> error(String msg, @Nullable T body) {
        return new ApiResponse<>(ERROR, body, null);
    }

}
