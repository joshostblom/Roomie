package com.example.roomie.features.navBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.roomie.navigation.destinations.HomeDestination

open class NavBarItem(
    val title: String? = null,
    val icon: ImageVector,
    val onClick: () -> Unit,
)

class HomeNavBarItem(
    navController: NavController
) : NavBarItem(
    title = "Home",
    icon = Icons.Default.Home,
    onClick = {
        navController.navigate(HomeDestination.route)
    }
)