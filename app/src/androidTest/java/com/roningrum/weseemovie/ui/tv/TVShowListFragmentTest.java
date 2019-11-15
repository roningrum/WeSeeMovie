package com.roningrum.weseemovie.ui.tv;

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

public class TVShowListFragmentTest {
    @Rule
    public final ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private final TVShowListFragment tvShowListFragment = new TVShowListFragment();

    @Before
    public void setUp() {
        activityRule.getActivity().setFragment(tvShowListFragment);
    }

    @Test
    public void loadMovies() {
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show)).check(new RecyclerViewItemCountAssertion(11));
    }


}