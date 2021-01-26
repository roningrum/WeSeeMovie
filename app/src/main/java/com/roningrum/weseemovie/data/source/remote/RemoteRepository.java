package com.roningrum.weseemovie.data.source.remote;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.roningrum.weseemovie.data.source.remote.api.ApiResponse;
import com.roningrum.weseemovie.data.source.remote.api.ApiService;
import com.roningrum.weseemovie.data.source.remote.response.Constant;
import com.roningrum.weseemovie.data.source.remote.response.MovieResponse;
import com.roningrum.weseemovie.data.source.remote.response.TvResponse;
import com.roningrum.weseemovie.model.Movie;
import com.roningrum.weseemovie.model.TVShow;
import com.roningrum.weseemovie.utils.EspressoIdlingResource;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private final ApiService apiService;
    private final long SERVICE_LATENCY_IN_MILLIS = 2000;

    private RemoteRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public static RemoteRepository getINSTANCE(ApiService apiService) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(apiService);
        }
        return INSTANCE;
    }

    public LiveData<ApiResponse<List<Movie>>> getAllMovies() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<Movie>>> resultMovie = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Call<MovieResponse> movieResponseCall = apiService.getMovieList(Constant.language);
            movieResponseCall.enqueue(new Callback<MovieResponse>() {
                @Override
                public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                    assert response.body() != null;
                    resultMovie.setValue(ApiResponse.success(response.body().getResults()));
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                }
            });
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultMovie;
    }

    public LiveData<ApiResponse<List<TVShow>>> getAllTvShow() {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<List<TVShow>>> resultTvShow = new MutableLiveData<>();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Call<TvResponse> tvResponseCall = apiService.getTVList(Constant.language);
            tvResponseCall.enqueue(new Callback<TvResponse>() {
                @Override
                public void onResponse(@NonNull Call<TvResponse> call, @NonNull Response<TvResponse> response) {
                    assert response.body() != null;
                    resultTvShow.setValue(ApiResponse.success(response.body().getResults()));
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<TvResponse> call, @NonNull Throwable t) {
                }
            });
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultTvShow;
    }

    public LiveData<ApiResponse<Movie>> getMovieDetails(int movieId) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<Movie>> resultMovieDetail = new MutableLiveData<>();
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Call<Movie> movieCall = apiService.getMovieDetail(movieId, Constant.language);
            movieCall.enqueue(new Callback<Movie>() {
                @Override
                public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                    resultMovieDetail.setValue(ApiResponse.success(response.body()));

                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
//                    loadMovieDetailCallback.onDataNotAvailable(t.getMessage());
                }
            });
        }, SERVICE_LATENCY_IN_MILLIS);

        return resultMovieDetail;
    }


    public LiveData<ApiResponse<TVShow>> getTvShowDetails(int tvId) {
        EspressoIdlingResource.increment();
        MutableLiveData<ApiResponse<TVShow>> resultTvShowDetails = new MutableLiveData<>();

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Call<TVShow> tvShowCall = apiService.getTvShowDetail(tvId, Constant.language);
            tvShowCall.enqueue(new Callback<TVShow>() {
                @Override
                public void onResponse(@NonNull Call<TVShow> call, @NonNull Response<TVShow> response) {
                    resultTvShowDetails.setValue(ApiResponse.success(response.body()));
                    if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<TVShow> call, @NonNull Throwable t) {

                }
            });
        }, SERVICE_LATENCY_IN_MILLIS);
        return resultTvShowDetails;
    }
}

