package com.example.roomie.navigation

import androidx.compose.runtime.Composable
import com.example.roomie.features.home.HomeScreen
import com.example.roomie.domain.payments.PaymentsItem
import com.example.roomie.domain.people.Person
import com.example.roomie.features.payments.PaymentsScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import java.util.Date

@RootNavGraph(true)
@Destination
@Composable
fun Home() {
    HomeScreen()
}

@RootNavGraph
@Destination
@Composable
fun Payments() {
    PaymentsScreen(
       items = listOf(
           PaymentsItem(
               title = "Walmart",
               whoPaid = Person(name = "Josh"),
               payment = 1.22,
               date = Date(),
           ),
           PaymentsItem(
               title = "Allo",
               whoPaid = Person(name = "Kylee"),
               payment = 60.22,
               date = Date(),
           )
       )
    )
}