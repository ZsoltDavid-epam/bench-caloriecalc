package com.epam.caloriecalc.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.epam.caloriecalc.data.local.CalorieDatabase
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord
import com.epam.caloriecalc.data.local.relations.IntakeWithProduct
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant.now

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class CalorieDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: CalorieDatabase
    private lateinit var dao: CalorieDao

    private lateinit var testProduct: ProductRecord
    private lateinit var testIntake: IntakeRecord

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CalorieDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.dao

        testProduct = ProductRecord(
            productId = 12,
            name = "Apple",
            calories = 53f,
            totalFat = 0.2f,
            protein = 0.3f,
            carbohydrates = 14.1f,
            isFood = true
        )

        testIntake = IntakeRecord(
            productId = testProduct.productId,
            intakeId = 33,
            timestamp = now()
        )
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertProductRecord() = runBlockingTest {
        dao.insertProduct(testProduct)

        val allProducts = dao.getAllProductHistory().first()

        assertThat(allProducts.contains(testProduct))
    }

    @Test
    fun deleteProductRecord() = runBlockingTest {
        dao.insertProduct(testProduct)
        dao.deleteProduct(testProduct)

        val allProducts = dao.getAllProductHistory().first()

        assertThat(allProducts).doesNotContain(testProduct)
    }

    @Test
    fun insertIntakeAfterProduct() = runBlockingTest {
        val intakeWithProduct = IntakeWithProduct(testProduct, testIntake)

        dao.insertProduct(testProduct)
        dao.insertIntake(testIntake)

        val allIntakes = dao.getAllIntakeHistory().first()

        assertThat(allIntakes.contains(intakeWithProduct))
    }

    @Test
    fun deleteIntakeWithProduct() = runBlockingTest {
        val intakeWithProduct = IntakeWithProduct(testProduct, testIntake)

        dao.insertProduct(testProduct)
        dao.insertIntake(testIntake)

        dao.deleteProduct(testProduct)

        val allIntakes = dao.getAllIntakeHistory().first()

        assertThat(allIntakes).doesNotContain(intakeWithProduct)
    }

    @Test
    fun getProductById() = runBlockingTest {
        dao.insertProduct(testProduct)

        val insertedProduct = dao.getProductById(testProduct.productId).first()

        assertThat(insertedProduct).isEqualTo(testProduct)
    }

    @Test
    fun getIntakeById() = runBlockingTest {
        dao.insertProduct(testProduct)
        dao.insertIntake(testIntake)

        val insertedIntake = dao.getIntakeById(testIntake.intakeId).first()

        assertThat(insertedIntake).isEqualTo(testIntake)
    }
}