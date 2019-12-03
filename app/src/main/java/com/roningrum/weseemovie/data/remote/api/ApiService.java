package com.roningrum.weseemovie.data.remote.api;

import com.roningrum.weseemovie.BuildConfig;
import com.roningrum.weseemovie.data.locale.entity.Movie;
import com.roningrum.weseemovie.data.locale.entity.TVShow;
import com.roningrum.weseemovie.data.remote.response.MovieResponse;
import com.roningrum.weseemovie.data.remote.response.TvResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    @GET("discover/movie?api_key=" + BuildConfig.API_KEY)
    Call<MovieResponse> getMovieList(@Query("language") String language);

    @GET("discover/tv?api_key=" + BuildConfig.API_KEY)
    Call<TvResponse> getTVList(@Query("language") String language);

    @GET("movie/{movie_id}?api_key=" + BuildConfig.API_KEY)
    Call<Movie> getMovieDetail(@Path("movie_id") int Id, @Query("language") String language);

    @GET("tv/{tv_id}?api_key=" + BuildConfig.API_KEY)
    Call<TVShow> getTvShowDetail(@Path("tv_id") int Id, @Query("language") String language);

}
