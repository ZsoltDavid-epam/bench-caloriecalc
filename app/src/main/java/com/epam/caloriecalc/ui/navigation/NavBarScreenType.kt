package com.epam.caloriecalc.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.epam.caloriecalc.R
import com.epam.caloriecalc.util.Localizations

sealed class NavBarScreenType(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Home : NavBarScreenType(
        route = "home",
        title = Localizations.getString(R.string.screen_home),
        icon = Icons.Default.Home
    )
    object History : NavBarScreenType(
        route = "history",
        title = Localizations.getString(R.string.screen_history),
        icon = Icons.Default.History
    )

    object Settings : NavBarScreenType(
        route = "settings",
        title = Localizations.getString(R.string.screen_settings),
        icon = Icons.Default.Settings
    )
}
