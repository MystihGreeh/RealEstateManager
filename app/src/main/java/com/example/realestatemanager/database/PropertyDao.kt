package com.example.realestatemanager.database


import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.realestatemanager.model.Property

@Dao
interface PropertyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(property: Property) : Long

    @Delete
    fun delete(property: Property)

    @Query("Select * from properties order by property_id ASC")
    fun getAllProperties(): LiveData<List<Property>>

    @Update
    suspend fun update(property: Property)

    @Query("Select * from properties order by property_id ASC")
    fun getFilteredProperties(): LiveData<List<Property>>

    @Query("SELECT * FROM properties WHERE property_id = :propertyId")
    fun getItemsWithCursor(propertyId: Long): Cursor?


    // List of properties with pictures with filters
    //@Transaction
    @Query("SELECT * FROM properties WHERE (price BETWEEN :priceMini AND :priceMax) AND (surface BETWEEN :surfaceMini AND :surfaceMax) AND (number_of_room BETWEEN :roomMini AND :roomMax)")
    fun getPropertiesWithFilters(
        priceMini: Int?,
        priceMax: Int?,
        surfaceMini: Int?,
        surfaceMax: Int?,
        roomMini: Int?,
        roomMax: Int?,

    ): LiveData<List<Property>>

}