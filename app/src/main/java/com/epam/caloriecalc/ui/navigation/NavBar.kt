package com.epam.caloriecalc.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.epam.caloriecalc.ui.navDestination
import com.ramcosta.composedestinations.navigation.navigateTo

@Composable
fun NavBar(
    navController: NavController
) {
    val currentDestination = navController.currentBackStackEntryAsState()
        .value?.navDestination

    BottomNavigation {
        NavBarDestination.values().forEach { destination ->
            BottomNavigationItem(
                selected = currentDestination == destination.direction,
                onClick = {
                    navController.navigateTo(destination.direction) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(destination.icon, contentDescription = stringResource(destination.label))
                },
                label = {
                    Text(stringResource(destination.label))
                }
            )
        }
    }
}