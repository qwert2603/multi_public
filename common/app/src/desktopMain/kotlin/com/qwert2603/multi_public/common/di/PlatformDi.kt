package com.qwert2603.multi_public.common.di

import org.koin.core.module.Module
import org.koin.dsl.module

actual class PlatformDi {
    internal actual fun createModule(): Module = module { }
    internal actual fun initApp() = Unit
}