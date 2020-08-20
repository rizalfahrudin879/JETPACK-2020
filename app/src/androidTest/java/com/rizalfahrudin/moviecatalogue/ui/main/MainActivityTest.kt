package com.rizalfahrudin.moviecatalogue.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.rizalfahrudin.moviecatalogue.R
import com.rizalfahrudin.moviecatalogue.utils.Data
import org.junit.Rule
import org.junit.Test

class MainActivityTest {
    private val dataMovie = Data.generateDataMovie()
    private val dataTv = Data.generateDataTv()

    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun loadDataMovie() {
        onView(withText(R.string.movie)).perform(click())
        onView(withId(R.id.rv_movie_tv))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_tv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataMovie.size))
    }


    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie_tv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tv_title_detail))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_title_detail))
            .check(matches(withText(dataMovie[0].title)))
    }
    @Test
    fun loadDataTv() {
        onView(withText(R.string.tv)).perform(click())
        onView(withId(R.id.rv_movie_tv))
            .check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie_tv))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dataTv.size))
    }
    @Test
    fun loadDetailTv() {
        onView(withText(R.string.tv)).perform(click())

        onView(withId(R.id.rv_movie_tv))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        onView(withId(R.id.tv_title_detail))
            .check(matches(isDisplayed()))

        onView(withId(R.id.tv_title_detail))
            .check(matches(withText(dataTv[0].title)))
    }
}