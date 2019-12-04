package com.roningrum.weseemovie.ui.tv;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.roningrum.weseemovie.data.locale.entity.TVShow;
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

public class TVShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TVShowViewModel tvShowViewModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);

    @Before
    public void setUp() {
        tvShowViewModel = new TVShowViewModel(movieRepository);
    }

    @Test
    public void getTvShows() {
        ArrayList<TVShow> dummyTvShows = FakeMovieDataDummy.generateDummyTVShow();
        MutableLiveData<List<TVShow>> tvshows = new MutableLiveData<>();
        tvshows.setValue(dummyTvShows);
        when(movieRepository.getAllTvs()).thenReturn(tvshows);

        Observer<List<TVShow>> observer = mock(Observer.class);
        tvShowViewModel.getAllTvShows().observeForever(observer);
        verify(observer).onChanged(dummyTvShows);

    }

}