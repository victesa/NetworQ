package com.victorkirui.domain.usecases

import com.victorkirui.domain.models.PersonalCards
import com.victorkirui.domain.repositories.CardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File

class GetImageFromFileUseCase(private val cardRepository: CardRepository){
    operator fun invoke(): Flow<List<PersonalCards>> {
        return cardRepository.getAllPersonalCards().map { list->
            list.map { businessCard ->
                val file = File(businessCard.cardImagePath)
                businessCard.copy(
                    cardImage = file
                )
            }
        }
    }
}

