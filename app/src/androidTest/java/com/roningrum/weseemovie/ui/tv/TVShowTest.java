package com.roningrum.weseemovie.ui.tv;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.ui.home.HomeMenuActivity;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class TVShowTest {
    @Rule
    public ActivityTestRule<HomeMenuActivity> activityTestRule = new ActivityTestRule<>(HomeMenuActivity.class);

    @Test
    public void toDetailMovieActivity() {
        onView(withId(R.id.nav_tv_menu)).perform(click()).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()));
        onView(withId(R.id.rv_tv_show)).perform(RecyclerViewActions.actionOnItemAtPosition(9, click()));
        onView(withId(R.id.tv_name_tv_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_name_tv_detail)).check(matches(withText("The Umbrella Academy(2019)")));
        Espresso.pressBack();
    }

}