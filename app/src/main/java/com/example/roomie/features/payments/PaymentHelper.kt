package com.example.roomie.features.payments

import com.example.roomie.data.models.Payment
import com.example.roomie.data.models.Person

class PaymentHelper {
    companion object {
        fun verifyPayment(
            payment: Payment,
        ): List<String> {
            val result = mutableListOf<String>()

            if (payment.whoPaid == Person()) {
                result.add("Who's paying must be specified")
            }
            if (payment.title.isBlank()) {
                result.add("Where it was paid must be specified")
            }
            if (payment.payment == 0.0) {
                result.add("Payment must be greater than 0")
            }

            return result
        }
    }
}