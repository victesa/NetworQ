package com.victorkirui.cards.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.HorizontalDivider
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
fun BluePhoenixCard(
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
            painter = painterResource(com.victorkirui.resources.R.drawable.blue_phoenix_card),
            contentDescription = "Red Phoenix Card",
            modifier = Modifier
                .width(1000.dp) // Set width explicitly
                .aspectRatio(1.75f), // Height will be calculated automatically
            contentScale = ContentScale.Crop
        )


        Row(modifier = Modifier.matchParentSize()){
            Column(modifier = Modifier
                .fillMaxWidth(.5f)
                .fillMaxSize()
                .padding(40.dp),
                verticalArrangement = Arrangement.Center){
                Text(fullName, style = MaterialTheme.typography.titleMedium, fontSize = 48.sp)

                if (jobTitle.isNotEmpty()){
                    Text(jobTitle, color = Color.Gray, fontSize = 22.sp, fontWeight = FontWeight.SemiBold)
                }

                HorizontalDivider(color = Color(0xFF00a79d), thickness = 2.dp, modifier = Modifier.width(50.dp).padding(top = 10.dp))

                if (phoneNumber.isNotEmpty() || emailAddress.isNotEmpty()){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                        verticalAlignment = Alignment.CenterVertically){
                        Box(modifier = Modifier
                            .size(50.dp)
                            .background(color = Color(0xFF00a79d), shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ){
                            Icon(imageVector = Icons.Default.Phone,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(35.dp))

                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(modifier = Modifier){
                            if(phoneNumber.isNotEmpty()){
                                Text(text = phoneNumber, fontSize = 20.sp, color = Color.White)

                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            if (emailAddress.isNotEmpty()){
                                Text(text = emailAddress, fontSize = 20.sp, color = Color.White)
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                if (website.isNotEmpty()){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically){
                        Box(modifier = Modifier
                            .size(50.dp)
                            .background(color = Color(0xFF00a79d), shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ){
                            Icon(painter = painterResource(com.victorkirui.resources.R.drawable.internet),
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(35.dp))

                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = website, fontSize = 22.sp, color = Color.White)

                    }
                }


                Spacer(modifier = Modifier.height(8.dp))

                if (location.isNotEmpty()){
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically){
                        Box(modifier = Modifier
                            .size(50.dp)
                            .background(color = Color(0xFF00a79d), shape = CircleShape),
                            contentAlignment = Alignment.Center
                        ){
                            Icon(imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier
                                    .size(35.dp))

                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = location, fontSize = 22.sp, color = Color.White)

                    }
                }

            }

            Spacer(modifier = Modifier.fillMaxWidth(.3f))

            Box(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                contentAlignment = Alignment.Center){
                Text(text = companyName,
                    color = Color.Black,
                    fontSize = 38.sp,
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }


    }

}


@Preview(widthDp = 1000)
@Composable
fun PreviewPhoenixCard(){
    BluePhoenixCard(
        fullName = "John Doe",
        jobTitle = "Marketing Manager",
        phoneNumber = "+254707413953",
        location = "Nairobi, Kenya",
        website = "www.johndoe.com",
        companyName = "Amref International",
        emailAddress = "VictorKirui.dev@gmail.com"
    )
}