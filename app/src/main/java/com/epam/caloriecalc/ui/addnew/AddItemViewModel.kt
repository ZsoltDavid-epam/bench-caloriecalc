package com.epam.caloriecalc.ui.addnew

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord
import com.epam.caloriecalc.data.local.repository.CalorieRepository
import com.epam.caloriecalc.util.AddItemEvent
import com.epam.caloriecalc.util.SnackbarActionType
import com.epam.caloriecalc.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    internal val repository: CalorieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddItemEvent) {
        when (event) {
            is AddItemEvent.OnAddItemClick -> {
                viewModelScope.launch {

                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            message = "Undo",
                            action = SnackbarActionType.Undo
                        )
                    )
                    return@launch
                }
            }
        }
    }

    fun repositoryInsertIntake(item: ProductRecord){
        viewModelScope.launch {
            repository.insertIntake(IntakeRecord(productId = item.productId))
        }

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}

