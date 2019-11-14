package com.roningrum.weseemovie.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.Movie;
import com.roningrum.weseemovie.utils.FakeMovieDataDummy;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailMovieActivityTest {
    private Movie dummyMovie = FakeMovieDataDummy.generateDummyMovies().get(1);

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityActivityTestRule = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailMovieActivity.class);
            result.putExtra(DetailMovieActivity.EXTRA_FILMS, dummyMovie);
            return result;
        }
    };

    @Test
    public void loadMovieDetail() {
        onView(withId(R.id.tv_name_movie_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_name_movie_detail)).check(matches(withText(dummyMovie.getName())));
        onView(withId(R.id.tv_sinopsis_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_sinopsis_detail)).check(matches(withText(dummyMovie.getSynopsis())));
    }

}