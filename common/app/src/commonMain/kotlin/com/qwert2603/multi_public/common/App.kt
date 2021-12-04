package com.qwert2603.multi_public.common

import androidx.compose.runtime.Composable
import com.qwert2603.multi_public.common.presentation.root.RootComponent
import com.qwert2603.multi_public.common.presentation.root.RootUi
import com.qwert2603.multi_public.design.theme.MultiPublicTheme

@Composable
fun App(rootComponent: RootComponent) {
    MultiPublicTheme {
        RootUi(rootComponent)
    }
}
