package com.epam.caloriecalc.data.model

import android.os.Parcelable
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductIntake(
    val product: ProductRecord,
    val intake: IntakeRecord
): Parcelable