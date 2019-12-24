package com.roningrum.weseemovie.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.roningrum.weseemovie.data.source.MovieRepository;
import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.vo.Resource;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private MovieRepository movieRepository;

    private MutableLiveData<String> mLogin = new MutableLiveData<>();

    public MovieViewModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    LiveData<Resource<List<MovieEntity>>> movies = Transformations.switchMap(mLogin,
            data -> movieRepository.getAllMovies());

    public LiveData<Resource<PagedList<MovieEntity>>> getFavoritedMoviePaged() {
        return movieRepository.getFavoritedMoviePaged();
    }

    void setUserName(String username) {
        mLogin.setValue(username);
    }

    public void setFavorite(MovieEntity movieEntity) {
        final boolean newState = !movieEntity.isFavorite();
        movieRepository.setMovieFavBookMark(movieEntity, newState);
    }
}
