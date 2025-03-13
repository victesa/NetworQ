package com.victorkirui.cards.ui

import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.cards.components.ImageSelectionDialog
import com.victorkirui.cards.components.SavingProgressDialog
import com.victorkirui.common.components.CustomTextField
import com.victorkirui.design.theme.AppYellow
import com.victorkirui.resources.CardOptions
import com.victorkirui.resources.R
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddCardRoute(windowSizeClass: WindowSizeClass, viewModel: AddCardViewModel = koinViewModel(), activity: Activity){
    val isCompact = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

    val uiState by viewModel.uiState.collectAsState()
    val cardState by viewModel.cardState.collectAsState()

    AddCardScreen(isCompact = isCompact,
        firstNameValue = uiState.firstName,
        onFirstNameValueChange = {viewModel.updateFirstName(it)},
        lastNameValue = uiState.lastName,
        onLastNameValueChange = {viewModel.updateLastName(it)},
        emailAddressValue = uiState.emailAddress,
        onEmailAddressValueChange = {viewModel.updateEmailAddress(it)},
        companyNameValue = uiState.companyName,
        onCompanyNameValueChange = {viewModel.updateCompanyName(it)},
        jobTitleValue = uiState.jobTitle,
        onJobTitleValueChange = {viewModel.updateJobTitle(it)},
        websiteValue = uiState.website,
        onWebsiteValueChange = {viewModel.updateWebsite(it)},
        isDialogVisible = cardState.cardDialogIsVisible,
        onDialogVisibilityChange = { viewModel.onDialogVisibilityChange(it) },
        images = viewModel.cardOptions,
        onImageSelected = {
            Log.d("ImageSelection", "Card Selected: $it")
            viewModel.updateCardImage(it)
        },
        phoneNumberValue = uiState.phoneNumber,
        onPhoneNumberValueChange = { viewModel.updatePhoneNumber(it) },
        locationValue = uiState.location,
        onLocationValueChange = {viewModel.updateLocation(it)},
        onSaveDetails = {viewModel.onSaveDetails(activity)},
        savingDialogIsVisible = cardState.savingDialogIsVisible,
        savingDialogProgress = cardState.savingDialogProgress,
        errorSavingCard = cardState.errorSavingCard,
        cardImage = uiState.cardImage,
        clearError = {viewModel.clearError()})
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun AddCardScreen(
    isCompact: Boolean,
    firstNameValue: String,
    onFirstNameValueChange: (String) -> Unit,
    lastNameValue: String,
    onLastNameValueChange: (String) -> Unit,
    emailAddressValue: String,
    onEmailAddressValueChange: (String) -> Unit,
    companyNameValue: String,
    onCompanyNameValueChange: (String) -> Unit,
    jobTitleValue: String,
    onJobTitleValueChange: (String) -> Unit,
    websiteValue: String,
    onWebsiteValueChange: (String) -> Unit,
    isDialogVisible: Boolean,
    onDialogVisibilityChange: (Boolean) -> Unit,
    images: List<Int>,
    onImageSelected: (Int) -> Unit,
    phoneNumberValue: String,
    onPhoneNumberValueChange:(String) -> Unit,
    locationValue: String,
    onLocationValueChange:(String) -> Unit,
    onSaveDetails:() -> Unit,
    savingDialogIsVisible: Boolean,
    savingDialogProgress: Float,
    errorSavingCard: String,
    cardImage: Int,
    clearError:() -> Unit
) {
    val layoutPadding = if (isCompact) 16.dp else 24.dp
    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.Black)
                .imePadding(), // Adds padding to prevent overlap
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f) // Allows scrolling when keyboard appears
                    .padding(layoutPadding),
                contentPadding = WindowInsets.ime.asPaddingValues() // Adds bottom padding for keyboard
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Add Card",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold
                        )

                        Icon(
                            painter = painterResource(R.drawable.circular_shaped_cancel_icon),
                            contentDescription = "Exit Add Card Screen",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }
                item { CardEditComponent(
                    onDialogVisibilityChange = onDialogVisibilityChange,
                    cardImage = cardImage
                ) }
                item { Spacer(modifier = Modifier.height(24.dp)) }

                item {
                    CustomTextField(
                        text = "First Name",
                        value = firstNameValue,
                        onValueChange = onFirstNameValueChange,
                        label = "First Name"
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item {
                    CustomTextField(
                        text = "Last Name",
                        value = lastNameValue,
                        onValueChange = onLastNameValueChange,
                        label = "Last Name"
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item {
                    CustomTextField(
                        text = "Email Address",
                        value = emailAddressValue,
                        onValueChange = onEmailAddressValueChange,
                        label = "Email"
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item {
                    CustomTextField(
                        text = "Phone Number",
                        value = phoneNumberValue,
                        onValueChange = onPhoneNumberValueChange,
                        label = "Phone Number"
                    )
                }

                item { Spacer(modifier = Modifier.height(56.dp)) }

                item {
                    CustomTextField(
                        text = "Company",
                        onValueChange = onCompanyNameValueChange,
                        value = companyNameValue,
                        label = "Company Name"
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item {
                    CustomTextField(
                        text = "Job Title",
                        onValueChange = onJobTitleValueChange,
                        value = jobTitleValue,
                        label = "Job Title"
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item {
                    CustomTextField(
                        text = "Location",
                        onValueChange = onLocationValueChange,
                        value = locationValue,
                        label = "e.g Nairobi, Kenya"
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }

                item {
                    CustomTextField(
                        text = "Website",
                        value = websiteValue,
                        onValueChange = onWebsiteValueChange,
                        label = "Website"
                    )
                }

                item { Spacer(modifier = Modifier.height(16.dp)) }
            }

            var buttonModifier by remember{ mutableStateOf(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = layoutPadding)
                    .padding(vertical = 8.dp)
            ) }


            if (WindowInsets.isImeVisible){
                buttonModifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = layoutPadding)
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = layoutPadding)
                    .padding(vertical = 8.dp)
            ) {
                Button(
                    onClick = {
                        onSaveDetails()
                    },
                    colors = ButtonDefaults.buttonColors(AppYellow),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Save", color = Color.Black, modifier = Modifier.padding(8.dp))
                }
            }
        }

        if (isDialogVisible){
            ImageSelectionDialog(onDismiss = { onDialogVisibilityChange(false) },
                images = images,
                onImageSelected = onImageSelected,
                currentImage = cardImage)
        }

        if (savingDialogIsVisible){
            SavingProgressDialog(savingDialogProgress)
        }

        if (errorSavingCard.isNotEmpty()){
            Toast.makeText(context, errorSavingCard, Toast.LENGTH_SHORT).show()
            clearError()
        }
    }
}



@Composable
fun CardEditComponent(
    onDialogVisibilityChange:(Boolean) -> Unit,
    cardImage: Int
){
    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(painter = painterResource(cardImage),
            contentDescription = "Red Card Background Image",
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .fillMaxWidth(.85f))

        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = {onDialogVisibilityChange(true)},
            content = {
                Row(modifier = Modifier
                    .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center)
                {
                    Icon(imageVector = Icons.Default.Edit,
                        contentDescription = "Change Card Background",
                        tint = Color.Black)

                    Text(text = "Change Image",
                        color = Color.Black)
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = AppYellow),
            modifier = Modifier
                .fillMaxWidth(.65f))
    }
}


@Preview
@Composable
fun PreviewAddCardCompactScreen(){
    var firstNameValue by remember { mutableStateOf("") }
    var lastNameValue by remember { mutableStateOf("") }
    var emailAddressValue by remember { mutableStateOf("") }
    var companyNameValue by remember { mutableStateOf("") }
    var jobTitleValue by remember { mutableStateOf("") }
    var websiteValue by remember { mutableStateOf("") }
    var isDialogVisible by remember{ mutableStateOf(false) }
    var image by remember{ mutableIntStateOf(com.victorkirui.resources.R.drawable.red_phoenix_card) }
    var phoneNumberValue by remember { mutableStateOf("") }
    var locationValue by remember { mutableStateOf("") }

    AddCardScreen(isCompact = true,
        firstNameValue = firstNameValue,
        onFirstNameValueChange = {firstNameValue = it},
        lastNameValue = lastNameValue,
        onLastNameValueChange = {lastNameValue = it},
        emailAddressValue = emailAddressValue,
        onEmailAddressValueChange = {emailAddressValue = it},
        companyNameValue = companyNameValue,
        onCompanyNameValueChange = {companyNameValue = it},
        jobTitleValue = jobTitleValue,
        onJobTitleValueChange = {jobTitleValue = it},
        websiteValue = websiteValue,
        onWebsiteValueChange = {websiteValue = it},
        isDialogVisible = isDialogVisible,
        onDialogVisibilityChange = {
            isDialogVisible = it
        },
        images = CardOptions.getAll(),
        onImageSelected = {image = it},
        phoneNumberValue = phoneNumberValue,
        onPhoneNumberValueChange = {phoneNumberValue = it},
        locationValue = locationValue,
        onLocationValueChange = {locationValue = it},
        onSaveDetails = {},
        errorSavingCard = "",
        savingDialogIsVisible = false,
        savingDialogProgress = 0f,
        cardImage = R.drawable.red_phoenix_card,
        clearError = {})
}


@Preview(device = "spec:width=673dp,height=841dp")
@Composable
fun PreviewAddCardMediumScreen(){
    var firstNameValue by remember { mutableStateOf("") }
    var lastNameValue by remember { mutableStateOf("") }
    var emailAddressValue by remember { mutableStateOf("") }
    var companyNameValue by remember { mutableStateOf("") }
    var jobTitleValue by remember { mutableStateOf("") }
    var websiteValue by remember { mutableStateOf("") }
    var isDialogVisible by remember{ mutableStateOf(false) }
    var image by remember{ mutableIntStateOf(com.victorkirui.resources.R.drawable.red_phoenix_card) }
    var phoneNumberValue by remember { mutableStateOf("") }
    var locationValue by remember { mutableStateOf("") }


    AddCardScreen(
        isCompact = false,
        firstNameValue = firstNameValue,
        onFirstNameValueChange = {firstNameValue = it},
        lastNameValue = lastNameValue,
        onLastNameValueChange = {lastNameValue = it},
        emailAddressValue = emailAddressValue,
        onEmailAddressValueChange = {emailAddressValue = it},
        companyNameValue = companyNameValue,
        onCompanyNameValueChange = {companyNameValue = it},
        jobTitleValue = jobTitleValue,
        onJobTitleValueChange = {jobTitleValue = it},
        websiteValue = websiteValue,
        onWebsiteValueChange = {websiteValue = it},
        isDialogVisible = isDialogVisible,
        onDialogVisibilityChange = {
            isDialogVisible = it
        },
        images = CardOptions.getAll(),
        onImageSelected = {image = it},
        phoneNumberValue = phoneNumberValue,
        onPhoneNumberValueChange = {phoneNumberValue = it},
        locationValue = locationValue,
        onLocationValueChange = {locationValue = it},
        onSaveDetails = {},
        errorSavingCard = "",
        savingDialogIsVisible = false,
        savingDialogProgress = 0f,
        cardImage = R.drawable.red_phoenix_card,
        clearError = {})
}