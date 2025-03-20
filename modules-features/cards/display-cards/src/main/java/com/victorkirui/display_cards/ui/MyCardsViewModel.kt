package com.victorkirui.display_cards.ui

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.victorkirui.domain.models.PersonalCards
import com.victorkirui.domain.usecases.DeleteCardsUseCase
import com.victorkirui.domain.usecases.GetImageFromFileUseCase
import com.victorkirui.domain.usecases.SendCardsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MyCardsViewModel(
    private val getImageFromFileUseCase: GetImageFromFileUseCase,
    private val sendCardsUseCase: SendCardsUseCase,
    private val deleteCardsUseCase: DeleteCardsUseCase
): ViewModel() {

    private val _myCards = MutableStateFlow<List<PersonalCards>>(emptyList())
    val myCards = _myCards.asStateFlow()

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()

    init {
        getAllCards()
    }

    private fun getAllCards(){
       viewModelScope.launch {
           getImageFromFileUseCase().collect{cards->
               _myCards.update {
                   cards
               }
           }
       }
    }

    fun onSelectionModeChanged(showSelectionMode: Boolean) {
        _state.update { currentState ->
            when (showSelectionMode) {
                true -> currentState.copy(selectionMode = true)
                false -> currentState.copy(selectionMode = false, selectedCards = emptyList())
            }
        }
    }

    fun addCardToSelection(card: PersonalCards){
        if (state.value.selectedCards.contains(card)){
            _state.update {
                it.copy(
                    selectedCards = state.value.selectedCards.filter {personalCards->
                        personalCards != card }
                )
            }
        }else{
            _state.update {
                it.copy(
                    selectedCards = state.value.selectedCards + card
                )
            }
        }
    }

    fun onSendCards(context: Context) {
        val imageNames = state.value.selectedCards.mapNotNull { it.cardImage?.name }

        if (imageNames.isNotEmpty()) {
            sendCardsUseCase.invoke(context, imageNames) // Pass the list
        } else {
            Log.d("Sending", "No images found")
        }

        _state.update {
            it.copy(
                progressDialogVisible = false,
                selectedCards = emptyList(),
                progressDialogText = ""
            )
        }
    }


    fun onDeleteCards(){
        viewModelScope.launch {
            _state.update {
                it.copy(
                    progressDialogVisible = true,
                    progressDialogText = "Deleting"
                )
            }
            deleteCardsUseCase.invoke(state.value.selectedCards)

            _state.update {
                it.copy(
                    progressDialogVisible = false,
                    selectedCards = emptyList(),
                    progressDialogText = ""
                )
            }
        }
    }

}


data class State(
    var selectedCards: List<PersonalCards> = emptyList(),
    var selectionMode: Boolean = false,
    var progressDialogVisible: Boolean = false,
    var progressDialogText: String = ""
)