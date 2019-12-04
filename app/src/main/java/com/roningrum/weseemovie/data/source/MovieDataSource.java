package com.roningrum.weseemovie.data.source;

import androidx.lifecycle.LiveData;

import com.roningrum.weseemovie.data.locale.entity.Movie;
import com.roningrum.weseemovie.data.locale.entity.TVShow;

import java.util.List;

interface MovieDataSource {

    LiveData<List<Movie>> getAllMovies();

    LiveData<Movie> getMovieDetails(int movieId);

    LiveData<List<TVShow>> getAllTvs();

    LiveData<TVShow> getTvShowDetails(int tvId);

}
