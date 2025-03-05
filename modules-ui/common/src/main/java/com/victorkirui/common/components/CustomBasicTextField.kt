package com.victorkirui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.victorkirui.design.theme.AppYellow
import com.victorkirui.design.theme.CustomTextFieldColors

@Composable
fun CustomBasicTextField(text: String,
                         textFieldValue: String,
                         onTextFieldValueChange: (String) -> Unit,
                         textLabel: String){
    Column {
        Text(text = text, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { onTextFieldValueChange(it)},
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(textLabel)
            },
            maxLines = 1,
            colors = CustomTextFieldColors
        )
    }
}



@Composable
fun CustomTextField(
    text: String,
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Enter text"
) {
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    Column {
        Text(text = text, color = Color.White, fontSize = 14.sp, fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(
                    width = 1.dp,
                    color = if (isFocused) AppYellow else Color.Gray, // Change color on focus
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 12.dp)
                .focusRequester(focusRequester)
                .onFocusChanged { isFocused = it.isFocused } // Track focus state
                .clickable { focusRequester.requestFocus() } // Allows clicking the whole box to focus
        ) {
            if (value.isEmpty() && !isFocused) {
                Text(
                    text = label,
                    color = Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
            }

            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                textStyle = TextStyle(fontSize = 14.sp, color = Color.White),
                cursorBrush = SolidColor(AppYellow),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
                    .onFocusChanged { isFocused = it.isFocused }, // Update focus state
                decorationBox = { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        innerTextField()
                    }
                }
            )
        }
    }
}



@Preview
@Composable
fun CustomTextFieldPreview() {
    var text by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().background(color = Color.Black)){
        CustomTextField(
            value = text,
            onValueChange = { text = it },
            label = "Name",
            text = "Event Name"
        )
    }
}



//@Preview
//@Composable
//fun CustomBasicTextFieldPreview(){
//    Column(modifier = Modifier.fillMaxSize()
//        .background(color = Color.Black)
//        .padding(16.dp)){
//
//        Try()
//
////        CustomBasicTextField(text = "Event Name", textFieldValue = "", onTextFieldValueChange = {}, textLabel = "Name")
//    }
//}