package com.example.roomie.features.navBar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NavBar(
    items: List<NavBarItem>
) {
    NavigationBar (
        containerColor = MaterialTheme.colorScheme.primary
    ) {

        items.forEach {
            NavBarTab(item = it)
        }
    }
}

@Composable
fun RowScope.NavBarTab(
    item: NavBarItem
) {
    NavigationBarItem(
        selected = true,
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
        }
    )
}