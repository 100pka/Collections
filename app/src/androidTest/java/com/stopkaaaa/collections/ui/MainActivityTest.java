package com.stopkaaaa.collections.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import com.stopkaaaa.collections.R;

import static androidx.test.espresso.Espresso.onView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.containsString;


public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
            MainActivity.class);

    public static Matcher<View> atPositionOnView(final int position, final Matcher<View> itemMatcher,
                                                 @NonNull final int targetViewId) {

        return new BoundedMatcher<View, RecyclerView>(RecyclerView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has view id " + itemMatcher + " at position " + position);
            }

            @Override
            public boolean matchesSafely(final RecyclerView recyclerView) {
                RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(position);
                View targetView = viewHolder.itemView.findViewById(targetViewId);
                return itemMatcher.matches(targetView);
            }
        };
    }

    @Test
    public void testShowingInvalidNumberNotification() {
        onView(withId(R.id.elementsAmount)).perform(typeText("abc"), closeSoftKeyboard());
        onView(withId(R.id.threadsAmount)).perform(typeText("0"), closeSoftKeyboard());
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.elementsAmount)).check(matches(withHint(R.string.enter_valid_number)));
        onView(withId(R.id.threadsAmount)).check(matches(withHint(R.string.enter_valid_number)));
    }

    @Test
    public void testCalculationSetValidTimeToListItem() {
        onView(withId(R.id.elementsAmount)).perform(typeText("11111"), closeSoftKeyboard());
        onView(withId(R.id.threadsAmount)).perform(typeText("5"), closeSoftKeyboard());
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.collectionsRecycler))
                .check(matches(atPositionOnView(0, withText(containsString("ms")), R.id.itemTime)));
    }
}