package com.example.weatherapp.util

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object DateUtil {

    fun LocalDate.formatCurrentDay(): String {
        return this.format(DateTimeFormatter.ofPattern("MMM dd"))
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
            .filter { it != '.' }
    }

    fun LocalDate.formatDayOfWeek(): String {
        return this.dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }
}