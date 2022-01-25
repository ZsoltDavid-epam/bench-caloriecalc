package com.epam.caloriecalc.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.epam.caloriecalc.data.local.dao.CalorieDao
import com.epam.caloriecalc.data.local.entities.IntakeRecord
import com.epam.caloriecalc.data.local.entities.ProductRecord

@Database(entities = [ProductRecord::class, IntakeRecord::class], version = 1, exportSchema = false)
abstract class CalorieDatabase : RoomDatabase(){
    abstract val dao: CalorieDao
}