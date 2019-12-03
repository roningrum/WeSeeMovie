package com.roningrum.weseemovie.data.remote;

import androidx.annotation.NonNull;

import com.roningrum.weseemovie.data.locale.entity.Movie;
import com.roningrum.weseemovie.data.locale.entity.TVShow;
import com.roningrum.weseemovie.data.remote.api.ApiService;
import com.roningrum.weseemovie.data.remote.response.Constant;
import com.roningrum.weseemovie.data.remote.response.MovieResponse;
import com.roningrum.weseemovie.data.remote.response.TvResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {
    private static RemoteRepository INSTANCE;
    private ApiService apiService;

    private RemoteRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public static RemoteRepository getINSTANCE(ApiService apiService) {
        if (INSTANCE == null) {
            INSTANCE = new RemoteRepository(apiService);
        }
        return INSTANCE;
    }

    public void getAllMovies(LoadMoviesCallback loadMovieCallback) {
        Call<MovieResponse> movieResponseCall = apiService.getMovieList(Constant.language);
        movieResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(@NonNull Call<MovieResponse> call, @NonNull Response<MovieResponse> response) {
                loadMovieCallback.onAllMoviesReceive(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<MovieResponse> call, @NonNull Throwable t) {
                loadMovieCallback.onDataNotAvailable(t.getMessage());

            }
        });
    }

    public void getAllTvShow(LoadTvShowsCallback loadTvShowsCallback) {
        Call<TvResponse> tvResponseCall = apiService.getTVList(Constant.language);
        tvResponseCall.enqueue(new Callback<TvResponse>() {
            @Override
            public void onResponse(@NonNull Call<TvResponse> call, @NonNull Response<TvResponse> response) {
                loadTvShowsCallback.onAllTvShowsReceive(response.body().getResults());
            }

            @Override
            public void onFailure(@NonNull Call<TvResponse> call, @NonNull Throwable t) {
                loadTvShowsCallback.onDataNotAvailable(t.getMessage());
            }
        });
    }

    public void getMovieDetail(int movieId, LoadMovieDetailCallback loadMovieDetailCallback) {
        Call<Movie> movieCall = apiService.getMovieDetail(movieId, Constant.language);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(@NonNull Call<Movie> call, @NonNull Response<Movie> response) {
                loadMovieDetailCallback.onMovieDetailReceive(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Movie> call, @NonNull Throwable t) {
                loadMovieDetailCallback.onDataNotAvailable(t.getMessage());
            }
        });
    }

    public void getTvShowDetail(int tvId, LoadTvShowDetailCallback loadTvShowDetailCallback) {
        Call<TVShow> tvShowCall = apiService.getTvShowDetail(tvId, Constant.language);
        tvShowCall.enqueue(new Callback<TVShow>() {
            @Override
            public void onResponse(@NonNull Call<TVShow> call, @NonNull Response<TVShow> response) {
                loadTvShowDetailCallback.onTvShowDetailReceive(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TVShow> call, @NonNull Throwable t) {
                loadTvShowDetailCallback.onDataNotAvailable(t.getMessage());
            }
        });
    }

    public interface LoadMoviesCallback {
        void onAllMoviesReceive(List<Movie> movieResponse);

        void onDataNotAvailable(String message);
    }

    public interface LoadTvShowsCallback {
        void onAllTvShowsReceive(List<TVShow> tvShowResponse);

        void onDataNotAvailable(String message);
    }

    public interface LoadMovieDetailCallback {
        void onMovieDetailReceive(Movie movieDetailResponse);

        void onDataNotAvailable(String message);
    }

    public interface LoadTvShowDetailCallback {
        void onTvShowDetailReceive(TVShow tvShowDetailResponse);

        void onDataNotAvailable(String message);
    }
}
