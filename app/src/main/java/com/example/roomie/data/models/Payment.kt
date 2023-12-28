package com.example.roomie.data.models

import java.time.LocalDate

data class Payment (
    val title: String = "",
    val whoPaid: Person = Person(),
    val payment: Double = 0.0,
    val date: LocalDate = LocalDate.now(),
)