package com.example.roomie.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.roomie.data.models.HomeConfiguration
import com.example.roomie.domain.homeConfiguration.HomeConfigurationDatabase
import com.example.roomie.domain.payments.PaymentDatabase
import com.example.roomie.domain.people.PeopleDatabase
import com.example.roomie.features.home.HomeScreen
import com.example.roomie.features.payRent.PayRentScreen
import com.example.roomie.features.payments.PaymentsScreen
import com.example.roomie.features.settings.Settings
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
    val context = LocalContext.current
    val peopleDatabase by remember { mutableStateOf(PeopleDatabase(context)) }
    val paymentDatabase by remember { mutableStateOf(PaymentDatabase(context)) }
    val homeConfigDatabase by remember { mutableStateOf(HomeConfigurationDatabase(context)) }

    PayRentScreen(
        payments = paymentDatabase.getPayments(),
        people = peopleDatabase.getPeople(),
        rentCost = homeConfigDatabase.getConfiguration().rentCost,
    )
}

@RootNavGraph
@Destination
@Composable
fun PaymentsDestination() {

    val context = LocalContext.current
    val paymentDatabase by remember { mutableStateOf(PaymentDatabase(context)) }
    var payments by remember { mutableStateOf(paymentDatabase.getPayments()) }

    PaymentsScreen(
        items = payments,
        setItems = {
            paymentDatabase.setPayments(it)
            payments = it
        },
    )
}

@RootNavGraph
@Destination
@Composable
fun MenuDestination() {

    val context = LocalContext.current
    val peopleDatabase by remember { mutableStateOf(PeopleDatabase(context)) }
    var people by remember { mutableStateOf(peopleDatabase.getPeople()) }
    val homeConfigDatabase by remember { mutableStateOf(HomeConfigurationDatabase(context)) }
    var rentCost = homeConfigDatabase.getConfiguration().rentCost

    Settings(
        people = people,
        setPeople = {
            peopleDatabase.setPeople(it)
            people = it
        },
        rentCost = rentCost,
        setRentCost = {
            homeConfigDatabase.setConfiguration(
                HomeConfiguration(
                rentCost = it,
            )
            )
            rentCost = it
        }
    )
}