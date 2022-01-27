package com.epam.caloriecalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.epam.caloriecalc.ui.navigation.NavBar
import com.epam.caloriecalc.ui.navigation.NavBarGraph
import com.epam.caloriecalc.ui.theme.CalorieCalcTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieCalcTheme {
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
                                        contentDescription = getString(R.string.app_icon),
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

