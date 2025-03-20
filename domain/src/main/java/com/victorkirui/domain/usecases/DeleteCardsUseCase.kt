package com.victorkirui.domain.usecases

import android.util.Log
import com.victorkirui.domain.models.AddCardModel
import com.victorkirui.domain.models.PersonalCards
import com.victorkirui.domain.repositories.CardRepository
import kotlinx.coroutines.flow.collectLatest

class DeleteCardsUseCase(
    private val cardRepository: CardRepository
) {
    suspend operator fun invoke(cards: List<PersonalCards>){
        Log.d("DeleteCardsUseCase", "Deleting cards: $cards")
        cardRepository.deleteCard(cards)
    }
}