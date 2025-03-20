package com.victorkirui.cards

import com.victorkirui.cards.ui.AddCardViewModel
import com.victorkirui.domain.domainModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val cardModule = module {
    includes(domainModule)

    viewModel {
        AddCardViewModel(get())
    }

}