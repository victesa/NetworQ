package com.victorkirui.domain.repositories

import com.victorkirui.domain.models.EventModel


interface AddEventRepository {

    suspend fun saveEventDetails(eventModel: EventModel)
}