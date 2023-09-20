package com.example.roomie.features.shared

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(
    title: String,
) {
    Text(
        modifier = Modifier
            .padding(bottom = 5.dp),
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
    )
}