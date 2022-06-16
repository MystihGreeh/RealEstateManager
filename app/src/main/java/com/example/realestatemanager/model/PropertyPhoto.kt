package com.example.realestatemanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey


@Entity(tableName = "photos",
    foreignKeys = [
        ForeignKey(
            entity = Property::class,
            parentColumns = ["property_id"],
            childColumns = ["property"],
            onDelete = CASCADE)
    ])

data class PropertyPhoto(
    @ColumnInfo(name = "property") var property: Long?,
    @ColumnInfo(name = "photo_name") var name: String,
    @ColumnInfo(name = "photo_description") var description: String,
){


    @PrimaryKey(autoGenerate = true)
    var photo_id = 0}