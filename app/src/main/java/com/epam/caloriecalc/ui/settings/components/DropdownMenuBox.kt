package com.epam.caloriecalc.ui.settings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.epam.caloriecalc.ui.theme.Typography

@Composable
fun DropdownMenuBox(
    items: List<Pair<String, Int>>,
    savedIndex: Int,
    onClick: (Int) -> Unit
) {
    var expandedState by remember { mutableStateOf(false) }

    var selectedIndex by remember { mutableStateOf(savedIndex) }

    LaunchedEffect(key1 = selectedIndex) {
        onClick(selectedIndex)
    }

    Box(
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = items[selectedIndex].first,
            style = Typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    border = BorderStroke(width = 2.dp, Color.Gray)
                )
                .padding(4.dp)
                .clickable(onClick = { expandedState = true })
        )
        DropdownMenu(
            expanded = expandedState,
            onDismissRequest = { expandedState = false },
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .background(Color.Gray, shape = RoundedCornerShape(4.dp))
        ) {
            items.forEachIndexed { index, item ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expandedState = false
                }) {
                    Text(
                        text = item.first,
                        style = Typography.h6,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}