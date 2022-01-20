package com.epam.caloriecalc.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavBarScreenType(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : NavBarScreenType(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object History : NavBarScreenType(
        route = "history",
        title = "History",
        icon = Icons.Default.History
    )

    object Settings : NavBarScreenType(
        route = "settings",
        title = "Settings",
        icon = Icons.Default.Settings
    )
}
