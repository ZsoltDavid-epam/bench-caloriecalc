package com.epam.caloriecalc.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
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
            AddItemScreen(itemlist = testitems)
        }
    }

}