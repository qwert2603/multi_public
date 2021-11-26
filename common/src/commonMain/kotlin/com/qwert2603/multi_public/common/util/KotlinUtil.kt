package com.qwert2603.multi_public.common.util

@Suppress("UNUSED")
val Any?.allCases
    get() = Unit

fun <T> ifOrNull(condition: Boolean, valueProvider: () -> T?): T? = if (condition) valueProvider() else null