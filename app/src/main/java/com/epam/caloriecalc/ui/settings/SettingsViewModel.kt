package com.epam.caloriecalc.ui.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.epam.caloriecalc.data.settings.SettingsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val settingsManager: SettingsManager
) : ViewModel() {

    private val _themeState = MutableStateFlow(0)
    val themeState = _themeState.asStateFlow()

    init {
        viewModelScope.launch {
            _themeState.value = settingsManager.themeMode.stateIn(viewModelScope).value
        }
    }

    fun changeThemeMode(value: Int) {
        viewModelScope.launch {
            settingsManager.setThemeMode(value)
        }
    }
}