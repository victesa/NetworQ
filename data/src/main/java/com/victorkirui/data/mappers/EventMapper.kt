package com.victorkirui.data.mappers

import com.victorkirui.domain.models.EventModel
import com.victorkirui.local.entities.EventEntity

object EventMapper {
    fun mapToEntity(eventModel: EventModel): EventEntity {
        return EventEntity(
            eventName = eventModel.eventName,
            tags = eventModel.tag,
            location = eventModel.location,
            date = eventModel.date,
            description = eventModel.description
        )
    }
}
