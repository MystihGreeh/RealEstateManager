package com.example.realestatemanager.viewModel

import androidx.lifecycle.ViewModel
import com.example.realestatemanager.model.Agent
import com.example.realestatemanager.repositories.AgentRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AddAgentViewModel (private val repository: AgentRepository) : ViewModel(){
    val allAgents: Flow<List<Agent>> = repository.allAgents

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    @DelicateCoroutinesApi
    fun insert(agent: Agent) = GlobalScope.launch {
        repository.insert(agent)
    }


}