package com.example.roomie.features.amountOwed

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = "Summary",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
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
    Row (
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        Text(
            text = person.name,
            fontWeight = FontWeight.Bold,
        )

        Text(
            text = String.format("$%.2f", amount),
            color = if (amount < 0) Color.Red else Color.Green,
        )
    }
}