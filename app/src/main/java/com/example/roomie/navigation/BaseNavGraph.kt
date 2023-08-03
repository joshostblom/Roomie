package com.example.roomie.navigation

import androidx.compose.runtime.Composable
import com.example.roomie.features.home.HomeScreen
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(true)
@Destination
@Composable
fun Home() {
    HomeScreen()
}