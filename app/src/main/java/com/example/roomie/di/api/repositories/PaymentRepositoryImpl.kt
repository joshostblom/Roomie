package com.example.roomie.di.api.repositories

import android.content.Context
import com.example.roomie.Factory
import com.example.roomie.data.repositories.PaymentRepository
import com.example.roomie.data.models.Payment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

class PaymentRepositoryImpl @Inject constructor(
    private val gson: Gson,
) : PaymentRepository {
    override suspend fun getPayments(): List<Payment> {
        val sharedPref = Factory.shared().getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE)
        val paymentsType = object : TypeToken<List<Payment>>() {}.type
        return gson.fromJson(sharedPref.getString("payments", ""), paymentsType) ?: emptyList()
    }

    override suspend fun setPayments(payments: List<Payment>): List<Payment> {
        val sharedPref = Factory.shared().getApplicationContext().getSharedPreferences("data", Context.MODE_PRIVATE)
        sharedPref.edit().putString("payments", gson.toJson(payments)).apply()
        return payments
    }
}