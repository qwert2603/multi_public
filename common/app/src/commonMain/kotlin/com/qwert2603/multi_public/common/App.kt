package com.qwert2603.multi_public.common

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import com.arkivanov.essenty.lifecycle.resume
import com.qwert2603.multi_public.common.presentation.root.RootComponent
import com.qwert2603.multi_public.common.presentation.root.RootUi
import com.qwert2603.multi_public.design.theme.MultiPublicTheme

@Composable
fun App() {
    MultiPublicTheme {
        val lifecycleRegistry = LifecycleRegistry()
        lifecycleRegistry.resume()

        // todo: create RootComponent in platform
        val componentContext = DefaultComponentContext(lifecycleRegistry)
        val rootComponent = RootComponent(componentContext)
        RootUi(rootComponent)
    }
}
