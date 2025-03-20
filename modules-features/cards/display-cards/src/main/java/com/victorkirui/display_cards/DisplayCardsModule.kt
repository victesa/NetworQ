package com.victorkirui.display_cards

import com.victorkirui.display_cards.ui.MyCardsViewModel
import com.victorkirui.domain.domainModule
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val displayCardsModule = module {
    includes(domainModule)
    viewModel {
        MyCardsViewModel(
            getImageFromFileUseCase = get(),
            sendCardsUseCase = get(),
            deleteCardsUseCase = get(),
        )
    }
}
