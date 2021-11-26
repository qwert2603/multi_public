package com.qwert2603.multi_public.common.util

import kotlinx.coroutines.CancellationException

sealed interface CallResult<out T> {

    data class Error(val error: Throwable) : CallResult<Nothing> {
        init {
            error.printStackTrace()
        }
    }

    data class Success<out T>(val data: T) : CallResult<T>
}

inline fun <T> callForResult(action: () -> T) = try {
    CallResult.Success(action())
} catch (cancellationException: CancellationException) {
    throw cancellationException
} catch (t: Throwable) {
    CallResult.Error(t)
}

fun <T> CallResult<T>.getOrNull() = when (this) {
    is CallResult.Error -> null
    is CallResult.Success -> data
}