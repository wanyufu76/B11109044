package com.example.b11109044

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import kotlin.math.absoluteValue


@Composable
fun SpotDetails(
    spot: Spot,
    onBackClicked: () -> Unit,
    onMapClicked: () -> Unit
) {
    val context = LocalContext.current
    var dragOffset by remember { mutableStateOf(0f) }
    val dex = Color(0xFF004D8D)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectDragGestures { _, dragAmount ->
                    dragOffset += dragAmount.x
                    if (dragOffset.absoluteValue > 100) {
                        onBackClicked()
                    }
                }
            }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.Start)
                .clickable { onBackClicked() }
        ) {
            Image(
                painter = painterResource(id = R.drawable.baseline_arrow_back_24),
                contentDescription = "Back",
                modifier = Modifier.size(24.dp),
                alignment = Alignment.CenterStart
            )
        }
        Box(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(id = spot.photoResId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(16f / 9f)
                        .clip(RoundedCornerShape(16.dp))
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    Text(
                        text = spot.name,
                        style = MaterialTheme.typography.h4.copy(color = Color.Black),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = spot.description,
                        style = MaterialTheme.typography.body1.copy(color = Color.Black),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = "地址：${spot.location}",

                    )
                    Text(
                        text = "透過Google Map檢視位置",
                        style = MaterialTheme.typography.body1.copy(color = Color.Black),
                        modifier = Modifier
                            .clickable {
                                onMapClicked()
                                val mapUrl = "https://www.google.com/maps/search/?api=1&query=${spot.location}"
                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
                                if (intent.resolveActivity(context.packageManager) != null) {
                                    context.startActivity(intent)
                                } else {
                                    val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(mapUrl))
                                    context.startActivity(webIntent)
                                }
                            }
                                .padding(vertical = 16.dp)
                                .fillMaxWidth()
                                .background(color = dex),
                                color = Color.White,
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center,

                    )
                }
            }
        }
    }
}