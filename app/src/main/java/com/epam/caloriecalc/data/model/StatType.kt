package com.epam.caloriecalc.data.model

import com.epam.caloriecalc.R

sealed class StatType(val nameResId: Int, val unitResId: Int) {
    object Calories : StatType(R.string.calories, R.string.unit_kcal)
    object Carbs : StatType(R.string.carbs, R.string.unit_g)
    object Fat : StatType(R.string.fat, R.string.unit_g)
    object Protein: StatType(R.string.protein, R.string.unit_g)
}
