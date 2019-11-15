package com.roningrum.weseemovie.ui.movie;

import androidx.test.rule.ActivityTestRule;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.testing.SingleFragmentActivity;
import com.roningrum.weseemovie.utils.RecyclerViewItemCountAssertion;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;


public class MovieListFragmentTest {
    @Rule
    public final ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private final MovieListFragment movieListFragment = new MovieListFragment();

    @Before
    public void setUp() {
        activityRule.getActivity().setFragment(movieListFragment);
    }

    @Test
    public void loadMovies() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_movie)).check(new RecyclerViewItemCountAssertion(11));
    }
}