package com.roningrum.weseemovie.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.roningrum.weseemovie.data.locale.entity.Movie;
import com.roningrum.weseemovie.data.source.MovieRepository;
import com.roningrum.weseemovie.ui.utils.FakeMovieDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel movieViewModel;
    private final MovieRepository movieRepository = mock(MovieRepository.class);

    @Before
    public void setUp() {
        movieViewModel = new MovieViewModel(movieRepository);
    }

    @Test
    public void getMovies() {
        ArrayList<Movie> dummyMovies = FakeMovieDataDummy.generateDummyMovies();
        MutableLiveData<List<Movie>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);
        when(movieRepository.getAllMovies()).thenReturn(movies);
        Observer<List<Movie>> observer = mock(Observer.class);
        movieViewModel.getAllMovies().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }

}