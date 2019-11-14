package com.roningrum.weseemovie.ui.detail;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.Movie;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DetailMovieViewModelTest {
    private DetailMovieViewModel viewModel;
    private Movie movieDummy;

    @Before
    public void setUp() {
        viewModel = new DetailMovieViewModel();
        movieDummy = new Movie("Alita: Battle Angel(2019)",
                "Action,Science Fiction, \nThriller, Adventure",
                "2h 2m",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Robert Rodriguez",
                "January 31st 2019",
                R.drawable.banner_movie_alita,
                R.drawable.poster_alita);
    }

    @Test
    public void getMovieDetail() {
        viewModel.setMovie(movieDummy);
        Movie movieDummyData = viewModel.getMovie();
        assertNotNull(movieDummyData);
        assertEquals(movieDummy.getName(), movieDummyData.getName());
        assertEquals(movieDummy.getGenre(), movieDummyData.getGenre());
        assertEquals(movieDummy.getDuration(), movieDummyData.getDuration());
        assertEquals(movieDummy.getSynopsis(), movieDummyData.getSynopsis());
        assertEquals(movieDummy.getCreator(), movieDummyData.getCreator());
        assertEquals(movieDummy.getDate(), movieDummyData.getDate());
        assertEquals(movieDummy.getPhotoBanner(), movieDummyData.getPhotoBanner());
        assertEquals(movieDummy.getPoster(), movieDummyData.getPoster());
    }

}