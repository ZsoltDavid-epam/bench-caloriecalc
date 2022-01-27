package com.epam.caloriecalc.util

import com.epam.caloriecalc.CalorieCalcApplication

object Localizations {
    fun getString (id: Int): String =
        CalorieCalcApplication.appResources.getString(id)
}