package com.roningrum.weseemovie.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.model.TVShow;
import com.roningrum.weseemovie.utils.EspressoIdlingResource;
import com.roningrum.weseemovie.utils.FakeMovieDataDummy;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailTVShowActivityTest {
    private final TVShow dummyTVS = FakeMovieDataDummy.generateDummyTVShow().get(0);

    @Rule
    public ActivityTestRule<DetailTVShowActivity> activityActivityTestRule = new ActivityTestRule<DetailTVShowActivity>(DetailTVShowActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailTVShowActivity.class);
            result.putExtra(DetailTVShowActivity.EXTRA_TV, dummyTVS.getId());
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
    public void loadTVShowDetail() {
        onView(withId(R.id.tv_name_tv_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_name_tv_detail)).check(matches(withText(dummyTVS.getName())));
        onView(withId(R.id.tv_sinopsis_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_sinopsis_detail)).check(matches(withText(dummyTVS.getOverview())));
        onView(withId(R.id.action_add_fav)).perform(click());
    }

}