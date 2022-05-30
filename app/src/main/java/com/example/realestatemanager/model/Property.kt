package com.example.realestatemanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "properties")

class Property(
    @ColumnInfo(name = "property_id") @PrimaryKey var id: Long?,
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
    @ColumnInfo(name = "transportation") var transportation: Boolean,
    @ColumnInfo(name = "market") var market: Boolean,
    @ColumnInfo(name = "parks") var parks: Boolean,
    @ColumnInfo(name = "parking") var parking: Boolean,
    @ColumnInfo(name = "school") var school: Boolean,
    @ColumnInfo(name = "select all") var selectAll: Boolean,
    @ColumnInfo(name = "is sold") var isSold: Boolean,
    @ColumnInfo(name = "agent") var agent: String,
    @ColumnInfo(name = "propertyImage") var propertyImage: String,
    @ColumnInfo(name = "createdTimestamp") val createdTimestamp:String,
    @ColumnInfo(name = "soldTimestamp") val soldTimestamp:String,
    @ColumnInfo(name = "fullAddress") val fullAddress:String,
    @ColumnInfo(name = "longitude") val longitude: Double?,
    @ColumnInfo(name = "latitude") val latitude: Double?,
    @ColumnInfo(name = "propertyStaticMapUrl") val propertyStaticMapUrl: String?
    ){

}







