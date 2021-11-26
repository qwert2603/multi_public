package com.qwert2603.multi_public.android

import android.app.Application
import com.qwert2603.multi_public.common.di.Di

class MultiPublicApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Di.startDi()
    }
}