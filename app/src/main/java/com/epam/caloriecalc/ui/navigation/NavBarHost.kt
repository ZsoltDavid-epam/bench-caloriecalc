package com.epam.caloriecalc.ui.navigation

import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.epam.caloriecalc.ui.addnew.AddItemScreen
import com.epam.caloriecalc.ui.addnew.testitems
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

        composable(route = NavBarScreenType.AddItem.route) {
            AddItemScreen()
        }
    }
}