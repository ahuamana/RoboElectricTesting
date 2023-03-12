package com.cursosandroidant.inventory.mainModule.view


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.cursosandroidant.inventory.R
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest2 {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun actionBar_menuItemClick_returnMsg(){
        //Recomendado para menús de opciones
        onView(withId(R.id.recyclerView))
            .perform(click())

        onView(withId(R.id.action_history))
            .perform(click())

        var snackbar_msg = ""
        activityScenarioRule.scenario.onActivity {
            snackbar_msg = it.getString(R.string.main_msg_go_history)
        }

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(snackbar_msg)))
    }

    @Test
    fun contextMenu_menuItemClick_returnMsg(){
        //Recomendado para menús de contexto
        onView(withId(R.id.recyclerView))
            .perform(click())

        openActionBarOverflowOrOptionsMenu(ApplicationProvider.getApplicationContext())

        onView(withText("Salir"))
            .perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Salir…")))
    }
}
