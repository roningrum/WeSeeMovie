package com.roningrum.weseemovie.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.locale.entity.TVShow;
import com.roningrum.weseemovie.data.source.MovieRepository;

public class DetailTVShowViewModel extends ViewModel {
    private int tvId;
    private MovieRepository movieRepository;

    public DetailTVShowViewModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    public LiveData<TVShow> getTvShowDetail(int tvId) {
        return movieRepository.getTvShowDetails(tvId);
    }

    public int getTvId() {
        return tvId;
    }

    public void setTvId(int tvId) {
        this.tvId = tvId;
    }
}
