package com.epam.caloriecalc.ui.addnew

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.epam.caloriecalc.data.local.CalorieDatabase
import com.epam.caloriecalc.data.local.dao.CalorieDao
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class AddItemViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    private lateinit var dao: CalorieDao
    private lateinit var db: CalorieDatabase

    @Before
    fun createDb() {
        db = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().targetContext,
            CalorieDatabase::class.java
        ).setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor()).allowMainThreadQueries().build()
        dao = db.dao
        testScope.launch {
            for (productItem in testitems) {
                dao.insertProduct(productItem)
            }
            for (intakeItem in testIntakes) {
                dao.insertIntake(intakeItem)
            }
        }

    }

    @Test
    fun testItemAdd() = runBlockingTest {
        var itemsAfterAdd = IntakeRecord()
        testScope.launch {
            dao.insertIntake(IntakeRecord(intakeId = 34, productId = 12))
            itemsAfterAdd = dao.getLastIntake()

        }
        assertThat(itemsAfterAdd.intakeId).isEqualTo(34)
    }
}