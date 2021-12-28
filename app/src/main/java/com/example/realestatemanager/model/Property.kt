package com.example.realestatemanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "properties",
    foreignKeys = [
        ForeignKey(
            entity = Agent::class,
            parentColumns = ["agent_id"],
            childColumns = ["agent"],
            onDelete = ForeignKey.NO_ACTION)])

data class Property(
    @ColumnInfo(name = "property_id") @PrimaryKey var id: String = "",
    @ColumnInfo(name = "type_property")var type:  TypeProperty = TypeProperty.HOUSE,
    var priceInDollars: Int,
    var surfaceInMeters: Int = 0,
    var numberOfRoom: Int = 0,
    var numberOfBedroom: Int = 0,
    //var nbrBathroom: Int = 0,
    var description: String = "",
    var street: String,
    var postcode: String,
    var city: String,
    var country: String,
    var isSold: Boolean = false,
    var availableDate: String,
    var soldDate: String = "",
    var agentId: Int,
    var coverPhoto: String,
    var labelPhoto: String,
    var agent: String)

enum class TypeProperty(val typeName: String) {
    FLAT("Flat"),
    TOWNHOUSE("Townhouse"),
    PENTHOUSE("Penthouse"),
    HOUSE("House"),
    DUPLEX("Duplex")
}
