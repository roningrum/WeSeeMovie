package com.roningrum.weseemovie.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.roningrum.weseemovie.data.source.MovieRepository;
import com.roningrum.weseemovie.di.Injection;
import com.roningrum.weseemovie.ui.detail.DetailMovieViewModel;
import com.roningrum.weseemovie.ui.detail.DetailTVShowViewModel;
import com.roningrum.weseemovie.ui.movie.MovieViewModel;
import com.roningrum.weseemovie.ui.tv.TVShowViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private static volatile ViewModelFactory INSTANCE;

    private final MovieRepository movieRepository;

    private ViewModelFactory(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public static ViewModelFactory getInstance(Application application) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(Injection.provideRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(TVShowViewModel.class)) {
            return (T) new TVShowViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(DetailMovieViewModel.class)) {
            return (T) new DetailMovieViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(DetailTVShowViewModel.class)) {
            return (T) new DetailTVShowViewModel(movieRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass);
    }
}
