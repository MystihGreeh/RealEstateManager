package com.example.realestatemanager.viewModel

import androidx.lifecycle.ViewModel
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.repositories.AgentRepository
import com.example.realestatemanager.repositories.PropertyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddPropertyViewModel (private val repository: PropertyRepository, private val agentRepository: AgentRepository) : ViewModel() {

    val allProperties: Flow<List<Property>> = repository.allProperties

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(property: Property) = GlobalScope.launch {
        repository.insert(property)
    }

    suspend fun checkAgent() = withContext(Dispatchers.IO) {
        agentRepository.getCountAgent() > 0
    }
}


