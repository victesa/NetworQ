package com.victorkirui.data.repositories

import com.victorkirui.data.mappers.EventMapper
import com.victorkirui.domain.models.EventModel
import com.victorkirui.domain.repositories.AddEventRepository
import com.victorkirui.local.daos.EventDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AddEventRepositoryImpl(private val eventDao: EventDao,
    private val eventMapper: EventMapper
): AddEventRepository {
    override suspend fun saveEventDetails(eventModel: EventModel) {
        withContext(Dispatchers.IO){
            eventDao.insertEvent(
                eventMapper.mapToEntity(eventModel)
            )
        }
    }
}