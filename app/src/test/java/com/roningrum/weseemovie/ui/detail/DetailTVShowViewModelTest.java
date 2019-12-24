package com.roningrum.weseemovie.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.roningrum.weseemovie.data.source.MovieRepository;
import com.roningrum.weseemovie.model.TVShow;
import com.roningrum.weseemovie.ui.utils.FakeMovieDataDummy;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailTVShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private DetailTVShowViewModel viewModel;
    private final MovieRepository movieRepository = mock(MovieRepository.class);
    private final TVShow tvShowDummy = FakeMovieDataDummy.getTvShowsDetail();
    private final int tvshowId = tvShowDummy.getId();

    @Before
    public void setUp() {
        viewModel = new DetailTVShowViewModel(movieRepository);
    }

    @Test
    public void getTVShowDetail() {
        MutableLiveData<TVShow> tvshow = new MutableLiveData<>();
        tvshow.setValue(tvShowDummy);
        when(movieRepository.getTvShowDetails(tvshowId)).thenReturn(tvshow);

        Observer<TVShow> observer = mock(Observer.class);
        viewModel.getTvShowDetail(tvshowId).observeForever(observer);
        verify(observer).onChanged(tvShowDummy);
    }

}