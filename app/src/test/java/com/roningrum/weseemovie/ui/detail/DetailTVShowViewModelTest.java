package com.roningrum.weseemovie.ui.detail;

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

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DetailTVShowViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    private DetailTVShowViewModel viewModel;
    private final MovieRepository movieRepository = mock(MovieRepository.class);
    private final TVShowEntity tvShowDummy = FakeMovieDataDummy.generateDummyTVLocals().get(0);
    private final int tvshowId = tvShowDummy.getId();

    @Before
    public void setUp() {
        viewModel = new DetailTVShowViewModel(movieRepository);
        viewModel.setTvId(tvshowId);
        viewModel.setFavoriteTVShow();
    }

    @Test
    public void getTVShowDetail() {
        Resource<TVShowEntity> resource = Resource.success(FakeMovieDataDummy.getTvShowDetails(tvShowDummy, true));
        MutableLiveData<Resource<TVShowEntity>> tvShowDetails = new MutableLiveData<>();
        tvShowDetails.setValue(resource);

        when(movieRepository.getTvShowDetails(tvshowId)).thenReturn(tvShowDetails);

        Observer<Resource<TVShowEntity>> observer = mock(Observer.class);
        viewModel.detailTvShows.observeForever(observer);
        verify(observer).onChanged(resource);
    }

}