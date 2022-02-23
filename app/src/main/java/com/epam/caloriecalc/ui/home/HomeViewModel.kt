package com.epam.caloriecalc.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epam.caloriecalc.data.local.relations.ProductWithIntakes
import com.epam.caloriecalc.data.local.repository.CalorieRepository
import com.epam.caloriecalc.data.model.DailyStat
import com.epam.caloriecalc.data.settings.SettingsManager
import com.epam.caloriecalc.ui.addnew.testitems
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    repository: CalorieRepository,
    settingsManager: SettingsManager
) : ViewModel() {

    var isLoading by mutableStateOf(true)
        private set

    val themeMode = settingsManager.themeMode

    var intakesTodayStats by mutableStateOf(DailyStat())
        private set

    val intakesToday = repository.getAllIntakeInDateInterval(
        start = Instant.now().truncatedTo(ChronoUnit.DAYS).toEpochMilli(),
        end = Instant.now().toEpochMilli()
    )

    init {
        viewModelScope.launch {
            intakesTodayStats = calculateTodayStats(intakesToday.stateIn(this).value)
            /* Delay for the splash screen loading */
            delay(1000L)
            isLoading = false
            if (repository.getAllProductHistory().first().isEmpty()) {
                for (item in testitems)
                    repository.insertProduct(item)
            }
        }
    }

    private fun calculateTodayStats(intakeList: List<ProductWithIntakes>): DailyStat {
        val dailyStat = DailyStat()

        intakeList.forEach {
            it.product.apply {
                dailyStat.let { stat ->
                    stat.totalCalories += (calories * it.intakes.size)
                    stat.totalCarbs += (carbohydrates * it.intakes.size)
                    stat.totalFat += (totalFat * it.intakes.size)
                    stat.totalProtein += (protein * it.intakes.size)
                }
            }
        }

        return dailyStat
    }

}
