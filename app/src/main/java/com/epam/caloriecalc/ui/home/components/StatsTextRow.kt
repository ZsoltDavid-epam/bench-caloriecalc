package com.epam.caloriecalc.ui.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.epam.caloriecalc.data.model.StatType

@Composable
fun StatsTextRow(
    statType: StatType,
    statAmount: Float
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(0.75f),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "$statType:")

        Text(text = "${String.format("%.2f", statAmount)} ${statType.getUnitLabel()}")

    }

}