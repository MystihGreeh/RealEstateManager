package com.example.realestatemanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "properties")

class Property(
    @ColumnInfo(name = "type") var typeOfGood: String,
    @ColumnInfo(name = "price") var priceInDollars: String,
    @ColumnInfo(name = "surface") var surfaceInMeters: String,
    @ColumnInfo(name = "number of room") var numberOfRoom: String,
    @ColumnInfo(name = "number of berdoom") var numberOfBedroom: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "street") var street: String,
    @ColumnInfo(name = "postal code") var postalcode: String,
    @ColumnInfo(name = "city") var city: String,
    @ColumnInfo(name = "country") var country: String,
    @ColumnInfo(name = "transportation") var transportation: String,
    @ColumnInfo(name = "market") var market: String,
    @ColumnInfo(name = "parks") var parks: String,
    @ColumnInfo(name = "parking") var parking: String,
    @ColumnInfo(name = "school") var school: String,
    @ColumnInfo(name = "select all") var selectAll: String,
    //@ColumnInfo(name = "is sold") var isSold: Boolean,
    @ColumnInfo(name = "agent") var agent: String,
    @ColumnInfo(name = "propertyImage") var propertyImage: String?,
    //@ColumnInfo(name = "timestamp") val timestamp:String
    ){


    @PrimaryKey(autoGenerate = true)
    var id = 0
}







