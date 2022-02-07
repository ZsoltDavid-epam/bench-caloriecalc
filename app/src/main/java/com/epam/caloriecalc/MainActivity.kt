package com.epam.caloriecalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.epam.caloriecalc.ui.home.HomeViewModel
import com.epam.caloriecalc.ui.navigation.NavBar
import com.epam.caloriecalc.ui.navigation.NavBarGraph
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
            splashScreen.setKeepOnScreenCondition { homeViewModel.isLoading.value }

            CalorieCalcTheme(
                darkTheme = when (darkTheme) {
                    SETTINGS_THEME_LIGHT -> false
                    SETTINGS_THEME_DARK -> true
                    else -> isSystemInDarkTheme()
                }
            ) {
                val navController = rememberNavController()
                Surface(color = MaterialTheme.colors.background) {
                    Scaffold(
                        topBar = {
                            TopAppBar(
                                title = {
                                    Text(stringResource(id = R.string.app_name))
                                },
                                navigationIcon = {
                                    Icon(
                                        painterResource(id = R.drawable.ic_calories),
                                        contentDescription = stringResource(R.string.app_icon),
                                        modifier = Modifier.fillMaxSize(0.75f)
                                    )
                                }
                            )
                        },
                        bottomBar = {
                            NavBar(navController = navController)
                        }
                    ) {
                        NavBarGraph(navController = navController)
                    }
                }
            }
        }
    }
}

