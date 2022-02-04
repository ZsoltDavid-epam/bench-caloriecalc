package com.epam.caloriecalc.util

import com.epam.caloriecalc.data.local.relations.IntakeWithProduct

sealed class IntakeEvent {
    data class OnDetailsClick(val intakeWithProduct: IntakeWithProduct): IntakeEvent()
    //data class OnDoneChange(val intake: IntakeRecord, val isDone: Boolean): IntakeEvent()
    data class OnDeleteClick(val intakeWithProduct: IntakeWithProduct): IntakeEvent()
    object OnUndoDeleteClick: IntakeEvent()
    //object OnAddIntakeClick: CalorieEvent()
}
