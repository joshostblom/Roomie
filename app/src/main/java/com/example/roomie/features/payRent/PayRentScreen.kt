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
import com.example.roomie.data.models.Payment
import com.example.roomie.data.models.Person
import com.example.roomie.features.shared.Header

@Composable
fun PayRentScreen(
    payments: List<Payment>,
    people: List<Person>,
//    payRent: () -> Unit,
    rentCost: Double,
) {
    val currentPerson = people.first()
    val calcHelper = CalculationsHelper(
        payments = payments,
        people = people,
    )
    val rentOwed = calcHelper.getRentAmountOwed(
        person = currentPerson,
        rentCost = rentCost,
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Header("Pay Rent")

        Row() {
            Text("Rent due: ")
            Text(rentCost.toString())
        }

        Row() {
            Text("Amount owed: ")
            Text(rentOwed.toString())
        }

        Button(
            onClick = {} //payRent
        ) {
            Text("Pay Rent")
        }
    }
}