package com.example.realestatemanager.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.repositories.PropertyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddPropertyViewModel(private val repository : PropertyRepository) : ViewModel() {

    val allProperties: LiveData<List<Property>> = repository.allProperties

    fun deleteProperty(property: Property) = viewModelScope.launch(Dispatchers.IO){
        repository.delete(property)
    }

    fun updateProperty(property: Property) = viewModelScope.launch(Dispatchers.IO){
        repository.update(property)
    }

    fun addProperty(property: Property) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(property)
    }


}


