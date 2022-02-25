package com.epam.caloriecalc.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.epam.caloriecalc.data.model.StatType
import com.epam.caloriecalc.util.toPatternString
import java.time.Instant
import java.util.Locale

@Composable
fun StatsTextRow(
    statType: StatType,
    statAmount: Float = 0f,
    statTime: Instant? = null,
    textStyle: TextStyle = MaterialTheme.typography.subtitle1
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "${stringResource(id = statType.nameResId)}:", style = textStyle)

        Text(
            text =
            if (statTime == null)
                "${
                    String.format(
                        Locale.getDefault(),
                        if (statType != StatType.Amount) "%.1f" else "%.0f",
                        statAmount
                    )
                } ${stringResource(id = statType.unitResId)}"
            else String.format(
                stringResource(
                    id = statType.unitResId,
                    statTime.toPatternString("yyyy.MM.dd"),
                    statTime.toPatternString("HH:mm")
                )
            ),
            style = textStyle
        )
    }
}
