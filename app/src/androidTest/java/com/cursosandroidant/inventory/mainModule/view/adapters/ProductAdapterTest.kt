package com.cursosandroidant.inventory.mainModule.view.adapters

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.cursosandroidant.inventory.mainModule.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import com.cursosandroidant.inventory.R
import com.cursosandroidant.inventory.clickChildViewWithId
import org.hamcrest.Matcher
import org.hamcrest.Matchers.*
import org.junit.Assert.fail

@LargeTest
@RunWith(AndroidJUnit4::class)
class ProductAdapterTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun listItemClick() {
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(1, click()))
    }

    @Test
    fun listItemClick2() {
        onView(withId(R.id.recyclerView))
            .perform(RecyclerViewActions.actionOnItemAtPosition<ViewHolder>(1, click()))

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Queso")))
    }

    @Test
    fun listItem_LongClick_removeFromView(){
        onView(withId(R.id.recyclerView))
            .perform(actionOnItem<ProductAdapter.ViewHolder>(
                hasDescendant(withText(containsString("Tijeras"))), longClick()
            ))

        //Thread.sleep(1000)
    }

    @Test
    fun listItem_LongClick_removeFromView_more_than_one_action_and_handled_exception(){

        onView(withId(R.id.recyclerView))
            .perform(
                actionOnItem<ProductAdapter.ViewHolder>(
                hasDescendant(withText(containsString("Tijeras"))), longClick()),
                scrollTo<ProductAdapter.ViewHolder>(
                    hasDescendant(withText(containsString("Vino"))))
            )
        Thread.sleep(1000)

        try {
            onView(withId(R.id.recyclerView))
                .perform(
                    scrollTo<ProductAdapter.ViewHolder>(
                    hasDescendant(withText(containsString("Tijeras")))
                ))
            fail("Tijeras still exist!!!")//If the item still exists, the test fails
        } catch (e: Exception) {
            assertThat((e as? PerformException), `is`(notNullValue()))
        }
    }

    @Test
    fun cbFavorite_click_changeState(){
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<ProductAdapter.ViewHolder>(
                1, clickChildViewWithId(R.id.cbFavorite)
            ))
        Thread.sleep(2_000)
    }



}