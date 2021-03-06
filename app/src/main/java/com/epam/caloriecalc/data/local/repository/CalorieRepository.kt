package com.epam.caloriecalc.data.local.repository

import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord
import com.epam.caloriecalc.data.local.relations.ProductWithIntakes
import kotlinx.coroutines.flow.Flow

interface CalorieRepository {
    suspend fun insertProduct(product: ProductRecord)

    suspend fun deleteProduct(product: ProductRecord)

    suspend fun insertIntake(intake: IntakeRecord): Long

    suspend fun deleteIntake(intake: IntakeRecord)

    suspend fun deleteIntakeById(id: Long)

    suspend fun getLastIntake(): IntakeRecord

    fun getProductById(productId: Int): Flow<ProductRecord>

    fun getAllProductHistory(): Flow<List<ProductRecord>>

    fun getIntakeById(intakeId: Int): Flow<IntakeRecord>

    fun getAllIntakeHistory(): Flow<List<ProductWithIntakes>>

    fun getAllIntakeInDateInterval(start: Long, end: Long): Flow<List<ProductWithIntakes>>
}