package com.victorkirui.networq

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.victorkirui.cards.ui.AddCardRoute
import com.victorkirui.display_cards.ui.MyCardsRoute
import com.victorkirui.networq.ui.theme.NetworQTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val windowSizeClass = calculateWindowSizeClass(this)

            NetworQTheme(
                darkTheme = true,
                dynamicColor = true
            ){
                Scaffold(modifier = Modifier.background(color = Color.Black)){
                    Column(modifier = Modifier.padding(it)){
                        MyCardsRoute(windowSize = windowSizeClass, context = this@MainActivity)

//                        AddCardRoute(windowSizeClass = windowSizeClass, activity = this@MainActivity)
                    }
                }
            }

        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NetworQTheme {
        Greeting("Android")
    }
}