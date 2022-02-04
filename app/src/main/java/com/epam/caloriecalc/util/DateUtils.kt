package com.epam.caloriecalc.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

fun Instant.toLocalDate() : LocalDate {
    val zonedDateTime = this.atZone(ZoneId.systemDefault())

    return zonedDateTime.toLocalDate()
}