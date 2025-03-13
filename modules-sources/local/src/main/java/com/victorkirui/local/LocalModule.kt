package com.victorkirui.local

import androidx.room.Room
import com.victorkirui.local.daos.EventDao
import com.victorkirui.local.database.NetworQDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val localModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            NetworQDatabase::class.java,
            NetworQDatabase.DATABASE_NAME
        ).build()
    }

    single<EventDao> {
        get <NetworQDatabase>().eventDao()
    }


}