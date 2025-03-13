package com.victorkirui.cards.ui

import android.app.Activity
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import captureComposableAsBitmap
import com.victorkirui.cards.components.BlueConstructionCard
import com.victorkirui.cards.components.BluePhoenixCard
import com.victorkirui.cards.components.GreenLeafCard
import com.victorkirui.cards.components.PlainGreenCard
import com.victorkirui.cards.components.RedPhoenixCard
import com.victorkirui.domain.models.AddCardModel
import com.victorkirui.domain.usecases.SaveCardUseCase
import com.victorkirui.resources.CardOptions
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class AddCardViewModel(
    private val saveCardUseCase: SaveCardUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow(AddCardModel())
    val uiState = _uiState.asStateFlow()

    private val _cardState = MutableStateFlow(CardState())
    val cardState = _cardState.asStateFlow()

    val cardOptions = CardOptions.getAll()

    fun updateCardImage(image: Int) {
        _uiState.update {
            it.copy(
                cardImage = image
            )
        }

        _cardState.update {
            it.copy(
                cardDialogIsVisible = false
            )
        }
    }

    fun updateFirstName(firstName: String) {
        _uiState.update {
            it.copy(
                firstName = firstName
            )
        }
    }

    fun updateLastName(lastName: String) {
        _uiState.update {
            it.copy(
                lastName = lastName
            )
        }
    }

    fun updateEmailAddress(emailAddress: String) {
        _uiState.update {
            it.copy(
                emailAddress = emailAddress
            )
        }
    }

    fun updateCompanyName(companyName: String) {
        _uiState.update {
            it.copy(
                companyName = companyName
            )
        }
    }

    fun updateJobTitle(jobTitle: String) {
        _uiState.update {
            it.copy(
                jobTitle = jobTitle
            )
        }
    }

    fun updateWebsite(website: String) {
        _uiState.update {
            it.copy(
                website = website
            )
        }
    }

    fun onDialogVisibilityChange(isVisible: Boolean){
        Log.d("DialogVisibility", "Dialog visibility changed to: $isVisible")
        _cardState.update {
            it.copy(
                cardDialogIsVisible = isVisible
            )
        }
    }

    fun updatePhoneNumber(phoneNumber: String){
        _uiState.update {
            it.copy(
                phoneNumber = phoneNumber
            )
        }
    }

    fun updateLocation(location: String){
        _uiState.update {
            it.copy(
                location = location
            )
        }
    }

    fun onSaveDetails(activity: Activity) {
        Log.d("SavingCard", "Saving process started")
        viewModelScope.launch {
            _cardState.update {
                it.copy(
                    savingDialogIsVisible = true
                )
            }

            val selectedOption = CardOptions.entries.firstOrNull { it.resId == uiState.value.cardImage }
                ?: CardOptions.OPTION_1

            try {
                val composable = backgroundImageComposable(
                    selectedOption = selectedOption
                )

                _cardState.update {
                    it.copy(
                        savingDialogProgress = .25f // Start with 25%
                    )
                }

                val bitmap = withContext(Dispatchers.IO) {
                    Log.d("SavingCard", "Capturing composable as bitmap")
                    captureComposableAsBitmap(
                        activity = activity,
                        content = composable
                    )
                }

                _cardState.update {
                    it.copy(
                        savingDialogProgress = .5f // 50% after bitmap capture
                    )
                }

                saveCardUseCase.invoke(
                    addCardModel = uiState.value,
                    bitmap = bitmap,
                    onBitMapReady = {
                        Log.d("SavingCard", "Bitmap ready callback")
                        _cardState.update {
                            it.copy(
                                savingDialogProgress = .75f // 75% after bitmap ready
                            )
                        }
                    },
                    onDetailsStored = {
                        Log.d("SavingCard", "Details stored callback")
                        _cardState.update {
                            it.copy(
                                savingDialogProgress = 1f,
                                savingDialogIsVisible = false// 100% after details stored
                            )
                        }
                    },
                    onImageStored = {
                        Log.d("SavingCard", "Image stored callback")
                    }
                )
            } catch (e: IOException) {
                Log.e("SavingCard", "File I/O error: ${e.message}")
                _cardState.update {
                    it.copy(
                        errorSavingCard = "Error saving image: ${e.message}",
                        savingDialogIsVisible = false
                    )
                }
            } catch (e: IllegalStateException) {
                Log.e("SavingCard", "Illegal state error: ${e.message}")
                _cardState.update {
                    it.copy(
                        errorSavingCard = "Error capturing image: ${e.message}",
                        savingDialogIsVisible = false
                    )
                }
            } catch (e: Exception) {
                Log.e("SavingCard", "General error: ${e.message}")
                _cardState.update {
                    it.copy(
                        errorSavingCard = "An unexpected error occurred: ${e.message}",
                        savingDialogIsVisible = false)
                }}}}

    fun clearError(){
        _cardState.update {
            it.copy(
                errorSavingCard = ""
            )
        }
    }

    private fun backgroundImageComposable(selectedOption: CardOptions): @Composable () -> Unit {
        return when (selectedOption) {
            CardOptions.OPTION_1 -> { { RedPhoenixCard(
                fullName = "${uiState.value.firstName} ${uiState.value.lastName}",
                companyName = uiState.value.companyName,
                location = uiState.value.location, // Fixed location
                jobTitle = uiState.value.jobTitle,
                website = uiState.value.website,
                phoneNumber = uiState.value.phoneNumber,
                emailAddress = uiState.value.emailAddress
            ) } }
            CardOptions.OPTION_2 -> { { BlueConstructionCard(
                fullName = "${uiState.value.firstName} ${uiState.value.lastName}",
                companyName = uiState.value.companyName,
                location = uiState.value.location, // Fixed location
                jobTitle = uiState.value.jobTitle,
                website = uiState.value.website,
                phoneNumber = uiState.value.phoneNumber,
                emailAddress = uiState.value.emailAddress
            ) } }
            CardOptions.OPTION_3 -> { { BluePhoenixCard(
                fullName = "${uiState.value.firstName} ${uiState.value.lastName}",
                companyName = uiState.value.companyName,
                location = uiState.value.location, // Fixed location
                jobTitle = uiState.value.jobTitle,
                website = uiState.value.website,
                phoneNumber = uiState.value.phoneNumber,
                emailAddress = uiState.value.emailAddress
            ) } }
            CardOptions.OPTION_4 -> { { GreenLeafCard(
                fullName = "${uiState.value.firstName} ${uiState.value.lastName}",
                companyName = uiState.value.companyName,
                location = uiState.value.location, // Fixed location
                jobTitle = uiState.value.jobTitle,
                website = uiState.value.website,
                phoneNumber = uiState.value.phoneNumber,
                emailAddress = uiState.value.emailAddress
            ) } }
            CardOptions.OPTION_5 -> { { PlainGreenCard(
                fullName = "${uiState.value.firstName} ${uiState.value.lastName}",
                companyName = uiState.value.companyName,
                location = uiState.value.location, // Fixed location
                jobTitle = uiState.value.jobTitle,
                website = uiState.value.website,
                phoneNumber = uiState.value.phoneNumber,
                emailAddress = uiState.value.emailAddress
            ) } }
        }
    }

}

data class CardState(
    var cardDialogIsVisible: Boolean = false,

    var savingDialogIsVisible: Boolean = false,
    var savingDialogProgress: Float = 0f,

    var errorSavingCard: String = ""
)