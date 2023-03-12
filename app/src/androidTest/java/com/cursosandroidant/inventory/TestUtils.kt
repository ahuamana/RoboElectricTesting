package com.cursosandroidant.inventory

import android.view.View
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import org.hamcrest.Matcher

fun clickChildViewWithId(cbFavorite: Int): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> = ViewMatchers.withId(cbFavorite)

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: androidx.test.espresso.UiController?, view: View?) {
            val v = view?.findViewById<View>(cbFavorite)
            v?.performClick()
        }
    }
}