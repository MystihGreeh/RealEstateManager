package com.example.realestatemanager.injections

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.realestatemanager.repositories.AgentRepository
import com.example.realestatemanager.viewModel.AddAgentViewModel

class ViewModelFactory ( private val agentRepository: AgentRepository) : ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{
            modelClass.isAssignableFrom(AddAgentViewModel::class.java) -> AddAgentViewModel(agentRepository) as T

            else -> throw Exception("Unknown ViewModel class")

        }
    }
}