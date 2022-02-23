package com.epam.caloriecalc.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord
import com.epam.caloriecalc.data.local.relations.ProductWithIntakes
import kotlinx.coroutines.flow.Flow

@Dao
interface CalorieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProduct(product: ProductRecord)

    @Delete
    suspend fun deleteProduct(product: ProductRecord)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIntake(intake: IntakeRecord): Long

    @Delete
    suspend fun deleteIntake(intake: IntakeRecord)

    @Query("SELECT * FROM product_table WHERE productId = :productId")
    fun getProductById(productId: Int): Flow<ProductRecord>

    @Query("SELECT * FROM product_table")
    fun getAllProductHistory(): Flow<List<ProductRecord>>

    @Query("SELECT * FROM intake_table ORDER BY timestamp DESC LIMIT 1;")
    suspend fun getLastIntake(): IntakeRecord

    @Transaction
    @Query("SELECT * FROM intake_table WHERE intakeId = :intakeId")
    fun getIntakeById(intakeId: Int): Flow<IntakeRecord>

    @Transaction
    @Query("SELECT * FROM product_table")
    fun getAllIntakeHistory(): Flow<List<ProductWithIntakes>>

    @Transaction
    @Query("SELECT * FROM product_table WHERE productId IN (SELECT productId FROM intake_table WHERE timestamp BETWEEN :start AND :end)")
    fun getAllIntakeInDateInterval(start: Long, end: Long): Flow<List<ProductWithIntakes>>

    @Query("DELETE FROM intake_table WHERE intakeId = :id")
    suspend fun deleteIntakeById(id: Long)
}