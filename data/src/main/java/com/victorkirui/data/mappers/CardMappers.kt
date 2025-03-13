package com.victorkirui.data.mappers

import com.victorkirui.domain.models.AddCardModel
import com.victorkirui.local.entities.BusinessCard

object CardMappers {
    fun addCardModelToBusinessCardModel(addCardModel: AddCardModel): BusinessCard{
        return BusinessCard(
            firstName = addCardModel.firstName,
            lastName = addCardModel.lastName,
            emailAddress = addCardModel.emailAddress,
            phoneNumber = addCardModel.phoneNumber,
            companyName = addCardModel.companyName,
            location = addCardModel.location,
            website = addCardModel.location,
            cardImage = addCardModel.cardImagePath,
            jobTitle = addCardModel.jobTitle,
        )
    }
}