package com.victorkirui.data

import com.victorkirui.data.mappers.EventMapper
import com.victorkirui.data.repositories.AddEventRepositoryImpl
import com.victorkirui.data.repositories.CardRepositoryImp
import com.victorkirui.domain.repositories.AddEventRepository
import com.victorkirui.domain.repositories.CardRepository
import com.victorkirui.local.localModule
import org.koin.dsl.module

val dataModule = module {
    includes(localModule)

    single {
        EventMapper
    }

    single<AddEventRepository>{
        AddEventRepositoryImpl(
            eventDao = get(),
            eventMapper = get()
        )
    }


    single<CardRepository> {
        CardRepositoryImp(
            cardDao = get(),
        )
    }

}