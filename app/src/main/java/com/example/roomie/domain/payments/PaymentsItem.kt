package com.example.roomie.domain.payments

import com.example.roomie.domain.people.Person
import java.time.LocalDate

data class PaymentsItem (
    val title: String = "",
    val whoPaid: Person = Person(),
    val payment: Double = 0.0,
    val date: LocalDate = LocalDate.now(),
)