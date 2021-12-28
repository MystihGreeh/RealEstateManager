package com.example.realestatemanager.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.realestatemanager.repositories.AgentRepository
import com.example.realestatemanager.repositories.PropertyRepository

class ViewModelFactory(private val repository: PropertyRepository, private val agentRepository: AgentRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddPropertyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddPropertyViewModel(repository, agentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}