package com.victorkirui.cards.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PlainGreenCard(
    fullName: String,
    jobTitle: String,
    phoneNumber: String,
    location: String,
    website: String,
    companyName: String,
    emailAddress: String
){
    Box{
        Image(
            painter = painterResource(com.victorkirui.resources.R.drawable.plain_green_card),
            contentDescription = "Red Phoenix Card",
            modifier = Modifier
                .width(1000.dp) // Set width explicitly
                .aspectRatio(1.75f), // Height will be calculated automatically
            contentScale = ContentScale.Crop
        )

        Row(modifier = Modifier
            .matchParentSize(),
            verticalAlignment = Alignment.CenterVertically){
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(.45f)
                .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center){

                Text(fullName,
                    style = MaterialTheme.typography.titleMedium,
                    color = Color(0xFF90a955),
                    fontSize = 40.sp)

                if (jobTitle.isNotEmpty()){
                    Text(jobTitle,
                        color = Color.White,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center)
                }

                if(companyName.isNotEmpty()){
                    Box(modifier = Modifier
                        .fillMaxHeight(.7f)
                        .fillMaxWidth(.8f)
                        .padding(horizontal = 16.dp),
                        contentAlignment = Alignment.Center){
                        Text(text = companyName,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp)
                    }

                }

            }

            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.3f),
                verticalArrangement = Arrangement.SpaceBetween){

                if (phoneNumber.isNotEmpty() || emailAddress.isNotEmpty()){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                        verticalAlignment = Alignment.CenterVertically){
                        Icon(imageVector = Icons.Default.Phone,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(35.dp))

                        Spacer(modifier = Modifier.width(16.dp))

                       Column(modifier = Modifier.padding(horizontal = 32.dp)){
                           if(phoneNumber.isNotEmpty()) {
                               Text(
                                   text = phoneNumber, color =
                                   Color.Black, fontSize = 20.sp
                               )
                           }

                           if (emailAddress.isNotEmpty()){
                               Text(text = emailAddress, color =
                               Color.Black, fontSize = 20.sp)
                           }
                       }
                    }
                }

                if (website.isNotEmpty()){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                        verticalAlignment = Alignment.CenterVertically){
                        Spacer(modifier = Modifier.height(48.dp))

                        Icon(painter = painterResource(com.victorkirui.resources.R.drawable.internet),
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(35.dp))

                        Spacer(modifier = Modifier.width(48.dp))

                        Text(text = website, color = Color.Black, fontSize = 20.sp)
                    }
                }

                if (location.isNotEmpty()){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp),
                        verticalAlignment = Alignment.CenterVertically){
                        Spacer(modifier = Modifier.height(24.dp))
                        Icon(imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(35.dp))

                        Spacer(modifier = Modifier.width(48.dp))

                        Text(text = location, color = Color.Black, fontSize = 20.sp)
                    }
                }
            }
        }

    }
}

@Preview(widthDp = 1000)
@Composable
fun PreviewPlainGreenCard(){
    PlainGreenCard(
        fullName = "Mariana Anderson",
        jobTitle = "Marketing Manager",
        phoneNumber = "+254707413953",
        location = "Nairobi, Kenya",
        website = "www.amref.com",
        companyName = "Amref International",
        emailAddress = "VictorKirui.dev@gmail.com"
    )
}