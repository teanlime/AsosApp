package com.teanlime.asosapp.home.presentation;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.teanlime.asosapp.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class HomeActivityShould {

    @Rule
    public ActivityTestRule<HomeActivity> homeActivityRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void display_navigation_drawer() {
        onView(withId(R.id.drawer))
                .check(matches(isDisplayed()));
    }

    @Test
    public void display_show_women_text_on_women_button() {
        onView(withId(R.id.women_button))
                .check(matches(withText(R.string.shop_women)));
    }

    @Test
    public void display_show_men_text_on_men_button() {
        onView(withId(R.id.men_button))
                .check(matches(withText(R.string.shop_men)));
    }

    @Test
    public void set_women_button_checked_and_men_button_unchecked() {
        onView(withId(R.id.women_button))
                .perform(click())
                .check(matches(isChecked()));

        onView(withId(R.id.men_button))
                .check(matches(isNotChecked()));
    }

    @Test
    public void set_men_button_checked_and_women_button_unchecked() {
        onView(withId(R.id.men_button))
                .perform(click())
                .check(matches(isChecked()));

        onView(withId(R.id.women_button))
                .check(matches(isNotChecked()));
    }
}