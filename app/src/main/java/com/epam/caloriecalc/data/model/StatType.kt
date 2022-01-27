package com.epam.caloriecalc.data.model

import com.epam.caloriecalc.R
import com.epam.caloriecalc.util.Localizations

sealed class StatType {
    object Calories : StatType()
    object Carbs : StatType()
    object Fat : StatType()
    object Protein: StatType()

    override fun toString(): String {
        return when (this) {
            Calories -> Localizations.getString(R.string.calories)
            Carbs -> Localizations.getString(R.string.carbs)
            Fat -> Localizations.getString(R.string.fat)
            Protein -> Localizations.getString(R.string.protein)
        }
    }

    fun getUnitLabel(): String {
        return when (this) {
            Calories -> Localizations.getString(R.string.unit_kcal)
            else -> Localizations.getString(R.string.unit_g)
        }
    }
}

