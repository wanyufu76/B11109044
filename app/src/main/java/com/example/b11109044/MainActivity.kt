package com.example.b11109044

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.*
import androidx.compose.runtime.remember
import com.example.b11109044.ui.theme.B11109044Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            B11109044Theme {
                MyApp()
            }
        }
    }
}


@Composable
fun MyApp() {
    MaterialTheme {
        val spotsView = remember { SpotList() }

        var selectedSpot by remember { mutableStateOf<Spot?>(null) }

        if (selectedSpot == null) {
            Front(spots = spotsView.spots) { spot ->
                selectedSpot = spot
            }
        } else {
            SpotDetails(
                spot = selectedSpot!!,
                onBackClicked = { selectedSpot = null },
                onMapClicked = {}
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    B11109044Theme {
        MyApp()
    }
}
