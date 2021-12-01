package com.qwert2603.multi_public.util

import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object DateTimeUtil {
    fun formatDateTime(zonedDateTime: ZonedDateTime): String = zonedDateTime
        .withZoneSameInstant(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
}