package com.epam.caloriecalc.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epam.caloriecalc.data.local.relations.IntakeWithProduct
import com.epam.caloriecalc.data.local.repository.CalorieRepository
import com.epam.caloriecalc.data.model.DailyStat
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.temporal.ChronoUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CalorieRepository
) : ViewModel() {

    val intakesTodayStats = MutableStateFlow(DailyStat())

    val intakesToday = repository.getAllIntakeInDateInterval(
        start = Instant.now().truncatedTo(ChronoUnit.DAYS).toEpochMilli(),
        end = Instant.now().toEpochMilli()
    )

    init {
        viewModelScope.launch {
            intakesTodayStats.value = calculateTodayStats(intakesToday.stateIn(this).value)
        }
    }

    private fun calculateTodayStats(intakeList: List<IntakeWithProduct>): DailyStat {
        val dailyStat = DailyStat()

        intakeList.forEach {
            it.product.apply {
                dailyStat.let { stat ->
                    stat.totalCalories += calories
                    stat.totalCarbs += carbohydrates
                    stat.totalFat += totalFat
                    stat.totalProtein += protein
                }
            }
        }

        return dailyStat
    }

}
