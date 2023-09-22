package com.example.roomie.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.roomie.domain.payments.PaymentDatabase
import com.example.roomie.domain.people.PeopleDatabase
import com.example.roomie.features.home.HomeScreen
import com.example.roomie.features.payRent.PayRentScreen
import com.example.roomie.features.payments.PaymentsScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(true)
@Destination
@Composable
fun HomeDestination() {

    val context = LocalContext.current
    val peopleDatabase by remember { mutableStateOf(PeopleDatabase(context)) }
    val paymentDatabase by remember { mutableStateOf(PaymentDatabase(context)) }

    HomeScreen(
        people = peopleDatabase.getPeople(),
        payments = paymentDatabase.getPayments(),
    )
}

@RootNavGraph
@Destination
@Composable
fun PayRentDestination() {
    PayRentScreen()
}

@RootNavGraph
@Destination
@Composable
fun PaymentsDestination() {

    val context = LocalContext.current
    val paymentDatabase by remember { mutableStateOf (PaymentDatabase(context) )}
    var items by remember { mutableStateOf(paymentDatabase.getPayments()) }

    PaymentsScreen(
        items = items,
        setItems = {
            paymentDatabase.setPayments(it)
            items = it
        },
    )
}