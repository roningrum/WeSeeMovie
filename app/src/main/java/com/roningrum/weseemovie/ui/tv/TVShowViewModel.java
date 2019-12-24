package com.roningrum.weseemovie.ui.tv;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.source.MovieRepository;
import com.roningrum.weseemovie.data.source.locale.entity.TVShowEntity;
import com.roningrum.weseemovie.vo.Resource;

import java.util.List;

public class TVShowViewModel extends ViewModel {
    private MovieRepository movieRepository;
    private MutableLiveData<String> mLogin = new MutableLiveData<>();

    public TVShowViewModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    LiveData<Resource<List<TVShowEntity>>> tvShows = Transformations.switchMap(mLogin,
            data -> movieRepository.getAllTvs());

    void setUserName(String username) {
        mLogin.setValue(username);
    }
}
