package com.roningrum.weseemovie.ui.tv;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.ui.home.HomeMenuActivity;
import com.roningrum.weseemovie.utils.EspressoIdlingResource;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class TVShowTest {
    @Rule
    public ActivityTestRule<HomeMenuActivity> activityTestRule = new ActivityTestRule<>(HomeMenuActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @Test
    public void toDetailMovieActivity() {
        onView(withId(R.id.nav_tv_menu)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition(10, click()));
        onView(withId(R.id.tv_name_tv_detail)).check(matches(isDisplayed()));
        Espresso.pressBack();
    }

}