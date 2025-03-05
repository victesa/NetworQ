package com.victorkirui.domain.usecases

import com.victorkirui.domain.models.EventModel
import com.victorkirui.domain.repositories.AddEventRepository
import com.victorkirui.utils.DateUtils

class SaveEventUseCase(private val addEventRepository: AddEventRepository) {
    suspend operator fun invoke(eventModel: EventModel){
        if (eventModel.eventName.isEmpty()){
            throw IllegalArgumentException("Event Name is Blank")
        }else if(eventModel.tag.isEmpty()){
            throw IllegalArgumentException("Tag cannot be blank")
        }else{
            if (eventModel.date.isEmpty()){
                addEventRepository.saveEventDetails(eventModel.copy(date = DateUtils.getCurrentDate()))
            }else{
                addEventRepository.saveEventDetails(eventModel)
            }
        }
    }
}