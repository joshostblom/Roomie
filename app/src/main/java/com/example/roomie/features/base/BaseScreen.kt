package com.example.roomie.features.base

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.roomie.features.navBar.HomeNavBarItem
import com.example.roomie.features.navBar.MenuNavBarItem
import com.example.roomie.features.navBar.PaymentsNavBarItem
import com.example.roomie.features.navBar.NavBar
import com.example.roomie.features.navBar.PayRentNavBarItem
import com.example.roomie.navigation.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.rememberNavHostEngine

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScreen() {

    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            NavBar(
                navController = navController,
                items = listOf(
                    HomeNavBarItem(navController),
                    PayRentNavBarItem(navController),
                    PaymentsNavBarItem(navController),
                    MenuNavBarItem(navController),
                )
            )
        }

    ) {
        DestinationsNavHost(
            modifier = Modifier.padding(it),
            navGraph = NavGraphs.root,
            engine = rememberNavHostEngine(),
            navController = navController,
        )
    }
}