package com.qwert2603.multi_public.common

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.qwert2603.multi_public.common.presentation.root.RootComponent
import com.qwert2603.multi_public.common.presentation.root.RootUi
import com.qwert2603.multi_public.common.presentation.theme.MultiPublicTheme

@Composable
fun App() {
    MultiPublicTheme {
        val lifecycleRegistry = LifecycleRegistry()
        lifecycleRegistry.resume()

        val componentContext = DefaultComponentContext(lifecycleRegistry)
        val rootComponent = RootComponent(componentContext)
        RootUi(rootComponent)
    }
}
