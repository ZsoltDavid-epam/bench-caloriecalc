package com.epam.caloriecalc.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.epam.caloriecalc.R
import com.epam.caloriecalc.ui.home.components.TodayStats

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val intakeTodayStats by viewModel.intakesTodayStats.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .offset(x = (-5).dp, y = (-65).dp)
            .padding(16.dp),
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier,
                onClick = {
                    /* Open the Add Screen */
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End

    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth(0.75f)
                .fillMaxHeight(1f)
                .offset(x = 32.dp, y = 16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            TodayStats(dailyStat = intakeTodayStats)


        }

    }
}