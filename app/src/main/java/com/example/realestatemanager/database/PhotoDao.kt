package com.example.realestatemanager.database


import androidx.room.*
import com.example.realestatemanager.model.PropertyPhoto

@Dao
interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPhoto(photos: List<PropertyPhoto>)

    @Query("DELETE FROM propertiesPictures WHERE photo_id IN (:photoId)")
    suspend fun deletePhotos(photoId: List<String>)

    @Update
    suspend fun updatePhoto(photos: List<PropertyPhoto>)
}