package com.epam.caloriecalc.di

import android.app.Application
import androidx.room.Room
import com.epam.caloriecalc.data.local.CalorieDatabase
import com.epam.caloriecalc.data.local.repository.CalorieRepository
import com.epam.caloriecalc.data.local.repository.CalorieRepositoryImpl
import com.epam.caloriecalc.data.remote.FakeApi
import com.epam.caloriecalc.data.settings.SettingsManager
import com.epam.caloriecalc.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): CalorieDatabase =
        Room.databaseBuilder(
            app,
            CalorieDatabase::class.java,
            Constants.CALORIE_DATABASE_NAME
        ).build()

    @Provides
    @Singleton
    fun provideCalorieRepository(db: CalorieDatabase): CalorieRepository =
        CalorieRepositoryImpl(db.dao)


    @Provides
    @Singleton
    fun provideSettingsManager(app: Application): SettingsManager =
        SettingsManager(app)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(FakeApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit) =
        retrofit.create(FakeApi::class.java)

}
