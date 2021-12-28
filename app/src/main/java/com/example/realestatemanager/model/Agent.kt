package com.example.realestatemanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "agents")
data class Agent (

    @ColumnInfo(name = "first_name")val firstName: String = "",
    @ColumnInfo(name = "last_name")val lastName: String = "",
    @ColumnInfo (name = "email")val email: String = "",
    @ColumnInfo(name = "phone_number")val phoneNumber: String = "")

{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "agent_id")val id:Int? = null

}

