package com.victorkirui.domain.repositories

import com.victorkirui.domain.models.AddCardModel
import com.victorkirui.domain.models.PersonalCards
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun saveCard(addCardModel: AddCardModel)

    suspend fun deleteCard(cards: List<PersonalCards>)

    fun getAllPersonalCards(): Flow<List<PersonalCards>>
}