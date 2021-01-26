package com.roningrum.weseemovie.data.source.remote.api;

import com.roningrum.weseemovie.BuildConfig;
import com.roningrum.weseemovie.data.source.remote.response.MovieResponse;
import com.roningrum.weseemovie.data.source.remote.response.TvResponse;
import com.roningrum.weseemovie.model.Movie;
import com.roningrum.weseemovie.model.TVShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("discover/movie?api_key=" + BuildConfig.API_KEY)
    Call<MovieResponse> getMovieList(@Query("language") String language);

    @GET("discover/tv?api_key=" + BuildConfig.API_KEY)
    Call<TvResponse> getTVList(@Query("language") String language);

    @GET("movie/{movieId}?api_key=" + BuildConfig.API_KEY)
    Call<Movie> getMovieDetail(@Path("movieId") int Id, @Query("language") String language);

    @GET("tv/{tvId}?api_key=" + BuildConfig.API_KEY)
    Call<TVShow> getTvShowDetail(@Path("tvId") int Id, @Query("language") String language);

}
