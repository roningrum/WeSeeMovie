package com.roningrum.weseemovie.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.roningrum.weseemovie.data.locale.entity.Movie;
import com.roningrum.weseemovie.data.locale.entity.TVShow;
import com.roningrum.weseemovie.data.remote.RemoteRepository;
import com.roningrum.weseemovie.ui.utils.FakeMovieDataDummy;
import com.roningrum.weseemovie.ui.utils.LiveDataTestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class MovieRepositoryTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private final RemoteRepository remote = Mockito.mock(RemoteRepository.class);
    private final FakeMovieRepository fakeMovieRepository = new FakeMovieRepository(remote);

    private final ArrayList<Movie> movies = FakeMovieDataDummy.generateDummyMovies();
    private final ArrayList<TVShow> tvShows = FakeMovieDataDummy.generateDummyTVShow();
    private final TVShow tvShowDetail = FakeMovieDataDummy.getTvShowsDetail();
    private final Movie movieDetail = FakeMovieDataDummy.getMovieDetail();
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
        doAnswer(invocation -> {
            ((RemoteRepository.LoadMoviesCallback) invocation.getArguments()[0])
                    .onAllMoviesReceive(movies);
            return null;
        }).when(remote).getAllMovies(any(RemoteRepository.LoadMoviesCallback.class));
        List<Movie> result = LiveDataTestUtils.getValue(fakeMovieRepository.getAllMovies());

        verify(remote, times(1)).getAllMovies(any(RemoteRepository.LoadMoviesCallback.class));

        assertNotNull(result);
        assertEquals(movies.size(), result.size());
    }

    @Test
    public void getAllTVShows() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTvShowsCallback) invocation.getArguments()[0])
                    .onAllTvShowsReceive(tvShows);
            return null;
        }).when(remote).getAllTvShow(any(RemoteRepository.LoadTvShowsCallback.class));
        List<TVShow> result = LiveDataTestUtils.getValue(fakeMovieRepository.getAllTvs());

        verify(remote, times(1)).getAllTvShow(any(RemoteRepository.LoadTvShowsCallback.class));

        assertNotNull(result);
        assertEquals(movies.size(), result.size());
    }

    @Test
    public void getTVShows() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadTvShowDetailCallback) invocation.getArguments()[1])
                    .onTvShowDetailReceive(tvShowDetail);
            return null;
        }).when(remote).getTvShowDetail(eq(tvShowId), any(RemoteRepository.LoadTvShowDetailCallback.class));

        TVShow tvShow = LiveDataTestUtils.getValue(fakeMovieRepository.getTvShowDetails(tvShowId));

        verify(remote, times(1)).getTvShowDetail(eq(tvShowId), any(RemoteRepository.LoadTvShowDetailCallback.class));
        assertNotNull(tvShow);
        assertEquals(tvShowDetail, tvShow);
    }

    @Test
    public void getMovieDetails() {
        doAnswer(invocation -> {
            ((RemoteRepository.LoadMovieDetailCallback) invocation.getArguments()[1])
                    .onMovieDetailReceive(movieDetail);
            return null;
        }).when(remote).getMovieDetail(eq(movieId), any(RemoteRepository.LoadMovieDetailCallback.class));

        Movie movie = LiveDataTestUtils.getValue(fakeMovieRepository.getMovieDetails(movieId));
        verify(remote, times(1)).getMovieDetail(eq(movieId), any(RemoteRepository.LoadMovieDetailCallback.class));
        assertEquals(movieDetail.getId(), movie.getId());
    }


}