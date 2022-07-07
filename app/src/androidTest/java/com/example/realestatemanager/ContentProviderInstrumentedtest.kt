package com.example.realestatemanager

import android.content.ContentResolver
import android.content.ContentUris
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.matcher.ViewMatchers.assertThat
import android.support.test.runner.AndroidJUnit4
import androidx.room.Room
import com.example.realestatemanager.database.RealEstateManagerDatabase
import com.example.realestatemanager.provider.ItemContentProvider
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.notNullValue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith




class ContentProviderInstrumentedtest {

    @RunWith(AndroidJUnit4::class)
    class ItemContentProviderTest {
        // FOR DATA
        private var mContentResolver: ContentResolver? = null
        @Before
        fun setUp() {
            Room.inMemoryDatabaseBuilder(
                InstrumentationRegistry.getInstrumentation().getContext(),
                RealEstateManagerDatabase::class.java
            )
                .allowMainThreadQueries()
                .build()
            mContentResolver = InstrumentationRegistry.getInstrumentation().getContext()
                .getContentResolver()
        }

        @get:Test
        val itemsWhenNoItemInserted: Unit
            get() {
                val cursor: Cursor? = mContentResolver!!.query(
                    ContentUris.withAppendedId(
                        ItemContentProvider.URI_PROPERTY,
                        USER_ID
                    ), null, null, null, null
                )
                assertThat(cursor, notNullValue())
                assertThat(cursor?.getCount(), `is`(0))
                cursor?.close()
            }

        @Test
        fun insertAndGetItem() {

            // BEFORE : Adding demo item
            val userUri: Uri? =
                mContentResolver!!.insert(ItemContentProvider.URI_PROPERTY, generateItem())

            // TEST
            val cursor: Cursor? = mContentResolver!!.query(
                ContentUris.withAppendedId(
                    ItemContentProvider.URI_PROPERTY,
                    USER_ID
                ), null, null, null, null
            )
            assertThat(cursor, notNullValue())
            assertThat(cursor?.getCount(), `is`(1))
            assertThat(cursor?.moveToFirst(), `is`(true))
            assertThat(
                cursor?.getString(cursor.getColumnIndexOrThrow("text")),
                `is`("Visite cet endroit de rêve !")
            )
        }

        // ---
        private fun generateItem(): ContentValues {
            val values = ContentValues()
            values.put("text", "Visite cet endroit de rêve !")
            values.put("category", "0")
            values.put("isSelected", "false")
            values.put("userId", "1")
            return values
        }

        companion object {
            // DATA SET FOR TEST
            private const val USER_ID: Long = 1
        }
    }
}