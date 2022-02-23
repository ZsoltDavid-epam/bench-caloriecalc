package com.epam.caloriecalc.ui.home

import android.os.Build.VERSION_CODES.Q
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.epam.caloriecalc.data.local.repository.FakeCalorieRepository
import com.epam.caloriecalc.data.model.DailyStat
import com.epam.caloriecalc.data.settings.SettingsManager
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Q])
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel

    private lateinit var repository: FakeCalorieRepository

    private val scope = TestCoroutineScope()

    private val settingsManager = SettingsManager(
        ApplicationProvider.getApplicationContext()
    )

    @Before
    fun setup() {
        repository = FakeCalorieRepository()
        repository.insertTestValues()

        viewModel = HomeViewModel(repository, settingsManager)
    }

    @Test
    fun calculateTodayStats() {
        val intakeTodayStats = viewModel.intakesTodayStats
        val stats = DailyStat()

        scope.launch {
            val list = repository.getAllIntakeHistory().first()
            list.forEach {
                with(it.product) {
                    stats.totalCalories += calories * it.intakes.size
                    stats.totalCarbs += carbohydrates * it.intakes.size
                    stats.totalFat += totalFat * it.intakes.size
                    stats.totalProtein += protein * it.intakes.size
                }
            }
        }
        assertThat(intakeTodayStats).isEqualTo(stats)
    }
}