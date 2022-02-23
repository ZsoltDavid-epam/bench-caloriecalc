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
import androidx.navigation.compose.rememberNavController
import com.epam.caloriecalc.ui.core.DefaultSnackbar
import com.epam.caloriecalc.ui.home.HomeViewModel
import com.epam.caloriecalc.ui.navigation.AppToolbar
import com.epam.caloriecalc.ui.navigation.NavBar
import com.epam.caloriecalc.ui.navigation.NavBarHost
import com.epam.caloriecalc.ui.navigation.NavBarScreenType
import com.epam.caloriecalc.ui.theme.CalorieCalcTheme
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_DARK
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_DEFAULT
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_LIGHT
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
                var currentTitleResId by remember { mutableStateOf(R.string.app_name) }

                navController.addOnDestinationChangedListener { controller, _, _ ->
                    canPop = controller.previousBackStackEntry != null
                            && controller.currentBackStackEntry?.destination?.route != NavBarScreenType.Home.route

                    currentTitleResId =
                        when (controller.currentBackStackEntry?.destination?.route?.substringBefore(
                            '?'
                        )) {
                            NavBarScreenType.Home.route -> NavBarScreenType.Home.titleResId
                            NavBarScreenType.AddItem.route -> NavBarScreenType.AddItem.titleResId
                            NavBarScreenType.History.route -> NavBarScreenType.History.titleResId
                            NavBarScreenType.Details.route -> NavBarScreenType.Details.titleResId
                            NavBarScreenType.Settings.route -> NavBarScreenType.Settings.titleResId
                            else -> { R.string.app_name }
                        }
                }
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            AppToolbar(
                                canPop = canPop,
                                navigateUp = {
                                    navController.navigateUp()
                                },
                                titleResId = currentTitleResId
                            )
                        },
                        bottomBar = {
                            NavBar(navController = navController)
                        }
                    ) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            NavBarHost(navController = navController, scaffoldState = scaffoldState)
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

