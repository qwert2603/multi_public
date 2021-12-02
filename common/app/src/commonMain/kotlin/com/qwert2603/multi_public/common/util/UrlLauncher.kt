package com.qwert2603.multi_public.common.util

import org.koin.core.definition.Definition

expect val urlLauncherDefinition: Definition<UrlLauncher>

interface UrlLauncher {
    fun openUrl(url: String): Boolean
}