package com.example.realestatemanager.repositories

import com.example.realestatemanager.database.RealEstateManagerDatabase
import com.example.realestatemanager.model.Agent
import kotlinx.coroutines.flow.Flow

class AgentRepository (private val database: RealEstateManagerDatabase){


    val allAgents: Flow<List<Agent>> = database.getAgentDao().getAllAgents()


    suspend fun insert(agent: Agent) {
        database.getAgentDao().createAgent(agent)
    }

    suspend fun getAgent(agentId: String) : List<Agent> {
        return database.getAgentDao().getAgent(agentId)
    }

    suspend fun getCountAgent() : Int {
        return database.getAgentDao().getRowCount()
    }
}