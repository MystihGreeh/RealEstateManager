package com.example.realestatemanager.model


import android.content.ContentValues
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable


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
    @ColumnInfo(name = "createdTimestamp") var createdTimestamp:String,
    @ColumnInfo(name = "soldTimestamp") var soldTimestamp:String,
    @ColumnInfo(name = "fullAddress") var fullAddress:String,
    @ColumnInfo(name = "longitude") var longitude: Double?,
    @ColumnInfo(name = "latitude") var latitude: Double?,
    @ColumnInfo(name = "propertyStaticMapUrl") var propertyStaticMapUrl: String?
    ) : Serializable {


    // --- UTILS ---
    fun fromContentValues(values: ContentValues): Property {
        val property = Property(id, typeOfGood, priceInDollars, surfaceInMeters, numberOfRoom, numberOfBedroom, description, street, postalcode, city, country, transportation, market, parks, parking, school, selectAll, isSold, agent, propertyImage, createdTimestamp, soldTimestamp, fullAddress, longitude, latitude, propertyStaticMapUrl)
        if (values.containsKey("property_id")) property.id =(values.getAsLong("text"))
        if (values.containsKey("type")) property.typeOfGood =(values.getAsString("category"))
        if (values.containsKey("price")) property.priceInDollars = (values.getAsString("isSelected"))
        if (values.containsKey("surface")) property.surfaceInMeters = (values.getAsString("userId"))
        if (values.containsKey("number of room")) property.numberOfRoom = (values.getAsString("text"))
        if (values.containsKey("number of berdoom")) property.numberOfBedroom = (values.getAsString("text"))
        if (values.containsKey("description")) property.description = (values.getAsString("text"))
        if (values.containsKey("street")) property.street = (values.getAsString("text"))
        if (values.containsKey("postal code")) property.postalcode = (values.getAsString("text"))
        if (values.containsKey("city")) property.city = (values.getAsString("text"))
        if (values.containsKey("country")) property.country = (values.getAsString("text"))
        if (values.containsKey("transportation")) property.transportation = (values.getAsBoolean("text"))
        if (values.containsKey("market")) property.market = (values.getAsBoolean("text"))
        if (values.containsKey("parks")) property.parks = (values.getAsBoolean("text"))
        if (values.containsKey("parking")) property.parking = (values.getAsBoolean("text"))
        if (values.containsKey("school")) property.school = (values.getAsBoolean("text"))
        if (values.containsKey("select all")) property.selectAll = (values.getAsBoolean("text"))
        if (values.containsKey("is sold")) property.isSold = (values.getAsBoolean("text"))
        if (values.containsKey("agent")) property.agent = (values.getAsString("text"))
        if (values.containsKey("propertyImage")) property.propertyImage = (values.getAsString("text"))
        if (values.containsKey("createdTimestamp")) property.createdTimestamp = (values.getAsString("text"))
        if (values.containsKey("soldTimestamp")) property.soldTimestamp = (values.getAsString("text"))
        if (values.containsKey("fullAddress")) property.fullAddress = (values.getAsString("text"))
        if (values.containsKey("longitude")) property.longitude = (values.getAsDouble("text"))
        if (values.containsKey("latitude")) property.latitude = (values.getAsDouble("text"))
        if (values.containsKey("propertyStaticMapUrl")) property.propertyStaticMapUrl = (values.getAsString("text"))

        return property
    }

}







