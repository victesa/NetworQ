package com.victorkirui.domain.models

data class EventModel(
    var eventName: String = "",
    var tag: String = "",
    var location: String = "",
    var date: String = "",
    var description: String = "",
    var dateIsSelected: Boolean = false,
    var datePickerIsVisible: Boolean = false,
    var eventIsSaved: Boolean = false
)