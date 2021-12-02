package com.qwert2603.multi_public.common.di

import android.app.Application
import com.qwert2603.multi_public.common.util.StartedActivityProvider
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.java.KoinJavaComponent

actual class PlatformDi(private val application: Application) {

    actual fun createModule(): Module = module {
        single { application }
        single { StartedActivityProvider(get()) }
    }

    actual fun initApp() {
        val koin = KoinJavaComponent.getKoin()
        koin.get<StartedActivityProvider>().init()
    }
}