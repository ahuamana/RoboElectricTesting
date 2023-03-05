package com.cursosandroidant.inventory.mainModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [21,30])
@RunWith(AndroidJUnit4::class)
class MainViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun checkWelcomeTest(){
        val viewModel = MainViewModel(ApplicationProvider.getApplicationContext())
        val observer = Observer<Boolean>{}

        try{
            viewModel.isWelcome().observeForever {  }
            val result = viewModel.isWelcome().value
            assertThat(result, not(nullValue()))
            assertThat(result, `is`(true))
        }finally {
            viewModel.isWelcome().removeObserver(observer)
        }
    }

}