package com.example.realestatemanager.repositories

import androidx.lifecycle.LiveData
import com.example.realestatemanager.database.PropertyDao
import com.example.realestatemanager.model.Property

class PropertyRepository (private val propertyDao: PropertyDao) {

    val allProperties: LiveData<List<Property>> = propertyDao.getAllProperties()
    //val propertiesFiltered: LiveData<List<Property>> = propertyDao.getPropertiesWithFilters()


    suspend fun insert(property: Property) : Long{
       return propertyDao.insert(property)

    }

    fun delete(property: Property){
        propertyDao.delete(property)
    }

    suspend fun update(property: Property){
        propertyDao.update(property)
    }


    fun select(priceMini: Int, priceMax: Int, surfaceMini: Int, surfaceMax: Int, roomMini: Int,
               roomMax: Int): LiveData<List<Property>> {
         return propertyDao.getPropertiesWithFilters(priceMini, priceMax, surfaceMini, surfaceMax, roomMini, roomMax)

    }
}