package com.example.roomie.features.payRent

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.roomie.domain.calculations.CalculationsHelper
import com.example.roomie.domain.payments.Payment
import com.example.roomie.domain.people.Person
import com.example.roomie.features.shared.Header

@Composable
fun PayRentScreen(
    payments: List<Payment>,
    people: List<Person>,
    rentCost: Double,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Header("Pay Rent")

        val calcHelper = CalculationsHelper(
            payments = payments,
            people = people,
        )
        val amountOwed = calcHelper.getRentAmountOwed(
            person = people.first(),
            rentCost = rentCost,
        )

        Row() {
            Text(
                text = "Rent due: "
            )

            Text(
                text = rentCost.toString()
            )
        }

        Row() {
            Text(
                text = "Amount owed: "
            )

            Text(
                text = amountOwed.toString()
            )
        }

        Button(
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Pay Rent"
            )
        }
    }
}