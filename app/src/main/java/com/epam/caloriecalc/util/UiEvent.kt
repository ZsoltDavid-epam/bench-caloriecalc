package com.epam.caloriecalc.util

import com.epam.caloriecalc.R
import com.epam.caloriecalc.data.local.entities.IntakeRecord

sealed class UiEvent {
    data class ShowSnackbar(
        val messageId: Int,
        val itemName: String? = null,
        val action: SnackbarActionType
    ) : UiEvent()
    data class RestoreDeleted(
        val item: IntakeRecord
    ) : UiEvent()
}

sealed class SnackbarActionType(
    val actionResId: Int
) {
    object Undo : SnackbarActionType(
        actionResId = R.string.undo
    )
}