package com.roningrum.weseemovie.ui.movie;

import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.Movie;
import com.roningrum.weseemovie.utils.MovieDataDummy;

import java.util.List;

public class MovieViewModel extends ViewModel {
    public List<Movie> getAllMovies() {
        return MovieDataDummy.generateDummyMovies();
    }
}
