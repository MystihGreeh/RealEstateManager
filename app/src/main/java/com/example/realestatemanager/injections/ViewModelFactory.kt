package com.example.realestatemanager.injections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.realestatemanager.repositories.AgentRepository
import com.example.realestatemanager.repositories.PropertyRepository
import com.example.realestatemanager.viewModel.*

class ViewModelFactory(
    private val agentRepository: AgentRepository,
    private val propertyRepository: PropertyRepository,

) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(MainActivityViewModel::class.java) -> MainActivityViewModel(agentRepository, propertyRepository) as T
            modelClass.isAssignableFrom(AddAgentViewModel::class.java) -> AddAgentViewModel(agentRepository) as T
            modelClass.isAssignableFrom(AddPropertyViewModel::class.java) -> AddPropertyViewModel(propertyRepository) as T
            modelClass.isAssignableFrom(FragmentListViewModel::class.java) -> FragmentListViewModel(agentRepository, propertyRepository) as T
            modelClass.isAssignableFrom(FragmentPropertyDetailsViewModel::class.java) -> FragmentPropertyDetailsViewModel(agentRepository, propertyRepository) as T
            modelClass.isAssignableFrom(FragmentSearchViewModel::class.java) -> FragmentSearchViewModel(agentRepository, propertyRepository) as T
            modelClass.isAssignableFrom(FragmentMapViewModel::class.java) -> FragmentMapViewModel(agentRepository, propertyRepository) as T

            else -> throw Exception("Unknown ViewModel class")

        }

    }


}