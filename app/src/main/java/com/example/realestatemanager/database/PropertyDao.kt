package com.example.realestatemanager.database


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.realestatemanager.model.Property

@Dao
interface PropertyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(property: Property)

    @Delete
    suspend fun delete(property: Property)

    @Query("Select * from properties order by id ASC")
    fun getAllProperties(): LiveData<List<Property>>

    @Update
    suspend fun update(property: Property)
}