package com.epam.caloriecalc.ui.details

import androidx.lifecycle.ViewModel
import com.epam.caloriecalc.data.local.repository.CalorieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val repository: CalorieRepository
) : ViewModel() {

}