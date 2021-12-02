package com.qwert2603.multi_public.common.di

import org.koin.core.context.startKoin

object Di {
    fun startDi(platformDi: PlatformDi) {
        startKoin {
            modules(listOf(
                appModule(),
                platformDi.createModule(),
            ))
        }
        platformDi.initApp()
    }
}