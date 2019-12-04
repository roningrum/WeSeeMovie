package com.roningrum.weseemovie.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.locale.entity.Movie;
import com.roningrum.weseemovie.data.source.MovieRepository;

public class DetailMovieViewModel extends ViewModel {
    private int movieId;
    private final MovieRepository movieRepository;

    public DetailMovieViewModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    LiveData<Movie> getMovieDetail(int movieId) {
        return movieRepository.getMovieDetails(movieId);
    }

    public int getMovieId() {
        return movieId;
    }

    void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
