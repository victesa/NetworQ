package com.victorkirui.local.daos

import androidx.room.Dao
import androidx.room.Insert
import com.victorkirui.local.entities.EventEntity

@Dao
interface EventDao {

    @Insert
    fun insertEvent(eventEntity: EventEntity)
}