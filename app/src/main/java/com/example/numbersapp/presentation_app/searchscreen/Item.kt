package com.example.numbersapp.presentation_app.searchscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.numbersapp.domain.model.NumberInfo

@Composable
fun Item(
   // modifier: Modifier = Modifier,
    numberInfo: NumberInfo
) {
    Row(
        modifier= Modifier.height(60.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .widthIn(min = 70.dp)
                .fillMaxHeight()
                .drawBehind { drawRect(color = Color.LightGray) }
        ) {
            Text(
                text = numberInfo.number,
                style = MaterialTheme.typography.h6

            )
        }
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = numberInfo.mean,
              //  maxLines = 1
            )
        }
    }
}