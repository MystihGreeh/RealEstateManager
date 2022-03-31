package com.example.realestatemanager.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.repositories.PropertyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FragmentListViewModel(private val repository : PropertyRepository) : ViewModel() {

    val allProperties: Flow<List<Property>> = repository.allProperties.asFlow()

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