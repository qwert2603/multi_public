package com.qwert2603.multi_public.common.util

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import coil.compose.rememberImagePainter

@Composable
actual fun UrlImage(
    imageUrl: String?,
    placeHolder: ImageVector,
    modifier: Modifier,
) {
    Image(
        painter = rememberImagePainter(imageUrl) {
            // todo: placeHolder
        },
        contentDescription = null,
        modifier = modifier,
    )
}