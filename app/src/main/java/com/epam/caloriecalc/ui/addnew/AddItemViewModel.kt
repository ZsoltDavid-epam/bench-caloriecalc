package com.epam.caloriecalc.ui.addnew

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.repository.CalorieRepository
import com.epam.caloriecalc.util.AddItemEvent
import com.epam.caloriecalc.util.SnackbarActionType
import com.epam.caloriecalc.util.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    internal val repository: CalorieRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            for (item in testitems)
                repository.insertProduct(item)
        }
    }

    private lateinit var pendingItem: IntakeRecord
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddItemEvent) {
        when (event) {
            is AddItemEvent.OnAddItemClick -> {
                viewModelScope.launch {
                    repository.insertIntake(IntakeRecord(productId = event.product.productId))
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            message = "Undo",
                            action = SnackbarActionType.Undo
                        )
                    )
                }
            }
            is AddItemEvent.OnUndoClick -> {
                viewModelScope.launch {
                    pendingItem = repository.getLastIntake()
                    repository.deleteIntake(pendingItem)
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

