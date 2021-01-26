package com.roningrum.weseemovie.data.source;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.roningrum.weseemovie.data.source.locale.LocalRepository;
import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.data.source.locale.entity.TVShowEntity;
import com.roningrum.weseemovie.data.source.remote.NetworkBoundResource;
import com.roningrum.weseemovie.data.source.remote.RemoteRepository;
import com.roningrum.weseemovie.data.source.remote.api.ApiResponse;
import com.roningrum.weseemovie.model.Movie;
import com.roningrum.weseemovie.model.TVShow;
import com.roningrum.weseemovie.utils.AppExecutors;
import com.roningrum.weseemovie.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class FakeMovieRepository implements MovieDataSource {
    private volatile static FakeMovieRepository INSTANCE = null;
    private final LocalRepository localRepository;
    private final RemoteRepository remoteRepository;
    private final AppExecutors appExecutors;

    public FakeMovieRepository(@NonNull LocalRepository localRepository, @NonNull RemoteRepository remoteRepository, AppExecutors appExecutors) {
        this.localRepository = localRepository;
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
    }

    public static FakeMovieRepository getINSTANCE(LocalRepository localRepository, RemoteRepository remoteRepository, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (FakeMovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FakeMovieRepository(localRepository, remoteRepository, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<List<MovieEntity>>> getAllMovies() {
        return new NetworkBoundResource<List<MovieEntity>, List<Movie>>(appExecutors) {
            @Override
            protected LiveData<List<MovieEntity>> loadFromDB() {
                return localRepository.getAllMovies();
            }

            @Override
            protected Boolean shouldFetch(List<MovieEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<Movie>>> createCall() {
                return remoteRepository.getAllMovies();
            }

            @Override
            protected void saveCallResult(List<Movie> movieResponse) {
                List<MovieEntity> movieEntities = new ArrayList<>();

                for (Movie movie : movieResponse) {
                    movieEntities.add(new MovieEntity(movie.getId(),
                            movie.getBackdrop_path(),
                            movie.getOverview(),
                            movie.getPoster_path(),
                            movie.getTitle(),
                            movie.getRelease_date(),
                            movie.getVote_average(),
                            null));
                }
                localRepository.insertMovies(movieEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<MovieEntity>>> getFavoritedMoviePaged() {
        return new NetworkBoundResource<PagedList<MovieEntity>, List<Movie>>(appExecutors) {

            @Override
            protected LiveData<PagedList<MovieEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getMoviesFavs(),/* page size */ 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MovieEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<Movie>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<Movie> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MovieEntity>> getMovieDetails(int movieId) {
        return new NetworkBoundResource<MovieEntity, Movie>(appExecutors) {
            @Override
            protected LiveData<MovieEntity> loadFromDB() {
                return localRepository.getMovieDetails(movieId);
            }

            @Override
            protected Boolean shouldFetch(MovieEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<Movie>> createCall() {
                return remoteRepository.getMovieDetails(movieId);
            }

            @Override
            protected void saveCallResult(Movie data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<List<TVShowEntity>>> getAllTvs() {
        return new NetworkBoundResource<List<TVShowEntity>, List<TVShow>>(appExecutors) {

            @Override
            protected LiveData<List<TVShowEntity>> loadFromDB() {
                return localRepository.getAllTvShows();
            }

            @Override
            protected Boolean shouldFetch(List<TVShowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiResponse<List<TVShow>>> createCall() {
                return remoteRepository.getAllTvShow();
            }

            @Override
            protected void saveCallResult(List<TVShow> data) {
                List<TVShowEntity> tvShowEntities = new ArrayList<>();
                for (TVShow tvShow : data) {
                    tvShowEntities.add(new TVShowEntity(
                            tvShow.getId(),
                            tvShow.getName(),
                            tvShow.getPoster_path(),
                            tvShow.getBackdrop_path(),
                            tvShow.getOverview(),
                            tvShow.getFirst_air_date(),
                            tvShow.getVote_average(),
                            null));
                }
                localRepository.inserTvShows(tvShowEntities);
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<PagedList<TVShowEntity>>> getFavoriteTvShowPaged() {
        return new NetworkBoundResource<PagedList<TVShowEntity>, List<TVShow>>(appExecutors) {

            @Override
            protected LiveData<PagedList<TVShowEntity>> loadFromDB() {
                return new LivePagedListBuilder<>(localRepository.getTvShowFavs(), /* page size */ 20).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TVShowEntity> data) {
                return false;
            }

            @Override
            protected LiveData<ApiResponse<List<TVShow>>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(List<TVShow> data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TVShowEntity>> getTvShowDetails(int tvId) {
        return new NetworkBoundResource<TVShowEntity, TVShow>(appExecutors) {

            @Override
            protected LiveData<TVShowEntity> loadFromDB() {
                return localRepository.getTvShowetails(tvId);
            }

            @Override
            protected Boolean shouldFetch(TVShowEntity data) {
                return (data == null);
            }

            @Override
            protected LiveData<ApiResponse<TVShow>> createCall() {
                return remoteRepository.getTvShowDetails(tvId);
            }

            @Override
            protected void saveCallResult(TVShow data) {

            }
        }.asLiveData();
    }

    @Override
    public void setMovieFavBookMark(MovieEntity movie, boolean state) {
        Runnable runnable = () -> localRepository.setMovieFavorite(movie, state);

        appExecutors.diskIO().execute(runnable);
    }

    @Override
    public void setTvShowFavBookMark(TVShowEntity tvShowEntity, boolean state) {

        Runnable runnable = () -> localRepository.setTvShowsFavorite(tvShowEntity, state);

        appExecutors.diskIO().execute(runnable);
    }
}
