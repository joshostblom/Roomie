package com.example.roomie.features.amountOwed

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.example.roomie.domain.calculations.CalculationsHelper
import com.example.roomie.domain.payments.Payment
import com.example.roomie.domain.people.Person

@Composable
fun AmountOwedList (
    people: List<Person>,
    payments: List<Payment>,
) {
    val calculationsHelper = CalculationsHelper(
        people = people,
        payments = payments,
    )

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopCenter),
            text = "Summary",
            fontWeight = FontWeight.Bold,
        )

        LazyColumn {
            items(people.size) {
                AmountOwed(
                    person = people[it],
                    amount = calculationsHelper.getAmountOwed(people[it])
                )
            }
        }
    }
}

@Composable
fun AmountOwed (
    person: Person,
    amount: Double,
) {
    Row {

    }
}