package com.victorkirui.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.victorkirui.local.daos.EventDao
import com.victorkirui.local.entities.EventEntity

@Database(entities = [EventEntity::class], version = 1, exportSchema = false)
abstract class NetworQDatabase: RoomDatabase() {
    companion object{
        const val DATABASE_NAME = "NetworQ_Database"
    }
    abstract fun eventDao(): EventDao
}