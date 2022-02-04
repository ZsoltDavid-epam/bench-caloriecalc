package com.epam.caloriecalc.ui.settings

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.epam.caloriecalc.R
import com.epam.caloriecalc.ui.theme.Typography
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_DEFAULT
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_LIGHT
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_DARK

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {

    var expandedState by remember { mutableStateOf(false) }

    val themeItems =
        listOf(
            (stringResource(R.string.theme_device_default) to SETTINGS_THEME_DEFAULT),
            (stringResource(R.string.theme_light) to SETTINGS_THEME_LIGHT),
            (stringResource(R.string.theme_dark) to SETTINGS_THEME_DARK)
        )

    var selectedIndex by remember { mutableStateOf(viewModel.themeState.value) }

    LaunchedEffect(key1 = selectedIndex) {
        viewModel.changeThemeMode(themeItems[selectedIndex].second)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = stringResource(R.string.appearance) + ":",
                style = Typography.h5,
                textDecoration = TextDecoration.Underline
            )
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = themeItems[selectedIndex].first,
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
                    themeItems.forEachIndexed { index, s ->
                        DropdownMenuItem(onClick = {
                            selectedIndex = index
                            expandedState = false
                        }) {
                            Text(
                                text = s.first,
                                style = Typography.h6,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}