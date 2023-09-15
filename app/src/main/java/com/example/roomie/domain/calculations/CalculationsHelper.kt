package com.example.roomie.domain.calculations

import com.example.roomie.domain.payments.Payment
import com.example.roomie.domain.people.Person

class CalculationsHelper(
    private val payments: List<Payment>,
    private val people: List<Person>,
) {
    fun getAmountOwed(
        person: Person,
    ): Double {
        return getTotalSpent() / people.count() - getTotalSpent(person)
    }

    fun getTotalSpent(
        person: Person? = null,
        people: List<Person>? = null,
    ): Double {
        var total = 0.0
        var thesePayments = payments
        if (person != null) {
            thesePayments = payments.filter { it.whoPaid == person }
        } else if (people != null) {
            thesePayments = payments.filter { people.contains(it.whoPaid) }
        }
        thesePayments.forEach {
            total += it.payment
        }
        return total
    }
}