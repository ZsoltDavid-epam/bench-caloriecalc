package com.epam.caloriecalc.ui.history

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.epam.caloriecalc.util.IntakeEvent
import com.epam.caloriecalc.util.UiEvent
import kotlinx.coroutines.flow.collect

@Composable
fun HistoryScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: HistoryViewModel = hiltViewModel()
) {

    val todayDate by viewModel.todayDate.collectAsState()

    val scaffoldState = rememberScaffoldState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    onNavigate(event)
                }
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = context.getString(event.action.actionResId)
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(IntakeEvent.OnUndoDeleteClick)
                    }
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            //Text(todayDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)))
        }
    }


}