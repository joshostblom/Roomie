package com.example.roomie.domain.payments

import android.content.Context
import com.example.roomie.domain.people.Person
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PaymentsHelper(
    private val context: Context,
) {
    fun getPayments(): List<PaymentsItem> {
        val sharedPref = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        val paymentsType = object : TypeToken<List<PaymentsItem>>() {}.type
        return Gson().fromJson(sharedPref.getString("payments", ""), paymentsType) ?: emptyList()
    }

    fun setPayments(
        payments: List<PaymentsItem>,
    ) {
        val sharedPref = context.getSharedPreferences("data", Context.MODE_PRIVATE)
        sharedPref.edit().putString("payments", Gson().toJson(payments)).apply()
    }
}