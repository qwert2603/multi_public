package com.qwert2603.multi_public.design.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
expect fun UrlImage(
    imageUrl: String?,
    placeHolder: ImageVector?,
    modifier: Modifier = Modifier,
)