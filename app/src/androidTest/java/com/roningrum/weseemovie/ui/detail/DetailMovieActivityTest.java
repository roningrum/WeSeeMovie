package com.roningrum.weseemovie.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.model.Movie;
import com.roningrum.weseemovie.utils.EspressoIdlingResource;
import com.roningrum.weseemovie.utils.FakeMovieDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailMovieActivityTest {
    private final Movie dummyMovie = FakeMovieDataDummy.generateDummyMovies().get(0);

    @Rule
    public ActivityTestRule<DetailMovieActivity> activityActivityTestRule = new ActivityTestRule<DetailMovieActivity>(DetailMovieActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailMovieActivity.class);
            result.putExtra(DetailMovieActivity.EXTRA_FILMS, dummyMovie.getId());
            return result;
        }
    };

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getEspressoIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getEspressoIdlingResource());
    }
    @Test
    public void loadMovieDetail() {
        onView(withId(R.id.tv_name_movie_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_name_movie_detail)).check(matches(withText(dummyMovie.getTitle())));
        onView(withId(R.id.tv_sinopsis_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_sinopsis_detail)).check(matches(withText(dummyMovie.getOverview())));
    }

}