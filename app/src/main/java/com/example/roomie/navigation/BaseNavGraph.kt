package com.example.roomie.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.example.roomie.domain.payments.PaymentsHelper
import com.example.roomie.features.home.HomeScreen
import com.example.roomie.features.payments.PaymentsScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

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

    val paymentsHelper = PaymentsHelper(LocalContext.current)
    var items by remember { mutableStateOf(paymentsHelper.getPayments()) }

    PaymentsScreen(
        items = items,
        setItems = {
            paymentsHelper.setPayments(it)
            items = it
        },
    )
}