package com.example.moviecatalogbylatiefniam.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.moviecatalogbylatiefniam.R
import com.example.moviecatalogbylatiefniam.utils.Data
import com.example.moviecatalogbylatiefniam.utils.Espressoldling

import org.junit.After
import org.junit.Test


import org.junit.Before
import org.junit.Rule

class HomeActivityTest {
    private val dataMovie = Data.generateMovie()
    private val dataTvShow = Data.generateTv()

    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)
    @Before
    fun setUp(){
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(Espressoldling.idlingResource)
    }
    @After
    fun tearDown(){
        IdlingRegistry.getInstance().unregister(Espressoldling.idlingResource)
    }
    @Test
    fun loadMovie() {
        onView(withId(R.id.recyclerviewmovie)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerviewmovie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataMovie.size
            )
        )
    }
    @Test
    fun detailMovie(){
        onView(withId(R.id.recyclerviewmovie)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerviewmovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
       onView(withId(R.id.image)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(withText(dataMovie[0].movieTitle)))
        onView(withId(R.id.duration)).check(matches(isDisplayed()))
        onView(withId(R.id.duration)).check(matches(withText(dataMovie[0].movieDuration)))
        onView(withId(R.id.genre)).check(matches(isDisplayed()))
        onView(withId(R.id.genre)).check(matches(withText(dataMovie[0].movieGenre)))

    }
    @Test
    fun loadTv() {

        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.recyclerviewtv)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerviewtv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dataTvShow.size
            )
        )
    }
    @Test
    fun detailTv(){
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.recyclerviewtv)).check(matches(isDisplayed()))

        onView(withId(R.id.recyclerviewtv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
        onView(withId(R.id.image)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.title)).check(matches(withText(dataTvShow[0].tvTitle)))
        onView(withId(R.id.duration)).check(matches(isDisplayed()))
        onView(withId(R.id.duration)).check(matches(withText(dataTvShow[0].tvDuration)))
        onView(withId(R.id.genre)).check(matches(isDisplayed()))
        onView(withId(R.id.genre)).check(matches(withText(dataTvShow[0].tvGenre)))

    }
}