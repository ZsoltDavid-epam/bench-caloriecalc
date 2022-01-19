package com.epam.caloriecalc.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.epam.caloriecalc.data.model.StatType
import com.epam.caloriecalc.ui.home.components.StatsTextRow

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
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
                    contentDescription = "Add"
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

            Row(modifier = Modifier.fillMaxWidth(0.75f)) {
                Text(text = "Today's stats:")
            }

            Spacer(modifier = Modifier.height(24.dp))

            StatsTextRow(statType = StatType.Energy, statAmount = 2125)
            StatsTextRow(statType = StatType.Carbs, statAmount = 86)
            StatsTextRow(statType = StatType.Fat, statAmount = 113)
            StatsTextRow(statType = StatType.Protein, statAmount = 129)

        }

    }
}

@Preview
@Composable
fun HomeScreenPreview(

) {
    HomeScreen()
}