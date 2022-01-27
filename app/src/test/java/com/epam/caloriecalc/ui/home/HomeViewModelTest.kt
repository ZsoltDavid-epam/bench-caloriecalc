package com.epam.caloriecalc.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.epam.caloriecalc.data.local.repository.FakeCalorieRepository
import com.epam.caloriecalc.data.model.DailyStat
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel

    private lateinit var repository: FakeCalorieRepository

    private val scope = TestCoroutineScope()

    @Before
    fun setup() {
        repository = FakeCalorieRepository()
        repository.insertTestValues()

        viewModel = HomeViewModel(repository)
    }

    @Test
    fun calculateTodayStats() {
        val intakeTodayStats = viewModel.intakesTodayStats.value
        val stats = DailyStat()

        scope.launch {
            val list = repository.getAllProductHistory().first()
            list.forEach {
                stats.totalCalories += it.calories
                stats.totalCarbs += it.carbohydrates
                stats.totalFat += it.totalFat
                stats.totalProtein += it.protein
            }
        }
        assertThat(intakeTodayStats).isEqualTo(stats)
    }
}