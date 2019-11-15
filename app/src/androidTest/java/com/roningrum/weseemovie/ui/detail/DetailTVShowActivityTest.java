package com.roningrum.weseemovie.ui.detail;

import android.content.Context;
import android.content.Intent;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.rule.ActivityTestRule;

import com.roningrum.weseemovie.R;
import com.roningrum.weseemovie.data.TVShow;
import com.roningrum.weseemovie.utils.FakeTVShowDataDummy;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DetailTVShowActivityTest {
    private final TVShow dummyTVS = FakeTVShowDataDummy.generateDummyTvs().get(3);

    @Rule
    public ActivityTestRule<DetailTVShowActivity> activityActivityTestRule = new ActivityTestRule<DetailTVShowActivity>(DetailTVShowActivity.class) {
        @Override
        protected Intent getActivityIntent() {
            Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
            Intent result = new Intent(targetContext, DetailTVShowActivity.class);
            result.putExtra(DetailTVShowActivity.EXTRA_TV, dummyTVS);
            return result;
        }
    };

    @Test
    public void loadTVShowDetail() {
        onView(withId(R.id.tv_name_tv_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_name_tv_detail)).check(matches(withText(dummyTVS.getName())));
        onView(withId(R.id.tv_sinopsis_detail)).check(matches(isDisplayed()));
        onView(withId(R.id.tv_sinopsis_detail)).check(matches(withText(dummyTVS.getSynopsis())));
    }

}