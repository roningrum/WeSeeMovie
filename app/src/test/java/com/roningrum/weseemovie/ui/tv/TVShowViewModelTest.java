package com.roningrum.weseemovie.ui.tv;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

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

    @Test
    public void getFavTvShows() {
        MutableLiveData<Resource<PagedList<TVShowEntity>>> dummyTvShows = new MutableLiveData<>();
        PagedList<TVShowEntity> pagedList = mock(PagedList.class);

        dummyTvShows.setValue(Resource.success(pagedList));

        when(movieRepository.getFavoriteTvShowPaged()).thenReturn(dummyTvShows);

        Observer<Resource<PagedList<TVShowEntity>>> observer = mock(Observer.class);

        tvShowViewModel.getFavoritedTvSPaged().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));

    }

}