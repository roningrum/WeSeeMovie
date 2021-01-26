package com.roningrum.weseemovie.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.roningrum.weseemovie.data.source.MovieRepository;
import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.ui.utils.FakeMovieDataDummy;
import com.roningrum.weseemovie.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private MovieViewModel movieViewModel;
    private MovieRepository movieRepository = mock(MovieRepository.class);

    @Before
    public void setUp() {
        movieViewModel = new MovieViewModel(movieRepository);
    }

    @Test
    public void getMovies() {
        Resource<List<MovieEntity>> resource = Resource.success(FakeMovieDataDummy.generateDummyMovieLocals());
        MutableLiveData<Resource<List<MovieEntity>>> dummyMovies = new MutableLiveData<>();
        dummyMovies.setValue(resource);

        when(movieRepository.getAllMovies()).thenReturn(dummyMovies);

        Observer<Resource<List<MovieEntity>>> observer = mock(Observer.class);

        String USERNAME = "roningrum";
        movieViewModel.setUserName(USERNAME);
        movieViewModel.movies.observeForever(observer);

        verify(observer).onChanged(resource);
    }

    @Test
    public void getFavMovies() {
        MutableLiveData<Resource<PagedList<MovieEntity>>> dummyMovies = new MutableLiveData<>();
        PagedList<MovieEntity> pagedList = mock(PagedList.class);

        dummyMovies.setValue(Resource.success(pagedList));

        when(movieRepository.getFavoritedMoviePaged()).thenReturn(dummyMovies);

        Observer<Resource<PagedList<MovieEntity>>> observer = mock(Observer.class);

        movieViewModel.getFavoritedMoviePaged().observeForever(observer);

        verify(observer).onChanged(Resource.success(pagedList));

    }

}