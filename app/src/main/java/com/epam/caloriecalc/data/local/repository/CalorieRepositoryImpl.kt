package com.epam.caloriecalc.data.local.repository

import com.epam.caloriecalc.data.local.dao.CalorieDao
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord
import com.epam.caloriecalc.data.local.relations.IntakeWithProduct
import kotlinx.coroutines.flow.Flow

class CalorieRepositoryImpl(
    private val dao: CalorieDao
) : CalorieRepository {
    override suspend fun insertProduct(product: ProductRecord) {
        return dao.insertProduct(product)
    }

    override suspend fun deleteProduct(product: ProductRecord) {
        return dao.deleteProduct(product)
    }

    override suspend fun insertIntake(intake: IntakeRecord) :Long {
        return dao.insertIntake(intake)
    }

    override suspend fun deleteIntake(intake: IntakeRecord) {
        return dao.deleteIntake(intake)
    }

    override suspend fun deleteIntakeById(id: Long) {
        return dao.deleteIntakeById(id)
    }

    override suspend fun getLastIntake(): IntakeRecord {
      return dao.getLastIntake()
    }

    override fun getProductById(productId: Int): Flow<ProductRecord> {
        return dao.getProductById(productId)
    }

    override fun getAllProductHistory(): Flow<List<ProductRecord>> {
        return dao.getAllProductHistory()
    }

    override fun getIntakeById(intakeId: Int): Flow<IntakeRecord> {
        return dao.getIntakeById(intakeId)
    }

    override fun getAllIntakeHistory(): Flow<List<IntakeWithProduct>> {
        return dao.getAllIntakeHistory()
    }

    override fun getAllIntakeInDateInterval(start: Long, end: Long): Flow<List<IntakeWithProduct>> {
        return dao.getAllIntakeInDateInterval(start, end)
    }
}