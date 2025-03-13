package com.victorkirui.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val primaryKey: Int = 0,
    var eventName: String = "",
    var tags: String = "",
    var location: String? = null,
    var date: String = "",
    var description: String = ""
)
