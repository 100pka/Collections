package com.stopkaaaa.collections;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.stopkaaaa.collections.di.DaggerActivityTestRule;
import com.stopkaaaa.collections.di.component.AppComponent;
import com.stopkaaaa.collections.di.component.DaggerAppComponent;
import com.stopkaaaa.collections.di.module.AppModuleTest;
import com.stopkaaaa.collections.ui.MainActivity;

import static androidx.test.espresso.Espresso.onView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.containsString;

@RunWith(AndroidJUnit4ClassRunner.class)
public class UITests {

    private AppComponent componentTest;

    @Before
    public void setupTests(){
    }

    @Rule
    public ActivityTestRule<MainActivity> mainActivityRule =
            new DaggerActivityTestRule<>(MainActivity.class, (application, activity) -> {
                componentTest = DaggerAppComponent.builder()
                        .appModule(new AppModuleTest(CollectionsMapsApp.getInstance()))
                        .build();
                CollectionsMapsApp.getInstance().setAppComponent(componentTest);
            });



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
    public void testCalculationSetValidTimeToListItem() throws InterruptedException {
        onView(withId(R.id.elementsAmount)).perform(typeText("11111"), closeSoftKeyboard());
        onView(withId(R.id.threadsAmount)).perform(typeText("5"), closeSoftKeyboard());
        onView(withId(R.id.startButton)).perform(click());
        Thread.sleep(5000);
        onView(withId(R.id.collectionsRecycler))
                .check(matches(atPositionOnView(0, withText(containsString("ms")), R.id.itemTime)));
    }

    @Test
    public void testCalculationStopped() throws InterruptedException {
        onView(withId(R.id.elementsAmount)).perform(typeText("11111"), closeSoftKeyboard());
        onView(withId(R.id.threadsAmount)).perform(typeText("5"), closeSoftKeyboard());
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.startButton)).check(matches(withText(R.string.stop)));
        Thread.sleep(500);
        onView(withId(R.id.startButton)).perform(click());
        onView(withId(R.id.startButton)).check(matches(withText(R.string.start)));
    }
}