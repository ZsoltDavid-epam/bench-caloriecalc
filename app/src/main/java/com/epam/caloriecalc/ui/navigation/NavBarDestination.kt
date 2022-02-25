package com.epam.caloriecalc.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.epam.caloriecalc.R
import com.epam.caloriecalc.ui.destinations.HistoryScreenDestination
import com.epam.caloriecalc.ui.destinations.HomeScreenDestination
import com.epam.caloriecalc.ui.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec

enum class NavBarDestination(
    val direction: DirectionDestinationSpec,
    val icon: ImageVector,
    @StringRes val label: Int
) {
    Home(HomeScreenDestination, Icons.Default.Home, R.string.screen_home),
    History(HistoryScreenDestination, Icons.Default.History, R.string.screen_history),
    Settings(SettingsScreenDestination, Icons.Default.Settings, R.string.screen_settings)
}