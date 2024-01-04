package com.example.roomie.features.monthlySummary

import com.example.roomie.data.models.Payment
import com.example.roomie.data.models.Person

class MonthlySummaryState (
    val isLoading: Boolean = false,
    val error: String? = null,
    val people: List<Person> = emptyList(),
    val payments: List<Payment> = emptyList(),
)