package com.roningrum.weseemovie.data.source;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.roningrum.weseemovie.data.locale.entity.Movie;
import com.roningrum.weseemovie.data.locale.entity.TVShow;
import com.roningrum.weseemovie.data.remote.RemoteRepository;

import java.util.List;

public class MovieRepository implements MovieDataSource {
    private volatile static MovieRepository INSTANCE = null;
    private final RemoteRepository remoteRepository;

    private MovieRepository(RemoteRepository remoteRepository) {
        this.remoteRepository = remoteRepository;
    }

    public static MovieRepository getINSTANCE(RemoteRepository remoteRepository) {
        if (INSTANCE == null) {
            synchronized (MovieRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MovieRepository(remoteRepository);
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public LiveData<List<Movie>> getAllMovies() {
        MutableLiveData<List<Movie>> movieResults = new MutableLiveData<>();
        remoteRepository.getAllMovies(new RemoteRepository.LoadMoviesCallback() {
            @Override
            public void onAllMoviesReceive(List<Movie> movieResponse) {
                movieResults.postValue(movieResponse);
            }

            @Override
            public void onDataNotAvailable(String message) {
                Log.e("Data Error", "" + message);
            }
        });
        return movieResults;
    }

    @Override
    public LiveData<Movie> getMovieDetails(int movieId) {
        MutableLiveData<Movie> movieResults = new MutableLiveData<>();
        remoteRepository.getMovieDetail(movieId, new RemoteRepository.LoadMovieDetailCallback() {
            @Override
            public void onMovieDetailReceive(Movie movieDetailResponse) {
                movieResults.postValue(movieDetailResponse);
            }

            @Override
            public void onDataNotAvailable(String message) {
                Log.e("Data Error", "" + message);
            }
        });
        return movieResults;
    }

    @Override
    public LiveData<List<TVShow>> getAllTvs() {
        MutableLiveData<List<TVShow>> tvResults = new MutableLiveData<>();
        remoteRepository.getAllTvShow(new RemoteRepository.LoadTvShowsCallback() {
            @Override
            public void onAllTvShowsReceive(List<TVShow> tvShowResponse) {
                tvResults.postValue(tvShowResponse);
            }

            @Override
            public void onDataNotAvailable(String message) {
                Log.e("Error message", "" + message);
            }
        });
        return tvResults;
    }

    @Override
    public LiveData<TVShow> getTvShowDetails(int tvId) {
        MutableLiveData<TVShow> tvDetailResults = new MutableLiveData<>();
        remoteRepository.getTvShowDetail(tvId, new RemoteRepository.LoadTvShowDetailCallback() {
            @Override
            public void onTvShowDetailReceive(TVShow tvShowDetailResponse) {
                tvDetailResults.postValue(tvShowDetailResponse);
            }

            @Override
            public void onDataNotAvailable(String message) {
                Log.e("Data Error", "" + message);
            }
        });
        return tvDetailResults;
    }


}
