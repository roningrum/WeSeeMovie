package com.roningrum.weseemovie.ui.movie;

import com.roningrum.weseemovie.data.Movie;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertNotNull;

public class MovieViewModelTest {
    private MovieViewModel movieViewModel;

    @Before
    public void setUp() {
        movieViewModel = new MovieViewModel();
    }

    @Test
    public void getMovies() {
        List<Movie> movies = movieViewModel.getAllMovies();
        assertNotNull(movies);
        assertEquals(11, movies.size());
    }

}