package com.epam.caloriecalc.data.settings

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.epam.caloriecalc.util.Constants
import com.epam.caloriecalc.util.Constants.SETTINGS_THEME_DEFAULT
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = Constants.SETTINGS_NAME)

class SettingsManager @Inject constructor(
   @ApplicationContext private val appContext: Context
) {

    private val settingsDataStore = appContext.dataStore

    suspend fun setThemeMode(mode: Int) {
        settingsDataStore.edit { settings ->
            settings[PreferencesKeys.THEME_MODE_KEY] = mode
        }
    }

    val themeMode: Flow<Int> = settingsDataStore.data.map { preferences ->
        preferences[PreferencesKeys.THEME_MODE_KEY] ?: SETTINGS_THEME_DEFAULT
    }


    private object PreferencesKeys {
        val THEME_MODE_KEY = intPreferencesKey("theme_mode_key")
    }
}