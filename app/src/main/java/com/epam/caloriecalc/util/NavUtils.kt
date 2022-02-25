package com.epam.caloriecalc.util

import androidx.annotation.StringRes
import com.epam.caloriecalc.R
import com.epam.caloriecalc.ui.destinations.AddItemScreenDestination
import com.epam.caloriecalc.ui.destinations.Destination
import com.epam.caloriecalc.ui.destinations.DetailScreenDestination
import com.epam.caloriecalc.ui.destinations.HistoryScreenDestination
import com.epam.caloriecalc.ui.destinations.HomeScreenDestination
import com.epam.caloriecalc.ui.destinations.SettingsScreenDestination


@get:StringRes
val Destination.title
    get(): Int {
        return when (this) {
            HomeScreenDestination -> R.string.screen_home
            AddItemScreenDestination -> R.string.screen_additem
            HistoryScreenDestination -> R.string.screen_history
            DetailScreenDestination -> R.string.details
            SettingsScreenDestination -> R.string.screen_settings
        }
    }