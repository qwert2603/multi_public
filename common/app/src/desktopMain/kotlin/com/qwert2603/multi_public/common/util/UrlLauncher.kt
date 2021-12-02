package com.qwert2603.multi_public.common.util

import com.qwert2603.multi_public.util.callForResult
import org.koin.core.definition.Definition
import java.awt.Desktop
import java.net.URI

actual val urlLauncherDefinition: Definition<UrlLauncher> get() = { UrlLauncherImpl() }

class UrlLauncherImpl : UrlLauncher {

    override fun openUrl(url: String): Boolean {
        if (!Desktop.isDesktopSupported()) return false

        val desktop = Desktop.getDesktop()

        if (!desktop.isSupported(Desktop.Action.BROWSE)) return false

        callForResult {
            desktop.browse(URI.create(url))
            return true
        }

        return false
    }
}