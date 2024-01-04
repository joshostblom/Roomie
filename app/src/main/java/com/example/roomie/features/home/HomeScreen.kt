package com.example.roomie.features.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.roomie.data.models.Payment
import com.example.roomie.data.models.Person
import com.example.roomie.features.monthlySummary.MonthlySummary

@Composable
fun HomeScreen() {

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        MonthlySummary()
    }
}
