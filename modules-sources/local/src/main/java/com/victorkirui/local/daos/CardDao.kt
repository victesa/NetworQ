package com.victorkirui.local.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.victorkirui.local.entities.BusinessCard
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Insert
    fun insert(businessCard: BusinessCard)

}