package com.victorkirui.data.repositories

import com.victorkirui.data.mappers.CardMappers
import com.victorkirui.domain.models.AddCardModel
import com.victorkirui.domain.repositories.CardRepository
import com.victorkirui.local.daos.CardDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class CardRepositoryImp(private val cardDao: CardDao): CardRepository {
    override suspend fun saveCard(addCardModel: AddCardModel) {
        withContext(Dispatchers.IO) {
            cardDao.insert(
                CardMappers.addCardModelToBusinessCardModel(addCardModel)
            )
        }
    }
}