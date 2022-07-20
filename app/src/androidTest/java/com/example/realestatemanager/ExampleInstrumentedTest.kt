package com.example.realestatemanager

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    // This is fixed
    private val ITEMS_COUNT = 12




    /**
     * When we delete a property, the property is no more shown
     */
    @Test
    fun onPropertyDeleteClickTest() {
        // Given : We remove the element at position 2

        // When perform a click on a delete icon

        // Then : the number of element is 11


    }


    /** When we click on the property, the property details display
     */
    @Test
    fun onPropertyClickTest() {

    }

    @Test
    fun propertySearchTest() {

    }



}