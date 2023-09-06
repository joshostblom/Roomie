package com.example.roomie.features.navBar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavBar(
    navController: NavController,
    items: List<NavBarItem>,
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar (
        containerColor = MaterialTheme.colorScheme.primary
    ) {

        items.forEach {
            NavBarTab(
                item = it,
                currentRoute = backStackEntry.value?.destination?.route,
            )
        }
    }
}

@Composable
fun RowScope.NavBarTab(
    currentRoute: String?,
    item: NavBarItem,
) {
    NavigationBarItem(
        selected = currentRoute == item.route,
        onClick = {
            item.onClick()
        },
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = item.title
            )
        },
        label = {
            if (item.title != null) {
                Text(
                    text = item.title
                )
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MaterialTheme.colorScheme.primary,
            selectedTextColor = MaterialTheme.colorScheme.onPrimary,
            indicatorColor = MaterialTheme.colorScheme.secondary,
        )
    )
}