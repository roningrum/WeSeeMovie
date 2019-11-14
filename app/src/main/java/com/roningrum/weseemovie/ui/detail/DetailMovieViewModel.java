package com.roningrum.weseemovie.ui.detail;

import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.Movie;
import com.roningrum.weseemovie.utils.MovieDataDummy;

public class DetailMovieViewModel extends ViewModel {
    private Movie movie;

    public Movie getMovie() {
        for (int i = 0; i < MovieDataDummy.generateDummyMovies().size(); i++) {
            movie = MovieDataDummy.generateDummyMovies().get(i);
        }
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
