package com.example.realestatemanager.repositories

import androidx.lifecycle.LiveData
import com.example.realestatemanager.database.PropertyDao
import com.example.realestatemanager.model.Property

class PropertyRepository (private val propertyDao: PropertyDao) {

    val allProperties: LiveData<List<Property>> = propertyDao.getAllProperties()

    val filteredProperties: LiveData<List<Property>> = propertyDao.getFilteredProperties()

    suspend fun insert(property: Property) : Long{
       return propertyDao.insert(property)

    }

    suspend fun delete(property: Property){
        propertyDao.delete(property)
    }

    suspend fun update(property: Property){
        propertyDao.update(property)
    }


    /*suspend fun select(property: Property){
        propertyDao.getPropertiesWithFilters()
    }*/
}