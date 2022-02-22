package com.epam.caloriecalc.ui.addnew

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.epam.caloriecalc.R
import com.epam.caloriecalc.util.AddItemEvent
import com.epam.caloriecalc.util.UiEvent
import kotlinx.coroutines.flow.collect


@Composable
fun AddItemScreen(
    viewModel: AddItemViewModel = hiltViewModel(),
) {

    val productList by viewModel.productList.collectAsState(initial = emptyList())

    val scaffoldState = rememberScaffoldState()

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = context.getString(event.action.actionResId)
                    )

                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(AddItemEvent.OnUndoClick)
                    }
                }
            }
        }

    }
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(vertical = 10.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        stringResource(R.string.addnewitem_title),
                        style = MaterialTheme.typography.h6
                    )
                }
            }
            items(productList.sortedWith(compareBy { it.name })) { item ->
                AddItemCard(item) {
                    viewModel.onEvent(AddItemEvent.OnAddItemClick(item))
                }
            }
        }
    }
}