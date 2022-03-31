package com.example.realestatemanager.utils

class Constants {
    companion object {

        enum class Status(var status: String) {
            Available("Available"),
            Blocked("Blocked"),
            Sold("Sold")
        }

        enum class Amenity(var type: String) {
            School("School"),
            PublicTransport("Public Transport"),
            Mall("Mall"),
            ConvenienceStore("Convenience Store"),
            Park("Park"),
            PostOffice("Post Office")
        }

        enum class Type(var type: String) {
            Flat("Flat"),
            Duplex("Duplex"),
            Penthouse("Penthouse"),
            House("House"),
            Villa("Villa")
        }
    }
}