package com.epam.caloriecalc.util

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Instant.toLocalDate(): LocalDate =
    this.atZone(ZoneId.systemDefault()).toLocalDate()

fun Instant.toPatternString(pattern: String): String =
    DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault())
        .format(this)
