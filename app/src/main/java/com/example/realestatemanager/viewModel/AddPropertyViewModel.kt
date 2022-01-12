package com.example.realestatemanager.viewModel

import androidx.lifecycle.ViewModel
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.repositories.PropertyRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AddPropertyViewModel(private val repository: PropertyRepository) : ViewModel() {

    val allProperties: Flow<List<Property>> = repository.allProperties

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(property: Property) = GlobalScope.launch {
        repository.insert(property)
    }

}


