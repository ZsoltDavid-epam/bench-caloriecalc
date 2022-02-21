package com.epam.caloriecalc.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.epam.caloriecalc.ui.addnew.AddItemScreen
import com.epam.caloriecalc.ui.addnew.testitems
import androidx.navigation.navArgument
import com.epam.caloriecalc.ui.details.DetailScreen
import com.epam.caloriecalc.ui.history.HistoryScreen
import com.epam.caloriecalc.ui.home.HomeScreen

@Composable
fun NavBarHost(
    navController: NavHostController,
    scaffoldState: ScaffoldState
) {
    NavHost(
        navController = navController,
        startDestination = NavBarScreenType.Home.route
    ) {
        composable(route = NavBarScreenType.Home.route) {
            HomeScreen(
                onAddPressed = {
                    navController.navigate(NavBarScreenType.AddItem.route)
                },
                scaffoldState = scaffoldState
            )
        }
        composable(route = NavBarScreenType.History.route) {
            HistoryScreen(
                onNavigate = {
                    navController.navigate(it.route)
                }
            )
        }
        composable(route = NavBarScreenType.Settings.route) {
            //SettingsScreen()
        }
        composable(
            route = NavBarScreenType.Details.route + "?intakeId={intakeId}",
            arguments = listOf(
                navArgument(name = "intakeId") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) {
            DetailScreen(
                onPopBackStack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = NavBarScreenType.AddItem.route) {
            AddItemScreen()
        }
    }

}