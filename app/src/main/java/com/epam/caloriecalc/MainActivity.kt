package com.epam.caloriecalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.epam.caloriecalc.ui.NavGraphs
import com.epam.caloriecalc.ui.addnew.AddItemScreen
import com.epam.caloriecalc.ui.core.DefaultSnackbar
import com.epam.caloriecalc.ui.destinations.AddItemScreenDestination
import com.epam.caloriecalc.ui.destinations.DetailScreenDestination
import com.epam.caloriecalc.ui.destinations.HistoryScreenDestination
import com.epam.caloriecalc.ui.destinations.HomeScreenDestination
import com.epam.caloriecalc.ui.history.HistoryScreen
import com.epam.caloriecalc.ui.home.HomeScreen
import com.epam.caloriecalc.ui.home.HomeViewModel
import com.epam.caloriecalc.ui.navDestination
import com.epam.caloriecalc.ui.navigation.AppToolbar
import com.epam.caloriecalc.ui.navigation.NavBar
import com.epam.caloriecalc.ui.navigation.NavBarDestination
import com.epam.caloriecalc.ui.theme.CalorieCalcTheme
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_DARK
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_DEFAULT
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_LIGHT
import com.epam.caloriecalc.util.title
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val darkTheme by homeViewModel.themeMode.collectAsState(initial = SETTINGS_THEME_DEFAULT)
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { homeViewModel.isLoading }

            CalorieCalcTheme(
                darkTheme = when (darkTheme) {
                    SETTINGS_THEME_LIGHT -> false
                    SETTINGS_THEME_DARK -> true
                    else -> isSystemInDarkTheme()
                }
            ) {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                var canPop by remember { mutableStateOf(false) }

                val currentDestination = navController.currentBackStackEntryAsState()
                    .value?.navDestination

                navController.addOnDestinationChangedListener { controller, _, _ ->
                    canPop = controller.previousBackStackEntry != null
                            && controller.currentBackStackEntry?.destination?.route != NavBarDestination.Home.direction.route
                }
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            currentDestination?.title?.let {
                                var details = ""
                                if (currentDestination == DetailScreenDestination) {
                                    navController.currentBackStackEntry?.let { entry ->
                                        val arg = DetailScreenDestination.argsFrom(
                                            entry
                                        )
                                        details = arg.product.name
                                    }
                                }
                                AppToolbar(
                                    canPop = canPop,
                                    navigateUp = {
                                        navController.navigateUp()
                                    },
                                    titleResId = it,
                                    title = if (currentDestination == DetailScreenDestination) {
                                        navController.currentBackStackEntry?.let { entry ->
                                            val argument = DetailScreenDestination.argsFrom(
                                                entry
                                            )
                                            argument.product.name
                                        }
                                    } else null
                                )
                            }
                        },
                        bottomBar = {
                            NavBar(navController)
                        }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {

                            DestinationsNavHost(
                                navGraph = NavGraphs.root,
                                navController = navController
                            ) {
                                composable(HomeScreenDestination) {
                                    HomeScreen(
                                        scaffoldState = scaffoldState,
                                        navigator = destinationsNavigator
                                    )
                                }
                                composable(HistoryScreenDestination) {
                                    HistoryScreen(
                                        scaffoldState = scaffoldState,
                                        navigator = destinationsNavigator
                                    )
                                }
                                composable(AddItemScreenDestination) {
                                    AddItemScreen(
                                        scaffoldState = scaffoldState
                                    )
                                }
                            }
                            DefaultSnackbar(
                                snackbarHostState = scaffoldState.snackbarHostState,
                                onAction = {
                                    scaffoldState.snackbarHostState.currentSnackbarData?.performAction()
                                },
                                modifier = Modifier.align(Alignment.BottomCenter)
                            )
                        }
                    }
                }
            }
        }
    }
}

