package com.epam.caloriecalc.data.local.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord
import com.epam.caloriecalc.util.Constants.PRODUCT_ID_COLUMN_NAME

data class IntakeWithProduct(
    @Embedded val product: ProductRecord,
    @Relation(
        parentColumn = PRODUCT_ID_COLUMN_NAME,
        entityColumn = PRODUCT_ID_COLUMN_NAME
    )
    val intake: IntakeRecord
)