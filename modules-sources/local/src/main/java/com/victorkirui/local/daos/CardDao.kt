package com.victorkirui.local.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.victorkirui.local.entities.BusinessCard
import kotlinx.coroutines.flow.Flow

@Dao
interface CardDao {

    @Insert
    fun insert(businessCard: BusinessCard)

    @Query("SELECT * FROM BusinessCard")
    fun getAllBusinessCards(): Flow<List<BusinessCard>>

    @Query("DELETE FROM BusinessCard WHERE cardImage IN (:cardImagePath)")
    suspend fun deleteByCardImagePath(cardImagePath: List<String>)

}