package com.example.b11109044

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Front(spots: List<Spot>, onSpotSelected: (Spot) -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(5.dp)
    ) {
        Text(
            text = "韓國熱門旅遊景點",
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(),
            fontSize = 44.sp,
            textAlign = TextAlign.Center
        )
        Column(modifier = Modifier.weight(1f)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(spots) { spot ->
                    SpotItem(spot = spot) {
                        onSpotSelected(spot)
                    }
                }
            }
        }
    }
}

@Composable
fun SpotItem(spot: Spot, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        val borderWidth = 4.dp
        Image(
            painter = painterResource(id = spot.photoResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .padding(start = 3.dp)
                .padding(borderWidth)
                .clip(RoundedCornerShape(16.dp))
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Box(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = spot.name,
                    style = MaterialTheme.typography.h6.copy(fontSize = 20.sp),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Start,

                )
            }

        }
    }
}
