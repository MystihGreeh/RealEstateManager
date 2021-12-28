package com.example.realestatemanager.database


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.realestatemanager.model.Property
import kotlinx.coroutines.flow.Flow

@Dao
interface PropertyDao {
    @Query("SELECT * FROM properties")
    fun getAllProperties(): Flow<List<Property>>

    @Query("SELECT * FROM properties WHERE property_id = :propertyId")
    suspend fun getProperty(propertyId: String): List<Property>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createProperty(property: Property)

}