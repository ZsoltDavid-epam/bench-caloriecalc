package com.epam.caloriecalc.ui.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.epam.caloriecalc.R
import com.epam.caloriecalc.ui.settings.components.DropdownMenuBox
import com.epam.caloriecalc.ui.theme.Typography
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_DARK
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_DEFAULT
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_LIGHT

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel()
) {

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
            DropdownMenuBox(
                items = themeItems,
                savedIndex = viewModel.themeState.value,
                onClick = { selectedIndex = it })
        }
    }
}