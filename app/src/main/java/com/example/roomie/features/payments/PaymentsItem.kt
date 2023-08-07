package com.example.roomie.features.payments

import java.util.Date

data class PaymentsItem (
    val title: String = "",
    val payment: Double = 0.0,
    val date: Date = Date(),
    val onEdit: () -> Unit = {},
    val onDelete: () -> Unit = {},
)