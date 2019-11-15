package com.roningrum.weseemovie.ui.detail;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.TVShow;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DetailTVShowViewModelTest {
    private DetailTVShowViewModel viewModel;
    private TVShow tvShowDummy;

    @Before
    public void setUp() {
        viewModel = new DetailTVShowViewModel();
        tvShowDummy = new TVShow(
                "The Umbrella Academy(2019)",
                "Action&Adventure,Drama,\nSci-fi&Fantasy",
                "42m",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Greg Berlanti, \nMarch Guggenheim, \nAndrew Kreisberg",
                R.drawable.banner_tv_shows_umbrella,
                R.drawable.poster_the_umbrella);
    }

    @Test
    public void getTVShowDetail() {
        viewModel.setTvShow(tvShowDummy);
        TVShow tvShowDummyData = viewModel.getTvShow();
        assertNotNull(tvShowDummyData);
        assertEquals(tvShowDummy.getName(), tvShowDummyData.getName());
        assertEquals(tvShowDummy.getGenre(), tvShowDummyData.getGenre());
        assertEquals(tvShowDummy.getDuration(), tvShowDummyData.getDuration());
        assertEquals(tvShowDummy.getSynopsis(), tvShowDummyData.getSynopsis());
        assertEquals(tvShowDummy.getCreator(), tvShowDummyData.getCreator());
        assertEquals(tvShowDummy.getPhotoBanner(), tvShowDummyData.getPhotoBanner());
        assertEquals(tvShowDummy.getPoster(), tvShowDummyData.getPoster());
    }

}