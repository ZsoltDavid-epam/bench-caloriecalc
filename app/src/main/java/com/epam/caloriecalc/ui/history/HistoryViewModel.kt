package com.epam.caloriecalc.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epam.caloriecalc.R
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.repository.CalorieRepository
import com.epam.caloriecalc.ui.navigation.NavBarScreenType
import com.epam.caloriecalc.util.IntakeEvent
import com.epam.caloriecalc.util.SnackbarActionType
import com.epam.caloriecalc.util.UiEvent
import com.epam.caloriecalc.util.toLocalDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.Instant
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: CalorieRepository
) : ViewModel() {

    val intakeHistory = repository.getAllIntakeHistory()

    val todayDate = MutableStateFlow(Instant.now().toLocalDate())

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var deletedIntake: IntakeRecord? = null

    fun onEvent(event: IntakeEvent) {
        when (event) {
            is IntakeEvent.OnDetailsClick -> {
                sendUiEvent(
                    UiEvent.Navigate(
                        NavBarScreenType.Details.route +
                                "?intakeId=${event.intakeWithProduct.intake.intakeId}"
                    )
                )
            }
            is IntakeEvent.OnDeleteClick -> {
                viewModelScope.launch {
                    deletedIntake = event.intakeWithProduct.intake
                    repository.deleteIntake(event.intakeWithProduct.intake)
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            message = event.intakeWithProduct.product.name,
                            action = SnackbarActionType.Undo
                        )
                    )
                }
            }
            is IntakeEvent.OnUndoDeleteClick -> {
                deletedIntake?.let { intake ->
                    viewModelScope.launch {
                        repository.insertIntake(intake)
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