package com.roningrum.weseemovie.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.roningrum.weseemovie.data.source.locale.LocalRepository;
import com.roningrum.weseemovie.data.source.locale.entity.MovieEntity;
import com.roningrum.weseemovie.data.source.locale.entity.TVShowEntity;
import com.roningrum.weseemovie.data.source.remote.RemoteRepository;
import com.roningrum.weseemovie.model.Movie;
import com.roningrum.weseemovie.model.TVShow;
import com.roningrum.weseemovie.ui.utils.FakeMovieDataDummy;
import com.roningrum.weseemovie.ui.utils.InstantAppExecutors;
import com.roningrum.weseemovie.ui.utils.LiveDataTestUtils;
import com.roningrum.weseemovie.ui.utils.PagedListUtil;
import com.roningrum.weseemovie.vo.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private LocalRepository local = Mockito.mock(LocalRepository.class);
    private RemoteRepository remote = Mockito.mock(RemoteRepository.class);
    private InstantAppExecutors instantAppExecutors = Mockito.mock(InstantAppExecutors.class);
    private FakeMovieRepository fakeMovieRepository = new FakeMovieRepository(local, remote, instantAppExecutors);

    //local
    private List<MovieEntity> movieLocals = FakeMovieDataDummy.generateDummyMovieLocals();
    private List<TVShowEntity> tvShowLocals = FakeMovieDataDummy.generateDummyTVLocals();
    //remote
    private List<Movie> movies = FakeMovieDataDummy.generateDummyMovies();
    private List<TVShow> tvShows = FakeMovieDataDummy.generateDummyTVShow();

    private final int movieId = movies.get(0).getId();
    private final int tvShowId = tvShows.get(0).getId();

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void getAllMovies() {
        MutableLiveData<List<MovieEntity>> dummyMovies = new MutableLiveData<>();
        dummyMovies.setValue(movieLocals);

        when(local.getAllMovies()).thenReturn(dummyMovies);

        Resource<List<MovieEntity>> result = LiveDataTestUtils.getValue(fakeMovieRepository.getAllMovies());

        verify(local).getAllMovies();
        assertNotNull(result.data);
        assertEquals(movies.size(), result.data.size());
    }

    @Test
    public void getAllTVShows() {
        MutableLiveData<List<TVShowEntity>> listMutableLiveData = new MutableLiveData<>();
        listMutableLiveData.setValue(tvShowLocals);

        when(local.getAllTvShows()).thenReturn(listMutableLiveData);

        Resource<List<TVShowEntity>> result = LiveDataTestUtils.getValue(fakeMovieRepository.getAllTvs());

        verify(local).getAllTvShows();
        assertNotNull(result.data);
        assertEquals(tvShows.size(), result.data.size());
    }

    @Test
    public void getTVShowsDetail() {
        MutableLiveData<TVShowEntity> dummyTvShowDetail = new MutableLiveData<>();
        dummyTvShowDetail.setValue(FakeMovieDataDummy.getTvShowDetails(FakeMovieDataDummy.getTvShowDetails(FakeMovieDataDummy.generateDummyTVLocals().get(0), true), true));

        when(local.getTvShowetails(tvShowId)).thenReturn(dummyTvShowDetail);

        Resource<TVShowEntity> result = LiveDataTestUtils.getValue(fakeMovieRepository.getTvShowDetails(tvShowId));

        verify(local).getTvShowetails(tvShowId);
        assertNotNull(result.data);
        assertNotNull(result.data.getName());
        assertEquals(tvShows.get(0).getName(), result.data.getName());
    }

    @Test
    public void getMovieDetails() {
        MutableLiveData<MovieEntity> dummyMovieDetails = new MutableLiveData<>();
        dummyMovieDetails.setValue(FakeMovieDataDummy.getMovieDetails(FakeMovieDataDummy.generateDummyMovieLocals().get(0), true));
        when(local.getMovieDetails(movieId)).thenReturn(dummyMovieDetails);

        Resource<MovieEntity> result = LiveDataTestUtils.getValue(fakeMovieRepository.getMovieDetails(movieId));

        verify(local).getMovieDetails(movieId);
        assertNotNull(result.data);
        assertNotNull(result.data.getTitle());
        assertEquals(movies.get(0).getTitle(), result.data.getTitle());
    }

    @Test
    public void getFavoriteTVShows() {
        DataSource.Factory<Integer, TVShowEntity> dataSource = mock(DataSource.Factory.class);

        when(local.getTvShowFavs()).thenReturn(dataSource);
        fakeMovieRepository.getFavoriteTvShowPaged();
        Resource<PagedList<TVShowEntity>> result = Resource.success(PagedListUtil.mockPagedList(tvShowLocals));

        verify(local).getTvShowFavs();
        assertNotNull(result.data);
        assertEquals(tvShowLocals.size(), result.data.size());
    }

    @Test
    public void getFavoriteMovies() {
        DataSource.Factory<Integer, MovieEntity> dataSource = mock(DataSource.Factory.class);

        when(local.getMoviesFavs()).thenReturn(dataSource);
        fakeMovieRepository.getFavoritedMoviePaged();
        Resource<PagedList<MovieEntity>> result = Resource.success(PagedListUtil.mockPagedList(movieLocals));

        verify(local).getMoviesFavs();
        assertNotNull(result.data);
        assertEquals(movieLocals.size(), result.data.size());
    }


}