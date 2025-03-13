package com.victorkirui.cards.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun SavingProgressDialog(progress: Float){
    Dialog(
        onDismissRequest = {},
        properties = DialogProperties(dismissOnClickOutside = false)){
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = Color.White)
                .clip(RoundedCornerShape(10.dp))
                .padding(24.dp)){
            CircularProgressIndicator(
                progress = { progress },
                color = Color.Green
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = "Saving Details", color = Color.Black)
        }
    }
}


@Preview
@Composable
fun PreviewSavingProgressDialog(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black)
        .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        SavingProgressDialog(progress = .3f)
    }
}