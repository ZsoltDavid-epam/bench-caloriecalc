package com.epam.caloriecalc.util

import com.epam.caloriecalc.data.local.entities.ProductRecord

sealed class AddItemEvent {
    data class OnAddItemClick(val product: ProductRecord) : AddItemEvent()
    object OnUndoClick : AddItemEvent()
}