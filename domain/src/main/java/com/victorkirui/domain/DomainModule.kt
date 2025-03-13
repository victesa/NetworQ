package com.victorkirui.domain

import com.victorkirui.domain.repositories.AddEventRepository
import com.victorkirui.domain.repositories.CardRepository
import com.victorkirui.domain.usecases.SaveCardUseCase
import com.victorkirui.domain.usecases.SaveEventUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { SaveEventUseCase(get<AddEventRepository>()) }

    factory {
        SaveCardUseCase(get(),
            get<CardRepository>())
    }

}