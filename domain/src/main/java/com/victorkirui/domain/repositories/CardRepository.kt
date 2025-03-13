package com.victorkirui.domain.repositories

import com.victorkirui.domain.models.AddCardModel
import kotlinx.coroutines.flow.Flow

interface CardRepository {

    suspend fun saveCard(addCardModel: AddCardModel)

}