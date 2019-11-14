package com.roningrum.weseemovie.ui.detail;

import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.Movie;
import com.roningrum.weseemovie.utils.MovieDataDummy;

public class DetailMovieViewModel extends ViewModel {
    private Movie movie;

    public Movie getMovie() {
        movie = MovieDataDummy.getMovie();
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
