package com.epam.caloriecalc.util

import com.epam.caloriecalc.R

sealed class UiEvent {
    data class Navigate(val route: String) : UiEvent()
    data class ShowSnackbar(
        val messageId: Int,
        val itemName: String? = null,
        val action: SnackbarActionType
    ) : UiEvent()
}

sealed class SnackbarActionType(
    val actionResId: Int
) {
    object Undo : SnackbarActionType(
        actionResId = R.string.undo
    )
}