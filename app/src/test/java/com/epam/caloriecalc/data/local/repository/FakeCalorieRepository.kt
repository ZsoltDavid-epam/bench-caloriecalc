package com.epam.caloriecalc.data.local.repository

import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord
import com.epam.caloriecalc.data.local.relations.IntakeWithProduct
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class FakeCalorieRepository : CalorieRepository {

    private val productRecords = mutableListOf<ProductRecord>()
    private val intakeRecords = mutableListOf<IntakeRecord>()
    private var intakeWithProductRecords = flowOf(listOf<IntakeWithProduct>())

    fun insertTestValues() {
        productRecords.apply {
            add(
                ProductRecord(
                    productId = 12,
                    name = "Apple",
                    calories = 53f,
                    totalFat = 0.2f,
                    protein = 0.3f,
                    carbohydrates = 14.1f,
                    isFood = true
                )
            )

            add(
                ProductRecord(
                    productId = 15,
                    name = "Egg",
                    calories = 147f,
                    totalFat = 9.7f,
                    protein = 12.5f,
                    carbohydrates = 0.7f,
                    isFood = true
                )
            )
        }

        intakeRecords.apply {
            add(
                IntakeRecord(
                    productId = 15,
                    intakeId = 35
                )
            )

            add(
                IntakeRecord(
                    productId = 12,
                    intakeId = 33
                )
            )
        }

        refreshFlow()
    }

    private fun refreshFlow() {
        intakeWithProductRecords = flow {
            val list = mutableListOf<IntakeWithProduct>()
            productRecords.forEachIndexed { index, record ->
                list.add(
                    IntakeWithProduct(
                        record,
                        intakeRecords[index]
                    )
                )
            }

            emit(list)
        }
    }

    override suspend fun insertProduct(product: ProductRecord) {
        productRecords.add(product)
    }

    override suspend fun deleteProduct(product: ProductRecord) {
        productRecords.remove(product)
    }

    override suspend fun insertIntake(intake: IntakeRecord) {
        intakeRecords.add(intake)
        refreshFlow()
    }

    override suspend fun deleteIntake(intake: IntakeRecord) {
        intakeRecords.remove(intake)
        refreshFlow()
    }

    override fun getProductById(productId: Int): Flow<ProductRecord> {
        return flowOf(productRecords.first { it.productId == productId })
    }

    override fun getAllProductHistory(): Flow<List<ProductRecord>> {
        return flowOf(productRecords)
    }

    override fun getIntakeById(intakeId: Int): Flow<IntakeRecord> {
        return flowOf(intakeRecords.first { it.intakeId == intakeId })
    }

    override fun getAllIntakeHistory(): Flow<List<IntakeWithProduct>> {
        return intakeWithProductRecords
    }

    override fun getAllIntakeInDateInterval(start: Long, end: Long): Flow<List<IntakeWithProduct>> {
        return intakeWithProductRecords
    }
}