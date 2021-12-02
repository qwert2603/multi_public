package com.qwert2603.multi_public.android

import android.app.Application
import com.qwert2603.multi_public.common.di.Di
import com.qwert2603.multi_public.common.di.PlatformDi

class MultiPublicApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val platformDi = PlatformDi(application = this)
        Di.startDi(platformDi)
    }
}