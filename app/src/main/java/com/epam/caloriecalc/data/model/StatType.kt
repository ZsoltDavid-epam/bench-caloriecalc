package com.epam.caloriecalc.data.model

sealed class StatType {
    object Calories : StatType()
    object Carbs : StatType()
    object Fat : StatType()
    object Protein: StatType()

    override fun toString(): String {
        return when (this) {
            Calories -> "Calories"
            Carbs -> "Carbs"
            Fat -> "Fat"
            Protein -> "Protein"
        }
    }

    fun getUnitLabel(): String {
        return when (this) {
            Calories -> "kcal"
            else -> "g"
        }
    }
}

