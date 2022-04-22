package com.example.realestatemanager.model

class Address {
    data class Address(
        val street: String?,
        val city: String,
        val postalCode: Int,
        val country: String,
        var propertyLatitude: Double? = null,
        var propertyLongitude: Double? = null,

        )
}