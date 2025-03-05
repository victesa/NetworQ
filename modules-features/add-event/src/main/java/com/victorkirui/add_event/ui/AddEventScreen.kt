package com.victorkirui.add_event.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.victorkirui.common.components.CustomTextField
import com.victorkirui.common.components.DateTextField
import com.victorkirui.common.components.DescriptionTextField
import com.victorkirui.common.components.ShowDatePicker

@Composable
fun AddEventRoute(
    windowSizeClass: WindowSizeClass,
    viewModel: AddEventViewModel = viewModel(),
    navigateBack: () -> Unit
) {
    val isCompact = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact
    val uiState by viewModel.uiState.collectAsState()
    val eventSaveState by viewModel.saveEventState.collectAsState()
    val context = LocalContext.current

    // Common UI Layout
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        AddEventScreen(
            exitEditScreen = {
                viewModel.onExit()
                TODO() },
            tagTextValue = uiState.tag,
            onDatePickerIsVisibleChange = {
                viewModel.onDatePickerIsVisible(true)
            },
            eventNameTextValue = uiState.eventName,
            onTagTextValueChange = {
                viewModel.onTagChange(it)
            },
            dateTextFieldValue = uiState.date,
            descriptionTextValue = uiState.description,
            locationTextValue = uiState.location,
            onEventNameTextValueChange = {
                viewModel.onEventNameChange(it)
            },
            onSaveButtonClick = { TODO() },
            onLocationTextValueChange = {
                viewModel.onLocationChange(it)
            },
            onDescriptionTextValueChange = {
                viewModel.onDescriptionChange(it)
            },
            isCompact = isCompact
        )

        // Show Date Picker if Visible
        if (uiState.datePickerIsVisible) {
            ShowDatePicker(
                onDismiss = { viewModel.onDatePickerIsVisible(false) },
                onConfirm = {
                    viewModel.onDateChange(it)
                    viewModel.onDateIsSelected(true)
                    viewModel.onDatePickerIsVisible(false)
                }
            )
        }

        // Handle Different UI States
        when (eventSaveState) {
            EventSaveState.Idle -> Unit // No extra UI needed
            EventSaveState.Loading -> {
                // Show Loading Indicator
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.White
                )
            }
            EventSaveState.Success -> {
                navigateBack() // Navigate back when event is successfully saved
            }
            is EventSaveState.Error -> {
                val errorMessage = (eventSaveState as EventSaveState.Error).message
                // Show error toast
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}




@Composable
fun AddEventScreen(exitEditScreen:() -> Unit,
                          eventNameTextValue: String,
                          onEventNameTextValueChange: (String) -> Unit,
                          tagTextValue: String,
                          onTagTextValueChange: (String) -> Unit,
                          locationTextValue: String,
                          onLocationTextValueChange: (String) -> Unit,
                          dateTextFieldValue: String,
                          onDatePickerIsVisibleChange:() -> Unit,
                          descriptionTextValue: String,
                          onDescriptionTextValueChange: (String) -> Unit,
                          onSaveButtonClick: () -> Unit,
                          isCompact: Boolean){
    val paddingValue = if (isCompact) 24.dp else 32.dp

    var descriptionIsHidden by remember { mutableStateOf(false) }

    val modifier = if(descriptionIsHidden) Modifier.fillMaxWidth().padding(horizontal = paddingValue).fillMaxHeight(.9f).imePadding() else Modifier.fillMaxWidth().padding(horizontal = paddingValue).fillMaxHeight(.9f)

    Column(modifier = Modifier.fillMaxSize()){

        Box(modifier = Modifier.fillMaxWidth().padding(horizontal = (paddingValue / 2), vertical = paddingValue)){
            Icon(painter = painterResource(com.victorkirui.resources.R.drawable.cancel),
                contentDescription = "Cancel",
                modifier = Modifier
                    .clickable { exitEditScreen() }
                    .size(40.dp)
                    .padding(bottom = 8.dp), tint = Color.White)}

        LazyColumn(modifier = modifier){


            item {
                CustomTextField(
                    text = "Event Name *",
                    value = eventNameTextValue,
                    onValueChange = onEventNameTextValueChange,
                    label = "Name"
                )

            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {

                CustomTextField(
                    text = "Tag *",
                    value = tagTextValue,
                    onValueChange = onTagTextValueChange,
                    label = "E.g. Tech Marketing"
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                CustomTextField(
                    text = "Location",
                    value = locationTextValue,
                    onValueChange = onLocationTextValueChange,
                    label = "E.g Nairobi, Kenya"
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                DateTextField(
                    textFieldValue = dateTextFieldValue,
                    onDatePickerIsVisibleChange = {onDatePickerIsVisibleChange()},
                )
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                DescriptionTextField(
                    textFieldValue = descriptionTextValue,
                    onTextFieldValueChange = {onDescriptionTextValueChange(it)},
                    onComponentHidden = {descriptionIsHidden = true},
                    onComponentVisible = {descriptionIsHidden = false}
                )
            }
        }

        Box(modifier = Modifier.fillMaxSize().padding(horizontal = paddingValue), contentAlignment = Alignment.Center){
            Button(
                colors = ButtonDefaults.buttonColors(containerColor = Color.White,
                    contentColor = Color.Black,
                    disabledContentColor = Color.Transparent,
                    disabledContainerColor = Color.Gray),
                onClick = {
                    onSaveButtonClick()
                },
                content = {
                    Text(text = "Save", modifier = Modifier.padding(8.dp))
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}


@Preview
@Composable
fun AddEventCompactScreenPreview(){

    var datePickerIsVisible by rememberSaveable { mutableStateOf(false) }
    var selectedDate by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)){

        AddEventScreen(exitEditScreen = {},
            tagTextValue = "",
            onDatePickerIsVisibleChange = {datePickerIsVisible = true},
            eventNameTextValue = "",
            onTagTextValueChange = {},
            dateTextFieldValue = selectedDate,
            descriptionTextValue = "",
            locationTextValue = "",
            onEventNameTextValueChange = {},
            onSaveButtonClick = {},
            onLocationTextValueChange = {},
            onDescriptionTextValueChange = {},
            isCompact = true)


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

@Preview(device = "spec:width=673dp,height=841dp")
@Composable
fun AddEventMediumScreenPreview(){

    var datePickerIsVisible by rememberSaveable { mutableStateOf(false) }
    var selectedDate by rememberSaveable { mutableStateOf("") }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)){

        AddEventScreen(exitEditScreen = {},
            tagTextValue = "",
            onDatePickerIsVisibleChange = {datePickerIsVisible = true},
            eventNameTextValue = "",
            onTagTextValueChange = {},
            dateTextFieldValue = "",
            descriptionTextValue = "",
            locationTextValue = "",
            onEventNameTextValueChange = {},
            onSaveButtonClick = {},
            onLocationTextValueChange = {},
            onDescriptionTextValueChange = {},
            isCompact = false)

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