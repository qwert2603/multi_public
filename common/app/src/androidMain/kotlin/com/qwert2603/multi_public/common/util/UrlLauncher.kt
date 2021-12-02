package com.qwert2603.multi_public.common.util

import android.content.Intent
import android.net.Uri
import com.qwert2603.multi_public.util.AppCoroutineScope
import com.qwert2603.multi_public.util.callForResult
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import org.koin.core.definition.Definition

actual val urlLauncherDefinition: Definition<UrlLauncher> get() = { UrlLauncherImpl(get(), get()) }

class UrlLauncherImpl(
    private val startedActivityProvider: StartedActivityProvider,
    private val appCoroutineScope: AppCoroutineScope,
) : UrlLauncher {

    override fun openUrl(url: String, onResult: ((Boolean) -> Unit)?) {
        callForResult {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

            appCoroutineScope.launch {
                val activity = startedActivityProvider.startedActivity.filterNotNull().first()
                activity.startActivity(intent)

                onResult?.invoke(true)
            }
        }

        onResult?.invoke(false)
    }
}