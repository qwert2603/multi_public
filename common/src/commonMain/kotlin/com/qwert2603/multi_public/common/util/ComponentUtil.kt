package com.qwert2603.multi_public.common.util

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.essenty.lifecycle.doOnDestroy
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

fun ComponentContext.createComponentScope() = CoroutineScope(Dispatchers.Main + SupervisorJob())
    .also { scope -> lifecycle.doOnDestroy(scope::cancel) }