package com.epam.caloriecalc.data.local.dao

import androidx.room.*
import com.epam.caloriecalc.data.local.relations.IntakeWithProduct
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord
import kotlinx.coroutines.flow.Flow

@Dao
interface CalorieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: ProductRecord)

    @Delete
    suspend fun deleteProduct(product: ProductRecord)

    @Insert
    suspend fun insertIntake(intake: IntakeRecord)

    @Delete
    suspend fun deleteIntake(intake: IntakeRecord)

    @Query("SELECT * FROM product_table WHERE productId = :productId")
    fun getProductById(productId: Int): Flow<ProductRecord>

    @Query("SELECT * FROM product_table")
    fun getAllProductHistory(): Flow<List<ProductRecord>>

    @Transaction
    @Query("SELECT * FROM intake_table WHERE intakeId = :intakeId")
    fun getIntakeById(intakeId: Int): Flow<IntakeRecord>

    @Transaction
    @Query("SELECT * FROM product_table")
    fun getAllIntakeHistory(): Flow<List<IntakeWithProduct>>

}