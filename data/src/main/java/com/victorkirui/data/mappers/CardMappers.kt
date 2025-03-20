package com.victorkirui.data.mappers

import com.victorkirui.domain.models.AddCardModel
import com.victorkirui.domain.models.PersonalCards
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
            website = addCardModel.website,
            cardImage = addCardModel.cardImagePath,
            jobTitle = addCardModel.jobTitle,
        )
    }

    fun personalCardsToBusinessCardModel(personalCards: PersonalCards): BusinessCard{
        return BusinessCard(
            firstName = personalCards.firstName,
            lastName = personalCards.lastName,
            emailAddress = personalCards.emailAddress,
            phoneNumber = personalCards.phoneNumber,
            companyName = personalCards.companyName,
            location = personalCards.location,
            website = personalCards.website,
            cardImage = personalCards.cardImagePath,
            jobTitle = personalCards.jobTitle,
        )
    }
}