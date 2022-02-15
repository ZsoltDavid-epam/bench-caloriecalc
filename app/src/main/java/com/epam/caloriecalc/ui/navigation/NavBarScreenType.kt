package com.epam.caloriecalc.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddTask
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.epam.caloriecalc.R

sealed class NavBarScreenType(
    val route: String,
    val titleResId: Int,
    val icon: ImageVector
) {
    object Home : NavBarScreenType(
        route = "home",
        titleResId = R.string.screen_home,
        icon = Icons.Default.Home
    )
    object History : NavBarScreenType(
        route = "history",
        titleResId = R.string.screen_history,
        icon = Icons.Default.History
    )
    object Settings : NavBarScreenType(
        route = "settings",
        titleResId = R.string.screen_settings,
        icon = Icons.Default.Settings
    )

    object AddItem : NavBarScreenType(
        route = "additem",
        titleResId = R.string.screen_additem,
        icon = Icons.Default.AddTask
    )
}
