package com.epam.caloriecalc.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.epam.caloriecalc.R
import com.epam.caloriecalc.data.model.DailyStat
import com.epam.caloriecalc.data.model.StatType

@Composable
fun TodayStats(
    modifier: Modifier = Modifier,
    dailyStat: DailyStat
) {
    Column(modifier = modifier) {
        Text(text = stringResource(R.string.todays_stats) + ":")

        Spacer(modifier = Modifier.height(24.dp))

        dailyStat.apply {
            StatsTextRow(statType = StatType.Calories, statAmount = totalCalories)
            StatsTextRow(statType = StatType.Carbs, statAmount = totalCarbs)
            StatsTextRow(statType = StatType.Fat, statAmount = totalFat)
            StatsTextRow(statType = StatType.Protein, statAmount = totalProtein)
        }
    }
}