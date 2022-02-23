package com.epam.caloriecalc.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.epam.caloriecalc.R

@Composable
fun AppToolbar(
    canPop: Boolean,
    navigateUp: () -> Unit,
    titleResId: Int
) {
    TopAppBar(
        title = {
            Text(stringResource(id = titleResId))
        },
        navigationIcon = if (canPop) {
            {
                IconButton(onClick = { navigateUp() }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }

            }
        } else {
            {
                Icon(
                    painterResource(id = R.drawable.ic_calories),
                    contentDescription = stringResource(R.string.app_icon),
                    modifier = Modifier.fillMaxSize(0.75f)
                )
            }
        }
    )

}