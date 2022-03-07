package com.epam.caloriecalc.util

import com.epam.caloriecalc.data.model.ProductIntake

sealed class HistoryEvent {
    data class OnDeleteClick(val productIntake: ProductIntake): HistoryEvent()
    object OnUndoDeleteClick: HistoryEvent()
    object DeleteFinal: HistoryEvent()
}