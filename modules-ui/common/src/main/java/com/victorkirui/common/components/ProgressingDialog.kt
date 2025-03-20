package com.victorkirui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressingDialog(text: String){
    BasicAlertDialog(onDismissRequest = {},
        content = {
            Column(verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(16.dp)
                    .padding(vertical = 10.dp)){
                CircularProgressIndicator(color = Color.Green,
                    modifier = Modifier.size(60.dp),
                    strokeWidth = 5.dp)

                Spacer(modifier = Modifier.height(24.dp))

                Text(text = text,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black, fontSize = 16.sp)
                }
            },
        modifier = Modifier
            .background(color = Color.White, shape = RoundedCornerShape(20.dp))
    )
}

@Preview
@Composable
fun PreviewProgressingDialog(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.Black),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        ProgressingDialog("Deleting")
    }
}