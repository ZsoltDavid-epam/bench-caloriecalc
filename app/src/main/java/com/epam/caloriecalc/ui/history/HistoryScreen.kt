package com.epam.caloriecalc.ui.history

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.epam.caloriecalc.data.model.ProductIntake
import com.epam.caloriecalc.ui.core.CalorieItemCard
import com.epam.caloriecalc.ui.destinations.DetailScreenDestination
import com.epam.caloriecalc.ui.history.components.DateDivider
import com.epam.caloriecalc.util.HistoryEvent
import com.epam.caloriecalc.util.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect

@OptIn(ExperimentalFoundationApi::class, androidx.compose.animation.ExperimentalAnimationApi::class)
@Destination
@Composable
fun HistoryScreen(
    viewModel: HistoryViewModel = hiltViewModel(),
    navigator: DestinationsNavigator,
    scaffoldState: ScaffoldState
) {
    val intakeHistory by viewModel.intakeHistory.collectAsState(initial = emptyList())

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackbar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = context.getString(event.messageId, event.itemName),
                        actionLabel = context.getString(event.action.actionResId)
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(HistoryEvent.OnUndoDeleteClick)

                    }
                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
    ) {
        AnimatedVisibility(visible = intakeHistory.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                intakeHistory.forEach { (date, productWithIntakes) ->

                    stickyHeader {
                        DateDivider(date = date)
                    }

                    val itemList: MutableList<ProductIntake> = mutableListOf()
                    productWithIntakes.forEach { item ->
                        item.intakes.forEach { intake ->
                            itemList.add(
                                ProductIntake(
                                    item.product,
                                    intake
                                )
                            )
                        }
                    }

                    itemList.sortedWith(compareByDescending { it.intake.timestamp })
                        .forEach { productIntake ->
                            item {
                                CalorieItemCard(
                                    product = productIntake.product,
                                    intake = productIntake.intake,
                                    onClickEvent = {
                                        navigator.navigate(
                                            DetailScreenDestination(
                                                productIntake.product,
                                                productIntake.intake
                                            ),
                                            onlyIfResumed = true
                                        )
                                    },
                                    onDeleteClick = {
                                        viewModel.onEvent(
                                            HistoryEvent.OnDeleteClick(
                                                ProductIntake(
                                                    productIntake.product,
                                                    productIntake.intake
                                                )
                                            )
                                        )
                                    }
                                )
                            }

                        }
                }

            }
        }
    }


}