package com.cursosandroidant.inventory.addModule.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cursosandroidant.inventory.entities.Product
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddViewModelTest{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun `addProduct() with empty name returns false`(){
        val viewModel = AddViewModel()
        val product = Product(
            117,
            "Xbox",
            20,
            "https://upload.wikimedia.org/wikipedia/commons/3/37/Xbox-Debug-Console-Set.jpg",
            4.8, 56)

        val observer = Observer<Boolean> {  }

        try {
            viewModel.getResult().observeForever(observer)
            viewModel.addProduct(product)

            val result = viewModel.getResult().value

            assertThat(result, `is`(true))
            assertThat(result, not(nullValue()))

        }finally {
            viewModel.getResult().removeObserver(observer)
        }
    }
}