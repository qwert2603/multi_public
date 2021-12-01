package com.qwert2603.multi_public.common.data

import java.time.Instant
import java.time.ZoneOffset
import java.time.ZonedDateTime

object ServiceMapperUtil {
    fun parseZonedDateTime(epochSecond: Long): ZonedDateTime = Instant.ofEpochSecond(epochSecond).atZone(ZoneOffset.UTC)
}