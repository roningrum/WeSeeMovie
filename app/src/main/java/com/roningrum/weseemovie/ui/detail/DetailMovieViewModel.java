package com.roningrum.weseemovie.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.roningrum.weseemovie.data.source.MovieRepository;
import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.vo.Resource;

public class DetailMovieViewModel extends ViewModel {
    private MovieRepository movieRepository;

    private MutableLiveData<Integer> movieId = new MutableLiveData<>();

    public DetailMovieViewModel(MovieRepository mMovieRepository) {
        this.movieRepository = mMovieRepository;
    }

    LiveData<Resource<MovieEntity>> detailMovies = Transformations.switchMap(movieId,
            movieId -> movieRepository.getMovieDetails(movieId));

    public int getMovieId() {
        if (movieId.getValue() == null) return 0;
        return movieId.getValue();
    }

    void setMovieId(int movieId) {
        this.movieId.setValue(movieId);
    }

    void setFavoriteMovie() {
        if (detailMovies.getValue() != null) {
            MovieEntity movieEntity = detailMovies.getValue().data;

            if (movieEntity != null) {
                final boolean newState = !movieEntity.isFavorite();
                movieRepository.setMovieFavBookMark(movieEntity, newState);
            }
        }
    }
}
