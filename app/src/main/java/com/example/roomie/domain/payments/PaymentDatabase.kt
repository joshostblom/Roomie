package com.example.roomie.domain.payments

import android.content.Context
import com.example.roomie.data.models.Person
import com.example.roomie.domain.typeAdapters.LocalDateTypeAdapter
import com.example.roomie.data.models.Payment
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.time.LocalDate

class PaymentDatabase(
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

    fun verifyPayment(
        payment: Payment,
    ) : List<String> {
        val result = mutableListOf<String>()

        if(payment.whoPaid == Person()) {
            result.add("Who's paying must be specified")
        }
        if(payment.title.isBlank()) {
            result.add("Where it was paid must be specified")
        }
        if(payment.payment == 0.0) {
            result.add("Payment must be greater than 0")
        }

        return result
    }
}