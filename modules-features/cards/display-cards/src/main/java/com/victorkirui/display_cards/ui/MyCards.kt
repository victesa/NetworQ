package com.victorkirui.display_cards.ui

import android.content.Context
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.victorkirui.common.components.ProgressingDialog
import com.victorkirui.domain.models.PersonalCards
import org.koin.androidx.compose.koinViewModel
import java.io.File

@Composable
fun MyCardsRoute(windowSize: WindowSizeClass,
                 myCardsViewModel: MyCardsViewModel = koinViewModel(),
                 context: Context
){

    val cards by myCardsViewModel.myCards.collectAsState()
    val uiState by myCardsViewModel.state.collectAsState()

    if (uiState.progressDialogVisible){
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center){
            MyCardsScreen(
                widthSizeClass = windowSize.widthSizeClass,
                selectionMode = uiState.selectionMode,
                onSendCards = {
                    myCardsViewModel.onSendCards(context)
                },
                onDeleteCards = {
                    myCardsViewModel.onDeleteCards()
                },
                onCancel = {myCardsViewModel.onSelectionModeChanged(false)},
                navigateToAddCardScreen = {
                    TODO()
                },
                cards = cards,
                selectedList = uiState.selectedCards,
                onSelectionMode = {myCardsViewModel.onSelectionModeChanged(true)},
                navigateToCardDetails = {
                    TODO()
                },
                addCardToSelection = {
                    myCardsViewModel.addCardToSelection(it)
                }
            )

            ProgressingDialog(text = uiState.progressDialogText)
        }
    }else{
        MyCardsScreen(
            widthSizeClass = windowSize.widthSizeClass,
            selectionMode = uiState.selectionMode,
            onSendCards = {
                myCardsViewModel.onSendCards(context)
            },
            onDeleteCards = {
                myCardsViewModel.onDeleteCards()
            },
            onCancel = {myCardsViewModel.onSelectionModeChanged(false)},
            navigateToAddCardScreen = {},
            cards = cards,
            selectedList = uiState.selectedCards,
            onSelectionMode = {myCardsViewModel.onSelectionModeChanged(true)},
            navigateToCardDetails = {},
            addCardToSelection = {
                myCardsViewModel.addCardToSelection(it)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCardsScreen(widthSizeClass: WindowWidthSizeClass,
                  selectionMode: Boolean,
                  onSendCards:() -> Unit,
                  onDeleteCards:() -> Unit,
                  onCancel:() -> Unit,
                  navigateToAddCardScreen:() -> Unit,
                  cards: List<PersonalCards>,
                  selectedList: List<PersonalCards>,
                  onSelectionMode:() -> Unit,
                  navigateToCardDetails:() -> Unit,
                  addCardToSelection:(PersonalCards) -> Unit){
    val layoutPadding = if (widthSizeClass == WindowWidthSizeClass.Compact) 16.dp else 24.dp

    Scaffold(modifier = Modifier
        .fillMaxSize(),
        containerColor = Color.Black,
        topBar = {
            if(selectionMode){
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically){
                            Icon(imageVector = Icons.Default.Close,
                                contentDescription = "Close Selection Mode",
                                tint = Color.White,
                                modifier = Modifier.clickable {
                                    onCancel()
                                })
                            Spacer(modifier = Modifier.width(8.dp))

                            Text("Selected Items", color = Color.White, fontWeight = FontWeight.SemiBold)
                        }
                            },
                    actions = {
                        Icon(imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Cards",
                            tint = Color.Red,
                            modifier = Modifier
                                .padding(end = 8.dp)
                                .clickable {
                                    onDeleteCards()
                                }
                        )


                        Icon(imageVector = Icons.AutoMirrored.Default.Send,
                            contentDescription = "Send Cards",
                            tint = Color.White,
                            modifier = Modifier
                                .padding(end = 10.dp)
                                .clickable {
                                    onSendCards()

                                }
                        )

                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
                )
            }else{
                TopAppBar(
                    title = { Text("My Cards", color = Color.White, fontWeight = FontWeight.SemiBold) },
                    colors = TopAppBarDefaults
                        .topAppBarColors(
                        containerColor = Color.Black
                    )
                )
            }
        },
        floatingActionButton = {
           AnimatedVisibility(
               visible = !selectionMode,
               enter = slideInVertically(initialOffsetY = {it}),
               exit = slideOutVertically(targetOffsetY = {it})
           ){
               FloatingActionButton(
                   onClick = {
                       navigateToAddCardScreen()
                   },
                   containerColor = Color(0xFFD9F502),
                   content = {
                       Icon(imageVector = Icons.Default.Add, contentDescription = "Add Card")
                   }
               )
           }
        }){paddingValues ->

        LazyColumn(contentPadding = paddingValues,
            modifier = Modifier
                .padding(horizontal = layoutPadding)
                .fillMaxSize()){

            items(cards.size){
                ImageItem(
                    file = cards[it].cardImage!!,
                    selected = selectedList.contains(cards[it]),
                    showSelection = selectionMode,
                    onShowSelectionMode = {
                        onSelectionMode()
                    },
                    navigateToDetailsScreen = {
                        navigateToCardDetails()
                    },
                    cardSelected = {
                        addCardToSelection(cards[it])
                    }
                )

                Spacer(modifier = Modifier.height(10.dp))
            }

        }

    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageItem(
    file: File,
    selected: Boolean,
    showSelection: Boolean,
    onShowSelectionMode: () -> Unit,
    cardSelected: () -> Unit,
    navigateToDetailsScreen:() -> Unit
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .combinedClickable(onClick = if (showSelection) cardSelected else navigateToDetailsScreen, onLongClick = onShowSelectionMode),
        verticalAlignment = Alignment.CenterVertically
    ){

        AnimatedVisibility(visible = showSelection){
            RadioButton(selected = selected, onClick = cardSelected)
        }

        val cardModifier = if(!selected) {
            Modifier
                .aspectRatio(1.75f)
                .combinedClickable(
                    onClick = if (showSelection) cardSelected else navigateToDetailsScreen,
                    onLongClick = onShowSelectionMode
                )
        }else{
            Modifier
                .aspectRatio(1.75f)
                .combinedClickable(
                    onClick = if (showSelection) cardSelected else navigateToDetailsScreen,
                    onLongClick = onShowSelectionMode
                )
                .border(width = 2.dp, color = Color.Yellow, shape = RoundedCornerShape(10.dp))
        }

        Card(){
            Image(painter = rememberAsyncImagePainter(file),
                contentDescription = null,
                modifier = cardModifier
            )
        }


    }
}

@Preview
@Composable
fun PreviewMyCardsCompactScreen(){

    MyCardsScreen(
        widthSizeClass = WindowWidthSizeClass.Compact,
        selectionMode = true,
        cards = emptyList(),
        selectedList = emptyList(),
        onDeleteCards = {},
        onSendCards = {},
        onCancel = {},
        navigateToCardDetails = {},
        navigateToAddCardScreen = {},
        onSelectionMode = {},
        addCardToSelection = {}
    )

}

@Preview(device = "spec:width=673dp,height=841dp")
@Composable
fun PreviewMyCardsMediumScreen(){

    MyCardsScreen(
        widthSizeClass = WindowWidthSizeClass.Medium,
        selectionMode = true,
        cards = emptyList(),
        selectedList = emptyList(),
        onDeleteCards = {},
        onSendCards = {},
        onCancel = {},
        navigateToCardDetails = {},
        navigateToAddCardScreen = {},
        onSelectionMode = {},
        addCardToSelection = {}
        )

}