package com.example.realestatemanager.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.realestatemanager.model.Agent
import kotlinx.coroutines.flow.Flow

@Dao
interface AgentDao {
    @Query("SELECT * FROM agents")
    fun getAllAgents(): Flow<List<Agent>>

    @Query("SELECT * FROM agents WHERE agent_id = :agentId")
    suspend fun getAgent(agentId: String): List<Agent>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createAgent(agent: Agent)

    @Query("SELECT COUNT(*) FROM agents")
    suspend fun getRowCount(): Int


}