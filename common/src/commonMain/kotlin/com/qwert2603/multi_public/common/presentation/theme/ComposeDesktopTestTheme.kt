package com.qwert2603.multi_public.common.presentation.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun MultiPublicTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        typography = MaterialTheme.typography.copy(
            h6 = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            ),
            caption = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
            ),
        ),
        content = content,
    )
}