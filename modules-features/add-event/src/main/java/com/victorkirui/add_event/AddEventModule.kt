package com.victorkirui.add_event

import com.victorkirui.add_event.ui.AddEventViewModel
import com.victorkirui.domain.domainModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val addEventModule = module {
    includes(domainModule)
    viewModel {
        AddEventViewModel(
            saveEventUseCase = get()
        )
    }
}