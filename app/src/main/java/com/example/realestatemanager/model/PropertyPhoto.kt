package com.example.realestatemanager.model

import androidx.room.*


@Entity(
    tableName = "propertiesPictures",
    indices = [Index(value = ["id_property"])],
    foreignKeys = [
        ForeignKey(
            entity = Property::class,
            parentColumns = ["property_id"],
            childColumns = ["id_property"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

data class PropertyPhoto(
    @PrimaryKey @ColumnInfo(name = "photo_id") var id: String = "",
    var label: String = "",
    var url: String = "",
    @ColumnInfo(name = "thumbnail_url") var thumbnailUrl: String? = null,
    @ColumnInfo(name = "server_url") var serverUrl: String? = null,
    @ColumnInfo(name = "id_property") var property: String? = null,
    @ColumnInfo(name = "photo_description") var description: String = "",
    @ColumnInfo(name = "order_number") var orderNumber: Int? = null)