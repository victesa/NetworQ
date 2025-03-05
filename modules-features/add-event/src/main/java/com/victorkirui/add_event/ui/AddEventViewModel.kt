package com.victorkirui.add_event.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.domain.models.EventModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddEventViewModel(): ViewModel() {

    private var _uiState = MutableStateFlow(EventModel())
    var uiState = _uiState.asStateFlow()

    private var _saveEventState = MutableStateFlow<EventSaveState>(EventSaveState.Idle)
    var saveEventState = _saveEventState.asStateFlow()

    fun onEventNameChange(name: String){
        _uiState.update {
            it.copy(
                eventName = name
            )
        }
    }

    fun onTagChange(tag: String){
        _uiState.update {
            it.copy(
                tag = tag
            )
        }
    }

    fun onLocationChange(location: String){
        _uiState.update {
            it.copy(
                location = location
            )
        }
    }

    fun onDateChange(date: String){
        _uiState.update {
            it.copy(
                date = date
            )
        }
    }

    fun onDescriptionChange(description: String){
        _uiState.update {
            it.copy(
                description = description
            )
        }
    }

    fun onDateIsSelected(isSelected: Boolean){
        _uiState.update {
            it.copy(
                dateIsSelected = isSelected
            )
        }
    }

    fun onDatePickerIsVisible(isVisible: Boolean){
        _uiState.update {
            it.copy(
                datePickerIsVisible = isVisible
            )
        }
    }

    fun saveEvent(){
        viewModelScope.launch {
            try {
                _saveEventState.value = EventSaveState.Loading
                _saveEventState.value = EventSaveState.Success
                onExit()
            }catch (e: Exception){
                _saveEventState.value =
                    EventSaveState.Error(e.message ?: "Unknown Error. Try again Later")
            }
        }
    }

    fun onExit(){
        _uiState.value = EventModel()
    }
}




sealed class EventSaveState{
    data object Idle: EventSaveState()
    data object Loading: EventSaveState()
    data object Success: EventSaveState()
    data class Error(val message: String): EventSaveState()
}