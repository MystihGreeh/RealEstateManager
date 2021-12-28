package com.example.realestatemanager.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.realestatemanager.model.Agent
import com.example.realestatemanager.model.Property

@Database(entities = [Agent::class, Property::class], version = 1)

abstract class RealEstateManagerDatabase : RoomDatabase() {

    abstract fun getAgentDao(): AgentDao
    abstract fun getPropertyDao(): PropertyDao

    companion object {
        @Volatile
        private var INSTANCE: RealEstateManagerDatabase? = null

        fun getDatabase(context: Context): RealEstateManagerDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RealEstateManagerDatabase::class.java,
                    "RealEstateManager_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}