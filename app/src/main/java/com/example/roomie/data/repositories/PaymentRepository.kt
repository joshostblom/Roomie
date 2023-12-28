package com.example.roomie.data.repositories

import com.example.roomie.data.models.Payment

interface PaymentRepository {
    suspend fun getPayments() : List<Payment>
    suspend fun setPayments(payments: List<Payment>) : List<Payment>
}