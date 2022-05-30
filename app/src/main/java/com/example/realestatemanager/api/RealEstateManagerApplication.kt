package com.example.realestatemanager.api

import android.app.Application
import com.example.realestatemanager.database.RealEstateManagerDatabase
import com.example.realestatemanager.repositories.PhotoPropertyRepository
import com.example.realestatemanager.repositories.PropertyRepository

class RealEstateManagerApplication : Application(){
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { RealEstateManagerDatabase.getDatabase(this) }
    val repository by lazy { PropertyRepository(database.getPropertyDao())}
    val photoRepository by lazy { PhotoPropertyRepository(database.getPhotoDao())}

}