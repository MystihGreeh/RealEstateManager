package com.example.realestatemanager.repositories

import androidx.lifecycle.LiveData
import com.example.realestatemanager.database.PhotoDao
import com.example.realestatemanager.model.PropertyPhoto

class PhotoPropertyRepository (private val photoDao: PhotoDao){

    val allPhotos: LiveData<List<PropertyPhoto>> = photoDao.getAllPhotos()

    suspend fun insert(photo: PropertyPhoto){
        photoDao.insert(photo)
    }

    suspend fun delete(photo: PropertyPhoto){
        photoDao.delete(photo)
    }

    suspend fun update(photo: PropertyPhoto){
        photoDao.update(photo)
    }

}