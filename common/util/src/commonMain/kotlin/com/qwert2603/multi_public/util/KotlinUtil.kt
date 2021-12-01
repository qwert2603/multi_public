package com.qwert2603.multi_public.util

import kotlinx.coroutines.Job
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

@Suppress("UNUSED")
val Any?.allCases
    get() = Unit

fun <T> ifOrNull(condition: Boolean, valueProvider: () -> T?): T? = if (condition) valueProvider() else null

fun switchJob() = switchObject(Job::cancel)

fun <T> switchObject(cancelPrev: (T) -> Unit) = object : ReadWriteProperty<Any?, T?> {

    var t: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T? {
        return t
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T?) {
        t?.apply(cancelPrev)
        t = value
    }
}