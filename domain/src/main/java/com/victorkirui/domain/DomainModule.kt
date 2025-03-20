package com.victorkirui.domain

import android.app.Application
import android.content.Context
import com.victorkirui.domain.repositories.AddEventRepository
import com.victorkirui.domain.repositories.CardRepository
import com.victorkirui.domain.usecases.DeleteCardsUseCase
import com.victorkirui.domain.usecases.GetImageFromFileUseCase
import com.victorkirui.domain.usecases.SaveCardUseCase
import com.victorkirui.domain.usecases.SaveEventUseCase
import com.victorkirui.domain.usecases.SendCardsUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val domainModule = module {
    factory { SaveEventUseCase(get<AddEventRepository>()) }

    factory {
        SaveCardUseCase(get(),
            get<CardRepository>())
    }

    factory {
        GetImageFromFileUseCase(get<CardRepository>())
    }

    single<Context> {
        get<Application>().applicationContext
    }

    factory{
        SendCardsUseCase()
    }

    factory{
        DeleteCardsUseCase(get())
    }

}