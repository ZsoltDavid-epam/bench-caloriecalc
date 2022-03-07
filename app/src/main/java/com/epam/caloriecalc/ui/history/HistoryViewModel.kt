package com.epam.caloriecalc.ui.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epam.caloriecalc.R
import com.epam.caloriecalc.data.local.relations.ProductWithIntakes
import com.epam.caloriecalc.data.local.repository.CalorieRepository
import com.epam.caloriecalc.data.model.ProductIntake
import com.epam.caloriecalc.data.remote.FakeApi
import com.epam.caloriecalc.util.HistoryEvent
import com.epam.caloriecalc.util.SnackbarActionType
import com.epam.caloriecalc.util.UiEvent
import com.epam.caloriecalc.util.toLocalDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
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

    private var deletedIntake: ProductIntake? = null

    fun onEvent(event: HistoryEvent) {
        when (event) {
            is HistoryEvent.OnDeleteClick -> {
                viewModelScope.launch {
                    deletedIntake = event.productIntake
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            messageId = R.string.snackbar_delete_intake_message,
                            itemName = event.productIntake.product.name,
                            action = SnackbarActionType.Undo
                        )
                    )
                }
            }
            is HistoryEvent.OnUndoDeleteClick -> {
                deletedIntake?.let {
                    sendUiEvent(
                        UiEvent.RestoreDeleted(it.intake)
                    )
                }
            }
            is HistoryEvent.DeleteFinal -> {
                deletedIntake?.let {
                    viewModelScope.launch {
                        repository.deleteIntake(it.intake)

                        try {
                            fakeApi.postRemoveIntake(it.intake.intakeId.toLong())
                        } catch (e: Exception) {
                            Log.d(FakeApi.TAG_FAKE_API, FakeApi.LOG_REMOVE_INTAKE_FAILED)
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

}