package com.roningrum.weseemovie.ui.tv;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.roningrum.weseemovie.data.source.MovieRepository;
import com.roningrum.weseemovie.data.source.locale.entity.TVShowEntity;
import com.roningrum.weseemovie.ui.utils.FakeMovieDataDummy;
import com.roningrum.weseemovie.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TVShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private TVShowViewModel tvShowViewModel;
    private final MovieRepository movieRepository = mock(MovieRepository.class);

    @Before
    public void setUp() {
        tvShowViewModel = new TVShowViewModel(movieRepository);
    }

    @Test
    public void getTvShows() {
        Resource<List<TVShowEntity>> resource = Resource.success(FakeMovieDataDummy.generateDummyTVLocals());
        MutableLiveData<Resource<List<TVShowEntity>>> dummyTvshows = new MutableLiveData<>();
        dummyTvshows.setValue(resource);

        when(movieRepository.getAllTvs()).thenReturn(dummyTvshows);

        Observer<Resource<List<TVShowEntity>>> observer = mock(Observer.class);
        String USERNAME = "roningrum";
        tvShowViewModel.setUserName(USERNAME);
        tvShowViewModel.tvShows.observeForever(observer);
        verify(observer).onChanged(resource);

    }

}