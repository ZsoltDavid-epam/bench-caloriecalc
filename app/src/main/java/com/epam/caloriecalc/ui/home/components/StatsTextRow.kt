package com.epam.caloriecalc.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.epam.caloriecalc.data.model.StatType
import java.util.*

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
        Text(text = "${stringResource(id = statType.nameResId)}:")

        Text(text = "${String.format(Locale.getDefault(), "%.2f", statAmount)} ${stringResource(id = statType.unitResId)}")
    }
}