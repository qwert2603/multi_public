package com.qwert2603.multi_public.util

sealed interface LoadingState<out E, out T> {
    object None : LoadingState<Nothing, Nothing>
    object Loading : LoadingState<Nothing, Nothing>
    data class Error<out E>(val error: E) : LoadingState<E, Nothing>
    data class Success<out T>(val data: T) : LoadingState<Nothing, T>

    companion object
}

fun LoadingState.Companion.Error(): LoadingState<Unit, Nothing> = LoadingState.Error(Unit)

val LoadingState<*, *>.isError: Boolean get() = this is LoadingState.Error<*>

val <T>LoadingState<*, T>.dataOrNull: T? get() = (this as? LoadingState.Success<T>)?.data