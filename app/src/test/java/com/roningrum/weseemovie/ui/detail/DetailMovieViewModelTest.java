package com.roningrum.weseemovie.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.roningrum.weseemovie.data.locale.entity.Movie;
import com.roningrum.weseemovie.data.source.MovieRepository;
import com.roningrum.weseemovie.ui.utils.FakeMovieDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailMovieViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DetailMovieViewModel viewModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);
    private Movie movieDummy = FakeMovieDataDummy.getMovieDetail();
    private int movieId = movieDummy.getId();

    @Before
    public void setUp() {
        viewModel = new DetailMovieViewModel(movieRepository);
        viewModel.setMovieId(movieId);
    }

    @Test
    public void getMovieDetail() {
        MutableLiveData<Movie> movie = new MutableLiveData<>();
        movie.setValue(movieDummy);
        when(movieRepository.getMovieDetails(movieId)).thenReturn(movie);
        Observer<Movie> observer = mock(Observer.class);
        viewModel.getMovieDetail(movieId).observeForever(observer);
        verify(observer).onChanged(movieDummy);
    }

}