package com.epam.caloriecalc.data.local.util

import androidx.room.TypeConverter
import java.time.Instant
import java.util.*

class Converters {

    @TypeConverter
    fun fromTimestamp(value: Long): Instant {
        return Instant.ofEpochMilli(value)
    }

    @TypeConverter
    fun instantToTimestamp(instant: Instant): Long {
        return instant.toEpochMilli()
    }
}