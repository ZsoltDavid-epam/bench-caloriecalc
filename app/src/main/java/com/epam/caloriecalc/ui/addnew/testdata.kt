package com.epam.caloriecalc.ui.addnew

import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord

val testitems = listOf(
    ProductRecord(
        productId = 15,
        name = "Milk",
        calories = 51.3f,
        totalFat = 1.9f,
        protein = 3.5f,
        carbohydrates = 4.9f,
        isFood = false,
        imagePath = ""
    ),
    ProductRecord(
        productId = 1,
        name = "Sausage",
        calories = 321.9f,
        totalFat = 26.9f,
        protein = 18.4f,
        carbohydrates = 1.4f,
        isFood = true,
        imagePath = ""
    ),
    ProductRecord(
        productId = 2,
        name = "Orange",
        calories = 50.4f,
        totalFat = 0.1f,
        protein = 0.9f,
        carbohydrates = 12.4f,
        isFood = true,
        imagePath = ""
    ),
    ProductRecord(
        productId = 3,
        name = "Apple",
        calories = 53f,
        totalFat = 0.2f,
        protein = 0.3f,
        carbohydrates = 14.1f,
        isFood = true,
        imagePath = ""
    ),
    ProductRecord(
        productId = 4,
        name = "Pear",
        calories = 56.7f,
        totalFat = 0.1f,
        protein = 0.4f,
        carbohydrates = 14.9f,
        isFood = true,
        imagePath = ""
    ),
    ProductRecord(
        productId = 5,
        name = "Butter",
        calories = 721.5f,
        totalFat = 79.6f,
        protein = 0.8f,
        carbohydrates = 0.1f,
        isFood = true,
        imagePath = ""
    ),
    ProductRecord(
        productId = 6,
        name = "Egg",
        calories = 147f,
        totalFat = 9.7f,
        protein = 12.5f,
        carbohydrates = 0.7f,
        isFood = true,
        imagePath = ""
    ),
    ProductRecord(
        productId = 7,
        name = "Banana",
        calories = 89.4f,
        totalFat = 0.3f,
        protein = 1.1f,
        carbohydrates = 23.2,
        isFood = true,
        imagePath = ""
    ),
    ProductRecord(
        productId = 8,
        name = "Salami",
        calories = 368f,
        totalFat = 32.1f,
        protein = 21.1f,
        carbohydrates = 0.7f,
        isFood = true,
        imagePath = ""
    ),
    ProductRecord(
        productId = 9,
        name = "Grapes",
        calories = 69.6f,
        totalFat = 0.2f,
        protein = 0.7f,
        carbohydrates = 18.1f,
        isFood = true,
        imagePath = ""
    ),
    ProductRecord(
        productId = 10,
        name = "Pork(Grilled)",
        calories = 236.2f,
        totalFat = 14f,
        protein = 26.2f,
        carbohydrates = 0f,
        isFood = true,
        imagePath = ""
    ),
    ProductRecord(
        productId = 11,
        name = "Coffee",
        calories = 1f,
        totalFat = 0f,
        protein = 0.1f,
        carbohydrates = 0f,
        isFood = false,
        imagePath = ""
    ),
    ProductRecord(
        productId = 12,
        name = "Wine",
        calories = 81.4f,
        totalFat = 0f,
        protein = 0f,
        carbohydrates = 2.6f,
        isFood = false,
        imagePath = ""
    ),
    ProductRecord(
        productId = 13,
        name = "Tea",
        calories = 1f,
        totalFat = 0f,
        protein = 0f,
        carbohydrates = 0.3f,
        isFood = false,
        imagePath = ""
    ),
    ProductRecord(
        productId = 14,
        name = "Spirits",
        calories = 227.7f,
        totalFat = 0f,
        protein = 0f,
        carbohydrates = 0f,
        isFood = true,
        imagePath = ""
    )
)

val testIntakes = listOf(
    IntakeRecord(
        intakeId = 1,
        productId = 14,
        amount = 1
    ),
    IntakeRecord(
        intakeId = 2,
        productId = 12,
        amount = 3
    )


)
