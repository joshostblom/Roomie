package com.example.roomie.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.roomie.domain.payments.PaymentsHelper
import com.example.roomie.domain.people.PeopleHelper
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
    val peopleHelper by remember { mutableStateOf(PeopleHelper(context)) }
    val paymentsHelper by remember { mutableStateOf(PaymentsHelper(context)) }

    HomeScreen(
        people = peopleHelper.getPeople(),
        payments = paymentsHelper.getPayments(),
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
    val paymentsHelper by remember { mutableStateOf (PaymentsHelper(context) )}
    var items by remember { mutableStateOf(paymentsHelper.getPayments()) }

    PaymentsScreen(
        items = items,
        setItems = {
            paymentsHelper.setPayments(it)
            items = it
        },
    )
}