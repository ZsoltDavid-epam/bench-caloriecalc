package com.epam.caloriecalc.data.model

sealed class StatType {
    object Energy : StatType()
    object Carbs : StatType()
    object Fat : StatType()
    object Protein: StatType()

    override fun toString(): String {
        return when (this) {
            Energy -> "Energy"
            Carbs -> "Carbs"
            Fat -> "Fat"
            Protein -> "Protein"
        }
    }

    fun getUnitLabel(): String {
        return when (this) {
            Energy -> "kcal"
            else -> "g"
        }
    }
}

