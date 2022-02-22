package com.epam.caloriecalc.data.model

import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord

data class ProductIntake(
    val product: ProductRecord,
    var intake: IntakeRecord
)