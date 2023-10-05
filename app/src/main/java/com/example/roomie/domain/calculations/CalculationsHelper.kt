package com.example.roomie.domain.calculations

import com.example.roomie.domain.payments.Payment
import com.example.roomie.domain.people.Person
import java.time.LocalDate

class CalculationsHelper(
    private val payments: List<Payment>,
    private val people: List<Person>,
) {
    fun getAmountOwed(
        person: Person,
        month: LocalDate? = null,
        notMonth: LocalDate? = null,
    ): Double {
        return getTotalSpent(
            month = month,
            notMonth = notMonth,
        ) / people.count() - getTotalSpent(
            person = person,
            notMonth = notMonth,
        )
    }

    fun getTotalSpent(
        person: Person? = null,
        people: List<Person>? = null,
        month: LocalDate? = null,
        notMonth: LocalDate? = null,
    ): Double {
        var total = 0.0
        var thesePayments = payments
        if (person != null) {
            thesePayments = thesePayments.filter { it.whoPaid == person }
        } else if (people != null) {
            thesePayments = thesePayments.filter { people.contains(it.whoPaid) }
        }
        if (month != null) {
            thesePayments = thesePayments.filter { it.date.monthValue == month.monthValue && it.date.year == month.year}
        } else if (notMonth != null) {
            thesePayments = thesePayments.filterNot { it.date.monthValue == notMonth.monthValue && it.date.year == notMonth.year }
        }
        thesePayments.forEach {
            total += it.payment
        }
        return total
    }


}