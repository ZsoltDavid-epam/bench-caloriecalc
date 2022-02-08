package com.epam.caloriecalc.data.local.repository

import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord
import com.epam.caloriecalc.data.local.relations.IntakeWithProduct
import kotlinx.coroutines.flow.Flow

interface CalorieRepository {
    suspend fun insertProduct(product: ProductRecord)

    suspend fun deleteProduct(product: ProductRecord)

    suspend fun insertIntake(intake: IntakeRecord)

    suspend fun deleteIntake(intake: IntakeRecord)

    fun getProductById(productId: Int): Flow<ProductRecord>

    fun getAllProductHistory(): Flow<List<ProductRecord>>

    fun getIntakeById(intakeId: Int): Flow<IntakeRecord>

    fun getAllIntakeHistory(): Flow<List<IntakeWithProduct>>

    fun getAllIntakeInDateInterval(start: Long, end: Long): Flow<List<IntakeWithProduct>>
}