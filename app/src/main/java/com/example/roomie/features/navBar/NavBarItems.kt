package com.example.roomie.features.navBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.roomie.navigation.destinations.HomeDestination
import com.example.roomie.navigation.destinations.PaymentsDestination

open class NavBarItem(
    val title: String? = null,
    val icon: ImageVector,
    val route: String,
    val onClick: () -> Unit,
)

class HomeNavBarItem(
    navController: NavController
) : NavBarItem(
    title = "Home",
    icon = Icons.Default.Home,
    route = HomeDestination.route,
    onClick = {
        navController.navigate(HomeDestination.route)
    }
)

class ItemsNavBarItem(
    navController: NavController
) : NavBarItem(
    title = "Payments",
    icon = Icons.Default.List,
    route = PaymentsDestination.route,
    onClick = {
        navController.navigate(PaymentsDestination.route)
    }
)