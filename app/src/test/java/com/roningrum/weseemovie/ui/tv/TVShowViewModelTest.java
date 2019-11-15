package com.roningrum.weseemovie.ui.tv;

import com.roningrum.weseemovie.data.TVShow;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TVShowViewModelTest {
    private TVShowViewModel tvShowViewModel;

    @Before
    public void setUp() {
        tvShowViewModel = new TVShowViewModel();
    }

    @Test
    public void getTvShows() {
        List<TVShow> tvShows = tvShowViewModel.getAllTvShows();
        assertNotNull(tvShows);
        assertEquals(11, tvShows.size());
    }

}