package com.epam.caloriecalc

import android.app.Application
import android.content.res.Resources
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CalorieCalcApplication : Application() {

    companion object {
        lateinit var instance: Application
        lateinit var appResources: Resources
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appResources = resources
    }
}
