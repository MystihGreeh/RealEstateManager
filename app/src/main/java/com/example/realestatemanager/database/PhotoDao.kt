package com.example.realestatemanager.database


import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.realestatemanager.model.PropertyPhoto

@Dao
interface PhotoDao  {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(photo: PropertyPhoto)

    @Delete
    suspend fun delete(photo: PropertyPhoto)

    @Query("Select * from photos order by photo_id ASC")
    fun getAllPhotos(): LiveData<List<PropertyPhoto>>


    @Update
    suspend fun update(photo: PropertyPhoto)
}