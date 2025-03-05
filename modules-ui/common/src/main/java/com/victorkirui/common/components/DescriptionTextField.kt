package com.victorkirui.common.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.design.theme.CustomTextFieldColors
import kotlinx.coroutines.launch



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DescriptionTextField(
    textFieldValue: String,
    onTextFieldValueChange: (String) -> Unit,
    onComponentHidden: () -> Unit // Callback when the component is hidden, keyboard is visible, and it's focused.
    ,
    onComponentVisible:() -> Unit
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val coroutineScope = rememberCoroutineScope()

    var isComponentBlocked by remember { mutableStateOf(false) }
    var previousHidden by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }  // track the focus state

    // Get the keyboard height as a Dp value.
    val imeHeight = WindowInsets.ime.asPaddingValues().calculateBottomPadding()

    // Capture density and screen configuration to work with pixels.
    val density = LocalDensity.current
    val configuration = LocalConfiguration.current
    val screenHeightPx = with(density) { configuration.screenHeightDp.dp.toPx() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            // Update focus state and scroll into view if focused.
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
                if (isFocused) {
                    coroutineScope.launch {
                        bringIntoViewRequester.bringIntoView()
                    }
                }

                if (!isFocused){
                    onComponentVisible()
                }
            }
            // Measure the component's position and height.
            .onGloballyPositioned { coordinates ->
                val componentHeightPx = coordinates.size.height.toFloat()
                val componentYPositionPx = coordinates.positionInRoot().y
                val imeHeightPx = with(density) { imeHeight.toPx() }

                // Check if the component is blocked by the keyboard.
                val blocked = isComponentBlockedByKeyboard(
                    componentHeightPx,
                    componentYPositionPx,
                    screenHeightPx,
                    imeHeightPx
                )
                // Define "hidden" only when blocked and the keyboard is visible.
                val hidden = blocked && (imeHeight > 0.dp)
                // Fire the callback only if the component transitions to hidden and is focused.
                if (!previousHidden && hidden && isFocused) {
                    onComponentHidden()
                }
                previousHidden = hidden
                isComponentBlocked = blocked
            }
    ) {
        Text(
            text = "Description",
            color = Color.White,
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = onTextFieldValueChange,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .bringIntoViewRequester(bringIntoViewRequester),
            label = { Text("Description") },
            colors = CustomTextFieldColors,
            singleLine = false,
        )
    }

    // Optionally, add padding at the bottom if the keyboard is visible.
    if (imeHeight > 0.dp) {
        Spacer(modifier = Modifier.height(imeHeight))
    }
}

fun isComponentBlockedByKeyboard(
    componentHeightPx: Float,
    componentYPositionPx: Float,
    screenHeightPx: Float,
    imeHeightPx: Float
): Boolean {
    // The component is blocked if its bottom edge is lower than the top edge of the keyboard.
    return (componentYPositionPx + componentHeightPx) > (screenHeightPx - imeHeightPx)
}





@Preview
@Composable
fun DescriptionTextFieldPreview(){
    Column(modifier = Modifier.fillMaxSize()
        .background(color = Color.Black)
        .padding(16.dp)){

        DescriptionTextField(textFieldValue = "", onTextFieldValueChange = {}, onComponentHidden = {}, onComponentVisible = {})
    }
}