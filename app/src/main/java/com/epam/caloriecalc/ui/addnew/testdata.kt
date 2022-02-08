package com.epam.caloriecalc.ui.addnew

import com.epam.caloriecalc.data.local.entities.ProductRecord

val testitems = listOf(
    ProductRecord(
        productId = 0,
        name = "Milk",
        calories = 15.2f,
        totalFat = 5.32f,
        protein = 36.7f,
        carbohydrates = 25.1f,
        isFood = false,
        imagePath = ""
    ),
    ProductRecord(
        productId = 1,
        name = "Sausage",
        calories = 150.2f,
        totalFat = 50.32f,
        protein = 360.7f,
        carbohydrates = 250.1f,
        isFood = true,
        imagePath = ""
    )
)