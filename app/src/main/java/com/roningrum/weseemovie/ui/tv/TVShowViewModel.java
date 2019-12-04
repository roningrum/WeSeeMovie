package com.roningrum.weseemovie.ui.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.locale.entity.TVShow;
import com.roningrum.weseemovie.data.source.MovieRepository;

import java.util.List;

public class TVShowViewModel extends ViewModel {
    private final MovieRepository movieRepository;

    public TVShowViewModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    LiveData<List<TVShow>> getAllTvShows() {
        return movieRepository.getAllTvs();
    }
}
