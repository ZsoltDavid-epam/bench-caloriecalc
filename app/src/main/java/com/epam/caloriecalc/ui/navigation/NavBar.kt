package com.epam.caloriecalc.ui.navigation

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun NavBar(
    navController: NavHostController
) {
    val screens = listOf(
        NavBarScreenType.Home,
        NavBarScreenType.History,
        NavBarScreenType.Settings
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screenType = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }

}

@Composable
fun RowScope.AddItem(
    screenType: NavBarScreenType,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screenType.title)
        },
        icon = {
            Icon(imageVector = screenType.icon, contentDescription = "${screenType.title} Navigation Icon")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screenType.route
        } == true,
        onClick = {
            navController.navigate(screenType.route)
        }
    )
}