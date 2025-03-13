package com.victorkirui.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BusinessCard(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    val firstName: String,

    val lastName: String,

    val emailAddress: String,

    val phoneNumber: String,

    val companyName: String,

    val jobTitle: String,

    var website: String,

    var location: String,

    var cardImage: String
)
