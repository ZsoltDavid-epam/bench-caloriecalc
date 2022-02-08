package com.epam.caloriecalc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.epam.caloriecalc.ui.addnew.AddItemScreen
import com.epam.caloriecalc.ui.addnew.testitems
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
            HomeScreen { navController.navigate(NavBarScreenType.AddItem.route) }
        }
        composable(route = NavBarScreenType.History.route) {
            //HistoryScreen()
        }
        composable(route = NavBarScreenType.Settings.route) {
            //SettingsScreen()
        }
        composable(route = NavBarScreenType.AddItem.route) {
            AddItemScreen(itemlist = testitems)
        }
    }

}