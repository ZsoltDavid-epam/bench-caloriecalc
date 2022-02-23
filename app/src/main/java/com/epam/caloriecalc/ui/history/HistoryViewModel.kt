package com.epam.caloriecalc.ui.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epam.caloriecalc.R
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.relations.ProductWithIntakes
import com.epam.caloriecalc.data.local.repository.CalorieRepository
import com.epam.caloriecalc.data.remote.FakeApi
import com.epam.caloriecalc.ui.navigation.NavBarScreenType
import com.epam.caloriecalc.util.HistoryEvent
import com.epam.caloriecalc.util.SnackbarActionType
import com.epam.caloriecalc.util.UiEvent
import com.epam.caloriecalc.util.toLocalDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: CalorieRepository,
    private val fakeApi: FakeApi
) : ViewModel() {

    val intakeHistory = repository.getAllIntakeHistory().map { list ->
        val datedIntakeHistory: MutableList<Pair<LocalDate, List<ProductWithIntakes>>> =
            mutableListOf()

        val fullDateList =
            list.flatMap { it.intakes }
                .map { it.timestamp.toLocalDate() }
                .toSet()
                .sortedWith(
                    compareByDescending { it }
                )

        fullDateList.forEach { date ->
            val currentDateItems =
                list.map { item ->
                    item.copy(intakes = item.intakes.filter { intake ->
                        intake.timestamp.toLocalDate() == date
                    })
                }

            datedIntakeHistory.add(
                Pair(
                    date,
                    currentDateItems
                )
            )
        }

        datedIntakeHistory
    }

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedIntake: IntakeRecord? = null

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.OnDetailsClick -> {
                sendUiEvent(
                    UiEvent.Navigate(
                        NavBarScreenType.Details.route +
                                "?intakeId=${event.productIntake.intake.intakeId}"
                    )
                )
            }
            is HistoryEvent.OnDeleteClick -> {
                viewModelScope.launch {
                    deletedIntake = event.productIntake.intake
                    repository.deleteIntake(event.productIntake.intake)
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            messageId = R.string.snackbar_delete_intake_message,
                            itemName = event.productIntake.product.name,
                            action = SnackbarActionType.Undo
                        )
                    )
                    try {
                        fakeApi.postRemoveIntake(event.productIntake.intake.intakeId.toLong())
                    } catch (e: Exception) {
                        Log.d(FakeApi.TAG_FAKE_API, FakeApi.LOG_REMOVE_INTAKE_FAILED)
                    }
                }
            }
            is HistoryEvent.OnUndoDeleteClick -> {
                deletedIntake?.let { intake ->
                    viewModelScope.launch {
                        repository.insertIntake(intake)
                        try {
                            fakeApi.postAddIntake(intake.intakeId.toLong())
                        } catch (e: Exception) {
                            Log.d(FakeApi.TAG_FAKE_API, FakeApi.LOG_ADD_INTAKE_FAILED)
                        }
                    }
                }
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

    fun insertDemoIntakes() {
        viewModelScope.launch {
            delay(1000)
            repository.insertIntake(
                IntakeRecord(
                    productId = 4,
                    intakeId = 33
                )
            )
            repository.insertIntake(
                IntakeRecord(
                    productId = 3,
                    intakeId = 35,
                    timestamp = Instant.now().minus(20, ChronoUnit.DAYS)
                )
            )
            repository.insertIntake(
                IntakeRecord(
                    productId = 2,
                    intakeId = 36,
                    timestamp = Instant.now().minus(20, ChronoUnit.DAYS)
                        .minus(1, ChronoUnit.MINUTES)
                )
            )
            repository.insertIntake(
                IntakeRecord(
                    productId = 3,
                    intakeId = 34,
                    timestamp = Instant.now().minus(20, ChronoUnit.MINUTES)
                )
            )

        }
    }
}