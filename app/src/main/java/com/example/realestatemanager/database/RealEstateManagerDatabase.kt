package com.example.realestatemanager.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.realestatemanager.model.Property
import com.example.realestatemanager.model.PropertyPhoto

@Database(entities = [Property::class, PropertyPhoto::class],
    version = 1,
    exportSchema = false)


abstract class RealEstateManagerDatabase : RoomDatabase() {

    abstract fun getPropertyDao(): PropertyDao
    abstract fun getPhotoDao(): PhotoDao

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