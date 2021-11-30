package com.qwert2603.multi_public.design.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import coil.compose.rememberImagePainter

@Composable
actual fun UrlImage(
    imageUrl: String?,
    placeHolder: ImageVector,
    modifier: Modifier,
) {
    Box(modifier) {
        var imageLoaded by remember(imageUrl) { mutableStateOf(false) }

        Image(
            painter = rememberImagePainter(imageUrl) {
                listener(onSuccess = { _, _ -> imageLoaded = true })
            },
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )
        if (!imageLoaded) {
            Image(
                imageVector = placeHolder,
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}