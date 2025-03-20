package com.victorkirui.domain.models

import java.io.File

data class PersonalCards(
    var cardImage: File? = null,
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
