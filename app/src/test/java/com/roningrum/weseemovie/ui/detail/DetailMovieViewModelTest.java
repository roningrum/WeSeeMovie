package com.roningrum.weseemovie.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.roningrum.weseemovie.data.source.MovieRepository;
import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.ui.utils.FakeMovieDataDummy;
import com.roningrum.weseemovie.vo.Resource;

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
    private final MovieRepository movieRepository = mock(MovieRepository.class);
    private final MovieEntity dummyMovieDetails = FakeMovieDataDummy.generateDummyMovieLocals().get(0);
    private final int movieId = dummyMovieDetails.getId();

    @Before
    public void setUp() {
        viewModel = new DetailMovieViewModel(movieRepository);
        viewModel.setMovieId(movieId);
        viewModel.setFavoriteMovie();
    }

    @Test
    public void getMovieDetail() {
        Resource<MovieEntity> resource = Resource.success(FakeMovieDataDummy.getMovieDetails(dummyMovieDetails, true));
        MutableLiveData<Resource<MovieEntity>> movieDetails = new MutableLiveData<>();
        movieDetails.setValue(resource);

        when(movieRepository.getMovieDetails(movieId)).thenReturn(movieDetails);

        Observer<Resource<MovieEntity>> observer = mock(Observer.class);
        viewModel.detailMovies.observeForever(observer);
        verify(observer).onChanged(resource);

    }
}