package com.epam.caloriecalc.ui.addnew

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epam.caloriecalc.R
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.repository.CalorieRepository
import com.epam.caloriecalc.data.remote.FakeApi
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
    private val fakeApi: FakeApi
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()
    var rowId: Long? = null
    val productList = repository.getAllProductHistory()

    fun onEvent(event: AddItemEvent) {

        when (event) {
            is AddItemEvent.OnAddItemClick -> {
                viewModelScope.launch {
                    rowId =
                        repository.insertIntake(IntakeRecord(productId = event.product.productId))
                    rowId?.let {
                        try {
                            fakeApi.postAddIntake(it)
                        } catch (e: Exception) {
                            Log.d(FakeApi.TAG_FAKE_API, FakeApi.LOG_ADD_INTAKE_FAILED)
                        }
                    }
                    sendUiEvent(
                        UiEvent.ShowSnackbar(
                            messageId = R.string.snackbar_item_added,
                            itemName = event.product.name,
                            action = SnackbarActionType.Undo
                        )
                    )
                }
            }
            is AddItemEvent.OnUndoClick -> {
                viewModelScope.launch {
                    rowId?.let {
                        repository.deleteIntakeById(it)
                        try {
                            fakeApi.postRemoveIntake(it)
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

