package com.example.realestatemanager.database


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.realestatemanager.model.Property

@Dao
interface PropertyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(property: Property) : Long

    @Delete
    suspend fun delete(property: Property)

    @Query("Select * from properties order by property_id ASC")
    fun getAllProperties(): LiveData<List<Property>>

    @Update
    suspend fun update(property: Property)

    @Query("Select * from properties order by property_id ASC")
    fun getFilteredProperties(): LiveData<List<Property>>

    // List of properties with pictures with filters
    //@Transaction
    /*@Query("SELECT * FROM properties WHERE (type IN (:propertyType)) AND (`is sold` IN (:sold)) AND (price BETWEEN :priceMini AND :priceMax) AND (surface BETWEEN :surfaceMini AND :surfaceMax) AND (`number of room` BETWEEN :roomMini AND :roomMax) AND (`number of berdoom` BETWEEN :bedroomMini AND :bedroomMax) AND (propertyImage IN(:withPhoto)) AND (agent IN (:agent)) AND (soldTimestamp BETWEEN :dateSoldMini AND :dateSoldMax) AND (createdTimestamp BETWEEN :dateEntryMin AND :dateEntryMax)")
    suspend fun getPropertiesWithFilters(
        propertyType: String, sold: Boolean,
        priceMini: Double, priceMax: Double,
        surfaceMini: Int, surfaceMax: Int,
        roomMini: Int, roomMax: Int,
        bedroomMini: Int, bedroomMax: Int,
        withPhoto: Boolean, agent: String,
        dateEntryMin: String, dateEntryMax: String,
        dateSoldMini: String, dateSoldMax: String
    ): List<Property>?*/

}