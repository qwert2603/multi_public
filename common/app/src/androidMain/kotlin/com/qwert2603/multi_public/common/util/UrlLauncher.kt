package com.qwert2603.multi_public.common.util

import android.content.Context
import org.koin.core.definition.Definition

actual val urlLauncherDefinition: Definition<UrlLauncher> get() = { UrlLauncherImpl(get()) }

class UrlLauncherImpl(private val context: Context) : UrlLauncher {

    override fun openUrl(url: String): Boolean {
        return false
    }
}