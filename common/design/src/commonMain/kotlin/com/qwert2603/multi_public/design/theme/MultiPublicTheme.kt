package com.qwert2603.multi_public.design.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun MultiPublicTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = MultiPublicTypography,
        content = content,
    )
}