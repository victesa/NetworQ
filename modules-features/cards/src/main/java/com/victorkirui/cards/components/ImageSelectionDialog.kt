package com.victorkirui.cards.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSelectionDialog(onDismiss: () -> Unit,
                         images: List<Int>,
                         onImageSelected:(Int) -> Unit,
                         currentImage: Int) {
    val pagerState = rememberPagerState { images.size }
    val hapticFeedback = LocalHapticFeedback.current

    LaunchedEffect(pagerState.settledPage) {
        hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
    }

    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(color = Color(0xFF131315), shape = RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Select an Image",
                    style = MaterialTheme.typography.titleMedium,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(24.dp))

                val imageWidth = 180.dp // Adjusted width
                val imageHeight = imageWidth * (590f / 1004f) // Maintain aspect ratio

                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                    contentPadding = PaddingValues(horizontal = 24.dp), // Ensures 1.5 images are visible
                    pageSize = PageSize.Fixed(imageWidth),
                    pageSpacing = 8.dp
                ) { page ->
                    Box(
                        modifier = Modifier
                            .width(imageWidth)
                            .height(imageHeight)
                            .clip(RoundedCornerShape(16.dp))
                            .border(
                                width = if (currentImage == images[page]) 3.dp else 0.dp, // Add border if selected
                                color = if (currentImage == images[page]) Color.Yellow else Color.Transparent,
                                shape = RoundedCornerShape(16.dp)
                            )
                            .clickable {
                                onImageSelected(images[page])
                            }
                    ) {
                        Image(
                            painter = painterResource(id = images[page]),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(RoundedCornerShape(16.dp)), // Ensures rounded corners
                            contentScale = ContentScale.Fit
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}









@Preview
@Composable
fun PreviewImageSelectionDialog(){
    Column(modifier = Modifier.fillMaxSize().background(color = Color.Black),
        verticalArrangement = Arrangement.Center){
        ImageSelectionDialog({}, listOf(), {}, com.victorkirui.resources.R.drawable.red_phoenix_card)
    }
}