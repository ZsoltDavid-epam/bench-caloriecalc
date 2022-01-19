package com.epam.caloriecalc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.epam.caloriecalc.ui.home.HomeScreen

@Composable
fun NavBarGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = NavBarScreenType.Home.route
    ) {
        composable(route = NavBarScreenType.Home.route) {
            HomeScreen()
        }
        composable(route = NavBarScreenType.History.route) {
            //HistoryScreen()
        }
        composable(route = NavBarScreenType.Settings.route) {
            //SettingsScreen()
        }

    }

}