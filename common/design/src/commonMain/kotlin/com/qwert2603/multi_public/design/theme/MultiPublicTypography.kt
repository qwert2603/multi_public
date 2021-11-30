package com.qwert2603.multi_public.design.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

internal val MultiPublicTypography
    @Composable get() = MaterialTheme.typography.copy(
        h6 = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        ),
        body1 = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.Normal,
        ),
        caption = TextStyle(
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
        ),
    )