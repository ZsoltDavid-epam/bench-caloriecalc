package com.epam.caloriecalc.data.local.repository

import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord
import com.epam.caloriecalc.data.local.relations.ProductWithIntakes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import java.time.Instant
import java.time.temporal.ChronoUnit

class FakeCalorieRepository : CalorieRepository {

    private val eggRecord =
        ProductWithIntakes(
            product = ProductRecord(
                productId = 15,
                name = "Egg",
                calories = 147f,
                totalFat = 9.7f,
                protein = 12.5f,
                carbohydrates = 0.7f,
                isFood = true
            ),
            intakes = listOf(
                IntakeRecord(
                    productId = 15,
                    intakeId = 30,
                    timestamp = Instant.now().minus(3, ChronoUnit.DAYS)
                ),
                IntakeRecord(
                    productId = 15,
                    intakeId = 31,
                    timestamp = Instant.now().minus(3, ChronoUnit.HOURS)
                ),
                IntakeRecord(
                    productId = 15,
                    intakeId = 32
                )
            )
        )


    private val appleRecord =
        ProductWithIntakes(
            product = ProductRecord(
                productId = 12,
                name = "Apple",
                calories = 53f,
                totalFat = 0.2f,
                protein = 0.3f,
                carbohydrates = 14.1f,
                isFood = true
            ),
            intakes = listOf(
                IntakeRecord(
                    productId = 12,
                    intakeId = 40,
                    timestamp = Instant.now().minus(4, ChronoUnit.DAYS)
                ),
                IntakeRecord(
                    productId = 12,
                    intakeId = 41,
                    timestamp = Instant.now().minus(4, ChronoUnit.HOURS)
                ),
                IntakeRecord(
                    productId = 12,
                    intakeId = 42
                ),
                IntakeRecord(
                    productId = 12,
                    intakeId = 38
                )
            )
        )


    private val productRecords = mutableListOf<ProductRecord>()
    private val intakeRecords = mutableListOf<IntakeRecord>()

    private var productWithIntakesList = flowOf(listOf(eggRecord, appleRecord))

    fun insertTestValues() {

        productRecords.apply {
            add(eggRecord.product)
            add(appleRecord.product)
        }

        intakeRecords.apply {
            eggRecord.intakes.forEach {
                add(it)
            }
            appleRecord.intakes.forEach {
                add(it)
            }
        }

        refreshFlow()
    }

    private fun refreshFlow() {
        productWithIntakesList = flow {
            val list = mutableListOf<ProductWithIntakes>()

            productRecords.forEach { product ->
                val intakes = intakeRecords.filter { it.productId == product.productId }
                list.add(ProductWithIntakes(product, intakes))
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

    override suspend fun insertIntake(intake: IntakeRecord): Long {
        intakeRecords.add(intake)
        refreshFlow()
        return intake.intakeId.toLong()
    }

    override suspend fun deleteIntake(intake: IntakeRecord) {
        intakeRecords.remove(intake)
        refreshFlow()
    }

    override suspend fun deleteIntakeById(id: Long) {
        intakeRecords.remove(intakeRecords.first { it.intakeId == id.toInt() })
        refreshFlow()
    }
    override suspend fun getLastIntake(): IntakeRecord {
        return intakeRecords[intakeRecords.size]
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

    override fun getAllIntakeHistory(): Flow<List<ProductWithIntakes>> {
        return productWithIntakesList
    }

    override fun getAllIntakeInDateInterval(start: Long, end: Long): Flow<List<ProductWithIntakes>> {
        return productWithIntakesList
    }
}