package com.roningrum.weseemovie.data.source.locale.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.data.source.locale.entity.TVShowEntity;

import java.util.List;

@Dao
public interface WeSeeMovieDao {

    @WorkerThread
    @Query("SELECT * FROM Movies")
    LiveData<List<MovieEntity>> getMovies();

    @WorkerThread
    @Query("SELECT * FROM TvShows")
    LiveData<List<TVShowEntity>> getTvShows();

    @Query("SELECT * FROM Movies WHERE id = :movieId")
    LiveData<MovieEntity> getDetailMovie(int movieId);

    @Query("SELECT * FROM TvShows WHERE id = :tvShowId")
    LiveData<TVShowEntity> getDetailTvShow(int tvShowId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertMovies(List<MovieEntity> movies);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertTvShows(List<TVShowEntity> tvShows);

    @WorkerThread
    @Query("SELECT * FROM Movies WHERE favorite = 1")
    DataSource.Factory<Integer, MovieEntity> getFavoriteMovies();

    @WorkerThread
    @Query("SELECT * FROM TvShows WHERE favorite = 1")
    DataSource.Factory<Integer, TVShowEntity> getFavoriteTvShows();

    @Update
    int updateTvShow(TVShowEntity tvShowEntity);

    @Update
    int updateMovie(MovieEntity movieEntity);

}
