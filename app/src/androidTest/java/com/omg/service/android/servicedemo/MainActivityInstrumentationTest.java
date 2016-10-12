package com.omg.service.android.servicedemo;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;

/**
 * Created by mgujare on 10/11/16.
 */

@RunWith(AndroidJUnit4.class)
@LargeTest

public class MainActivityInstrumentationTest {
    @Rule
    public ActivityTestRule mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    @Test
    public void beforeClick(){
        onView(withId(R.id.info)).check(matches(withText("Hello World!")));
    }

    @Test
    public void afterClick(){
        onView(withId(R.id.button1)).perform(click());

        onView(withId(R.id.info)).check(matches(not(withText("Hello World!"))));
    }

}
