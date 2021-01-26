package com.roningrum.weseemovie.ui.favorit;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.roningrum.weseemovie.testing.SingleFragmentActivity;
import com.roningrum.weseemovie.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class FavTvShowFragmentTest {
    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private FavTvShowFragment favTvShowFragment = new FavTvShowFragment();

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
        activityRule.getActivity().setFragment(favTvShowFragment);
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void loadTvShowFavs() {
//        onView(withId(R.id.rv_tvShows_favs)).check(matches(isDisplayed()));
//        onView(withId(R.id.rv_tvShows_favs)).check(new RecyclerViewItemCountAssertion(20));

    }

}