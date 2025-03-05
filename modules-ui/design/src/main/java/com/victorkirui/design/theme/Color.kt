package com.victorkirui.design.theme

import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.TextFieldColors
import androidx.compose.ui.graphics.Color


val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val AppYellow = Color(0xFFD9F502)

val CustomTextFieldColors = TextFieldColors(
    focusedTextColor = Color.White,
    unfocusedTextColor = Color.Gray,
    disabledTextColor = Color.LightGray,
    errorTextColor = Color.Red,

    focusedContainerColor = Color.Black,
    unfocusedContainerColor = Color.Black,
    disabledContainerColor = Color.LightGray,
    errorContainerColor = Color.Black,

    cursorColor = AppYellow,
    errorCursorColor = Color.Red,
    textSelectionColors = TextSelectionColors(
        handleColor = Color.Black,
        backgroundColor = Color.Transparent
    ),

    focusedIndicatorColor = AppYellow,
    unfocusedIndicatorColor = Color.Gray,
    disabledIndicatorColor = Color.LightGray,
    errorIndicatorColor = Color.Red,

    focusedLeadingIconColor = AppYellow,
    unfocusedLeadingIconColor = Color.Gray,
    disabledLeadingIconColor = Color.LightGray,
    errorLeadingIconColor = Color.Red,

    focusedTrailingIconColor = AppYellow,
    unfocusedTrailingIconColor = Color.Gray,
    disabledTrailingIconColor = Color.LightGray,
    errorTrailingIconColor = Color.Red,

    focusedLabelColor = AppYellow,
    unfocusedLabelColor = Color.Gray,
    disabledLabelColor = Color.LightGray,
    errorLabelColor = Color.Red,

    focusedPlaceholderColor = AppYellow,
    unfocusedPlaceholderColor = Color.LightGray,
    disabledPlaceholderColor = Color.DarkGray,
    errorPlaceholderColor = Color.Red,

    focusedSupportingTextColor = AppYellow,
    unfocusedSupportingTextColor = Color.Gray,
    disabledSupportingTextColor = Color.LightGray,
    errorSupportingTextColor = Color.Red,

    focusedPrefixColor = AppYellow,
    unfocusedPrefixColor = Color.Gray,
    disabledPrefixColor = Color.LightGray,
    errorPrefixColor = Color.Red,

    focusedSuffixColor = AppYellow,
    unfocusedSuffixColor = Color.Gray,
    disabledSuffixColor = Color.LightGray,
    errorSuffixColor = Color.Red
)