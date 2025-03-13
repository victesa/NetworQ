package com.victorkirui.domain.models

data class AddCardModel(
    var cardImage: Int = com.victorkirui.resources.R.drawable.red_phoenix_card,
    var firstName: String = "",
    var lastName: String = "",
    var phoneNumber: String = "",
    var location: String = "",
    var emailAddress: String = "",

    var companyName: String = "",
    var jobTitle: String = "",
    var website: String = "",


    var cardImagePath: String = ""
)