package com.example.roomie.data.models

import androidx.compose.ui.graphics.Color

data class Person(
    val id: Int = 0,
    val name: String = "",
    val color: Color = Color.White,
    val paidRent: Boolean = false,
)
