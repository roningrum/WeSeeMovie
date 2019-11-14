package com.roningrum.weseemovie.ui.detail;

import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.Movie;

public class DetailMovieViewModel extends ViewModel {
    private Movie movie;

    public Movie getMovie() {
        return this.movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
