package com.example.roomie.domain.people

import androidx.compose.ui.graphics.Color

data class Person(
    val id: Int = 0,
    val name: String = "",
    val color: Color = Color.White,
)
