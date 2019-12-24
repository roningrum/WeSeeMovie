package com.roningrum.weseemovie.data.source.locale;

import androidx.lifecycle.LiveData;

import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.data.source.locale.entity.TVShowEntity;
import com.roningrum.weseemovie.data.source.locale.room.WeSeeMovieDao;

import java.util.List;

public class LocalRepository {
    private static LocalRepository INSTANCE;
    private final WeSeeMovieDao weSeeMovieDao;

    public LocalRepository(WeSeeMovieDao weSeeMovieDao) {
        this.weSeeMovieDao = weSeeMovieDao;
    }

    public static LocalRepository getINSTANCE(WeSeeMovieDao weSeeMovieDao) {
        if (INSTANCE == null) {
            INSTANCE = new LocalRepository(weSeeMovieDao);
        }
        return INSTANCE;
    }

    public LiveData<List<MovieEntity>> getAllMovies() {
        return weSeeMovieDao.getMovies();
    }

    public LiveData<MovieEntity> getMovieDetails(int id) {
        return weSeeMovieDao.getDetailMovie(id);
    }

    public LiveData<List<TVShowEntity>> getAllTvShows() {
        return weSeeMovieDao.getTvShows();
    }

    public LiveData<TVShowEntity> getTvShowetails(int id) {
        return weSeeMovieDao.getDetailTvShow(id);
    }

    public LiveData<List<MovieEntity>> getMoviesFavs(int movieId) {
        return weSeeMovieDao.getFavoriteMovies(movieId);
    }

    public LiveData<List<TVShowEntity>> getTvShowFavs(int tvShowId) {
        return weSeeMovieDao.getFavoriteTvShows(tvShowId);
    }

    public void insertMovies(List<MovieEntity> movies) {
        weSeeMovieDao.insertMovies(movies);
    }

    public void inserTvShows(List<TVShowEntity> tvShows) {
        weSeeMovieDao.insertTvShows(tvShows);
    }

    public void setMovieFavorite(MovieEntity movies, boolean newState) {
        movies.setFavorite(newState);
        weSeeMovieDao.updateMovie(movies);
    }

    public void setTvShowsFavorite(TVShowEntity tvShowEntity, boolean newState) {
        tvShowEntity.setFavorite(newState);
        weSeeMovieDao.updateTvShow(tvShowEntity);
    }
}
