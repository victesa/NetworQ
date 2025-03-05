package com.victorkirui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.design.theme.AppYellow
import com.victorkirui.resources.R
import com.victorkirui.utils.DateUtils
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
@Composable
fun DateTextField(
    textFieldValue: String,
    textLabel: String = DateUtils.getCurrentDate(),
    onDatePickerIsVisibleChange: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }


    val interactionSource = remember{MutableInteractionSource()}

    Column {
        Text(
            text = "Date *",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(50.dp)
                .border(
                    1.dp,
                    if (isFocused) AppYellow else Color.Gray,
                    RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 12.dp)
                .clickable { onDatePickerIsVisibleChange() } // Opens date picker
                .focusRequester(focusRequester)
                .onFocusChanged { isFocused = it.isFocused }
        ) {
            if (textFieldValue.isEmpty()) {
                Text(
                    text = textLabel,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.CenterStart).padding(start = 30.dp)
                )
            }

            BasicTextField(
                value = textFieldValue,
                onValueChange = {},
                textStyle = TextStyle(fontSize = 14.sp, color = Color.White),
                readOnly = true, // Prevent manual input
                cursorBrush = SolidColor(Color.White),
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterStart)
                    .focusRequester(focusRequester),
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.calendar),
                            contentDescription = "Calendar Icon",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        innerTextField()
                    }
                },
                interactionSource = interactionSource
            )

            LaunchedEffect(interactionSource){
                interactionSource.interactions.collect{interactions->
                    if (interactions is PressInteraction.Press){
                        onDatePickerIsVisibleChange()
                    }
                }
            }
        }
    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowDatePicker(onDismiss: () -> Unit, onConfirm: (String) -> Unit) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                val selectedDate = datePickerState.selectedDateMillis?.let { millis ->
                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    sdf.format(Date(millis))
                } ?: "No date selected"

                onConfirm(selectedDate)
            }) {
                Text("OK")
            }
        }
    ) {
        DatePicker(state = datePickerState)
    }
}

@Preview
@Composable
fun DateTextFieldPreview() {
    var datePickerIsVisible by rememberSaveable { mutableStateOf(false) }
    var selectedDate by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(16.dp)
    ) {
        DateTextField(
            textFieldValue = selectedDate,
            onDatePickerIsVisibleChange = { datePickerIsVisible = true },
        )

        if (datePickerIsVisible) {
            ShowDatePicker(
                onDismiss = { datePickerIsVisible = false },
                onConfirm = {
                    selectedDate = it
                    datePickerIsVisible = false
                }
            )
        }
    }
}
