package com.roningrum.weseemovie.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.locale.entity.Movie;
import com.roningrum.weseemovie.data.source.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private MovieRepository movieRepository;

    public MovieViewModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    LiveData<List<Movie>> getAllMovies() {
        return movieRepository.getAllMovies();
    }
}
