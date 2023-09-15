package com.example.roomie.domain.payments

import android.content.Context
import com.example.roomie.domain.typeAdapters.LocalDateTypeAdapter
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

class PaymentsHelper(
    private val context: Context,
) {
    private val gson = GsonBuilder()
        .registerTypeAdapter(LocalDate::class.java, LocalDateTypeAdapter())
        .create()

    fun getPayments(): List<Payment> {
        val sharedPref = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        val paymentsType = object : TypeToken<List<Payment>>() {}.type
        return gson.fromJson(sharedPref.getString("payments", ""), paymentsType) ?: emptyList()
    }

    fun setPayments(
        payments: List<Payment>,
    ) {
        val sharedPref = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        sharedPref.edit().putString("payments", gson.toJson(payments)).apply()
    }
}