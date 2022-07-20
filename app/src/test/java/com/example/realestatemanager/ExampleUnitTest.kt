package com.example.realestatemanager

import com.example.realestatemanager.utils.Utils
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.text.SimpleDateFormat
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(JUnit4::class)
class ExampleUnitTest {

    @Test
    fun isInternetAvailableTest() {

    }

    @Test
    fun currentcyConversionTest() {
        val result = Utils.Companion.convertDollarToEuro (500)
        //assertThat(result).isEqualTo(406)
    }


    @Test
    fun getTodayDateTest() {
        val date = SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().time)
        val result = Utils.Companion.getTodayDate()
        //assertThat(result).isEqualTo(date)
    }

    @Test
    fun convertDollarToEuroTest() {

    }

    @Test
    fun calculateLoanTest() {

    }

   


}