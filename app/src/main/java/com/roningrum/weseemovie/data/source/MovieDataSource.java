package com.roningrum.weseemovie.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.data.source.locale.entity.TVShowEntity;
import com.roningrum.weseemovie.vo.Resource;

import java.util.List;

interface MovieDataSource {

    LiveData<Resource<List<MovieEntity>>> getAllMovies();

    LiveData<Resource<PagedList<MovieEntity>>> getFavoritedMoviePaged();

    LiveData<Resource<MovieEntity>> getMovieDetails(int movieId);

    LiveData<Resource<List<TVShowEntity>>> getAllTvs();

    LiveData<Resource<PagedList<TVShowEntity>>> getFavoriteTvShowPaged();

    LiveData<Resource<TVShowEntity>> getTvShowDetails(int tvId);

    void setMovieFavBookMark(MovieEntity movie, boolean state);

    void setTvShowFavBookMark(TVShowEntity tvShowEntity, boolean state);

}
