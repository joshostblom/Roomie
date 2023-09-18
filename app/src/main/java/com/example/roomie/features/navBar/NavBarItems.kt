package com.example.roomie.features.navBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.roomie.navigation.destinations.HomeDestinationDestination
import com.example.roomie.navigation.destinations.PayRentDestinationDestination
import com.example.roomie.navigation.destinations.PaymentsDestinationDestination

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
    route = HomeDestinationDestination.route,
    onClick = {
        navController.navigate(HomeDestinationDestination.route)
    }
)

class PayRentNavBarItem(
    navController: NavController
) : NavBarItem(
    title = "Pay Rent",
    icon = Icons.Default.Check,
    route = PayRentDestinationDestination.route,
    onClick = {
        navController.navigate(PayRentDestinationDestination.route)
    }
)

class PaymentsNavBarItem(
    navController: NavController
) : NavBarItem(
    title = "Payments",
    icon = Icons.Default.List,
    route = PaymentsDestinationDestination.route,
    onClick = {
        navController.navigate(PaymentsDestinationDestination.route)
    }
)