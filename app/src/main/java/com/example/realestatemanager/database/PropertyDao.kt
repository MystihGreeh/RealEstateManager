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
    @Query("SELECT * FROM properties WHERE (type IN (:propertyType)) AND (`is sold` IN (:sold)) AND (price BETWEEN :priceMini AND :priceMax) AND (surface BETWEEN :surfaceMini AND :surfaceMax) AND (`number of room` BETWEEN :roomMini AND :roomMax) AND (`number of berdoom` BETWEEN :bedroomMini AND :bedroomMax) AND (propertyImage IN(:withPhoto)) AND (agent IN (:agent)) AND (soldTimestamp BETWEEN :dateSoldMini AND :dateSoldMax) AND (createdTimestamp BETWEEN :dateEntryMin AND :dateEntryMax)")
    suspend fun getPropertiesWithFilters(
        propertyType: String = "house", sold: Boolean = true,
        priceMini: Double, priceMax: Double,
        surfaceMini: Int = 0, surfaceMax: Int = 1000000,
        roomMini: Int = 1, roomMax: Int = 500,
        bedroomMini: Int = 0, bedroomMax: Int = 500,
        withPhoto: Boolean = true, agent: String = "Charlotte",
        dateEntryMin: String = "00/00/1900", dateEntryMax: String = "00/00/2900",
        dateSoldMini: String = "00/00/1900", dateSoldMax: String = "00/00/2900"
    ): List<Property>?

}